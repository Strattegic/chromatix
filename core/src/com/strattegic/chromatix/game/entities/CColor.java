package com.strattegic.chromatix.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

public class CColor
{
  public static final int CYAN = 0;
  public static final int GREEN = 1;
  public static final int GREY = 2;
  public static final int ORANGE = 3;
  public static final int PURPLE = 4;
  public static final int YELLOW = 5;
  
  public static Color COLOR_BG = Color.valueOf("333333FF");
  
  private int colorId;
  
  private CColor( int id, String name )
  {
    colorId = id;
  }
  
  public CColor( int id )
  {
    colorId = id;
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
      case CYAN: return "cyan";
      case GREEN: return "green";
      case GREY: return "grey";
      case ORANGE: return "orange";
      case PURPLE: return "purple";
      case YELLOW: return "yellow";
      default: return "DEFAULT";
    }
  }

  public int getId()
  {
    return colorId;
  }

  @Override
  public String toString()
  {
    // TODO Auto-generated method stub
    return getName( colorId );
  }  
}
