package com.strattegic.chromatix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Constants;

public class MainMenuScreen implements Screen 
{
	ChromatixGame game;
	
	private Stage uiStage;
	private Viewport viewport;
	
	private Image logo;
	private Table mainTable;
		private Button startGameButton;
		private Button optionsButton;

	public MainMenuScreen( ChromatixGame game )
	{
		this.game = game;
		viewport = new FitViewport( Constants.WIDTH, Constants.HEIGHT );
		uiStage = new Stage( viewport );
		Gdx.input.setInputProcessor(uiStage);
	}
	
	@Override
	public void show() 
	{
		Image bg = new Image( AssetLoader.bg_grey );
		uiStage.addActor(bg);
		startGameButton = new TextButton("Start Game", AssetLoader.uiSkin);//"Start Game", new LabelStyle(new BitmapFont(), Color.BLACK ) );
		startGameButton = new TextButton( "Start", AssetLoader.textButtonStyle );
		startGameButton.addListener( new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	game.setScreen( new GameScreen( game ) );
	        }
	    });
		optionsButton = new TextButton( "Options", AssetLoader.textButtonStyle );
		
		logo = new Image( AssetLoader.logo );
		logo.setPosition(0, uiStage.getHeight()-200);
		logo.setWidth( 480 );
		logo.setHeight( 100 );
		uiStage.addActor( logo );
		
		mainTable = new Table();
		mainTable.add( startGameButton ).size(100, 70).padBottom(0);
		mainTable.row();
		mainTable.add( optionsButton ).size(100, 70);
		
		mainTable.setX( Constants.WIDTH / 2 );
		mainTable.setY( Constants.HEIGHT / 2 );
		uiStage.addActor( mainTable );
	}

	@Override
	public void render( float delta ) 
	{
		Color bgColor = Constants.COLOR_BACKGROUND;
        Gdx.gl.glClearColor(bgColor.a, bgColor.b, bgColor.g, bgColor.r);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update( delta );
		for( Actor a : uiStage.getActors() )
		{
			a.act( delta );
		}
		uiStage.draw();
	}
	
	public void update( float delta )
	{
		
	}

	@Override
	public void resize(int width, int height) 
	{
		uiStage.getViewport().update(width, height, true);
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
}
