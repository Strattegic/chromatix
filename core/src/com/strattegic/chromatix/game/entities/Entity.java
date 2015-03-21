package com.strattegic.chromatix.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;


public abstract class Entity
{
	private float x, y;
	private float width, height;
	private float rotation;
	private float speed;
	private int color;
		
	public Entity(float x, float y) 
	{
		setPosition(x, y);
	}
	
	public void setPosition( float x, float y )
	{
		this.x = x;
		this.y = y;
	}
	
	public abstract Circle getBoundingCircle();
	public abstract void update( float delta );

	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * @return the texture
	 */
	public abstract TextureRegion getTexture();
	
	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return the rotation
	 */
	public float getRotation() {
		return rotation;
	}

	/**
	 * @param rotation the rotation to set
	 */
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	@Override
	public String toString()
	{
		return "Entity - X: "+getX()+" Y: "+getY();
	}
}
