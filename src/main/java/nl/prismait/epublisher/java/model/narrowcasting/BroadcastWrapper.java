// ***************************************************************************
//
// Copyright 2013, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.model.narrowcasting;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import nl.prismait.epublisher.java.model.VersionedBaseEntity;
import nl.prismait.epublisher.java.model.nstreinen.BroadcastNSTreinen;
import nl.prismait.epublisher.java.model.wayfinder.BroadcastWayfinder;

@JsonTypeInfo(use = Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "dtype")
@JsonSubTypes({
@Type(value = BroadcastNarrowcastingNS.class, name = "BroadcastNarrowcastingNS"),
@Type(value = BroadcastWayfinder.class, name = "BroadcastWayfinder"),
@Type(value = BroadcastNSTreinen.class, name = "BroadcastNSTreinen")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="broadcastwrapper")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)

public class BroadcastWrapper extends VersionedBaseEntity implements Comparable<BroadcastWrapper>
{
	private Broadcast broadcast;
	private Integer orderOfBroadcast;
	private boolean alreadyPublished;
	private Integer playlistId;
	private String version;
	// The location property of the screen requesting a broadcast. This is a Transient property.
	private String location;
	
	// brighcove or vimeo organization permissions
	private String videoPermissions;
		
	private String screengroupBackgroundImage;
	private String screenBackgroundImage;

	private Integer minVideoResolutionWidth;
	private Integer minVideoResolutionHeight;
	
	private String iframeUrl;
	
	private List<String> rssUrls;
	
	private Integer playlistStartHour;
	private Integer playlistStartMinute;
	private Integer playlistEndHour;
	private Integer playlistEndMinute;
	
	private boolean isDeleted;
	private boolean isModified;
	private boolean isAdded;
	private Integer broadcastParentId;
	private boolean active = true;
	
	//transient property we use for Matomo tracking
	private String tenantId;
	private String advertisementId;
	
	
	
	
	
	
	public String getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = ((active == null) ? true : active);
	}

	@Column(name="broadcast_parentid")
	public Integer getBroadcastParentId() {
		return broadcastParentId;
	}

	public void setBroadcastParentId(Integer broadcastParentId) {
		this.broadcastParentId = broadcastParentId;
	}
	
	public boolean isAdded() {
		return isAdded;
	}

	public void setAdded(Boolean isAdded) {
		this.isAdded = !(isAdded == null || !isAdded);
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean isDeleted) {
		this.isDeleted = !(isDeleted == null || !isDeleted);
	}

	public boolean isModified() {
		return isModified;
	}

	public void setModified(Boolean isModified) {
		this.isModified = !(isModified == null || !isModified);
	}

	@Transient
	public String getVideoPermissions() {
		return videoPermissions;
	}

	public void setVideoPermissions(String videoPermissions) {
		this.videoPermissions = videoPermissions;
	}

	@Transient
	public String getIframeUrl() {
		return iframeUrl;
	}

	public void setIframeUrl(String iframeUrl) {
		this.iframeUrl = iframeUrl;
	}

	@Transient
	public List<String> getRssUrls() {
		return rssUrls;
	}

	public void setRssUrls(List<String> rssUrls) {
		this.rssUrls = rssUrls;
	}

	@Transient
	public String getScreengroupBackgroundImage() {
		return screengroupBackgroundImage;
	}

	public void setScreengroupBackgroundImage(String screengroupBackgroundImage) {
		this.screengroupBackgroundImage = screengroupBackgroundImage;
	}

	@Transient
	public String getScreenBackgroundImage() {
		return screenBackgroundImage;
	}

	public void setScreenBackgroundImage(String screenBackgroundImage) {
		this.screenBackgroundImage = screenBackgroundImage;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Broadcast getBroadcast()
	{
		return broadcast;
	}

	public void setBroadcast(Broadcast broadcast)
	{

		this.broadcast = broadcast;
	}

	public Integer getOrderOfBroadcast()
	{

		return orderOfBroadcast;
	}

	public void setOrderOfBroadcast(Integer orderOfBroadcast)
	{

		this.orderOfBroadcast = orderOfBroadcast;
	}

	public boolean isAlreadyPublished()
	{

		return alreadyPublished;
	}

	public void setAlreadyPublished(boolean alreadyPublished)
	{

		this.alreadyPublished = alreadyPublished;
	}

	@Transient
	public Integer getPlaylistId()
	{
		return playlistId;
	}

	public void setPlaylistId(Integer playlistId)
	{
		this.playlistId = playlistId;
	}

	@Transient
	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	@Transient
	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public boolean isActive(Date date)
	{
		return (broadcast.getDisplayStartDate() == null || broadcast.getDisplayStartDate().getTime() <= date.getTime()) && (broadcast.getDisplayEndDate() == null || date.getTime() <= broadcast.getDisplayEndDate().getTime());
	}

	@Transient
	public Integer getMinVideoResolutionWidth()
	{
		return minVideoResolutionWidth;
	}

	public void setMinVideoResolutionWidth(Integer minVideoResolutionWidth)
	{
		this.minVideoResolutionWidth = minVideoResolutionWidth;
	}

	@Transient
	public Integer getMinVideoResolutionHeight()
	{
		return minVideoResolutionHeight;
	}

	public void setMinVideoResolutionHeight(Integer minVideoResolutionHeight)
	{
		this.minVideoResolutionHeight = minVideoResolutionHeight;
	}

	@Transient
	public Integer getPlaylistStartHour() {
		return playlistStartHour;
	}

	@Transient
	public void setPlaylistStartHour(Integer playlistStartHour) {
		this.playlistStartHour = playlistStartHour;
	}

	@Transient
	public Integer getPlaylistStartMinute() {
		return playlistStartMinute;
	}

	@Transient
	public void setPlaylistStartMinute(Integer playlistStartMinute) {
		this.playlistStartMinute = playlistStartMinute;
	}

	@Transient
	public Integer getPlaylistEndHour() {
		return playlistEndHour;
	}

	@Transient
	public void setPlaylistEndHour(Integer playlistEndHour) {
		this.playlistEndHour = playlistEndHour;
	}

	@Transient
	public Integer getPlaylistEndMinute() {
		return playlistEndMinute;
	}

	@Transient
	public void setPlaylistEndMinute(Integer playlistEndMinute) {
		this.playlistEndMinute = playlistEndMinute;
	}
	
//	public String getDtype() {
//		return dtype;
//	}
//
//	public void setDtype(String dtype) {
//		this.dtype = dtype;
//	}
	
	@Transient
	public String getTenantId() {
		return tenantId;
	}
	
	@Transient
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Override
    public int compareTo(BroadcastWrapper o) {
		 return Integer.valueOf(getOrderOfBroadcast()).compareTo(Integer.valueOf(o.getOrderOfBroadcast()));
    }
	/*
	* This method will be skipped and passed onto the json subtypes copyObject functions present in: 
	* BroadcastNarrowcastingNS, BroadcastWayfinder and BroadcastNSTreinen
	*/
	public BroadcastWrapper copyObject()
	{
		BroadcastWrapper newBw= new BroadcastWrapper();
		
		return newBw;
	}
}
