package nl.prismait.epublisher.java.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import nl.prismait.epublisher.java.dao.ITemplateDAO;
import nl.prismait.epublisher.java.model.ETemplate;
import nl.prismait.epublisher.java.model.exception.EpublisherConcurrencyException;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.util.Sanitize;

@Service
public class TemplateService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ITemplateDAO templateDAO;

	/**
	 * Saves a template
	 * 
	 * @param template
	 * 			an instance of {@link ETemplate}
	 * @return the saved template
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	public ETemplate save(ETemplate template) throws EpublisherException
	{
		ETemplate savedTemplate;

		// *** ckeditor sanitize ***
		if (template.getColophonText() != null && !template.getColophonText().isEmpty()) {
			template.setColophonText(Sanitize.sanitizeString(template.getColophonText()));
		}
		
		if (template.getFooterText() != null && !template.getFooterText().isEmpty()) {
			template.setFooterText(Sanitize.sanitizeString(template.getFooterText()));
		}
		// *** ckeditor sanitize ***
		
		try
		{
			savedTemplate = templateDAO.save(template);
		}
		catch (HibernateOptimisticLockingFailureException e)
		{
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException("Een fout is opgetreden. \nEen andere gebruiker heeft deze template tussentijds aangepast.\nVervers het template scherm en probeer het opnieuw.", e);
		}
		catch (DataIntegrityViolationException e)
		{
			if (e.getCause() instanceof ConstraintViolationException)
				throw new EpublisherException("Template met deze naam bestaat al.", e);
			else
				throw new EpublisherException("Error saving Template", e);
		}
		catch (Exception e)
		{
			throw new EpublisherException("Error saving Template", e);
		}

		return savedTemplate;
	}

	/**
	 * Deletes a template
	 * 
	 * @param template
	 * 			an instance of {@link ETemplate} to delete
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	public void delete(ETemplate template) throws EpublisherException
	{
		try
		{
			templateDAO.delete(template);
		}
		catch (HibernateOptimisticLockingFailureException e)
		{
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException("Een fout is opgetreden. \nEen andere gebruiker heeft deze template tussentijds aangepast.\nVervers het template scherm en probeer het opnieuw.", e);
		}
		catch (DataIntegrityViolationException e)
		{
			throw new EpublisherException("Template wordt nog gebruikt in een publicatie of editie en kan dus niet verwijderd worden.", e);
		}
	}

	/**
	 * Returns all templates sorted by name
	 * 
	 * @return list all templates
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	public List<ETemplate> getAllTemplates() throws EpublisherException
	{
		List<ETemplate> templates = templateDAO.getAllTemplates();
		Collections.sort(templates);
		return templates;
	}

}
