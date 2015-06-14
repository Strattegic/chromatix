package com.strattegic.chromatix.game.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.strattegic.chromatix.game.entities.ColorButton;
import com.strattegic.chromatix.game.entities.ColorButtonPanel;

public class GeneralGameScreenButtonPanelInputHandler extends InputListener
{
  private ColorButtonPanel panel;

  public GeneralGameScreenButtonPanelInputHandler( ColorButtonPanel panel )
  {
    this.panel = panel;
  }

  @Override
  public void touchUp( InputEvent event, float x, float y, int pointer, int button )
  {
    System.out.println( event );
  }

  @Override
  public boolean touchDown( InputEvent event, float x, float y, int pointer, int button )
  {
    // Change the Shield Color based on the button pressed
    panel.getGeneralGameScreen().getShield().setColor( ((ColorButton) event.getListenerActor()).getButtonColor() );
    return true;
  }
}
