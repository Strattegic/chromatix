package com.strattegic.chromatix.game;

import com.badlogic.gdx.Game;
import com.strattegic.chromatix.game.helpers.AssetLoader;
import com.strattegic.chromatix.game.screens.MainScreen;

public class ChromatixGame extends Game 
{

	@Override
	public void create() {
		AssetLoader.load();
		setScreen( new MainScreen( this ) );
	}
	
}
