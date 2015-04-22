package com.strattegic.chromatix.game.entities;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strattegic.chromatix.game.helpers.AssetLoader;

public class ColorButton extends ImageButton
{
  private float width, height, x, y;
    
  private TextureRegionDrawable texture;
  
  CColor buttonColor;
  
  public ColorButton( CColor color )
  {
    this( 0, 0, color );
  }
  
  public ColorButton( float x, float y, CColor color )
  {
    super( AssetLoader.getColorButton( color ) );
    this.x = x;
    this.y = y;
    width = 80;
    height = 80;
    
    buttonColor = color;
  }

  public TextureRegionDrawable getTexture()
  {
    return texture;
  }

  public void setTexture( TextureRegionDrawable texture )
  {
    this.texture = texture;
  }

  public float getWidth()
  {
    return width;
  }

  public void setWidth( float width )
  {
    this.width = width;
  }

  public float getHeight()
  {
    return height;
  }

  public void setHeight( float height )
  {
    this.height = height;
  }

  public float getX()
  {
    return x;
  }

  public void setX( float x )
  {
    this.x = x;
  }

  public float getY()
  {
    return y;
  }

  public void setY( float y )
  {
    this.y = y;
  }

  public CColor getButtonColor()
  {
    return buttonColor;
  }  
}
