// ***************************************************************************
//
// Copyright 2013, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.model.narrowcasting;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import nl.prismait.epublisher.java.model.VersionedBaseEntity;

@SuppressWarnings("unused")
@JsonTypeInfo(
			use = Id.NAME,
			include = As.PROPERTY,
			property = "dtype")
@JsonSubTypes({
		@Type(value = EPublisherClock.class, name = "EPublisherClock"),
		@Type(value = EPublisherDate.class, name = "EPublisherDate"),
		@Type(value = EPublisherNarrowcastingImage.class, name = "EPublisherNarrowcastingImage"),
		@Type(value = EPublisherRss.class, name = "EPublisherRss"),
		@Type(value = EPublisherTwitterFeed.class, name = "EPublisherTwitterFeed"),
		@Type(value = EPublisherText.class, name = "EPublisherText"),
		@Type(value = EPublisherVideo.class, name = "EPublisherVideo"),
		@Type(value = EPublisherTitle.class, name = "EPublisherTitle"),
		@Type(value = EPublisherTelephoneNumber.class, name = "EPublisherTelephoneNumber"),
		@Type(value = EPublisherWeatherInfo.class, name = "EPublisherWeatherInfo"),
		@Type(value = EPublisherTravelInfo.class, name = "EPublisherTravelInfo"),
		@Type(value = EPublisherWebsite.class, name = "EPublisherWebsite"),
		@Type(value = EPublisherTextAndImage.class, name = "EPublisherTextAndImage"),
//		@Type(value = EPublisherWayfinder.class, name = "EPublisherWayfinder"),
		@Type(value = EPublisherPowerpoint.class, name = "EPublisherPowerpoint")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public abstract class ContentBlock extends VersionedBaseEntity
{
	private int containerId;
	private boolean enabled;
	private String title;
	private boolean transparent;
	private String fileuuid;
	private Integer variant;

	public int getContainerId()
	{
		return containerId;
	}

	public void setContainerId(int containerId)
	{
		this.containerId = containerId;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}
	
	public boolean isTransparent() {
		return transparent;
	}

	public void setTransparent(boolean transparent) {
		this.transparent = transparent;
	}
	
	public String getFileuuid() {
		return fileuuid;
	}

	public void setFileuuid(String fileuuid) {
		this.fileuuid = fileuuid;
	}
	
	public Integer getVariant() {
		return variant;
	}

	public void setVariant(Integer variant) {
		this.variant = variant;
	}
	// This method will skip to the children clone method
	public ContentBlock copyObject() 
	{
		return null;
		
	}
}
