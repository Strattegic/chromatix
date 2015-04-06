package com.strattegic.chromatix.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Constants;

public class NewGameScreen extends GeneralGameScreen
{
  
  private ArrayList<Integer> currentColors;
  private Vector2 colorButtonSize;
  
  private Stage uiStage;

  public NewGameScreen( ChromatixGame game )
  {
    super( game );
    
    colorButtonSize = new Vector2(50,50);
    currentColors = new ArrayList<Integer>();
    currentColors.add( Constants.COLORS.BLUE );
    currentColors.add( Constants.COLORS.RED );
    currentColors.add( Constants.COLORS.GREEN );
    
    uiStage = new Stage( getViewport() );
    initGUI();
  }
  
  public void initGUI()
  {
    float alignedXPos = Constants.WIDTH / (currentColors.size()+1);
    
    for( int i = 0; i < currentColors.size(); i++ )
    {
//      ImageButton btn = new ImageButton( AssetLoader.getBall( currentColors.get( i ) ) );
//      , alignedXPos*(i+1)-(colorButtonSize.x / 2), 70, colorButtonSize.x, colorButtonSize.y );
    }
    Gdx.app.log( "WIDTH", "width: "+Gdx.graphics.getWidth() );
  }

  
  @Override
  public void render( float delta )
  {
    Color bgColor = Constants.COLOR_BACKGROUND;
    Gdx.gl.glClearColor(bgColor.a, bgColor.b, bgColor.g, bgColor.r);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    getCamera().update();
    
    getBatch().setProjectionMatrix(getCamera().combined);
    
    getBatch().begin();
    float alignedXPos = Constants.WIDTH / (currentColors.size()+1);
    
    for( int i = 0; i < currentColors.size(); i++ )
    {
      getBatch().draw( AssetLoader.getBall( currentColors.get( i ) ), alignedXPos*(i+1)-(colorButtonSize.x / 2), 70, colorButtonSize.x, colorButtonSize.y );
      System.out.println( alignedXPos*(i+1)-(colorButtonSize.x / 2) );
    }
    getBatch().end();
    
  }

}
