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
public class EPublisherImage extends EPublisherFile implements Cloneable{
	private Integer parentImageId = -1;
	private int width;
	private int height;
	private String altText;
	private String caption;
	private Boolean generated = false;
	private Boolean mobileupload = false;
	
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void setWidth(int width) {

		this.width = width;
	}

	public int getWidth() {

		return width;
	}

	public void setHeight(int height) {

		this.height = height;
	}

	public int getHeight() {

		return height;
	}

	public void setGenerated(Boolean generated)
	{
		this.generated = generated;
	}

	public Boolean getGenerated()
	{
		return generated;
	}

	public Integer getParentImageId()
	{
		return parentImageId;
	}

	public void setParentImageId(Integer parentImageId)
	{
		this.parentImageId = parentImageId;
	}


	public Boolean getMobileupload() {
		return mobileupload;
	}


	public void setMobileupload(Boolean mobileupload) {
		this.mobileupload = mobileupload;
	}
	
	@Override
	public EPublisherImage copyObject() 
	{
		EPublisherImage newEPublisherImage = new EPublisherImage();	
		
		newEPublisherImage.setAltText(getAltText());
		newEPublisherImage.setCaption(getCaption());
		newEPublisherImage.setFileName(getFileName());
		newEPublisherImage.setFolderid(getFolderid());
		newEPublisherImage.setFileSizeInbytes(getFileSizeInbytes());
		newEPublisherImage.setGenerated(getGenerated());
		newEPublisherImage.setHeight(getHeight());
		newEPublisherImage.setLiferayFileEntryId(getLiferayFileEntryId());
		newEPublisherImage.setName(getName());
		newEPublisherImage.setParentImageId(getParentImageId());
		newEPublisherImage.setSortOrder(getSortOrder());
		newEPublisherImage.setUrl(getUrl());
		newEPublisherImage.setUsername(getUsername());
		newEPublisherImage.setWidth(getWidth());
		newEPublisherImage.setMobileupload(getMobileupload());
		
		return newEPublisherImage;
	}
}
