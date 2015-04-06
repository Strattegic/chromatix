package com.strattegic.chromatix.game.screens;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.entities.Ball;
import com.strattegic.chromatix.game.entities.Wheel;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.helpers.GameData;
import com.strattegic.chromatix.game.helpers.Utils;
import com.strattegic.chromatix.game.input.GameScreenInputHandler;

public class GameScreen implements Screen 
{
	private ChromatixGame game;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
		   
	private Wheel wheel;
	private ArrayList<Ball> balls;
	
	private boolean gameOver = false;
	
	private Stage uiStage;
		private Label scoreLabel;
		private Button menuButton;
		private Image[] liveImages;
		private TextButton resetButton;
		private Table resetTable;
		private Label resetLabel;
		private Label resetScoreLabel;
		
		Label fpsLabel;
	
	public GameScreen( ChromatixGame game )
	{
		this.game = game;
		GameData.LIVES_CURRENT = 5;
		GameData.SCORE = 0;
		balls = new ArrayList<Ball>();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
	    viewport = new FitViewport( Constants.WIDTH, Constants.HEIGHT, camera );
	    viewport.apply();
		initGUI();
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(uiStage);
		inputMultiplexer.addProcessor( new GameScreenInputHandler( this ) );
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		AssetLoader.MUSIC_SOLEM_VOW.play();
		AssetLoader.MUSIC_SOLEM_VOW.setVolume( GameData.VOLUME_MUSIC );
		AssetLoader.MUSIC_SOLEM_VOW.setLooping( true );
	}
	
	private void initGUI()
	{
		uiStage = new Stage( viewport );
		scoreLabel = new Label("Score: 0", new LabelStyle( new BitmapFont(), new Color(255, 255, 255, 1)) );
		scoreLabel.setPosition(Constants.WIDTH - 100, Constants.HEIGHT - 70);
		uiStage.addActor( scoreLabel );
		menuButton = new TextButton("Menu", AssetLoader.uiSkin);
		menuButton.setPosition(Constants.WIDTH*0.02f, Constants.HEIGHT*0.96f);
		menuButton.addListener( new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				game.setScreen( new MainMenuScreen( game ) );
				AssetLoader.MUSIC_SOLEM_VOW.stop();
			}
		});
		uiStage.addActor( menuButton );
		
		liveImages = new Image[ GameData.LIVES_MAX ];
		for( int i = 0; i < liveImages.length; i++ )
		{
			liveImages[i] = new Image( AssetLoader.heartFull );
			liveImages[i].setSize(29, 25);
			liveImages[i].setPosition( Constants.WIDTH - 30 - (i*29) - 5, Constants.HEIGHT - 35 );
			uiStage.addActor( liveImages[i] );
		}
		
		fpsLabel = new Label("", AssetLoader.uiSkin);
		fpsLabel.setY(20);
//		uiStage.addActor(fpsLabel);
	}
	
	public void setGameOver()
	{
		gameOver = true;
		// Remove all the actors before showing the GameOver Screen
		for( int i = 0; i < liveImages.length; i++ )
		{
			liveImages[i].remove();
		}
		scoreLabel.remove();
		
		// Reset Container Overlay
		resetButton = new TextButton( "Restart", AssetLoader.uiSkin );
		resetButton.addListener( new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				game.setScreen( new GameScreen( game ) );
			}
		});
		resetLabel = new Label( "Restart?", AssetLoader.uiSkin );
		resetScoreLabel = new Label(""+GameData.SCORE, new LabelStyle( AssetLoader.font_quicksand, Color.WHITE));
		resetScoreLabel.setFontScale(2f, 2f);
		resetScoreLabel.setAlignment( Align.center );
		resetScoreLabel.setColor(0.8f, 0.8f, 0.8f, .5f);
		
		resetTable = new Table();
		resetTable.setColor(1, 1, 0, 1);
		resetTable.add( resetScoreLabel ).size( Constants.WIDTH * 0.9f, 200 );
		resetTable.row();
		resetTable.add( resetLabel ).padBottom(20);
		resetTable.row();
		resetTable.add( resetButton );
		resetTable.setPosition(Constants.WIDTH / 2, Constants.HEIGHT / 2);
//		resetTable.debug();
		
		uiStage.addActor(resetTable);
		GameData.getInstance().setHighscoreArcade( GameData.SCORE );
	}
		
	@Override
	public void show() 
	{
		int maxBalls = 50;
		for( int i = 0; i < maxBalls; i++ )
		{
			balls.add( new Ball( camera.viewportWidth / 2 + (Utils.rand(-60, 60)), 500 + ( i * 100 + Utils.rand(50, 100) ) ) );
		}
		wheel = new Wheel( camera.viewportWidth / 2, 200 );
	}

	@Override
	public void render(float delta) 
	{
		Color bgColor = Constants.COLOR_BACKGROUND;
        Gdx.gl.glClearColor(bgColor.a, bgColor.b, bgColor.g, bgColor.r);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		uiStage.draw();
		batch.begin();
		batch.draw( AssetLoader.gameControl, 25, 30, 430, 60 );
		// Draw Background Image
//		batch.draw( AssetLoader.bg_grey, 0, 0 );
        
		if( !gameOver )
		{
			camera.update();
			
			batch.setProjectionMatrix(camera.combined);
			
			update( delta );
			batch.draw( wheel.getTexture(), wheel.getX() - wheel.getWidth() / 2, wheel.getY() - wheel.getHeight() / 2, wheel.getWidth() / 2, wheel.getHeight() / 2, 
					wheel.getWidth(), wheel.getHeight(), 1f, 1f, wheel.getRotation());
						
			for( Ball b : balls )
			{
			    batch.draw( b.getTexture(), b.getX() - b.getWidth() / 2, b.getY() - b.getHeight() / 2, b.getWidth(), b.getHeight() );
			}
		}
		
		batch.end();
		fpsLabel.setText( "FPS: "+Gdx.graphics.getFramesPerSecond() );
	}
	
	public void update( float delta )
	{
		if( !gameOver )
		{
			wheel.update(delta);		
			boolean ballRemoved = false;
			boolean livesChanged = false;
			Iterator<Ball> i = balls.iterator();
			while ( i.hasNext() ) 
			{
				Ball b = i.next(); // must be called before you can call i.remove()
				b.update( delta );
				if( b.getBoundingCircle().overlaps( wheel.getBoundingCircle() ) )
				{
					// A Ball entered the Wheel
					// Determine where it hit
					if( Utils.isBallHitRightWheelSegment( b, wheel ) )
					{
						GameData.SCORE++;
						scoreLabel.setText( "Score: "+GameData.SCORE );
					}
					else
					{
						GameData.LIVES_CURRENT--;
						livesChanged = true;
					}
					
					i.remove();
					ballRemoved = true;
					break;
				}
			}
			
	
			if( livesChanged )
			{
	//			Gdx.app.log("Lives", "changed: true");
				if( GameData.LIVES_CURRENT <= 0 )
				{
					setGameOver();
				}
				else
				{
//					Gdx.app.log("Lives", "j="+(liveImages.length - 1)+";j > "+(liveImages.length - (GameData.LIVES_MAX - GameData.LIVES_CURRENT))+" ");
					for( int j = liveImages.length - 1; j > liveImages.length - (GameData.LIVES_MAX - GameData.LIVES_CURRENT) - 1; j-- )
					{
						liveImages[j].setDrawable( new SpriteDrawable( new Sprite( AssetLoader.heartEmpty ) ) );
					}
				}
			}
			
			if( ballRemoved )
			{
//				balls.add( new Ball( camera.viewportWidth / 2, balls.get( balls.size() - 1 ).getY() + 100 ) );
//				balls.add( new Ball( camera.viewportWidth / 2 + (Utils.rand(-60, 60)), 200 + ( 100 + Utils.rand(50, 100) ) ) );
			}
			
			for( Actor a : uiStage.getActors() )
			{
				a.act(delta);
			}
		}
	}

	@Override
	public void resize(int width, int height)
	{
		viewport.update( width,height );
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() 
	{
		uiStage.dispose();
	}

	public Wheel getwheel() 
	{
		return wheel;
	}
	
	public Viewport getViewport()
	{
		return viewport;
	}
	
	public Camera getCamera()
	{
		return camera;
	}

}
