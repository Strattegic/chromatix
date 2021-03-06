package com.strattegic.chromatix.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.strattegic.chromatix.game.screens.GameScreen;

public class GameScreenInputHandler implements InputProcessor
{
  GameScreen gameScreen;

  public GameScreenInputHandler( GameScreen gameScreen )
  {
    this.gameScreen = gameScreen;
  }

  @Override
  public boolean keyDown( int keycode )
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean keyUp( int keycode )
  {
    if( keycode == Input.Keys.NUM_1 && gameScreen.getButtonGroup().getColorArray().size() >= 1 )
    {
      gameScreen.getShield().setColor( gameScreen.getButtonGroup().getColorArray().get( 0 ) );
    }
    else if( keycode == Input.Keys.NUM_2 && gameScreen.getButtonGroup().getColorArray().size() >= 2 )
    {
      gameScreen.getShield().setColor( gameScreen.getButtonGroup().getColorArray().get( 1 ) );
    }
    else if( keycode == Input.Keys.NUM_3 && gameScreen.getButtonGroup().getColorArray().size() >= 3 )
    {
      gameScreen.getShield().setColor( gameScreen.getButtonGroup().getColorArray().get( 2 ) );
    }
    return false;
  }

  @Override
  public boolean keyTyped( char character )
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchDown( int screenX, int screenY, int pointer, int button )
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchUp( int screenX, int screenY, int pointer, int button )
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchDragged( int screenX, int screenY, int pointer )
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean mouseMoved( int screenX, int screenY )
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean scrolled( int amount )
  {
    // TODO Auto-generated method stub
    return false;
  }

}
