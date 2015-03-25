package com.strattegic.chromatix.game.helpers;

import com.badlogic.gdx.graphics.Color;

public class Constants 
{	
//	public static Color COLOR_BACKGROUND = new Color( 10066329 );
	public static Color COLOR_BACKGROUND = new Color( 4210752 );	
	
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
				case 4: return "purple";
				default: return "DEFAULT";
			}
		}
		
		public static final int RED = 0;
		public static final int GREEN = 1;
		public static final int YELLOW = 2;
		public static final int BLUE = 3;
		public static final int PURPLE = 4;
	}
}
