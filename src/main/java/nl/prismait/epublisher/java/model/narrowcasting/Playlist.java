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

import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import nl.prismait.epublisher.java.model.Publication;
import nl.prismait.epublisher.java.model.VersionedBaseEntity;
import nl.prismait.epublisher.java.model.nstreinen.PlaylistNSTreinen;
import nl.prismait.epublisher.java.model.wayfinder.PlaylistWayfinder;

@JsonTypeInfo(use = Id.NAME,
		property = "dtype")
@JsonSubTypes({
@Type(value = PlaylistNarrowcastingNS.class, name = "PlaylistNarrowcastingNS"),
@Type(value = PlaylistWayfinder.class, name = "PlaylistWayfinder"),
@Type(value = PlaylistNSTreinen.class, name = "PlaylistNSTreinen")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "playlist")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public class Playlist extends VersionedBaseEntity {

	public static final String PLAYLIST_TO_PREVIEW_KEY_NAME = "playlistToPreview";

	private Publication publication;

	private String name;
	private String description;
	
	private SortedSet<BroadcastWrapper> broadcastwrappers = new TreeSet<>();
	private List<PlayTime> playFrequency;

	private int priority;
	private boolean deleted;
	private Date publicationDate;
	private Date lastUpdated;
	private Integer ancestorPlaylistId;
	
	private boolean settingsDifferentThanPublished = false;
	private boolean orderDifferentThanPublished = false;
	private boolean broadcastsDifferentThanPublished = false;
	
	private String state;
	private String lastModifiedBy;
	private String lastSubmittedBy;
	private Integer parentId;

//	private String dtype;
	private Train trainDetails;
	private Geolocation location;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private List<ScreenGroup> screengroups;
	@Transient
	private List<ScreenGroup> writeScreengroups;
	
	private boolean shared;
	private String createdBy;
	private Date createdDate;
	@Transient
	private String lastAction;
	private String uuid;
	
	
	@Transient
	public String getLastAction() {
		return lastAction;
	}

	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(Boolean shared) {
		this.shared = ((shared == null) ? false : shared);
	}

	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@Transient
	public List<ScreenGroup> getWriteScreengroups() {
		return writeScreengroups;
	}

	public void setWriteScreengroups(List<ScreenGroup> writeScreengroups) {
		this.writeScreengroups = writeScreengroups;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
            name = "playlist_screengroup",
            joinColumns = @JoinColumn(name = "playlist_id "),
            inverseJoinColumns = @JoinColumn(name = "screengroup_id")
    )
	public List<ScreenGroup> getScreengroups() {
		return screengroups;
	}

	public void setScreengroups(List<ScreenGroup> screengroups) {
		this.screengroups = screengroups;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
            name = "playlist_geolocation",
            joinColumns = @JoinColumn(name = "playlist_id ", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "geolocation_id")
    )
	public Geolocation getLocation() {
		return location;
	}

	public void setLocation(Geolocation location) {
		this.location = location;
	}

	public void setTrainDetails(Train trainDetails) {
		this.trainDetails = trainDetails;
	}

	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinTable(
			name = "playlist_train",
			joinColumns = @JoinColumn(name = "playlist_id "),
			inverseJoinColumns = @JoinColumn(name = "train_id")
	)
	public Train getTrainDetails() {
		return trainDetails;
	}
	
	public boolean isBroadcastsDifferentThanPublished() {
		return broadcastsDifferentThanPublished;
	}

	public void setBroadcastsDifferentThanPublished(Boolean broadcastsDifferentThanPublished) {
		this.broadcastsDifferentThanPublished = !(broadcastsDifferentThanPublished == null || !broadcastsDifferentThanPublished);
	}

	public boolean isSettingsDifferentThanPublished() {
		return settingsDifferentThanPublished;
	}

	public void setSettingsDifferentThanPublished(boolean settingsDifferentThanPublished) {
		this.settingsDifferentThanPublished = settingsDifferentThanPublished;
	}

	public boolean isOrderDifferentThanPublished() {
		return orderDifferentThanPublished;
	}

	public void setOrderDifferentThanPublished(Boolean orderDifferentThanPublished) {
		this.orderDifferentThanPublished = !(orderDifferentThanPublished == null || !orderDifferentThanPublished);
	}
	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		// fill missing states
		if (state == null) {
			if(!this.isDeleted()){
				if(!this.isSettingsDifferentThanPublished() && !this.isOrderDifferentThanPublished()
						&& !this.isBroadcastsDifferentThanPublished()){
					this.state = "initial";
				} else if(this.isSettingsDifferentThanPublished() || this.isOrderDifferentThanPublished() || 
						this.isBroadcastsDifferentThanPublished()) {
					this.state = "modified";
				}
			}
			
		} else {
			this.state = state;
		}
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getLastSubmittedBy() {
		return lastSubmittedBy;
	}

	public void setLastSubmittedBy(String lastSubmittedBy) {
		this.lastSubmittedBy = lastSubmittedBy;
	}

	public Integer getAncestorPlaylistId() {
		return ancestorPlaylistId;
	}

	public void setAncestorPlaylistId(Integer ancestorPlaylistId) {
		this.ancestorPlaylistId = ancestorPlaylistId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("orderOfBroadcast asc")
	public SortedSet<BroadcastWrapper> getBroadcastwrappers() 
	{
		return broadcastwrappers;
	}
	
	public void setBroadcastwrappers(SortedSet<BroadcastWrapper> broadcastwrappers) 
	{
		this.broadcastwrappers = broadcastwrappers;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="playlist_id", insertable = false, updatable = false)
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<PlayTime> getPlayFrequency() 
	{
		return playFrequency;
	}
	
	public void setPlayFrequency(List<PlayTime> playFrequency) 
	{
		this.playFrequency = playFrequency;
	}
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="publication_id", referencedColumnName="id"																																				)
	public Publication getPublication()
	{
		return publication;
	}
	
	public void setPublication(Publication publication) 
	{
		this.publication = publication;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public int getPriority() 
	{
		return priority;
	}
	
	public void setPriority(int priority) 
	{
		this.priority = priority;
	}
	
	public void setDeleted(boolean deleted) 
	{
		this.deleted = deleted;
	}

	public boolean isDeleted()
	{
		return deleted;
	}
	
	public Date getPublicationDate() 
	{
		return publicationDate;
	}
	
	public void setPublicationDate(Date publicationDate) 
	{
		this.publicationDate = publicationDate;
	}

	public Date getLastUpdated() 
	{
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) 
	{
		this.lastUpdated = lastUpdated;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
//	public String getDtype() {
//		return dtype;
//	}
//
//	public void setDtype(String dtype) {
//		this.dtype = dtype;
//	}


	public Playlist publish() 
	{
		Playlist newPlaylist = this.copyObject();

		setPublicationDate(new Date());
		
		for (BroadcastWrapper wrapper : newPlaylist.getBroadcastwrappers()) 
		{
			wrapper.setAlreadyPublished(true);
		}
		
		return newPlaylist;
	}

	/**
	 * This method will be skipped and passed onto the json subtypes copyObject functions present in: 
	 * PlaylistNarrowcastingNS, PlaylistWayfinders and PlaylistNStreinen
	 * @ param e-mail
	 */
	public Playlist copyObject() 
	{
		Playlist newPlaylist = new Playlist();
		
		return newPlaylist;
	}
}
