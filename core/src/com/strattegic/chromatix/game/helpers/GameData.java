package com.strattegic.chromatix.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameData 
{
  private static GameData gameData;
  private Preferences prefs;
  
	public static float VOLUME_MUSIC = 0.8f;
	public static float VOLUME_SOUND = 0.8f;
	
	public static long SCORE = 0;
	public static int LIVES_CURRENT = 5;
	public static int LIVES_MAX = 5;
	
	private GameData()
	{
	  prefs = Gdx.app.getPreferences("ChromatixPreferences");
	}
	
	public static GameData getInstance()
	{
	  if( gameData == null )
	  {
	    return new GameData();
	  }
	  return gameData;
	}
	
	public Long getHighscoreArcade()
	{
    return prefs.getLong( "Highscore_Arcade", 0 );
	}

  public void setHighscoreArcade( long score )
  {
    prefs.putLong( "Highscore_Arcade", score );
  }
}
