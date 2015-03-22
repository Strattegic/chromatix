package com.strattegic.chromatix.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.strattegic.chromatix.game.entities.Wheel;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.screens.GameScreen;

public class GameScreenInputHandler implements InputProcessor 
{
	private GameScreen screen;

	public GameScreenInputHandler(GameScreen screen) 
	{
		this.screen = screen;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) 
	{
		if( screenY > Gdx.graphics.getHeight() * 0.2 )
		{
			screen.getwheel().setRotationFromMiddle( screenX, screenY, screen.getViewport().getScreenWidth(), screen.getViewport().getScreenHeight() );
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) 
	{
//		System.out.println( "TOUCH UP");
		if( screen.getwheel().isRotating() )
		{
			screen.getwheel().setRotationAmount( 0 );
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) 
	{
		if( screenY > Gdx.graphics.getHeight() * 0.2 )
		{
			System.out.println( screenY );
			screen.getwheel().setRotationFromMiddle( screenX, screenY, screen.getViewport().getScreenWidth(), screen.getViewport().getScreenHeight() );
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
