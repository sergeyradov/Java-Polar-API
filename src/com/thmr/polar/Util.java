package com.thmr.polar;

/**  
* Utility class
*   
* @author Thomas Mayer
* @version 1.0 
*/ 
public class Util
{
	static public boolean contains(final int[] array, final int key)
	{
		for (final int i : array)
		{
			if (i == key)
			{
				return true;
			}
		}
		return false;
	}

}
