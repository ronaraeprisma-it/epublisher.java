package nl.prismait.epublisher.java.model.narrowcasting;

import java.util.Date;

import nl.prismait.epublisher.java.model.EPublisherMedia;

public class Assets {
	private Integer broadcastId;
	private String name;
	private String assetId;
	private String link;
	private String type;
	private String lastChangedDate;
	
	
	
	public String getLastChangedDate() {
		return lastChangedDate;
	}
	public void setLastChangedDate(String lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}
	public Integer getBroadcastId() {
		return broadcastId;
	}
	public void setBroadcastId(Integer broadcastId) {
		this.broadcastId = broadcastId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	
	
	
	
	
}
