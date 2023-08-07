package nl.prismait.epublisher.java.model.dto;

import java.util.List;

import nl.prismait.epublisher.java.model.narrowcasting.BroadcastSearch;

public class BroadcastSearchResult 
{
	private int startrowSearch;
	private int maxResults;
	private int totalNumberOfResults;
	private List<BroadcastSearch> broadcastSearchObject;
	private List<PublicationBroadcastSearchDto> menuList;
	

	
	public List<PublicationBroadcastSearchDto> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<PublicationBroadcastSearchDto> menuList) {
		this.menuList = menuList;
	}
	
	public int getStartrowSearch() {
		return startrowSearch;
	}
	public void setStartrowSearch(int startrowSearch) {
		this.startrowSearch = startrowSearch;
	}
	public int getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	public int getTotalNumberOfResults() {
		return totalNumberOfResults;
	}
	public void setTotalNumberOfResults(int totalNumberOfResults) {
		this.totalNumberOfResults = totalNumberOfResults;
	}
	public List<BroadcastSearch> getBroadcastSearchObject() {
		return broadcastSearchObject;
	}
	public void setBroadcastSearchObject(List<BroadcastSearch> broadcastSearchObject) {
		this.broadcastSearchObject = broadcastSearchObject;
	}
	
}
