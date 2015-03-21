package com.strattegic.chromatix.game.entities;

public class RadColor 
{
	private int color;
	private float minDegree;
	private float maxDegree;
	
	public RadColor( int color, float minDegree, float maxDegree )
	{
		this.color = color;
		this.minDegree = minDegree;
		this.maxDegree = maxDegree;
	}

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
	 * @return the minDegree
	 */
	public float getMinDegree() {
		return minDegree;
	}

	/**
	 * @param minDegree the minDegree to set
	 */
	public void setMinDegree(float minDegree) {
		this.minDegree = minDegree;
	}

	/**
	 * @return the maxDegree
	 */
	public float getMaxDegree() {
		return maxDegree;
	}

	/**
	 * @param maxDegree the maxDegree to set
	 */
	public void setMaxDegree(float maxDegree) {
		this.maxDegree = maxDegree;
	}	
}
