package com.strattegic.chromatix.game.screens;

public class MapData
{
  private long score;
  private int maxHealth;
  private int health;
  
  public MapData()
  {
    score = 0;
    maxHealth = 5;
    health = 5;
  }
  
  public long getScore()
  {
    return score;
  }
  public void setScore( long score )
  {
    this.score = score;
  }
  public int getMaxHealth()
  {
    return maxHealth;
  }
  public void setMaxHealth( int maxHealth )
  {
    this.maxHealth = maxHealth;
  }
  public int getHealth()
  {
    return health;
  }
  public void setHealth( int health )
  {
    this.health = health;
  }
  
  
}
