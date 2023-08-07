// ***************************************************************************
// 
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL$
//		$Id$
// 
// ***************************************************************************
package nl.prismait.epublisher.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EPublisherLink extends EPublisherMedia {

	@Override
	public void setUrl(String url) {

		this.url = url;
	}

	@Override
	@Column(name="url", insertable=false, updatable=false)
	public String getUrl(){
		return url;
	}
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	public EPublisherLink copyObject() 
	{
		EPublisherLink newEPublisherLink = new EPublisherLink();	
		
		newEPublisherLink.setFileName(getFileName());
		newEPublisherLink.setFolderid(getFolderid());
		newEPublisherLink.setName(getName());
		newEPublisherLink.setSortOrder(getSortOrder());
		newEPublisherLink.setUrl(getUrl());
		newEPublisherLink.setUsername(getUsername());

		return newEPublisherLink;
	}
}
