
// ***************************************************************************
//     Copyright 2018, Prisma-IT (www.prisma-it.com)
//     All rights reserved
//
//     $HeadURL$
//     $Id$
// ***************************************************************************

package nl.prismait.epublisher.java.model.narrowcasting;

import java.util.Date;

import javax.persistence.Entity;

import nl.prismait.epublisher.java.model.VersionedBaseEntity;

@Entity
public class BroadcastPlay extends VersionedBaseEntity {
	private Date playTime;
	private int screenId;
	private int publicationId;
	private int playlistId;
	private int broadcastId;
	
	public Date getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}

	public int getScreenId() {
		return screenId;
	}
	
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	
	public int getPublicationId() {
		return publicationId;
	}
	
	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}
	
	public int getPlaylistId() {
		return playlistId;
	}
	
	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}
	
	public int getBroadcastId() {
		return broadcastId;
	}
	
	public void setBroadcastId(int broadcastId) {
		this.broadcastId = broadcastId;
	}
}
