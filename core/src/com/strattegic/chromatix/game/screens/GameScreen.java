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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.GameScreenInputGestureHandler;
import com.strattegic.chromatix.game.GameScreenInputHandler;
import com.strattegic.chromatix.game.entities.Ball;
import com.strattegic.chromatix.game.entities.Wheel;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Constants;

public class GameScreen implements Screen 
{
	private ChromatixGame game;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private ShapeRenderer debugRenderer;
	   
	private Wheel wheel;
	private ArrayList<Ball> balls;
	
	private int score;
	private int lives, maxLives;
	private boolean gameOver = false;
	
	private Stage uiStage;
		private Label scoreLabel;
		private Button menuButton;
		private Image[] liveImages;
		private TextButton resetButton;
		private Table resetTable;
		private Label resetLabel;
	
	public GameScreen( ChromatixGame game )
	{
		this.game = game;
		score = 0;
		maxLives = 5;
		lives = maxLives;
		balls = new ArrayList<Ball>();
		batch = new SpriteBatch();
		debugRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
	    viewport = new FitViewport( Constants.WIDTH,Constants.HEIGHT,camera );
	    viewport.apply();
		initGUI();
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(uiStage);
		inputMultiplexer.addProcessor( new GestureDetector( new GameScreenInputGestureHandler( this ) ) );
		inputMultiplexer.addProcessor( new GameScreenInputHandler( this ) );
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
	private void initGUI()
	{
		uiStage = new Stage( viewport );
		scoreLabel = new Label("Score ", new LabelStyle( new BitmapFont(), new Color(255, 255, 255, 1)) );
		scoreLabel.setPosition(Constants.WIDTH - 100, Constants.HEIGHT - 70);
		uiStage.addActor( scoreLabel );
		menuButton = new TextButton("Menu", AssetLoader.uiSkin);
		menuButton.setPosition(Constants.WIDTH*0.02f, Constants.HEIGHT*0.96f);
		uiStage.addActor( menuButton );
		menuButton.addListener( new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				game.setScreen( new MainMenuScreen( game ) );
			}
		});
		liveImages = new Image[ lives ];
		for( int i = 0; i < liveImages.length; i++ )
		{
			liveImages[i] = new Image( AssetLoader.heartFull );
			liveImages[i].setSize(29, 25);
			liveImages[i].setPosition( Constants.WIDTH - 30 - (i*29) - 5, Constants.HEIGHT - 35 );
			uiStage.addActor( liveImages[i] );
		}
		
		// Reset Container Overlay
		resetLabel = new Label( "dingens", AssetLoader.uiSkin );
		resetButton = new TextButton( "Restart", AssetLoader.uiSkin );
		resetButton.addListener( new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				game.setScreen( new GameScreen( game ) );
			}
		});
		resetTable = new Table();
		resetTable.setColor(1, 1, 0, 1);
		resetTable.add( resetLabel ).padBottom(20);
		resetTable.row();
		resetTable.add( resetButton );
		resetTable.setPosition(Constants.WIDTH / 2, Constants.HEIGHT / 2);
//		resetTable.debug();
		
		uiStage.addActor(resetTable);
		resetTable.setVisible(false);
	}
		
	@Override
	public void show() 
	{
		int maxBalls = 50;
		for( int i = 0; i < maxBalls; i++ )
		{
			balls.add( new Ball( camera.viewportWidth / 2, 300 + ( i * 200 ) ) );
			balls.add( new Ball( camera.viewportWidth / 2+50, 300 + ( i * 200+50 ) ) );
			balls.add( new Ball( camera.viewportWidth / 2-50, 300 + ( i * 200+100 ) ) );
		}
		wheel = new Wheel( camera.viewportWidth / 2, 100 );
	}

	@Override
	public void render(float delta) 
	{
		Color bgColor = Constants.COLOR_BACKGROUND;
        Gdx.gl.glClearColor(bgColor.a, bgColor.b, bgColor.g, bgColor.r);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		
		// Draw Background Image
		batch.draw( AssetLoader.bg_grey, 0, 0 );
        
		if( !gameOver )
		{
			debugRenderer.begin(ShapeType.Filled);
			debugRenderer.setColor(Color.RED);
			debugRenderer.rect(Gdx.graphics.getWidth() / 2 - 1, 0, 2, Gdx.graphics.getHeight());
			camera.update();
			
			batch.setProjectionMatrix(camera.combined);
			
			update( delta );
	//		batch.draw( wheel.getTexture(), wheel.getX() - wheel.getWidth() / 2, wheel.getY(), wheel.getWidth(), wheel.getHeight() );
			batch.draw( wheel.getTexture(), wheel.getX() - wheel.getWidth() / 2, wheel.getY(), wheel.getWidth() / 2, wheel.getHeight() / 2, 
					wheel.getWidth(), wheel.getHeight(), 1f, 1f, wheel.getRotation());
			for( Ball b : balls )
			{
	//			debugRenderer.circle(b.getBoundingCircle().x, b.getBoundingCircle().y, b.getBoundingCircle().radius);
			    batch.draw( b.getTexture(), b.getX() - b.getWidth() / 2, b.getY(), b.getWidth(), b.getHeight() );
			}
			debugRenderer.end();
		}
		uiStage.draw();
		
		batch.end();
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
	//				Gdx.app.log("Ball_Hit_wheel", "Ball hit => Ball("+ColorWheel.COLORS.getName( b.getColor() )+") - Rad("+Rad.COLORS.getName( rad.getCurrentColorTop() )+")");
					// A Ball entered the TheRad
					if( b.getColor() == wheel.getCurrentHitColor( b.getBoundingCircle() ) )
					{
						// The colors match
//						score++;
//						scoreLabel.setText( "Score: "+score );
					}
					else
					{
						// The colors don't match
//						lives--;
//						livesChanged = true;
					}
					i.remove();
					ballRemoved = true;
					break;
				}
			}
			
	
			if( livesChanged )
			{
	//			Gdx.app.log("Lives", "changed: true");
				if( lives <= 0 )
				{
					gameOver = true;
					resetLabel.setText("Your Score was "+score);
					resetTable.setVisible(true);
				}
				else
				{
	//				Gdx.app.log("Lives", "j="+(liveImages.length - 1)+";j > "+(liveImages.length - (maxLives - lives))+" ");
					for( int j = liveImages.length - 1; j > liveImages.length - (maxLives - lives) - 1; j-- )
					{
						liveImages[j].setDrawable( new SpriteDrawable( new Sprite( AssetLoader.heartEmpty ) ) );
					}
				}
			}
			
			if( ballRemoved )
			{
				balls.add( new Ball( camera.viewportWidth / 2, balls.get( balls.size() - 1 ).getY() + 100 ) );
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
