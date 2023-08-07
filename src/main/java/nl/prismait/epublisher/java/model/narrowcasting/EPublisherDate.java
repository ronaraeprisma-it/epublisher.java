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
public class EPublisherDate extends ContentBlock
{
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public EPublisherDate copyObject() 
	{
		EPublisherDate newEPublisherDate = new EPublisherDate();
		
		newEPublisherDate.setContainerId(getContainerId());
		newEPublisherDate.setEnabled(isEnabled());
		newEPublisherDate.setTitle(getTitle());

		return newEPublisherDate;
	}
}
