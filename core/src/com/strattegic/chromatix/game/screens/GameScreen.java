package com.strattegic.chromatix.game.screens;

import javafx.scene.shape.MoveTo;

import javax.sound.midi.Sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.BallFactory;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.helpers.GameData;
import com.strattegic.chromatix.game.helpers.GameState;

public class GameScreen extends GeneralGameScreen
{
    
  private Label scoreLabel;
  private TextArea debugLogger;
  
  private Slider ballsSpeedSlider;
  
  BallFactory ballFactory;
  
  private boolean debug;

  /**
   * Screen for the main game (arcade)
   * @param game
   */
  public GameScreen( ChromatixGame game )
  {
    super( game );
    getGameData().setScore( 10034 );
    debug = false;
    
    initGUI();
    
    ballFactory = new BallFactory( this, 8 );
    
  }
  
  public void initGUI()
  {    
    scoreLabel = new Label("Score: 0", new LabelStyle( new BitmapFont(), new Color(255, 255, 255, 1)) );
    scoreLabel.setPosition(Constants.WIDTH - 100, Constants.HEIGHT - 25);
    getUiStage().addActor( scoreLabel );
    
    // DEBUG
    if( debug )
    {
      ballsSpeedSlider = new Slider( 0, 1000, 30, false, AssetLoader.uiSkin );
      ballsSpeedSlider.setPosition( Constants.WIDTH / 2 - ballsSpeedSlider.getWidth() / 2, Constants.HEIGHT-50 );
      ballsSpeedSlider.setValue( 100 );
      getUiStage().addActor( ballsSpeedSlider );
      debugLogger = new TextArea( "123", AssetLoader.uiSkin );
      debugLogger.setPosition( 0, Constants.HEIGHT-200 );
      debugLogger.setHeight( 200 );
      debugLogger.setVisible( false );
      getUiStage().addActor( debugLogger );
    }
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
    getBatch().draw( AssetLoader.gameBackground, 0, 0, 480, 848 );

    if( GameState.isRunning() )
    {
      getBatch().draw( getEarth().getTexture(), getEarth().getX() - getEarth().getRadius(), getEarth().getY(), getEarth().getRadius() * 2, getEarth().getRadius() * 2 );
      
      ballFactory.draw( getBatch() );
      
//      getBatch().draw( AssetLoader.buttonBg, 0, 0, Constants.WIDTH, 150 );
    }
    
    // The shield will be rendered even when the game is lost
    getBatch().draw( getShield().getTexture(), getShield().getX() - getShield().getRadius(), getShield().getY(), getShield().getRadius()*2, getShield().getRadius()*2 );
    getBatch().end();

    update( delta );
    getUiStage().draw();
  }
  
  public void update( float delta )
  {   
    getUiStage().act( delta );
    // TODO LÖSCHEN!
    setGameOver();
    // TODO LÖSCHEN!
    
    if( GameState.isRunning() )
    {
      ballFactory.update( delta );
      scoreLabel.setText( "Score: "+ this.getGameData().getScore() );
      if( debug )
      {
        GameData.DEBUG_BALL_SPEED = ballsSpeedSlider.getValue();
      }
      if( getEarth().isDestroyed() )
      {
        setGameOver();
      }
    }
    else if( GameState.isGameOver() )
    {
    }
    
  }

  /**
   * Sets the gameState to "gameOver" and updates the GUI to accommodate for it
   */
  private void setGameOver()
  {
    GameState.setGameOver();
    for( Actor a : getUiStage().getActors() )
    {
      a.setVisible( false );
    }
        
    LabelStyle scoreStyle = new LabelStyle( AssetLoader.getFont( AssetLoader.FONT_NAMES.FONT_MAIN, 25 ), Color.WHITE );
    Label scoreLabel = new Label( getGameData().getScore() + "", scoreStyle );
//    resetLabel.setFontScale( 3f );
    scoreLabel.setAlignment( Align.center );
    scoreLabel.setPosition( getShield().getX() - scoreLabel.getWidth() / 2, getShield().getY() - scoreLabel.getHeight() + 10 );
    scoreLabel.addListener( new ResetButtonListener() );
    
    //TODO ANIMATION!
    LabelStyle scoreNewStyle = new LabelStyle( AssetLoader.getFont( AssetLoader.FONT_NAMES.FONT_MAIN, 17 ), Color.YELLOW );
    Label newHighscoreLabel = new Label( "new Highscore", scoreNewStyle );
    newHighscoreLabel.setPosition( getShield().getX() - newHighscoreLabel.getWidth() / 2, scoreLabel.getY() - 25 );
    
    MoveToAction action = Actions.action(MoveToAction.class);
    action.setPosition(100, 100);
    action.setDuration(5);
    action.setInterpolation(Interpolation.bounceOut);
    newHighscoreLabel.addAction(action);
        
    getUiStage().addActor( scoreLabel );
    getUiStage().addActor( newHighscoreLabel );
    getUiStage().setDebugAll( false );
  }
  
  private class ResetButtonListener extends ClickListener
  {
    @Override
    public void clicked( InputEvent event, float x, float y )
    {
      getGame().setScreen( new GameScreen( getGame() ) );
    }
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
