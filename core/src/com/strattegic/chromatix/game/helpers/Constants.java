package com.strattegic.chromatix.game.helpers;

import java.util.ArrayList;

import com.strattegic.chromatix.game.entities.RadColor;

public class Constants 
{
	private static Constants instance = null;
	
	public static ArrayList<RadColor> list;
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = 848;
	
	public static class COLORS
	{
		public static String getName( int color )
		{
			switch( color )
			{
				case 0: return "red";
				case 1: return "green";
				case 2: return "yellow";
				case 3: return "blue";
				default: return "DEFAULT";
			}
		}
		
		public static final int RED = 0;
		public static final int GREEN = 1;
		public static final int YELLOW = 2;
		public static final int BLUE = 3;
	}
	
	private Constants()
	{
		list = new ArrayList<RadColor>();
		list.add( new RadColor( COLORS.BLUE, 0, 72 ) );
		list.add( new RadColor( COLORS.GREEN, 72, 144 ) );
		list.add( new RadColor( COLORS.YELLOW, 144, 216 ) );
		list.add( new RadColor( COLORS.RED, 216, 288 ) );
		list.add( new RadColor( COLORS.RED, 288, 360 ) );
	}
	
	public static Constants getInstance()
	{
		if( instance == null )
		{
			return new Constants();
		}
		return instance;
	}
	
	public ArrayList<RadColor> getRadColors()
	{
		return list;
	}
}
