package nl.prismait.epublisher.java.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SchedulePublish extends VersionedBaseEntity{
	private Edition edition;
	private Date scheduledDate;
	private Integer scheduledHour;
	private Integer scheduledMinute;
	private String status;
	private boolean isCancelled;
	private String createdBy;
	private Date updatedTimestamp;
	
	@ManyToOne
	@JoinColumn(name="edition_id", referencedColumnName="id")
	public Edition getEdition() {
		return edition;
	}
	public void setEdition(Edition edition) {
		this.edition = edition;
	}
	public Date getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public Integer getScheduledHour() {
		return scheduledHour;
	}
	public void setScheduledHour(Integer scheduledHour) {
		this.scheduledHour = scheduledHour;
	}
	public Integer getScheduledMinute() {
		return scheduledMinute;
	}
	public void setScheduledMinute(Integer scheduledMinute) {
		this.scheduledMinute = scheduledMinute;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isCancelled() {
		return isCancelled;
	}
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}
	
}
