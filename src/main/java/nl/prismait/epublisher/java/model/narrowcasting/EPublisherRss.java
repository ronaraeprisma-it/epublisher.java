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
public class EPublisherRss extends ContentBlock {

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
	public EPublisherRss copyObject() 
	{
		EPublisherRss newEPublisherRss = new EPublisherRss();
		
		newEPublisherRss.setContainerId(getContainerId());
		newEPublisherRss.setEnabled(isEnabled());
		newEPublisherRss.setTitle(getTitle());
		newEPublisherRss.setUrl(getUrl());
		
		return newEPublisherRss;
	}
}
