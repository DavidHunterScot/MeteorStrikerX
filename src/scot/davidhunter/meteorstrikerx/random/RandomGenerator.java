package scot.davidhunter.meteorstrikerx.random;

import java.util.Random;

import scot.davidhunter.meteorstrikerx.constants.Constants;

public class RandomGenerator
{
	private Random random;
	
	public RandomGenerator()
	{
		random = new Random();
	}
	
	public boolean isMeteorGenerated()
	{
		return random.nextDouble() < Constants.METEOR_PROBABILITY;
	}
	
	// we want to generate a random integer in the range [0, GAME_WIDTH -
	// SHIP_WIDTH]
	public int generateRandomX()
	{
		return random.nextInt( Constants.GAME_WIDTH - 2 * Constants.SPACESHIP_WIDTH ) + Constants.SPACESHIP_WIDTH;
	}
}
