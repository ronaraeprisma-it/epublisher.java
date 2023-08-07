// ***************************************************************************
//
// Copyright 2011, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL:$
// $Id: $
//
// ***************************************************************************
package nl.prismait.epublisher.java.web.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import freemarker.template.TemplateException;
import nl.prismait.epublisher.java.business.BaseController;
import nl.prismait.epublisher.java.dao.SessionIndentifierDAO;
import nl.prismait.epublisher.java.model.config.PropertiesUtil;
import nl.prismait.epublisher.java.model.dto.GenericResultWrapper;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.ExternalRSSLink;
import nl.prismait.epublisher.java.model.narrowcasting.Screen;
import nl.prismait.epublisher.java.model.narrowcasting.ScreenGroup;
import nl.prismait.epublisher.java.service.PlaylistService;
import nl.prismait.epublisher.java.service.ScreenService;

@Controller
public class NarrowcastingController extends BaseController
{
	private static final Logger LOG = LoggerFactory.getLogger(NarrowcastingController.class);
	Properties properties = new Properties();
	
	@Autowired
	PlaylistService playlistService;

	@Autowired
	ScreenService screenService;

//	@Autowired
//	@Qualifier("web")
//	OutputChannelWeb outputChannelWeb;
	
	@Autowired
	public SessionIndentifierDAO sessionDao;

	@ModelAttribute("redirect_base")
	public String populateRedirectBase()
	{
		return new StringBuilder("/").append(PropertiesUtil.getProperty("epublisher.blazeds.contextroot")).toString();
	}

	
	/**
	 * URL:/BlazeDS/broadcastviewer GET
	 * @param preview String 
	 * @param model spring model object
	 * @param request http response
	 * @return string
	 */
	/**
	 * URL:/BlazeDS/broadcastviewer GET
	 * @param model spring model object
	 * @param request http response
	 * @return string
	 */
	@RequestMapping(value = "/broadcastviewer", method = RequestMethod.GET)
	public String broadcastViewer(
			Model model
			, HttpServletRequest request
			) throws IOException, TemplateException
	{
		Map<String, Object> map = new HashMap<>();
        map.put("accountId", PropertiesUtil.getProperty("accountId"));
        map.put("playerId", PropertiesUtil.getProperty("playerId"));
        map.put("isSecure", request.isSecure());
        map.put("redirect_base", populateRedirectBase());
        map.put("version", this.getClass().getPackage().getImplementationVersion());
        map.put("tenantID", sessionDao.getTenantIdByURL(request.getServerName()));
	
		return playlistService.getTemplateForScreen(map,request);
	}

/*	@RequestMapping(value = "/broadcastviewer", method = RequestMethod.GET)
	public String broadcastViewer(
			@RequestParam(value = "preview", required = false) String preview
			, Model model
			, HttpServletRequest request
			)
	{
		String uri = request.getScheme() + "://" +   // "http" + "://
	             request.getServerName() ;
		model.addAttribute("isSecure", request.isSecure());
		model.addAttribute("preview", preview);
		model.addAttribute("redirect_base", populateRedirectBase());
		model.addAttribute("accountId", PropertiesUtil.getProperty("accountId"));
		model.addAttribute("playerId", PropertiesUtil.getProperty("playerId"));
		return "redirect:" + uri+"/plugins/narrowcastplayer/assets/narrowcasting.html";
	}*/

	/**
	 * Retrieve next broadcast in currently playing playlist for a screen
	 * URL:/BlazeDS/retrievebroadcast/{screenId}/{lastPlayedBroadcastPlaylistId}/{lastPlayedBroadcastId} GET
	 * @param screenId String
	 * @param lastPlayedBroadcastPlaylistId Integer
	 * @param lastPlayedBroadcastId Integer
	 * @param model  model object
	 * @param response http response
	 * @return BroadcastWrapper
	 * @throws IOException if any IO exception occurs
	 */
	@RequestMapping(value = "/retrievebroadcast/{screenId}/{lastPlayedBroadcastPlaylistId}/{lastPlayedBroadcastId}", method = RequestMethod.GET)
	@ResponseBody
	public BroadcastWrapper retrieveBroadcastForScreen(
			@PathVariable("screenId") String screenId,
			@PathVariable("lastPlayedBroadcastPlaylistId") Integer lastPlayedBroadcastPlaylistId,
			@PathVariable("lastPlayedBroadcastId") Integer lastPlayedBroadcastId,
			Model model, HttpServletRequest request) throws IOException
	{
		List<String> rssUrlList = new ArrayList<>(); 
		// Update last request of screen
		screenService.updateLastRequestOfScreen(screenId);

		// Get the next Broadcast
		BroadcastWrapper broadcast = playlistService.getNextBroadcast(screenId, lastPlayedBroadcastPlaylistId, lastPlayedBroadcastId);
		if(broadcast== null)
			broadcast = new BroadcastWrapper();

		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
		} catch (IOException e) {
			LOG.warn("Exception loading project properties.", e);
		}
		// Put the ePublisher version into the broadcast so the client can check whether it needs to update itself.
		broadcast.setVersion(properties.getProperty("version"));

		// Get screen data and put the location of the screen in the broadcast data
		Screen screen = screenService.getScreenByDisplayId(screenId);
		ScreenGroup screenGroup = screenService.getScreenGroupforScreen(screenId);
		broadcast.setLocation(screen.getDisplayName());

		// Get screen data and put the min video resolution in the broadcast data		
		broadcast.setMinVideoResolutionHeight(screen.getMinVideoResolutionHeight());
		broadcast.setMinVideoResolutionWidth(screen.getMinVideoResolutionWidth());
		if(screen.getExternalRSSLink() != null && !screen.getExternalRSSLink().isEmpty()) {
			for(ExternalRSSLink rssUrl: screen.getExternalRSSLink()) {
				rssUrlList.add(rssUrl.getUrl());
				
			}
		}
		
		broadcast.setRssUrls(rssUrlList);
		broadcast.setScreenBackgroundImage(screen.getBackgroundImage() != null ? screen.getBackgroundImage().getUrl(): null);
		broadcast.setScreengroupBackgroundImage(screenGroup.getBackgroundImage() != null ? screenGroup.getBackgroundImage().getUrl(): null);
		broadcast.setIframeUrl(screen.getIframeUrl() != null ? screen.getIframeUrl(): null);
		
		//set tenant ID for Matomo script
		String tenant = sessionDao.getTenantIdByURL(request.getServerName());
		broadcast.setTenantId(tenant);
		
		return broadcast;
	}

	
	/**
	 * Retrieve first broadcast of current playing playlist for a screen
	 * URL:/BlazeDS/retrievebroadcast/{screenId} GET
	 * @param screenId String
	 * @param model model object
	 * @param response http response
	 * @return BroadcastWrapper
	 * @throws IOException if any IO exception occurs
	 */
	@RequestMapping(value = "/retrievebroadcast/{screenId}", method = RequestMethod.GET)
	@ResponseBody
	public BroadcastWrapper retrieveFirstBroadcastForScreen(@PathVariable("screenId") String screenId, Model model, HttpServletRequest request) throws IOException
	{
		return retrieveBroadcastForScreen(screenId, null, null, model, request);
	}

	/**
	 * Generate a functional URL from a given header
	 * 
	 * @param request
	 * @return string
	 */
	private String getSafeRedirectBase(HttpServletRequest request)
	{
		String header = request.getHeader("Host");

		// Use the configured URL if the header's an IP address or empty.
		if (InetAddressValidator.getInstance().isValid(header.split(":")[0]) || header.length() == 0)
			header = PropertiesUtil.getProperty("epublisher.baseurl");
		else
			header = new StringBuilder(request.getScheme()).append("://").append(header).append("/").toString();

		return new StringBuilder(header).append(PropertiesUtil.getProperty("epublisher.blazeds.contextroot")).toString();
	}
	
	/**
	 * Disable screen ID debug feature
	 * URL:/BlazeDS/narrowcasting/disableScreenIdDebug/{screenId} GET
	 * @param screenId String
	 * @return String
	 * @throws EpublisherException 
	 */
	@RequestMapping(value = "/disableScreenIdDebug/{screenId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean disableScreenIdDebug(@PathVariable("screenId") String screenId) throws EpublisherException
	{
		Screen screen = screenService.getScreenByDisplayId(screenId);
		System.out.println("LLLLLLLLLLL: " + screenId);
		screen.setScreenIdDebug(false);
		screenService.save(screen);
		
		return screen.getScreenIdDebug();
	}
	/**
	 * Get all screen settings
	 * URL:/BlazeDS/narrowcasting/getScreenSettings/{screenId} GET
	 * @param screenId String
	 * @return String
	 * @throws EpublisherException 
	 */
	@RequestMapping(value = "/getScreenSettings/{screenId}", method = RequestMethod.GET)
	@ResponseBody
	public Screen getScreenSettings(@PathVariable("screenId") String screenId) throws EpublisherException
	{
		System.out.println("RRRRRRRRRRRRRRRRRRRR: " + screenId);
		Screen screen = screenService.getScreenByDisplayId(screenId);
		
		return screen;
	}
	
	/**
	 * just a heartbeat method for checking connection in narrowcasting screens
	 * @return ""
	 * @throws EpublisherException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getNarrowcastingResponse")
	@ResponseBody
	public GenericResultWrapper getNarrowcastingResponse() throws EpublisherException
	{
		return createResult("");
	}

	@RequestMapping(method = RequestMethod.GET, value = "getBlazeDSVersion")
	@ResponseBody
	public GenericResultWrapper getBlazeDSVersion() throws EpublisherException {
		return createResult(properties.getProperty("version"));
	}
}
