package com.strattegic.chromatix.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.entities.CColor;
import com.strattegic.chromatix.game.entities.ColorButtonPanel;
import com.strattegic.chromatix.game.entities.Earth;
import com.strattegic.chromatix.game.entities.Shield;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.helpers.GameState;
import com.strattegic.chromatix.game.input.GameScreenInputHandler;

/**
 * The Abstract Class of every GameScreen. Every GameMode can be extended from this class.
 * A default implementation is already set and you just have to override the things you want to change.
 * @author Strattegic
 *
 */
public abstract class GeneralGameScreen implements Screen
{
  private ChromatixGame game;

  private SpriteBatch batch;
  private OrthographicCamera camera;
  private Viewport viewport;
  private MapData gameData;
  private Stage uiStage;

  private Earth earth;
  private Shield shield;
  private ColorButtonPanel buttonGroup;

  public GeneralGameScreen( ChromatixGame game )
  {
    this.game = game;
    batch = new SpriteBatch();
    camera = new OrthographicCamera();
    viewport = new FitViewport( Constants.WIDTH, Constants.HEIGHT, camera );
    viewport.apply();
    GameState.setState( GameState.RUNNING );
    gameData = new MapData();

    shield = new Shield( new CColor( CColor.CYAN ) );
    earth = new Earth( shield.getX(), shield.getY() + shield.getRadius() / 2 + 10, 5 );
    buttonGroup = new ColorButtonPanel( this );
    uiStage = new Stage( viewport );
    // uiStage.setDebugAll( true );

    InputMultiplexer multi = new InputMultiplexer();
    multi.addProcessor( getUiStage() );
    Gdx.input.setInputProcessor( multi );
  }

  public ChromatixGame getGame()
  {
    return game;
  }

  public void setGame( ChromatixGame game )
  {
    this.game = game;
  }

  public SpriteBatch getBatch()
  {
    return batch;
  }

  public void setBatch( SpriteBatch batch )
  {
    this.batch = batch;
  }

  public OrthographicCamera getCamera()
  {
    return camera;
  }

  public void setCamera( OrthographicCamera camera )
  {
    this.camera = camera;
  }

  public Viewport getViewport()
  {
    return viewport;
  }

  public void setViewport( Viewport viewport )
  {
    this.viewport = viewport;
  }

  @Override
  public void show()
  {
    ArrayList<CColor> colors = new ArrayList<CColor>();
    colors.add( new CColor( CColor.CYAN ) );
    colors.add( new CColor( CColor.PURPLE ) );
    colors.add( new CColor( CColor.YELLOW ) );
    getButtonGroup().updateColors( colors );
    
    uiStage.addActor( buttonGroup );
    uiStage.addActor( getButtonGroup() );
  }

  @Override
  public void render( float delta )
  {
    uiStage.draw();
  }

  public ColorButtonPanel getButtonGroup()
  {
    return buttonGroup;
  }

  @Override
  public void resize( int width, int height )
  {
    getViewport().update( width, height );
    getCamera().position.set( getCamera().viewportWidth / 2, getCamera().viewportHeight / 2, 0 );
  }

  @Override
  public void pause()
  {
    // TODO Auto-generated method stub
    System.out.println( "pause" );
  }

  @Override
  public void resume()
  {
    // TODO Auto-generated method stub
    System.out.println( "resume" );
  }

  public MapData getGameData()
  {
    return gameData;
  }

  public Shield getShield()
  {
    return shield;
  }

  public Earth getEarth()
  {
    return earth;
  }

  public Stage getUiStage()
  {
    return uiStage;
  }
}
