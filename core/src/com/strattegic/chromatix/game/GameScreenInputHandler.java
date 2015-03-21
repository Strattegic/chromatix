package com.strattegic.chromatix.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureAdapter;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.screens.GameScreen;

public class GameScreenInputHandler extends GestureAdapter 
{
	private GameScreen screen;

	public GameScreenInputHandler( GameScreen screen ) 
	{
		Gdx.app.log("Input", "Input initialized");
		this.screen = screen;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) 
	{
		// When clicked in the lower half
		if( y >= Constants.HEIGHT / 2 )
		{
			// Clicked on the right side
			if( x >= Constants.WIDTH / 2 )
			{
				onRight();
			}
			// Clicked on the left side
			else
			{
				onLeft();
			}
		}
		return true;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (Math.abs(velocityX) > Math.abs(velocityY)) {
			if (velocityX > 0) {
				onRight();
			} else {
				onLeft();
			}
		} else {
			if (velocityY > 0) {
				onDown();
			} else {
				onUp();
			}
		}
		return super.fling(velocityX, velocityY, button);
	}

	public void onRight() 
	{
//		Gdx.app.log("Input", "Swipe right");
		if( !screen.getwheel().isRotating() )
		{
			screen.getwheel().turnRight();
		}
	}

	private void onUp() 
	{
//		if( !screen.getRad().isRotating() )
//		{
//			screen.getRad().turnOneHundredEightyLeft();
//		}
	}

	private void onDown() 
	{
//		if( !screen.getRad().isRotating() )
//		{
//			screen.getRad().turnOneHundredEightyRight();
//		}
	}

	private void onLeft() 
	{
//		Gdx.app.log("Input", "Swipe left");
		if( !screen.getwheel().isRotating() )
		{
			screen.getwheel().turnLeft();
		}
	}
}
