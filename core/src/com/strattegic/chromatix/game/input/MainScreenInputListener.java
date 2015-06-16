package com.strattegic.chromatix.game.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.strattegic.chromatix.game.screens.GameScreen;
import com.strattegic.chromatix.game.screens.MainScreen;

public class MainScreenInputListener extends InputListener
{
  MainScreen mainScreen;
  
  public MainScreenInputListener( MainScreen screen )
  {
    mainScreen = screen;
  }

  /* (non-Javadoc)
   * @see com.badlogic.gdx.scenes.scene2d.InputListener#touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent, float, float, int, int)
   */
  @Override
  public void touchUp( InputEvent event, float x, float y, int pointer, int button )
  {
    if( event.getTarget().equals( mainScreen.getArcadeLabel() ) )
    {
      // arcade
      mainScreen.getGame().setScreen( new GameScreen( mainScreen.getGame() ) );
    }
    else if( event.getTarget().equals( mainScreen.getChallengeLabel() ) )
    {
      // challenge
    }
  }

  /* (non-Javadoc)
   * @see com.badlogic.gdx.scenes.scene2d.InputListener#touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent, float, float, int, int)
   */
  @Override
  public boolean touchDown( InputEvent event, float x, float y, int pointer, int button )
  {
    return true;
  }
  
}
