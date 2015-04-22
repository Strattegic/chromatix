package com.strattegic.chromatix.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

public class CColor
{
  public static final int RED = 0;
  public static final int GREEN = 1;
  public static final int YELLOW = 2;
  public static final int BLUE = 3;
  public static final int PURPLE = 4;
  
  public static Color COLOR_BG = Color.valueOf("333333FF");
  
  private int colorId;
  private String colorName;
  
  private CColor( int id, String name )
  {
    colorId = id;
    colorName = name;
  }
  
  public CColor( int id )
  {
    colorId = id;
    switch( id )
    {
      case 0: colorName = "red";
      case 1: colorName = "green";
      case 2: colorName = "yellow";
      case 3: colorName = "blue";
      case 4: colorName = "purple";
      default: colorName = "DEFAULT";
    }
  }
  
  public static ArrayList<CColor> getColors()
  {
    ArrayList<CColor> list = new ArrayList<CColor>();
    list.add( new CColor( 0, "red" ) );
    list.add( new CColor( 1, "green" ) );
    list.add( new CColor( 2, "yellow" ) );
    list.add( new CColor( 3, "blue" ) );
    
    return list;
  }
  
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

  public int getId()
  {
    return colorId;
  }
}
