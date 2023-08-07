package nl.prismait.epublisher.java.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.prismait.epublisher.java.model.ETemplate;
import nl.prismait.epublisher.java.model.dto.GenericResultWrapper;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.service.TemplateService;
import nl.prismait.epublisher.java.util.EpublisherConstants;

@Controller
@RequestMapping("editor/template")
public class TemplateController extends BaseController
{

	@Autowired
	private TemplateService templateService;
	

	/**
	 * Saves a template URL: /BlazeDS/editor/template/save POST
	 * 
	 * @param template
	 * 			an instance of {@link ETemplate} to be saved
	 * @return the saved template
	 * @see ETemplate
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "save")
	@ResponseBody
	public GenericResultWrapper save(@RequestBody ETemplate template) throws EpublisherException
	{
		if(template.getName() == null)
		{
			throw new EpublisherException("Template name can not be empty");
		}

		return createResult(templateService.save(template));
	}

	/**
	 * Deletes a template URL: /BlazeDS/editor/template/delete DELETE
	 * 
	 * @param template
	 * 			an instance of {@link ETemplate} to be deleted
	 * @return the status of delete
	 * @see ETemplate
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "delete")
	@ResponseBody
	public GenericResultWrapper delete(@RequestBody ETemplate template) throws EpublisherException
	{
		templateService.delete(template);
		return createResult(EpublisherConstants.NULL);
	}

	/**
	 * Returns all templates sorted by name URL: /BlazeDS/editor/template/all
	 * GET
	 * 
	 * @return list all templates
	 * @see ETemplate
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "all")
	@ResponseBody
	public GenericResultWrapper getAllTemplates() throws EpublisherException
	{
		return createResult(templateService.getAllTemplates());

	}

}
