package nl.prismait.epublisher.java.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import nl.prismait.epublisher.java.dao.sql.Sql;
import nl.prismait.epublisher.java.dao.util.SqlUtils;
import nl.prismait.epublisher.java.model.PlaylistScreengroupAndPublication;
import nl.prismait.epublisher.java.model.PlaylistsPublicationsAndBroadcasts;
import nl.prismait.epublisher.java.model.Publication;
import nl.prismait.epublisher.java.model.SimpleUser;
import nl.prismait.epublisher.java.model.narrowcasting.ActivePlaylistWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastCacheWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastPlay;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.Playlist;
import nl.prismait.epublisher.java.model.narrowcasting.PublicationNarrowcastingNS;
import nl.prismait.epublisher.java.model.narrowcasting.ScreenGroup;
import nl.prismait.epublisher.java.util.EpublisherConstants;

@Component("playlistDAO")
public class PlaylistDAOImpl implements IPlaylistDAO
{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private IPublicationDAO publicationDAO;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional
	public Playlist save(Playlist playlist , String email)
	{
		// new playlist
		if (playlist.getId() == null) {
			playlist.setCreatedBy(email);
			playlist.setCreatedDate(new Date());
		}
				
		playlist.setLastUpdated(new Date());
		playlist.setLastModifiedBy(email);
		hibernateTemplate.clear();
		// Save first, before retrieving other objects from hibernate to prevent hibernate errors.
		hibernateTemplate.saveOrUpdate(playlist);
		hibernateTemplate.flush();

		// Determine if the current settings are different that the settings of the published playlist and set its value
		playlist.setSettingsDifferentThanPublished(areSettingsDifferentThanPublishedPlaylist(playlist));
		playlist.setOrderDifferentThanPublished(isOrderDifferentThanPublishedPlaylist(playlist));
		playlist.setBroadcastsDifferentThanPublished(areBroadcastsDifferentThanPublishedPlaylist(playlist));
		
		// Playlist update state
		if ((playlist.getState() == null || playlist.getState().equals("modified")) && playlist.getLastSubmittedBy() != null) {
			playlist.setState("submitted");
		} 
		else if ((playlist.getState() == null 
				|| playlist.getState().equals("initial") 
				|| playlist.getState().equals("submitted")) 
				&& (playlist.isBroadcastsDifferentThanPublished() 
				|| playlist.isOrderDifferentThanPublished() 
				|| playlist.isSettingsDifferentThanPublished()))
		{
			playlist.setState("modified");
			playlist.setLastSubmittedBy(null);	
		}
		else if(!playlist.getState().equals("published")
				&& !playlist.isBroadcastsDifferentThanPublished() 
				&& !playlist.isOrderDifferentThanPublished() 
				&& !playlist.isSettingsDifferentThanPublished()) 
		{
			playlist.setState("initial");
		}
		
		hibernateTemplate.clear();
		// And save the playlist
		hibernateTemplate.saveOrUpdate(playlist);
		hibernateTemplate.flush();
		
		hibernateTemplate.flush();

		return playlist;
	}
	
	@Override
	public Integer getPublicationByPlaylistId(Integer playlistId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("Select publication.id from Playlist where id = :playlistId");
		query.setInteger("publicationId", playlistId);
		return (Integer) query.uniqueResult();
	}
	
	@Override
	public Playlist getPlaylistById(Integer playlistId)
	{
		if (playlistId == null) {
			return null;
		}
		
		return getPlaylistById(playlistId, false);
	}
	
	@Override
	public Boolean getPlaylistByName(String playlistName)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query<?> query = session.createQuery("from Playlist where name = :playlistName AND deleted = false");
		query.setParameter("playlistName", playlistName);
		
		@SuppressWarnings("unchecked")
		List<Playlist> playlists = (List<Playlist>) query.getResultList();
		
		Boolean playlistAlreadyExists = false;
		if(!playlists.isEmpty() || playlists.size() > 0) {
			playlistAlreadyExists = true;
		}
		
		return playlistAlreadyExists;
	}

	@Override
	public Playlist getPlaylistById(Integer playlistId, Boolean evict)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Playlist where id = :playlistId");
		query.setInteger("playlistId", playlistId);
		Playlist playlist = (Playlist) query.uniqueResult();

		if (evict)
		{
			// Evict the current playlist
			session.evict(playlist);
		}

		return playlist;
	}
	
	@Override
	public Playlist getPlaylistByParentId(Integer playlistParentId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Playlist where parentid = :playlistParentId");
		query.setInteger("playlistParentId", playlistParentId);
		Playlist playlist = (Playlist) query.uniqueResult();

		return playlist;
	}
	
	/**
	 * This method retrieves the latest playlist for a publication.
	 * This is an unpublished playlist.
	 */
	@Override
	public List<Playlist> getCurrentPlaylistsForPublication(Integer publicationId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Publication publication = publicationDAO.getPublicationById(publicationId);

		Criteria criteria = session.createCriteria(Playlist.class)
				.add(Restrictions.eq("publication", publication))
				.add(Restrictions.eq("deleted", Boolean.FALSE))
				.add(Restrictions.isNull("publicationDate"))
				.setFetchMode("broadcastWrapper", FetchMode.JOIN)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		List<Playlist> playlists = criteria.list();

		// adding an addSort to the hibernate Criteria above does not work for some reason.
		// Manually sorting by name here.
		Collections.sort(playlists, new Comparator<Playlist>()
		{
			@Override
			public int compare(Playlist p1, Playlist p2)
			{
				return p1.getName().compareToIgnoreCase(p2.getName());
			}
		});

		return playlists;
	}

	/**
	 * This method retrieves all playlist for a publication, with the same name.
	 */
	@Override
	public List<Playlist> getAllVersionsOfPlaylist(Playlist playlist)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Criteria criteria = session.createCriteria(Playlist.class)
				.add(Restrictions.eq("publication", playlist.getPublication()))
				.add(Restrictions.eq("name", playlist.getName()))
				.setFetchMode("broadcastWrapper", FetchMode.JOIN)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		List<Playlist> playlists = criteria.list();

		return playlists;
	}

	/**
	 * Retrieve all published playlists for a publication by publication id
	 * 
	 * @see nl.prismait.epublisher.java.dao.IPlaylistDAO#getPublishedPlaylistsForPublication(java.lang.Integer)
	 */
	@Override
	public List<Playlist> getPublishedPlaylistsForPublication(Integer publicationId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String query = "FROM Playlist " +
					   "WHERE publication.id = :publication_id " +
					   "AND deleted = false " +
					   "AND publicationDate IS NOT NULL";

		@SuppressWarnings("unchecked")
		List<Playlist> list = session.createQuery(query).setParameter("publication_id", publicationId).list();
		return list;
	}

	/**
	 * Retrieve all published playlists from a certain playlist
	 * 
	 * @see nl.prismait.epublisher.java.dao.IPlaylistDAO#getPublishedPlaylistsForPublication(java.lang.Integer)
	 */
	@Override
	public List<Playlist> getPublishedPlaylistsForPlaylist(Integer id, Integer parentId)
	{
		Integer idToBeUsed = 0;
		
		if (parentId != null) {
			idToBeUsed = parentId;
		} else {
			parentId = id;
		}
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String query = "FROM Playlist " +
					   "WHERE id = :idToBeUsed OR parentid = :idToBeUsed " +
					   "AND deleted = false " +
					   "AND publicationDate IS NOT NULL";

		@SuppressWarnings("unchecked")
		List<Playlist> list = session.createQuery(query).setParameter("idToBeUsed", idToBeUsed).list();
		
		return list;
	}
	
	/**
	 * Retrieve all active playlists of screengroups, for a given publication.
	 */
	@Override
	public List<PlaylistScreengroupAndPublication> getActivePlaylistsAndScreengroupsForPublication(Integer publicationId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		/*
		 * This query is constructed using a large first query with placeholders (ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY)
		 * The placeholders are:
		 * {0} = the last part of the where clause that filters on screengroup id (see large first query)
		 * {1} = the select part of data you want to retrieve. (Or something entirely different, up to you)
		 */
		String query = SqlUtils.getQuery(
				Sql.ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY
				, "IN (SELECT id FROM screengroup WHERE publication_id = :publicationid)"
				, Sql.ALL_ACTIVE_PLAYLISTS_AND_SCREENGROUPS_FOR_PUBLICATION);

		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createSQLQuery(query).setParameter("publicationid", publicationId).list();

		List<PlaylistScreengroupAndPublication> converted = convertObjectsToPlaylistScreengroupAndPublication(list);

		// Get all users with access to the publications
		for (PlaylistScreengroupAndPublication current : converted)
		{
			current.setPublicationUsers(getUsersWithAccessToPublication(current.getPublicationId()));
		}

		return converted;
	}
	
	
	@Override
	public List<PlaylistScreengroupAndPublication> getActivePlaylistsForPublication(Integer publicationId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		/*
		 * This query is constructed using a large first query with placeholders (ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY)
		 * The placeholders are:
		 * {0} = the last part of the where clause that filters on screengroup id (see large first query)
		 * {1} = the select part of data you want to retrieve. (Or something entirely different, up to you)
		 */
		String query = SqlUtils.getQuery(
				Sql.ACTIVE_PLAYLISTS_FOR_PUBLISH_FOR_SCREENGROUP_QUERY
				, "IN (SELECT id FROM screengroup WHERE publication_id = :publicationid)"
				, Sql.ALL_ACTIVE_PLAYLISTS_WITH_DURATION_FOR_PUBLICATION);

		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createNativeQuery(query).setParameter("publicationid", publicationId).list();

		List<PlaylistScreengroupAndPublication> converted = convertObjectsToPlaylistAndPublication(list);

		return converted;
	}
	
	public  List<PlaylistScreengroupAndPublication> convertObjectsToPlaylistScreengroupAndPublication(List<Object[]> objects)
	{
		List<PlaylistScreengroupAndPublication> result = new ArrayList<PlaylistScreengroupAndPublication>();

		for (Object[] object : objects)
		{
			PlaylistScreengroupAndPublication converted = new PlaylistScreengroupAndPublication();

			converted.setScreenGroupName((String) object[0]);
			converted.setPublicationName((String) object[1]);
			converted.setPublicationId((Integer) object[2]);
			
			Playlist playlist = getPlaylistById((Integer) object[3]);
			converted.setPlaylist(playlist);
			
			result.add(converted);
		}

		return result;
	}
	
	public  List<PlaylistScreengroupAndPublication> convertObjectsToPlaylistAndPublication(List<Object[]> objects)
	{
		List<PlaylistScreengroupAndPublication> result = new ArrayList<>();
		List<Integer> duplicateID = new ArrayList<>();
		for (Object[] object : objects)
		{
			PlaylistScreengroupAndPublication converted = new PlaylistScreengroupAndPublication();

			converted.setPublicationName((String) object[0]);
			converted.setPublicationId((Integer) object[1]);
			
			Playlist playlist = getPlaylistById((Integer) object[2]);
			converted.setPlaylist(playlist);
			converted.setTotalPlaylistDuration((BigInteger) object[6]);
			converted.setActive((boolean) object[8]);
			
			//case when playlists play frequencies overlap, we get 2 playlists back
			//1 active and 1 in-active, we need only the active one in this case
			//so we store the ID and later remove the in-active playlist
			result.forEach(res -> {
				if(res.getPlaylist().getId().equals(object[2])) {
					duplicateID.add(res.getPlaylist().getId());
				}
					
			});

			result.add(converted);
		}
		
		//only loop if we have actual duplicate playlists
		if(!duplicateID.isEmpty()) {
			for (Iterator<PlaylistScreengroupAndPublication> iter = result.listIterator(); iter.hasNext(); ) {
				PlaylistScreengroupAndPublication obj = iter.next();
				for(Integer id : duplicateID) {
					if (!obj.isActive() && obj.getPlaylist().getId().equals(id)) {
						iter.remove();
					}
				}
			}
		}
		
		return result;
	}
	
	private List<SimpleUser> getUsersWithAccessToPublication(Integer publicationId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createSQLQuery(Sql.USERS_WITH_ACCESS_TO_PUBLICATION).setParameter("publicationid", publicationId).list();

		List<SimpleUser> users = SimpleUser.convertObjectsToSimpleUsers(list);

		return users;
	}

	/**
	 * Retrieve all active playlists, and their broadcasts, for a screengroup.
	 */
	@Override
	public List<PlaylistsPublicationsAndBroadcasts> getActivePlaylistsAndBroadcastsForScreenGroup(Integer screenGroupId, Integer publicationId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		/*
		 * This query is constructed using a large first query with placeholders (ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY)
		 * The placeholders are:
		 * {0} = the last part of the where clause that filters on screengroup id (see large first query)
		 * {1} = the select part of data you want to retrieve. (Or something entirely different, up to you)
		 */
		String query = SqlUtils.getQuery(
				Sql.ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY
				, "= :screengroupid"
				, Sql.CURRENT_PLAYLISTS_AND_BROADCASTS_FOR_SCREENGROUP);

		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createSQLQuery(query).setParameter("screengroupid", screenGroupId).setParameter("publicationId", publicationId).list();
		

		List<PlaylistsPublicationsAndBroadcasts> result = PlaylistsPublicationsAndBroadcasts.convertObjectsToPlaylistsPublicationsAndBroadcasts(list);

		return result;
	}

	/**
	 * Retrieve all previous versions of this playlist
	 * 
	 * @param playlist an instance of {@link Playlist}
	 * @return list of play list
	 */
	public List<Playlist> getPreviousPlaylistsByName(Playlist playlist)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Criteria criteria = session.createCriteria(Playlist.class)
				.add(Restrictions.eq("name", playlist.getName()))
				.add(Restrictions.eq("publication", playlist.getPublication()))
				// .add(Restrictions.eq("deleted", Boolean.FALSE))
				.add(Restrictions.isNotNull("publicationDate"))
				.setFetchMode("broadcastWrapper", FetchMode.JOIN)
				.addOrder(Order.asc("name"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		List<Playlist> playlists = criteria.list();

		return playlists;
	}
	
	
	public List<Playlist> getPreviousPlaylistsByNameAndId(Playlist playlist)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criterion c1 = Restrictions.eq("name", playlist.getName()) ;
		Criterion c2 = Restrictions.eq("publication", playlist.getPublication()) ;
		Criterion c4 = Restrictions.eq("id", playlist.getAncestorPlaylistId()) ;
		
		Criteria criteria = session.createCriteria(Playlist.class)
				.add(Restrictions.or(Restrictions.and(c1, c2), Restrictions.and(c4, c2)))
				.add(Restrictions.isNotNull("publicationDate"))
				.setFetchMode("broadcastWrapper", FetchMode.JOIN)
				.addOrder(Order.asc("name"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		List<Playlist> playlists = criteria.list();

		return playlists;
	}

	@Override
	@Transactional
	public void removeAllPlaylistsOfPublication(PublicationNarrowcastingNS publication)
	{
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		hibernateTemplate.flush();

		hibernateTemplate.clear();

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String query = "UPDATE playlist SET deleted = true WHERE publication_id = :publicationId";

		session.createSQLQuery(query).setInteger("publicationId", publication.getId()).executeUpdate();
	}
	
	
	@Override
	@Transactional
	public void updateplaylistWrapper(Integer oldPlaylist, Integer newPlaylist)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String query = "UPDATE playlistwrapper SET playlistid = :newPlaylist WHERE playlistid = :oldPlaylist";

		session.createSQLQuery(query).setParameter("oldPlaylist", oldPlaylist).setParameter("newPlaylist", newPlaylist).executeUpdate();
	}

	@Override
	@Transactional
	public void logNewBroadcast(BroadcastPlay logBroadcast) {

		hibernateTemplate.saveOrUpdate(logBroadcast);
		hibernateTemplate.flush();
	}
	
	/**
	 * Retrieve all active playlists of screengroups, for a given publication.
	 */
	@Override
	public BroadcastCacheWrapper getActivePlaylistBroadcastWithAssets(String screenId,Integer pubId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		/*
		 * This query is constructed using a large first query with placeholders (ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY)
		 * The placeholders are:
		 * {0} = the last part of the where clause that filters on screengroup id (see large first query)
		 * {1} = the select part of data you want to retrieve. (Or something entirely different, up to you)
		 */
		String query = SqlUtils.getQuery(
				Sql.ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY
				, "IN (SELECT id FROM screengroup WHERE publication_id = :pubId)"
				, Sql.ACTIVE_PLAYLIST_BROADCAST_ASSETS);

		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createSQLQuery(query).setParameter("pubId", pubId).list();

		BroadcastCacheWrapper converted = BroadcastCacheWrapper.convertObjectsToBroadcastCache(list);

		
		return converted;
	}
	
	
	@Override
	public List<Playlist> getActivePlaylistsIdsForPublication(Integer publicationId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		/*
		 * This query is constructed using a large first query with placeholders (ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY)
		 * The placeholders are:
		 * {0} = the last part of the where clause that filters on screengroup id (see large first query)
		 * {1} = the select part of data you want to retrieve(refer first sql file for knowing the table ). (Or something entirely different, up to you)
		 */
		String query = SqlUtils.getQuery(
				Sql.ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY
				, "IN (SELECT id FROM screengroup WHERE publication_id = :publicationid)"
				, "SELECT DISTINCT playlistId FROM playlistTime pt where pt.publication_Id !=:publicationid ");

		@SuppressWarnings("unchecked")
		List<Integer> list = session.createSQLQuery(query).setParameter("publicationid", publicationId).list();

		List<Playlist> playlist = new ArrayList<>();
		if(list != null && !list.isEmpty())
		 playlist = getPlaylistListByIds(list);

		return playlist;
	}
	
	@Override
	public List<Playlist> getAncestorPlaylistsIdsForPublication(Integer publicationId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		/*
		 * This query is constructed using a large first query with placeholders (ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY)
		 * The placeholders are:
		 * {0} = the last part of the where clause that filters on screengroup id (see large first query)
		 * {1} = the select part of data you want to retrieve(refer first sql file for knowing the table ). (Or something entirely different, up to you)
		 */
		String query = SqlUtils.getQuery(
				Sql.ANCESTOR_PLAYLISTS_FOR_SCREENGROUP_QUERY
				, "IN (SELECT id FROM screengroup WHERE publication_id = :publicationid)"
				, "SELECT DISTINCT playlistId FROM playlists pl where pl.publication_Id !=:publicationid ");

		@SuppressWarnings("unchecked")
		List<Integer> list = session.createSQLQuery(query).setParameter("publicationid", publicationId).list();

		List<Playlist> playlist = new ArrayList<>();
		if(list != null && !list.isEmpty())
		 playlist = getPlaylistListByIds(list);

		return playlist;
	}
	

	private List<Playlist> getPlaylistListByIds(List<Integer> playlistId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Playlist where id in :playlistId");
		query.setParameterList("playlistId", playlistId);
		List<Playlist> playlist = (List<Playlist>) query.list();

		
		return (List<Playlist>) playlist;
	}

	@Override
	public List<Integer> getScreenGroupByPublicationId(Integer publicationId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery("SELECT id FROM screengroup WHERE publication_id = :publicationid");
		query.setParameter("publicationid", publicationId);
		return  (List<Integer>) query.list();
	}
	
	private Boolean isOrderDifferentThanPublishedPlaylist(Playlist playlist)
	{
		Integer matchingIds = 0;
		
		// Only continue if we have a playlist and that playlist has a publication
		if (playlist != null && playlist.getPublication() != null)
		{
			// Get all published playlists for the publication
			List<Playlist> playlists = getPublishedPlaylistsForPublication(playlist.getPublication().getId());

			// Loop through the playlists
			for (Playlist publishedPlaylist : playlists)
			{
				// If the name is the same (only way to identify them), it's the playlist we need to check
				if (publishedPlaylist.getName().equals(playlist.getName()))
				{	
					if(publishedPlaylist.getBroadcastwrappers().size() != playlist.getBroadcastwrappers().size()) {
						return true;
					} else {
						for(BroadcastWrapper currentWrapper : publishedPlaylist.getBroadcastwrappers()) {
							for (BroadcastWrapper wrapper : playlist.getBroadcastwrappers()) {
								if(currentWrapper.getBroadcast().getId().equals(wrapper.getBroadcast().getId()) 
									|| ( currentWrapper.getBroadcast().getParentId() != null && wrapper.getBroadcast().getParentId() != null 
										&& currentWrapper.getBroadcast().getParentId().equals(wrapper.getBroadcast().getParentId() ))
									|| ( currentWrapper.getBroadcast().getParentId() != null && currentWrapper.getBroadcast().getParentId().equals(wrapper.getBroadcast().getId() ))
									|| ( wrapper.getBroadcast().getParentId() != null && currentWrapper.getBroadcast().getId().equals(wrapper.getBroadcast().getParentId() ))) {
									//we count all matching ids for the case when a broadcast gets deleted and then another one was added
									//this way the playlist publish status will return false if a different broadcast has been added
									matchingIds++;
									
									if(!Objects.equals(currentWrapper.getOrderOfBroadcast(), wrapper.getOrderOfBroadcast())) {
										return true;
									}
								}
							}
						}
					}
					
					if(matchingIds != publishedPlaylist.getBroadcastwrappers().size()) {
						return true;
					}
					
				}
			}
		}

		return false;
	}
	
	private Boolean areSettingsDifferentThanPublishedPlaylist(Playlist playlist)
	{	
		boolean found = false;
		
		// Only continue if we have a playlist and that playlist has a publication
		if (playlist != null && playlist.getPublication() != null)
		{
			// Get all published playlists for the publication
			List<Playlist> playlists = getPublishedPlaylistsForPublication(playlist.getPublication().getId());

			// Loop through the playlists
			for (Playlist publishedPlaylist : playlists)
			{
				// If the name is the same (only way to identify them), it's the playlist we need to check
				if (publishedPlaylist.getName().equals(playlist.getName()))
				{	
					found = true;
					
					// If the priority is different, it already has different settings
					if (playlist.getPriority() != publishedPlaylist.getPriority()) {
						return true;
					}
					
					// Different frequency
					if( publishedPlaylist.getPlayFrequency().size() != playlist.getPlayFrequency().size()) {
						return true;
					}
					
					// Check if the playtimes are different
					for(int i=0; i < publishedPlaylist.getPlayFrequency().size(); i++) 
					{
						Boolean playtimeFound = false;
						
						for(int j=0; j < playlist.getPlayFrequency().size(); j++) 
						{
							if (playlist.getPlayFrequency().get(j).compareTo(publishedPlaylist.getPlayFrequency().get(i)) == 0)
							{
								// set it to be found
								playtimeFound = true;
								
								if(playlist.getPlayFrequency().get(j).getDays().size() != (publishedPlaylist.getPlayFrequency().get(i).getDays().size())) {
									playtimeFound = false;
								}
							}
						}
						if (Boolean.FALSE.equals(playtimeFound))
							return true;
					}
					
					
					// Different screengroups size on playlist level
					if (publishedPlaylist.getScreengroups() != null && playlist.getScreengroups() != null) {
						if(publishedPlaylist.getScreengroups().size() != playlist.getScreengroups().size()) {
							return true;
						}
						
						// Check for more possible differences on the screengroups (playlist level)
						for (ScreenGroup sg : publishedPlaylist.getScreengroups())
						{
							if (!playlist.getScreengroups().contains(sg)) {
								return true;
							}
						}
					} else {
						// both must be null or empty to avoid having differences
						if (publishedPlaylist.getScreengroups() != null && !publishedPlaylist.getScreengroups().isEmpty() 
								|| playlist.getScreengroups() != null && !playlist.getScreengroups().isEmpty()) {
							return true;
						}
					}
				}
			}
			
			if (!found){
				// never published (new playlist)
				return true;
			}
		}

		return false;
	}
	
	private Boolean areBroadcastsDifferentThanPublishedPlaylist(Playlist playlist)
	{
		// Only continue if we have a playlist and that playlist has a publication
		if (playlist != null && playlist.getPublication() != null)
		{
			// Get all published playlists for the publication
			List<Playlist> playlists = getPublishedPlaylistsForPublication(playlist.getPublication().getId());

			// Loop through the playlists
			for (Playlist publishedPlaylist : playlists)
			{
				// If the name is the same (only way to identify them), it's the playlist we need to check
				if (publishedPlaylist.getName().equals(playlist.getName()))
				{	
					if(publishedPlaylist.getBroadcastwrappers().size() != playlist.getBroadcastwrappers().size()) {
						return true;
					} else {
						
						for (BroadcastWrapper wrapper : playlist.getBroadcastwrappers()) {
							if(wrapper.isAdded() || wrapper.isModified() || wrapper.isDeleted()) {
								return true;
							}
						}
					}
				}
			}
			
		}

		return false;
	}
	
	@Override
	public Boolean publishTreinPlaylist(String playlistJSON) throws IOException
	{
		
			URL url = new URL (EpublisherConstants.NS_TREIN_CHANNEL_BASE_URL + EpublisherConstants.NS_TREIN_CHANNEL_PUBLISH_PLAYLIST);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			Boolean response = false;
			
			try(OutputStream os = con.getOutputStream()) {
			    byte[] input = playlistJSON.getBytes(StandardCharsets.UTF_8);
			    os.write(input, 0, input.length);			
			}catch (ConnectException e) {
				logger.info("Connection to microservice endpoint 'publishTreinPlaylist' failed.");
			} 
			
			try(BufferedReader br = new BufferedReader(
					  new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
					    
					    String responseLine = null;
					    while ((responseLine = br.readLine()) != null) {
					    	response = Boolean.valueOf(responseLine.trim());
					    }
					    logger.info("Response from microservice is: {}", response);
					}
			
		return response;
	}
	
	@Override
	public Boolean deleteTreinPlaylist(String playlistId) throws IOException
	{
		URL url = new URL (EpublisherConstants.NS_TREIN_CHANNEL_BASE_URL + EpublisherConstants.NS_TREIN_CHANNEL_DELETE_PLAYLIST);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		Boolean response = false;

		try(OutputStream os = con.getOutputStream()) {
			byte[] input = playlistId.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);			
		}catch (ConnectException e) {
			logger.info("Connection to microservice endpoint 'deleteTreinPlaylist' failed.");
		} 

		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {

			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response = Boolean.valueOf(responseLine.trim());
			}
			logger.info("Response from deleteTreinPlaylist microservice is: {}", response);
		}

		return response;
	}
	
	@Override
	public Boolean unpublishTreinPlaylist(String playlistId) throws IOException
	{
		URL url = new URL (EpublisherConstants.NS_TREIN_CHANNEL_BASE_URL + EpublisherConstants.NS_TREIN_CHANNEL_UNPUBLISH_PLAYLIST);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		Boolean response = false;

		try(OutputStream os = con.getOutputStream()) {
			byte[] input = playlistId.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);			
		}catch (ConnectException e) {
			logger.info("Connection to microservice endpoint 'unpublishTreinPlaylist' failed.");
		} 

		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {

			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response = Boolean.valueOf(responseLine.trim());
			}
			logger.info("Response from unpublishTreinPlaylist microservice is: {}", response);
		}

		return response;
	}
	
	@Override
	public Playlist sharePlaylist(Integer playlistId, String email)
	{
		return null;
	}

	@Override
	public Boolean validatePlaylistPriorityForUser(int priority, String email) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String query = "SELECT CASE WHEN maxplaylistpriority is null THEN true "
				+ " WHEN maxplaylistpriority >= :priority THEN true "
				+ "ELSE false "
				+ " END AS isAllowed "
				+ "FROM epublisheruser where email = :email";

		return (Boolean)session.createSQLQuery(query).setParameter("email", email).setParameter("priority", priority).uniqueResult();
	}
	
	@Override
	public List<Playlist> getPlaylistHistory(Integer playlistId, Integer limit)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        
		Query query = session.createQuery("SELECT p.id"
				+" FROM Playlist p"
				+" WHERE ( (p.id = :playlistId OR p.parentId = :playlistId)"
				+" AND ( p.state = 'published' OR p.state = 'unpublished'))"
				+" ORDER BY p.lastUpdated DESC"
				);
		
		query.setParameter("playlistId", playlistId);
		query.setMaxResults(limit);
		
		List<Playlist> convertedResult = new ArrayList<Playlist>();
		List<Integer> objects = query.getResultList();
		
		// convert objects to playlist
		for (Integer object : objects)
		{
			Playlist playlist = getPlaylistById((object));
			
			// set playlist's last action
			if (playlist.getState().equals("published")) {
				playlist.setLastAction("Published");
			} else {
				playlist.setLastAction("Unpublished");
			}
			
			convertedResult.add(playlist);
		}

		return convertedResult;
	}
	
	@Override
	public Long getPlaylistHistoryCount(Integer playlistId) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        
		Query query = session.createQuery("SELECT COUNT(p.id)"
				+" FROM Playlist p"
				+" WHERE ( (p.id = :playlistId OR p.parentId = :playlistId)"
				+" AND ( p.state = 'published' OR p.state = 'unpublished'))"
				);
		
		query.setParameter("playlistId", playlistId);

		return (Long) query.getSingleResult();
	}
	

	@Override
	@Transactional
	public BigInteger countBroadcastsInPlaylist(Integer playlistId) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        
		Query query = session.createSQLQuery("SELECT COUNT(broadcastwrappers_id)"
				+ "	FROM playlist_broadcastwrapper plbw"
				+ "	LEFT JOIN broadcastwrapper bw on plbw.broadcastwrappers_id = bw.id"
				+ "	where playlist_id = :playlistId"
				+ "	and bw.deleted = false"
				);
		
		query.setParameter("playlistId", playlistId);
		
		return (BigInteger) query.getSingleResult();
	}
	
	@Override
	public List<ActivePlaylistWrapper> getActivePlaylistIds(String screenId,Integer pubId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		/*
		 * This query is constructed using a large first query with placeholders (ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY)
		 * The placeholders are:
		 * {0} = the last part of the where clause that filters on screengroup id (see large first query)
		 * {1} = the select part of data you want to retrieve. (Or something entirely different, up to you)
		 */
		String query = SqlUtils.getQuery(
				Sql.ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY
				, "IN (SELECT id FROM screengroup WHERE publication_id = :pubId)"
				, Sql.ACTIVE_PLAYLIST_ID);

		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createSQLQuery(query).setParameter("pubId", pubId).list();


		
		return ActivePlaylistWrapper.convertObjectsToPlaylistCache(list);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Playlist> getSharedPlaylistInfo() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        
		Query query = session.createQuery(" from Playlist where shared = true and deleted = false");
		
		return query.list();
	}
	
	@Override
	public List<Playlist> getAllPlaylistsByParentId(Integer parentId){
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        
		Query query = session.createQuery("from Playlist where deleted = false and parentid = :parentId and id != :parentId");
		query.setParameter("parentId", parentId);
		
		return query.list();
	}
}
