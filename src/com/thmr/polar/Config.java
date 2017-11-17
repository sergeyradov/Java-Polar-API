package com.thmr.polar;

/**  
* Config class for some constants
*   
* @author Thomas Mayer
* @version 1.0 
*/ 
public class Config
{
	public static String POLAR_URL = "https://flow.polar.com";
	public static String LOGIN_URL = POLAR_URL + "/login";
	public static String TRAINING_URL = POLAR_URL + "/api/training/history";
	public static String TCX_URL = POLAR_URL + "/api/export/training/tcx";
	public static String GPX_URL = POLAR_URL + "/api/export/training/gpx";
	public static String CSV_URL = POLAR_URL + "/api/export/training/csv";
	
	public static int SPORT_ID_RUNNING = 1;
	public static int SPORT_ID_MOUNTAIN_BIKING = 5;
	public static int SPORT_ID_STRENGTH_TRAINING = 15;
	public static int SPORT_ID_OTHER_OUTDOOR = 16;
	public static int SPORT_ID_INDOOR_CYCLING = 18;
	public static int SPORT_ID_INLINE_SKATING = 29;
	public static int SPORT_ID_ROAD_CYCLING = 38;
}
