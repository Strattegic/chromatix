package com.strattegic.chromatix.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strattegic.chromatix.game.helpers.Constants.COLORS;

public class AssetLoader 
{
	public static TextureRegion ballRed;
	public static TextureRegion ballGreen;
	public static TextureRegion ballYellow;
	public static TextureRegion ballBlue;
	public static Skin uiSkin;
	public static Texture logo;
	public static Texture bg_grey;
	
	public static Texture heartFull;
	public static Texture heartEmpty;
	
	public static TextButtonStyle textButtonStyle;
	
	public static TextureRegion wheel;
	
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
		heartFull = new Texture( Gdx.files.internal("ui/hud_heartFull.png") );
		heartEmpty = new Texture( Gdx.files.internal("ui/hud_heartEmpty.png") );
		uiSkin = new Skin( Gdx.files.internal("skin/uiskin.json") );
		ballRed = new TextureRegion( new Texture( Gdx.files.internal("ball_red.png") ) );
		ballGreen = new TextureRegion( new Texture( Gdx.files.internal("ball_green.png") ) );
		ballYellow = new TextureRegion( new Texture( Gdx.files.internal("ball_yellow.png") ) );
		ballBlue = new TextureRegion( new Texture( Gdx.files.internal("ball_blue.png") ) );
		
		wheel = new TextureRegion( new Texture( Gdx.files.internal("rad/wheel.png") ) );
		
		logo = new Texture( Gdx.files.internal( "ui/chromatix.png" ) );
		bg_grey = new Texture(Gdx.files.internal("ui/bg_grey.png") );
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = new TextureRegionDrawable( new TextureRegion( new Texture( Gdx.files.internal("uipack/PNG/blue_button09.png"), true ) ) );
		textButtonStyle.down = new TextureRegionDrawable( new TextureRegion( new Texture( Gdx.files.internal("uipack/PNG/blue_button10.png") ) ) );
		textButtonStyle.font = new BitmapFont();
	}
}
