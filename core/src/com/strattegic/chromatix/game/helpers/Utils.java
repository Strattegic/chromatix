package com.strattegic.chromatix.game.helpers;

import java.util.Random;

public class Utils 
{
	public static int rand( int min, int max )
	{
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
}
