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
public class EPublisherTwitterFeed extends ContentBlock {
	
	private String embedCode;

	public void setEmbedCode(String embedCode)
	{
		this.embedCode = embedCode;
	}

	public String getEmbedCode()
	{
		return embedCode;
	}
	
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public EPublisherTwitterFeed copyObject() 
	{
		EPublisherTwitterFeed newEPublisherTwitterFeed = new EPublisherTwitterFeed();
		
		newEPublisherTwitterFeed.setContainerId(getContainerId());
		newEPublisherTwitterFeed.setEmbedCode(getEmbedCode());
		newEPublisherTwitterFeed.setEnabled(isEnabled());
		newEPublisherTwitterFeed.setTitle(getTitle());

		return newEPublisherTwitterFeed;
	}
}
