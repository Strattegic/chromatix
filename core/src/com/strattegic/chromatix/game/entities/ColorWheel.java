package com.strattegic.chromatix.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.helpers.Constants.COLORS;


public class ColorWheel extends Entity 
{
	private Circle boundingCircle;
	private int currentColorTop = 0;
	private float rotationSpeed = 500;
	private float priorRotationDegree = 0;
	private int currentRotationAmount = 0;
	private int currentRotationDirection = 0;
	
	public ColorWheel(float x, float y) {
		super(x, y);
		setWidth(200);
		setHeight(200);
		setRotation( 45 );
		setRotation( 360 / Constants.getInstance().getRadColors().size() / 2 );
		boundingCircle = new Circle( x, y, getWidth() / 2 );
	}
	
	public static int ROTATION_DIRECTION_NONE = 0;
	public static int ROTATION_DIRECTION_LEFT = 1;
	public static int ROTATION_DIRECTION_RIGHT = 2;

	@Override
	public Circle getBoundingCircle() 
	{
		return boundingCircle;
	}

	@Override
	public void update( float delta ) 
	{
		boundingCircle.set( getX(), getY() + getWidth() / 2, getWidth() / 2 );
		if( currentRotationDirection != ROTATION_DIRECTION_NONE )
		{
			if( currentRotationDirection == ROTATION_DIRECTION_LEFT )
			{
				if( getRotation() + delta*rotationSpeed >= priorRotationDegree + currentRotationAmount )
				{
					setRotation( priorRotationDegree + currentRotationAmount );
					if( getRotation() == 315 )
					{
						// Eine volle Umdrehung. Wieder auf 0 setzen
						setRotation( -45 );
					}
					currentRotationDirection = ROTATION_DIRECTION_NONE;
				}
				else
				{
					setRotation( getRotation() + delta*rotationSpeed );
				}
//				Gdx.app.log("Rotate_LEFT", "Rotation: "+getRotation());
			}
			else
			{
//				Gdx.app.log("Rotate_RIGHT", "Rotation: "+getRotation());
				if( getRotation() - delta*rotationSpeed <= priorRotationDegree - currentRotationAmount )
				{
					setRotation( priorRotationDegree - currentRotationAmount );
					if( getRotation() == -315 )
					{
						// Eine volle Umdrehung. Wieder auf 0 setzen
						setRotation( 45 );
					}
					currentRotationDirection = ROTATION_DIRECTION_NONE;
				}
				else
				{
					setRotation( getRotation() - delta*rotationSpeed );
				}
			}
		}
		setCurrentColorTop();
	}

	// Turn 90° to the right
	public void turnRight()
	{
		currentRotationDirection = ROTATION_DIRECTION_RIGHT;
		priorRotationDegree = getRotation();
		currentRotationAmount = 360 / Constants.getInstance().getRadColors().size();
	}

	// Turn 90° to the left
	public void turnLeft()
	{
		currentRotationDirection = ROTATION_DIRECTION_LEFT;
		priorRotationDegree = getRotation();
		currentRotationAmount = 360 / Constants.getInstance().getRadColors().size();
	}
	
//	// Turn 180° to the left
//	public void turnOneHundredEightyLeft()
//	{
//		currentRotationDirection = ROTATION_DIRECTION_LEFT;
//		priorRotationDegree = getRotation();
//		currentRotationAmount = 180;
//	}
//	
//	// Turn 180° to the right
//	public void turnOneHundredEightyRight()
//	{
//		currentRotationDirection = ROTATION_DIRECTION_RIGHT;
//		priorRotationDegree = getRotation();
//		currentRotationAmount = 180;
//	}
	
	private void setCurrentColorTop()
	{
		float r = getRotation();
//		Gdx.app.log("RAD", "Rotation: "+r);
		if( r >= 0 && r <= 90 )
		{
			currentColorTop = COLORS.BLUE;
		}
		else if( ( r > 90 && r <= 180 ) || ( r >= -270 && r < -180 ) )
		{
			currentColorTop = COLORS.GREEN;
		}
		else if( ( r > 180 && r <= 270 ) || ( r >= -180 && r < -90 ) )
		{
			currentColorTop = COLORS.YELLOW;
		}
		else if( (r > 270 && r <= 360) || ( r >= -90 && r < 0 ) )
		{
			currentColorTop = COLORS.RED;
		}
		System.out.println( "Rotation: "+r+" CurrentColor: "+COLORS.getName(currentColorTop) );
	}

	public int getCurrentColorTop() 
	{
		return currentColorTop;
	}

	@Override
	public TextureRegion getTexture() 
	{
		return AssetLoader.wheel;
	}
	
	public boolean isRotating()
	{
		return currentRotationDirection != ROTATION_DIRECTION_NONE;
	}
}
