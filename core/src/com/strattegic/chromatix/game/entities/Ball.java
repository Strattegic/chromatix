package com.strattegic.chromatix.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.GameData;

public class Ball extends Entity
{
  public static final int BALL_TYPE__NORMAL = 0;
  public static final int BALL_TYPE__REVERSE = 1;
  public static final int BALL_TYPE__MIXED = 2;
  
	private Circle boundingCircle;
	private long scorePoints;
	
	private float targetX, targetY;
	
	private double unitsTillTarget;
	
	private int type;
		
  public Ball(float x, float y, float targetX, float targetY, long size, int type )
	{
		super(x, y);
		setRadius(size);
		setSpeed( 2.6f - (size/30) );
		boundingCircle = new Circle( x, y+getRadius(), getRadius() / 2 );
		this.targetX = targetX;
		this.targetY = targetY;
		scorePoints = size;
		unitsTillTarget = Math.sqrt( Math.pow( getX() - targetX, 2 ) + Math.pow( getY() - targetY, 2 ) );
	  this.type = type;
	}
	
	public Ball()
	{
	  super(0, 0);
	  this.type = BALL_TYPE__NORMAL;
	}
	
	public void update( float delta ) 
	{
		boundingCircle.set( getX()+getRadius() / 2, getY()+getRadius() / 2, getRadius() / 2 ); 
		
		// On their way to earth
		float tx = targetX - getX();
		float ty = targetY - getY();
		double dist = Math.sqrt(tx*tx+ty*ty);
		  
		double velX = (tx/dist)*getSpeed() * (GameData.DEBUG_BALL_SPEED/100);
		double velY = (ty/dist)*getSpeed() * (GameData.DEBUG_BALL_SPEED/100);
		setX( (float) ( getX() + velX ) );
		setY( (float) ( getY() + velY ) );
		
		unitsTillTarget = Math.sqrt( Math.pow( getX() - targetX, 2 ) + Math.pow( getY() - targetY, 2 ) );
	}

//	@Override
//	public Circle getBoundingShape() 
//	{
//		return boundingCircle;
//	}

	public double getUnitsTillTarget()
  {
    return unitsTillTarget;
  }

  @Override
	public TextureRegion getTexture() 
	{
		return AssetLoader.getBall( this );
	}

  @Override
  public Shape2D getBoundingShape()
  {
    return boundingCircle;
  }
  
  public long getScorePoints()
  {
    return scorePoints;
  }

  public int getType()
  {
    return type;
  }

  public void setType( int type )
  {
    this.type = type;
  }

  public boolean isShieldEffective( Shield shield )
  {
    if( type == Ball.BALL_TYPE__NORMAL && getColor().getId() == shield.getColor().getId() )
      return true;
    else if( type == Ball.BALL_TYPE__REVERSE && getColor().getId() != shield.getColor().getId() )
      return true;
    return false;
  }

}
