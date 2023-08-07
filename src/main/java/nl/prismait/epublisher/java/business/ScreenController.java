package nl.prismait.epublisher.java.business;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.prismait.epublisher.java.model.Publication;
import nl.prismait.epublisher.java.model.dto.GenericResultWrapper;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.Screen;
import nl.prismait.epublisher.java.model.narrowcasting.ScreenGroup;
import nl.prismait.epublisher.java.service.ScreenService;
import nl.prismait.epublisher.java.util.EpublisherConstants;

@Controller
@RequestMapping("editor/screen")
public class ScreenController extends BaseController
{

	@Autowired
	private ScreenService screenService;
	
//	@Autowired
//	private SecurityValidator securityValidator;
	

	/**
	 * Saves the screengroup URL: /BlazeDS/editor/screen/saveScreenGroup POST
	 * 
	 * @param screenGroup
	 *            the screengroup to save.
	 * @return the saved screengroup
	 * @see ScreenGroup
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "saveScreenGroup")
	@ResponseBody
	@Transactional
	public GenericResultWrapper save(@RequestBody ScreenGroup screenGroup) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_ADMIN));
		return createResult(screenService.save(screenGroup));
	}

	/**
	 * Saves the screen. URL: /BlazeDS/editor/screen/save POST
	 * 
	 * @param screen
	 *            the screen to save.
	 * @return the saved screen.
	 * @see Screen
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "save")
	@ResponseBody
	public GenericResultWrapper save(@RequestBody Screen screen) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_ADMIN));
		Screen existingScreen = screenService.getScreenByDisplayId(screen.getDisplayId());
		if(existingScreen == null || screen.getId() != null)
			screen = screenService.save(screen);
		else
			throw new EpublisherException("Scherm met dit scherm id bestaat al.");
		return createResult(screen);
	}

	/**
	 * Deletes the screengroup URL: /BlazeDS/editor/screen/deleteScreenGroup
	 * DELETE
	 * 
	 * @param screenGroup
	 *            the screengroup to delete.
	 * @return the status of delete
	 * @see ScreenGroup
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "deleteScreenGroup")
	@ResponseBody
	@Transactional
	public GenericResultWrapper delete(@RequestBody ScreenGroup screenGroup) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_ADMIN));

		screenService.delete(screenGroup);
		return createResult(EpublisherConstants.NULL);
	}

	/**
	 * Deletes the screen. URL: /BlazeDS/editor/screen/delete DELETE
	 * 
	 * @param screen
	 *            the screen to delete.
	 * @return the status of delete
	 * @see Screen
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "delete")
	@ResponseBody
	@Transactional
	public GenericResultWrapper delete(@RequestBody Screen screen) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_ADMIN));

		screenService.delete(screen);
		return createResult(EpublisherConstants.NULL);
	}

	/**
	 * Retrieves a screengroup by it's id.
	 * URL:/BlazeDS/editor/screen/getScreenGroup/{screenGroupId} GET
	 * 
	 * @param screenGroupId
	 *            the id of the screengroup to retrieve.
	 * @return the selected screengroup
	 * @see ScreenGroup
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getScreenGroup/{screenGroupId}")
	@ResponseBody
	@Transactional
	public GenericResultWrapper getScreenGroup(@PathVariable int screenGroupId) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));

		return createResult(screenService.getScreenGroup(screenGroupId));
	}

	/**
	 * Retrieves all screenGroups. URL:/BlazeDS/editor/screen/{keyword}/getAllScreenGroups GET
	 * @param keyword 
	 * 
	 * @return a list of all screengroups.
	 * @see ScreenGroup
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "{keyword}/getAllScreenGroups")
	@ResponseBody
	@Transactional
	public GenericResultWrapper getAllScreenGroups(@PathVariable String keyword) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_ADMIN, EpublisherConstants.ROLE_CHIEFEDITOR, EpublisherConstants.ROLE_USER));

		return createResult(screenService.getAllScreenGroups(keyword));
	}

	/**
	 * Retrieves all screens for a screengroup. URL:/BlazeDS/editor/screen/getAllScreensForGroup/{screenGroupId} GET
	 * @param screenGroupId 
	 * 					id used to retrieve all the screens 
	 * @return a list of all screengroups.
	 * @see ScreenGroup
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getAllScreensForGroup/{screenGroupId}")
	@ResponseBody
	@Transactional
	public GenericResultWrapper getAllScreensForGroup(@PathVariable Integer screenGroupId) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_ADMIN, EpublisherConstants.ROLE_CHIEFEDITOR, EpublisherConstants.ROLE_USER));

		return createResult(screenService.getAllScreensForGroup(screenGroupId));
	}

	/**
	 * Return the parent screengroup based on a screenId
	 * 
	 * @param screenId
	 * 			to retrieve the screen group for the given screen id 
	 * @return an instance of screen group
	 */
	public ScreenGroup getScreenGroupforScreen(String screenId)
	{
		ScreenGroup screengroup = screenService.getScreenGroupforScreen(screenId);

		return screengroup;
	}



	/**
	 * Retrieves all screenGroups. URL:/BlazeDS/editor/screen/parent/{parentId}/publication/{publicationId} GET
	 * @param parentId
	 * 			id of the parent screen 
	 * @param publicationId
	 * 			the id of the publicaiton associated with the screen 
	 * @return a list of all screengroups.
	 * @see ScreenGroup
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "parent/{parentId}/publication/{publicationId}")
	@ResponseBody
	@Transactional
	public GenericResultWrapper getScreenDataSet(@PathVariable Integer parentId, @PathVariable Integer publicationId) throws EpublisherException
	{
		List<Object> screengroup = screenService.getScreenDataSet(parentId,publicationId);
		return createResult(screengroup);
	}
	
	
	/**
	 * For managing screens URL:/BlazeDS/editor/screen/allScreenGroupsTable GET
	 * @return a list of all screengroups.
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "allScreenGroupsTable")
	@ResponseBody
	@Transactional
	public GenericResultWrapper getAllScreenGroupsTable() throws EpublisherException
	{
		List<Object> screengroup = screenService.getAllScreenGroupsTable();
		return createResult(screengroup);
	}
	
	
	/**
	 * savePublicationScreen URL:/BlazeDS/editor/screen/savePublicationScreen/{publicationId} POST
	 * @param json 
	 * 			input json from front end
	 * @param publicationId 
	 * 					id of the publication to which the screen should be saved . 
	 * @return a list of all screengroups.
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "savePublicationScreen/{publicationId}")
	@ResponseBody
	public GenericResultWrapper savePublicationScreen(@RequestBody Map<String, List<Map<String,Object>>> json,@PathVariable Integer publicationId ) throws EpublisherException
	{
		
		List<Map<String,Object>> selectedList = json.get("selected");
		List<Map<String,Object>> unselectedList = json.get("unSelected");
		Publication publication= screenService.getPublicationById(publicationId);
		return createResult(screenService.savePublicationScreen(selectedList,unselectedList,publication));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getScreenDataSetForPlaylist/{publicationId}")
	@ResponseBody
	public GenericResultWrapper getScreenDataSetForPlaylist( @PathVariable Integer publicationId) throws EpublisherException
	{
		List<ScreenGroup> screengroup = screenService.getScreenDataSetForPlaylist(publicationId);
		return createResult(screengroup);
	}
	
}