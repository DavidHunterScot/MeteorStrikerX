package scot.davidhunter.spaceshipgame.objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import scot.davidhunter.spaceshipgame.constants.Constants;

public class Meteor extends Sprite
{
	private List<ImageIcon> frames;
	private int imageIndex;
	
	public Meteor( int x, int y )
	{
		this.x = x;
		this.y = y;
		initialize();
	}
	
	private void initialize()
	{
		frames = new ArrayList<>();
		for ( int f = 0; f < Constants.METEOR_ANIMATION_FRAMES; f++ )
			frames.add( new ImageIcon( Constants.METEOR_ANIMATION_URL_TEMPLATE.replace( Constants.METEOR_ANIMATION_FRAME_INDICATOR + "", ( f + 1 ) + "" ) ) );
		setImage( frames.get( 0 ).getImage() );
	}
	
	@Override
	protected void update()
	{
		// we have to move the meteor from top to bottom
		// increment the y position.
		y += Constants.METEOR_SPEED;
		
		// if the meteor is outside the scope of the canvas we have
		// to remove the meteor.
		// THIS IS THE END OF THE GAME !!!
		if ( y >= Constants.GAME_HEIGHT )
			die();
		
		// we have to update the index of the images.
		imageIndex++;
		
		if ( imageIndex > frames.size() - 1 )
			imageIndex = 0;
	}
	
	@Override
	protected void act( Graphics g )
	{
		g.drawImage( frames.get( imageIndex ).getImage(), x, y, null );
	}
}
