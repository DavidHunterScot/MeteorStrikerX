package scot.davidhunter.meteorstrikerx.objects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import scot.davidhunter.meteorstrikerx.constants.Constants;

public class SpaceShip extends Sprite
{
	// the user is able to move the spaceship on the x axis (horizontally).
	private int dx;
	private List<ImageIcon> frames;
	private int imageIndex;
	
	public SpaceShip()
	{
		this.frames = new ArrayList<>();
		for ( int f = 0; f < Constants.SPACESHIP_ANIMATION_FRAMES; f++ )
			frames.add( new ImageIcon( Constants.SPACESHIP_ANIMATION_URL_TEMPLATE.replace( Constants.SPACESHIP_ANIMATION_FRAME_INDICATOR + "", f + "" ) ) );
		
		// set the first frame as the initial image.
		setImage( frames.get( 0 ).getImage() );
		
		// set the initial (x,y) location of the spaceship.
		int initialX = Constants.GAME_WIDTH / 2 - Constants.SPACESHIP_WIDTH / 2;
		int initialY = Constants.GAME_HEIGHT - Constants.SPACESHIP_HEIGHT - 10;
		
		setX( initialX );
		setY( initialY );
	}
	
	@Override
	protected void update()
	{
		// update the location (x coordingate) of the ship.
		x += dx;
		
		// check the boundaries
		// can not go beyond the canvas on the left side.
		if ( x <= 0 )
			x = 0;
		if ( x >= Constants.GAME_WIDTH - Constants.SPACESHIP_WIDTH )
			x = Constants.GAME_WIDTH - Constants.SPACESHIP_WIDTH;
		
		// update the frame (actual image) in every iteration.
		imageIndex++;
		
		// reinitialize the index if it is out of bounds.
		if ( imageIndex > frames.size() - 1 )
			imageIndex = 0;
	}
	
	@Override
	protected void act( Graphics g )
	{
		// This is when we show the spaceship (paint on the canvas).
		setImage( frames.get( imageIndex ).getImage() );
		g.drawImage( getImage(), x, y, null );
	}
	
	public void keyPressed( KeyEvent e )
	{
		int key = e.getKeyCode();
		
		// the user hits the left arrow
		if ( key == KeyEvent.VK_LEFT )
			dx = -3;
		// the user hits the right arrow
		if ( key == KeyEvent.VK_RIGHT )
			dx = 3;
	}
	
	public void keyReleased( KeyEvent e )
	{
		// we have to handle keyReleased events as well - otherwise the spaceship
		// would not be able to stand still
		
		int key = e.getKeyCode();
		
		// the user hits the left arrow
		if ( key == KeyEvent.VK_LEFT )
			dx = 0;
		// the user hits the right arrow
		if ( key == KeyEvent.VK_RIGHT )
			dx = 0;
	}
	
}
