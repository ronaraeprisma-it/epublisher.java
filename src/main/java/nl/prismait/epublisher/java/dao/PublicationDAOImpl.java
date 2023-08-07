package nl.prismait.epublisher.java.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import nl.prismait.epublisher.java.model.narrowcasting.PlayTime;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import nl.prismait.epublisher.java.dao.sql.Sql;
import nl.prismait.epublisher.java.dao.util.SqlUtils;
//import nl.prismait.epublisher.java.model.ArticleWrapper;
//import nl.prismait.epublisher.java.model.AudienceCDP;
import nl.prismait.epublisher.java.model.Edition;
import nl.prismait.epublisher.java.model.EditionWrapper;
import nl.prismait.epublisher.java.model.OutputChannel;
import nl.prismait.epublisher.java.model.Publication;
import nl.prismait.epublisher.java.model.SchedulePublish;
//import nl.prismait.epublisher.java.model.User;
//import nl.prismait.epublisher.java.model.dto.ArticleArchiveDTO;
import nl.prismait.epublisher.java.model.dto.PublicationSummaryDTO;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.Playlist;
import nl.prismait.epublisher.java.model.narrowcasting.PublicationNarrowcastingNS;
//import nl.prismait.epublisher.java.model.nsnewsletter.EditionNewsletterNS;
import nl.prismait.epublisher.java.model.nstreinen.PublicationNSTreinen;
//import nl.prismait.epublisher.java.model.portalpage.PublicationPortalPage;
import nl.prismait.epublisher.java.model.wayfinder.PublicationWayfinder;

@Component("publicationDAO")
public class PublicationDAOImpl implements IPublicationDAO
{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private IPlaylistDAO playlistDAO;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional
	public Publication save(Publication publication)
	{
		publication = hibernateTemplate.merge(publication);

		if (publication instanceof PublicationNarrowcastingNS || publication instanceof PublicationNSTreinen || publication instanceof PublicationWayfinder)
		{
			hibernateTemplate.flush();
			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
			
			session.createSQLQuery(Sql.CORRECT_PLAYLIST_PRIORITIES_TO_NEW_PRIORITY).setParameter("publicationid", publication.getId()).executeUpdate();
		}

		return publication;
	}

	@Override
	public OutputChannel save(OutputChannel outputChannel)
	{
		hibernateTemplate.saveOrUpdate(outputChannel);

		return outputChannel;
	}

	@Override
	@Transactional
	public Edition save(Edition edition)
	{
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		edition.setLastUpdated(new Date());

		hibernateTemplate.flush();
		hibernateTemplate.clear();
		
		hibernateTemplate.saveOrUpdate(edition);

		hibernateTemplate.flush();
		hibernateTemplate.clear();
		
		return edition;
	}

	@Override
	@Transactional
	public void delete(Publication publication) throws EpublisherException
	{
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		
		deletePublicationFromUserList(publication);

		hibernateTemplate.flush();

		hibernateTemplate.clear();

		publication.setDeleted(true);
		publication.setDeletedDateTime(new Date());

		hibernateTemplate.saveOrUpdate(publication);
		hibernateTemplate.flush();

		hibernateTemplate.clear();

	}

	private void deletePublicationFromUserList(Publication publication)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query querySql = session.createSQLQuery("Select epublisheruser_id from epublisheruser_publication where availablepublications_id = :id ");
		querySql.setParameter("id",publication.getId().intValue());
		
		@SuppressWarnings("unchecked")
		List<Integer> usersId = (List<Integer>)querySql.list();
		if(!usersId.isEmpty())
		{
//			Query query = session.createQuery("from User where id IN (:id)");
//			query.setParameterList("id",usersId ,IntegerType.INSTANCE );
			
//			@SuppressWarnings("unchecked")
//			List<User> users = query.list();
//
//			for (User user : users)
//			{
//				for (Publication userPublication : user.getAvailablePublications())
//				{
//					if (userPublication.getId().intValue() == publication.getId().intValue())
//					{
//						user.getAvailablePublications().remove(userPublication);
//						hibernateTemplate.merge(user);
//						break;
//					}
//				}
//			}
		}
	}

	@Override
	public void delete(Edition edition)
	{
		hibernateTemplate.delete(edition);
		hibernateTemplate.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Publication> findAll()
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query queryResult = session.createQuery("from Publication where deleted = :deleted order by name");
		queryResult.setBoolean("deleted", false);
		return queryResult.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PublicationSummaryDTO> findAllSummaries()
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query queryResult = session.createQuery("SELECT pub.id as id,pub.name as name, pub.outputChannel as channel "
				+ "from Publication pub "
				+ " where pub.deleted = :deleted order by name");
		queryResult.setBoolean("deleted", false);
		queryResult.setResultTransformer( Transformers.aliasToBean(PublicationSummaryDTO.class) );
		return queryResult.list();
	}

	@Override
	public Publication getPublicationById(Integer publicationId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Object pub = session.getReference(Publication.class, publicationId);
		if (!Hibernate.isInitialized(pub))
			Hibernate.initialize(pub);

		return (Publication) ((pub instanceof HibernateProxy)
				? session.unwrap(SessionImplementor.class).getPersistenceContext().unproxy(pub)
				: pub);
	}
	
	
	@Override
	public void getPublicationByApiId(Integer apiId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//		Query query = session.createQuery("from Publication where apiid = :apiId");
//		query.setInteger("apiId", apiId);
//		Publication publication = (Publication) query.uniqueResult();
		
//		return publication;
		return;
	}

	@Override
	public Publication getPublicationByName(String publicationName)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Publication where name = :publicationName");
		query.setString("publicationName", publicationName);
		Publication publication = (Publication) query.uniqueResult();

		return publication;
	}
	
	@Override
	public Publication getPublicationByNameAndOutputChannel(String publicationName, String outputchannelName)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Publication where name = :publicationName");
		query.setString("publicationName", publicationName);
		List<Publication> publicationList = (List<Publication>) query.getResultList();

		for(Publication pub : publicationList) {
			if(pub.getOutputChannel().getName().equals(outputchannelName)) {
				return pub;
			}
		}
			
		return null;
	}

	@Override
	@Transactional
	public Edition getEditionById(Integer editionId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Edition where id = :editionId");
		query.setInteger("editionId", editionId);
		Edition edition = (Edition) query.uniqueResult();

		return edition;
	}

	@Override
	public Edition getFullEditionById(Integer editionId) {
		return null;
	}

	@Override
	public Edition getFullEditionById(Integer editionId, Boolean evict) {
		return null;
	}

	@Override
	public void getAllEditionsForPublication(Integer publicationId) {

	}

//	@Override
//	@Transactional
//	public Edition getFullEditionById(Integer editionId)
//	{
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		//Query query = session.createQuery("from Edition e left join fetch e.articlewrappers aw left join fetch aw.article where e.id = :editionId");
		// left join fetch e.articlewrappers aw left join fetch aw.article
	//	query.setInteger("editionId", editionId);
		
		
//		Criteria secondCriteria = session.createCriteria(Edition.class)
//				.add(Restrictions.eq("id", editionId));
//		Edition currentEdition = (Edition) secondCriteria.uniqueResult();

//		Query query = session.createQuery("from ArticleWrapper aw left join fetch aw.article a where aw.edition.id = :editionId order by aw.id desc");
//		query.setInteger("editionId", currentEdition.getId());
//		List<ArticleWrapper> wrapperList= query.list();
//		currentEdition.setArticlewrappers(wrapperList);
		
		//Edition edition = (Edition) query.uniqueResult();

//		return currentEdition;
//	}

//	@Override
//	public Edition getFullEditionById(Integer editionId, Boolean evict)
//	{
//		Edition edition = getFullEditionById(editionId);
//
//		if (evict)
//		{
//			hibernateTemplate.evict(edition);
//		}
//
//		return edition;
//	}

	
	/**@Override
	public Edition getCurrentEditionForPublication(Integer	publicationId,Integer subEditionId) { 
		Edition currentEdition = null;

			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

			Publication publication = getPublicationById(publicationId);

			Criteria criteria =	session.createCriteria(Edition.class)
					.setProjection(Projections.max("lastUpdated"))
					.add(Restrictions.eq("publication", publication))
					.add(Restrictions.isNull("publicationDate")) 
					.add(Restrictions.eq("deleted",false))
					.setFetchMode("articleWrapper", FetchMode.JOIN);

			if(subEditionId != null && subEditionId != -1 ) {
				criteria.add(Restrictions.eq("id",subEditionId)); }else {
					criteria.add(Restrictions.isNull("parentEdition")); }

			@SuppressWarnings("unchecked") List<Timestamp> list = criteria.list();
			Timestamp latestTimeStamp = null;

			for (Timestamp timestamp : list) { if (timestamp != null) { if
				(latestTimeStamp == null || timestamp.after(latestTimeStamp)) latestTimeStamp
				= timestamp; } }

			if (latestTimeStamp != null) {
				Criteria secondCriteria =session.createCriteria(Edition.class)
						.add(Restrictions.eq("lastUpdated",	latestTimeStamp))
						.add(Restrictions.eq("publication", publication))
						.add(Restrictions.isNull("publicationDate"))
						.add(Restrictions.eq("deleted",	false));
				
			if(subEditionId != null && subEditionId != -1 ) {
				secondCriteria.add(Restrictions.eq("id",subEditionId)); 
				}
			else {
				secondCriteria.add(Restrictions.isNull("parentEdition"));
				}
			currentEdition =(Edition) secondCriteria.uniqueResult(); 
			if(currentEdition.getPublication() instanceof HibernateProxy) {
				session.unwrap(SessionImplementor.class).getPersistenceContext().unproxy(currentEdition.getPublication()); 
					}
			}
			else { // return new edition
				currentEdition = publication != null ? publication.retrieveNewEdition() :currentEdition;
				if (currentEdition != null) {
					currentEdition.setEditionNumber(1);
				} 
			}

			return currentEdition; 
		}
	 **/
	
	@Override
	public Edition getCurrentEditionForPublication(Integer publicationId,Integer subEditionId)
	{
		Edition currentEdition = null;

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
 
		Publication publication = getPublicationById(publicationId);
//		case for portalpage edition
//		if(publication instanceof PublicationPortalPage) {
//			return getCurrentEditionForPublicationPortalPage(publication);
//		}
		
		StringBuilder queryString =new StringBuilder("select max(lastUpdated) from Edition where publication.id = :publicationid AND deleted = false and publicationDate is null ");

		
		if(subEditionId != null && subEditionId != -1 ) {
			queryString.append(" and id = :subEditionId");
		}else  {
			queryString.append(" and parentEdition is null");
		}
		
		Query query = session.createQuery(queryString.toString());
		query.setParameter("publicationid", publicationId);
		if(subEditionId != null && subEditionId != -1 )
			query.setParameter("subEditionId", subEditionId);
		
		

		@SuppressWarnings("unchecked")
		Timestamp timestamp = (Timestamp) query.uniqueResult();
		Timestamp latestTimeStamp = null;

			if (timestamp != null)
			{
				if (latestTimeStamp == null || timestamp.after(latestTimeStamp))
					latestTimeStamp = timestamp;
			}

		if (latestTimeStamp != null)
		{
			StringBuilder queryString1 =new StringBuilder("from Edition e left join fetch e.articlewrappers aw left join fetch aw.article where e.publication.id = :publicationid AND e.deleted = false and e.publicationDate is null and e.lastUpdated = :latestTimeStamp ");
			
			if(subEditionId != null && subEditionId != -1 ) {
				queryString1.append(" and e.id = :subEditionId");
			}else  {
				queryString1.append(" and e.parentEdition is null");
			}
			
			Query query1 = session.createQuery(queryString1.toString());
			query1.setParameter("publicationid", publicationId);
			query1.setParameter("latestTimeStamp", latestTimeStamp);
			if(subEditionId != null && subEditionId != -1 )
				query1.setParameter("subEditionId", subEditionId);
			
			currentEdition= (Edition) query1.uniqueResult();
			if(currentEdition != null && currentEdition.getPublication() instanceof HibernateProxy)
			{
				session.unwrap(SessionImplementor.class).getPersistenceContext().unproxy(currentEdition.getPublication());
			}
		}
		else
		{
			
			// return new edition
			currentEdition = publication != null ? publication.retrieveNewEdition() : currentEdition;
			if (currentEdition != null) {
				currentEdition.setEditionNumber(1);
			}
		}
		// SAAS-1234
		if (currentEdition != null && currentEdition.getPublication() != null
				&& currentEdition.getPublication() instanceof HibernateProxy) {
			currentEdition.setPublication(publication);
		}
		
//		if(currentEdition.getArticlewrappers() != null) {
//			for(ArticleWrapper wrapper : currentEdition.getArticlewrappers()) {
//				if(wrapper.getArticle() != null && wrapper.getArticle().getPublication() != null && wrapper.getArticle().getPublication() instanceof HibernateProxy)
//				{
//					wrapper.getArticle().setPublication(publication);
//				}
//			}
//		}
		
		return currentEdition;
	}
	
	@Override
	public Edition getCurrentEditionForPublicationPortalPage(Publication publication)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Edition currentEdition = null;
		StringBuilder queryString =new StringBuilder("select max(lastUpdated) from Edition where publication.id = :publicationid AND deleted = false ");
	
		Query query = session.createQuery(queryString.toString());
		query.setParameter("publicationid", publication.getId());
		
		@SuppressWarnings("unchecked")
		Timestamp timestamp = (Timestamp) query.uniqueResult();
		Timestamp latestTimeStamp = null;

			if (timestamp != null)
			{
				if (latestTimeStamp == null || timestamp.after(latestTimeStamp))
					latestTimeStamp = timestamp;
			}

		if (latestTimeStamp != null)
		{
			StringBuilder queryString1 =new StringBuilder("from Edition e left join fetch e.articlewrappers aw left join fetch aw.article where e.publication.id = :publicationid and e.lastUpdated = :latestTimeStamp ");
			
			Query query1 = session.createQuery(queryString1.toString());
			query1.setParameter("publicationid", publication.getId());
			query1.setParameter("latestTimeStamp", latestTimeStamp);
			currentEdition=(Edition) query1.uniqueResult();
		}else {
			currentEdition = publication != null ? publication.retrieveNewEdition() : currentEdition;
		}

		if (currentEdition != null) {
			currentEdition.setEditionNumber(1);
		}		
		
		if (currentEdition != null && currentEdition.getPublication() != null
				&& currentEdition.getPublication() instanceof HibernateProxy) {
			currentEdition.setPublication(publication);
		}
		
//		currentEdition.getArticlewrappers().sort(Comparator.comparing(ArticleWrapper::getOrderOfArticle));
//
//		if(currentEdition.getArticlewrappers() != null) {
//			for(ArticleWrapper wrapper : currentEdition.getArticlewrappers()) {
//				if(wrapper.getArticle() != null && wrapper.getArticle().getPublication() != null && wrapper.getArticle().getPublication() instanceof HibernateProxy)
//				{
//					wrapper.getArticle().setPublication(publication);
//				}
//			}
//		}
		
		return currentEdition;

	}
	
	@Override
	public Edition getCurrentEditionForPublicationWithPagination(Integer publicationId,Integer startPage, Integer maxResult)
	{
		Edition currentEdition = null;

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Publication publication = getPublicationById(publicationId);

		Criteria criteria = session.createCriteria(Edition.class).setProjection(Projections.max("lastUpdated"))
				.add(Restrictions.eq("publication", publication))
				.add(Restrictions.isNull("publicationDate"))
				.add(Restrictions.eq("deleted", false));

		@SuppressWarnings("unchecked")
		List<Timestamp> list = criteria.list();
		Timestamp latestTimeStamp = null;

		for (Timestamp timestamp : list)
		{
			if (timestamp != null)
			{
				if (latestTimeStamp == null || timestamp.after(latestTimeStamp))
					latestTimeStamp = timestamp;
			}
		}

		if (latestTimeStamp != null)
		{
			Criteria secondCriteria = session.createCriteria(Edition.class)
					.add(Restrictions.eq("lastUpdated", latestTimeStamp))
					.add(Restrictions.eq("publication", publication))
					.add(Restrictions.isNull("publicationDate"))
					.add(Restrictions.eq("deleted", false));
			currentEdition = (Edition) secondCriteria.uniqueResult();

//			Query query = session.createQuery("from ArticleWrapper where edition_id = :editionId order by orderOfArticle desc");
//			query.setInteger("editionId", currentEdition.getId());
//			query.setFirstResult(startPage*maxResult);
//			query.setMaxResults(maxResult);
//			List<ArticleWrapper> wrapperList= query.list();
//			currentEdition.setArticlewrappers(wrapperList);
			
//			currentEdition.setTotalNumberOfArticleWrappers(getTotalNumberOfArticleWrapper(currentEdition.getId()).intValue());
			if (!Hibernate.isInitialized(currentEdition.getPublication()))
				Hibernate.initialize(currentEdition.getPublication());
			if(currentEdition != null && currentEdition.getPublication() instanceof HibernateProxy)
			{
				currentEdition.setPublication(publication);
				//session.unwrap(SessionImplementor.class).getPersistenceContext().unproxy(currentEdition.getPublication());
			}
			
		}
		else
		{
			// return new edition
			currentEdition = publication != null ? publication.retrieveNewEdition() : currentEdition;
			if (currentEdition != null) {
				currentEdition.setEditionNumber(1);
			}
		}

		return currentEdition;
	}

	@Override
	public void getCurrentScheduledEdition() {

	}

	@Override
	public Edition getCurrentEditionForPublication(Integer publicationId, boolean loadAllArticles)
	{
		Edition currentEdition = null;

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Publication publication = getPublicationById(publicationId);

		Criteria criteria = session.createCriteria(Edition.class).setProjection(Projections.max("lastUpdated"))
				.add(Restrictions.eq("publication", publication))
				.add(Restrictions.isNull("publicationDate"))
				.add(Restrictions.eq("deleted", false));

		@SuppressWarnings("unchecked")
		List<Timestamp> list = criteria.list();
		Timestamp latestTimeStamp = null;

		for (Timestamp timestamp : list)
		{
			if (timestamp != null)
			{
				if (latestTimeStamp == null || timestamp.after(latestTimeStamp))
					latestTimeStamp = timestamp;
			}
		}

		if (latestTimeStamp != null)
		{
			Criteria secondCriteria = session.createCriteria(Edition.class)
					.add(Restrictions.eq("lastUpdated", latestTimeStamp))
					.add(Restrictions.eq("publication", publication))
					.add(Restrictions.isNull("publicationDate"))
					.add(Restrictions.eq("deleted", false))
					.setFetchMode("articleWrapper", FetchMode.JOIN)
					.createAlias("articlewrappers", "aw");
			currentEdition = (Edition) secondCriteria.uniqueResult();
		}
		else
		{
			// return new edition
			currentEdition = publication.retrieveNewEdition();
			currentEdition.setEditionNumber(1);
			currentEdition = hibernateTemplate.merge(currentEdition);
			hibernateTemplate.evict(currentEdition);
		}

		return currentEdition;
	}

	@Override
	@Transactional
	public Edition getCurrentEditionForPublicationLazy(Integer publicationId,Integer subEditionId)
	{
		Edition currentEdition = null;

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Publication publication = getPublicationById(publicationId);

		Criteria criteria = session.createCriteria(Edition.class).setProjection(Projections.max("lastUpdated"))
				.add(Restrictions.eq("publication", publication))
				.add(Restrictions.isNull("parentEdition"))
				.add(Restrictions.isNull("publicationDate"))
				.add(Restrictions.eq("deleted", false));
		if(subEditionId != null && subEditionId != -1 ) {
			criteria.add(Restrictions.eq("id",subEditionId));
		}else  {
			criteria.add(Restrictions.isNull("parentEdition"));
		}
		

		@SuppressWarnings("unchecked")
		List<Timestamp> list = criteria.list();
		Timestamp latestTimeStamp = null;

		for (Timestamp timestamp : list)
		{
			if (timestamp != null)
			{
				if (latestTimeStamp == null || timestamp.after(latestTimeStamp))
					latestTimeStamp = timestamp;
			}
		}

		if (latestTimeStamp != null)
		{
			Criteria secondCriteria = session.createCriteria(Edition.class)
					.add(Restrictions.eq("lastUpdated", latestTimeStamp))
					.add(Restrictions.eq("publication", publication))
					.add(Restrictions.isNull("parentEdition"))
					.add(Restrictions.isNull("publicationDate"))
					.add(Restrictions.eq("deleted", false));
			if(subEditionId != null && subEditionId != -1 ) {
				secondCriteria.add(Restrictions.eq("id",subEditionId));
			}else  {
				secondCriteria.add(Restrictions.isNull("parentEdition"));
			}
			currentEdition = (Edition) secondCriteria.uniqueResult();
		}
		else
		{
			// return new edition
			currentEdition = publication.retrieveNewEdition();
			currentEdition.setEditionNumber(1);
			currentEdition = save(currentEdition);
		}

		return currentEdition;
	}
	@Override
	@Transactional(readOnly=true)
	public Edition lastPublishedEditionForPublication(Integer publicationId)
	{
		Publication publication = getPublicationById(publicationId);
		return lastPublishedEditionForPublication(publication);
	}

	@Override
	public Edition lastPublishedEditionForPublication(Publication publication)
	{
		Edition lastPublishedEdition = null;

		Session session = null;
		try {
		    session = hibernateTemplate.getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
		    session = hibernateTemplate.getSessionFactory().openSession();
		}


		Criteria criteria = session.createCriteria(Edition.class).setProjection(Projections.max("publicationDate")).add(Restrictions.eq("publication", publication))
				.add(Restrictions.isNull("parentEdition")).add(Restrictions.eq("deleted", false));

		@SuppressWarnings("unchecked")
		List<Timestamp> list = criteria.list();
		Timestamp latestTimeStamp = null;

		for (Timestamp timestamp : list)
		{
			if (timestamp != null)
			{
				if (latestTimeStamp == null || timestamp.after(latestTimeStamp))
					latestTimeStamp = timestamp;
			}
		}

		if (latestTimeStamp != null)
		{
			Criteria secondCriteria = session.createCriteria(Edition.class)
					.add(Restrictions.eq("publicationDate", latestTimeStamp))
					.add(Restrictions.eq("publication", publication))
					.add(Restrictions.isNull("parentEdition"))
					.add(Restrictions.eq("deleted", false));

			lastPublishedEdition = (Edition) secondCriteria.uniqueResult();
		}

		return lastPublishedEdition;
	}
	

	@Override
	@Transactional
	public List<String> lastPublishedEditionArticleIdsForPublication(Integer publicationId)
	{
		Publication publication = getPublicationById(publicationId);
		return lastPublishedEditionArticleIdsForPublication(publication);
	}

	@Override
	public List<String> lastPublishedEditionArticleIdsForPublication(Publication publication)
	{
		final List<String> lastPublishedEditionArticleIds = new ArrayList<String>();

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Criteria criteria = session.createCriteria(Edition.class).setProjection(Projections.max("publicationDate")).add(Restrictions.eq("publication", publication)).add(Restrictions.eq("deleted", false));

		@SuppressWarnings("unchecked")
		List<Timestamp> list = criteria.list();
		Timestamp latestTimeStamp = null;

		for (Timestamp timestamp : list)
		{
			if (timestamp != null)
			{
				if (latestTimeStamp == null || timestamp.after(latestTimeStamp))
					latestTimeStamp = timestamp;
			}
		}

		if (latestTimeStamp != null)
		{
			Criteria secondCriteria = session.createCriteria(Edition.class)
					.add(Restrictions.eq("publicationDate", latestTimeStamp))
					.add(Restrictions.eq("publication", publication))
					.add(Restrictions.eq("deleted", false));

//			((Edition)secondCriteria.uniqueResult()).getArticlewrappers().forEach(a -> { if (a.getArticle() != null && a.getArticle().getArticleId() != null) { lastPublishedEditionArticleIds.add(a.getArticle().getArticleId()); } });
		}

		return lastPublishedEditionArticleIds;
	}

//	@Override
//	public List<Edition> getAllEditionsForPublication(Integer publicationId)
//	{
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//
//		Query query = session.createQuery("from Edition where publication.id = :publicationid and deleted = false and publicationDate IS NOT NULL order by editionnumber");
//		query.setInteger("publicationid", publicationId);
//
//		@SuppressWarnings("unchecked")
//		List<Edition> editions = query.list();
//
//		return editions;
//	}
	
	@Override
	public List<Object[]> getAllArticlesForEditionSummaryId(Integer publicationId) 
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query query = session.createSQLQuery("SELECT e.id AS id, e.editionnumber AS editionNumber, e.name AS name,"
				+" e.publicationDate AS publicationDate, a.id AS articleId, a.title AS articleTitle, awrapper.id AS wrapperId, awrapper.tinyUrl as tinyurl "
				+" FROM edition e LEFT OUTER JOIN articlewrapper awrapper ON awrapper.edition_id = e.id"
				+" LEFT OUTER JOIN article a ON a.id = awrapper.article_id "
				+" WHERE e.publication_id = :publicationId AND e.deleted = false"
				+" AND e.publicationDate IS NOT NULL ORDER BY e.editionnumber,  awrapper.orderofarticle desc");
		query.setInteger("publicationId", publicationId);
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.list();
		return results;
	}
	
	@Override
	public List<Object[]> getAllArticlesForScheduledEditionSummaryId(Integer publicationId) 
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();


		Query query = session.createSQLQuery("SELECT e.id AS id, e.editionnumber AS editionNumber, e.name AS name,"
				+" e.publicationDate AS publicationDate, a.id AS articleId, a.title AS articleTitle , sp.status AS status , sp.cancelled AS cancelled,"
				+ " sp.scheduledDate , sp.scheduledHour, sp.scheduledMinute , e.parentedition_id "
				+" FROM schedulePublish sp INNER JOIN edition e on sp.edition_id = e.id LEFT OUTER JOIN articlewrapper awrapper ON awrapper.edition_id = e.id"
				+" LEFT OUTER JOIN article a ON a.id = awrapper.article_id "
				+" WHERE e.publication_id = :publicationId  AND sp.status != 'COMPLETED'  AND e.deleted = false"
				+ " and  sp.edition_id not in (select e.parentedition_id from edition e inner join schedulepublish sp on sp.edition_id = e.id where sp.status != 'COMPLETED' and e.parentedition_id is not null ) "
				+"  ORDER BY e.id desc");
		query.setParameter("publicationId", publicationId);
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.list();
		return results;
	}

	@Override
	public void getScheduledEditionByEditionId(Integer editionId) {

	}

	@Override
	public List getAllPublicationsForUser(Integer id) {
		return null;
	}

	@Override
	public List<Edition> getFullSubEdition(Edition edition) {
		return null;
	}

	@Override
	public OutputChannel getOutputChannelInstance(Class<?> outputChannelClass)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query query = session.createQuery("from OutputChannel outputChannel where outputChannel.class = :outputchannelClass");

		String name = outputChannelClass.getSimpleName();

		query.setParameter("outputchannelClass", name);

		OutputChannel outputChannel = (OutputChannel) query.uniqueResult();

		return outputChannel;
	}

	@Override
	@Transactional
	public List<Publication> getAllPublicationsOfType(String publicationType)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.flush();

		session.clear();
		StringBuilder queryString = new StringBuilder("Select distinct publication from Publication publication ");

		if(publicationType.equals("PublicationNarrowcastingNS") || publicationType.equals("PublicationNSTreinen") || publicationType.equals("PublicationWayfinder")) {
			queryString.append( " LEFT JOIN FETCH  publication.disabledBroadcast LEFT JOIN FETCH publication.playlistWrapper where dtype = :publicationClass and publication.deleted = :deleted order by publication.name"); 
		}else {
			queryString.append( " where dtype = :publicationClass and publication.deleted = :deleted order by publication.name");
		}
		Query query = session.createQuery(queryString.toString());

		query.setParameter("deleted", false);

		query.setParameter("publicationClass", publicationType);
		@SuppressWarnings("unchecked")
		List<Publication> publications = query.list();
		/*final List<Publication> publications = new LinkedList<>();
		for(final Object o : query.list()) {
			logger.info("Pub name"+ o.getClass());
			publications.add((Publication)o);
		}
*/
		session.flush();

		session.clear();

		return publications;
	}

	@Override
	public long getTotalNumberOfNewsletterPublications()
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//		Query query = session.createQuery("select count(*) from Publication publication where publication.class = PublicationNewsletterNS and deleted = :deleted");
//		query.setBoolean("deleted", false);
//		long totalNumberOfNewsletterPublications = (Long) query.uniqueResult();

//		return totalNumberOfNewsletterPublications;
		return 0;
	}

	@Override
	public long getTotalNumberOfNewslettersSent()
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//		Query query = session.createQuery("select sum(totalNumberOfSentMails) from Edition edition where edition.class = EditionNewsletterNS");
		long totalNumberOfNewslettersSent = 0;

//		if (query.uniqueResult() != null)
//		{
//			totalNumberOfNewslettersSent = (Long) query.uniqueResult();
//		}

		return totalNumberOfNewslettersSent;
	}

	
//	@Override
//	public Edition getEditionForAudienceList(Set<AudienceCDP> audienceList, Publication publication, Integer editionId) {
//		boolean isExist = false;
//		Edition subEdition = null;
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//		Query query = session.createQuery("from Edition e join fetch e.publication pub join fetch e.parentEdition pe"
//				+ " where  pub.id = :publicationId and e.publicationDate is null and (pe.id = :editionId)");
//		// left join fetch e.articlewrappers aw left join fetch aw.article
//		query.setInteger("publicationId", publication.getId());
//		query.setInteger("editionId", editionId);
//		List<EditionNewsletterNS> editionList =  query.list();
//		if(!editionList.isEmpty()) {
//			for(EditionNewsletterNS edition:editionList ) {
//				if(edition.getAudience().size() == audienceList.size()) {
//					for(AudienceCDP audience: edition.getAudience()) {
//						if(!audienceList.contains(audience)) {
//							isExist = false;
//							break;
//						}else {
//							isExist = true;
//						}
//					}
//				}
//				if(isExist) {
//					subEdition = edition;
//					break;
//				}
//
//			}
//		}
//		return subEdition;
//	}


	@Override
	@Transactional
	public Edition getLastPublishedEditionByPublicationName(String publicationName)
	{
		Edition lastPublishedEdition = null;

		Publication publication = getPublicationByName(publicationName);

		lastPublishedEdition = lastPublishedEditionForPublication(publication.getId());

		return lastPublishedEdition;
	}

	@Override
	public List<Edition> getEditionsByYear(Integer publicationId, Integer year)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query query = session.createQuery("from Edition where publication.id = :publicationid AND deleted = false and Year(publicationDate)= :year order by publicationDate desc");

		query.setInteger("publicationid", publicationId);
		query.setInteger("year", year);

		@SuppressWarnings("unchecked")
		List<Edition> editions = query.list();

		return editions;
	}

	@Override
	public List<Integer> getallPublicationIds()
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		SQLQuery query = session.createSQLQuery("select id from publication where deleted = :deleted");

		query.setBoolean("deleted", false);

		@SuppressWarnings("unchecked")
		List<Integer> list = query.list();

		List<Integer> allPublicationIds = new ArrayList<Integer>();

		for (Integer publicationIdObject : list)
		{
			allPublicationIds.add(publicationIdObject);
		}

		return allPublicationIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Publication> getPublicationsWithApiId(String apiPublishDate)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		List<Publication> list =null;
		
		Query query = session.createSQLQuery("select distinct p.id from publication p "
				+ "inner join publication_group_publication pg on p.id= pg.publications_id "
				+ " inner join edition e on e.publication_id = p.id where p.deleted=false and p.dtype = 'PublicationIntranetNS' and e.publicationdate > :apiPublishDate ");
		query.setParameter("apiPublishDate", Timestamp.valueOf( apiPublishDate));
		List<Integer> result = query.list();
		if(result !=null) {
			 query = session.createQuery("from Publication p where id IN (:result)");
			 
			query.setParameterList("result",result ,IntegerType.INSTANCE );
			 list = query.list();
			 list.forEach(pub->Hibernate.initialize(pub.getPublicationGroups()));
		}
		return list;
		
	}


	@Override
	public List<Map<String, String>> getAllPublicationsOfTypeSummaries(String publicationType) {

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query query = session.createQuery("SELECT new map(id as id, name as name) from Publication publication where publication.class = :publicationClass and deleted = :deleted");

		query.setBoolean("deleted", false);
		query.setParameter("publicationClass", publicationType);

		@SuppressWarnings("unchecked")
		List<Map<String, String>> publications = query.list();

		return publications;
	}
	
//	private Long getTotalNumberOfArticleWrapper(Integer editionId) {
//
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//
//		Query query = session.createQuery("SELECT count(*) from ArticleWrapper where edition_id = :editionId ");
//
//		query.setInteger("editionId",editionId);
//		return  (Long)query.uniqueResult();
//	}

//	@SuppressWarnings("unchecked")
//	@Override
//	@Transactional
//	public List<SchedulePublish> getCurrentScheduledEdition() {
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//
//
//		Integer startHour = new Date().getHours();
//		Integer startMinutes = new Date().getMinutes();
//		Query query = session.createQuery("from SchedulePublish where scheduleddate = current_date AND scheduledhour =  :starthour"
//				+ " AND scheduledminute between :startMinutes AND :endMinutes AND status = 'READY'  AND cancelled = false ");
//
//		query.setParameter("starthour", startHour);
//		query.setParameter("startMinutes", startMinutes -1);
//		query.setParameter("endMinutes", startMinutes);
//		return  query.list();
//	}

	@Override
	@Transactional
	public SchedulePublish saveSchedulePublish(SchedulePublish schedule) {
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		return  hibernateTemplate.merge(schedule);

	}
	
//	@Override
//	@Transactional
//	public SchedulePublish getScheduledEditionByEditionId(Integer editionId) {
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//
//		Query query = session.createQuery("from SchedulePublish where edition_id =:editionId");
//		query.setParameter("editionId", editionId);
//		return  (SchedulePublish) query.uniqueResult();
//	}

	
//	@SuppressWarnings("unchecked")
//	@Override
//    public List getAllPublicationsForUser(Integer userId)
//    {
//        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//
//        Query query = session.createQuery("select p from User u join u.availablePublications p where p.deleted = :deleted and u.id = :userId order by p.name ");
//        query.setParameter("deleted", false);
//        query.setParameter("userId", userId);
//        return query.list();
//    }
	
//	@Override
//	public List<Edition> getFullSubEdition(Edition edition) {
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//		Query query = session.createQuery("from Edition e join fetch e.publication pub join fetch e.parentEdition pe"
//				+ " where  pub.id = :publicationId and e.publicationDate is null and (pe.id = :editionId)");
//		// left join fetch e.articlewrappers aw left join fetch aw.article
//		query.setInteger("publicationId", edition.getPublication().getId());
//		query.setInteger("editionId", edition.getId());
//		List<Edition> editionList =  query.list();
//
//		return editionList;
//	}
	
	@Override
	public List<Object[]> getAvailableSubEditionForPublication(Integer editionId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(" select edition0_.id as editionId, string_agg(acdp.name, '+') as name from Edition edition0_  inner join edition_audiencecdp ea on edition0_.id = ea.edition_id inner join audience_cdp acdp on acdp.id = ea.audience_id" + 
				" where  (edition0_.publicationDate is null) and (edition0_.parentedition_id = :editionId )" + 
				" group by edition0_.id");
		query.setInteger("editionId", editionId);
		List<Object[]> editionList =  query.list();
		return editionList;
	}
	
	@Override
	@Transactional
	public void deleteSubEditions(List<Edition> editionList) throws EpublisherException
	{
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);

		hibernateTemplate.deleteAll(editionList);
		hibernateTemplate.flush();

		hibernateTemplate.clear();

	}
	
	@Override
	public List<Object[]> getAllArticlesForEditionSummaryId(Integer publicationId,Integer startRow ,Integer pageSize) 
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query query = session.createSQLQuery("SELECT e.id AS id, e.editionnumber AS editionNumber, e.name AS name,"
				+" e.publicationDate AS publicationDate, a.id AS articleId, a.title AS articleTitle,  (SELECT count(id) from edition where parentedition_id = e.id) "
				+ " as subeditionCount"
				+" FROM edition e LEFT OUTER JOIN articlewrapper awrapper ON awrapper.edition_id = e.id"
				+" LEFT OUTER JOIN article a ON a.id = awrapper.article_id "
				+" WHERE e.id in (select id from edition where publication_id = :publicationId and deleted=false  ORDER BY editionnumber desc OFFSET (:pageSize * :startRow ) ROWS  " + 
				"  FETCH NEXT :pageSize ROWS ONLY)"
				+ " AND e.parentedition_id IS NULL "
				+" AND e.publicationDate IS NOT NULL ORDER BY e.editionnumber,  awrapper.orderofarticle desc");
		query.setInteger("publicationId", publicationId);
		query.setInteger("pageSize", pageSize);
		query.setInteger("startRow", startRow);
		
		List<Object[]> results = query.list();
		return results;
	}
	
	@Override
	public Integer getTotalEditionsCountForPublication(Integer publicationId) 
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query editionCountQuery = session.createSQLQuery("SELECT count(id) from edition where publication_id = :publicationId "
				+ "and deleted=false "
				+ "and publicationDate IS NOT NULL");
		editionCountQuery.setInteger("publicationId", publicationId);
		Integer editionCount = Integer.valueOf(editionCountQuery.uniqueResult().toString());
		
		return editionCount;
	}
	
//	@Override
//	public List<ArticleArchiveDTO> getArchivedArticleForIntranetOrRailpocket(Integer publicationId, String apiPublishDate,int maxResults, int startRow,String dtype) {
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//
//		String sqlQuery = SqlUtils.getQuery(Sql.GET_ARCHIVE_ARTICLES_FOR_INTRANET_OR_RAILPOCKET
//				,":dtype"
//				,":apiPublishDate"
//				,":publicationID");
//
//		NativeQuery query= session.createSQLQuery(sqlQuery);
//		query.setParameter("dtype", dtype);
//		query.setParameter("publicationID", publicationId);
//		query.setTimestamp("apiPublishDate", Timestamp.valueOf( apiPublishDate));
//		query.setResultTransformer( Transformers.aliasToBean(ArticleArchiveDTO.class) );
//		query.setMaxResults(maxResults);
//		query.setFirstResult(startRow * maxResults);
//		return  (List<ArticleArchiveDTO>) query.list();
//	}
	
	@Override
	public Integer getLandingPageEditionIdByPublicationId(Integer publicationId) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        
		Query query = session.createSQLQuery("SELECT e.id"
				+" FROM edition e"
				+" WHERE e.publication_id = :publicationId");
		query.setInteger("publicationId", publicationId);
        Integer landingPageEditionId = null;
        
        landingPageEditionId = (Integer) query.uniqueResult();
        
        return landingPageEditionId;
	}
	
//	private List<Integer> getWrapperIdsForArticleId(ArticleWrapper wrapper) {
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//
//		Query query = session.createSQLQuery("Select aw.id from articlewrapper aw inner join edition e on aw.edition_id = e.id inner join publication p on e.publication_id= p.id"
//				+ " where e.publication_id = :pubId and aw.dtype ='ArticleIntranetNS' and (aw.removedfromedition = false or aw.removedfromedition is null)  and aw.article_id = :articleId   and e.publicationdate is not null ");
//		query.setParameter("pubId", wrapper.getEdition().getPublication().getId());
//		query.setParameter("articleId", wrapper.getArticle().getId());
//
//		return  ( List<Integer>) query.list();
//
//	}
	
//	@Override
//	public void updateArticleWrappers(ArticleWrapper wrapper) {
//
//	//get articlewrapper id
//		List<Integer> wrapperIds = getWrapperIdsForArticleId(wrapper);
//		if(!wrapperIds.isEmpty()) {
//			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//			session.setFlushMode(FlushMode.AUTO);
//			SQLQuery query = session.createSQLQuery("UPDATE articleWrapper SET removedfromedition = TRUE  WHERE dtype ='ArticleIntranetNS' AND id in (:wrapperIds)");
//			query.setParameterList("wrapperIds", wrapperIds,  IntegerType.INSTANCE);
//
//			query.executeUpdate();
//		}
//
//	}
	
	@Override
	public long getSubmittedPlaylistsById(Integer publicationId) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        
		Query query = session.createQuery("SELECT COUNT(DISTINCT id)"
				+" FROM Playlist"
				+" WHERE publication.id = :publicationId"
				+" AND deleted = false"
				+" AND state = 'submitted'");
		
		query.setParameter("publicationId", publicationId);
		
        return (Long) query.uniqueResult();
	}
	
	@Override
	public long getModifiedPlaylistsById(Integer publicationId) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        
		Query query = session.createQuery("SELECT COUNT(DISTINCT id)"
				+" FROM Playlist"
				+" WHERE publication.id = :publicationId"
				+" AND deleted = false"
				+" AND state = 'modified'");
		
		query.setParameter("publicationId", publicationId);
		
        return (Long) query.uniqueResult();
	}

	@Override
	public List<Object[]> getOrphanPublication() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery("select p.name as pubname, sg.name as screengroup from publication p left outer join screengroup sg on p.id = sg.publication_id  " + 
				"where  p.dtype = 'PublicationNarrowcastingNS' and p.deleted=false  order by p.name asc");
		
		List<Object[]> result = query.list();
		return result;
	}

	@Override
	public List<Edition> getEditionsByDateRange(String startDate, String endDate, List<Integer> publications, String type) throws ParseException {
		return null;
	}

//	@Override
//	public List<Edition> getEditionsByDateRange(final String startDate, final String endDate, final List<Integer> publications, String type) throws ParseException
//	{
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//
//		Query query = session.createQuery("from Edition "
//										+ "where publication_id IN (:publications) "
//										+ "AND dtype IN (:types) "
//										+ "AND publicationDate BETWEEN :startDate AND :endDate");
//
//		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date startdate = formatter.parse(startDate);
//        Date enddate = formatter.parse(endDate);
//        enddate = DateUtils.addHours(enddate, 23);
//        List<String> types = new ArrayList<String>();
//
//        if(publications != null && !publications.isEmpty()) {
//			query.setParameterList("publications", publications, IntegerType.INSTANCE);
//		}
//        if(type.contains("app") || type.contains("insite") || type.contains("Intranet")) {
//        	types.add("EditionIntranetNS");
//        } else if (type.contains("newsletter") || type.contains("Newsletter")) {
//        	types.add("EditionNewsletterNS");
//        } else {
//        	types.add("EditionIntranetNS");
//        	types.add("EditionNewsletterNS");
//        }
//
//        query.setParameterList("types", types, StringType.INSTANCE);
//		query.setTimestamp("startDate", (new Timestamp(startdate.getTime())));
//		query.setTimestamp("endDate", (new Timestamp(enddate.getTime())));
//		query.setMaxResults(90);
//		@SuppressWarnings("unchecked")
//		List<Edition> editions = query.list();
//
//		return editions;
//	}
	
	@Override
	@Transactional
	public Edition getPublishedEditionById(Integer editionId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Edition where id = :editionId and publicationDate IS NOT NULL");
		query.setInteger("editionId", editionId);
		Edition edition = (Edition) query.uniqueResult();

		return edition;
	}
	
	@Override
	public int getOptOutAbonneesAmount(final Date startDate, final Date endDate, final Integer editionId) {
		final Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		final String queryParameter = editionId != null ? "e.id = :editionId" : "e.publicationdate between :startDate  and :endDate ";
		final String queryString = SqlUtils.getQuery(Sql.GET_OPT_OUT_ABONNEES, queryParameter);
		
		final Query query = session.createSQLQuery(queryString);
		if(editionId!=null) {
			query.setInteger("editionId", editionId);
		} else {
			query.setParameter("startDate", startDate, DateType.INSTANCE);
			query.setParameter("endDate", endDate, DateType.INSTANCE);
		}
		return ((BigDecimal) query.uniqueResult()).intValue();
	}

	
//	@Override
//	public List<ArticleArchiveDTO> getArticleForRssFeed(Integer publicationId, String apiPublishDate,int maxResults, int startRow) {
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//
//		String sqlQuery = SqlUtils.getQuery(Sql.GET_ARTICLE_FOR_RSSFEED
//				,":apiPublishDate"
//				,":publicationID");
//
//		SQLQuery query= session.createSQLQuery(sqlQuery);
//		query.setInteger("publicationID", publicationId);
//		query.setTimestamp("apiPublishDate", Timestamp.valueOf( apiPublishDate));
//		query.setResultTransformer( Transformers.aliasToBean(ArticleArchiveDTO.class) );
//		query.setMaxResults(maxResults);
//		query.setFirstResult(startRow * maxResults);
//		return  (List<ArticleArchiveDTO>) query.list();
//	}
	
	@Override
	public List<EditionWrapper> getEditionsForPublication(Integer publicationId)
	{
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query query = session.createSQLQuery("Select DISTINCT e.id as id, e.editionnumber as number, e.publicationdate as publicationdate " + 
				"		from Edition  e left join edition_subscriptiongroup sg on e.id = sg.edition_id " + 
				"		WHERE publication_id =  :publicationid " + 
				"		AND publicationdate IS NOT NULL " + 
				"		AND (sg.subscriptiongroups_id IS NOT NULL OR e.parentedition_id IS NOT NULL OR (sg.subscriptiongroups_id IS NULL AND e.parentedition_id IS NULL AND id not in (select parentedition_id from edition where parentedition_id is not null and publication_id =  :publicationid) ))" + 
				"		ORDER BY number DESC");
		
		query.setInteger("publicationid", publicationId);
		query.setResultTransformer( Transformers.aliasToBean(EditionWrapper.class) );
		
		return query.list();
	}

	@Override
	public void getListOfPublications(String type) {

	}

//	@Override
//	public List<Publication> getListOfPublications(String type) {
//			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//			Query query = session.createQuery("from Publication where dtype = :publicationClass and deleted = :deleted");
//			query.setBoolean("deleted", false);
//			query.setParameter("publicationClass", type);
//
//			return query.getResultList();
//	}
	
	@Override
	public List<Playlist> getPublicationHistory(Integer publicationId, Integer limit) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        
		Query query = session.createQuery("SELECT id"
				+" FROM Playlist"
				+" WHERE ( publication.id = :publicationId"
				+" AND ((deleted = true AND state = 'published') OR (state = 'modified' AND lastUpdated = createdDate AND deleted != true)))"
				+" ORDER BY lastUpdated DESC"
				);
		
		query.setParameter("publicationId", publicationId);
		query.setMaxResults(limit);
		
		List<Playlist> convertedResult = new ArrayList<Playlist>();
		List<Integer> objects = query.getResultList();
		
		// convert objects to playlist
		for (Integer object : objects)
		{
			Playlist playlist = playlistDAO.getPlaylistById((object));
			
			// set playlist's last action
			if (playlist.isDeleted()) {
				playlist.setLastAction("Deleted");
			} else {
				playlist.setLastAction("Created");
			} 
			
			convertedResult.add(playlist);
		}

		return convertedResult;
	}
	
	@Override
	public Long getPublicationHistoryCount(Integer publicationId) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        
		Query query = session.createQuery("SELECT COUNT(id)"
				+" FROM Playlist"
				+" WHERE ( publication.id = :publicationId"
				+" AND ((deleted = true AND state = 'published') OR (state = 'modified' AND lastUpdated = createdDate AND deleted != true)))"
				);
		
		query.setParameter("publicationId", publicationId);

		return (Long) query.getSingleResult();
	}
	
	//Main Portal Page cannot be deleted
	//Main Portal Page has a hardcoded ID, this cannot be a duplicate
	//Only 1 Portal page can exist per Language (nl, en, fr, de)
	@Override
	public Boolean checkIfNewPortalPageIsValid(String uuid, String language, Boolean checkUuid, Boolean checkLanguage) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		StringBuilder queryString =new StringBuilder("SELECT COUNT(id)"
				+ " FROM publication p"
				+ " WHERE (p.deleted = false)"
				+ " AND p.dtype = 'PublicationPortalPage'");
		
		if(checkUuid) {
			queryString.append(" AND p.uuid = :uuid");
		}
		
		if(checkLanguage) {
			queryString.append(" AND (p.language = :language)");
		}
		
		Query query = session.createSQLQuery(queryString.toString());
		
		
		if(checkUuid) {
			query.setParameter("uuid", uuid);
		}
		
		if(checkLanguage) {
			query.setParameter("language", language);
		}
		
		//if == 0 then it does not exist, so it is valid
		Boolean isValid = ((BigInteger)query.getSingleResult()).longValue() == 0;
		
		return isValid;
	}
	
	//GET publication/playlist/template/screen info and sync to advertisement microservice through a cron job
	@Override
	@Transactional
	public List<Object[]> getSharedInformation() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String query = ("SELECT p.id, p.dtype, p.broadcastduration, p.maxplaylistduration, pla.playlist_array, gl.id as publicationLocationId, gl.street as streetname, gl.housenumber,gl.postalcode as zipcode, gl.state,gl.businessarea, gl.externalName"
				+ "				FROM publication p"
				+ "				JOIN("
				+ "					SELECT p.id AS id, string_agg(concat('{\"playlist_id\":', CASE WHEN pl.parentid IS NULL THEN CAST(pl.id as integer) ELSE CAST(pl.parentid as integer) END, ',','\"playlist_label\":', '\"', CAST(pl.name AS VARCHAR),'\"', ',','\"currentPlaylistId\":', '\"', CAST(pl.id AS integer),'\"' ,'}') ,';') as playlist_array"
				+ "					    FROM playlist pl"
				+ "					LEFT JOIN publication p on p.id = pl.publication_id"
				+ "					LEFT JOIN playlist_geolocation pgeo on pl.id = pgeo.playlist_id"
				+ "					LEFT JOIN geolocation pgl on pgeo.geolocation_id = pgl.id"
				+ "					WHERE pl.shared = true"
				+ "					AND pl.deleted = false"
				+ "					GROUP BY p.id"
				+ "					) pla USING (id)"
				+ "					LEFT JOIN geolocation gl on p.geolocation_id = gl.id;");
		
		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createSQLQuery(query).list();
		
		System.out.println("list.size(): " + list.size());
		
		return list;
	}

	@Override
	public List<PlayTime> getPlaylistFrequenciesByID(Integer playlistId) {
		return null;
	}


	//for advertisement microservice we get the playlist frequencies and map them to the microservice POJO (AdvertisementPlaylist)
//	@Override
//	@Transactional
//	public List<PlayTime> getPlaylistFrequenciesByID(Integer playlistId) {
//		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
//		Query query = session.createQuery("from PlayTime where playlist_id = :playlistId");
//		query.setParameter("playlistId", playlistId);
//
//		return query.getResultList();
//	}
	
	//for advertisement microservice we get the playlist screens and map them to the microservice POJO (AdvertisementPlaylist)
	@Override
	@Transactional
	public List<Object[]> getPlaylistScreensLocationByID(Integer playlistId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery("SELECT s.id AS screenId, g.id as geolocationId, g.latitude, g.longitude, g.range from "
				+ "	geolocation g"
				+ "	left join screen_geolocation sgl on g.id = sgl.geolocation_id"
				+ "	left join screen s on sgl.screen_id = s.id"
				+ "	left join screengroup sgr on s.screengroup_id = sgr.id"
				+ "	left join publication p on sgr.publication_id = p.id"
				+ "	left join playlist pl on p.id = pl.publication_id"
				+ "	where pl.id = :playlistId");
		
		query.setParameter("playlistId", playlistId);
		
		return query.list();
	}

	@Override
	@Transactional
	public List<Playlist> getSharedBookingInformation() {
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String query = " select p from Playlist p "
				+ "where p.shared= true and p.deleted = false  ";
		
		@SuppressWarnings("unchecked")
		List<Playlist> list = session.createQuery(query).list();
		
		
		return list;
	}
	
	
}

