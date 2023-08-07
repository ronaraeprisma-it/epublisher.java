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

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import nl.prismait.epublisher.java.model.EPublisherImage;
import nl.prismait.epublisher.java.model.VersionedBaseEntity;

@Entity
public class Broadcast extends VersionedBaseEntity {

	public static final String BROADCAST_TO_PREVIEW_KEY_NAME = "broadcastToPreview";
	
	private String name;
	private String description;
	private TemplateNarrowcasting template;
	private int duration;
	private Set<ContentBlock> contentBlocks;
	private Date dateCreated;
	private Date displayStartDate;
	private Date displayEndDate;
	private Integer createdBy; // user Id
	private String createdByName;
	//this transient property is introduced to indicate whether the broadcast is active 
	private boolean isActive;
	private Date lastChangedDate;
	private EPublisherImage thumbnail;
	private TemplateApp app;
	private boolean wayfinder;
	private Integer parentId;
	private String title;
	private boolean isDeleted;
	private String uuid;
	

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public boolean isWayfinder() {
		return wayfinder;
	}

	public void setWayfinder(boolean wayfinder) {
		this.wayfinder = wayfinder;
	}

	public Date getLastChangedDate() {
		return lastChangedDate;
	}

	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}
	
	
	@Transient
	public boolean isActive() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date currentDate = cal.getTime(); 
		
		  //if displayStartDate is before current timestamp and displayEndDate is after current timestamp
		if((this.displayStartDate == null || ( this.displayStartDate.compareTo(currentDate))<=0 ) 
		&& (this.displayEndDate ==null || (0 <=  (this.displayEndDate.compareTo(currentDate)))))
			isActive=true;
		else
			isActive = false;
		
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
	
	@OneToOne
	public TemplateNarrowcasting getTemplate() {
	
		return template;
	}
	
	public void setTemplate(TemplateNarrowcasting template) {
	
		this.template = template;
	}
	
	public int getDuration() {
	
		return duration;
	}
	
	public void setDuration(int duration) {
	
		this.duration = duration;
	}
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@OrderBy("containerId")
	public Set<ContentBlock> getContentBlocks() {
	
		return contentBlocks;
	}
	
	public void setContentBlocks(Set<ContentBlock> contentBlocks) {
	
		this.contentBlocks = contentBlocks;
	}

	public void setDateCreated(Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}

	public Date getDateCreated()
	{
		return dateCreated;
	}

	public Date getDisplayStartDate()
	{
		return displayStartDate;
	}

	public void setDisplayStartDate(Date displayStartDate)
	{
		this.displayStartDate = displayStartDate;
	}

	public Date getDisplayEndDate()
	{
		return displayEndDate;
	}

	public void setDisplayEndDate(Date displayEndDate)
	{
		this.displayEndDate = displayEndDate;
	}

	public Integer getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	@Formula("COALESCE( (SELECT usr.lastname || ', ' || usr.firstname || ' ' || usr.middlename FROM epublisheruser usr where usr.id = createdBy), "
			+ "(SELECT usr.lastname ||  ', ' || usr.firstname FROM epublisheruser usr where usr.id = createdBy))")
	public String getCreatedByName()
	{
		return createdByName;
	}

	public void setCreatedByName(String createdByName)
	{
		this.createdByName = createdByName;
	}
	
	@OneToOne
	public EPublisherImage getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(EPublisherImage thumbnail) {
		this.thumbnail = thumbnail;
	}

	@OneToOne
	public TemplateApp getApp() {
		return app;
	}

	public void setApp(TemplateApp app) {
		this.app = app;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Broadcast copyObject() 
	{
		Broadcast newBroadcast = new Broadcast();
		
		newBroadcast.setActive(isActive);
		
		// Content blocks
		Set<ContentBlock> newContentBlock = new HashSet<>();
		
		if (this.getContentBlocks() != null)
		{
			for (ContentBlock content : this.getContentBlocks()) 
			{
				newContentBlock.add(content.copyObject());
			}
			
			newBroadcast.setContentBlocks(newContentBlock);
		}

		
		newBroadcast.setCreatedBy(getCreatedBy());
		newBroadcast.setCreatedByName(getCreatedByName());
		newBroadcast.setDateCreated(getDateCreated());
		newBroadcast.setDescription(getDescription());
		newBroadcast.setDisplayEndDate(getDisplayEndDate());
		newBroadcast.setDisplayStartDate(getDisplayStartDate());
		newBroadcast.setDuration(getDuration());
		newBroadcast.setName(getName());
		
		// template
		if (this.getTemplate() != null)
		{
			newBroadcast.setTemplate(getTemplate());
		}

		// lastChangedDate
		newBroadcast.setLastChangedDate(getLastChangedDate());
		
		// thumbnail
		if (getThumbnail() != null) {
			newBroadcast.setThumbnail(getThumbnail());
		}
		
		// app
		if (getApp() != null) {
			newBroadcast.setApp(getApp());
		}
		
		// wayfinder
		newBroadcast.setWayfinder(isWayfinder());
		
		//title field used by wayfinders (atm)
		newBroadcast.setTitle(getTitle());
		
		//when parentId is null, we use the broadcast ID
		if(getParentId() == null) {
			newBroadcast.setParentId(getId());
		}else {
			newBroadcast.setParentId(getParentId());
		}
		
		if(getUuid() != null && !getUuid().isEmpty() && getUuid().length() > 0) {
			newBroadcast.setUuid(getUuid());
		}
		
		return newBroadcast;
	}
}
