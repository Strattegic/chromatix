package com.strattegic.chromatix.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.strattegic.chromatix.game.helpers.AssetLoader;


public class Wheel extends Entity 
{
	private Circle boundingCircle;
	private float rotationAmount = 0;
	private float rotationSpeed = 2.3f;
	
	public Wheel(float x, float y) {
		super(x, y);
		setWidth(200);
		setHeight(200);
		setRotation( 0 );
		boundingCircle = new Circle( x, y, getWidth() / 2 );
	}
	
	@Override
	public Circle getBoundingCircle() 
	{
		return boundingCircle;
	}

	@Override
	public void update( float delta ) 
	{
		boundingCircle.set( getX(), getY(), getWidth() / 2 );
		// The wheel is free to move
		if( rotationAmount != 0 )
		{
			setRotation( getRotation() + delta * rotationAmount );
		}
	}
	
	@Override
	public TextureRegion getTexture() 
	{
		return AssetLoader.wheel;
	}
	
	public boolean isRotating()
	{
		return rotationAmount != 0;
	}

	public int getCurrentHitColor( Circle boundingCircle ) 
	{
//		System.out.println( "Ball (X: "+ boundingCircle.x+" Y: "+boundingCircle.y );
//		System.out.println( this.boundingCircle.x+" "+this.boundingCircle.y );
//		double angle = (Math.toDegrees( Math.atan2(boundingCircle.y - 360.0, 360.0 - boundingCircle.x) ) + 360.0) % 360.0;
				
		return 0;
	}
	
	public void setRotationFromMiddle( float touchX, float touchY, int screenWidth, int screenHeight )
	{
//		Gdx.app.log( "Wheel", "RotationSpeed => "+(Gdx.graphics.getWidth() / 2 - touchX) );
		rotationAmount = (Gdx.graphics.getWidth() / 2 - touchX) * rotationSpeed;
//		Gdx.app.log( "Wheel", "T: ("+touchX+":"+touchY+") "+rotationSpeed);
	}

	public void setRotationAmount( int i ) 
	{
		rotationAmount = i;
	}
}
