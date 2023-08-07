// ***************************************************************************
//
// Copyright 2015, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.model.narrowcasting;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class EPublisherWebsite extends ContentBlock
{
	private String url;

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}
	
	
	
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public EPublisherWebsite copyObject() 
	{
		EPublisherWebsite newEPublisherWebsite = new EPublisherWebsite();
		
		newEPublisherWebsite.setContainerId(getContainerId());
		newEPublisherWebsite.setEnabled(isEnabled());
		newEPublisherWebsite.setTitle(getTitle());
		newEPublisherWebsite.setUrl(getUrl());
		// copy transparency status also for this content block (WTC lobby template)
		newEPublisherWebsite.setTransparent(isTransparent());
		
		if (getVariant() != null)
		{
			newEPublisherWebsite.setVariant(getVariant());
		}
		
		return newEPublisherWebsite;
	}
}
