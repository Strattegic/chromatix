package com.strattegic.chromatix.game;

import com.badlogic.gdx.Game;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.screens.MainMenuScreen;
import com.strattegic.chromatix.game.screens.GameScreen;

public class ChromatixGame extends Game 
{

	@Override
	public void create() {
		AssetLoader.load();
		setScreen( new GameScreen( this ) );
	}
	
}
