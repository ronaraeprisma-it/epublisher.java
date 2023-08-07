// ***************************************************************************
// 
//		Copyright 2013, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL: https://svn.prisma-it.com/epublisher/ePublisherJavaBlazeDSWeb/branches/NS_EPB-1265/src/main/java/nl/prismait/epublisher/java/model/narrowcasting/EPublisherVideo.java $
//		$Id: EPublisherVideo.java 6349 2020-11-06 09:59:03Z d.lopes $
// 
// ***************************************************************************
package nl.prismait.epublisher.java.model.narrowcasting;

import javax.persistence.Entity;
import javax.persistence.Transient;

import nl.prismait.epublisher.java.model.EPublisherFile;
import nl.prismait.epublisher.java.model.EPublisherImage;

@Entity
public class EPublisherPowerpoint extends ContentBlock {
	
	private EPublisherFile powerpoint;
	
	
	@Transient
	public EPublisherFile getPowerpoint() {
		return powerpoint;
	}

	public void setPowerpoint(EPublisherFile powerpoint) {
		this.powerpoint = powerpoint;
	}

	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public EPublisherPowerpoint copyObject() 
	{
		EPublisherPowerpoint newEPublisherPowerpoint = new EPublisherPowerpoint();
		
		newEPublisherPowerpoint.setContainerId(getContainerId());
		newEPublisherPowerpoint.setEnabled(isEnabled());
		newEPublisherPowerpoint.setTitle(getTitle());
		newEPublisherPowerpoint.setPowerpoint(getPowerpoint());
		
		return newEPublisherPowerpoint;
	}
}
