package scot.davidhunter.meteorstrikerx.ui;

import scot.davidhunter.meteorstrikerx.constants.Constants;
import scot.davidhunter.meteorstrikerx.objects.Sprite;

public class CollisionDetector
{
	// detect the collisions between the meteors and the laser beams.
	// it returns true if there is a collision.
	public boolean collisionLaserMeteor( Sprite laser, Sprite meteor )
	{
		int shotX = laser.getX();
		int shotY = laser.getY();
		int meteorX = meteor.getX();
		int meteorY = meteor.getY();
		
		return shotX < meteorX + Constants.METEOR_WIDTH && meteorX < shotX + Constants.LASER_WIDTH && shotY < meteorY + Constants.METEOR_HEIGHT && meteorY < shotY + Constants.LASER_HEIGHT;
	}
	
	public boolean collisionMeteorSpaceShip( Sprite meteor, Sprite spaceShip )
	{
		int meteorX = meteor.getX();
		int meteorY = meteor.getY();
		int shipX = spaceShip.getX();
		int shipY = spaceShip.getY();
		
		return shipX < meteorX + Constants.METEOR_WIDTH && meteorX < shipX + Constants.SPACESHIP_WIDTH && shipY < meteorY + Constants.SPACESHIP_HEIGHT && meteorY < shipY + Constants.SPACESHIP_HEIGHT;
	}
	
	public boolean spriteCollision( Sprite firstSprite, Sprite secondSprite )
	{
		int firstSpriteX = firstSprite.getX();
		int firstSpriteY = firstSprite.getY();
		int secondSpriteX = secondSprite.getX();
		int secondSpriteY = secondSprite.getY();
		
		return secondSpriteX < firstSpriteX + Constants.METEOR_WIDTH && firstSpriteX < secondSpriteX + Constants.SPACESHIP_WIDTH && secondSpriteY < firstSpriteY + Constants.SPACESHIP_HEIGHT && firstSpriteY < secondSpriteY + Constants.SPACESHIP_HEIGHT;
	}
}
