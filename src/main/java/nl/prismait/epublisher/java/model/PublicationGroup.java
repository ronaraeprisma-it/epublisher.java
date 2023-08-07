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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "publication_group")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PublicationGroup extends VersionedBaseEntity
{
	private String theName;
	private Set<EPublisherImage> theImages = new HashSet<EPublisherImage>();
	private Set<Publication> thePublications = new HashSet<Publication>();
	private Integer apiId;
	private String theType;
	private boolean mandatory;
	
	
	

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinTable(name = "publication_group_epublishermedia",
	joinColumns = {@JoinColumn(name = "publication_group_id")},
	inverseJoinColumns = {@JoinColumn(name = "images_id")})
	public Set<EPublisherImage> getImages()
	{
		return theImages;
	}

	public void setImages(Set<EPublisherImage> images)
	{
		theImages = images;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "publication_group_publication",
			joinColumns = {@JoinColumn(name = "publication_group_id")},
			inverseJoinColumns = {@JoinColumn(name = "publications_id")})
	public Set<Publication> getPublications()
	{
		return thePublications;
	}

	public void setPublications(Set<Publication> publications)
	{
		thePublications = publications;
	}

	@Column(name="name", nullable=false)
	public String getName() {
		return theName;
	}

	public void setName(String name) {
		theName = name;
	}
	
	public void setApiId(Integer apiId)
	{
		this.apiId = apiId;
	}

	public Integer getApiId()
	{
		return apiId;
	}

	public String getType()
	{
		return theType;
	}

	public void setType(String type)
	{
		theType = type;
	}
}
