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

  public Earth( float x, float y, int health )
  {
    super( x, y );
    setRadius( 40 );
    boundingCircle = new Circle( x, y+getRadius(), getRadius()-2 );

    this.health = health;
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
    switch( health )
    {
      case 4: return AssetLoader.worldDamage1;
      case 3: return AssetLoader.worldDamage2;
      case 2: return AssetLoader.worldDamage3;
      case 1: return AssetLoader.worldDamage4;
    }
    return AssetLoader.world;
  }

  @Override
  public Shape2D getBoundingShape()
  {
    return boundingCircle;
  }

  public void doDamage()
  {
    health--;
    if( health <= 0 )
    {
      isDestroyed = true;
    }
  }
  
  public boolean isDestroyed()
  {
    return isDestroyed;
  }

}
