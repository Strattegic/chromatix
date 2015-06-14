package com.strattegic.chromatix.game.helpers;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Utils
{
  static FileHandle logFile = Gdx.files.local( "log.txt" );

  public static int rand( int min, int max )
  {
    Random rand = new Random();
    int randomNum = rand.nextInt( ( max - min ) + 1 ) + min;
    return randomNum;
  }

  public static Vector2 rand( ArrayList<Vector2> pools )
  {
    Vector2 pool = pools.get( Utils.rand( 0, pools.size() - 1 ) );
    return null;
  }

  public static Vector2 getRandomBallPosition()
  {
    int abst = 1000;
    int orientation = rand( 1, 4 );
    if ( orientation == 1 )
      // North
      return new Vector2( rand( -abst, abst + Constants.WIDTH ), rand( Constants.HEIGHT, abst + Constants.HEIGHT ) );
    else if ( orientation == 2 )
      // West
      return new Vector2( rand( -abst, 0 ), rand( -abst, abst + Constants.HEIGHT ) );
    else if ( orientation == 3 )
      return new Vector2( rand( Constants.WIDTH, Constants.WIDTH + abst ), rand( -1000, 1000 + Constants.HEIGHT ) );
    else
      return new Vector2( rand( -abst, abst + Constants.WIDTH ), rand( -1000, 0 ) );

  }

  public static void logToFile( String text )
  {
    logFile.writeString( text, true );
    logFile.writeString( "\n", true );
  }

  /**
   * Returns the Position of an actor in a Stage in World Coordinates...
   * @param actor
   * @return
   */
  public static Vector2 getStageLocation( Actor actor )
  {
    return actor.localToStageCoordinates( new Vector2( 0, 0 ) );
  }
}
