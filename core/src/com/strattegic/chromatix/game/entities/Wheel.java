package com.strattegic.chromatix.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Constants.COLORS;


public class Wheel extends Entity 
{
	private Circle boundingCircle;
	private float rotationAmount = 0;
	private float rotationSpeed = 2f;
	private ArrayList<WheelSegment> wheelSegments;
	
	public Wheel(float x, float y) {
		super(x, y);
		setWidth(200);
		setHeight(200);
		setRotation( 60 );
		wheelSegments = new ArrayList<WheelSegment>();
		wheelSegments.add( new WheelSegment( COLORS.BLUE, 0, 120 ) );
		wheelSegments.add( new WheelSegment( COLORS.GREEN, 120, 240 ) );
		wheelSegments.add( new WheelSegment( COLORS.RED, 240, 360 ) );
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
	
	public void setRotationFromMiddle( float touchX, float touchY, int screenWidth, int screenHeight )
	{
//		Gdx.app.log( "Wheel", "RotationSpeed => "+(Gdx.graphics.getWidth() / 2 - touchX) );
//		Gdx.app.log( "Wheel", "T: ("+touchX+":"+touchY+") "+rotationSpeed);

		rotationAmount = (Gdx.graphics.getWidth() / 2 - touchX) * rotationSpeed;
	}

	public void setRotationAmount( int i ) 
	{
		rotationAmount = i;
	}

	public ArrayList<WheelSegment> getWheelSegments() 
	{
		return wheelSegments;
	}

	public void setWheelSegments(ArrayList<WheelSegment> wheelSegments) 
	{
		this.wheelSegments = wheelSegments;
	}
	
}
