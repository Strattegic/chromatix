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
	
	public static TextureRegion ballRed;
	public static TextureRegion ballGreen;
	public static TextureRegion ballYellow;
	public static TextureRegion ballBlue;
	public static Skin uiSkin;
	public static TextureRegion logo;
	
	private static TextureRegionDrawable buttonRed;
	private static TextureRegionDrawable buttonGreen;
	private static TextureRegionDrawable buttonBlue;
  public static TextureRegion buttonBg;
  
  public static TextureRegion shieldRed;
  public static TextureRegion shieldGreen;
  public static TextureRegion shieldBlue;
	
	public static TextureRegion heartFull;
	public static TextureRegion heartFullHalfTransparent;
	public static TextureRegion heartEmpty;
	public static BitmapFont font_quicksand;
	public static TextureRegion gameControl;
	public static TextureRegionDrawable kenney_ui_home;
  public static TextureRegionDrawable kenney_ui_home_half_transparent;
	
	public static TextButtonStyle textButtonStyle;
	
	public static TextureRegion wheel;
	
	public static Sound SOUND_CLICK;
	public static Music MUSIC_SOLEM_VOW;

  public static TextureRegion earth;
		
  public static TextureRegion getBall( Ball ball )
  {
    if( ball.getType() == Ball.BALL_TYPE__NORMAL && ball.getColor().getId() == CColor.RED )
      return ballRed;
    else if( ball.getType() == Ball.BALL_TYPE__NORMAL && ball.getColor().getId() == CColor.GREEN )
      return ballGreen;
    else if( ball.getType() == Ball.BALL_TYPE__NORMAL && ball.getColor().getId() == CColor.BLUE )
      return ballBlue;
    else
      return ballRed;
  }
	
	public static TextureRegionDrawable getColorButton( CColor color )
	{
    switch( color.getId() )
    {
      case CColor.BLUE: return AssetLoader.buttonBlue;
      case CColor.RED: return AssetLoader.buttonRed;
      case CColor.GREEN: return AssetLoader.buttonGreen;
      default: return AssetLoader.buttonGreen;
    }
	}
	
	public static TextureRegion getShieldTexture( CColor color )
  {
    switch( color.getId() )
    {
      case CColor.BLUE: return AssetLoader.shieldBlue;
      case CColor.RED: return AssetLoader.shieldRed;
      case CColor.GREEN: return AssetLoader.shieldGreen;
      default: return AssetLoader.shieldGreen;
    }
  }
	
	public static void load() 
	{
		atlas = new TextureAtlas(Gdx.files.internal("packer/dingens.atlas"));
				
		heartFull = atlas.findRegion( "hud_heartFull" );
		heartFullHalfTransparent = atlas.findRegion( "hud_heartFull_half_transparent" );
		heartEmpty = atlas.findRegion( "hud_heartEmpty" );
		gameControl = atlas.findRegion( "game_control_amount" );
		uiSkin = new Skin( Gdx.files.internal("skin/uiskin.json") );
		
		ballRed = atlas.findRegion( "ball_red" );
		ballGreen = atlas.findRegion( "ball_green" );
		ballYellow = atlas.findRegion( "ball_yellow" );
		ballBlue = atlas.findRegion( "ball_blue" );
		
		buttonRed = new TextureRegionDrawable( atlas.findRegion( "color_button_red" ) );
		buttonBlue = new TextureRegionDrawable( atlas.findRegion( "color_button_blue" ) );
		buttonGreen = new TextureRegionDrawable( atlas.findRegion( "color_button_green" ) );
		buttonBg = new TextureRegion( atlas.findRegion( "color_button_bg" ) );
		
		earth = new TextureRegion( atlas.findRegion( "earth" ) );
		shieldRed = new TextureRegion( atlas.findRegion( "shield_red" ) );
		shieldGreen = new TextureRegion( atlas.findRegion( "shield_green" ) );
		shieldBlue = new TextureRegion( atlas.findRegion( "shield_blue" ) );
		
		wheel = atlas.findRegion( "wheel" );
		
		logo = atlas.findRegion( "chromatix_white" );
		font_quicksand = new BitmapFont( Gdx.files.internal( "font/generated_quicksand.fnt" ) );
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = new TextureRegionDrawable( atlas.findRegion( "grey_button10" ) );
		textButtonStyle.down = new TextureRegionDrawable( atlas.findRegion( "grey_button11" ) );
		textButtonStyle.font = new BitmapFont();
		textButtonStyle.fontColor = CColor.COLOR_BG;
		
		checkboxStyle = new CheckBoxStyle();
		checkboxStyle.checkboxOn = new TextureRegionDrawable( atlas.findRegion( "grey_boxCheckmark" ) );
		checkboxStyle.checkboxOff = new TextureRegionDrawable( atlas.findRegion( "grey_boxCross" ) );
		checkboxStyle.font = new BitmapFont();
				
		SOUND_CLICK = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/click.wav"));
		
		MUSIC_SOLEM_VOW = Gdx.audio.newMusic( Gdx.files.internal("sounds/Solemn_Vow_tabletopaudio_intro.mp3") );
		
		GAME_MODE_ARCADE = atlas.findRegion( "mode_arcade" );
		GAME_MODE_CHALLENGE = atlas.findRegion( "mode_challenge" );
		GAME_MODE_SECRET = atlas.findRegion( "mode_secret" );
		
		kenney_ui_home = new TextureRegionDrawable( atlas.findRegion( "kenney_ui_home" ) );
		kenney_ui_home_half_transparent = new TextureRegionDrawable( atlas.findRegion( "kenney_ui_home_half_transparent" ) );
	}

}
