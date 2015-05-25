package com.strattegic.chromatix.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strattegic.chromatix.game.entities.Ball;
import com.strattegic.chromatix.game.entities.CColor;

public class AssetLoader 
{
	public static CheckBoxStyle checkboxStyle;
	
	public static TextureRegion GAME_MODE_ARCADE;
	public static TextureRegion GAME_MODE_CHALLENGE;
	public static TextureRegion GAME_MODE_SECRET;
	
	public static TextureAtlas atlas;

  public static TextureRegion logo;
  public static TextureRegion gameBackground;
	
  public static TextureRegion world;
  public static TextureRegion worldDamage1;
  public static TextureRegion worldDamage2;
  public static TextureRegion worldDamage3;
  public static TextureRegion worldDamage4;
  
  public static TextureRegion shield;
	public static TextureRegion enemyPurple;
	public static TextureRegionDrawable colorButtonPurple;
  public static TextureRegionDrawable colorButtonCyan;
  public static TextureRegionDrawable colorButtonGreen;
  public static TextureRegionDrawable colorButtonGrey;
  public static TextureRegionDrawable colorButtonOrange;
  public static TextureRegionDrawable colorButtonYellow;
	public static Skin uiSkin;
	
	public static BitmapFont font_quicksand;
	
	public static TextButtonStyle textButtonStyle;
		
	public static Sound SOUND_CLICK;
	public static Music MUSIC_SOLEM_VOW;


		
  public static TextureRegion getBall( Ball ball )
  {
    return enemyPurple;
  }
	
	public static TextureRegionDrawable getColorButton( CColor color )
	{
	  switch( color.getId() )
	  {
	    case CColor.CYAN: return colorButtonCyan;
	    case CColor.GREEN: return colorButtonGreen;
	    case CColor.GREY: return colorButtonGrey;
	    case CColor.ORANGE: return colorButtonOrange;
	    case CColor.PURPLE: return colorButtonPurple;
	    case CColor.YELLOW: return colorButtonYellow;
	  }
    return colorButtonPurple;
	}
	
	public static TextureRegion getShieldTexture( CColor color )
  {
    return shield;
  }
	
	public static void load() 
	{
		atlas = new TextureAtlas(Gdx.files.internal("packer/dingens.atlas"));

    uiSkin = new Skin( Gdx.files.internal("skin/uiskin.json") );
		enemyPurple = atlas.findRegion( "enemy_purple" );
		world = atlas.findRegion( "world" );		
		logo = atlas.findRegion( "chromatix_white" );
		shield = atlas.findRegion( "shield" );
		gameBackground = atlas.findRegion( "game_bg" );
		
		colorButtonPurple = new TextureRegionDrawable( atlas.findRegion( "button_bottom_purple" ) );
		colorButtonCyan = new TextureRegionDrawable( atlas.findRegion( "button_bottom_cyan" ) );
		colorButtonGreen = new TextureRegionDrawable( atlas.findRegion( "button_bottom_green" ) );
		colorButtonGrey = new TextureRegionDrawable( atlas.findRegion( "button_bottom_grey" ) );
		colorButtonOrange = new TextureRegionDrawable( atlas.findRegion( "button_bottom_orange" ) );
		colorButtonYellow = new TextureRegionDrawable( atlas.findRegion( "button_bottom_yellow" ) );
		
		worldDamage1 = atlas.findRegion( "world_damage_test", 1 );
		worldDamage2 = atlas.findRegion( "world_damage_test", 2 );
		worldDamage3 = atlas.findRegion( "world_damage_test", 3 );
		worldDamage4 = atlas.findRegion( "world_damage_test", 4 );
		
		font_quicksand = new BitmapFont( Gdx.files.internal( "font/generated_quicksand.fnt" ) );
		
//		textButtonStyle = new TextButtonStyle();
//		textButtonStyle.up = new TextureRegionDrawable( atlas.findRegion( "grey_button10" ) );
//		textButtonStyle.down = new TextureRegionDrawable( atlas.findRegion( "grey_button11" ) );
//		textButtonStyle.font = new BitmapFont();
//		textButtonStyle.fontColor = CColor.COLOR_BG;
		
//		checkboxStyle = new CheckBoxStyle();
//		checkboxStyle.checkboxOn = new TextureRegionDrawable( atlas.findRegion( "grey_boxCheckmark" ) );
//		checkboxStyle.checkboxOff = new TextureRegionDrawable( atlas.findRegion( "grey_boxCross" ) );
//		checkboxStyle.font = new BitmapFont();
				
		SOUND_CLICK = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/click.wav"));
		
		MUSIC_SOLEM_VOW = Gdx.audio.newMusic( Gdx.files.internal("sounds/Solemn_Vow_tabletopaudio_intro.mp3") );
		
		GAME_MODE_ARCADE = atlas.findRegion( "mode_arcade" );
		GAME_MODE_CHALLENGE = atlas.findRegion( "mode_challenge" );
		GAME_MODE_SECRET = atlas.findRegion( "mode_secret" );
	}

}
