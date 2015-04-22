package com.strattegic.chromatix.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.strattegic.chromatix.game.ChromatixGame;
import com.strattegic.chromatix.game.MeshTutorial1;
import com.strattegic.chromatix.game.helpers.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WIDTH;
		config.height = Constants.HEIGHT;
		new LwjglApplication(new ChromatixGame(), config);

//    LwjglApplication app = new LwjglApplication(new MeshTutorial1(), "Mesh Tutorial 1", 800, 600);
	}
}
