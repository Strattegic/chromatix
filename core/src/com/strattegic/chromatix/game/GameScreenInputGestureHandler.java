package com.strattegic.chromatix.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureAdapter;
import com.strattegic.chromatix.game.screens.GameScreen;

public class GameScreenInputGestureHandler extends GestureAdapter
{
	private GameScreen screen;

	public GameScreenInputGestureHandler( GameScreen screen ) 
	{
		Gdx.app.log("Input", "Input initialized");
		this.screen = screen;
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
	}

	private void onUp() 
	{
		
	}

	private void onDown() 
	{
		
	}

	private void onLeft() 
	{
//		Gdx.app.log("Input", "Swipe left");
	}
}
