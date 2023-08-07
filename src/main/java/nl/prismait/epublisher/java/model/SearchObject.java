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

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class SearchObject extends VersionedBaseEntity{

	private String searchString;
	private Date fromDate;
	private Date toDate;
	private int numberOfDaysFromPresent;
	private Set<String> sources;
	private Set<Integer> authors;
	private String articleId;
	
	
	@Transient
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="profileauthors")
	@Column(name = "epublisheruser_id")
	public Set<Integer> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Integer> authors) {
		this.authors = authors;
	}

	public void setFromDate(Date fromDate) {

		this.fromDate = fromDate;
	}

	public Date getFromDate() {

		return fromDate;
	}

	public void setToDate(Date toDate) {

		this.toDate = toDate;
	}

	public Date getToDate() {

		return toDate;
	}

	public void setSources(Set<String> sources) {

		this.sources = sources;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="profilesources")
	public Set<String> getSources() {

		return sources;
	}

	public void setSearchString(String searchString) {

		this.searchString = searchString;
	}

	public String getSearchString() {

		return searchString;
	}

	public void setNumberOfDaysFromPresent(int numberOfDaysFromPresent) {

		this.numberOfDaysFromPresent = numberOfDaysFromPresent;
	}

	public int getNumberOfDaysFromPresent() {

		return numberOfDaysFromPresent;
	}


}
