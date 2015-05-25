package com.strattegic.chromatix.game.desktop;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class TexPack {
	public static void main (String[] args) throws Exception {
	  Settings s = new Settings();
	  s.filterMin = TextureFilter.MipMapLinearNearest;
	  s.filterMag = TextureFilter.Nearest;
    TexturePacker.process( s, "D:/gamedev/ChromatixWS/Chromatix/_data/texPackerIn2", "D:/gamedev/ChromatixWS/Chromatix/android/assets/packer", "dingens");
	}
}
