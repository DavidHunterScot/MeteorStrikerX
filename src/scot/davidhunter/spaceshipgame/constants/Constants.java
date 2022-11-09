package scot.davidhunter.spaceshipgame.constants;

public class Constants
{
	// we can not instantiate this class.
	private Constants()
	{
		
	}
	
	public static final int GAME_WIDTH = 420;
	public static final int GAME_HEIGHT = 746;
	
	public static final int SPACESHIP_WIDTH = 75;
	public static final int SPACESHIP_HEIGHT = 104;
	
	public static final int LASER_WIDTH = 12;
	public static final int LASER_HEIGHT = 62;
	
	public static final int LASER_SHOT_SPEED = 4;
	
	public static final double METEOR_PROBABILITY = 0.05;
	public static final int METEOR_SPEED = 2;
	public static final int METEOR_WIDTH = 48;
	public static final int METEOR_HEIGHT = 82;
	
	public static final String GAME_TITLE = "Space Ship Game";
	
	// constants for the images
	public static final String IMAGES_BASE_URL = "resources/images/";
	public static final String LOGO_URL = IMAGES_BASE_URL + "meteor.png";
	public static final String BACKGROUND_URL = IMAGES_BASE_URL + "background.png";
	public static final String LASER_URL = IMAGES_BASE_URL + "laser_shot.png";
	
	// meteors
	public static final String METEOR_ANIMATION_URL_TEMPLATE = IMAGES_BASE_URL + "meteor_#.png";
	public static final String METEOR_ANIMATION_FRAME_INDICATOR = "#";
	public static final int METEOR_ANIMATION_FRAMES = 10;
	
	// spaceship
	public static final String SPACESHIP_ANIMATION_URL_TEMPLATE = IMAGES_BASE_URL + "spaceship_#.png";
	public static final String SPACESHIP_ANIMATION_FRAME_INDICATOR = "#";
	public static final int SPACESHIP_ANIMATION_FRAMES = 4;
	
	// speed of the game (in FPS).
	// it is equivalent to 67 FPS
	public static final int GAME_SPEED = 15;
	
	public static final String GAME_OVER = "GAME OVER";
	public static final String GAME_OVER_SCORE = "Score: #";
	public static final String GAME_OVER_SCORE_INDICATOR = "#";
}
