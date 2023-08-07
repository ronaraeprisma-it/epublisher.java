package nl.prismait.epublisher.java.dao;
import java.util.List;
import nl.prismait.epublisher.java.model.narrowcasting.TemplateNarrowcasting;

/**
 * @author a.muscalu
 *
 */
public interface ITemplateNarrowcastingDAO {

	public TemplateNarrowcasting save (TemplateNarrowcasting template);
	public TemplateNarrowcasting getTemplateById(Integer id);
	public List<TemplateNarrowcasting> getUserNarrowcastingTemplates(List<Integer> userNarrowcastingTemplatesIds);
	public List<Integer> getUserNarrowcastingTemplatesIds(Integer userId);
	public TemplateNarrowcasting getAppNarrowcastingTemplate();
	
}
