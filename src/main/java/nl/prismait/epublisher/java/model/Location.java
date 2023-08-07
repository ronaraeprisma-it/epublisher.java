// ***************************************************************************
//
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
//
//		$HeadURL: https://svn.prisma-it.com/epublisher/saas/ePublisherJavaBlazeDSWeb/branches/NewsletterBranch/src/main/java/nl/prismait/epublisher/java/model/location.java $
//		$Id: location.java 4618 2018-12-31 12:49:08Z d.lopes $
//
// ***************************************************************************
package nl.prismait.epublisher.java.model;

import java.io.IOException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import nl.prismait.epublisher.java.model.interfaces.Renderer;
import nl.prismait.epublisher.java.model.interfaces.RenderingException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table(name = "location")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location extends VersionedBaseEntity implements Comparable<Location> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Location.class);

	// general properties
	private String name;
	private boolean deleted;

	public void setName(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public int compareTo(Location o) {

		return (name!=null?name: "").compareToIgnoreCase(o.name!=null?o.name: "" );
	}
}
