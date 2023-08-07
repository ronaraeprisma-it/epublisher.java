package nl.prismait.epublisher.java.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import nl.prismait.epublisher.java.business.comparator.ContentBlockComapratorOnContainerId;
import nl.prismait.epublisher.java.model.PlaylistScreengroupAndPublication;
//import nl.prismait.epublisher.java.model.User;
//import nl.prismait.epublisher.java.model.dashboard.schema.UserSession;
import nl.prismait.epublisher.java.model.dto.GenericResultWrapper;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.Broadcast;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.ContentBlock;
import nl.prismait.epublisher.java.model.narrowcasting.OutputChannelNarrowcastingNS;
import nl.prismait.epublisher.java.model.narrowcasting.Playlist;
import nl.prismait.epublisher.java.model.narrowcasting.PublicationNarrowcastingNS;
import nl.prismait.epublisher.java.service.PlaylistService;
//import nl.prismait.epublisher.java.service.SecurityValidator;
//import nl.prismait.epublisher.java.service.UserService;
import nl.prismait.epublisher.java.util.EpublisherConstants;

@Controller
@RequestMapping("editor/playlist")
public class PlaylistController extends BaseController
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PlaylistService playlistService;
	
//	@Autowired
//	private SecurityValidator securityValidator;
//
//	@Autowired
//	private UserService userService;
	
	Properties properties = new Properties();
	
	/**
	 * Saves the playlist URL: /BlazeDS/editor/playlist/save POST
	 * 
	 * @param playlist
	 *            the playlist to save
	 * @return the saved playlist
	 * @see Playlist
	 *@throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
//	@RequestMapping(method = RequestMethod.POST, value = "save/{publicationId}")
//	@ResponseBody
//	public GenericResultWrapper save(@RequestBody Playlist playlist, @PathVariable Integer publicationId) throws EpublisherException
//	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//		securityValidator.hasWriteAccess("Wijzig Playlist");
//
//		// Playlist submitted
//		if (playlist.getLastSubmittedBy() != null) {
//			securityValidator.hasWriteAccess("Indienen Playlist");
//			playlistService.checkMaxBroadcastLimitRule(user,playlist,"internal");
//		}
//
//		return createResult(playlistService.save(playlist,publicationId, user.getUserEmailaddress()));
//	}

	
	/**
	 * Removes the playlist and all previously published versions of it URL:
	 * /BlazeDS/editor/playlist/delete DELETE
	 * 
	 * @param playlist
	 *            the playlist to remove
	 * @return the status of delete
	 * @see Playlist
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
//	@RequestMapping(method = RequestMethod.DELETE, value = "delete")
//	@ResponseBody
//	@Transactional
//	public GenericResultWrapper remove(@RequestBody Playlist playlist,
//			@RequestParam(value="specific", required=false) String specific) throws EpublisherException
//	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//		securityValidator.hasWriteAccess("Verwijder Playlist");
//
//		playlistService.remove(playlist,user.getUserEmailaddress(),specific);
//		return createResult(EpublisherConstants.NULL);
//	}

	/**
	 * retrieves a playlist URL:/BlazeDS/editor/playlist/{playlistId}
	 * 
	 * @param playlistId
	 *            the playlist to retrieve
	 * @return the playlist
	 * @see Playlist
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "{playlistId}")
	@ResponseBody
	@Transactional
	public GenericResultWrapper getPlaylistById(@PathVariable Integer playlistId) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
		return createResult(playlistService.getPlaylistById(playlistId));
	}

	/**
	 * Returns all the current playlist for a publication.
	 * URL:/BlazeDS/editor/playlist/getCurrentPlaylistsForPublication/{
	 * playlistId} GET
	 * 
	 * @param publicationId
	 * 				the id of the publication to retrieve the current playlist
	 * @return all the current playlists for a publication.
	 * @see Playlist
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getCurrentPlaylistsForPublication/{publicationId}")
	@ResponseBody
	@Transactional
	public GenericResultWrapper getCurrentPlaylistsForPublication(@PathVariable Integer publicationId) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
		
		List<Playlist> playlist= playlistService.getCurrentPlaylistsForPublication(publicationId);
		if(playlist.isEmpty())
		{
			Playlist playlistObj = new Playlist();
			playlistObj.setPublication(new PublicationNarrowcastingNS());
			playlistObj.getPublication().setId(publicationId);
			playlistObj.getPublication().setOutputChannel(new OutputChannelNarrowcastingNS());
			playlistObj.getPublication().getOutputChannel().setName("narrowcastingNS");
			playlist.add(playlistObj);	
		}
		return createResult(playlist);
	}

	/**
	 * Adds a new broadcastWrapper with the selected broadcast to the selected
	 * playlist of the publication Extra synchronized to make sure the
	 * transaction in the child class is ended before the synchronized method
	 * 
	 * ends URL:/BlazeDS/editor/playlist/addBroadcastToPlaylist/{playlistId}/{
	 * broadcastId} POST
	 * 
	 * @param playlistId
	 * 			Playlist id to which the broadcast needs to be added
	 * @param broadcastId
	 * 			the broadcast id that needs to be added
	 * @return the updated playlist
	 * @see Playlist
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
//	@RequestMapping(method = RequestMethod.POST, value = "addBroadcastToPlaylist/{playlistId}/{broadcastId}")
//	@ResponseBody
//	@Transactional
//	public synchronized GenericResultWrapper addBroadcastToPlaylist(@PathVariable Integer playlistId, @PathVariable Integer broadcastId,
//			@RequestParam(value="broadcastPublicationMethod", required=false) String broadcastPublicationMethod,
//			@RequestParam(value="fromUitzendingen", required=false) boolean fromUitzendingen) throws EpublisherException
//	{
//		UserSession sessionUser = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		securityValidator.hasWriteAccess("Wijzig Uitzendingen");
//
//		//get user default broadcast publication method (kopie or origineel)
//		if(broadcastPublicationMethod == null || broadcastPublicationMethod.equals("undefined")) {
//			broadcastPublicationMethod = userService.loadBroadcastPublicationMethod(sessionUser.getTenantId());
//		}
//
//		return createResult(playlistService.addBroadcastToPlaylist(playlistId, broadcastId,broadcastPublicationMethod, sessionUser.getUserEmailaddress(),null, fromUitzendingen, false));
//
//	}

	/**
	 * Removes a Broadcast and it's Broadcastwrapper from the selected playlist
	 * of the publication Extra synchronized to make sure the transaction in the
	 * child class is ended before the synchronized method ends
	 * URL:/BlazeDS/editor/playlist/removeBroadcastFromPlaylist/{playlistId}/{
	 * broadcastId} POST
	 * 
	 * @param playlistId
	 * 			Playlist id to which the broadcast needs to be removed
	 * @param broadcastId
	 *			 the broadcast id that needs to be removed
	 * @return the updated playlist
	 * @see Playlist
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
//	@RequestMapping(method = RequestMethod.POST, value = "removeBroadcastFromPlaylist/{playlistId}/{broadcastId}")
//	@ResponseBody
//	@Transactional
//	public synchronized GenericResultWrapper removeBroadcastFromPlaylist(@PathVariable Integer playlistId, @PathVariable Integer broadcastId) throws EpublisherException
//	{
//		UserSession sessionUser = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		securityValidator.hasWriteAccess("Verwijder Uitzendingen");
//
//		// get the current playlist
//		Playlist currentPlaylist = playlistService.removeBroadcastFromPlaylist(playlistId, broadcastId, sessionUser.getUserEmailaddress());
//		return createResult(currentPlaylist);
//	}

	/**
	 * Prepares the preview of a playlist URL:
	 * /BlazeDS/editor/playlist/preparePreview POST
	 * 
	 * @param playlist
	 *            the playlist object to preview.
	 * @return the status
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "preparePreview")
	@ResponseBody
	public GenericResultWrapper preparePreview(@RequestBody Playlist playlist) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
		
		// Store the playlist in the user's session.
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		//Prepare video url for brightcove
		playlist.getBroadcastwrappers().forEach(wrapper->{
			playlistService.setStaticUrlForVideo(wrapper);
		});
		session.setAttribute(Playlist.PLAYLIST_TO_PREVIEW_KEY_NAME, playlist);

		return new GenericResultWrapper(HttpStatus.OK.value(), HttpStatus.OK.name());

	}
	
	/**
	 * Preview a playlist
	 * URL:/BlazeDS/previewplaylist GET
	 * @param lastPlayedBroadcastId Integer
	 * @return BroadcastWrapper
	 */
	@RequestMapping(value = "/previewplaylist", method = RequestMethod.GET)
	@ResponseBody
	public BroadcastWrapper previewPlaylist(@RequestParam(value = "lastplayed", required = false) Integer lastPlayedBroadcastId)
	{
		Date currentDate = new Date();
		
		// A playlist to be previewed is stored in the user's session.
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		Playlist playlist = (Playlist) session.getAttribute(Playlist.PLAYLIST_TO_PREVIEW_KEY_NAME);

		BroadcastWrapper wrapper = null;
		List<BroadcastWrapper> broadcastWrappersAsList = new ArrayList<BroadcastWrapper>();
		
		if (playlist != null && playlist.getBroadcastwrappers() != null)
		{
			//change broadcastWrappers from set to list
			broadcastWrappersAsList.addAll(playlist.getBroadcastwrappers());
			// Default to first active broadcast
			for (BroadcastWrapper bcw : broadcastWrappersAsList)
			{
				//extra check for no broadcast template case. 
				//ex: when we remove an app from tenant while broadcast was still linked to it. 
				if (bcw.isActive(currentDate) && bcw.getBroadcast().getTemplate() != null)
				{
					wrapper = bcw;
					break;
				}
			}

			// If we played one before, try to get the next
			if (lastPlayedBroadcastId != null && wrapper != null)
			{
				Integer index = -1;

				// find last played broadcast index
				for (BroadcastWrapper bcw : broadcastWrappersAsList)
				{
					if (bcw.getBroadcast().getId().equals(lastPlayedBroadcastId))
					{
						index = broadcastWrappersAsList.indexOf(bcw);
						break;
					}
				}

				// Go to next broadcast
				index++;

				// use next broadcast if active
				while (index < broadcastWrappersAsList.size())
				{
					BroadcastWrapper nextBroadcast = broadcastWrappersAsList.get(index);
					//extra check for no broadcast template case. 
					//ex: when we remove an app from tenant while broadcast was still linked to it. 
					if (nextBroadcast.isActive(currentDate) && nextBroadcast.getBroadcast().getTemplate() != null)
					{
						wrapper = nextBroadcast;
						break;
					}

					index++;
				}
				
			}
		}

		if (wrapper != null)
		{
			try {
				properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
			} catch (IOException e) {
				logger.warn("Exception loading project properties.", e);
			}
			
			wrapper.setPlaylistId(playlist.getId());
			wrapper.setVersion(properties.getProperty("version"));
			wrapper.setLocation("Preview");
			
			// Make sure contentblocks are sorted correctly
			if(null!=wrapper.getBroadcast().getContentBlocks())
			{
				//sort content block by container id, so that it appears ordered in front end 
				Set<ContentBlock> set =  new TreeSet<ContentBlock>(new ContentBlockComapratorOnContainerId());
				for(ContentBlock content : wrapper.getBroadcast().getContentBlocks())
				{
					set.add(content);
				}
				wrapper.getBroadcast().setContentBlocks(set);
			}
			
		}

		return wrapper;
	}
	
	
	/**
	 * Publishes the submitted playlist for viewing. URL:
	 * /BlazeDS/editor/playlist/publish/{playlistId} POST
	 * 
	 * @param playlistId
	 *            the id of the playlist to publish.
	 * @return  an instance of newPlaylist
	 * @see Playlist
	 *@throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
//	@RequestMapping(method = RequestMethod.POST, value = "publish/{playlistId}")
//	@ResponseBody
//	public GenericResultWrapper publish(@PathVariable Integer playlistId) throws EpublisherException
//	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		securityValidator.hasWriteAccess("Publiceer Playlist");
//		return createResult(playlistService.publish(playlistId,user));
//	}

	
	/**
	 * Unpublishes the submitted playlist for viewing. URL:
	 * /BlazeDS/editor/playlist/unpublish/{playlistId} POST
	 * 
	 * @param playlistId
	 *            the id of the playlist to unpublish.
	 * @return  an instance of newPlaylist
	 * @see Playlist
	 *@throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
//	@RequestMapping(method = RequestMethod.POST, value = "unpublish/{playlistId}")
//	@ResponseBody
//	public GenericResultWrapper unpublish(@PathVariable Integer playlistId) throws EpublisherException
//	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		securityValidator.hasWriteAccess("Publiceer Playlist");
//		return createResult(playlistService.unpublish(playlistId,user.getUserEmailaddress()));
//	}
	
	
	/**
	 * Returns all the active playlists for screengroups for a given
	 * publication. URL:
	 * /BlazeDS/editor/playlist/getActivePlaylistsAndScreengroupsForPublication/
	 * {publicationId} GET
	 * 
	 * @param publicationId
	 * 		to get active playlists and screengroups for the givenPublication id
	 * @return all the active playlists for screengroups for a publication.
	 * @see PlaylistScreengroupAndPublication
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getActivePlaylistsAndScreengroupsForPublication/{publicationId}")
	@ResponseBody
	public GenericResultWrapper getActivePlaylistsAndScreengroupsForPublication(@PathVariable Integer publicationId) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));

		return createResult(playlistService.getActivePlaylistsAndScreengroupsForPublication(publicationId));
	}
	
	/**
	 * Returns all the active playlists for screengroups for a given
	 * publication. URL:
	 * /BlazeDS/editor/playlist/getActivePlaylistsAndScreengroupsForPublication/
	 * {publicationId} GET
	 * 
	 * @param screenId
	 * 		to get active playlists and screengroups for the givenPublication id
	 * @return all the active playlists for screengroups for a publication.
	 * @see PlaylistScreengroupAndPublication
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getActivePlaylistBroadcastWithAssets/{screenId}")
	@ResponseBody
	public GenericResultWrapper getActivePlaylistBroadcastWithAssets(@PathVariable String screenId) throws EpublisherException
	{
	

		return createResult(playlistService.getActivePlaylistBroadcastWithAssets(screenId));
	}
	
	
	/**
	 * Returns all the active playlists for screengroups for a given
	 * publication. URL:
	 * /BlazeDS/editor/playlist/getActivePlaylistWithBroadcast/{publicationId} GET
	 * 
	 * @return all the active playlists for screengroups for a publication.
	 * @see PlaylistScreengroupAndPublication
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getAncestorPlaylistWithBroadcast/{publicationId}")
	@ResponseBody
	public GenericResultWrapper getAncestorPlaylistWithBroadcast(@PathVariable Integer publicationId) throws EpublisherException
	{
		return createResult(playlistService.getAncestorPlaylists(publicationId));
	}
	
	/**
	 * Returns all the active playlists  for a given
	 * publication. URL:
	 * /BlazeDS/editor/playlist/getActivePlaylistWithDuration/{publicationId} GET
	 * 
	 * @return all the active playlists for screengroups for a publication.
	 * @see PlaylistScreengroupAndPublication
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getActivePlaylistWithDuration/{publicationId}")
	@ResponseBody
	public GenericResultWrapper getActivePlaylistWithDuration(@PathVariable Integer publicationId) throws EpublisherException
	{
		return createResult(playlistService.getActivePlaylistsForPublication(publicationId));
	}
	
//	@RequestMapping(method = RequestMethod.POST, value = "discard/{playlistId}")
//	@ResponseBody
//	public GenericResultWrapper discard(@PathVariable Integer playlistId) throws EpublisherException
//	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		securityValidator.hasWriteAccess("Wijzig Playlist");
//		return createResult(playlistService.discard(playlistId,user.getUserEmailaddress()));
//	}
	
	@GetMapping(value = "checkIfExists/{playlistId}/{publicationId}")
	@ResponseBody
	public GenericResultWrapper checkIfExists(@PathVariable Integer playlistId,
			@PathVariable Integer publicationId) throws EpublisherException
	{
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
		return createResult(playlistService.checkIfExists(playlistId,publicationId));
	}
	
//	@PostMapping(value = "copyPlaylistToPublications/{playlistId}")
//	@ResponseBody
//	public GenericResultWrapper copyPlaylistToPublications(@RequestBody List<Integer> publicationIds,
//			@PathVariable Integer playlistId) throws EpublisherException
//	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		securityValidator.hasWriteAccess("Wijzig Playlist");
//		return createResult(playlistService.copyPlaylistToPublications(playlistId,publicationIds,user.getUserEmailaddress()));
//	}
	
//	@PostMapping(value = "share/{playlistId}")
//	@ResponseBody
//	public GenericResultWrapper share(@PathVariable Integer playlistId) throws EpublisherException
//	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		securityValidator.hasWriteAccess("Delen Playlist");
//
//		return createResult(playlistService.sharePlaylist(playlistId,user.getUserEmailaddress()));
//	}
	
//	@PostMapping(value = "unshare/{playlistId}")
//	@ResponseBody
//	public GenericResultWrapper unshare(@PathVariable Integer playlistId) throws EpublisherException
//	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//		securityValidator.hasWriteAccess("Delen Playlist");
//
//		return createResult(playlistService.unsharePlaylist(playlistId,user.getUserEmailaddress()));
//	}
	
	/**
	 * Returns all the history  for a given
	 * playlist. URL:
	 * /BlazeDS/editor/playlist/getPlaylistHistory/{playlistId}/{limit} GET
	 * 
	 * @return all the history from a certain playlist.
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
//	@RequestMapping(method = RequestMethod.GET, value = "getPlaylistHistory/{playlistId}/{limit}")
//	@ResponseBody
//	public GenericResultWrapper getPlaylistHistory(@PathVariable Integer playlistId,
//			@PathVariable Integer limit) throws EpublisherException
//	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
//
//		return createResult(playlistService.getPlaylistHistory(playlistId,limit));
//	}
	
	/**
	 * Returns the count of results for a given
	 * playlist. URL:
	 * /BlazeDS/editor/playlist/getPlaylistHistoryCount/{playlistId} GET
	 * 
	 * @return the count of results for a certain playlist.
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getPlaylistHistoryCount/{playlistId}")
	@ResponseBody
	public GenericResultWrapper getPlaylistHistoryCount(@PathVariable Integer playlistId) throws EpublisherException
	{
//		UserSession user = securityValidator.loadUserFromEPublisher();
//		securityValidator.checkRoles(Arrays.asList(EpublisherConstants.ROLE_USER));
		
		return createResult(playlistService.getPlaylistHistoryCount(playlistId));
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "getActivePlaylistIds/{screenId}")
	@ResponseBody
	public GenericResultWrapper getActivePlaylistIds(@PathVariable String screenId) throws EpublisherException
	{
	

		return createResult(playlistService.getActivePlaylistIds(screenId));
	}
}