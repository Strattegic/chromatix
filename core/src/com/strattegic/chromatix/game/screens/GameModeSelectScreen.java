package com.strattegic.chromatix.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.GameData;
import com.strattegic.chromatix.game.input.GameModeSelectInputHandler;

public class GameModeSelectScreen implements Screen
{
	private ChromatixGame game;
	
	private Table mainTable;
		private Image modeImgArcade, modeImgChallenge, modeImgSecret;
	
	private Viewport viewport;
	private Stage uiStage;
	
	private int currentMode = 0;
	
	private ArrayList<Image> modes;
	private int modeWidth = 350;
	
	public GameModeSelectScreen( ChromatixGame game ) 
	{
		this.game = game;		
		viewport = new FitViewport( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		uiStage = new Stage( viewport );
//		uiStage.setDebugAll( true );
//		uiStage.addActor(slider);
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor( new GestureDetector( new GameModeSelectInputHandler( this ) ) );
		inputMultiplexer.addProcessor(uiStage);
		Gdx.input.setInputProcessor( inputMultiplexer );
		
		mainTable = new Table();
		mainTable.setY( Gdx.graphics.getHeight() / 2 );
		mainTable.left();
		
		modes = new ArrayList<Image>();
		modeImgArcade = new Image( AssetLoader.GAME_MODE_ARCADE );
		modeImgArcade.addListener( new GameModeSelectListener() );
		modes.add( modeImgArcade );
		modeImgChallenge = new Image( AssetLoader.GAME_MODE_CHALLENGE );
		modeImgChallenge.addListener( new GameModeSelectListener() );
		modes.add( modeImgChallenge );
		modeImgSecret = new Image( AssetLoader.GAME_MODE_SECRET );
		modeImgSecret.addListener( new GameModeSelectListener() );
		modes.add( modeImgSecret );
		
		for( int i = 0; i < modes.size(); i++ )
		{
			Table table = new Table( AssetLoader.uiSkin );
			table.add( modes.get(i) ).size(300, 500);
			if( i == 0 )
			{
				mainTable.add( table ).padRight( 50 );
			}
			else if( i == modes.size() - 1 )
			{
				mainTable.add( table );
			}
			else
			{
				mainTable.add( table ).padRight( 50 );
			}
		}
		
//		mainTable.setX( 680 / 3 );
		mainTable.setX( Gdx.graphics.getWidth() / 2 - 300 / 2 );
//		System.out.println( Gdx.graphics.getWidth() / 2 + 300 / 2 );
		uiStage.addActor(mainTable);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) 
	{
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		uiStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void swipeLeft() 
	{
		if( currentMode < modes.size() - 1 )
		{
			currentMode++;
			mainTable.setPosition( mainTable.getX()-modeWidth, mainTable.getY() );
		}
	}
	
	public void swipeRight() 
	{
		if( currentMode > 0 )
		{
			currentMode--;
			mainTable.setPosition( mainTable.getX()+modeWidth, mainTable.getY() );
		}
	}
	
	private class GameModeSelectListener extends InputListener
	{
		Vector2 lastDownPos;
		
		public GameModeSelectListener() {
			lastDownPos = new Vector2();
		}
		
		@Override
	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
	    {
			lastDownPos.x = x;
			lastDownPos.y = y;
	        return true;
	    }
		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
		{
			if( lastDownPos.x == x && lastDownPos.y == y && event.getTarget() == modes.get( currentMode ) )
			{
				AssetLoader.SOUND_CLICK.play( GameData.VOLUME_SOUND );
				game.setScreen( new GameScreen( game ) );
			}
			super.touchUp(event, x, y, pointer, button);
		}		
	}

}
