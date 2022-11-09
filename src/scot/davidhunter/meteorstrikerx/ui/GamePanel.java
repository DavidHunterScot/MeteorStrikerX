package scot.davidhunter.meteorstrikerx.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import scot.davidhunter.meteorstrikerx.callbacks.GameEventListener;
import scot.davidhunter.meteorstrikerx.constants.Constants;
import scot.davidhunter.meteorstrikerx.constants.GameVariables;
import scot.davidhunter.meteorstrikerx.objects.Background;
import scot.davidhunter.meteorstrikerx.objects.Laser;
import scot.davidhunter.meteorstrikerx.objects.Meteor;
import scot.davidhunter.meteorstrikerx.objects.SpaceShip;
import scot.davidhunter.meteorstrikerx.random.RandomGenerator;

public class GamePanel extends JPanel
{
	private Timer timer;
	private SpaceShip spaceShip;
	private Background background;
	private List<Laser> lasers;
	private List<Meteor> meteors;
	private RandomGenerator randomGenerator;
	private CollisionDetector collisionDetector;
	
	public GamePanel()
	{
		initializeVariables();
		initializeLayout();
		startAnimation();
	}
	
	private void initializeVariables()
	{
		addKeyListener( new GameEventListener( this ) );
		spaceShip = new SpaceShip();
		background = new Background( 0, 0 );
		lasers = new ArrayList<>();
		meteors = new ArrayList<>();
		randomGenerator = new RandomGenerator();
		collisionDetector = new CollisionDetector();
	}
	
	private void startAnimation()
	{
		// we want to update + repaint the canvas in every N seconds
		// how many ms to wait.
		timer = new Timer( Constants.GAME_SPEED, new GameLoop( this ) );
		timer.start();
	}
	
	private void initializeLayout()
	{
		setPreferredSize( new Dimension( Constants.GAME_WIDTH, Constants.GAME_HEIGHT ) );
		setFocusable( true );
	}
	
	@Override
	protected void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		handleCanvas( g );
	}
	
	private void handleCanvas( Graphics g )
	{
		if ( GameVariables.IN_GAME )
		{
			handleBackground( g );
			handleSpaceShip( g );
			handleLaserBeams( g );
			handleMeteors( g );
			handleScoreAndShields( g );
		}
		else
		{
			// This is the game over case.
			if ( timer.isRunning() )
				timer.stop();
			
			gameOver( g );
		}
		
		// we want to sync the canvas.
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void handleScoreAndShields( Graphics g )
	{
		if ( ! GameVariables.IN_GAME )
			return;
		
		Font font = new Font( "Helvetica", Font.BOLD, 20 );
		g.setColor( Color.GRAY );
		g.setFont( font );
		g.drawString( "Score: " + GameVariables.SCORE, Constants.GAME_WIDTH - 150, 50 );
		g.drawString( "Shields: " + GameVariables.SHIELDS, 50, 50 );
	}
	
	private void gameOver( Graphics g )
	{
		// want to move the background
		background.update( g );
		
		// GAME OVER !!!
		Font font = new Font( "Helvetica", Font.BOLD, 50 );
		FontMetrics fontMetrics = getFontMetrics( font );
		
		// Draw game over
		g.setColor( Color.WHITE );
		g.setFont( font );
		g.drawString( Constants.GAME_OVER, Constants.GAME_WIDTH / 2 - fontMetrics.stringWidth( Constants.GAME_OVER ) / 2, Constants.GAME_HEIGHT / 2 - 100 );
		
		// Draw the score
		String score = Constants.GAME_OVER_SCORE.replace( Constants.GAME_OVER_SCORE_INDICATOR, GameVariables.SCORE + "" );
		g.setColor( Color.YELLOW );
		g.drawString( score, Constants.GAME_WIDTH / 2 - fontMetrics.stringWidth( score ) / 2, Constants.GAME_HEIGHT - 300 );
	}
	
	private void handleMeteors( Graphics g )
	{
		for ( Meteor meteor : meteors )
			meteor.update( g );
	}
	
	private void handleLaserBeams( Graphics g )
	{
		for ( Laser laser : lasers )
			if ( ! laser.isDead() )
				laser.update( g );
	}
	
	private void handleBackground( Graphics g )
	{
		background.update( g );
	}
	
	private void handleSpaceShip( Graphics g )
	{
		spaceShip.update( g );
	}
	
	public void loop()
	{
		// update
		update();
		// repaint the canvas
		repaint();
	}
	
	private void update()
	{
		if ( spaceShip.isDead() )
		{
			GameVariables.IN_GAME = false;
			return;
		}
		
		// detect collisions
		// check whether the game is over
		// generate the meteors at random
		if ( randomGenerator.isMeteorGenerated() )
		{
			int randomX = randomGenerator.generateRandomX();
			int randomY = 0 - Constants.METEOR_HEIGHT;
			meteors.add( new Meteor( randomX, randomY ) );
		}
		
		// Check whether meteors reach the bottom of the canvas.
		// if one of the meteors reaches the bottom of the screen
		// it is game over (space ship sies automatically)
		for ( Meteor meteor : meteors )
			if ( meteor.isDead() )
				spaceShip.die();
			
		// Check whether to remove dead laser beams.
		List<Laser> destroyedLasers = new ArrayList<>();
		
		for ( Laser laser : lasers )
			if ( laser.isDead() )
				destroyedLasers.add( laser );
			
		lasers.removeAll( destroyedLasers );
		
		// Check the collisions
		// Collision between the lasers and the meteors.
		Meteor destroyedMeteor = null;
		Laser destroyedLaser = null;
		
		// brute-force approach because we have to consider all the meteor-laser pairs.
		// 0(M*N) M: number of meteors N: number of lasers.
		for ( Laser laser : lasers )
		{
			if ( ! laser.isDead() )
			{
				for ( Meteor meteor : meteors )
				{
					if ( collisionDetector.spriteCollision( laser, meteor ) )
					{
						destroyedMeteor = meteor;
						destroyedLaser = laser;
						GameVariables.SCORE += 20;
					}
				}
				
				meteors.remove( destroyedMeteor );
			}
		}
		
		lasers.remove( destroyedLaser );
		
		// Detect the collisions between meteors and the spaceship.
		destroyedMeteor = null;
		
		for ( Meteor meteor : meteors )
		{
			if ( collisionDetector.spriteCollision( meteor, spaceShip ) )
			{
				destroyedMeteor = meteor;
				GameVariables.SHIELDS--;
				
				if ( GameVariables.SHIELDS < 0 )
					spaceShip.die();
			}
		}
		
		meteors.remove( destroyedMeteor );
	}
	
	public void keyPressed( KeyEvent e )
	{
		spaceShip.keyPressed( e );
		
		// when the user hits the 'space' this is when the
		// laser beams are generated.
		int key = e.getKeyCode();
		
		if ( key == KeyEvent.VK_SPACE )
		{
			if ( GameVariables.IN_GAME )
			{
				int x = spaceShip.getX();
				int y = spaceShip.getY();
				
				lasers.add( new Laser( x, y ) );
			}
		}
	}
	
	public void keyReleased( KeyEvent e )
	{
		spaceShip.keyReleased( e );
	}
}
