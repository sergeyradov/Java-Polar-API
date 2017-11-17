package polarflow;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import com.thmr.polar.Config;
import com.thmr.polar.PolarSDK;
import com.thmr.polar.Training;
import com.thmr.polar.TrainingList;

/**  
* Sample class for using the Polar Java API
* 
* java PolarFlow <username> <password>
*   
* @author Thomas Mayer
* @version 1.0 
*/ 
public class PolarFlow
{
	public static void main(String[] args)
	{
		for (String s: args) {
            System.out.println(s);
        }
		
		// create java sdk object
		PolarSDK polarSDK = new PolarSDK();
		
		// login with username (email) and password
		polarSDK.login(args[0], args[1]);
		
		// get training data
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		//int[] sportIds = {Config.SPORT_ID_MOUNTAIN_BIKING,Config.SPORT_ID_ROAD_CYCLING};
		int[] sportIds = {Config.SPORT_ID_MOUNTAIN_BIKING};
		//int[] sportIds = {}; // get all sport ids
		fromDate.set(2017, 7, 1);
		toDate.set(2017, 10, 15);
		
		TrainingList trainingList = polarSDK.getTrainingData(fromDate.getTime(), toDate.getTime(), sportIds);
		int count = 0;
		
		for (Training training : trainingList.getTrainings())
		{
			
			System.out.println("no: " + count++ + ", " +
					"sportName: " + training.getSportName() + ", " +
					"sportId: " + training.getSportId() + ", " +
					"distance: " + training.getDistance() + ", " +
					"duration: " + training.getDuration() + ", " + 
					"durasdur: " + training.getDurationAsString());
			
			System.out.println("overall mountainbiking distance: " + trainingList.getOverallDistance(new int[]{Config.SPORT_ID_MOUNTAIN_BIKING}) + ", overall duration: " + trainingList.getOverallDurationAsString(new int[]{Config.SPORT_ID_MOUNTAIN_BIKING}));
			
			//System.out.println("id: " + training.getSportId() + ", name: " + training.getSportName());
		}
		
		Training t = trainingList.getTrainings().get(0);
		try
		{
			
			File myTCXFile = new File("c:\\temp\\test.tcx");
			FileOutputStream fosTCX = new FileOutputStream(myTCXFile);
			polarSDK.downloadTCX(t, fosTCX, true);
			fosTCX.close();

			File myGPXFile = new File("c:\\temp\\test.gpx");
			FileOutputStream fosGPX = new FileOutputStream(myGPXFile);
			polarSDK.downloadGPX(t, fosGPX, true);
			fosGPX.close();

			File myCSVFile = new File("c:\\temp\\test.csv");
			FileOutputStream fosCSV = new FileOutputStream(myCSVFile);
			polarSDK.downloadCSV(t, fosCSV, true);
			fosCSV.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
