package com.strattegic.chromatix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.helpers.GameData;
import com.strattegic.chromatix.game.input.GameModeSelectInputHandler;

public class MainMenuScreen implements Screen
{
  ChromatixGame game;

  private Stage uiStage;
  private Viewport viewport;

  private Image logo;

  private CheckBox musicCheck, soundCheck;

  private Table modeSelectTable;
  private Image modeImgArcade, modeImgChallenge, modeImgSecret;
  private TextButton optionsButton;
  private int currentMode = 0;

  private int modeWidth = 350;

  public MainMenuScreen( ChromatixGame game )
  {
    this.game = game;
    viewport = new FitViewport( Constants.WIDTH, Constants.HEIGHT );
    uiStage = new Stage( viewport );

    modeSelectTable = new Table();
//    modeSelectTable.setDebug( true );
    modeSelectTable.setY( Constants.HEIGHT / 2 );
    modeSelectTable.left();

    Table modeTableArcade = new Table();
    modeImgArcade = new Image( AssetLoader.GAME_MODE_ARCADE );
    modeImgArcade.setName( "arcade" );
    Label arcadeLabel = new Label( "Highscore: "+GameData.getInstance().getHighscoreArcade(), AssetLoader.uiSkin );
    modeTableArcade.add( modeImgArcade );
    modeTableArcade.row();
    modeTableArcade.add( arcadeLabel ).left();
    modeTableArcade.addListener( new GameModeSelectListener() );
    modeTableArcade.setName( "arcade" );
    modeSelectTable.add( modeTableArcade ).size( 300, 500 ).padRight( 50 );

    Table modeTableChallenge = new Table();
    modeImgChallenge = new Image( AssetLoader.GAME_MODE_CHALLENGE );
    modeImgChallenge.setName( "challenge" );
    modeTableChallenge.addListener( new GameModeSelectListener() );
    modeTableChallenge.add( modeImgChallenge );
    modeSelectTable.add( modeTableChallenge ).size( 300, 500 ).padRight( 50 );
    
    Table modeTableSecret = new Table();
    modeImgSecret = new Image( AssetLoader.GAME_MODE_SECRET );
    modeImgSecret.setName( "secret" );
    modeTableSecret.addListener( new GameModeSelectListener() );
    modeTableSecret.add( modeImgSecret );
    modeSelectTable.add( modeTableSecret ).size( 300, 500 );

    // mainTable.setX( 680 / 3 );
    modeSelectTable.setX( Constants.WIDTH / 2 - 300 / 2 );
    // System.out.println( Gdx.graphics.getWidth() / 2 + 300 / 2 );
    uiStage.addActor( modeSelectTable );

    InputMultiplexer inputMultiplexer = new InputMultiplexer();
    inputMultiplexer.addProcessor( new GestureDetector( new GameModeSelectInputHandler( this ) ) );
    inputMultiplexer.addProcessor( uiStage );
    Gdx.input.setInputProcessor( inputMultiplexer );
  }

  @Override
  public void show()
  {

    logo = new Image( AssetLoader.logo );
    logo.setPosition( 0, uiStage.getHeight() - 150 );
    logo.setWidth( Constants.WIDTH );
    logo.setHeight( 98 );
    uiStage.addActor( logo );

    optionsButton = new TextButton( "Options", AssetLoader.textButtonStyle );
    
    musicCheck = new CheckBox( " Music", AssetLoader.checkboxStyle );
    musicCheck.setChecked( GameData.VOLUME_MUSIC > 0 );
    musicCheck.addListener( new VolumeCheckboxChangeListener() );
    soundCheck = new CheckBox( " Sound", AssetLoader.checkboxStyle );
    soundCheck.setChecked( GameData.VOLUME_SOUND > 0 );
    soundCheck.addListener( new VolumeCheckboxChangeListener() );

    Table shortSettingsTable = new Table();
    shortSettingsTable.setWidth( Constants.WIDTH );
    shortSettingsTable.add( optionsButton ).expandX().width( 100 ).left();
    Table soundButtonsTable = new Table();
    soundButtonsTable.add( musicCheck ).left();
    soundButtonsTable.row();
    soundButtonsTable.add( soundCheck ).left();
    shortSettingsTable.add( soundButtonsTable );
    shortSettingsTable.right().bottom().padRight( 20 ).padBottom( 20 ).padLeft( 20 );
    uiStage.addActor( shortSettingsTable );
    uiStage.setDebugAll( false );
  }

  @Override
  public void render( float delta )
  {
    Color bgColor = Constants.COLOR_BACKGROUND;
    Gdx.gl.glClearColor( bgColor.a, bgColor.b, bgColor.g, bgColor.r );
    Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
    update( delta );
    for ( Actor a : uiStage.getActors() )
    {
      a.act( delta );
    }
    uiStage.draw();
  }

  public void update( float delta )
  {

  }

  @Override
  public void resize( int width, int height )
  {
    uiStage.getViewport().update( width, height, true );
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

  private class VolumeCheckboxChangeListener extends ChangeListener
  {

    @Override
    public void changed( ChangeEvent event, Actor actor )
    {
      if ( actor == musicCheck )
      {
        AssetLoader.SOUND_CLICK.play( GameData.VOLUME_SOUND );
        GameData.VOLUME_MUSIC = ( (CheckBox) actor ).isChecked() ? 0.8f : 0;
      }
      else if ( actor == soundCheck )
      {
        GameData.VOLUME_SOUND = ( (CheckBox) actor ).isChecked() ? 0.8f : 0;
        AssetLoader.SOUND_CLICK.play( GameData.VOLUME_SOUND );
      }
    }

  }

  public void swipeLeft()
  {
    if ( currentMode < 2 )
    {
      currentMode++;
      modeSelectTable.setPosition( modeSelectTable.getX() - modeWidth,
          modeSelectTable.getY() );
    }
  }

  public void swipeRight()
  {
    if ( currentMode > 0 )
    {
      currentMode--;
      modeSelectTable.setPosition( modeSelectTable.getX() + modeWidth,
          modeSelectTable.getY() );
    }
  }

  private class GameModeSelectListener extends InputListener
  {
    Vector2 lastDownPos;

    public GameModeSelectListener()
    {
      lastDownPos = new Vector2();
    }

    @Override
    public boolean touchDown( InputEvent event, float x, float y, int pointer,
        int button )
    {
      lastDownPos.x = x;
      lastDownPos.y = y;
      return true;
    }

    @Override
    public void touchUp( InputEvent event, float x, float y, int pointer,
        int button )
    {
//      Gdx.app.log( "Mode", "touchUp "+event.getTarget() + " " + modeImgArcade );
      if ( lastDownPos.x == x && lastDownPos.y == y && event.getTarget() == modeImgArcade )
      {
        AssetLoader.SOUND_CLICK.play( GameData.VOLUME_SOUND );
        game.setScreen( new GameScreen( game ) );
      }
      super.touchUp( event, x, y, pointer, button );
    }
  }
}
