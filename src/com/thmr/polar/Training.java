package com.thmr.polar;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**  
* Training class contains the data for a training such as sport type, duration, distance etc 
*   
* @author Thomas Mayer
* @version 1.0 
*/ 
public class Training
{
	private long id;
	private long duration;
	private String swimDistance;
	private String trainingLoadHtml;
	private boolean hasTrainingTarget;
	private boolean swimmingSport;
	private String swimmingPoolUnits;
	private double distance;
	private int calories;
	private int hrAvg;
	private String note;
	private String sportName;
	private int sportId;
	private String startDate;
	private long recoveryTime;
	private String iconUrl;

	public Training()
	{
		super();
	}

	public Training(long id, long duration, double distance, int calories, int hrAvg, String note, String sportName,
			int sportId, String startDate, long recoveryTime, String iconUrl)
	{
		super();
		this.id = id;
		this.duration = duration;
		this.distance = distance;
		this.calories = calories;
		this.hrAvg = hrAvg;
		this.note = note;
		this.sportName = sportName;
		this.sportId = sportId;
		this.startDate = startDate;
		this.recoveryTime = recoveryTime;
		this.iconUrl = iconUrl;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getDuration()
	{
		return duration;
	}

	public void setDuration(long duration)
	{
		this.duration = duration;
	}
			
	public String getDurationAsString()
	{
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(duration),
			    TimeUnit.MILLISECONDS.toMinutes(duration) % TimeUnit.HOURS.toMinutes(1),
			    TimeUnit.MILLISECONDS.toSeconds(duration) % TimeUnit.MINUTES.toSeconds(1));
		
		return hms;
	}

	public double getDistance()
	{
		return distance;
	}
	
	public double getDistanceInKm()
	{
		if(distance != 0)
		{
			return distance / 1000;
		}
		
		return 0.0;
	}

	public void setDistance(double distance)
	{
		this.distance = distance;
	}

	public int getCalories()
	{
		return calories;
	}

	public void setCalories(int calories)
	{
		this.calories = calories;
	}

	public int getHrAvg()
	{
		return hrAvg;
	}

	public void setHrAvg(int hrAvg)
	{
		this.hrAvg = hrAvg;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getSportName()
	{
		return sportName;
	}

	public void setSportName(String sportName)
	{
		this.sportName = sportName;
	}

	public int getSportId()
	{
		return sportId;
	}

	public void setSportId(int sportId)
	{
		this.sportId = sportId;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public long getRecoveryTime()
	{
		return recoveryTime;
	}

	public void setRecoveryTime(long recoveryTime)
	{
		this.recoveryTime = recoveryTime;
	}

	public String getIconUrl()
	{
		return iconUrl;
	}

	public void setIconUrl(String iconUrl)
	{
		this.iconUrl = iconUrl;
	}

	public String getSwimDistance()
	{
		return swimDistance;
	}

	public void setSwimDistance(String swimDistance)
	{
		this.swimDistance = swimDistance;
	}

	public String getTrainingLoadHtml()
	{
		return trainingLoadHtml;
	}

	public void setTrainingLoadHtml(String trainingLoadHtml)
	{
		this.trainingLoadHtml = trainingLoadHtml;
	}

	public boolean isHasTrainingTarget()
	{
		return hasTrainingTarget;
	}

	public void setHasTrainingTarget(boolean hasTrainingTarget)
	{
		this.hasTrainingTarget = hasTrainingTarget;
	}

	public boolean isSwimmingSport()
	{
		return swimmingSport;
	}

	public void setSwimmingSport(boolean swimmingSport)
	{
		this.swimmingSport = swimmingSport;
	}

	public String getSwimmingPoolUnits()
	{
		return swimmingPoolUnits;
	}

	public void setSwimmingPoolUnits(String swimmingPoolUnits)
	{
		this.swimmingPoolUnits = swimmingPoolUnits;
	}

	
}
