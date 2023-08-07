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

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EPublisherFile extends EPublisherMedia implements Cloneable{

	private long fileSizeInbytes;
	private long liferayFileEntryId;

	public void setFileSizeInbytes(long fileSizeInbytes) {

		this.fileSizeInbytes = fileSizeInbytes;
	}

	public long getFileSizeInbytes() {

		return fileSizeInbytes;
	}

	public void setLiferayFileEntryId(long liferayFileEntryId) {

		this.liferayFileEntryId = liferayFileEntryId;
	}

	public long getLiferayFileEntryId() {

		return liferayFileEntryId;
	}
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	/**
	 * @return copy of EpublisherFile
	 */
	public EPublisherFile copyObject() 
	{
		EPublisherFile newEPublisherFile = new EPublisherFile();	
		
		newEPublisherFile.setFileName(getFileName());
		newEPublisherFile.setFileSizeInbytes(getFileSizeInbytes());
		newEPublisherFile.setFolderid(getFolderid());
		newEPublisherFile.setLiferayFileEntryId(getLiferayFileEntryId());
		newEPublisherFile.setName(getName());
		newEPublisherFile.setSortOrder(getSortOrder());
		newEPublisherFile.setUrl(getUrl());
		newEPublisherFile.setUsername(getUsername());

		return newEPublisherFile;
	}
}
