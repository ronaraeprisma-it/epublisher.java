package nl.prismait.epublisher.java.dao;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import nl.prismait.epublisher.java.model.EPublisherFile;
import nl.prismait.epublisher.java.model.EPublisherImage;
import nl.prismait.epublisher.java.model.EPublisherMedia;
import nl.prismait.epublisher.java.model.ImageSize;
import nl.prismait.epublisher.java.model.exception.EpublisherException;

@Component("fileDAO")
public class FileDAOImpl implements IFileDAO
{	
	private HibernateTemplate hibernateTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{

		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	
	@Override
	@Transactional
	public EPublisherFile saveUploadedFile(EPublisherFile file) throws EpublisherException
	{
		logger.info("In saveUploadedFile method");
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		hibernateTemplate.saveOrUpdate(file);
		hibernateTemplate.flush();
		return file;
	}

	
	@Override
	@Transactional
	public Long retriveSavedFile(Integer id)  throws EpublisherException
	{
		logger.info("In retriveSavedFile method");
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery("Select folderid from EPublisherMedia where id = :id");
		query.setInteger("id", id);
		return ((Number) query.uniqueResult()).longValue();
		}

	
	@Override
	@Transactional
	public EPublisherImage saveUploadedImage(EPublisherImage imageToReturn) throws EpublisherException {
		logger.info("In saveUploadedImage method");
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		hibernateTemplate.saveOrUpdate(imageToReturn);
		hibernateTemplate.flush();
		return imageToReturn;
	}


	@Override
	public ImageSize getImageSize(Integer imageSizeId) {
		return hibernateTemplate.get(ImageSize.class, imageSizeId);
	}


	@Override
	public List<EPublisherMedia> getAvailableImages(String email, Integer itemsPerPage, Integer offset) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query queryResult = session.createQuery("from EPublisherMedia where mediatype = 'EPublisherImage' and mobileupload = true  and  username = :email ORDER BY id");
		queryResult.setFirstResult(offset);
		queryResult.setMaxResults(itemsPerPage);
		queryResult.setParameter("email", email);
		return (List<EPublisherMedia>) queryResult.list();
	}
	
	@Override
	public List<EPublisherImage> getImageById(Integer imageID) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query queryResult = session.createQuery("from EPublisherMedia where mediatype = 'EPublisherImage' and id = :imageID");
		queryResult.setParameter("imageID", imageID);
		return (List<EPublisherImage>) queryResult.list();
	}
	
}
