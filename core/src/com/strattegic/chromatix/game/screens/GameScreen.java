package com.strattegic.chromatix.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.entities.CColor;
import com.strattegic.chromatix.game.entities.ColorButton;
import com.strattegic.chromatix.game.entities.Earth;
import com.strattegic.chromatix.game.entities.Shield;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.BallFactory;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.helpers.GameData;
import com.strattegic.chromatix.game.input.GameScreenInputHandler;

public class GameScreen extends GeneralGameScreen
{
  private Shield shield;
  private Earth earth;
  private ArrayList<ColorButton> currentColors;
  
  private Stage uiStage;
    private ImageButton homeButton;
  
  private Label scoreLabel;
  private TextArea debugLogger;
  
  private Slider ballsSpeedSlider;
  
  BallFactory ballFactory;

  public GameScreen( ChromatixGame game )
  {
    super( game );
    shield = new Shield( new CColor( CColor.BLUE ) );
    earth = new Earth( shield.getX(), shield.getY()+shield.getRadius()/2 + 10 );
    
    currentColors = new ArrayList<ColorButton>();
    currentColors.add( new ColorButton( new CColor( CColor.RED ) ) );
    currentColors.add( new ColorButton( new CColor( CColor.BLUE ) ) );
    currentColors.add( new ColorButton( new CColor( CColor.GREEN ) ) );
    
    uiStage = new Stage( getViewport() );
    InputMultiplexer multi = new InputMultiplexer();
    multi.addProcessor( uiStage );
    multi.addProcessor( new GameScreenInputHandler( this ) );
    Gdx.input.setInputProcessor( multi );
    initGUI();
    GameData.SCORE = 0;
    
    ballFactory = new BallFactory( shield, earth, 8 );
  }
  
  public void initGUI()
  {
//    float alignedXPos = Constants.WIDTH / (currentColors.size()+1);
    
    Table btnTableBottom = new Table();
    btnTableBottom.setWidth( Constants.WIDTH );
    btnTableBottom.setPosition( 0, 70 );
//    btnTableBottom.setDebug( true );
    
    for( int i = 0; i < currentColors.size(); i++ )
    {
      float padding = (Constants.WIDTH - ((currentColors.size()) * currentColors.get( i ).getWidth())) / ((currentColors.size() +1)*2);
      ImageButton btn = currentColors.get( i );
      btn.addListener( new ColorButtonListener() );
      btn.setWidth( Constants.WIDTH / currentColors.size() );
//      btnTableBottom.add( btn ).padLeft( padding ).padRight( padding );
      btnTableBottom.add( btn ).size( Constants.WIDTH / currentColors.size(), 50 );
    }
    uiStage.addActor( btnTableBottom );
    
    scoreLabel = new Label("Score: 0", new LabelStyle( new BitmapFont(), new Color(255, 255, 255, 1)) );
    scoreLabel.setPosition(Constants.WIDTH - 100, Constants.HEIGHT - 25);
    uiStage.addActor( scoreLabel );
        
    homeButton = new ImageButton( AssetLoader.kenney_ui_home_half_transparent );
    homeButton.setY( 796 );
//    homeButton.
    uiStage.addActor( homeButton );
    
    // DEBUG
    ballsSpeedSlider = new Slider( 0, 1000, 30, false, AssetLoader.uiSkin );
    ballsSpeedSlider.setPosition( Constants.WIDTH / 2 - ballsSpeedSlider.getWidth() / 2, Constants.HEIGHT-50 );
    ballsSpeedSlider.setValue( 100 );
    uiStage.addActor( ballsSpeedSlider );
    debugLogger = new TextArea( "123", AssetLoader.uiSkin );
    debugLogger.setPosition( 0, Constants.HEIGHT-200 );
    debugLogger.setHeight( 200 );
    debugLogger.setVisible( false );
    uiStage.addActor( debugLogger );
  }

  
  @Override
  public void render( float delta )
  {
    Color bgColor = Constants.COLOR_BACKGROUND;
    Gdx.gl.glClearColor(bgColor.a, bgColor.b, bgColor.g, bgColor.r);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    getCamera().update();
    
    getBatch().setProjectionMatrix(getCamera().combined);

    if( !earth.isDestroyed() )
    {
      getBatch().begin();

//    debugRenderer.begin(ShapeType.Line);
//      Circle earthCircle = (Circle) earth.getBoundingShape();
//      debugRenderer.circle( shield.getBoundingShapeInner().x, shield.getBoundingShapeInner().y, shield.getBoundingShapeInner().radius );
//    debugRenderer.end();
      
      getBatch().draw( earth.getTexture(), earth.getX() - earth.getRadius(), earth.getY(), earth.getRadius() * 2, earth.getRadius() * 2 );
      getBatch().draw( shield.getTexture(), shield.getX() - shield.getRadius(), shield.getY(), shield.getRadius()*2, shield.getRadius()*2 );
      
      ballFactory.draw( getBatch() );
      
      getBatch().draw( AssetLoader.buttonBg, 0, 0, Constants.WIDTH, 150 );
      getBatch().end();

//    debugRenderer.begin(ShapeType.Line);
//      Circle earthCircle = (Circle) earth.getBoundingShape();
//      debugRenderer.circle( shield.getBoundingShapeInner().x, shield.getBoundingShapeInner().y, shield.getBoundingShapeInner().radius );
//      debugRenderer.circle( earth.getMiddlePos().x, earth.getMiddlePos().y, earthCircle.radius );
//    debugRenderer.end();

//      debugRenderer.setColor( Color.RED );
//      debugRenderer.begin(ShapeType.Line);
//      Circle bc = (Circle) balls.get( 0 ).getBoundingShape();
//      System.out.println( bc.x );
//      debugRenderer.circle( bc.x, bc.y, bc.radius );
//      debugRenderer.rect( 200, 200, 50, 50 );
//      debugRenderer.end();
      update( delta );
    }
    
    uiStage.draw();
  }
  
  public void update( float delta )
  {      
    ballFactory.update( delta );
    scoreLabel.setText( "Score: "+GameData.SCORE );
    if( earth.isDestroyed() )
    {
      setGameOver();
    }
    
    GameData.DEBUG_BALL_SPEED = ballsSpeedSlider.getValue();
  }
  
  private void setGameOver()
  {
    for( Actor a : uiStage.getActors() )
    {
      a.setVisible( false );
    }
    
    Label resetLabel = new Label( GameData.SCORE+"", AssetLoader.uiSkin );
    resetLabel.setWidth( 200 );
    resetLabel.setHeight( 200 );
    resetLabel.setFontScale( 3f );
    resetLabel.setAlignment( Align.center );
    resetLabel.setPosition( Constants.WIDTH / 2- 100, Constants.HEIGHT / 2 - 100 );
    resetLabel.addListener( new ResetButtonListener() );
    uiStage.addActor( resetLabel );
    uiStage.setDebugAll( false );
  }
  
  private class ResetButtonListener extends ClickListener
  {

    @Override
    public void clicked( InputEvent event, float x, float y )
    {
      getGame().setScreen( new GameScreen( getGame() ) );
    }
    
  }
  
  private class ColorButtonListener extends ChangeListener
  {

    @Override
    public void changed( ChangeEvent event, Actor actor )
    {
      for( ColorButton b : currentColors )
      {
        if( b == actor )
        {
          shield.setColor( b.getButtonColor() );
        }
      }
    }

  }
  
  public ArrayList<ColorButton> getButtons()
  {
    return currentColors;
  }
  
  public Shield getShield()
  {
    return shield;
  }
  
}
