package com.strattegic.chromatix.game.helpers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontStorage
{
  private BitmapFont bitmapFont;
  private float size;
  private String fontName;

  public FontStorage( String fontName , BitmapFont bitmapFont, float size )
  {
    this.bitmapFont = bitmapFont;
    this.size = size;
    this.fontName = fontName;
  }

  public BitmapFont getBitmapFont()
  {
    return bitmapFont;
  }

  public void setBitmapFont( BitmapFont bitmapFont )
  {
    this.bitmapFont = bitmapFont;
  }

  public float getSize()
  {
    return size;
  }

  public void setSize( float size )
  {
    this.size = size;
  }

  public String getFontName()
  {
    return fontName;
  }

  public void setFontName( String fontName )
  {
    this.fontName = fontName;
  }

}
