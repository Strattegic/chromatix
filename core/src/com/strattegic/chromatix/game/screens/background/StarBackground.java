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
  private int alphaMax = 4;
  
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
  private int flyOrientation = 0;
  
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

  /**
   * @return the maxStars
   */
  public int getMaxStars()
  {
    return maxStars;
  }

  /**
   * @param maxStars the maxStars to set
   */
  public void setMaxStars( int maxStars )
  {
    this.maxStars = maxStars;
  }

  /**
   * @return the minStars
   */
  public int getMinStars()
  {
    return minStars;
  }

  /**
   * @param minStars the minStars to set
   */
  public void setMinStars( int minStars )
  {
    this.minStars = minStars;
  }

  /**
   * @return the alphaMin
   */
  public int getAlphaMin()
  {
    return alphaMin;
  }

  /**
   * @param alphaMin the alphaMin to set
   */
  public void setAlphaMin( int alphaMin )
  {
    this.alphaMin = alphaMin;
  }

  /**
   * @return the alphaMax
   */
  public int getAlphaMax()
  {
    return alphaMax;
  }

  /**
   * @param alphaMax the alphaMax to set
   */
  public void setAlphaMax( int alphaMax )
  {
    this.alphaMax = alphaMax;
  }

  /**
   * @return the widthMin
   */
  public int getWidthMin()
  {
    return widthMin;
  }

  /**
   * @param widthMin the widthMin to set
   */
  public void setWidthMin( int widthMin )
  {
    this.widthMin = widthMin;
  }

  /**
   * @return the widthMax
   */
  public int getWidthMax()
  {
    return widthMax;
  }

  /**
   * @param widthMax the widthMax to set
   */
  public void setWidthMax( int widthMax )
  {
    this.widthMax = widthMax;
  }

  /**
   * @return the speedMin
   */
  public int getSpeedMin()
  {
    return speedMin;
  }

  /**
   * @param speedMin the speedMin to set
   */
  public void setSpeedMin( int speedMin )
  {
    this.speedMin = speedMin;
  }

  /**
   * @return the speedMax
   */
  public int getSpeedMax()
  {
    return speedMax;
  }

  /**
   * @param speedMax the speedMax to set
   */
  public void setSpeedMax( int speedMax )
  {
    this.speedMax = speedMax;
  }

  /**
   * @return the bgMinX
   */
  public int getBgMinX()
  {
    return bgMinX;
  }

  /**
   * @param bgMinX the bgMinX to set
   */
  public void setBgMinX( int bgMinX )
  {
    this.bgMinX = bgMinX;
  }

  /**
   * @return the bgMaxX
   */
  public int getBgMaxX()
  {
    return bgMaxX;
  }

  /**
   * @param bgMaxX the bgMaxX to set
   */
  public void setBgMaxX( int bgMaxX )
  {
    this.bgMaxX = bgMaxX;
  }

  /**
   * @return the bgMinY
   */
  public int getBgMinY()
  {
    return bgMinY;
  }

  /**
   * @param bgMinY the bgMinY to set
   */
  public void setBgMinY( int bgMinY )
  {
    this.bgMinY = bgMinY;
  }

  /**
   * @return the bgMaxY
   */
  public int getBgMaxY()
  {
    return bgMaxY;
  }

  /**
   * @param bgMaxY the bgMaxY to set
   */
  public void setBgMaxY( int bgMaxY )
  {
    this.bgMaxY = bgMaxY;
  }

  /**
   * @return the flyOrientation
   */
  public int getFlyOrientation()
  {
    return flyOrientation;
  }

  /**
   * @param flyOrientation the flyOrientation to set
   */
  public void setFlyOrientation( int flyOrientation )
  {
    this.flyOrientation = flyOrientation;
  }
}
