package com.strattegic.chromatix.game.entities;

import com.badlogic.gdx.graphics.Color;

public class CColor extends Color
{
  public static final int RED = 0;
  public static final int GREEN = 1;
  public static final int YELLOW = 2;
  public static final int BLUE = 3;
  public static final int PURPLE = 4;
  
  public static Color COLOR_RED = Color.valueOf( "dc0202" );
  public static Color COLOR_GREEN = Color.valueOf( "0ada00" );
  public static Color COLOR_BLUE = Color.valueOf( "0419e4" );
  public static Color COLOR_BG = Color.valueOf("333333FF");
  
  public static String getName( int color )
  {
    switch( color )
    {
      case 0: return "red";
      case 1: return "green";
      case 2: return "yellow";
      case 3: return "blue";
      case 4: return "purple";
      default: return "DEFAULT";
    }
  }
}
