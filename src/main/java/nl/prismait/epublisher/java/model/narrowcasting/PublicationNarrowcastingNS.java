package nl.prismait.epublisher.java.model.narrowcasting;
// ***************************************************************************
//
// Copyright 2011, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//import nl.prismait.epublisher.java.model.Edition; - since for narrowcasting it is Playlist instead of Edition
import nl.prismait.epublisher.java.model.Publication;

@Entity
public class PublicationNarrowcastingNS extends Publication
{
	private Set<ScreenGroup> availableScreenGroups;
	private List<Playlist> playlists;
	private int maxPlaylistPriority;
	private Set<Broadcast> disabledBroadcast;
	private Set<PlaylistWrapper> playlistWrapper;
	@Transient
	private Integer submittedPlaylists;
	private Integer maxPlaylists;
	private Integer maxPlaylistDuration;
	private Integer broadcastDuration;
	
	
	
	
	public Integer getMaxPlaylists() {
		return maxPlaylists;
	}

	public void setMaxPlaylists(Integer maxPlaylists) {
		this.maxPlaylists = maxPlaylists;
	}

	@Transient
	public Integer getSubmittedPlaylists() {
		return submittedPlaylists;
	}

	public void setSubmittedPlaylists(Integer submittedPlaylists) {
		this.submittedPlaylists = submittedPlaylists;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy("orderOfPlaylist desc")
	@JoinTable(
            name = "publication_playlistWrapper",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "playlistwrapper_id")
    )
	public Set<PlaylistWrapper> getPlaylistWrapper() {
		return playlistWrapper;
	}

	public void setPlaylistWrapper(Set<PlaylistWrapper> playlistWrapper) {
		this.playlistWrapper = playlistWrapper;
	}
		
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(
            name = "publication_disabledbroadcast",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "disabledbroadcast_id")
    )
	public Set<Broadcast> getDisabledBroadcast() {
		return disabledBroadcast;
	}



	public void setDisabledBroadcast(Set<Broadcast> disabledBroadcast) {
		this.disabledBroadcast = disabledBroadcast;
	}
	
	public void setAvailableScreenGroups(Set<ScreenGroup> availableScreenGroups)
	{
		this.availableScreenGroups = availableScreenGroups;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "publication_id")
	@ForeignKey(name = "FK_publication_screengroup")
	public Set<ScreenGroup> getAvailableScreenGroups()
	{
		return availableScreenGroups;
	}

	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
		
	}
	
//	public Edition retrieveNewEdition()
//	{
//		// TODO: write new playlist edition code
//		/*
//		 * EditionMagazineNS newEdition = new EditionMagazineNS();
//		 * newEdition.setName(getName() );
//		 * newEdition.setPublication(this);
//		 * 
//		 * newEdition.setArticlewrappers(new ArrayList<ArticleWrapper>());
//		 */
//		return null;
//	}

//	public Edition retrieveNewEdition(Edition previousEdition)
//	{
//		// TODO: write new playlist edition code
//		/*
//		 * EditionMagazineNS newEdition = new EditionMagazineNS();
//		 * newEdition.setName(getName() );
//		 * newEdition.setPublication(this);
//		 * newEdition.setEditionNumber(previousEdition.getEditionNumber() + 1);
//		 * 
//		 * newEdition.setArticlewrappers(new ArrayList<ArticleWrapper>());
//		 */
//		return null;
//	}

	@Override
	public Class<?> retrieveOutputChannelClass()
	{
		return OutputChannelNarrowcastingNS.class;
	}

	public void setPlaylists(List<Playlist> playlists)
	{
		this.playlists = playlists;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "publication")
	@LazyCollection(LazyCollectionOption.TRUE)
	@OrderBy("name asc")
	// @Filter(name="limitPlaylistsByDeleted", condition="deleted = 0")
	public List<Playlist> getPlaylists()
	{
		return playlists;
	}

	public int getMaxPlaylistPriority()
	{
		return maxPlaylistPriority;
	}

	public void setMaxPlaylistPriority(int maxPlaylistPriority)
	{
		this.maxPlaylistPriority = maxPlaylistPriority;
	}
	
	public Integer getMaxPlaylistDuration() {
		return maxPlaylistDuration;
	}
	
	public void setMaxPlaylistDuration(Integer maxPlaylistDuration) {
		this.maxPlaylistDuration = maxPlaylistDuration;
	}
	
	public Integer getBroadcastDuration() {
		return broadcastDuration;
	}

	public void setBroadcastDuration(Integer broadcastDuration) {
		this.broadcastDuration = broadcastDuration;
	}
}
