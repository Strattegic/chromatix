package com.strattegic.chromatix.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.helpers.Utils;
import com.strattegic.chromatix.game.screens.background.StarBackground;

public class MainScreen implements Screen
{
  private final int labelBorderWidth = 270;
  private final int labelBorderHeight = 62;
  
  private ChromatixGame game;
  private Viewport viewport;
  private Stage uiStage;
  private Image logo;
  private SpriteBatch batch;
  private ShapeRenderer shapeRenderer;
  private Label arcadeLabel;
  private Label challengeLabel;
  private Label settingsLabel;
  private Table menuTable;
  
  private StarBackground starBackground;

  public MainScreen( ChromatixGame chromatixGame )
  {
    this.game = chromatixGame;
    viewport = new FitViewport( Constants.WIDTH, Constants.HEIGHT );
    uiStage = new Stage( viewport );
    starBackground = new StarBackground();
  }

  @Override
  public void show()
  {
    batch = new SpriteBatch();
    shapeRenderer = new ShapeRenderer();
    
    logo = new Image( AssetLoader.logo );
    logo.setWidth( 323 );
    logo.setHeight( 66 );
    logo.setPosition( Constants.WIDTH / 2 - logo.getWidth() / 2, Constants.HEIGHT * 0.8f );
    uiStage.addActor( logo );
    
    menuTable = new Table();
    LabelStyle menuLabelStyle = new LabelStyle( AssetLoader.getFont( AssetLoader.FONT_NAMES.FONT_MAIN, 25 ), Color.WHITE );
    
    arcadeLabel = new Label( "Arcade", menuLabelStyle );
    challengeLabel = new Label( "Challenge Mode", menuLabelStyle );
    settingsLabel = new Label( "Settings", menuLabelStyle );
    
    menuTable.add( arcadeLabel ).center();
    menuTable.row();
    menuTable.add( challengeLabel ).center().padTop( 20 );
    menuTable.row();
    menuTable.add( settingsLabel ).center().padTop( 20 );
    
    menuTable.setWidth( Constants.WIDTH / 2 );
    menuTable.setPosition( Constants.WIDTH / 2 - menuTable.getWidth() / 2, Constants.HEIGHT * 0.3f );
    uiStage.addActor( menuTable );
    uiStage.setDebugAll( false );
  }

  @Override
  public void render( float delta )
  {
    Color bgColor = Constants.COLOR_BACKGROUND;
    Gdx.gl.glClearColor( bgColor.a, bgColor.b, bgColor.g, bgColor.r );
    Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
    
    
//    batch.begin();
//    batch.draw( AssetLoader.gameBackground, 0, 0, 480, 848 );
//    batch.end();

    // Blend has to be enabled to support the alpha channel
    Gdx.gl.glEnable(GL20.GL_BLEND);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    shapeRenderer.begin( ShapeType.Line );
//    shapeRenderer.setColor( Color.WHITE );
    shapeRenderer.setColor( 200, 10, 50, 0.5f );
    shapeRenderer.rect( Constants.WIDTH / 2 - labelBorderWidth / 2, Utils.getStageLocation( arcadeLabel ).y + ((arcadeLabel.getHeight() - labelBorderHeight) / 2), labelBorderWidth, labelBorderHeight );
    shapeRenderer.rect( Constants.WIDTH / 2 - labelBorderWidth / 2, Utils.getStageLocation( challengeLabel ).y + ((arcadeLabel.getHeight() - labelBorderHeight) / 2), labelBorderWidth, labelBorderHeight );
    shapeRenderer.rect( Constants.WIDTH / 2 - labelBorderWidth / 2, Utils.getStageLocation( settingsLabel ).y + ((arcadeLabel.getHeight() - labelBorderHeight) / 2), labelBorderWidth, labelBorderHeight );

    starBackground.drawStars( shapeRenderer, delta );
    shapeRenderer.end();
    Gdx.gl.glDisable(GL20.GL_BLEND);
    
    Utils.getStageLocation( arcadeLabel );
    
    uiStage.act();
    uiStage.draw();
  }

  @Override
  public void resize( int width, int height )
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void pause()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void resume()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void hide()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void dispose()
  {
    // TODO Auto-generated method stub
    
  }

}
