package com.thmr.polar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**  
* TrainingList contains all the retrieved trainings as Training objects and some convenience methods for calculating the sum of trainings
*   
* @author Thomas Mayer
* @version 1.0 
*/ 
public class TrainingList
{
	private List<Training> trainings = new ArrayList<Training>();


	public TrainingList()
	{
		super();
	}

	public TrainingList(List<Training> trainings)
	{
		super();
	}

	public List<Training> getTrainings()
	{
		return trainings;
	}

	public void setTrainings(List<Training> trainings)
	{
		this.trainings = trainings;
	}
	
	/**
	 * 
	 * @param sportIds leave blank if all sport types should be considered
	 * @return
	 */
	public double getOverallDistance(int[] sportIds)
	{
		double overallDistance = 0.0;
		
		for(Training t : trainings)
		{
			//if(Arrays.asList(sportIds).contains(t.getSportId()))
			if(Util.contains(sportIds, t.getSportId()))
			{
				overallDistance += t.getDistance();
			}
		}
		
		return overallDistance;
	}
	
	/**
	 * 
	 * @param sportIds leave blank if all sport types should be considered
	 * @return
	 */
	public long getOverallDuration(int[] sportIds)
	{
		long overallDuration = 0;
		
		for(Training t : trainings)
		{
			if(Util.contains(sportIds, t.getSportId()))
			{
				overallDuration += t.getDuration();
			}
		}
		
		return overallDuration;
	}
	
	public String getOverallDurationAsString(int[] sportIds)
	{
		long overallDuration = getOverallDuration(sportIds);
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(overallDuration),
			    TimeUnit.MILLISECONDS.toMinutes(overallDuration) % TimeUnit.HOURS.toMinutes(1),
			    TimeUnit.MILLISECONDS.toSeconds(overallDuration) % TimeUnit.MINUTES.toSeconds(1));
		
		return hms;
	}
}
