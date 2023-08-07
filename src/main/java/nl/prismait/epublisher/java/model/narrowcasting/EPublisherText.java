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
public class EPublisherText extends ContentBlock {
	
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
	public EPublisherText copyObject() 
	{
		EPublisherText newEPublisherText = new EPublisherText();
		
		newEPublisherText.setContainerId(getContainerId());
		newEPublisherText.setContent(getContent());
		newEPublisherText.setEnabled(isEnabled());
		newEPublisherText.setTitle(getTitle());

		return newEPublisherText;
	}
}
