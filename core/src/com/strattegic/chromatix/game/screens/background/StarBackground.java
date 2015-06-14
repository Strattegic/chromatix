package com.strattegic.chromatix.game.screens.background;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.helpers.Utils;

/**
 * Contains methods for drawing a background starfield using the {@link ShapeRenderer}.<br />
 * They are randomly generated. The constrains can be adjusted at runtime.
 * @author Pure
 *
 */
public class StarBackground
{
  private ArrayList<Star> stars;
  private int maxStars = 300;
  private int minStars = 200;
  
  private int alphaMin = 0;
  private int alphaMax = 6;
  
  private int widthMin = 1;
  private int widthMax = 3;
  
  private int speedMin = 1;
  private int speedMax = 100;
  
  private int bgMinX = 0;
  private int bgMaxX = Constants.WIDTH;
  private int bgMinY = 0;
  private int bgMaxY = Constants.HEIGHT;
  
  // Orientation in degrees
  // 90 means right
  // 180 bottom and so on
  private int flyOrientation = 90;
  
  /**
   * Initiates a StarBackground with the default values.
   */
  public StarBackground()
  {
    stars = new ArrayList<Star>();
  }
  
  private void initStars()
  {
    int starCountMax = Utils.rand( minStars, maxStars );
    for( int i = 0; i < starCountMax; i++ )
    {
      float alpha = (float)Utils.rand( alphaMin, alphaMax ) / 10;
      stars.add( new Star( new Vector2( Utils.rand( bgMinX, bgMaxX ), Utils.rand( bgMinY, bgMaxY ) ), Utils.rand( widthMin, widthMax ), alpha, Utils.rand( speedMin, speedMax ) ) );
    }
  }
  
  /**
   * Draws a starfield with the given {@link ShapeRenderer}.
   * @param batch
   * @param delta
   */
  public void drawStars( ShapeRenderer batch, float delta )
  {
    if( stars.size() <= 0 )
    {
      initStars();
    }
    batch.setAutoShapeType( true );
    batch.set( ShapeType.Filled );
    for( Star star : stars )
    {
      batch.setColor( 200, 10, 50, star.getAlpha() );
      batch.circle( star.getPosition().x, star.getPosition().y, star.getWidth() );
      
      // Update the star's position
      float velX = (float) ( Math.cos( Math.toRadians( flyOrientation ) ) * 1.05 );
      float velY = (float) ( Math.sin( Math.toRadians( flyOrientation ) ) * 1.05 );
      star.setPosition( new Vector2( star.getPosition().x - velX, star.getPosition().y - velY ) );
      
      
      // if a star is out of bounds, let it fly through the screen again (set position to the opposite site)
      // should provide a better memory management than to instantiate a new star every time
      if( star.getPosition().x < bgMinX )
      {
        star.setPosition( new Vector2( bgMaxX, Utils.rand( bgMinY, bgMaxY ) ) );
      }
      else if( star.getPosition().x > bgMaxX )
      {
        star.setPosition( new Vector2( bgMinX, Utils.rand( bgMinY, bgMaxY ) ) );
      }
      else if( star.getPosition().y < bgMinY )
      {
        star.setPosition( new Vector2( Utils.rand( bgMinX, bgMaxX ), bgMaxY ) );
      }
      else if( star.getPosition().y > bgMaxY )
      {
        star.setPosition( new Vector2( Utils.rand( bgMinX, bgMaxX ), bgMinY ) );
      }
    }
  }

  public int getMaxStars()
  {
    return maxStars;
  }

  public void setMaxStars( int maxStars )
  {
    this.maxStars = maxStars;
  }

  public int getMinStars()
  {
    return minStars;
  }

  public void setMinStars( int minStars )
  {
    this.minStars = minStars;
  }

  public int getAlphaMin()
  {
    return alphaMin;
  }

  public void setAlphaMin( int alphaMin )
  {
    this.alphaMin = alphaMin;
  }

  public int getAlphaMax()
  {
    return alphaMax;
  }

  public void setAlphaMax( int alphaMax )
  {
    this.alphaMax = alphaMax;
  }

  public int getWidthMin()
  {
    return widthMin;
  }

  public void setWidthMin( int widthMin )
  {
    this.widthMin = widthMin;
  }

  public int getWidthMax()
  {
    return widthMax;
  }

  public void setWidthMax( int widthMax )
  {
    this.widthMax = widthMax;
  }
}
