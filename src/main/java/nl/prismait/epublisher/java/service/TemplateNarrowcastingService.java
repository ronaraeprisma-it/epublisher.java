package nl.prismait.epublisher.java.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.prismait.epublisher.java.dao.ITemplateNarrowcastingDAO;
import nl.prismait.epublisher.java.model.exception.EpublisherConcurrencyException;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.TemplateNarrowcasting;

@Service
public class TemplateNarrowcastingService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ITemplateNarrowcastingDAO templateNarrowcastingDAO;
	
	@Transactional
	public TemplateNarrowcasting save(TemplateNarrowcasting template) throws EpublisherException 
	{
		TemplateNarrowcasting savedTemplate = null;
		
		try {
			savedTemplate = templateNarrowcastingDAO.save(template);
		} catch (HibernateOptimisticLockingFailureException e) {
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException(
					"Er is een fout opgetreden.\nEen andere gebruiker heeft deze sjabloon tussentijds aangepast.\nVervers het scherm en probeer het opnieuw.",
					e);
		} finally {
		}
		return savedTemplate;
	}
	
	public TemplateNarrowcasting findTemplateById(Integer id) 
	{
		TemplateNarrowcasting template = templateNarrowcastingDAO.getTemplateById(id);
		return template;
	}
	
	@Transactional
	public List<TemplateNarrowcasting> getUserNarrowcastingTemplates(Integer userId) 
	{
		List<TemplateNarrowcasting> userTemplates = new ArrayList<TemplateNarrowcasting>();
		//first we get list of current user templates ids
		List<Integer> userNarrowcastingTemplatesIds = templateNarrowcastingDAO.getUserNarrowcastingTemplatesIds(userId);
		
		//if user has templates assigned, we get them by id
		if(userNarrowcastingTemplatesIds != null && userNarrowcastingTemplatesIds.size() > 0) {
		//then we get and return the current user narrowcasting templates
		userTemplates = templateNarrowcastingDAO.getUserNarrowcastingTemplates(userNarrowcastingTemplatesIds);
		}
		
		return userTemplates;
	}
	
	public TemplateNarrowcasting getAppNarrowcastingTemplate() 
	{
		TemplateNarrowcasting template = templateNarrowcastingDAO.getAppNarrowcastingTemplate();
		return template;
	}

}
