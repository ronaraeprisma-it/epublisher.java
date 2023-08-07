package nl.prismait.epublisher.java.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.prismait.epublisher.java.dao.IScreenDAO;
import nl.prismait.epublisher.java.model.Publication;
import nl.prismait.epublisher.java.model.exception.EpublisherConcurrencyException;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.Screen;
import nl.prismait.epublisher.java.model.narrowcasting.ScreenFilter;
import nl.prismait.epublisher.java.model.narrowcasting.ScreenGroup;

@Service
public class ScreenService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IScreenDAO screenDAO;

	/**
	 * Saves the screengroup
	 * 
	 * @param screenGroup
	 *            the screengroup to save.
	 * @return the saved screengroup
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	@Transactional
	public ScreenGroup save(ScreenGroup screenGroup) throws EpublisherException
	{
		ScreenGroup savedScreenGroup = null;
		ScreenGroup parentScreenGroup = new ScreenGroup();
		
		List<ScreenGroup> childScreenGroups = new ArrayList<>();
		if(null!=screenGroup.getId()){
			childScreenGroups =getAllChildScreenGroups(screenGroup);
		}
		
		if(screenGroup.getParentScreenGroup() != null && screenGroup.getParentScreenGroup().getId() ==null)
			screenGroup.setParentScreenGroup(null);

		ScreenGroup databaseScreenGroup = screenDAO.getScreenGroup(screenGroup.getId());

		if(screenGroup.getParentScreenGroup() != null && screenGroup.getParentScreenGroup().getId() !=null) {
			//get the publication of parent screen group 
			parentScreenGroup = screenDAO.getScreenGroup(screenGroup.getParentScreenGroup().getId(), true);
			screenGroup.setParentScreenGroup(parentScreenGroup);
		}
		
		if (databaseScreenGroup != null && databaseScreenGroup.getPublication() != null)
		{
			screenGroup.setPublication(databaseScreenGroup.getPublication());
		}

		// check if the parentscreengroup is not the same as this group
		if (screenGroup.getParentScreenGroup() != null && screenGroup.getId() != null && screenGroup.getId().equals(screenGroup.getParentScreenGroup().getId()))
		{
			throw new EpublisherException("Het is niet toegestaan om een schermgroep toe te voegen aan zichzelf.", null);
		}

		// then check if this screengroup isn't added to one of his children screengroups
		// because this is not permitted
		if (screenGroup.getParentScreenGroup() != null && !childScreenGroups.isEmpty())
		{
			checkScreengroupRecursion(childScreenGroups, screenGroup.getParentScreenGroup());
		}
		try
		{
			savedScreenGroup = screenDAO.save(screenGroup);
		}
		catch (HibernateOptimisticLockingFailureException e)
		{
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException("Er is een fout opgetreden.\nEen andere gebruiker heeft deze schermgroep tussentijds aangepast.\nVervers de schermgroepenlijst en probeer het opnieuw.", e);
		}
		catch (DataIntegrityViolationException e)
		{
			if (e.getCause() instanceof ConstraintViolationException)
				throw new EpublisherException("Scherm met dit scherm id bestaat al.", e);
			else
				throw new EpublisherException("Error saving Screen", e);
		}
		finally
		{

		}
		return savedScreenGroup;
	}

	private void checkScreengroupRecursion(List<ScreenGroup> childScreenGroups, ScreenGroup originalParentScreenGroup) throws EpublisherException
	{
		// loop through all children of the currentscreengroup to see if they match the screengroup that's being added as parent
		for (ScreenGroup group : childScreenGroups)
		{
			// if the groups match, the screengroup that is being saved, is being given a parent that is also a child of this group,
			// this causes unwanted behaviour (you create an infinite loop of screengroups when you would try to navigate the screengroup tree)
			if (group.getId().equals(originalParentScreenGroup.getId()))
			{
				throw new EpublisherException("Het is niet toegestaan om een schermgroep toe te voegen\naan een van zijn eigen onderliggende schermgroepen.", null);
			}
			// check the children of this child screengroup as well
			if (group.getScreenGroups() != null)
			{
				checkScreengroupRecursion(group.getScreenGroups(), originalParentScreenGroup);
			}
		}

	}
	
	private List<ScreenGroup> getAllChildScreenGroups(ScreenGroup screenGroup){
		return screenDAO.getAllChildScreenGroups(screenGroup.getId());
	}

	/**
	 * Saves the screen.
	 * 
	 * @param screen
	 *            the screen to save.
	 * @return the saved screen.
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	public Screen save(Screen screen) throws EpublisherException
	{
		Screen savedScreen = null;

		try
		{
			savedScreen = screenDAO.save(screen);
		}
		catch (HibernateOptimisticLockingFailureException e)
		{
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException("Er is een fout opgetreden.\nEen andere gebruiker heeft dit scherm tussentijds aangepast.\nVervers de schermgroepenlijst en probeer het opnieuw.", e);
		}
		catch (DataIntegrityViolationException e)
		{
			if (e.getCause() instanceof ConstraintViolationException)
				throw new EpublisherException("Scherm met dit scherm id bestaat al.", e);
			else
				throw new EpublisherException("Error saving Screen", e);
		}
		finally
		{

		}

		return savedScreen;
	}

	/**
	 * Deletes the screengroup
	 * 
	 * @param screenGroup
	 *            the screengroup to delete.
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	@Transactional
	public void delete(ScreenGroup screenGroup) throws EpublisherException
	{
		try
		{
			screenDAO.delete(screenGroup);
		}
		catch (HibernateOptimisticLockingFailureException e)
		{
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException("Een fout is opgetreden. \nEen andere gebruiker heeft deze schermgroep tussentijds aangepast.\nVervers het schermgroepen scherm en probeer het opnieuw.", e);
		}
		finally
		{
		}
	}

	/**
	 * Deletes the screen.
	 * 
	 * @param screen
	 *            the screen to delete.
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	@Transactional
	public void delete(Screen screen) throws EpublisherException
	{
		try
		{
			screenDAO.delete(screen);
		}
		catch (HibernateOptimisticLockingFailureException e)
		{
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException("Een fout is opgetreden. \nEen andere gebruiker heeft dit scherm tussentijds aangepast.\nVervers het schermgroepen scherm en probeer het opnieuw.", e);
		}
		finally
		{
		}
	}

	/**
	 * Retrieves a screengroup by it's id.
	 * 
	 * @param screenGroupId
	 *            the id of the screengroup to retrieve.
	 * @return the selected screengroup
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	@Transactional
	public ScreenGroup getScreenGroup(int screenGroupId) throws EpublisherException
	{
		ScreenGroup screenGroup =screenDAO.getScreenGroup(screenGroupId);

		return screenGroup;
	}

	/**
	 * Retrieves all screenGroups.
	 * @param keyword 
	 * 
	 * @return a list of all screengroups.
	 * @see ScreenGroup
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	@Transactional
	public List<ScreenGroup> getAllScreenGroups(String keyword) throws EpublisherException
	{
		ScreenFilter filter = new ScreenFilter();
		List<ScreenGroup> screenGroups;
		
		filter.setScreenGroupKeyword(keyword);
		filter.setScreenKeyword(keyword);
		
		// empty filter fetches all groups
		if (keyword.equals("") || keyword.equals("default-search")) {
			screenGroups = screenDAO.getScreenGroups(new ScreenFilter());
		}
		
		else {
			screenGroups = screenDAO.getScreenGroups(filter);
		}
		
		return screenGroups;
	}

	@Transactional
	public List<ScreenGroup> getScreenGroups(ScreenFilter filter) throws EpublisherException
	{
		return screenDAO.getScreenGroups(filter);
	}
	
	@Transactional
	public void updateLastRequestOfScreen(String displayId)
	{
		screenDAO.updateLastRequest(displayId);
	}
	
	public Screen getScreenByDisplayId(String displayId)
	{
		return screenDAO.getScreenByDisplayId(displayId);
	}
	
	/**
	 * Return the parent screengroup based on a screenId
	 * 
	 * @param screenId
	 * 				get the screen group for the screen id
	 * @return  an instance of screen group
	 */
	public ScreenGroup getScreenGroupforScreen(String screenId)
	{
		Screen screen = screenDAO.getScreenByDisplayId(screenId);

		ScreenGroup screengroup = screen.getParentScreenGroup();

		return screengroup;
	}
	

	/**
	 * Return the parent screengroup based on a screenId
	 * 
	 * @param screenGroupId
	 * 				get the screen group for the screen id
	 * @return  an instance of screen group
	 */
	public List<Screen> getAllScreensForGroup(Integer screenGroupId)
	{
		List<Screen> screens = screenDAO.getAllScreensForGroup(screenGroupId);

		return screens;
	}
	

	public List<Object> getScreenDataSet(Integer parentId, Integer publicationId) {
		return screenDAO.getScreenDataSet(parentId,publicationId);
	}
	
	public List<Object> getAllScreenGroupsTable() {
		return screenDAO.getAllScreenGroupsTable();
	}

	public Publication getPublicationById(Integer publicationId)
	{
		return screenDAO.getPublicationById(publicationId);
	}

	@Transactional
	public List<ScreenGroup> savePublicationScreen(List<Map<String, Object>> selectedList, List<Map<String, Object>> unselectedList,
			Publication publication) {
		
		List<ScreenGroup> screenGroupToSave = new ArrayList<>() ;
		
		if(!selectedList.isEmpty())
		{
			screenGroupToSave =getScreenGroupsToSave(screenGroupToSave,selectedList,publication);
			
		}
		if(!unselectedList.isEmpty())
		{
			screenGroupToSave=getScreenGroupsToSave(screenGroupToSave,unselectedList,null);
		}
		if(!screenGroupToSave.isEmpty())
		screenDAO.saveAll(screenGroupToSave);
		
		return screenGroupToSave;
	}

	private List<ScreenGroup> getScreenGroupsToSave(List<ScreenGroup> screenGroupToSave,List<Map<String, Object>> list,Publication publication) 
	{
		List<Object> screenGroupId =new ArrayList<>();
		List<ScreenGroup> screenGroup =  new ArrayList<>() ;
		
		for(Map<String,Object> obj: list)
		{
			screenGroupId.add(obj.get("id"));
		}
		
		screenGroup = screenDAO.getScreenGroupList(screenGroupId);
		for(ScreenGroup screenGroupObj:screenGroup)
		{
			screenGroupObj.setPublication(publication);
			screenGroupToSave.add(screenGroupObj);
		}
		return screenGroupToSave;
	}
	
	public List<ScreenGroup> getScreenDataSetForPlaylist(Integer publicationId) {
		return screenDAO.getAllScreensGroupsInPublication(publicationId);
	}
}
