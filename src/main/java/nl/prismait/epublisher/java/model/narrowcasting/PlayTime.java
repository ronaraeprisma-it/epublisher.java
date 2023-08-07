// ***************************************************************************
// 
//		Copyright 2013, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL$
//		$Id$
// 
// ***************************************************************************
package nl.prismait.epublisher.java.model.narrowcasting;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import nl.prismait.epublisher.java.model.VersionedBaseEntity;

@Entity
public class PlayTime extends VersionedBaseEntity implements Comparable<PlayTime> {

	private Set<Integer> days;
	private Date startDate;
	private Date endDate;
	private Integer startHour;
	private Integer startMinute;
	private Integer endHour;
	private Integer endMinute;
	private Integer phase;
	private Integer repetition;
	private Integer interval;
	

	@ElementCollection(fetch=FetchType.EAGER)
	public Set<Integer> getDays()
	{
		return days;
	}
	
	public void setDays(Set<Integer> days)
	{
		this.days = days;
	}
	
	public Date getStartDate()
	{
		return startDate;
	}
	
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	
	public Date getEndDate()
	{
		return endDate;
	}
	
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	
	public Integer getStartHour()
	{
		return startHour;
	}
	
	public void setStartHour(Integer startHour)
	{
		this.startHour = startHour;
	}
	
	public Integer getStartMinute()
	{
		return startMinute;
	}
	
	public void setStartMinute(Integer startMinute)
	{
		this.startMinute = startMinute;
	}
	
	public Integer getEndHour()
	{
		return endHour;
	}
	
	public void setEndHour(Integer endHour)
	{
		this.endHour = endHour;
	}
	
	public Integer getEndMinute()
	{
		return endMinute;
	}
	
	public void setEndMinute(Integer endMinute)
	{
		this.endMinute = endMinute;
	}
	
	public Integer getPhase() {
		return phase;
	}

	public void setPhase(Integer phase) {
		this.phase = phase;
	}

	public Integer getRepetition() {
		return repetition;
	}

	public void setRepetition(Integer repetition) {
		this.repetition = repetition;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public PlayTime copyObject()
	{
		PlayTime playTimeClone = new PlayTime();
		
		Set<Integer> daysClone = new HashSet<>();
		for (Integer i : days)
		{
			daysClone.add(i);
		}
		
		playTimeClone.setDays(daysClone);
		playTimeClone.setStartDate(startDate);
		playTimeClone.setEndDate(endDate);
		playTimeClone.setStartHour(startHour);
		playTimeClone.setStartMinute(startMinute);
		playTimeClone.setEndHour(endHour);
		playTimeClone.setEndMinute(endMinute);
		playTimeClone.setInterval(interval != null ? interval : null);
		playTimeClone.setRepetition(repetition != null ? repetition : null);
		playTimeClone.setPhase(phase != null ? phase : null);
		
		return playTimeClone;
	}

	@Override
	public int compareTo(PlayTime playtime)
	{
		int result = 0;
		
		result += Math.abs(compareDates(playtime.getStartDate(), this.getStartDate()));
		result += Math.abs(compareDates(playtime.getEndDate(), this.getEndDate()));
		
		result += Math.abs(compareIntegers(playtime.getStartHour(), this.getStartHour()));
		result += Math.abs(compareIntegers(playtime.getStartMinute(), this.getStartMinute()));
		result += Math.abs(compareIntegers(playtime.getEndHour(), this.getEndHour()));
		result += Math.abs(compareIntegers(playtime.getEndMinute(), this.getEndMinute()));
		
		if( !(this.getDays().containsAll(playtime.getDays())) )
			result += 1;
		
		return result;
	}
	
	private int compareDates(Date date1, Date date2)
	{
		int result = 0;
		
		if(date1 == null && date2 != null)
			result += 1;
		// this check was added for TICKET-1752
		if(date1 != null && date2 == null)
			result += 1;
		
		if(date1 != null && date2 != null)
			result += date1.compareTo(date2);
		
		return result;
	}
	
	private int compareIntegers(Integer int1, Integer int2)
	{
		int result = 0;
		
		if(int1 == null && int2 != null)
			result += 1;
		
		if(int1 != null)
			result += int1.compareTo(int2);
		
		return result;
	}
}
