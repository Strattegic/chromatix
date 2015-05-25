package com.strattegic.chromatix.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.entities.CColor;
import com.strattegic.chromatix.game.entities.Earth;
import com.strattegic.chromatix.game.entities.Shield;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.helpers.GameState;

public abstract class GeneralGameScreen implements Screen
{
  private ChromatixGame game;

  private SpriteBatch batch;
  private OrthographicCamera camera;
  private Viewport viewport;
  private MapData gameData;
  
  Earth earth;
  Shield shield;
  
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
    earth = new Earth( shield.getX(), shield.getY()+shield.getRadius()/2 + 10, 5 );
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
    // TODO Auto-generated method stub
    
  }

  @Override
  public void resize( int width, int height )
  {
    getViewport().update( width,height );
    getCamera().position.set(getCamera().viewportWidth/2,getCamera().viewportHeight/2,0);
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

  public MapData getGameData()
  {
    return gameData;
  }

  public abstract Shield getShield();
  
  public abstract Earth getEarth();
}
