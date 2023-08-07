package nl.prismait.epublisher.java.dao;

import java.util.List;

import nl.prismait.epublisher.java.model.ETemplate;


public interface ITemplateDAO{


	public ETemplate save(ETemplate template);
	public void delete(ETemplate template);
	public List<ETemplate> getAllTemplates();

}
