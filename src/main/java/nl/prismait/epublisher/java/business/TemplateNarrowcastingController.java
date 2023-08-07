package nl.prismait.epublisher.java.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.prismait.epublisher.java.model.dto.GenericResultWrapper;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.TemplateNarrowcasting;
import nl.prismait.epublisher.java.service.TemplateNarrowcastingService;

@Controller
@RequestMapping("templatenarrowcasting")
public class TemplateNarrowcastingController extends BaseController {
	
	@Autowired
	private TemplateNarrowcastingService templateNarrowcastingService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "saveNarrowcastingTemplate/{id}")
	@ResponseBody
	public GenericResultWrapper saveNarrowcastingTemplate(@PathVariable Integer id) throws EpublisherException
	{
		TemplateNarrowcasting template = templateNarrowcastingService.save(templateNarrowcastingService.findTemplateById(id));
		
		return createResult(template);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getNarrowcastingTemplate/{id}")
	@ResponseBody
	public GenericResultWrapper getNarrowcastingTemplate(@PathVariable Integer id) throws EpublisherException
	{
		TemplateNarrowcasting template = templateNarrowcastingService.findTemplateById(id);
		
		return createResult(template);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getUserNarrowcastingTemplates/{userId}")
	@ResponseBody
	public GenericResultWrapper getUserNarrowcastingTemplates(@PathVariable Integer userId) throws EpublisherException
	{
		return createResult(templateNarrowcastingService.getUserNarrowcastingTemplates(userId));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getAppNarrowcastingTemplate")
	@ResponseBody
	public GenericResultWrapper getAppNarrowcastingTemplate() throws EpublisherException
	{
		return createResult(templateNarrowcastingService.getAppNarrowcastingTemplate());
	}

	@RequestMapping(method = RequestMethod.GET, value = "getNarrowcastingTemplateResponse")
	@ResponseBody
	public GenericResultWrapper getNarrowcastingTemplateResponse() throws EpublisherException
	{
		return createResult("");
	}

	
}
