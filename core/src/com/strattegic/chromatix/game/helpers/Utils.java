package com.strattegic.chromatix.game.helpers;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.strattegic.chromatix.game.entities.Ball;
import com.strattegic.chromatix.game.entities.Wheel;
import com.strattegic.chromatix.game.entities.WheelSegment;

public class Utils 
{
	public static int rand( int min, int max )
	{
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	/**
	 * Calculatates the current hit WheelSegment.
	 * Constructs a right-angled triangle between the Wheel and the Ball and calculates the angle positioned inside the wheel.
	 * Then converts the angle to real Radial Degrees based on the wheel base rotation
	 * @param b
	 * @param w
	 * @return
	 */
	private static WheelSegment calculateAngleFromHit( Ball b, Wheel w )
	{
		Vector2 middleBall = new Vector2( b.getX(), b.getY() );
		Vector2 middleWheel = new Vector2( w.getX() , w.getY() );
		
		double angle = Math.toDegrees( Math.atan( (middleWheel.x - middleBall.x) / (middleWheel.y - middleBall.y) ) );
		double wheelAngleHit = w.getRotation() % 360 + angle;
		
		if( wheelAngleHit < 0 )
		{
			wheelAngleHit = 360 + wheelAngleHit;
		}
		
		for( WheelSegment ws : w.getWheelSegments() )
		{
			//Gdx.app.log("Hit", "Current Angle: "+wheelAngleHit+" Segment("+ws.getMinDegree()+" / "+ws.getMaxDegree() + ")");
			if( wheelAngleHit > ws.getMinDegree() && wheelAngleHit <= ws.getMaxDegree() )
			{
				return ws;
			}
		}
		Gdx.app.error("Wheel", "No Segment was hit??");
		return null;
	}

	/**
	 * Gets whether a ball has hit the WheelSegment of a given Wheel, with the same color as itself.
	 * @param b Ball
	 * @param wheel Wheel
	 * @return true / false
	 */
	public static boolean isBallHitRightWheelSegment(Ball b, Wheel wheel) 
	{
		WheelSegment ws = calculateAngleFromHit( b, wheel );
		if( ws != null && ws.getColor() == b.getColor() )
		{
			return true;
		}
		return false;
	}
}
