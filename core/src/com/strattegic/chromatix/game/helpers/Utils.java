package com.strattegic.chromatix.game.helpers;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.strattegic.chromatix.game.entities.Ball;
import com.strattegic.chromatix.game.entities.Wheel;

public class Utils 
{
	public static int rand( int min, int max )
	{
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public static void calculateAngleFromHit( Ball b, Wheel w )
	{
		Vector2 middleBall = new Vector2( b.getX(), b.getY() );
		Vector2 middleWheel = new Vector2( w.getX() , w.getY() );
		
		double angle = Math.toDegrees( Math.atan( (middleWheel.x - middleBall.x) / (middleWheel.y - middleBall.y) ) );
		System.out.println( "Angle: "+angle+" Difference: "+(middleWheel.x - middleBall.x) +" == "+(middleWheel.y - middleBall.y));
		
		System.out.println( w.getRotation() % 360 );
	}
}
