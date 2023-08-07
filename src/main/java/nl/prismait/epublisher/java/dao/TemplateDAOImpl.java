package nl.prismait.epublisher.java.dao;

import java.util.List;

import nl.prismait.epublisher.java.model.ETemplate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("templateDAO")
public class TemplateDAOImpl implements ITemplateDAO {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {

		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional
	public ETemplate save(ETemplate template) {

		hibernateTemplate.merge(template);
		
		return template;
	}

	@Override
	@Transactional
	public void delete(ETemplate template) {

		if (!isUsedInPublication(template))
		{
			template.setDeleted(true);

			hibernateTemplate.saveOrUpdate(template);
		}
		else
		{
			throw new DataIntegrityViolationException("Template used by an active publication, can not be removed.");
		}
	}

	private boolean isUsedInPublication(ETemplate template)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		
		Query query = session.createQuery("from Publication where template.id = :templateId and deleted = false");
		query.setInteger("templateId", template.getId());

		return !query.list().isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ETemplate> getAllTemplates() {

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query queryResult = session.createQuery("from ETemplate where deleted = false order by name");
		return queryResult.list();

	}
}
