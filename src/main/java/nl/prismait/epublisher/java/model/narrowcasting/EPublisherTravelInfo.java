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
public class EPublisherTravelInfo extends ContentBlock {
	
	public enum ReportType
	{
		TRAIN_DEPARTURE_TIMES, RAILWAY_MAP
	}
	
	private String content;

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getContent()
	{
		return content;
	}

	@Transient
	public String getUrl()
	{
		// not defined what to do!!!!!
		// if (content != null)
		// {
		// if (content.equals(ReportType.TRAIN_DEPARTURE_TIMES.name()))
		// return "http://www.ns.nl/actuele-vertrektijden/avt?station=rta";
		// if (content.equals(ReportType.RAILWAY_MAP.name()))
		// return "http://www.ns.nl/cgi-bin/spoorkaart/spoorkaart";
		// }
		return "http://vertrekwijzer.9292ov.nl/xml.aspx?s=821";
	}
	
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public EPublisherTravelInfo copyObject() 
	{
		EPublisherTravelInfo newEPublisherTravelInfo = new EPublisherTravelInfo();
		
		newEPublisherTravelInfo.setContainerId(getContainerId());
		newEPublisherTravelInfo.setContent(getContent());
		newEPublisherTravelInfo.setEnabled(isEnabled());
		newEPublisherTravelInfo.setTitle(getTitle());

		return newEPublisherTravelInfo;
	}
}
