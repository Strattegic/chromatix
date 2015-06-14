package com.strattegic.chromatix.game.entities;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.strattegic.chromatix.game.helpers.AssetLoader;

public class ColorButton extends ImageButton
{      
  private CColor buttonColor;
  
  public ColorButton( CColor color )
  {
    super( AssetLoader.getColorButton( color ) );
    buttonColor = color;
  }

  public CColor getButtonColor()
  {
    return buttonColor;
  }  
}
