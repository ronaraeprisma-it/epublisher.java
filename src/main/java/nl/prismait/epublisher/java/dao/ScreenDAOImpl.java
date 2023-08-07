package nl.prismait.epublisher.java.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import nl.prismait.epublisher.java.dao.sql.Sql;
import nl.prismait.epublisher.java.dao.util.SqlUtils;
import nl.prismait.epublisher.java.model.Publication;
import nl.prismait.epublisher.java.model.narrowcasting.Screen;
import nl.prismait.epublisher.java.model.narrowcasting.ScreenFilter;
import nl.prismait.epublisher.java.model.narrowcasting.ScreenGroup;
import nl.prismait.epublisher.java.util.EpublisherConstants;

@Component("screenDAO")
public class ScreenDAOImpl implements IScreenDAO
{

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional
	public Screen save(Screen screen)
	{
		hibernateTemplate.getSessionFactory().getCurrentSession().setHibernateFlushMode(FlushMode.AUTO);
		screen = hibernateTemplate.merge(screen);
		hibernateTemplate.flush();

		return screen;
	}

	@Override
	public ScreenGroup save(ScreenGroup screenGroup)
	{

		screenGroup = hibernateTemplate.merge(screenGroup);
		
		// set all screens with debug screen id option chosen
		List<Screen> screens = getAllScreensForGroup(screenGroup.getId());
		
		for (Screen s : screens) {
			s.setScreenIdDebug(screenGroup.isScreenIdDebug());
		}
		
		return screenGroup;
	}

	@Override
	public void delete(Screen screen)
	{
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		hibernateTemplate.delete(screen);
		hibernateTemplate.flush();

	}

	@Override
	public void delete(ScreenGroup screenGroup)
	{
		screenGroup= getScreenGroup(screenGroup.getId());
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		List<Screen> screens= screenGroup.getScreens();
		List<ScreenGroup> screensGroup= screenGroup.getScreenGroups();
		screens.clear();
		hibernateTemplate.flush();

		screenGroup= getScreenGroup(screenGroup.getId());
		hibernateTemplate.clear();
		hibernateTemplate.delete(screenGroup);
		hibernateTemplate.flush();
		
		hibernateTemplate.clear();
		screensGroup.clear();
		hibernateTemplate.flush();
		
		screenGroup= getScreenGroup(screenGroup.getId());
		
	}

	@Override
	public ScreenGroup getScreenGroup(Integer screenGroupId)
	{
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		return getScreenGroup(screenGroupId, false);
	}

	@Override
	public ScreenGroup getScreenGroup(Integer screenGroupId, Boolean eager)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Criteria criteria = session.createCriteria(ScreenGroup.class).add(Restrictions.idEq(screenGroupId)).setFetchMode("playFrequency", FetchMode.DEFAULT);

		if (eager)
			criteria.setFetchMode("publication", FetchMode.JOIN);

		ScreenGroup screengroup = (ScreenGroup) criteria.uniqueResult();

		return screengroup;
	}

	@Override
	public Screen getScreen(Integer screenId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Screen where id = :screenId");
		query.setInteger("screenId", screenId);
		Screen screen = (Screen) query.uniqueResult();

		return screen;
	}

	@Override
	public Screen getScreenByDisplayId(String screenId)
	{

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		System.out.println(session == null);
		Query query = session.createQuery("from Screen where displayId = :screenId");
		query.setParameter("screenId", screenId);

		Screen screen = (Screen) query.uniqueResult();

		return screen;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ScreenGroup> getScreenGroups(ScreenFilter filter)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		/*
		 * This query is constructed using a large first query with placeholders (ALL_SCREENGROUPS_TOP_DOWN_QUERY)
		 * The placeholders are:
		 * {0} = filter condition
		 */
		String sqlQuery = SqlUtils.getQuery(
				Sql.ALL_SCREENGROUPS_TOP_DOWN_QUERY
				, buildFilterCondition(filter));

		@SuppressWarnings("rawtypes")
		Query query = session.createNativeQuery(sqlQuery);
		setQueryParamsFromFilter(query, filter);
		
		List<Object[]> queryResult = query.list();

		List<ScreenGroup> result = new ArrayList<ScreenGroup>();

		for (Object[] screengroup : queryResult)
		{
			ScreenGroup fetchedScreengroup = getScreenGroup((Integer) screengroup[0], false);
			fetchedScreengroup.setIndent((Integer) screengroup[5]);
			
			if (fetchedScreengroup != null)
			{
				result.add(fetchedScreengroup);
			}
		}

		return result;
	}

	private String buildFilterCondition(ScreenFilter filter)
	{
		StringBuilder builder = new StringBuilder();
		
		if (filter.getScreenGroupKeyword() != null && filter.getScreenKeyword() != null)
		{
			builder.append(" LEFT OUTER JOIN screen ss ON sgs.id = ss.screengroup_id");
			builder.append(" WHERE sgs.name ilike :groupKeyword");
			builder.append(" OR sgs.description ilike :groupKeyword ");
			builder.append(" OR ss.name ilike :keyword ");
			builder.append(" OR ss.description ilike :keyword ");
			builder.append(" OR ss.displayid ilike :keyword ");
			builder.append(" OR ss.displayname ilike :keyword ");
			builder.append(" OR ss.location ilike :keyword ");
			builder.append(" OR ss.locationcode ilike :keyword");
		}
		else if (filter.getScreenGroupKeyword() != null)
		{
			builder.append(" WHERE sgs.name ilike :groupKeyword");
			builder.append(" OR sgs.description ilike :groupKeyword ");
		}
		else if (filter.getScreenKeyword() != null)
		{
			builder.append(" LEFT OUTER JOIN screen ss ON sgs.id = ss.screengroup_id");
			builder.append(" WHERE ss.name ilike :keyword ");
			builder.append(" OR ss.description ilike :keyword ");
			builder.append(" OR ss.displayid ilike :keyword ");
			builder.append(" OR ss.displayname ilike :keyword ");
			builder.append(" OR ss.location ilike :keyword ");
			builder.append(" OR ss.locationcode ilike :keyword");
		}
		
		return builder.toString();
	}

	private void setQueryParamsFromFilter(Query query, ScreenFilter filter)
	{
		if (filter.getScreenGroupKeyword() != null)
		{
			query.setParameter("groupKeyword", "%" + filter.getScreenGroupKeyword() + "%");
		}

		if (filter.getScreenKeyword() != null)
		{
			query.setParameter("keyword", "%" + filter.getScreenKeyword() + "%");
		}
	}

	@Override
	public void updateLastRequest(String displayId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String query = "UPDATE screen SET datetimelastrequest = current_timestamp WHERE displayid = :displayId";

		session.createSQLQuery(query).setString("displayId", displayId).executeUpdate();
	}

	@Override
	@Transactional
	public void removePublicationFromScreenGroups(Publication publication)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String query = "UPDATE screengroup SET publication_id = NULL WHERE publication_id = :publicationId";

		session.createSQLQuery(query).setInteger("publicationId", publication.getId()).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getScreenDataSet(Integer parentId, Integer publicationId) {
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String sqlQuery = SqlUtils.getQuery(Sql.GET_DATASET_FOR_SCREEN_TREE 
				,parentId == -1 ? EpublisherConstants.IS_NULL : " = :parentId"
				,publicationId == -1 ?   EpublisherConstants.IS_NULL : " = :publicationId"
				,parentId == -1 ?  EpublisherConstants.IS_NULL : " = :parentId" );
		
		SQLQuery queryStr= session.createSQLQuery(sqlQuery);
		if(parentId != -1)
		{
			queryStr.setParameter("parentId",parentId );
		}
		if(publicationId != -1)
		{
			queryStr.setInteger("publicationId", publicationId);
		}
		queryStr.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return queryStr.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllScreenGroupsTable() {
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String sqlQuery = SqlUtils.getQuery(Sql.GET_ALL_SCREENGROUPS );
		
		SQLQuery queryStr= session.createSQLQuery(sqlQuery);
		queryStr.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return queryStr.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Screen> getAllScreensForGroup(Integer screenGroupId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query queryResult = session.createQuery("from Screen where screengroup_id = :screenGroupId order by name");
		queryResult.setInteger("screenGroupId", screenGroupId);
		return queryResult.list();
	}
	
	@Override
	public List<ScreenGroup> getScreenGroupList(List<Object> screenGroupId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from ScreenGroup where id IN (:screenGroupId)");
		query.setParameterList("screenGroupId", screenGroupId,IntegerType.INSTANCE);
		return query.list();
	}

	@Override
	@Transactional
	public void saveAll(List<ScreenGroup> screenGroup) {
		hibernateTemplate.getSessionFactory().getCurrentSession().setHibernateFlushMode(FlushMode.AUTO);
		for (Iterator<ScreenGroup> it = screenGroup.iterator(); it.hasNext();)
		{
			hibernateTemplate.saveOrUpdate(it.next());
		}
		hibernateTemplate.flush();
	}
	
	
	@Override
	public Publication getPublicationById(Integer publicationId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Publication where id = :publicationId");
		query.setInteger("publicationId", publicationId);
		return (Publication) query.uniqueResult();
	}

	@Override
	public List<ScreenGroup> getAllChildScreenGroups(Integer id) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from ScreenGroup where screengroup_id = :id");
		query.setInteger("id", id);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ScreenGroup> getAllScreensGroupsInPublication(Integer publicationId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query queryResult = session.createQuery("from ScreenGroup where publication_id = :publicationId order by name");
		queryResult.setInteger("publicationId", publicationId);
		return queryResult.list();
	}
}
