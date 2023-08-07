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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import nl.prismait.epublisher.java.model.EPublisherImage;
import nl.prismait.epublisher.java.model.Publication;
import nl.prismait.epublisher.java.model.VersionedBaseEntity;

@Entity
public class ScreenGroup extends VersionedBaseEntity {

	private String name;
	private String description;
	@JsonIgnore
	private List<Screen> screens;
	@JsonIgnore
	private List<ScreenGroup> screenGroups;
	private ScreenGroup parentScreenGroup;
	@JsonIgnore
	private Publication publication;
	private EPublisherImage backgroundImage;
	@Transient
	private Integer indent;
	private boolean screenIdDebug;
	private List<PlayTime> playFrequency;
	
	@Transient
	public Integer getIndent() {
		return indent;
	}

	public void setIndent(Integer indent) {
		this.indent = indent;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="backgroundimage_id", referencedColumnName="id")
	public EPublisherImage getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(EPublisherImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
	
	public String getDescription() {
	
		return description;
	}
	
	public void setDescription(String description) {
	
		this.description = description;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="parentScreenGroup")
	@OrderBy("name asc")
	public List<Screen> getScreens() {
	
		return screens;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	public Publication getPublication()
	{
		return publication;
	}
	
	public void setScreens(List<Screen> screens) {
	
		this.screens = screens;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="parentScreenGroup")
	@OrderBy("name")
	public List<ScreenGroup> getScreenGroups() {
	
		return screenGroups;
	}
	
	public void setPublication(Publication publication) {
		
		this.publication = publication;
	}
	
	public void setScreenGroups(List<ScreenGroup> screenGroups) {
	
		this.screenGroups = screenGroups;
	}

	public void setParentScreenGroup(ScreenGroup parentScreenGroup) {

		this.parentScreenGroup = parentScreenGroup;
	}
	
	@ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER )
	@JoinColumn(name="screengroup_id", referencedColumnName="id")
	public ScreenGroup getParentScreenGroup() {

		return parentScreenGroup;
	}

	public boolean isScreenIdDebug() {
		return screenIdDebug;
	}

	public void setScreenIdDebug(boolean screenIdDebug) {
		this.screenIdDebug = screenIdDebug;
	}
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="screengroup_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<PlayTime> getPlayFrequency() 
	{
		return playFrequency;
	}
	
	public void setPlayFrequency(List<PlayTime> playFrequency) 
	{
		this.playFrequency = playFrequency;
	}
}
