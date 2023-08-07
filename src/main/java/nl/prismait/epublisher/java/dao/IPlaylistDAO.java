package nl.prismait.epublisher.java.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.List;

import nl.prismait.epublisher.java.model.PlaylistScreengroupAndPublication;
import nl.prismait.epublisher.java.model.PlaylistsPublicationsAndBroadcasts;
import nl.prismait.epublisher.java.model.narrowcasting.ActivePlaylistWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastCacheWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastPlay;
import nl.prismait.epublisher.java.model.narrowcasting.Playlist;
import nl.prismait.epublisher.java.model.narrowcasting.PublicationNarrowcastingNS;

public interface IPlaylistDAO
{

	public Playlist getPlaylistById(Integer playlistId);

	public List<Playlist> getCurrentPlaylistsForPublication(Integer publicationId);

	public List<Playlist> getPublishedPlaylistsForPublication(Integer publicationId);

	public List<Playlist> getAllVersionsOfPlaylist(Playlist playlist);

	public List<Playlist> getPreviousPlaylistsByName(Playlist playlist);

	public Playlist getPlaylistById(Integer playlistId, Boolean evict);

	public void removeAllPlaylistsOfPublication(PublicationNarrowcastingNS publication);

	public List<PlaylistsPublicationsAndBroadcasts> getActivePlaylistsAndBroadcastsForScreenGroup(Integer screenGroupId, Integer publicationId);

	public List<PlaylistScreengroupAndPublication> getActivePlaylistsAndScreengroupsForPublication(Integer publicationId);

	public Integer getPublicationByPlaylistId(Integer playlistId);

	public void logNewBroadcast(BroadcastPlay logBroadcast);

	public List<Playlist> getPreviousPlaylistsByNameAndId(Playlist currentPlaylist);

	BroadcastCacheWrapper getActivePlaylistBroadcastWithAssets(String screenId, Integer pubId);

	List<Playlist> getActivePlaylistsIdsForPublication(Integer publicationId);

	public void updateplaylistWrapper(Integer oldPlaylist, Integer newPlaylist);

	List<PlaylistScreengroupAndPublication> getActivePlaylistsForPublication(Integer publicationId);

	List<Playlist> getAncestorPlaylistsIdsForPublication(Integer publicationId);

	public List<Integer> getScreenGroupByPublicationId(Integer publicationId);
	Playlist save(Playlist playlist, String email);

	Playlist getPlaylistByParentId(Integer playlistParentId);

	/**
	 * Retrieve all published playlists from a certain playlist
	 * 
	 * @see nl.prismait.epublisher.java.dao.IPlaylistDAO#getPublishedPlaylistsForPublication(java.lang.Integer)
	 */
	List<Playlist> getPublishedPlaylistsForPlaylist(Integer id, Integer parentId);

	public Boolean publishTreinPlaylist(String playlistJSON) throws MalformedURLException, IOException;

	public Boolean deleteTreinPlaylist(String playlistId) throws IOException;

	public Playlist sharePlaylist(Integer playlistId, String email);

	public Boolean validatePlaylistPriorityForUser(int priority, String email);

	public List<Playlist> getPlaylistHistory(Integer playlistId, Integer limit);

	public Long getPlaylistHistoryCount(Integer playlistId);
	
	public Boolean getPlaylistByName(String playlistName);

	public Boolean unpublishTreinPlaylist(String playlistId) throws IOException;

	public BigInteger countBroadcastsInPlaylist(Integer playlistId);

	/**
	 * Retrieve all active playlists of screengroups, for a given publication.
	 */
   public List<ActivePlaylistWrapper> getActivePlaylistIds(String screenId, Integer pubId);
   
   public List<Playlist> getSharedPlaylistInfo();

   public List<Playlist> getAllPlaylistsByParentId(Integer parentId);
}