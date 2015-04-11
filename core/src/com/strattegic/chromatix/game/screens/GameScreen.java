package com.strattegic.chromatix.game.screens;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.entities.Ball;
import com.strattegic.chromatix.game.entities.CColor;
import com.strattegic.chromatix.game.entities.ColorButton;
import com.strattegic.chromatix.game.entities.Earth;
import com.strattegic.chromatix.game.entities.Shield;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.helpers.GameData;
import com.strattegic.chromatix.game.helpers.Utils;
import com.strattegic.chromatix.game.input.GameScreenInputHandler;

public class GameScreen extends GeneralGameScreen
{
  private Shield shield;
  private Earth earth;
  private ArrayList<ColorButton> currentColors;
  private ArrayList<Ball> balls;
  
  private Stage uiStage;
    private ImageButton homeButton;
  
  private ShapeRenderer debugRenderer;
  private Label scoreLabel;

  public GameScreen( ChromatixGame game )
  {
    super( game );
    debugRenderer = new ShapeRenderer();
    shield = new Shield( CColor.BLUE );
    earth = new Earth( shield.getX(), shield.getY()+shield.getRadius()/2 );
    
    currentColors = new ArrayList<ColorButton>();
    currentColors.add( new ColorButton( CColor.RED ) );
    currentColors.add( new ColorButton( CColor.BLUE ) );
    currentColors.add( new ColorButton( CColor.GREEN ) );
    
    uiStage = new Stage( getViewport() );
    InputMultiplexer multi = new InputMultiplexer();
    multi.addProcessor( uiStage );
    multi.addProcessor( new GameScreenInputHandler( this ) );
    Gdx.input.setInputProcessor( multi );
    initGUI();
    
    balls = new ArrayList<Ball>();
    balls.add( new Ball( 200, 700, earth.getMiddlePos().x, earth.getMiddlePos().y, 20 ) );
    GameData.SCORE = 0;
  }
  
  public void initGUI()
  {
//    float alignedXPos = Constants.WIDTH / (currentColors.size()+1);
    
    Table btnTableBottom = new Table();
    btnTableBottom.setWidth( Constants.WIDTH );
    btnTableBottom.setPosition( 0, 110 );
//    btnTableBottom.setDebug( true );
    
    for( int i = 0; i < currentColors.size(); i++ )
    {
      float padding = (Constants.WIDTH - ((currentColors.size()) * currentColors.get( i ).getWidth())) / ((currentColors.size() +1)*2);
      ImageButton btn = currentColors.get( i );
      btn.addListener( new ColorButtonListener() );
      btnTableBottom.add( btn ).padLeft( padding ).padRight( padding );
    }
    uiStage.addActor( btnTableBottom );
    
    scoreLabel = new Label("Score: 0", new LabelStyle( new BitmapFont(), new Color(255, 255, 255, 1)) );
    scoreLabel.setPosition(Constants.WIDTH - 100, Constants.HEIGHT - 25);
    uiStage.addActor( scoreLabel );
        
    homeButton = new ImageButton( AssetLoader.kenney_ui_home_half_transparent );
    homeButton.setY( 796 );
//    homeButton.
    uiStage.addActor( homeButton );
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
      for( Ball b : balls )
      {
        getBatch().draw( b.getTexture(), b.getX() - b.getWidth() / 2, b.getY() - b.getHeight() / 2, b.getRadius(), b.getRadius() );
      }

      getBatch().draw( AssetLoader.buttonBg, 0, 0, Constants.WIDTH, 212 );
      getBatch().end();

//    debugRenderer.begin(ShapeType.Line);
//      Circle earthCircle = (Circle) earth.getBoundingShape();
//      debugRenderer.circle( shield.getBoundingShapeInner().x, shield.getBoundingShapeInner().y, shield.getBoundingShapeInner().radius );
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
    Iterator<Ball> i = balls.iterator();
    boolean removeBall = false;
    while ( i.hasNext() ) 
    {
      Ball b = i.next(); // must be called before you can call i.remove()
      b.update( delta );
      if( Intersector.overlaps( (Circle) b.getBoundingShape(), (Circle) shield.getBoundingShape() )
          && !Intersector.overlaps( (Circle) b.getBoundingShape(), shield.getBoundingShapeInner() ) )
      {
        // Colors matched
        if( b.getColor() == shield.getColor() )
        {
          GameData.SCORE++;
          scoreLabel.setText( "Score: "+GameData.SCORE );
          removeBall = true;
        }
        // When the colors didn't match, the object is falling through
      }
      if( Intersector.overlaps( (Circle) b.getBoundingShape(), (Circle) earth.getBoundingShape() ) )
      {
        // the current object hit the earth
//        earth.doDamage();
        removeBall = true;
      }
      
      if( removeBall )
      {
        i.remove();
        removeBall = false;
      }
    }
    
    if( balls.size() < 8 )
    {
//      Ball b = new Ball( Utils.rand( 150, 350 ), balls.get( balls.size() - 1 ).getY() + Utils.rand( 15, 300 ), earth.getX(), earth.getY() );
      Vector2 rndPos = Utils.getRandomBallPosition();
      Ball b = new Ball( rndPos.x, rndPos.y, earth.getMiddlePos().x, earth.getMiddlePos().y, Constants.BALL_SIZES[ Utils.rand( 0, Constants.BALL_SIZES.length-1 ) ] );
      
      b.setColor( currentColors.get( Utils.rand( 0, currentColors.size() - 1 ) ).getButtonColor() );
      balls.add( b );
    }
    
    if( earth.isDestroyed() )
    {
      setGameOver();
    }
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
