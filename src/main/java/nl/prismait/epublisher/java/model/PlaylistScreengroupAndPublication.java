// ***************************************************************************
//
// Copyright 2014, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import nl.prismait.epublisher.java.model.narrowcasting.Playlist;

public class PlaylistScreengroupAndPublication
{
	private String screenGroupName;
	private String publicationName;
	private Integer publicationId;
	private Playlist playlist;
	private List<SimpleUser> publicationUsers;
	private BigInteger totalPlaylistDuration;
	private boolean isActive;
	
	
	

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public BigInteger getTotalPlaylistDuration() {
		return totalPlaylistDuration;
	}

	public void setTotalPlaylistDuration(BigInteger totalPlaylistDuration) {
		this.totalPlaylistDuration = totalPlaylistDuration;
	}


	public String getScreenGroupName()
	{
		return screenGroupName;
	}

	public void setScreenGroupName(String screenGroupName)
	{
		this.screenGroupName = screenGroupName;
	}

	public String getPublicationName()
	{
		return publicationName;
	}

	public void setPublicationName(String publicationName)
	{
		this.publicationName = publicationName;
	}

	public Integer getPublicationId()
	{
		return publicationId;
	}

	public void setPublicationId(Integer publicationId)
	{
		this.publicationId = publicationId;
	}


	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public List<SimpleUser> getPublicationUsers()
	{
		return publicationUsers;
	}

	public void setPublicationUsers(List<SimpleUser> publicationUsers)
	{
		this.publicationUsers = publicationUsers;
	}
}
