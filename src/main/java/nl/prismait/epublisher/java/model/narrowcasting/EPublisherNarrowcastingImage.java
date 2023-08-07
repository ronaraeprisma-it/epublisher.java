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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import nl.prismait.epublisher.java.model.EPublisherImage;

@Entity
public class EPublisherNarrowcastingImage extends ContentBlock {
	
	private EPublisherImage image;

	

	public void setImage(EPublisherImage image) {

		this.image = image;
	}
	
	@OneToOne
	public EPublisherImage getImage() {

		return image;
	}
	
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public EPublisherNarrowcastingImage copyObject() 
	{
		EPublisherNarrowcastingImage newEPublisherNarrowcastingImage = new EPublisherNarrowcastingImage();
		
		newEPublisherNarrowcastingImage.setContainerId(getContainerId());
		newEPublisherNarrowcastingImage.setEnabled(isEnabled());
		newEPublisherNarrowcastingImage.setTitle(getTitle());
		newEPublisherNarrowcastingImage.setImage(getImage());
		
		if(getFileuuid() != null && !getFileuuid().isEmpty() && getFileuuid().length() > 0) {
			newEPublisherNarrowcastingImage.setFileuuid(getFileuuid());
		}
		
		if (getVariant() != null)
		{
			newEPublisherNarrowcastingImage.setVariant(getVariant());
		}
		
		return newEPublisherNarrowcastingImage;
	}
}
