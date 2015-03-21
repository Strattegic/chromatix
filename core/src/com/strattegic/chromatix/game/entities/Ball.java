package com.strattegic.chromatix.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Utils;

public class Ball extends Entity
{
	Circle boundingCircle;
	
	public Ball(float x, float y)
	{
		super(x, y);
		setWidth(20);
		setHeight(20);
		setSpeed( 100 );
		setColor( Utils.rand(0, 3) );
		boundingCircle = new Circle( x, y, getWidth() / 2 );
	}
	
	public void update( float delta ) 
	{
		boundingCircle.set( getX(), getY() + getWidth() / 2, getWidth() / 2 ); 
		setY( getY() - delta * getSpeed() );
	}

	@Override
	public Circle getBoundingCircle() 
	{
		return boundingCircle;
	}

	@Override
	public TextureRegion getTexture() 
	{
		return AssetLoader.getBall( getColor() );
	}
}
