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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import nl.prismait.epublisher.java.model.EPublisherImage;
import nl.prismait.epublisher.java.model.VersionedBaseEntity;

@Entity
public class TemplateNarrowcasting extends VersionedBaseEntity {

	private String name;
	private EPublisherImage previewImage;
	private EPublisherImage defaultImage;
	private String htmlTemplate;
	private Set<ContainerArea> containerAreas;
	private String imageAspectRatio;
	private Integer templateImageWidth;
	private Integer templateImageHeight;
	private Boolean regenerateThumbnail;
	private Boolean wayfinderIconSupport;
	private Boolean enableTitle;
	
	@OneToOne
	public EPublisherImage getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(EPublisherImage defaultImage) {
		this.defaultImage = defaultImage;
	}

	public Integer getTemplateImageWidth() {
		return templateImageWidth;
	}

	public void setTemplateImageWidth(Integer templateImageWidth) {
		this.templateImageWidth = templateImageWidth;
	}

	public Integer getTemplateImageHeight() {
		return templateImageHeight;
	}

	public void setTemplateImageHeight(Integer templateImageHeight) {
		this.templateImageHeight = templateImageHeight;
	}

	public String getImageAspectRatio() {
		return imageAspectRatio;
	}

	public void setImageAspectRatio(String imageAspectRatio) {
		this.imageAspectRatio = imageAspectRatio;
	}

	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
	
	@OneToOne
	public EPublisherImage getPreviewImage() {
	
		return previewImage;
	}
	
	public void setPreviewImage(EPublisherImage previewImage) {
	
		this.previewImage = previewImage;
	}
	
	public String getHtmlTemplate() {
	
		return htmlTemplate;
	}
	
	public void setHtmlTemplate(String htmlTemplate) {
	
		this.htmlTemplate = htmlTemplate;
	}
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@OrderBy("containerPosition")
	public Set<ContainerArea> getContainerAreas() {
	
		return containerAreas;
	}
	
	public void setContainerAreas(Set<ContainerArea> containerAreas) {
	
		this.containerAreas = containerAreas;
	}


	public Boolean getRegenerateThumbnail() {
		return regenerateThumbnail;
	}

	public void setRegenerateThumbnail(Boolean regenerateThumbnail) {
		this.regenerateThumbnail = regenerateThumbnail;
	}
	
	@Column(name="wayfinder_icon_support")
	public Boolean getWayfinderIconSupport() {
		return wayfinderIconSupport;
	}

	public void setWayfinderIconSupport(Boolean wayfinderIconSupport) {
		this.wayfinderIconSupport = wayfinderIconSupport;
	}
	
	public Boolean getEnableTitle() {
		return enableTitle;
	}

	public void setEnableTitle(Boolean enableTitle) {
		this.enableTitle = enableTitle;
	}

	public TemplateNarrowcasting copyObject() 
	{
		TemplateNarrowcasting newTemplate = new TemplateNarrowcasting();
		
		// Name
		newTemplate.setName(getName());
		
		// Preview Image
		if (getPreviewImage() != null)
		{
			newTemplate.setPreviewImage(getPreviewImage());
		}
		
		// Default Image
		if (getDefaultImage() != null)
		{
			newTemplate.setDefaultImage(getDefaultImage());
		}
				
		// Html template
		newTemplate.setHtmlTemplate(getHtmlTemplate());
		
		// Container areas
		Set<ContainerArea> newContainerArea = new HashSet<>();
		
		// imageAspectRatio
		newTemplate.setImageAspectRatio(getImageAspectRatio());
		
		// templateImageWidth
		newTemplate.setTemplateImageWidth(getTemplateImageWidth());
		
		// templateImageHeight
		newTemplate.setTemplateImageHeight(getTemplateImageHeight());
		
		// regenerateThumbnail
		newTemplate.setRegenerateThumbnail(getRegenerateThumbnail());
		
		//wayfinder icon support
		newTemplate.setWayfinderIconSupport(getWayfinderIconSupport());
		
		//title boolean
		newTemplate.setEnableTitle(getEnableTitle());
		
		if (getContainerAreas() != null)
		{
			for (ContainerArea container : this.getContainerAreas()) 
			{
				newContainerArea.add(container.copyObject());
			}
			
			newTemplate.setContainerAreas(newContainerArea);
		}
		
		return newTemplate;
	}
}
