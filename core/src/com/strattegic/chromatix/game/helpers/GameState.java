package com.strattegic.chromatix.game.helpers;

public class GameState
{
  public static final int RUNNING = 0;
  public static final int PAUSED = 1;
  public static final int GAME_OVER = 2;

  private static int state = 0;
    
  public static int getState()
  {
    return state;
  }
  
  public static boolean isPaused()
  {
    return state == PAUSED;
  }
  
  public static boolean isRunning()
  {
    return state == RUNNING;
  }
  
  public static boolean isGameOver()
  {
    return state == GAME_OVER;
  }

  public static void setState( int state )
  {
    GameState.state = state;
  }
  
  public static void setPaused()
  {
    GameState.state = PAUSED;
  }
  
  public static void setGameOver()
  {
    GameState.state = GAME_OVER;
  }
  
  public static void setRunning()
  {
    GameState.state = RUNNING;
  }
}
