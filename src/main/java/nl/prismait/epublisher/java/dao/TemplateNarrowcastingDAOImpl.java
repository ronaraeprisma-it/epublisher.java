package nl.prismait.epublisher.java.dao;

import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import nl.prismait.epublisher.java.model.narrowcasting.TemplateNarrowcasting;

@Component("templateNarrowcastingDAO")
public class TemplateNarrowcastingDAOImpl implements ITemplateNarrowcastingDAO
{
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{

		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public TemplateNarrowcasting save(TemplateNarrowcasting template) {
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		template = hibernateTemplate.merge(template);
		hibernateTemplate.flush();
		hibernateTemplate.clear();
		return template;
	}
	
	@Override
	public TemplateNarrowcasting getTemplateById(Integer id) {
		return hibernateTemplate.get(TemplateNarrowcasting.class, id);
	}
	
	@Override
	public List<TemplateNarrowcasting> getUserNarrowcastingTemplates(List<Integer> templatesIds) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from TemplateNarrowcasting where id IN (:id)");
		query.setParameterList("id",templatesIds ,IntegerType.INSTANCE );
		
		List<TemplateNarrowcasting> templates = query.list();
		
		return templates;
	}
	
	@Override
	public List<Integer> getUserNarrowcastingTemplatesIds(Integer userId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createNativeQuery("Select id from TemplateNarrowcasting t"
				+ "	inner join epublisheruser_templatenarrowcasting ut on t.id = ut.availabletemplates_id"
				+ " where ut.epublisheruser_id = :userId");
		query.setParameter("userId", userId);
		List<Integer> templatesIds = query.list();;
		
		return templatesIds;
	}
	
	@Override
	public TemplateNarrowcasting getAppNarrowcastingTemplate() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from TemplateNarrowcasting where htmltemplate LIKE :website");
		query.setParameter("website" , "%website%");
		query.setMaxResults(1);
		
		return (TemplateNarrowcasting) query.uniqueResult();
	}
	
}
