// ***************************************************************************
//
//		Copyright 2012, Prisma-IT (www.prisma-it.com)
//		All rights reserved
//
//		$HeadURL$
//		$Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class VersionedBaseEntity extends BaseEntity {

	private int entityVersion;

	@Version
	public int getEntityVersion(){
		return entityVersion;
	}

	public void setEntityVersion(int version){
		this.entityVersion = version;
	}
}
