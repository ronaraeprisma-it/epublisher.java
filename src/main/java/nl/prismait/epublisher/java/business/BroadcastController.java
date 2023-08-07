package nl.prismait.epublisher.java.business;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import freemarker.template.TemplateException;
import nl.prismait.epublisher.java.business.comparator.ContentBlockComapratorOnContainerId;
import nl.prismait.epublisher.java.dao.SessionIndentifierDAO;
//import nl.prismait.epublisher.java.model.FunctionGroup;
import nl.prismait.epublisher.java.model.PlaylistScreengroupAndPublication;
import nl.prismait.epublisher.java.model.Route;
//import nl.prismait.epublisher.java.model.User;
import nl.prismait.epublisher.java.model.config.PropertiesUtil;
//import nl.prismait.epublisher.java.model.dashboard.schema.UserSession;
import nl.prismait.epublisher.java.model.dto.BroadcastSearchResult;
import nl.prismait.epublisher.java.model.dto.GenericResultWrapper;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.Broadcast;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastFilter;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.ContentBlock;
import nl.prismait.epublisher.java.model.narrowcasting.TemplateNarrowcasting;
import nl.prismait.epublisher.java.service.BroadCastService;
import nl.prismait.epublisher.java.service.PlaylistService;
//import nl.prismait.epublisher.java.service.SecurityValidator;
//import nl.prismait.epublisher.java.service.UserService;
import nl.prismait.epublisher.java.util.EpublisherConstants;

@Controller
@RequestMapping("editor/broadcast")
public class BroadcastController extends BaseController
{
	private static final Logger LOG = LoggerFactory.getLogger(BroadcastController.class);

	@Autowired
	private BroadCastService broadcastService;
	
//	@Autowired
//	private SecurityValidator securityValidator;

	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private SessionIndentifierDAO sessionDAO;
	
//	@Autowired
//	private UserService userService;
	
	@ModelAttribute("redirect_base")
	public String populateRedirectBase()
	{
		return new StringBuilder("/").append(PropertiesUtil.getProperty("epublisher.blazeds.contextroot")).toString();
	}
	
	Properties properties = new Properties();
	
	
	/**
	 * URL:/BlazeDS/broadcastviewer GET
	 * @param preview String 
	 * @param model spring model object
	 * @param request http response
	 * @return string
	 */
	@RequestMapping(value = "/broadcastviewer", method = RequestMethod.GET)
	public String broadcastViewer(
			@RequestParam(value = "preview", required = true) String preview
			, Model model
			, HttpServletRequest request
			) throws IOException, TemplateException
	{
		Map<String, Object> map = new HashMap<>();
        map.put("accountId", PropertiesUtil.getProperty("accountId"));
        map.put("playerId", PropertiesUtil.getProperty("playerId"));
        map.put("isSecure", request.isSecure());
        map.put("redirect_base", populateRedirectBase());
        map.put("preview", preview);
        map.put("version", this.getClass().getPackage().getImplementationVersion());
        map.put("tenantID", sessionDAO.getTenantIdByURL(request.getServerName()));
		
	
		return playlistService.getTemplateForScreen(map,request);
	}
	
	/**
	 * Prepares the preview of a Broadcast URL: /BlazeDS/editor/broadcast/preparePreview
	 * 
	 * @param broadcast
	 *            the broadcast object to preview.
	 * @return the GenericResultWrapper containing the status 
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "preparePreview")
	@ResponseBody 
	public GenericResultWrapper preparePreview(@RequestBody Broadcast broadcast) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));

		// Broadcasts can be previewed before they are saved in the database so
		// simply store it in the user's session.
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		playlistService.setUrlInBroadcast(broadcast);
		session.setAttribute(Broadcast.BROADCAST_TO_PREVIEW_KEY_NAME, broadcast);
		return new GenericResultWrapper(HttpStatus.OK.value(), HttpStatus.OK.name());
	}
	
	/**
	 * Preview a broadcast and generate thumbnail
	 *  URL:/BlazeDS/thumbnailbroadcast GET
	 * @return BroadcastWrapper
	 */
	@RequestMapping(value = "/thumbnailbroadcast", method = RequestMethod.GET)
	@ResponseBody
	public BroadcastWrapper thumbnailbroadcast()
	{
		// A broadcast to be previewed is stored in the user's session.
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		// Create a dummy wrapper for it and store the broadcast in the user's session in it.
		BroadcastWrapper wrapper = new BroadcastWrapper();
		wrapper.setId(-1);
		wrapper.setBroadcast((Broadcast) session.getAttribute(Broadcast.BROADCAST_TO_PREVIEW_KEY_NAME));
		//Added sorting for JIRA ticket number NS_EPB-972
		Set<ContentBlock> set =  new TreeSet<ContentBlock>(new ContentBlockComapratorOnContainerId());
		for(ContentBlock content : wrapper.getBroadcast().getContentBlocks())
		{
			set.add(content);
		}
		
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
		} catch (IOException e) {
			LOG.warn("Exception loading project properties.", e);
		}
		
		wrapper.getBroadcast().setContentBlocks(set);
		wrapper.setVersion(properties.getProperty("version"));
		wrapper.setLocation("Thumbnail");

		return wrapper;
	}
	
	/**
	 * Preview a broadcast
	 *  URL:/BlazeDS/previewbroadcast GET
	 * @return BroadcastWrapper
	 */
	@RequestMapping(value = "/previewbroadcast", method = RequestMethod.GET)
	@ResponseBody
	public BroadcastWrapper previewBroadcast()
	{
		// A broadcast to be previewed is stored in the user's session.
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		// Create a dummy wrapper for it and store the broadcast in the user's session in it.
		BroadcastWrapper wrapper = new BroadcastWrapper();
		wrapper.setId(-1);
		wrapper.setBroadcast((Broadcast) session.getAttribute(Broadcast.BROADCAST_TO_PREVIEW_KEY_NAME));
		//Added sorting for JIRA ticket number NS_EPB-972
		Set<ContentBlock> set =  new TreeSet<ContentBlock>(new ContentBlockComapratorOnContainerId());
		for(ContentBlock content : wrapper.getBroadcast().getContentBlocks())
		{
			set.add(content);
		}
		
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
		} catch (IOException e) {
			LOG.warn("Exception loading project properties.", e);
		}
		
		wrapper.getBroadcast().setContentBlocks(set);
		wrapper.setVersion(properties.getProperty("version"));
		wrapper.setLocation("Preview");

		return wrapper;
	}
	

	/**
	 * Gets all available templates. URL: /BlazeDS/editor/broadcast/allTemplates
	 * GET
	 * 
	 * @return all narrowcasting templates.
	 * @see TemplateNarrowcasting
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.GET, value = "allTemplates")
	@ResponseBody
	public GenericResultWrapper getAllTemplates() throws EpublisherException 
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
		return createResult(broadcastService.getAllTemplates());
	}

	/**
	 * Gets all available templates. URL: /BlazeDS/editor/broadcast/{id}
	 * GET
	 * @param id
	 * 			id of the broadcast to be retrieved
	 * @return broadcast found by given id 
	 * @see TemplateNarrowcasting
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */	
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	@ResponseBody
	public GenericResultWrapper findBroadcastById(@PathVariable Integer id) throws EpublisherException 
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
		return createResult(broadcastService.findBroadcastById(id));
	}
	
	/**
	 * Get broadcasts paginated URL: /BlazeDS/editor/broadcast/search POST
	 * 
	 * @param filter
	 * 			an instance of broadcast filter to search the broadcast.
	 * @return the list of broadcasts including the total number of broadcasts
	 * @see BroadcastSearchResult
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "search")
	@ResponseBody
	public GenericResultWrapper searchBroadcasts(@RequestBody BroadcastFilter filter) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
		return createResult(broadcastService.searchBroadcasts(filter));
	}

	/**
	 * Get broadcasts paginated URL: /BlazeDS/editor/broadcast/search GET
	 * @param ascendingSortOrder boolean
	 * @param broadcastName string 
	 * @param createdBy string
	 * @param numberOfResults Integer
	 * @param sortField string
	 * @param startPage Integer
	 * @param templateName string
	 * @param userId Integer
	 * @return the list of broadcasts including the total number of broadcasts
	 * @see BroadcastSearchResult
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.GET, value = "search")
	@ResponseBody
	public GenericResultWrapper searchBroadcasts(
			@RequestParam(value="ascendingSortOrder", defaultValue="true") boolean ascendingSortOrder
			, @RequestParam(value="broadcastName", required=false) String broadcastName
			, @RequestParam(value="createdBy", required=false) String createdBy
			, @RequestParam(value="numberOfResults", defaultValue="25") Integer numberOfResults
			, @RequestParam(value="sortField", required=false) String sortField
			, @RequestParam(value="startPage", defaultValue="0") Integer startPage
			, @RequestParam(value="templateName", required=false) String templateName
			, @RequestParam(value="userId", defaultValue="-1") Integer userId
		) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));

		BroadcastFilter filter = new BroadcastFilter();
		filter.setAscendingSortOrder(ascendingSortOrder);
		filter.setBroadcastName(broadcastName);
		filter.setCreatedBy(createdBy);
		filter.setNumberOfResults(numberOfResults);
		filter.setSortField(sortField);
		filter.setStartPage(startPage);
		filter.setTemplateName(templateName);
		if(userId != -1)
			filter.setUserId(userId);
		
		return createResult(broadcastService.searchBroadcasts(filter));
	}

	/**
	 * Saves a Broadcast URL: /BlazeDS/editor/broadcast/save POST
	 * 
	 * @param broadcast 
	 * 			the broadcast object to save.
	 * @param playlistId 
	 * @return the saved broadcast
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 * @throws IOException 
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "save")
	@ResponseBody
	public GenericResultWrapper save(@RequestBody Broadcast broadcast, @RequestParam(value="playlistId", required=false) 
		Integer playlistId, @RequestParam(value="editClone", required=false, defaultValue="false") String editClone,
		@RequestParam(value="broadcastWrapperId", required=false) Integer broadcastWrapperId,
		HttpServletRequest request) throws EpublisherException, IOException 
	{
//		UserSession sessionUser = securityValidator.loadUserFromEPublisher();
//		securityValidator.hasWriteAccess("Wijzig Uitzendingen");
		Broadcast savedBroadcast = null;
//		savedBroadcast = broadcastService.processBroadcast(editClone,broadcastWrapperId,broadcast,playlistId,sessionUser,request);
		
		//sort content
		if(savedBroadcast != null && null!=savedBroadcast.getContentBlocks())
		{
			//sort content block by container id, so that it appears ordered in front end 
			Set<ContentBlock> set =  new TreeSet<>(new ContentBlockComapratorOnContainerId());
			for(ContentBlock content : savedBroadcast.getContentBlocks())
			{
				set.add(content);
			}
			savedBroadcast.setContentBlocks(set);
		}
		
		return createResult(savedBroadcast);
	}

	/**
	 * Removes a Broadcast URL: /BlazeDS/editor/broadcast/deleteById/{broadcastId} DELETE
	 * @param broadcastId is the id of the broadcast to be deleted.
	 * 
	 * @return returns the status of delete
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.DELETE, value = "deleteById/{broadcastId}")
	@ResponseBody
	public GenericResultWrapper deleteById(@PathVariable Integer broadcastId) throws EpublisherException 
	{
//		securityValidator.hasWriteAccess("Verwijder Uitzendingen");
		broadcastService.delete(broadcastService.findBroadcastById(broadcastId));
		return createResult(EpublisherConstants.NULL);
	}

	/**
	 * Saves a BroadcastWrapper URL: /BlazeDS/editor/broadcast/saveBroadcastWrapper POST
	 * 
	 * @param broadcastWrapper
	 *            the broadcastwrapper object to save.
	 * @return the saved broadcastwrapper
	 * @see BroadcastWrapper
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "saveBroadcastWrapper")
	@ResponseBody
	public GenericResultWrapper save(@RequestBody BroadcastWrapper broadcastWrapper) throws EpublisherException 
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		securityValidator.hasPublicationRightsForPublication(playlistService.getPublicationByPlaylistId(broadcastWrapper.getPlaylistId()));
		return createResult(broadcastService.save(broadcastWrapper));

	}
	

	
	/**
	 * Get broadcasts paginated URL: /BlazeDS/editor/broadcast/searchSummaries GET
	 * @param ascendingSortOrder boolean
	 * @param broadcastName string 
	 * @param createdBy string
	 * @param numberOfResults Integer
	 * @param sortField string
	 * @param startPage Integer
	 * @param templateName string
	 * @param userId Integer
	 * @param wayfinder boolean 
	 * @return the list of broadcasts including the total number of broadcasts
	 * @see BroadcastSearchResult
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
//	@Transactional
//	@RequestMapping(method = RequestMethod.GET, value = "searchSummaries")
//	@ResponseBody
//	public GenericResultWrapper searchBroadcastsSummaries(
//			@RequestParam(value="ascendingSortOrder", defaultValue="true") boolean ascendingSortOrder
//			, @RequestParam(value="broadcastName", required=false) String broadcastName
//			, @RequestParam(value="createdBy", required=false) String createdBy
//			, @RequestParam(value="numberOfResults", defaultValue="25") Integer numberOfResults
//			, @RequestParam(value="sortField", required=false) String sortField
//			, @RequestParam(value="startPage", defaultValue="0") Integer startPage
//			, @RequestParam(value="templateName", required=false) String templateName
//			, @RequestParam(value="userId", defaultValue="-1") Integer userId
//			, @RequestParam(value="wayfinder", defaultValue="false") boolean wayfinder
//		) throws EpublisherException
//	{
//		UserSession sessionUser = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		User user = userService.getUserById(sessionUser.getUserID());
//
//		if(createdBy != null && createdBy != " " ){
//			createdBy.trim();
//			if(createdBy.contains(" ")){
//				createdBy.replaceAll("\\s+", "|");
//			}
//		}
//
//		BroadcastFilter filter = new BroadcastFilter();
//		filter.setAscendingSortOrder(ascendingSortOrder);
//		filter.setBroadcastName(broadcastName);
//		filter.setCreatedBy(createdBy);
//		filter.setNumberOfResults(numberOfResults);
//		filter.setSortField(sortField);
//		filter.setStartPage(startPage);
//		filter.setTemplateName(templateName);
//		filter.setWayfinder(wayfinder);
//
//		if(userId != -1)
//			filter.setUserId(userId);
//
//		return createResult(broadcastService.searchBroadcastsSummaries(filter,sessionUser.getUserEmailaddress(),user));
//	}

	/**
	 * Get broadcasts paginated URL: /BlazeDS/editor/broadcast/searchSummaries POST
	 * 
	 * @param filter
	 * 			an instance of broadcast filter to search the broadcast.
	 * @return the list of broadcasts including the total number of broadcasts
	 * @see BroadcastSearchResult
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
//	@Transactional
//	@RequestMapping(method = RequestMethod.POST, value = "searchSummaries")
//	@ResponseBody
//	public GenericResultWrapper searchBroadcastsSummaries(@RequestBody BroadcastFilter filter) throws EpublisherException
//	{
//		UserSession sessionUser = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		User user = userService.getUserById(sessionUser.getUserID());
//
//		if(filter.getCreatedBy() != null && filter.getCreatedBy() != " " ){
//			filter.setCreatedBy(filter.getCreatedBy().trim());
//			if(filter.getCreatedBy().contains(" ")){
//				filter.setCreatedBy(filter.getCreatedBy().replaceAll("\\s+", "|"));
//			}
//		}
//		return createResult(broadcastService.searchBroadcastsSummaries(filter,sessionUser.getUserEmailaddress(),user));
//	}
	
	/**
	 * Gets all available templates. URL: /BlazeDS/editor/broadcast/allRoutes
	 * GET
	 * 
	 * @return all routes
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
//	@Transactional
//	@RequestMapping(method = RequestMethod.GET, value = "allRoutes")
//	@ResponseBody
//	public GenericResultWrapper getAllRoutes() throws EpublisherException
//	{
//		UserSession sessionUser = securityValidator.loadUserFromEPublisher();
//		securityValidator.isAdminOrHasReadPermission("routes-tab");
//		User user = userService.getUserById(sessionUser.getUserID());
//
//		return createResult(broadcastService.getAllRoutes(user));
//	}
	
	/**
	 * Saves the function group URL: /BlazeDS/editor/broadcast/saveRoute POST
	 * 
	 * @param route
	 *            the route to save.
	 * @return the saved route
	 * @see FunctionGroup
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "saveRoute")
	@ResponseBody
	public GenericResultWrapper saveRoute(@RequestBody Route route) throws EpublisherException
	{
//		securityValidator.isAdminOrHasWritePermission("routes-tab");
		return createResult(broadcastService.saveRoute(route));
	}
	
	/**
	 * Removes a Broadcast URL: /BlazeDS/editor/broadcast/deleteRouteById/{routeId} DELETE
	 * @param routeId is the id of the route to be deleted.
	 * 
	 * @return returns the status of delete
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.DELETE, value = "deleteRouteById/{routeId}")
	@ResponseBody
	public GenericResultWrapper deleteRouteById(@PathVariable Integer routeId) throws EpublisherException 
	{
//		securityValidator.isAdminOrHasWritePermission("routes-tab");
		broadcastService.deleteRoute(broadcastService.findRouteById(routeId));
		return createResult(EpublisherConstants.NULL);
	}
	
	
//	@RequestMapping(method = RequestMethod.GET, value = "getChildBroadcast/{broadcastId}")
//	@ResponseBody
//	public GenericResultWrapper getChildBroadcast(@PathVariable Integer broadcastId) throws EpublisherException
//	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		return createResult(broadcastService.getChildBroadcast(broadcastId,user.getUserEmailaddress()));
//	}
	

//	@RequestMapping(method = RequestMethod.POST, value = "getBroadcastSummary/{broadcastId}")
//	@ResponseBody
//	public GenericResultWrapper getBroadcastSummary(@PathVariable Integer broadcastId,@RequestBody BroadcastFilter filter) throws EpublisherException
//	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		if(filter.getCreatedBy() != null && filter.getCreatedBy() != " " ){
//			filter.setCreatedBy(filter.getCreatedBy().trim());
//			if(filter.getCreatedBy().contains(" ")){
//				filter.setCreatedBy(filter.getCreatedBy().replaceAll("\\s+", "|"));
//			}
//		}
//		return createResult(broadcastService.getSearchSummaryForSingleBroadcast(broadcastId,user.getUserEmailaddress(),filter));
//	}
	
	/**
	 * Returns a count of all internal published broadcasts per tenant
	 * URL:
	 * /BlazeDS/editor/playlist/countPublishedInternalBroadcasts
	 * {publicationId} GET
	 * 
	 * @param publicationId
	 * @return count of all internal published broadcasts per tenant
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "countPublishedInternalBroadcasts")
	@ResponseBody
	public GenericResultWrapper countPublishedInternalBroadcasts() throws EpublisherException
	{
		return createResult(broadcastService.countPublishedInternalBroadcasts());
	}
	
	/**
	 * Returns a count of all external published broadcasts per tenant
	 * URL:
	 * /BlazeDS/editor/playlist/countPublishedInternalBroadcasts
	 * {publicationId} GET
	 * 
	 * @param publicationId
	 * @return count of all external published broadcasts per tenant
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "countPublishedExternalBroadcasts")
	@ResponseBody
	public GenericResultWrapper countPublishedExternalBroadcasts() throws EpublisherException
	{
		return createResult(broadcastService.countPublishedExternalBroadcasts());
	}
}
