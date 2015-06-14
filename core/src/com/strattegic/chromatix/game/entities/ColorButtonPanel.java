package com.strattegic.chromatix.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strattegic.chromatix.game.input.GeneralGameScreenButtonPanelInputHandler;
import com.strattegic.chromatix.game.screens.GeneralGameScreen;

public class ColorButtonPanel extends Table
{
  private ArrayList<CColor> colors;
  private GeneralGameScreen generalGameScreen;
  
  
  public ColorButtonPanel( GeneralGameScreen generalGameScreen )
  {
    this.generalGameScreen = generalGameScreen;
  }

  public void addColor( CColor color )
  {
    colors.add( color );
  }
  
  public void removeColor( CColor color )
  {
    for( CColor oldColor : colors )
    {
      if( oldColor == color )
      {
        colors.remove( oldColor );
        break;
      }
    }
  }
  
  public ArrayList<CColor> getColorArray()
  {
    return colors;
  }
  
  public void updateColors( ArrayList<CColor> colors )
  {
    this.colors = colors;
    updateButtons();
  }
  
  /**
   * Clears the Panel and adds the buttons to it.
   */
  private void updateButtons()
  {
    int buttonHeight = 140;
    this.clear();
    this.setWidth( Gdx.graphics.getWidth() );
    this.setHeight( buttonHeight );
    
    for( CColor color : colors )
    {
      ColorButton button = new ColorButton( color );
      this.add( button ).size( Gdx.graphics.getWidth() / colors.size(), buttonHeight );
      button.addListener( new GeneralGameScreenButtonPanelInputHandler( this ) );
    }
  }

  public GeneralGameScreen getGeneralGameScreen()
  {
    return generalGameScreen;
  }
}
