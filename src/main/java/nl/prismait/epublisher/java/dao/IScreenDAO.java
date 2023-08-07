package nl.prismait.epublisher.java.dao;

import java.util.List;

import nl.prismait.epublisher.java.model.Publication;
import nl.prismait.epublisher.java.model.narrowcasting.Screen;
import nl.prismait.epublisher.java.model.narrowcasting.ScreenFilter;
import nl.prismait.epublisher.java.model.narrowcasting.ScreenGroup;

public interface IScreenDAO
{

	public Screen save(Screen screen);

	public ScreenGroup save(ScreenGroup screenGroup);

	public void delete(Screen screen);

	public void delete(ScreenGroup screenGroup);

	public ScreenGroup getScreenGroup(Integer screenGroupId);

	public ScreenGroup getScreenGroup(Integer screenGroupId, Boolean eager);

	public Screen getScreen(Integer screenId);

	public List<ScreenGroup> getScreenGroups(ScreenFilter filter);

	public Screen getScreenByDisplayId(String screenId);

	public void updateLastRequest(String displayId);

	public void removePublicationFromScreenGroups(Publication publication);

	public List<Object> getScreenDataSet(Integer parentId, Integer publicationId);

	public List<Object> getAllScreenGroupsTable();

	public List<Screen> getAllScreensForGroup(Integer screenGroupId);
	
	public List<ScreenGroup> getScreenGroupList(List<Object> screenGroupId);

	public void saveAll(List<ScreenGroup> screenGroup);

	public Publication getPublicationById(Integer publicationId);

	public List<ScreenGroup> getAllChildScreenGroups(Integer id);

	public List<ScreenGroup> getAllScreensGroupsInPublication(Integer publicationId);

}
