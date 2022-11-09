package scot.davidhunter.spaceshipgame.objects;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import scot.davidhunter.spaceshipgame.constants.Constants;
import scot.davidhunter.spaceshipgame.image.ImageFactory;
import scot.davidhunter.spaceshipgame.image.ImageType;

public class Laser extends Sprite
{
	public Laser()
	{
		
	}
	
	public Laser( int x, int y )
	{
		this.x = x;
		this.y = y;
		initialize();
	}
	
	private void initialize()
	{
		// initialize the image.
		ImageIcon image = ImageFactory.createImage( ImageType.LASER );
		setImage( image.getImage() );
		
		// set the (x, y) position.
		setX( x + Constants.SPACESHIP_WIDTH / 2 - Constants.LASER_WIDTH / 2 );
		setY( y - Constants.LASER_HEIGHT );
	}
	
	@Override
	protected void update()
	{
		// laser beams travels vertically from the spaceship towards
		// the upper region of the canvas.
		this.y -= Constants.LASER_SHOT_SPEED;
		
		// if the laser is outside the scope of the screen then we have to
		// remove it.
		if ( y < 0 )
			die();
	}
	
	@Override
	protected void act( Graphics g )
	{
		g.drawImage( getImage(), x, y, null );
	}
	
}
