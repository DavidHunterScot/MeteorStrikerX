package scot.davidhunter.meteorstrikerx.ui;

import javax.swing.JFrame;

import scot.davidhunter.meteorstrikerx.constants.Constants;
import scot.davidhunter.meteorstrikerx.image.ImageFactory;
import scot.davidhunter.meteorstrikerx.image.ImageType;

public class GameMainFrame extends JFrame
{
	public GameMainFrame()
	{
		initialize();
	}
	
	private void initialize()
	{
		// we can add the panels to the main frame.
		add( new GamePanel() );
		
		pack();
		
		// define title of the frame...
		setTitle( Constants.GAME_TITLE );
		// icon (logo)
		setIconImage( ImageFactory.createImage( ImageType.LOGO ).getImage() );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setLocationRelativeTo( null );
		setResizable( false );
		setVisible( true );
	}
}
