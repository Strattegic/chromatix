package com.strattegic.chromatix.game.helpers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
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
  
  public static TextureRegion shieldCyan;
  public static TextureRegion shieldPurple;
  public static TextureRegion shieldYellow;
	public static TextureRegion enemyPurple;
	public static TextureRegion enemyCyan;
	public static TextureRegion enemyYellow;
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

  private static ArrayList<FontStorage> listOfFonts;

  public static class FONT_NAMES
  {
    public static final String FONT_MAIN = "fonts/crew36.TTF";
  }
		
  public static TextureRegion getBall( Ball ball )
  {
    switch( ball.getColor().getId() )
    {
      case CColor.CYAN: return enemyCyan;
      case CColor.YELLOW: return enemyYellow;
      case CColor.PURPLE: return enemyPurple;
      default: return enemyCyan;
    }
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
	  switch( color.getId() )
	  {
      case CColor.CYAN: return shieldCyan;
      case CColor.YELLOW: return shieldYellow;
      case CColor.PURPLE: return shieldPurple;
      default: return shieldCyan;
	  }
  }

  /**
   * Generates a Font with the given size and name.
   * No Font is ever generated twice. Every size and name combination is stored.
   * @param fontName
   * @param size
   * @return
   */
  public static BitmapFont getFont( String fontName, float size )
  {
    for ( FontStorage fs : listOfFonts )
    {
      if ( fs.getFontName().equals( fontName ) && size == fs.getSize() )
      {
        return fs.getBitmapFont();
      }
    }
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal( fontName ) );
    FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    parameter.size = (int) Math.ceil( size );
    parameter.minFilter = TextureFilter.Nearest;
    parameter.magFilter = TextureFilter.MipMapLinearNearest;
    BitmapFont bitmapFont = generator.generateFont( parameter ); // font size 12
                                                                 // pixels
    generator.dispose();
    listOfFonts.add( new FontStorage( fontName, bitmapFont, size ) );
    return bitmapFont;
  }

	public static void load() 
	{
		atlas = new TextureAtlas(Gdx.files.internal("packer/dingens.atlas"));

    uiSkin = new Skin( Gdx.files.internal("skin/uiskin.json") );
		enemyPurple = atlas.findRegion( "enemy_purple" );
		enemyCyan = atlas.findRegion( "enemy_cyan" );
		enemyYellow = atlas.findRegion( "enemy_yellow" );
		world = atlas.findRegion( "world" );		
		logo = atlas.findRegion( "chromatix_logo" );
		shieldCyan = atlas.findRegion( "shield_cyan" );
		shieldPurple = atlas.findRegion( "shield_purple" );
		shieldYellow = atlas.findRegion( "shield_yellow" );
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
		
		font_quicksand = new BitmapFont( Gdx.files.internal( "fonts/generated_quicksand.fnt" ) );
						
		SOUND_CLICK = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/click.wav"));
		
		MUSIC_SOLEM_VOW = Gdx.audio.newMusic( Gdx.files.internal("sounds/Solemn_Vow_tabletopaudio_intro.mp3") );
		
		GAME_MODE_ARCADE = atlas.findRegion( "mode_arcade" );
		GAME_MODE_CHALLENGE = atlas.findRegion( "mode_challenge" );
		GAME_MODE_SECRET = atlas.findRegion( "mode_secret" );
		
		listOfFonts = new ArrayList<FontStorage>();
	}

}
