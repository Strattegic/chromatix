package com.strattegic.chromatix.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Constants;

public class Shield extends Entity
{
  private Circle boundingShape, boundingFormInner;
    
  public Shield( CColor color )
  {
    super( Constants.WIDTH / 2, 420 );
    setRadius( 100 );
    setColor( color );
    boundingShape = new Circle( getX(), getY()+getRadius(), getRadius()-2 );
    boundingFormInner = new Circle( getX(), getY()+getRadius(), getRadius() - 55 );
  }

  public Circle getBoundingShapeInner()
  {
    return boundingFormInner;
  }

  @Override
  public TextureRegion getTexture()
  {
    return AssetLoader.getShieldTexture( getColor() );
  }

  @Override
  public void update( float delta )
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Shape2D getBoundingShape()
  {
    return boundingShape;
  }
}
