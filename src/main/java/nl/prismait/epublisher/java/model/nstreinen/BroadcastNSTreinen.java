// ***************************************************************************
//
// Copyright 2013, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.model.nstreinen;

import javax.persistence.Entity;
import javax.persistence.Transient;

import nl.prismait.epublisher.java.model.narrowcasting.BroadcastWrapper;

@Entity
public class BroadcastNSTreinen extends BroadcastWrapper
{
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
		
	}
	
	@Override
	public BroadcastWrapper copyObject()
	{
		BroadcastNSTreinen broadcastWrapperClone = new BroadcastNSTreinen();
		broadcastWrapperClone.setBroadcast(getBroadcast());
		broadcastWrapperClone.setOrderOfBroadcast(getOrderOfBroadcast());
		broadcastWrapperClone.setBroadcastParentId(getBroadcastParentId());
		broadcastWrapperClone.setPlaylistId(getPlaylistId());
		broadcastWrapperClone.setLocation(getLocation());
		broadcastWrapperClone.setMinVideoResolutionHeight(getMinVideoResolutionHeight());
		broadcastWrapperClone.setMinVideoResolutionWidth(getMinVideoResolutionWidth());
		broadcastWrapperClone.setPlaylistEndHour(getPlaylistEndHour());
		broadcastWrapperClone.setPlaylistEndMinute(getPlaylistEndMinute());
		broadcastWrapperClone.setPlaylistStartHour(getPlaylistStartHour());
		broadcastWrapperClone.setPlaylistStartMinute(getPlaylistStartMinute());
		broadcastWrapperClone.setRssUrls(getRssUrls());
		broadcastWrapperClone.setScreenBackgroundImage(getScreenBackgroundImage());
		broadcastWrapperClone.setVideoPermissions(getVideoPermissions());
		broadcastWrapperClone.setActive(isActive());
		
		return broadcastWrapperClone;
	}
}
