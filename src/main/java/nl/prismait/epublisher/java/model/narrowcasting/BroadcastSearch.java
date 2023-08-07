// ***************************************************************************
// 
//		Copyright 2012, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL$
//		$Id$
// 
// ***************************************************************************
package nl.prismait.epublisher.java.model.narrowcasting;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import nl.prismait.epublisher.java.model.dto.PublicationBroadcastSearchDto;


public class BroadcastSearch {
	
	private int startrowSearch;
	private int maxResults;
	private int totalNumberOfResults;
	private List<Broadcast> foundBroadcasts;
	private Date createdDate;
	private int id ;
	private String name;
	private String template;
	private boolean active;
	private List<PublicationBroadcastSearchDto> publications;
	private String app;
	private boolean wayfinder;
	private int numberOfActiveChildBroadcast;
	private String thumbnail;
	
	
	
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getNumberOfActiveChildBroadcast() {
		return numberOfActiveChildBroadcast;
	}

	public void setNumberOfActiveChildBroadcast(int numberOfActiveChildBroadcast) {
		this.numberOfActiveChildBroadcast = numberOfActiveChildBroadcast;
	}
	public boolean isWayfinder() {
		return wayfinder;
	}

	public void setWayfinder(boolean wayfinder) {
		this.wayfinder = wayfinder;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<PublicationBroadcastSearchDto> getPublications() {
		return publications;
	}

	public void setPublications(List<PublicationBroadcastSearchDto> publications) {
		this.publications = publications;
	}

	@JsonIgnore
	public int getStartrowSearch() {
	
		return startrowSearch;
	}
	
	public void setStartrowSearch(int startrowSearch) {
	
		this.startrowSearch = startrowSearch;
	}
	
	@JsonIgnore
	public int getMaxResults() {
	
		return maxResults;
	}
	
	public void setMaxResults(int maxResults) {
	
		this.maxResults = maxResults;
	}
	
	@JsonIgnore
	public int getTotalNumberOfResults() {
	
		return totalNumberOfResults;
	}
	
	public void setTotalNumberOfResults(int totalNumberOfResults) {
	
		this.totalNumberOfResults = totalNumberOfResults;
	}

	public void setFoundBroadcasts(List<Broadcast> foundBroadcasts) {

		this.foundBroadcasts = foundBroadcasts;
	}

	public List<Broadcast> getFoundBroadcasts() {

		return foundBroadcasts;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}
	
}
