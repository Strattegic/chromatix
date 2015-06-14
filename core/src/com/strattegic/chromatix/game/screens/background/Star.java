package com.strattegic.chromatix.game.screens.background;

import com.badlogic.gdx.math.Vector2;

public class Star
{
  private Vector2 position;
  private float width;
  private float alpha;
  private float speed;
  
  public Star( Vector2 position, float width, float alpha, float speed )
  {
    this.position = position;
    this.width = width;
    this.alpha = alpha;
    this.speed = speed;
  }

  public float getSpeed()
  {
    return speed;
  }

  public void setSpeed( float speed )
  {
    this.speed = speed;
  }

  public Vector2 getPosition()
  {
    return position;
  }

  public void setPosition( Vector2 position )
  {
    this.position = position;
  }

  public float getWidth()
  {
    return width;
  }

  public void setWidth( float width )
  {
    this.width = width;
  }

  public float getAlpha()
  {
    return alpha;
  }

  public void setAlpha( float alpha )
  {
    this.alpha = alpha;
  }
}