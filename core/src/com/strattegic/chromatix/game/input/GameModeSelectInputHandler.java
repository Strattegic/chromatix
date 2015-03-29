package com.strattegic.chromatix.game.input;

import com.badlogic.gdx.input.GestureDetector.GestureAdapter;
import com.strattegic.chromatix.game.screens.MainMenuScreen;

public class GameModeSelectInputHandler extends GestureAdapter
{
  private MainMenuScreen screen;

  public GameModeSelectInputHandler( MainMenuScreen mainMenuScreen )
  {
    this.screen = mainMenuScreen;
  }

  @Override
  public boolean fling( float velocityX, float velocityY, int button )
  {
    if ( Math.abs( velocityX ) > Math.abs( velocityY ) )
    {
      if ( velocityX > 0 )
      {
        swipeRight();
      }
      else
      {
        swipeLeft();
      }
    }
    else
    {
      if ( velocityY > 0 )
      {
        // onDown();
      }
      else
      {
        // onUp();
      }
    }
    return super.fling( velocityX, velocityY, button );
  }

  private void swipeLeft()
  {
    screen.swipeLeft();
  }

  private void swipeRight()
  {
    screen.swipeRight();
  }
}
