package scot.davidhunter.meteorstrikerx.app;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import scot.davidhunter.meteorstrikerx.ui.GameMainFrame;

public class App
{
	public static void main( String[] args )
	{
		// we make sure that the application will use the underlying OS related look and
		// feel.
		try
		{
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		}
		catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e )
		{
			e.printStackTrace();
		}
		
		// start the application on a distinct thread so we run the application on the
		// Event Dispatch Thread (EDT).
		EventQueue.invokeLater( () ->
		{
			new GameMainFrame();
		} );
	}
}
