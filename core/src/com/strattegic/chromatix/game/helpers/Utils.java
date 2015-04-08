package com.strattegic.chromatix.game.helpers;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class Utils 
{
	public static int rand( int min, int max )
	{
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public static Vector2 getRandomBallPosition()
	{
	  int abst = 1000;
	  int orientation = rand( 1, 4 );
	  if( orientation == 1 )
	    // North
	    return new Vector2( rand(-abst, abst+Constants.WIDTH), rand(Constants.HEIGHT, abst+Constants.HEIGHT));
	  else if( orientation == 2 )
	    // West
      return new Vector2( rand(-abst, 0), rand(-abst, abst+Constants.HEIGHT));
	  else if( orientation == 3 )
	    return new Vector2( rand(Constants.WIDTH, Constants.WIDTH+abst), rand(-1000, 1000+Constants.HEIGHT));
	  else
	    return new Vector2( rand(-abst, abst+Constants.WIDTH), rand(-1000, 0));
	    
	}  
}
