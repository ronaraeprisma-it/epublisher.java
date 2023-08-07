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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlaylistsPublicationsAndBroadcasts
{
	private Integer screenGroupId;
	private String screenGroupName;
	private Integer parentScreenGroupId;
	private Integer publicationId;
	private String publicationName;
	private Integer playlistId;
	private String playlistName;
	private Integer playlistPriority;
	private Integer broadcastWrapperId;
	private Integer broadcastId;
	private Integer orderOfBroadcast;
	private Integer startHour;
	private Integer startMinute;
	private Integer endHour;
	private Integer endMinute; 
	


	public Integer getStartHour() {
		return startHour;
	}

	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}

	public Integer getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(Integer startMinute) {
		this.startMinute = startMinute;
	}

	public Integer getEndHour() {
		return endHour;
	}

	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}

	public Integer getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}
	
	public Integer getScreenGroupId()
	{
		return screenGroupId;
	}

	public void setScreenGroupId(Integer screenGroupId)
	{
		this.screenGroupId = screenGroupId;
	}

	public String getScreenGroupName()
	{
		return screenGroupName;
	}

	public void setScreenGroupName(String screenGroupName)
	{
		this.screenGroupName = screenGroupName;
	}

	public Integer getParentScreenGroupId()
	{
		return parentScreenGroupId;
	}

	public void setParentScreenGroupId(Integer parentScreenGroupId)
	{
		this.parentScreenGroupId = parentScreenGroupId;
	}

	public Integer getPublicationId()
	{
		return publicationId;
	}

	public void setPublicationId(Integer publicationId)
	{
		this.publicationId = publicationId;
	}

	public String getPublicationName()
	{
		return publicationName;
	}

	public void setPublicationName(String publicationName)
	{
		this.publicationName = publicationName;
	}

	public Integer getPlaylistId()
	{
		return playlistId;
	}

	public void setPlaylistId(Integer playlistId)
	{
		this.playlistId = playlistId;
	}

	public String getPlaylistName()
	{
		return playlistName;
	}

	public void setPlaylistName(String playlistName)
	{
		this.playlistName = playlistName;
	}

	public Integer getPlaylistPriority()
	{
		return playlistPriority;
	}

	public void setPlaylistPriority(Integer playlistPriority)
	{
		this.playlistPriority = playlistPriority;
	}

	public Integer getBroadcastWrapperId()
	{
		return broadcastWrapperId;
	}

	public void setBroadcastWrapperId(Integer broadcastWrapperId)
	{
		this.broadcastWrapperId = broadcastWrapperId;
	}

	public Integer getBroadcastId()
	{
		return broadcastId;
	}

	public void setBroadcastId(Integer broadcastId)
	{
		this.broadcastId = broadcastId;
	}

	public Integer getOrderOfBroadcast()
	{
		return orderOfBroadcast;
	}

	public void setOrderOfBroadcast(Integer orderOfBroadcast)
	{
		this.orderOfBroadcast = orderOfBroadcast;
	}

	public static List<PlaylistsPublicationsAndBroadcasts> convertObjectsToPlaylistsPublicationsAndBroadcasts(List<Object[]> objects)
	{
		List<PlaylistsPublicationsAndBroadcasts> result = new ArrayList<PlaylistsPublicationsAndBroadcasts>();

		for (Object[] object : objects)
		{
			PlaylistsPublicationsAndBroadcasts converted = new PlaylistsPublicationsAndBroadcasts();

			converted.setScreenGroupId((Integer) object[0]);
			converted.setScreenGroupName((String) object[1]);
			converted.setParentScreenGroupId((Integer) object[2]);
			converted.setPublicationId((Integer) object[3]);
			converted.setPublicationName((String) object[4]);
			converted.setPlaylistName((String) object[5]);
			converted.setPlaylistPriority((Integer) object[6]);
			converted.setPlaylistId((Integer) object[7]);
			converted.setOrderOfBroadcast((Integer) object[8]);
			converted.setBroadcastWrapperId((Integer) object[9]);
			converted.setBroadcastId((Integer) object[10]);
			converted.setStartHour((Integer) object[12]);
			converted.setStartMinute((Integer) object[13]);
			converted.setEndHour((Integer) object[14]);
			converted.setEndMinute((Integer) object[15]);
			
			result.add(converted);
		}
		
		return result;
	}
}
