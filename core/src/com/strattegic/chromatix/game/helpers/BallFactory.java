package com.strattegic.chromatix.game.helpers;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.strattegic.chromatix.game.entities.Ball;
import com.strattegic.chromatix.game.entities.CColor;
import com.strattegic.chromatix.game.screens.GeneralGameScreen;

public class BallFactory
{
  protected int maxBalls = 0;
  ArrayList<Ball> balls;
  GeneralGameScreen screen;
  
  public BallFactory( GeneralGameScreen screen, int maxBalls )
  {
    this.maxBalls = maxBalls;
    this.screen = screen;
    balls = new ArrayList<Ball>();
    init();
  }
  
  public void init()
  {
    
  }
  
  public void update( float delta )
  {
    Iterator<Ball> i = balls.iterator();
    boolean removeBall = false;
    while ( i.hasNext() ) 
    {
      Ball b = i.next(); // must be called before you can call i.remove()
      b.update( delta );
      if( Intersector.overlaps( (Circle) b.getBoundingShape(), (Circle) screen.getShield().getBoundingShape() )
          && !Intersector.overlaps( (Circle) b.getBoundingShape(), screen.getShield().getBoundingShapeInner() ) )
      {
        // A Ball hit the shield, now let us look up what to do
        if( b.isShieldEffective( screen.getShield() ) )
        {
          screen.getGameData().setScore( screen.getGameData().getScore() + b.getScorePoints() );
          removeBall = true;
        }
        // When the colors didn't match, the object is falling through
      }
      if( Intersector.overlaps( (Circle) b.getBoundingShape(), (Circle) screen.getEarth().getBoundingShape() ) )
      {
        // the current object hit the earth
        screen.getEarth().doDamage();
        removeBall = true;
      }
      
      if( removeBall )
      {
        i.remove();
        removeBall = false;
      }
    }
    
    if( balls.size() < maxBalls )
    {
      double[] unitPool = new double[ balls.size() ];
      for( int j = 0; j < balls.size(); j++ )
      {
        unitPool[j] = balls.get( j ).getUnitsTillTarget();
      }

      boolean ballFound = false;
      
      Ball b = null;
      while( !ballFound )
      {
        Vector2 rndPos = Utils.getRandomBallPosition();
//        int ballType = Utils.rand( 0, 1 );
        int ballType = Ball.BALL_TYPE__NORMAL;
        b = new Ball( rndPos.x, rndPos.y, screen.getEarth().getMiddlePos().x, screen.getEarth().getMiddlePos().y, Constants.BALL_SIZES[ Utils.rand( 0, Constants.BALL_SIZES.length-1 ) ], ballType );
        // TODO: Color
//        b.setColor( new CColor( CColor.PURPLE ) );
        b.setColor( screen.getButtonGroup().getColorArray().get( Utils.rand( 0, screen.getButtonGroup().getColorArray().size() - 1 ) ) );
        
        ballFound = true;
        for( double d : unitPool )
        {
          if( (d > 0 && b.getUnitsTillTarget() >= d - 50 && b.getUnitsTillTarget() <= d + 50)
              ||
              (d <= 0 && b.getUnitsTillTarget() <= d - 50 && b.getUnitsTillTarget() >= d + 50) )
          {
            ballFound = false;
            break;
          }
        }
      }
      if( b != null )
      {
        balls.add( b );
      }
    }
  }
  
  public void draw( SpriteBatch batch )
  {
    for( Ball b : balls )
    {
      batch.draw( b.getTexture(), b.getX() - b.getWidth() / 2, b.getY() - b.getHeight() / 2, b.getRadius(), b.getRadius() );
//      double distance = Math.sqrt( Math.pow( b.getX() - earth.getMiddlePos().x, 2 ) + Math.pow( b.getY() - earth.getMiddlePos().y, 2 ) );
    }
  }
}
