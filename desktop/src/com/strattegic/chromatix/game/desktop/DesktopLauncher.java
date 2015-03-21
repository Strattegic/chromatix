package com.strattegic.chromatix.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.helpers.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WIDTH;
		config.height = Constants.HEIGHT;
		new LwjglApplication(new ChromatixGame(), config);
	}
}
