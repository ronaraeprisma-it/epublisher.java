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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Profile extends VersionedBaseEntity {

	private String name;
	private boolean profileActive;
	private Publication includeLatestEditionFromPublication;
	private SearchObject searchObject;

	public void setName(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setProfileActive(boolean profileActive) {

		this.profileActive = profileActive;
	}

	public boolean isProfileActive() {
		return profileActive;
	}
	
	public void setIncludeLatestEditionFromPublication(Publication includeLatestEditionFromPublication) {
		
		this.includeLatestEditionFromPublication = includeLatestEditionFromPublication;
	}	

	@ManyToOne
	public Publication getIncludeLatestEditionFromPublication() {

		return includeLatestEditionFromPublication;
	}

	public void setSearchObject(SearchObject searchObject) {

		this.searchObject = searchObject;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	public SearchObject getSearchObject() {

		return searchObject;
	}
}
