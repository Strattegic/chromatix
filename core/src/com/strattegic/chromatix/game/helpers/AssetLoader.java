package com.strattegic.chromatix.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strattegic.chromatix.game.helpers.Constants.COLORS;

public class AssetLoader 
{
	public static CheckBoxStyle checkboxStyle;
	public static Color BG_COLOR = Color.valueOf("333333FF");
	
	public static TextureRegion GAME_MODE_ARCADE;
	public static TextureRegion GAME_MODE_CHALLENGE;
	public static TextureRegion GAME_MODE_SECRET;
	
	public static TextureAtlas atlas;
	
	public static TextureRegion ballRed;
	public static TextureRegion ballGreen;
	public static TextureRegion ballYellow;
	public static TextureRegion ballBlue;
	public static Skin uiSkin;
	public static TextureRegion logo;
	
	public static TextureRegion heartFull;
	public static TextureRegion heartEmpty;
	public static BitmapFont font_quicksand;
	
	public static TextButtonStyle textButtonStyle;
	
	public static TextureRegion wheel;
	
	public static Sound SOUND_CLICK, MUSIC_SOLEM_VOW;
	
	public static TextureRegion getBall( int color )
	{
		if( color == COLORS.RED )
		{
			return ballRed;
		}
		else if( color == COLORS.GREEN )
		{
			return ballGreen;
		}
		else if( color == COLORS.YELLOW )
		{
			return ballYellow;
		}
		else
		{
			return ballBlue;
		}
	}
	
	public static void load() 
	{
		atlas = new TextureAtlas(Gdx.files.internal("packer/dingens.atlas"));
				
		heartFull = atlas.findRegion( "hud_heartFull" );
		heartEmpty = atlas.findRegion( "hud_heartEmpty" );
		uiSkin = new Skin( Gdx.files.internal("skin/uiskin.json") );
		
		ballRed = atlas.findRegion( "ball_red" );
		ballGreen = atlas.findRegion( "ball_green" );
		ballYellow = atlas.findRegion( "ball_yellow" );
		ballBlue = atlas.findRegion( "ball_blue" );
		
		wheel = atlas.findRegion( "wheel" );
		
		logo = atlas.findRegion( "chromatix_white" );
		font_quicksand = new BitmapFont( Gdx.files.internal( "font/generated_quicksand.fnt" ) );
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = new TextureRegionDrawable( atlas.findRegion( "grey_button10" ) );
		textButtonStyle.down = new TextureRegionDrawable( atlas.findRegion( "grey_button11" ) );
		textButtonStyle.font = new BitmapFont();
		textButtonStyle.fontColor = BG_COLOR;
		
		checkboxStyle = new CheckBoxStyle();
		checkboxStyle.checkboxOn = new TextureRegionDrawable( atlas.findRegion( "grey_boxCheckmark" ) );
		checkboxStyle.checkboxOff = new TextureRegionDrawable( atlas.findRegion( "grey_boxCross" ) );
		checkboxStyle.font = new BitmapFont();
				
		SOUND_CLICK = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/click.wav"));
		
		MUSIC_SOLEM_VOW = Gdx.audio.newSound( Gdx.files.internal("sounds/Solemn_Vow_tabletopaudio_sel.wav") );
		
		GAME_MODE_ARCADE = atlas.findRegion( "mode_arcade" );
		GAME_MODE_CHALLENGE = atlas.findRegion( "mode_challenge" );
		GAME_MODE_SECRET = atlas.findRegion( "mode_secret" );
	}
}
