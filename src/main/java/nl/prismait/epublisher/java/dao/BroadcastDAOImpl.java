package nl.prismait.epublisher.java.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import nl.prismait.epublisher.java.model.Location;
import nl.prismait.epublisher.java.dao.sql.Sql;
import nl.prismait.epublisher.java.dao.util.SqlUtils;
import nl.prismait.epublisher.java.model.Route;
//import nl.prismait.epublisher.java.model.User;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.Broadcast;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastFilter;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastSearch;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.TemplateNarrowcasting;

@Component("broadcastDAO")
public class BroadcastDAOImpl implements IBroadcastDAO
{
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional
	public Broadcast save(Broadcast broadcast) {
		broadcast.setLastChangedDate(new Date());
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		hibernateTemplate.clear();
		hibernateTemplate.saveOrUpdate(broadcast);
		hibernateTemplate.flush();
		return broadcast;
	}

	@Override
	@Transactional
	public BroadcastWrapper save(BroadcastWrapper broadcastWrapper) {
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		hibernateTemplate.saveOrUpdate(broadcastWrapper);
		hibernateTemplate.flush();
		
		return broadcastWrapper;
	}

	@Override
	public void delete(BroadcastWrapper broadcastWrapper)
	{
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		hibernateTemplate.delete(broadcastWrapper);
		hibernateTemplate.flush();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void deleteRoutesOnContentblocks(Route route)
	{
		// delete routes connections on contentblock_route
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery("DELETE FROM contentblock_route WHERE route_id = :routeId ;");
		query.setParameter("routeId", route.getId(),IntegerType.INSTANCE);
		query.executeUpdate();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void deleteRoutesOnLocations(Route route)
	{
		// delete routes connections on contentblock_route
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery("DELETE FROM route_location WHERE route_id = :routeId ;");
		query.setParameter("routeId", route.getId(),IntegerType.INSTANCE);
		query.executeUpdate();
	}
	
	@Override
	public void deleteRoute(Route route)
	{
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		hibernateTemplate.delete(route);
		hibernateTemplate.flush();
		hibernateTemplate.clear();
	}
	
	@Override
	public void deleteAll(List<BroadcastWrapper> broadcastWrapper)
	{
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		hibernateTemplate.deleteAll(broadcastWrapper);
		hibernateTemplate.flush();
		hibernateTemplate.clear();
	}
	
	
	@Override
	@Transactional
	public void deleteBroadcastWrapper(List<BroadcastWrapper> wrappers, Integer broadcastId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		List<Integer> wrapperIds = new ArrayList<Integer>(); 
	  
		for (BroadcastWrapper wrapper : wrappers) 
		    {
			  	wrapperIds.add(wrapper.getId()); 
		    }
	  
	  	//query to find out if any of the broadcasts wrappers are part of a unpublished playlist
	  	//if we find wrappers present in any playlist we show a message that 
	  	//the broadcast is still in use and cannot be deleted until it is not part of any playlist 
		Query queryPlaylistBroadcastWrapper = session.createSQLQuery("SELECT playlist_id"
			+ "	FROM playlist_broadcastwrapper plbw"
			+ "	INNER JOIN playlist pl on playlist_id = pl.id"
			+ " INNER JOIN broadcastwrapper bw on plbw.broadcastwrappers_id = bw.id"
			+ " INNER JOIN broadcast b on bw.broadcast_id = b.id"
			+ "	where pl.publicationdate is null"
			+ " and pl.deleted = false"
			+ " and bw.deleted = false"
			+ "	and (b.id = :broadcastId or b.parentid = :broadcastId)");
		queryPlaylistBroadcastWrapper.setParameter("broadcastId", broadcastId);
		
  		if (!queryPlaylistBroadcastWrapper.list().isEmpty())
			{
		  		throw new EpublisherException("De uitzending kan niet verwijderd worden, omdat deze nog gebruikt wordt in een playlist.");
			}
		
		if (!wrapperIds.isEmpty())
			{

			//SAAS-859 fix
			Query query = session.createSQLQuery("DELETE FROM publication_disabledbroadcast WHERE disabledbroadcast_id = :broadcastId");
			query.setParameter("broadcastId", broadcastId, IntegerType.INSTANCE);
			query.executeUpdate();
		}

		for (BroadcastWrapper wrapper : wrappers)
		{
			// we only update and not delete broadcastwrapper published ones SAAS-1620
			wrapper.setDeleted(true);
			save(wrapper);
		}
		
	}

	@Override
	@Transactional
	public void delete(Broadcast broadcast)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query queryBroadcastWrapper = session.createQuery("from BroadcastWrapper where broadcast.id = :broadcast");
		queryBroadcastWrapper.setParameter("broadcast", broadcast.getId());
		@SuppressWarnings("unchecked")
		List<BroadcastWrapper> broadcastWrappers = queryBroadcastWrapper.list();
		List<Integer> wrapperIds = new ArrayList<Integer>(); 
	  
		for (BroadcastWrapper wrapper : broadcastWrappers) 
		    {
			  	wrapperIds.add(wrapper.getId()); 
		    }
	  
	  	//query to find out if any of the broadcasts wrappers are part of a unpublished playlist
	  	//if we find wrappers present in any playlist we show a message that 
	  	//the broadcast is still in use and cannot be deleted until it is not part of any playlist 
		Query queryPlaylistBroadcastWrapper = session.createSQLQuery("SELECT playlist_id"
			+ "	FROM playlist_broadcastwrapper plbw"
			+ "	INNER JOIN playlist pl on playlist_id = pl.id"
			+ " INNER JOIN broadcastwrapper bw on plbw.broadcastwrappers_id = bw.id"
			+ " INNER JOIN broadcast b on bw.broadcast_id = b.id"
			+ "	where pl.publicationdate is null"
			+ "	and b.id = :broadcastId"
			+ " and pl.deleted = false"
			+ " and bw.deleted = false");
		queryPlaylistBroadcastWrapper.setParameter("broadcastId", broadcast.getId());
		
  		if (!queryPlaylistBroadcastWrapper.list().isEmpty())
			{
		  		throw new EpublisherException("De uitzending kan niet verwijderd worden, omdat deze nog gebruikt wordt in een playlist.");
			}
		
		if (!wrapperIds.isEmpty())
			{
			// delete only if broadcastwrappers in playlist
			Query query = session.createSQLQuery("DELETE FROM playlist_broadcastwrapper WHERE broadcastwrappers_id in (:wrapperIds);");
			query.setParameterList("wrapperIds", wrapperIds, IntegerType.INSTANCE);
			query.executeUpdate();

			//SAAS-859 fix
			query = session.createSQLQuery("DELETE FROM publication_disabledbroadcast WHERE disabledbroadcast_id = :broadcastId");
			query.setParameter("broadcastId", broadcast.getId(), IntegerType.INSTANCE);
			query.executeUpdate();
		}

		for (BroadcastWrapper wrapper : broadcastWrappers)
		{
			hibernateTemplate.clear();
			hibernateTemplate.delete(wrapper);
			hibernateTemplate.flush();
		}
		
		// delete broadcast
		Broadcast deleteBroadcastObject = hibernateTemplate.get(Broadcast.class, broadcast.getId());
		hibernateTemplate.clear();
		hibernateTemplate.delete(deleteBroadcastObject);
		hibernateTemplate.flush();
	}
	
	
	//because of the issue with wrong broadcast parent ID's 
	//we use this method to correctly delete the broken broadcasts
	//it is only called when catching a DataIntegrityViolationException while deleting a broadcast
	@Override
	public void deleteBrokenBroadcasts(Broadcast broadcast) {

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query queryBroadcastWrapper = session.createQuery("from BroadcastWrapper where broadcast.id = :broadcast");
		queryBroadcastWrapper.setParameter("broadcast", broadcast.getId());
		@SuppressWarnings("unchecked")
		List<BroadcastWrapper> broadcastWrappers = queryBroadcastWrapper.list();
		Set<BroadcastWrapper> wrappersSet = new HashSet<BroadcastWrapper>();
		
		
		Set<Integer> wrapperIds = new HashSet<Integer>(); 
		List<Integer> broadcastWrappersFromActivePlaylists = new ArrayList<Integer>();
	    List<Integer> ancestorsToBeDeleted = new ArrayList<Integer>();
	    
		Query queryancestorsToBeDeleted = session.createSQLQuery("with recursive cte(id, parentid, ids) "
				+ " as (select id, parentid,  array[id]"
				+ " from broadcast"
				+ " union all"
				+ " select t.id, t.parentid, ids || t.id"
				+ " from cte c"
				+ " join broadcast t on c.id = t.parentid)"
				+ " select id"
				+ " from cte"
				+ " where :broadcastId = any(ids)"
				+ " order by id desc ");
		queryancestorsToBeDeleted.setParameter("broadcastId", broadcast.getId());
		ancestorsToBeDeleted.addAll(queryancestorsToBeDeleted.list());
		
		for(Integer id : ancestorsToBeDeleted) {
			Query queryAncestorBroadcastWrapper = session.createQuery("from BroadcastWrapper where broadcast.id = :id");
			queryAncestorBroadcastWrapper.setParameter("id", id);
			broadcastWrappers.addAll(queryAncestorBroadcastWrapper.list());
			
		}
		
		wrappersSet.addAll(broadcastWrappers);
		
		for (BroadcastWrapper wrapper : wrappersSet) 
		    {
			  	wrapperIds.add(wrapper.getId()); 
		    }
	  
	  	//query to find out if any of the broadcasts wrappers are part of a unpublished playlist
	  	//if we find wrappers present in any playlist we show a message that 
	  	//the broadcast is still in use and cannot be deleted until it is not part of any playlist 
		Query queryPlaylistBroadcastWrapper = session.createSQLQuery("SELECT playlist_id"
			+ "	FROM playlist_broadcastwrapper plbw"
			+ "	INNER JOIN playlist pl on playlist_id = pl.id"
			+ " INNER JOIN broadcastwrapper bw on plbw.broadcastwrappers_id = bw.id"
			+ " INNER JOIN broadcast b on bw.broadcast_id = b.id"
			+ "	where pl.publicationdate is null"
			+ "	and b.id = :broadcastId"
			+ " and pl.deleted = false"
			+ " and bw.deleted = false");
		queryPlaylistBroadcastWrapper.setParameter("broadcastId", broadcast.getId());
		
  		if (!queryPlaylistBroadcastWrapper.list().isEmpty())
			{
		  		throw new EpublisherException("De uitzending kan niet verwijderd worden, omdat deze nog gebruikt wordt in een playlist.");
			}
		
		if (!wrapperIds.isEmpty())
			{
				
			// delete only if broadcastwrappers in playlist
			Query query = session.createSQLQuery("DELETE FROM playlist_broadcastwrapper WHERE broadcastwrappers_id in (:wrapperIds);");
			query.setParameterList("wrapperIds", wrapperIds, IntegerType.INSTANCE);
			query.executeUpdate();

			//SAAS-859 fix
			query = session.createSQLQuery("DELETE FROM publication_disabledbroadcast WHERE disabledbroadcast_id = :broadcastId");
			query.setParameter("broadcastId", broadcast.getId(), IntegerType.INSTANCE);
			query.executeUpdate();
		}
		
		for (BroadcastWrapper wrapper : wrappersSet)
		{
			hibernateTemplate.clear();
			hibernateTemplate.delete(wrapper);
			hibernateTemplate.flush();
		}
		
		for(Integer id : ancestorsToBeDeleted) {
			Broadcast deleteBroadcastObject = hibernateTemplate.get(Broadcast.class, id);
			if(deleteBroadcastObject != null) {
				hibernateTemplate.clear();
				hibernateTemplate.delete(deleteBroadcastObject);
				hibernateTemplate.flush();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateNarrowcasting> getAllTemplates()
	{
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query queryResult = session.createQuery("from TemplateNarrowcasting order by name");

		return queryResult.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Route> getAllRoutes(Set<Location> allowedLocations)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query queryResult = session.createQuery("from Route r where (r.location in :allowedLocations OR r.location.id = NULL) order by r.company");
		queryResult.setParameterList("allowedLocations", allowedLocations);
		
		return queryResult.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Route saveRoute(Route route) {
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		route = hibernateTemplate.merge(route);
		hibernateTemplate.flush();

		return route;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BroadcastSearch searchBroadcasts(BroadcastFilter filter)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Integer count = getBroadcastCount(filter);
		String queryString = buildSearchBroadcastsQuery(filter, true);
		SQLQuery query = session.createSQLQuery(queryString);
		setSearchBroadcastsQueryParameters(query, filter);

		List<Object[]> list = query.list();

		List<Integer> broadcastIds = new ArrayList<Integer>();

		for (Object[] broadcastIdObj : list)
		{
			broadcastIds.add((Integer) broadcastIdObj[0]);
		}

		List<Broadcast> broadcasts = new ArrayList<Broadcast>();
		if (!broadcastIds.isEmpty())
		{
			Criteria cmd = session.createCriteria(Broadcast.class);
			
			cmd.add(Restrictions.in("id", broadcastIds));
			cmd.createAlias("template", "template", CriteriaSpecification.LEFT_JOIN);
			cmd.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			if (filter.getSortField() != null)
			{
				if (filter.isAscendingSortOrder())
					cmd.addOrder(Order.asc(filter.getSortField()));
				else
					cmd.addOrder(Order.desc(filter.getSortField()));
			}
			else
				cmd.addOrder(Order.desc("dateCreated"));

			broadcasts = cmd.list();
		}
		BroadcastSearch broadcastSearchResult = new BroadcastSearch();

		broadcastSearchResult.setFoundBroadcasts(broadcasts);
		broadcastSearchResult.setMaxResults(filter.getNumberOfResults());
		broadcastSearchResult.setStartrowSearch(filter.getStartPage());
		broadcastSearchResult.setTotalNumberOfResults(count.intValue());

		return broadcastSearchResult;
	}

	public Integer getBroadcastCount(BroadcastFilter filter)
	{
		// discard paging to determine the full result count
		BroadcastFilter countFilter = filter.copyObject();
		countFilter.setStartPage(null);
		countFilter.setNumberOfResults(null);
		// don't need sorting as I am interested only in the number of results
		countFilter.setSortField(null);

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String queryString = buildSearchBroadcastsQuery(countFilter, false);
		SQLQuery query = session.createSQLQuery(queryString);
		setSearchBroadcastsQueryParameters(query, countFilter);

		return ((Number) query.uniqueResult()).intValue();
	}

	private void setSearchBroadcastsQueryParameters(SQLQuery query, BroadcastFilter filter)
	{
		if (filter.getTemplateName() != null)
			query.setParameter("templateName", "%" + filter.getTemplateName() + "%");

		if (filter.getBroadcastName() != null)
			query.setParameter("keyword", "%" + filter.getBroadcastName() + "%");

		if (filter.getCreatedBy() != null)
			query.setParameter("createdBy",  filter.getCreatedBy() );

		if (filter.getUserId() != null)
			query.setParameter("userId", filter.getUserId());
		
		if (filter.isWayfinder() || !filter.isWayfinder()) {
			query.setParameter("wayfinder", filter.isWayfinder());
		}
		
		if (filter.isWayfinder()) {
			query.setParameter("userWayfinderId", filter.getUserWayfinder(),IntegerType.INSTANCE);
		}
	}
	
	private String buildSearchBroadcastsQuery(BroadcastFilter filter, boolean usePaging )
	{
		StringBuilder query = new StringBuilder("select ");
		if(usePaging)
		{
			query.append(" b.id,b.name as name, template.name as templateName, app.name as appName, case when ((b.displayStartDate is null OR  b.displayStartDate <= CURRENT_TIMESTAMP )");
			query.append(" AND (b.displayEndDate is null OR CURRENT_TIMESTAMP <= b.displayEndDate))");
			query.append(" then true else false end as active , b.datecreated , b.wayfinder, em.url");
			query.append(" from broadcast b ");
			
		}else
		{
			query.append(" count(*) ");
			query.append(" from broadcast b ");
		}
		
		if (filter.getTemplateName() != null || filter.getSortField() != null)
			query.append(" left join templatenarrowcasting template on b.template_id = template.id ");
		else
			query.append(" left join templatenarrowcasting template on b.template_id = template.id ");
		
		if (filter.getAppName() != null || filter.getSortField() != null)
			query.append(" left join usertemplateapps app on b.app_id = app.id ");
		else
			query.append(" left join usertemplateapps app on b.app_id = app.id ");
		
		if (filter.getUserId() != null) {
			query.append(" left join epublisheruser_templatenarrowcasting on b.template_id = availabletemplates_id");
			query.append(" left join epublisheruser_app on b.app_id = availableapps_id");
		} else if (filter.isWayfinder()) {
			query.append(" left join epublisheruser_templatenarrowcasting et on b.template_id = et.availabletemplates_id");
		}
		
		if (filter.isExternalPlaylist()) {
			query.append(" inner join Broadcastwrapper bw on b.id = bw.broadcast_id "
					+ " inner join  playlist_broadcastwrapper pbw on pbw.broadcastwrappers_id = bw.id "
					+ " inner join playlist p on p.id = pbw.playlist_id ");
		}
		
		
		if (filter.getCreatedBy() != null) {
			query.append(" left join epublisheruser u on b.createdBy = u.id ");
		}
		
		query.append(" left join epublishermedia em on b.thumbnail_id = em.id ");

		
		if (filter.getTemplateName() != null)
		{
			query.append(" WHERE template.name ilike :templateName ");
		}

		if (filter.getBroadcastName() != null)
		{
			if (filter.getTemplateName() != null)
				query.append(" AND b.name ilike :keyword ");
			else
				query.append(" WHERE b.name ilike :keyword ");
		}

		// wayfinder
		if (filter.isWayfinder() || !filter.isWayfinder())
		{
			// wayfinder - filter by templates that are actually allowed
			if (filter.isWayfinder()) {
				if (filter.getTemplateName() != null || filter.getBroadcastName() != null)
					query.append(" AND b.wayfinder = :wayfinder AND et.availabletemplates_id IS NOT NULL "
							+ "AND et.epublisheruser_id = :userWayfinderId");
				else
					query.append(" WHERE b.wayfinder = :wayfinder AND et.availabletemplates_id IS NOT NULL "
							+ "AND et.epublisheruser_id = :userWayfinderId");
			} else {
				if (filter.getTemplateName() != null || filter.getBroadcastName() != null)
					query.append(" AND b.wayfinder = :wayfinder ");
				else
					query.append(" WHERE b.wayfinder = :wayfinder ");
			}
		}
		
		if (filter.getCreatedBy() != null)
		{
			if (filter.getTemplateName() != null || filter.getBroadcastName() != null || filter.isWayfinder() || !filter.isWayfinder())
				query.append(" AND (u.firstname ~* :createdBy OR u.lastname ~* :createdBy OR u.middlename ~* :createdBy ) ");
			else
				query.append(" WHERE (u.firstname ~* :createdBy OR u.lastname ~* :createdBy OR u.middlename ~* :createdBy) ");
		}

		if (filter.getUserId() != null)
		{
			if (filter.getTemplateName() != null || filter.getBroadcastName() != null || filter.getCreatedBy() != null 
					|| filter.isWayfinder() || !filter.isWayfinder())
				query.append(" AND epublisheruser_id = :userId ");
			else
				query.append(" WHERE epublisheruser_id = :userId ");
		}

		// make sure only parents are shown on the search and not clones
		if (filter.getTemplateName() != null || filter.getBroadcastName() != null || filter.getCreatedBy() != null 
				|| filter.isWayfinder() || !filter.isWayfinder() || filter.getUserId() != null) {
			query.append(" AND b.parentid is NULL ");
		}
		else {
			query.append(" WHERE b.parentid is NULL ");
		}
		
		//SAAS-1620
		query.append(" and b.deleted is false ");
		
		if (filter.isExternalPlaylist() ) {
			query.append(" and p.shared is true and p.deleted is false ");
		}
				
		if (filter.getSortField() != null && usePaging)
		{
			// bug in hibernate can't use place holders in query string template, hibernate ignoring order by statement so had to do it here
			if (filter.getSortField().equalsIgnoreCase("name"))
			{
				// name needs an alias as it can be ambiguous, template also contains a name - OH How I miss proper naming conventions
				query.append(" order by b.name ");
			}
			else
			{
				query.append(" order by " + filter.getSortField() + " ");
			}
			query.append(filter.isAscendingSortOrder() ? "asc " : "desc ");
		}
		else if(usePaging)
			query.append(" order by datecreated desc ");

		if (usePaging)
		{
			if (filter.getStartPage() != null && filter.getNumberOfResults() == null)
				query.append("offset " + filter.getStartPage() + " ");
			else if (filter.getStartPage() != null && filter.getNumberOfResults() != null)
			{
				query.append("offset " + filter.getStartPage() * filter.getNumberOfResults() + " ");
				query.append("limit " + filter.getNumberOfResults());
			}
			else if (filter.getNumberOfResults() != null)
				query.append("limit " + filter.getNumberOfResults());
		}
		return query.toString();
	}

	@Override
	public Broadcast getBroadcastById(Integer id) {
		return hibernateTemplate.get(Broadcast.class, id);
	}

//	@Override
//	public Route getRouteById(Integer id) {
//		return hibernateTemplate.get(Route.class, id);
//	}
	
	@Override
	public BroadcastWrapper getBroadcastWrapperById(Integer id)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query queryResult = session.createQuery("from BroadcastWrapper bw  where bw.id= :id and bw.broadcast.deleted is false");
		queryResult.setParameter("id", id);
		return (BroadcastWrapper) queryResult.uniqueResult();
	}

	
	@Override
	@Transactional
	public BroadcastWrapper getBroadcastByAdvertisementId(String id)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query queryResult = session.createQuery("from BroadcastWrapper bw  where bw.advertisementId= :id ");
		queryResult.setParameter("id", id);
		return (BroadcastWrapper) queryResult.uniqueResult();
	}
	public BroadcastSearch searchBroadcasts2(int startrow, int numberOfResults)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Long count = (Long) session.createQuery("select count(*) from Broadcast b where b.deleted is false ").uniqueResult();

		Criteria criteria = session.createCriteria(Broadcast.class);
		criteria.addOrder(Order.asc("name"));

		int firstResult = ((startrow + 1) * numberOfResults) - numberOfResults;
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(numberOfResults);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		List<Broadcast> broadcasts = criteria.list();

		BroadcastSearch broadcastSearchResult = new BroadcastSearch();

		broadcastSearchResult.setFoundBroadcasts(broadcasts);
		broadcastSearchResult.setMaxResults(numberOfResults);
		broadcastSearchResult.setStartrowSearch(startrow);
		broadcastSearchResult.setTotalNumberOfResults(count.intValue());

		return broadcastSearchResult;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchBroadcastsSummaries(BroadcastFilter filter) //,User user)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String queryString = buildSearchBroadcastsQuery(filter, true);
		NativeQuery<Object[]> query = session.createNativeQuery(queryString);
		
		// set id to filter wayfinders by template
//		if (filter.isWayfinder()) {
//			filter.setUserWayfinder(user.getId());
//		}
		
		setSearchBroadcastsQueryParameters(query, filter);
		
		return  query.list();
	}


	@Override
	public List<Object[]> getAvailablePublicationWithPlaylist(BroadcastFilter filter,String emailAddress, List<String> publicationTypes, boolean isExternalPlaylist) 
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		
		StringBuilder queryString = new StringBuilder("select "
								+ "distinct pu.id as id,pu.name as pub_name ,pl.id as playlist_id, pl.name as playlist_name, pu.dtype as dtype, "
								+ "pl.state as state"
								+ "	from"
								+ "	epublisheruser eu"
								+ "	INNER JOIN epublisheruser_publication epp ON eu.id = epp.epublisheruser_id"
								+ "	inner join publication pu on pu.id = epp.availablepublications_id"
								+ "	LEFT join playlist pl on pl.publication_id = pu.id "
								+ "	where"
								+ "	eu.email = :email"
								+ "	and pu.dtype IN (:publicationTypes)" 
								+ "	and pl.deleted = false "
								+ "	and pl.publicationdate IS NULL");
								
		if(isExternalPlaylist) {
			queryString.append( " and pl.shared IS true"
								+ "	ORDER BY pub_name , playlist_name");
		}else {
			queryString.append( "	ORDER BY pub_name , playlist_name");
		}
		
		NativeQuery<Object[]> query = session.createNativeQuery(queryString.toString());
		query.setParameter("email", emailAddress);
		query.setParameterList("publicationTypes", publicationTypes);
		return query.list();
	}

	@Override
	public List<Object[]> getActiveBroadcastForPlaylist(int broadcastId,String emailAddress, List<String> publicationTypes, boolean isExternalPlaylist) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		StringBuilder queryString = new StringBuilder("select DISTINCT pu.id as id,pu.name as pub_name ,pl.id as playlist_id, pl.name as playlist_name, case when b.id is null then false else true end as active, b.parentid, pl.state   "
								+ "	from "
								+ "epublisheruser eu 	"
								+ "	INNER JOIN epublisheruser_publication epp ON eu.id = epp.epublisheruser_id	"
								+ "	inner join publication pu on pu.id = epp.availablepublications_id	"
								+ "	LEFT join playlist pl on pl.publication_id = pu.id	"
								+ "	LEFT join playlist_broadcastwrapper pb on pb.playlist_id = pl.id	"
								+ "	LEFT join broadcastwrapper bw on pb.broadcastwrappers_id = bw.id	"
								+ "	LEFT join broadcast b on bw.broadcast_id = b.id "
								+ "	where	"
								+ "	eu.email = :email	"
								+ "	and pu.dtype IN (:publicationTypes) "
								+ "	and pl.deleted = false 	"
								+ " and pl.publicationdate IS NULL"
								+ " AND b.id = :broadcastId OR b.parentid = :broadcastId "
								+ " AND b.deleted is false ");
		if(isExternalPlaylist) {
			queryString.append( " and pl.shared IS true"
								+ "	ORDER BY pub_name , playlist_name");
		}else {
			queryString.append( "	ORDER BY pub_name , playlist_name");
		}
																										
		SQLQuery query = session.createSQLQuery(queryString.toString());
		query.setString("email", emailAddress);
		query.setInteger("broadcastId", broadcastId);
		query.setParameterList("publicationTypes", publicationTypes);

		return query.list();
	
	}
	
	@Override
	public List<Object[]> getActiveChildBroadcastForPlaylist(int broadcastId,String emailAddress, List<String> publicationTypes) {
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String queryString = ("select DISTINCT pu.id as id,pu.name as pub_name ,pl.id as playlist_id, pl.name as playlist_name, case when b.id is null then false else true end as active, b.id as broadcastid, pl.state , b.name ,em.url  "
								+ "	from "
								+ "epublisheruser eu 	"
								+ "	INNER JOIN epublisheruser_publication epp ON eu.id = epp.epublisheruser_id	"
								+ "	inner join publication pu on pu.id = epp.availablepublications_id	"
								+ "	LEFT join playlist pl on pl.publication_id = pu.id	"
								+ "	LEFT join playlist_broadcastwrapper pb on pb.playlist_id = pl.id	"
								+ "	LEFT join broadcastwrapper bw on pb.broadcastwrappers_id = bw.id	"
								+ "	LEFT join broadcast b on bw.broadcast_id = b.id"
								+ " left join epublishermedia em on b.thumbnail_id = em.id "
								+ "	where	"
								+ "	eu.email = :email	"
								+ "	and pu.dtype IN (:publicationTypes) "
								+ "	and pl.deleted = false 	"
								+ "	and pl.publicationdate IS NULL"
								+ " AND b.parentid = :broadcastId "
								+ " AND b.deleted is false "
								+ "	ORDER BY pub_name , playlist_name");
																										
		SQLQuery query = session.createSQLQuery(queryString);
		query.setString("email", emailAddress);
		query.setInteger("broadcastId", broadcastId);
		query.setParameterList("publicationTypes", publicationTypes);

		return query.list();
	}
	


	@Override
	public List<Integer> getBroadcastChildrenIds(Integer parentId)
	{

		Query query;
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		query = session.createQuery("select id from Broadcast where parentId = :parentId and deleted is false order by datecreated");
		query.setParameter("parentId", parentId);
		
		@SuppressWarnings("unchecked")
		List<Integer> list = query.list();

		return list;
	}
	
	@Override
	public Integer getBroadcastChildIdFromPlaylist(Integer parentId, Integer playlistId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		StringBuilder queryBuilder = new StringBuilder("SELECT b.id FROM broadcast b " +
				"INNER JOIN broadcastwrapper bw ON b.id = bw.broadcast_id " +
				"INNER JOIN playlist_broadcastwrapper pb ON bw.id = pb.broadcastwrappers_id " +
				"INNER JOIN playlist p ON pb.playlist_id = p.id " +
				"WHERE b.parentid = :parentId and p.id = :playlistId"
				+ " AND b.deleted is false");
		
		SQLQuery query = session.createSQLQuery(queryBuilder.toString());
		
		query.setParameter("parentId", parentId);
		query.setParameter("playlistId", playlistId);

		return (Integer) query.uniqueResult();
	}
	
	@Override
	public TemplateNarrowcasting getTemplateById(Integer id) {
		return hibernateTemplate.get(TemplateNarrowcasting.class, id);
	}

	@Override
	public String getBroadcastThumbnail(Integer broadcastId) {
		Query query;
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		query = session.createSQLQuery("select em.url from Broadcast b left join  epublishermedia em on b.thumbnail_id = em.id  where b.id = :id and b.deleted is false");
		query.setParameter("id", broadcastId);
		
		return (String) query.uniqueResult();

	}

	@Override
	public List<BroadcastWrapper> getBroadcastWrapperList(Integer id) {
		Query query;
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		query = session.createQuery("  from BroadcastWrapper bw where bw.broadcast.id = :broadcastId or bw.broadcast.parentId = :broadcastId and bw.deleted is false");
		query.setParameter("broadcastId", id);
		
		@SuppressWarnings("unchecked")
		List<BroadcastWrapper> list = query.list();

		return list;
	
	}
	
	/**
	 * Retrieve all active playlists of screengroups, for a given publication.
	 */
	@Override
	public Integer countPublishedInternalBroadcasts()
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		
		String queryString = ("select DISTINCT b.name, bw.id as bwid, b.id as bid, pu.id as puid,pu.name as pub_name ,pl.id as playlist_id, pl.name as playlist_name, case when b.id is null then false else true end as active, b.parentid, pl.state   "
								+ "	FROM "
								+ "epublisheruser eu 	"
								+ "	INNER JOIN epublisheruser_publication epp ON eu.id = epp.epublisheruser_id	"
								+ "	inner join publication pu on pu.id = epp.availablepublications_id	"
								+ "	LEFT join playlist pl on pl.publication_id = pu.id	"
								+ "	LEFT join playlist_broadcastwrapper pb on pb.playlist_id = pl.id	"
								+ "	LEFT join broadcastwrapper bw on pb.broadcastwrappers_id = bw.id	"
								+ "	LEFT join broadcast b on bw.broadcast_id = b.id "
								+ "	where	"
								+ "	pl.deleted = false 	"
								+ "	AND pl.publicationdate IS NOT NULL"
								+ " AND b.deleted is false AND bw.advertisementid IS NULL"
								+ "	ORDER BY pub_name , playlist_name");
																										
		SQLQuery query = session.createSQLQuery(queryString);

		return query.list().size();
	}
	
	/**
	 * Retrieve all active playlists of screengroups, for a given publication.
	 */
	@Override
	public Integer countPublishedExternalBroadcasts()
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		
		String queryString = ("select DISTINCT b.name, bw.id as bwid, b.id as bid, pu.id as puid,pu.name as pub_name ,pl.id as playlist_id, pl.name as playlist_name, case when b.id is null then false else true end as active, b.parentid, pl.state   "
								+ "	FROM "
								+ "epublisheruser eu 	"
								+ "	INNER JOIN epublisheruser_publication epp ON eu.id = epp.epublisheruser_id	"
								+ "	inner join publication pu on pu.id = epp.availablepublications_id	"
								+ "	LEFT join playlist pl on pl.publication_id = pu.id	"
								+ "	LEFT join playlist_broadcastwrapper pb on pb.playlist_id = pl.id	"
								+ "	LEFT join broadcastwrapper bw on pb.broadcastwrappers_id = bw.id	"
								+ "	LEFT join broadcast b on bw.broadcast_id = b.id "
								+ "	where	"
								+ "	pl.deleted = false 	"
								+ "	AND pl.publicationdate IS NOT NULL"
								+ " AND b.deleted is false AND bw.advertisementid IS NOT NULL"
								+ "	ORDER BY pub_name , playlist_name");
																										
		SQLQuery query = session.createSQLQuery(queryString);

		return query.list().size();
	}

	
	@Override
	@Transactional
	public TemplateNarrowcasting getTemplateByName(String name) {
		Query query;
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		query = session.createQuery("  from TemplateNarrowcasting where name =:name");
		query.setParameter("name", name);
		
		return (TemplateNarrowcasting) query.uniqueResult();
		}

	@Override
	public Route getRouteById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
