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
public class EPublisherWeatherInfo extends ContentBlock {
	
	public enum ReportType
	{
		WEATHER_MAP, RAIN_MAP, FORCAST_3_DAY
	};
	
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
		if (content.equals(ReportType.RAIN_MAP.name()))
			return "http://narrowcast.weatheronyoursite.com/radar.gif";
		if (content.equals(ReportType.WEATHER_MAP.name()))
			return "http://narrowcast.weatheronyoursite.com/vandaag.png";
		if (content.equals(ReportType.FORCAST_3_DAY.name()))
			return "http://narrowcast.weatheronyoursite.com/4daagse.png";

		return "";
	}
	
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public EPublisherWeatherInfo copyObject() 
	{
		EPublisherWeatherInfo newEPublisherWeatherInfo = new EPublisherWeatherInfo();
		
		newEPublisherWeatherInfo.setContainerId(getContainerId());
		newEPublisherWeatherInfo.setContent(getContent());
		newEPublisherWeatherInfo.setEnabled(isEnabled());
		newEPublisherWeatherInfo.setTitle(getTitle());

		return newEPublisherWeatherInfo;
	}
}
