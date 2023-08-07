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
import javax.persistence.Transient;

@Entity
public class EPublisherTitle extends ContentBlock
{

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
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public EPublisherTitle copyObject() 
	{
		EPublisherTitle newEPublisherTitle = new EPublisherTitle();
		
		newEPublisherTitle.setContainerId(getContainerId());
		newEPublisherTitle.setContent(getContent());
		newEPublisherTitle.setEnabled(isEnabled());
		newEPublisherTitle.setTitle(getTitle());

		return newEPublisherTitle;
	}
}
