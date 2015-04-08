package com.strattegic.chromatix.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.strattegic.chromatix.game.helpers.AssetLoader;

public class Ball extends Entity
{
	private Circle boundingCircle;
	
	private float targetX, targetY;
	
	public Ball(float x, float y, float targetX, float targetY, float size )
	{
		super(x, y);
		setRadius(size);
		setSpeed( 2.6f - (size/30) );
		boundingCircle = new Circle( x, y+getRadius(), getRadius() / 2 );
		this.targetX = targetX;
		this.targetY = targetY;
	}
	
	public void update( float delta ) 
	{
		boundingCircle.set( getX()+getRadius() / 2, getY()+getRadius() / 2, getRadius() / 2 ); 
		
		// On their way to earth
		float tx = targetX - getX();
		float ty = targetY - getY();
		double dist = Math.sqrt(tx*tx+ty*ty);
		  
		double velX = (tx/dist)*getSpeed();
		double velY = (ty/dist)*getSpeed();
		setX( (float) ( getX() + velX ) );
		setY( (float) ( getY() + velY ) );
	}

//	@Override
//	public Circle getBoundingShape() 
//	{
//		return boundingCircle;
//	}

	@Override
	public TextureRegion getTexture() 
	{
		return AssetLoader.getBall( getColor() );
	}

@Override
public Shape2D getBoundingShape()
{
  // TODO Auto-generated method stub
  return boundingCircle;
}
}
