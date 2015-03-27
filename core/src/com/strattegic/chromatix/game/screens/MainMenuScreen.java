package com.strattegic.chromatix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.helpers.Constants;
import com.strattegic.chromatix.game.helpers.GameData;

public class MainMenuScreen implements Screen 
{
	ChromatixGame game;
	
	private Stage uiStage;
	private Viewport viewport;
	
	private Image logo;
	private Table mainTable;
		private Button startGameButton;
		private Button optionsButton;
		
		private CheckBox musicCheck, soundCheck;

	public MainMenuScreen( ChromatixGame game )
	{
		this.game = game;
		viewport = new FitViewport( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		uiStage = new Stage( viewport );
		Gdx.input.setInputProcessor(uiStage);
	}
	
	@Override
	public void show() 
	{
		startGameButton = new TextButton( "Start", AssetLoader.textButtonStyle );
		startGameButton.addListener( new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	AssetLoader.SOUND_CLICK.play( GameData.VOLUME_SOUND );
	        	game.setScreen( new GameModeSelectScreen( game ) );
	        }
	    });
		optionsButton = new TextButton( "Options", AssetLoader.textButtonStyle );
		
		logo = new Image( AssetLoader.logo );
		logo.setPosition(0, uiStage.getHeight()-200);
		logo.setWidth( 480 );
		logo.setHeight( 100 );
		uiStage.addActor( logo );
		
		mainTable = new Table();
		mainTable.add( startGameButton ).size(100, 50).padBottom(20);
		mainTable.row();
		mainTable.add( optionsButton ).size(100, 50);
		
		mainTable.setX( Gdx.graphics.getWidth() / 2 );
		mainTable.setY( Gdx.graphics.getHeight() / 2 );
		uiStage.addActor( mainTable );
		
		musicCheck = new CheckBox(" Music", AssetLoader.checkboxStyle);
		musicCheck.setChecked( GameData.VOLUME_MUSIC > 0 );
		musicCheck.addListener( new VolumeCheckboxChangeListener() );
		soundCheck = new CheckBox(" Sound", AssetLoader.checkboxStyle);
		soundCheck.setChecked( GameData.VOLUME_SOUND > 0 );
		soundCheck.addListener( new VolumeCheckboxChangeListener() );
		
		Table shortSettingsTable = new Table();
		shortSettingsTable.setWidth( Gdx.graphics.getWidth() );
		shortSettingsTable.add( musicCheck ).left();
		shortSettingsTable.row();
		shortSettingsTable.add( soundCheck ).left();
		shortSettingsTable.right().bottom().padRight(20).padBottom(20);
		uiStage.addActor( shortSettingsTable );
		uiStage.setDebugAll(false);
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
	
	private class VolumeCheckboxChangeListener extends ChangeListener
	{

		@Override
		public void changed(ChangeEvent event, Actor actor) {
			if( actor == musicCheck )
			{
				AssetLoader.SOUND_CLICK.play( GameData.VOLUME_SOUND );
				GameData.VOLUME_MUSIC = ((CheckBox) actor).isChecked() ? 0.8f : 0;
			}
			else if( actor == soundCheck )
			{
				GameData.VOLUME_SOUND = ((CheckBox) actor).isChecked() ? 0.8f : 0;
				AssetLoader.SOUND_CLICK.play( GameData.VOLUME_SOUND );
			}
		}
		
	}
}
