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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import nl.prismait.epublisher.java.model.EPublisherImage;

@Entity
public class EPublisherTextAndImage extends ContentBlock
{
	private String content;
	private EPublisherImage image;

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getContent()
	{
		return content;
	}

	public void setImage(EPublisherImage image)
	{
		this.image = image;
	}

	@OneToOne
	public EPublisherImage getImage()
	{
		return image;
	}
	
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public EPublisherTextAndImage copyObject() 
	{
		EPublisherTextAndImage newEPublisherTextAndImage = new EPublisherTextAndImage();
		
		newEPublisherTextAndImage.setContainerId(getContainerId());
		newEPublisherTextAndImage.setContent(getContent());
		newEPublisherTextAndImage.setEnabled(isEnabled());
		newEPublisherTextAndImage.setTitle(getTitle());
		newEPublisherTextAndImage.setImage(getImage());
		
		return newEPublisherTextAndImage;
	}
}
