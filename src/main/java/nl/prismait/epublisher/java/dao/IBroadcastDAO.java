package nl.prismait.epublisher.java.dao;

import java.util.List;
import java.util.Set;

import nl.prismait.epublisher.java.model.Location;
import nl.prismait.epublisher.java.model.Route;
//import nl.prismait.epublisher.java.model.User;
import nl.prismait.epublisher.java.model.narrowcasting.Broadcast;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastFilter;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastSearch;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.TemplateNarrowcasting;

public interface IBroadcastDAO{

	public Broadcast save(Broadcast broadcast);
	public void delete(Broadcast broadcast);

	public BroadcastWrapper save(BroadcastWrapper broadcastWrapper);
	public void delete(BroadcastWrapper broadcastWrapper);

	public Broadcast getBroadcastById (Integer broadcastId);
	public BroadcastWrapper getBroadcastWrapperById (Integer broadcastWrapperId);

	public List<TemplateNarrowcasting> getAllTemplates();

	public BroadcastSearch searchBroadcasts(BroadcastFilter filter);
	public List<Object[]> searchBroadcastsSummaries(BroadcastFilter filter); //, User user);
	public Integer getBroadcastCount(BroadcastFilter filter);
	public List<Object[]> getAvailablePublicationWithPlaylist(BroadcastFilter filter,String emailAddress, List<String> publicationTypes, boolean isExternalPlaylist);
	public List<Object[]> getActiveBroadcastForPlaylist(int broadcastId,String emailAddress, List<String> publicationTypes,boolean isExternalPlaylist);
	void deleteAll(List<BroadcastWrapper> broadcastWrapper);
	public List<Route> getAllRoutes(Set<Location> allowedLocations);
	public Route saveRoute(Route route);
	public void deleteRoute(Route route);
	public Route getRouteById(Integer id);
	public void deleteRoutesOnContentblocks(Route route);
	public Integer getBroadcastChildIdFromPlaylist(Integer parentId, Integer playlistId);
	public List<Integer> getBroadcastChildrenIds(Integer parentId);
	public TemplateNarrowcasting getTemplateById(Integer id);
	void deleteRoutesOnLocations(Route route);
	List<Object[]> getActiveChildBroadcastForPlaylist(int broadcastId, String emailAddress,
			List<String> publicationTypes);
	public String getBroadcastThumbnail(Integer broadcastId);
	public void deleteBrokenBroadcasts(Broadcast broadcast);
	public List<BroadcastWrapper> getBroadcastWrapperList(Integer id);
	void deleteBroadcastWrapper(List<BroadcastWrapper> wrappers, Integer broadcastId);
	public Integer countPublishedInternalBroadcasts();
	public Integer countPublishedExternalBroadcasts();

	TemplateNarrowcasting getTemplateByName(String name);
	BroadcastWrapper getBroadcastByAdvertisementId(String string);

}