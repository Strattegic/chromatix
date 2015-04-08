package com.strattegic.chromatix.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.strattegic.chromatix.game.helpers.AssetLoader;

public class Earth extends Entity
{
  private Circle boundingCircle;
  
  private int health;
  private boolean isDestroyed;

  public Earth( float x, float y )
  {
    super( x, y );
    setRadius( 40 );
    boundingCircle = new Circle( x, y+getRadius(), getRadius()-2 );

    health = 5;
    isDestroyed = false;
  }
  
  public Vector2 getMiddlePos()
  {
    return new Vector2( getX(), getY()+getRadius() );
  }

  @Override
  public void update( float delta )
  {
    
  }

  @Override
  public TextureRegion getTexture()
  {
    return AssetLoader.earth;
  }

  @Override
  public Shape2D getBoundingShape()
  {
    return boundingCircle;
  }

  public void doDamage()
  {
    health--;
    if( health < 0 )
    {
      isDestroyed = true;
    }
  }
  
  public boolean isDestroyed()
  {
    return isDestroyed;
  }

}
