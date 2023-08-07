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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImageSize extends VersionedBaseEntity implements Comparable<ImageSize> {

	private String name;
	private int width;
	private int height;

	public void setName(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
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
	
	@Override
	public int compareTo(ImageSize o) {

		return name.compareToIgnoreCase(o.name);
	}
}
