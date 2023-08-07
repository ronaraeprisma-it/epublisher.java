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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.SortNatural;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import nl.prismait.epublisher.java.model.EPublisherImage;
import nl.prismait.epublisher.java.model.VersionedBaseEntity;

@Entity
public class Screen extends VersionedBaseEntity implements Comparable<Screen> {

	private String name;
	private EPublisherImage backgroundImage;
	private String description;
	private ScreenGroup parentScreenGroup;
	private String displayId;
	private int resolutionWidth;
	private int resolutionHeight;
	private Boolean touchEnabled;
	private Date dateTimeLastRequest;
	private String location;
	private String locationCode;
	private String displayName;
	private Integer minVideoResolutionWidth;
	private Integer minVideoResolutionHeight;
	private List<ExternalRSSLink> externalRSSLink;
	private String iframeUrl;
	private Boolean offlineEnabled;
	private Boolean screenIdDebug;
	private List<PlayTime> playFrequency;
	private Geolocation geolocation;
	
	
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
            name = "screen_geolocation",
            joinColumns = @JoinColumn(name = "screen_id "),
            inverseJoinColumns = @JoinColumn(name = "geolocation_id")
    )
	public Geolocation getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}

	public String getIframeUrl() {
		return iframeUrl;
	}

	public void setIframeUrl(String iframeUrl) {
		this.iframeUrl = iframeUrl;
	}

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@SortNatural
	@JoinTable(
            name = "screen_externalrsslink",
            joinColumns = @JoinColumn(name = "screen_id"),
            inverseJoinColumns = @JoinColumn(name = "externalrsslink_id")
    )
	public List<ExternalRSSLink> getExternalRSSLink() {
		return externalRSSLink;
	}

	public void setExternalRSSLink(List<ExternalRSSLink> externalRSSLink) {
		this.externalRSSLink = externalRSSLink;
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

	public void setParentScreenGroup(ScreenGroup parentScreenGroup) {

		this.parentScreenGroup = parentScreenGroup;
	}

	// 	@ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER  )
	@ManyToOne(fetch=FetchType.EAGER  )
	@JoinColumn(name="screengroup_id", referencedColumnName="id")
	public ScreenGroup getParentScreenGroup() {

		return parentScreenGroup;
	}
	
	public String getDisplayId() {
	
		return displayId;
	}
	
	public void setDisplayId(String displayId) {
	
		this.displayId = displayId;
	}
	
	public int getResolutionWidth() {
	
		return resolutionWidth;
	}
	
	public void setResolutionWidth(int resolutionWidth) {
	
		this.resolutionWidth = resolutionWidth;
	}
	
	public int getResolutionHeight() {
	
		return resolutionHeight;
	}
	
	public void setResolutionHeight(int resolutionHeight) {
	
		this.resolutionHeight = resolutionHeight;
	}
	
	public Boolean getTouchEnabled() {
	
		return touchEnabled;
	}
	
	public void setTouchEnabled(Boolean touchEnabled) {
	
		this.touchEnabled = touchEnabled;
	}

	@Override
	public int compareTo(Screen o) {

		return this.getName().compareToIgnoreCase(o.getName());
	}

	public void setDateTimeLastRequest(Date dateTimeLastRequest)
	{
		this.dateTimeLastRequest = dateTimeLastRequest;
	}

	public Date getDateTimeLastRequest()
	{
		return dateTimeLastRequest;
	}


	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getLocationCode()
	{
		return locationCode;
	}

	public void setLocationCode(String locationCode)
	{
		this.locationCode = locationCode;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public Integer getMinVideoResolutionWidth()
	{
		return minVideoResolutionWidth;
	}

	public void setMinVideoResolutionWidth(Integer minVideoResolutionWidth)
	{
		this.minVideoResolutionWidth = minVideoResolutionWidth;
	}

	public Integer getMinVideoResolutionHeight()
	{
		return minVideoResolutionHeight;
	}

	public void setMinVideoResolutionHeight(Integer minVideoResolutionHeight)
	{
		this.minVideoResolutionHeight = minVideoResolutionHeight;
	}

	public Boolean getOfflineEnabled() {
		return offlineEnabled;
	}

	public void setOfflineEnabled(Boolean offlineEnabled) {
		this.offlineEnabled = offlineEnabled;
	}

	public Boolean getScreenIdDebug() {
		if (screenIdDebug == null) {
			return false;
		}
		
		return screenIdDebug;
	}

	public void setScreenIdDebug(Boolean screenIdDebug) {
		this.screenIdDebug = screenIdDebug;
	}
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="screen_id")
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
