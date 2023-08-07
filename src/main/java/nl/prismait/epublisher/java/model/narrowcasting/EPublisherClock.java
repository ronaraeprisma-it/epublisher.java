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

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class EPublisherClock extends ContentBlock {
	
	private String timezone;

	
	public String getTimezone() {
	
		return timezone;
	}

	
	public void setTimezone(String timezone) {
	
		this.timezone = timezone;
	}
	
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public EPublisherClock copyObject() 
	{
		EPublisherClock newEPublisherClock = new EPublisherClock();
		
		newEPublisherClock.setContainerId(getContainerId());
		newEPublisherClock.setEnabled(isEnabled());
		newEPublisherClock.setTitle(getTitle());
		newEPublisherClock.setTimezone(getTimezone());
		
		return newEPublisherClock;
	}
}
