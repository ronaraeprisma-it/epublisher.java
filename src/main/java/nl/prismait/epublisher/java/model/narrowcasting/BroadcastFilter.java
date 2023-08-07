package nl.prismait.epublisher.java.model.narrowcasting;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BroadcastFilter {
	private String broadcastName;
	private String createdBy;
	private String sortField;
	private boolean ascendingSortOrder = true;
	private String templateName;
	private Integer startPage;
	private Integer numberOfResults;
	private Integer userId;
	private String appName;
	private boolean isWayfinder;
	private Integer userWayfinder;
	private boolean isExternalPlaylist;
	
	public boolean isExternalPlaylist() {
		return isExternalPlaylist;
	}

	public void setExternalPlaylist(boolean isExternalPlaylist) {
		this.isExternalPlaylist = isExternalPlaylist;
	}
	
	
	public Integer getUserWayfinder() {
		return userWayfinder;
	}

	public void setUserWayfinder(Integer userWayfinder) {
		this.userWayfinder = userWayfinder;
	}

	public boolean isWayfinder() {
		return isWayfinder;
	}

	public void setWayfinder(boolean isWayfinder) {
		this.isWayfinder = isWayfinder;
	}

	public String getBroadcastName() {
		return broadcastName;
	}

	public void setBroadcastName(String broadcastName) {
		this.broadcastName = broadcastName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public boolean isAscendingSortOrder() {
		return ascendingSortOrder;
	}

	public void setAscendingSortOrder(boolean ascendingSortOrder) {
		this.ascendingSortOrder = ascendingSortOrder;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Integer getStartPage() {
		return startPage;
	}

	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}

	public Integer getNumberOfResults() {
		return numberOfResults;
	}

	public void setNumberOfResults(Integer numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}


	public BroadcastFilter copyObject() {
		BroadcastFilter result = new BroadcastFilter();
		result.setAscendingSortOrder(isAscendingSortOrder());
		result.setBroadcastName(getBroadcastName());
		result.setCreatedBy(getCreatedBy());
		result.setNumberOfResults(getNumberOfResults());
		result.setSortField(getSortField());
		result.setStartPage(getStartPage());
		result.setTemplateName(getTemplateName());
		result.setUserId(getUserId());
		result.setAppName(getAppName());
		result.setWayfinder(isWayfinder);
		result.setExternalPlaylist(isExternalPlaylist);
		
		return result;
	}

}
