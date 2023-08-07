package nl.prismait.epublisher.java.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.cache.URLTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import nl.prismait.epublisher.java.business.comparator.ContentBlockComapratorOnContainerId;
import nl.prismait.epublisher.java.dao.IBroadcastDAO;
import nl.prismait.epublisher.java.dao.IPlaylistDAO;
import nl.prismait.epublisher.java.dao.IPublicationDAO;
//import nl.prismait.epublisher.java.dao.IUserDAO;
import nl.prismait.epublisher.java.dao.SessionIndentifierDAO;
import nl.prismait.epublisher.java.model.PlaylistScreengroupAndPublication;
import nl.prismait.epublisher.java.model.PlaylistsPublicationsAndBroadcasts;
import nl.prismait.epublisher.java.model.Publication;
import nl.prismait.epublisher.java.model.config.PropertiesUtil;
//import nl.prismait.epublisher.java.model.dashboard.schema.UserSession;
import nl.prismait.epublisher.java.model.exception.EpublisherConcurrencyException;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.ActivePlaylistWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.Broadcast;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastCacheWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastNarrowcastingNS;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastPlay;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.ContentBlock;
import nl.prismait.epublisher.java.model.narrowcasting.EPublisherVideo;
import nl.prismait.epublisher.java.model.narrowcasting.Geolocation;
import nl.prismait.epublisher.java.model.narrowcasting.Playlist;
import nl.prismait.epublisher.java.model.narrowcasting.PublicationNarrowcastingNS;
import nl.prismait.epublisher.java.model.narrowcasting.Screen;
import nl.prismait.epublisher.java.model.narrowcasting.ScreenGroup;
import nl.prismait.epublisher.java.model.narrowcasting.Train;
import nl.prismait.epublisher.java.model.nstreinen.BroadcastNSTreinen;
import nl.prismait.epublisher.java.model.nstreinen.PlaylistNSTreinen;
import nl.prismait.epublisher.java.model.nstreinen.PublicationNSTreinen;
import nl.prismait.epublisher.java.model.wayfinder.BroadcastWayfinder;
import nl.prismait.epublisher.java.model.wayfinder.PublicationWayfinder;

@Service
public class PlaylistService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPlaylistDAO playlistDAO;

	@Autowired
	private IBroadcastDAO broadcastDAO;

	@Autowired
	private IPublicationDAO publicationDAO;

	@Autowired
	private ScreenService screenService;

	@Autowired
	private BroadCastService broadcastService;

	@Autowired
	ServletContext context;

	@Autowired
	public SessionIndentifierDAO sessionDao;

//	@Autowired
//	private IUserDAO userDAO;	@Autowired
//	private IUserDAO userDAO;

	/**
	 * Saves the playlist
	 *
	 * @param playlist
	 *            the playlist to save
	 * @return the saved playlist
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	public Playlist save(Playlist playlist,Integer publicationId,String email) throws EpublisherException
	{
		Publication playlistPublication = null;

		// Fix from & to && - Sonarqube
		if(playlist.getPublication() == null && publicationId != null) {
			playlistPublication = publicationDAO.getPublicationById(publicationId);
			playlist.setPublication(playlistPublication);
		}

		// Make sure screengroups don't lose their publication when added to playlist 2nd level
		if(playlist.getWriteScreengroups() != null && !playlist.getWriteScreengroups().isEmpty()) {
			for (ScreenGroup sg : playlist.getWriteScreengroups())
			{
				sg.setPublication(playlistPublication);
			}

			playlist.setScreengroups(playlist.getWriteScreengroups());
		}

		Playlist savedPlaylist = null;

		try
		{
			savedPlaylist = playlistDAO.save(playlist,email);

			if (logger.isDebugEnabled())
			{
				if (playlist.getId() == null)
					logger.debug("Playlist " + playlist.getName() + "  is created.");
				else
					logger.debug("Playlist " + playlist.getName() + "  is updated.");
			}
		}
		catch (HibernateOptimisticLockingFailureException e)
		{
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException("Een fout is opgetreden. \nEen andere gebruiker heeft deze playlist tussentijds aangepast.\nVervers het publicatie scherm en probeer het opnieuw.", e);
		}
		catch (DataIntegrityViolationException e)
		{
			if (e.getCause() instanceof ConstraintViolationException)
				throw new EpublisherException("Playlist met deze naam bestaat al.", e);
			else
				throw new EpublisherException("Error saving Playlist", e);
		}
		finally
		{

		}

		return savedPlaylist;
	}

	/**
	 * Removes the playlist and all previously published versions of it
	 *
	 * @param playlist
	 *            the playlist to remove
	 * @param specific
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	@Transactional
	public void remove(Playlist playlist,String email, String specific) throws EpublisherException
	{
		try
		{

			Playlist playlistToDelete = playlistDAO.getPlaylistById(playlist.getId());

			//NSTrein playlists also get moved into another directory when deleted
			//we only delete a NSTrein playlist which has a ancestorPlaylistID
			//if it does not have ancestorPlaylistID, then it was never published, so it never existed in the OBIS sync bucket
			if(playlist.getParentId() != null && playlist instanceof PlaylistNSTreinen) {
				String playlistID;
				playlistID = playlist.getUuid() != null ? playlist.getUuid() :  playlist.getParentId().toString();
				Boolean microserviceResponse = false;
				try {
					microserviceResponse = Boolean.valueOf(playlistDAO.deleteTreinPlaylist(playlistID));
				} catch (IOException e) {
					logger.info("Deleting NS Trein playlist " + playlist.getName() + " has failed");
					e.printStackTrace();
				}
				if(!microserviceResponse) {
					throw new EpublisherException("Unpublishing of NSTrein playlist has failed.");
				}
			}

			if(specific != null && specific.equalsIgnoreCase("true")) {
				// delete only the specific given playlist
				playlistToDelete.setDeleted(true);
				playlistDAO.save(playlistToDelete,email);
			} else {
				// loop through all version of this playlist, set them to be deleted, and save them.
				for (Playlist currentPlaylist : playlistDAO.getAllVersionsOfPlaylist(playlistToDelete))
				{
					currentPlaylist.setDeleted(true);
					playlistDAO.save(currentPlaylist,email);
				}
			}
		}
		catch (HibernateOptimisticLockingFailureException e)
		{
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException("Een fout is opgetreden. \nEen andere gebruiker heeft deze playlist tussentijds aangepast.\nVervers het publicatie scherm en probeer het opnieuw.", e);
		}
		catch (DataIntegrityViolationException e)
		{
			throw new EpublisherException("Error removing Playlist", e);
		}
		finally
		{
			if (logger.isDebugEnabled())
				logger.debug("Playlist " + playlist.getName() + "  is removed.");
		}

	}

	/**
	 * retrieves a playlist
	 *
	 * @param playlistId
	 *            the playlist to retrieve
	 * @return the playlist
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	@Transactional
	public Playlist getPlaylistById(Integer playlistId) throws EpublisherException
	{
		Playlist playlist = playlistDAO.getPlaylistById(playlistId);

		return playlist;
	}

	/**
	 * retrieves a playlist by name
	 *
	 * @param playlistName
	 *            the playlist to retrieve
	 * @return the playlist
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	@Transactional
	public Boolean getPlaylistByName(String playlistName) throws EpublisherException
	{
		return playlistDAO.getPlaylistByName(playlistName);
	}

	/**
	 * Returns all the current playlist for a publication.
	 *
	 * @param publicationId
	 * 				the id of the publication to retrieve the current playlist
	 *
	 * @return all the current playlists for a publication.
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	public List<Playlist> getCurrentPlaylistsForPublication(Integer publicationId) throws EpublisherException
	{
		List<Playlist> playlists =playlistDAO.getCurrentPlaylistsForPublication(publicationId);
		/*
		 * This is a temporary fix for a handicapped frontend
		 */
		return getAllowedContentBlock(playlists);
	}


	@SuppressWarnings("unchecked")
	private List<Playlist> getAllowedContentBlock(List<Playlist> playlists )
	{
		for(Playlist playlist: playlists)
		{
			if(playlist.getBroadcastwrappers()!=null)
			{
				for(BroadcastWrapper wrapper: playlist.getBroadcastwrappers())
				{
					if(wrapper.getBroadcast()!= null && wrapper.getBroadcast().getContentBlocks()!=null)
					{
						Set<ContentBlock> set =  new TreeSet<ContentBlock>(new ContentBlockComapratorOnContainerId());
						for(ContentBlock content : wrapper.getBroadcast().getContentBlocks())
						{
							set.add(content);
						}
						wrapper.getBroadcast().setContentBlocks(set);
					}
				}
			}
		}
		return playlists;
	}



	/**
	 * Adds a new broadcastWrapper with the selected broadcast to the selected
	 * playlist of the publication Extra synchronized to make sure the
	 * transaction in the child class is ended before the synchronized method
	 * ends
	 *
	 * @param playlistId
	 * 			Playlist id to which the broadcast needs to be removed
	 * @param broadcastId
	 *			 the broadcast id that needs to be removed
	 * @return the updated playlist
	 * @see Playlist
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	@Transactional
	public synchronized Playlist addBroadcastToPlaylist(Integer playlistId, Integer broadcastId,
														String broadcastPublicationMethod, String email, Integer broadcastWrapperId, boolean fromUitzendingen,
														boolean isAdvertisement) throws EpublisherException
	{
		Playlist currentPlaylist = playlistDAO.getPlaylistById(playlistId);

		// create the broadcastwrappers array if necessary
		if (currentPlaylist.getBroadcastwrappers() == null)
		{
			currentPlaylist.setBroadcastwrappers(new TreeSet<>());
		}

		// check if the broadcast is not already present in the current playlist
		boolean broadcastAlreadyPresent = false;

		for (BroadcastWrapper broadcastWrapper : currentPlaylist.getBroadcastwrappers())
		{
			if (broadcastWrapper.getBroadcast() != null && broadcastWrapper.getBroadcast().getId().intValue() == broadcastId.intValue())
			{
				broadcastAlreadyPresent = true;
				break;
			}
		}

		// only add the article to the current edition if it is not already present
		if (!broadcastAlreadyPresent)
		{
			BroadcastWrapper newBroadcastWrapper = new BroadcastNarrowcastingNS();
			Broadcast broadcast = broadcastDAO.getBroadcastById(broadcastId);

			if(currentPlaylist.getPublication() instanceof PublicationWayfinder) {
				newBroadcastWrapper = new BroadcastWayfinder();
			}
			if(currentPlaylist.getPublication() instanceof PublicationNSTreinen) {
				newBroadcastWrapper = new BroadcastNSTreinen();
			}

			Broadcast newBroadcast;

			if(broadcastPublicationMethod.equals("kopie")) {
				// clone broadcast before assigning it to the playlist (NS_EPB-1265)
				newBroadcast = broadcast.copyObject();
				broadcastService.save(newBroadcast);
				newBroadcastWrapper.setBroadcastParentId(broadcast.getId());
			} else {
				// original publication method
				newBroadcast = broadcast;
			}

			// set advertisement id
			if(isAdvertisement) {
				newBroadcastWrapper.setAdvertisementId((broadcast.getParentId() != null ? broadcast.getParentId().toString() : broadcast.getId().toString()));
			}

			// check broadcast validity
			newBroadcastWrapper.setActive(checkBroadcastValidityAgainstPublicationSettings(newBroadcast, (PublicationNarrowcastingNS) currentPlaylist.getPublication()));

			//set state isAdded in broadcastwrapper
			newBroadcastWrapper.setAdded(true);
			newBroadcastWrapper.setModified(false);
			newBroadcastWrapper.setDeleted(false);
			newBroadcastWrapper.setBroadcast(newBroadcast);
			//set broadcastwrapper dtype same as playlist/publication dtype: PublicationNewsletterNS / PublicationNSTreinen
			//newBroadcastWrapper.setDtype(currentPlaylist.getDtype());

			// reorder the broadcastWrappers
			if (currentPlaylist.getBroadcastwrappers().isEmpty()) {
				//first broadcast needs to have 1 order instead of 0
				newBroadcastWrapper.setOrderOfBroadcast(1);
			} else {
				//add new broadcast to end of playlist
				int newOrder = currentPlaylist.getBroadcastwrappers().size() + 1;
				newBroadcastWrapper.setOrderOfBroadcast(newOrder);
			}

			currentPlaylist.getBroadcastwrappers().add(newBroadcastWrapper);

			// check playlist exceeds allowed duration
			if (exceedsAllowedDuration(currentPlaylist)) {
				for(BroadcastWrapper bw : currentPlaylist.getBroadcastwrappers()) {
					bw.setActive(false);
				}
			}

			// save the changed edition
			currentPlaylist = save(currentPlaylist,null,email);

			if (logger.isDebugEnabled()) {
				logger.debug("For playlist '" + currentPlaylist.getName() + "' broadcast '" + broadcast.getName() + "' is added.");
			}
		}

		return currentPlaylist;

	}

	private boolean checkValidityVideoBlocksAgainstPublicationSettings(Broadcast broadcast, PublicationNarrowcastingNS publication) {
		boolean valid = true;
		// check if video requirements match focusing on the negative outcome
		if (broadcast != null && broadcast.getContentBlocks() != null && !broadcast.getContentBlocks().isEmpty()){
			for (ContentBlock cb : broadcast.getContentBlocks()) {
				if (cb instanceof EPublisherVideo) {
					EPublisherVideo videoBlock = (EPublisherVideo) cb;
					// publication defined but video contentblocks sizes not defined
					if (videoBlock.getVideoHeight() == null && publication.getVideoHeight() != null
							|| (videoBlock.getVideoWidth() == null && publication.getVideoWidth() != null)) {
						valid = false;
					} else if (videoBlock.getVideoMinrate() == null && publication.getVideoMinRate() != null
							|| (videoBlock.getVideoMaxrate() == null && publication.getVideoMaxRate() != null)) {
						valid = false;
					}
					// both publication and contentblock specs defined
					else if ((videoBlock.getVideoHeight() != null && publication.getVideoHeight() != null
							&& videoBlock.getVideoHeight().intValue() != publication.getVideoHeight().intValue() )
							|| (videoBlock.getVideoWidth() != null && publication.getVideoWidth() != null
							&& videoBlock.getVideoWidth().intValue() != publication.getVideoWidth().intValue())) {
						valid = false;
					} else if ((videoBlock.getVideoMinrate() != null && publication.getVideoMinRate() != null
							&& videoBlock.getVideoMinrate().intValue() < publication.getVideoMinRate().intValue() )
							|| (videoBlock.getVideoMaxrate() != null && publication.getVideoMaxRate() != null
							&& videoBlock.getVideoMaxrate().intValue() > publication.getVideoMaxRate().intValue())) {
						valid = false;
					}
					// videos shorter than the publication broadcast duration
					else if (publication.getBroadcastDuration() != null && videoBlock.getDuration() != null
							&& videoBlock.getDuration() < publication.getBroadcastDuration()) {
						valid = false;
					}
				}
			}
		}
		return valid;
	}

	private boolean checkBroadcastValidityAgainstPublicationSettings(Broadcast broadcast, PublicationNarrowcastingNS publication) {
		// check if publication broadcast duration (if set) matches broadcast duration
		if (publication.getBroadcastDuration() != null && broadcast.getDuration() != publication.getBroadcastDuration()) {
			return false;
		}
		// Check content blocks
		return checkValidityVideoBlocksAgainstPublicationSettings(broadcast, publication);
	}

	/**
	 * Removes a Broadcast and it's Broadcastwrapper from the selected playlist
	 * of the publication Extra synchronized to make sure the transaction in the
	 * child class is ended before the synchronized method ends
	 *
	 * @param playlistId
	 * 			Playlist id to which the broadcast needs to be removed
	 * @param broadcastId
	 *			 the broadcast id that needs to be removed
	 * @return the updated playlist
	 * @see Playlist
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	@Transactional
	public synchronized Playlist removeBroadcastFromPlaylist(Integer playlistId, Integer broadcastId, String email) throws EpublisherException
	{
		// get the current playlist
		Playlist currentPlaylist = playlistDAO.getPlaylistById(playlistId);
		// get broadcast children clones from the specific playlist
		Integer childBroadcast = broadcastDAO.getBroadcastChildIdFromPlaylist(broadcastId, playlistId);
		Boolean isBroadcastPublished = false;
		//check if broadcast is part of published playlist
		if(currentPlaylist.getAncestorPlaylistId() != null) {
			isBroadcastPublished = isBroadcastPartOfPublishedPlaylist(currentPlaylist.getAncestorPlaylistId(), broadcastId);
		}
		BroadcastWrapper wrapperToBeDeleted = null;


		boolean broadcastwrapperRemoved = false;

		// wrappers to be removed
		for(BroadcastWrapper wrapper:currentPlaylist.getBroadcastwrappers())
		{
			// clone or parent without clones being removed OR parent with clones being removed
			if ( (childBroadcast == null && wrapper.getBroadcast() != null && wrapper.getBroadcast().getId().equals(broadcastId) )
					|| ( childBroadcast != null && wrapper.getBroadcast() != null &&
					wrapper.getBroadcast().getParentId() != null &&
					wrapper.getBroadcast().getParentId().equals(broadcastId)) )
			{
				if(isBroadcastPublished) {
					wrapper.setDeleted(true);
					wrapper.setAdded(false);
					wrapper.setModified(false);
					broadcastwrapperRemoved = true;
				}else {
					wrapperToBeDeleted = wrapper;
				}
			}
		}

		// only save the current playlist if we made changes
		if (broadcastwrapperRemoved && isBroadcastPublished)
		{
			// check broadcastwrapper validity against publication settings
			PublicationNarrowcastingNS publication = (PublicationNarrowcastingNS) currentPlaylist.getPublication();
			if (publication.getMaxPlaylistDuration() != null) {
				if (!exceedsAllowedDuration(currentPlaylist)) {
					for(BroadcastWrapper bw : currentPlaylist.getBroadcastwrappers()) {
						bw.setActive(checkBroadcastValidityAgainstPublicationSettings(bw.getBroadcast(), publication));
					}
				}
			}

			// save
			currentPlaylist = save(currentPlaylist,null,email);
		}
		else if(!isBroadcastPublished)
		{
			//if it is not published, we remove the wrapper directly, without showing a change in current playlist
			//if it is published, we remove the wrapper and show a changed playlist state
			//we also need to re-order the remaining broadcast wrappers
			ArrayList<BroadcastWrapper> wrapperList = new ArrayList<>(currentPlaylist.getBroadcastwrappers());
			wrapperList.remove(wrapperToBeDeleted);
			for (Integer i = 0; i < wrapperList.size(); i++) {
				wrapperList.get(i).setOrderOfBroadcast(i + 1);
			}

			SortedSet<BroadcastWrapper> wrapperSet = new TreeSet<>(wrapperList);
			currentPlaylist.setBroadcastwrappers(wrapperSet);

			currentPlaylist = save(currentPlaylist, null, email);
		}

		return currentPlaylist;
	}

	public Boolean isBroadcastPartOfPublishedPlaylist(Integer ancestorPlaylistId, Integer broadcastId )
	{
		Boolean isBroadcastPublished = false;
		//get broadcast we are trying to remove
		Broadcast currentBroadcast = broadcastDAO.getBroadcastById(broadcastId);
		//get ancestor playlist(published) and check if currentBroadcast exists
		Playlist ancestorPlaylist = playlistDAO.getPlaylistById(ancestorPlaylistId);

		//check if the broadcast we are trying to remove is already published in this playlist's ancestor
		if(ancestorPlaylist != null && !ancestorPlaylist.getBroadcastwrappers().isEmpty()) {
			isBroadcastPublished = ancestorPlaylist.getBroadcastwrappers()
					.stream()
					.filter(broadcastWrapper ->
							(broadcastWrapper.getBroadcast().getParentId()!=null?broadcastWrapper.getBroadcast().getParentId():broadcastWrapper.getBroadcast().getId())
									.equals
											(currentBroadcast.getParentId()!=null?currentBroadcast.getParentId():broadcastId))
					.findAny()
					.isPresent();
		}

		return isBroadcastPublished;
	}


	/**
	 * Publishes the submitted playlist for viewing.
	 *
	 * Playlists have 2 relevant and many irrelevant versions:
	 * 1. the published version with the delete flag set to false, which is the version
	 *    that will show on the screens.
	 * 2. the unpublished version with the delete flag set to false, which is the version
	 *    that is under edit and used for preview.
	 * 3. many published versions with the delete flag set to true.
	 *
	 * Until NS_EPB-1178 matching released and unreleased versions of a playlist happened
	 * by name. But since that resulted in duplication of the playlist when the name was
	 * changed, matching the published and unpublished version of a playlist now happens
	 * using the AncestorPlaylistId property, which tracks the published version of an
	 * unpublished playlist
	 *
	 * Schematically, with a playlist identified as name(ancestor)[deleted], the sequence
	 * of publishing A, renaming it to C and publisheing C looks like:
	 *
	 * Start   :  A(null)[false]
	 *              |
	 * Publish A-]  |
	 *              |
	 *            A*(null)[false] -]  A(A)[false]
	 *                  |                 |
	 * Edit A to C -]   |                 |
	 *                  |                 |
	 *            A*(null)[false]     C(A)[false]
	 *                  |                 |
	 * Publish C -]     |                 |
	 *                  |                 |
	 *            A*(null)[true]     C*(A)[false]  -]  C(A)[false]
	 *
	 *
	 * @param playlistId
	 *            the id of the playlist to publish.
	 * @param email
	 * @return newPlaylist
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	public Playlist publish(Integer playlistId) throws EpublisherException//, UserSession user) throws EpublisherException
	{
		Playlist currentPlaylist = playlistDAO.getPlaylistById(playlistId);

		// check publication max active broadcasts limit
	//	this.checkMaxBroadcastLimitRule(user,currentPlaylist,"internal");

		// validate the playlist priority level allowed for user
//		validatePlaylistPriorityForUser(currentPlaylist,user.getUserEmailaddress());

		Playlist newPlaylist = null;
		boolean hasDeletedBroadcasts = false;

		// Retrieve any previously published playlists from the database.
		// This based on the same name for a single playlist. If the name has to be dynamic an extra ID property has to be
		// added to a playlist to make it unique.
		List<Playlist> playlists = playlistDAO.getPreviousPlaylistsByNameAndId(currentPlaylist);

		// Loop previous playlists on publish
		for (Playlist oldPlayList : playlists)
		{
			// Set deleted property to true
			oldPlayList.setDeleted(true);
			oldPlayList.setPublication(null);
			//we do not share the previous playlists
			oldPlayList.setShared(false);
//			playlistDAO.save(oldPlayList,user.getUserEmailaddress());
		}

		try
		{
			// remove deleted broadcastwrappers (use iterator to avoid ConcurrentModificationException)
			Iterator<BroadcastWrapper> broadcastToDelete = currentPlaylist.getBroadcastwrappers().iterator();

			while (broadcastToDelete.hasNext()) {
				BroadcastWrapper wrapper = broadcastToDelete.next();

				if (wrapper.isDeleted()) {
					broadcastToDelete.remove();
					// set flag here that we need to re-order remaining broadcasts since we deleted
					// one or more
					if (!hasDeletedBroadcasts) {
						hasDeletedBroadcasts = true;
					}
				}
			}

			//for NSTreinen playlists we send Publication/Playlists stringyfied
			//to a microservice that will further process it
			if(currentPlaylist instanceof PlaylistNSTreinen) {
				ObjectMapper mapper = new ObjectMapper();
				String playlistString = mapper.writeValueAsString(currentPlaylist);
				//not in use yet, after microservice is deployed and we have url then we enable this part
				if(playlistString != null) {
					Boolean response = false;
					response = playlistDAO.publishTreinPlaylist(playlistString);

					if(response == null || response == false) {
						throw new EpublisherException("Publishing of NS Trein playlist failed." + currentPlaylist.getName());
					}

					logger.info("Publishing of NS Trein playlist response is: " + response.toString());
				}
			}

			// fix for SAAS-889/SAAS-1252, when removing a broadcast we also need to update
			// the order
			// of all other broadcasts to avoid issues.
			if (hasDeletedBroadcasts) {
				ArrayList<BroadcastWrapper> wrapperList = new ArrayList<>(currentPlaylist.getBroadcastwrappers());

				for (Integer i = 0; i < wrapperList.size(); i++) {
					wrapperList.get(i).setOrderOfBroadcast(i + 1);
				}

				SortedSet<BroadcastWrapper> wrapperSet = new TreeSet<>(wrapperList);
				currentPlaylist.setBroadcastwrappers(wrapperSet);
			}

			// To make this work in hibernate first publish before cloning
			currentPlaylist.setPublicationDate(new Date());
			currentPlaylist.setLastSubmittedBy(null);
			currentPlaylist.setState("published");

//			save(currentPlaylist,null,user.getUserEmailaddress());

			// Publish the current playlist
			newPlaylist = currentPlaylist.publish();

			// publish the current playlist and retrieve a new edition

			//clone the train details and train geo location
			if(currentPlaylist instanceof PlaylistNSTreinen) {
				if(currentPlaylist.getTrainDetails() != null) {
					Train clonedTrainDetails = currentPlaylist.getTrainDetails().copyObject();
					newPlaylist.setTrainDetails(clonedTrainDetails);
				}
				if(currentPlaylist.getLocation() != null) {
					Geolocation clonedTrainLocation = currentPlaylist.getLocation().copyObject();
					newPlaylist.setLocation(clonedTrainLocation);
				}
			}

			// save both editions since the current playlist is changed and a new one is created
			// save(currentPlaylist);
//			newPlaylist = save(newPlaylist,null,user.getUserEmailaddress());

			//Update Playlistwrappers with new playlist id
			if(currentPlaylist.getAncestorPlaylistId() != null) {
				playlistDAO.updateplaylistWrapper(currentPlaylist.getAncestorPlaylistId(), currentPlaylist.getId());
			}

			//remove shared status from old playlist
			currentPlaylist.setShared(false);
//			save(currentPlaylist, currentPlaylist.getPublication().getId(), user.getUserEmailaddress());



		}
		catch (Exception e)
		{
			logger.error("Publishing of playlist:" + currentPlaylist.getName() + " has failed.", e);
			throw new EpublisherException("Publishing of playlist has failed.", e);
		}


		logger.info("Published playlist " + newPlaylist.getName() + " with " + newPlaylist.getBroadcastwrappers().size() + " broadcasts.");
		if (logger.isDebugEnabled())
		{
			for (BroadcastWrapper wrapper : newPlaylist.getBroadcastwrappers())
			{
				logger.debug("Published playlist '" + newPlaylist.getName() + "' contains broadcast '" + wrapper.getBroadcast().getName() + "' (id=" + wrapper.getBroadcast().getId() + ")");
			}
		}

		return newPlaylist;
	}


	private void validatePlaylistPriorityForUser(Playlist currentPlaylist, String email) {

		//chcekc if the user has priority seeteting
		Boolean  isAllowedToPublish = playlistDAO.validatePlaylistPriorityForUser(currentPlaylist.getPriority(),email);

		if(!isAllowedToPublish) {
			throw new EpublisherException("You are not allowed to publish playlist with higher priority");
		}

	}

	/**
	 * Unpublishes the submitted playlist for viewing.
	 * @param playlistId
	 *            the id of the playlist to unpublish.
	 * @param email
	 * @return newPlaylist
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	public Playlist unpublish(Integer playlistId, String email) throws EpublisherException
	{
		Playlist currentPlaylist = playlistDAO.getPlaylistById(playlistId);

		//NSTrein playlists also get moved into another directory when un-published (similar to delete)
		//we only un-publish a NSTrein playlist which has a ancestorPlaylistID
		//if it does not have ancestorPlaylistID, then it was never published, so it never existed in the OBIS sync bucket
		if(currentPlaylist.getParentId() != null && currentPlaylist instanceof PlaylistNSTreinen) {
			String playlistID;
			playlistID = currentPlaylist.getUuid() != null ? currentPlaylist.getUuid() : currentPlaylist.getParentId().toString();
			Boolean microserviceResponse = false;
			try {
				microserviceResponse = Boolean.valueOf(playlistDAO.unpublishTreinPlaylist(playlistID));
				logger.info("Unpublishing NS Trein playlist " + currentPlaylist.getName());
			} catch (IOException e) {
				logger.error("Unpublishing NS Trein playlist failed: " + currentPlaylist.getName());
				e.printStackTrace();
				throw new EpublisherException("Unpublishing of NSTrein playlist has failed.", e);
			}
			if(!microserviceResponse) {
				throw new EpublisherException("Unpublishing of NSTrein playlist has failed.");
			}
		}

		// Retrieve any previously published playlists from the database.
		// This based on the same name for a single playlist. If the name has to be dynamic an extra ID property has to be
		// added to a playlist to make it unique.
		List<Playlist> playlists = playlistDAO.getPreviousPlaylistsByNameAndId(currentPlaylist);
		int i = 0;

		// Loop previous playlists
		for (Playlist oldPlayList : playlists)
		{
			if(oldPlayList.getState().equals("published") && oldPlayList.isDeleted() == false) {
				// last published playlist
				oldPlayList.setPublicationDate(null);
				oldPlayList.setLastSubmittedBy(null);
				oldPlayList.setState("unpublished");
				oldPlayList.setDeleted(true);
				playlistDAO.save(oldPlayList,email);
			}
		}

		try
		{
			// To make this work in hibernate first publish before cloning
			currentPlaylist.setLastSubmittedBy(null);
			currentPlaylist.setState("unpublished-child");

			save(currentPlaylist,null,email);
		}
		catch (Exception e)
		{
			logger.error("Unpublishing of playlist:" + currentPlaylist.getName() + " has failed.", e);
			throw new EpublisherException("Unpublishing of playlist has failed.", e);
		}

		logger.info("Unpublished playlist " + currentPlaylist.getName());

		return currentPlaylist;
	}


	public BroadcastWrapper getNextBroadcast(String screenId, Integer playlistId, Integer broadcastId)
	{
		BroadcastPlay logBroadcast = new BroadcastPlay();
		Screen screen = screenService.getScreenByDisplayId(screenId);
		ScreenGroup screengroup = screen.getParentScreenGroup();
		Integer pubId = null;
		ScreenGroup groupPub = screengroup;

		//take publiscation from available screen group
		while(pubId == null) {
			if(groupPub.getPublication()!=null) {
				pubId = groupPub.getPublication().getId();
			}else {
				if( groupPub.getParentScreenGroup()!= null )
					groupPub = groupPub.getParentScreenGroup();
				else
					pubId = 0;
			}
		}

		List<PlaylistsPublicationsAndBroadcasts> broadcasts = playlistDAO.getActivePlaylistsAndBroadcastsForScreenGroup(screengroup.getId(),pubId);

		Boolean getNext = false;
		BroadcastWrapper wrapper = null;
		PlaylistsPublicationsAndBroadcasts nextBroadcast = null;

		// loop over broadcasts to find the next one in line.
		for (PlaylistsPublicationsAndBroadcasts broadcast : broadcasts)
		{
			if (getNext)
			{
				nextBroadcast = broadcast;
				break;
			}

			if (broadcast.getPlaylistId().equals(playlistId) && broadcast.getBroadcastId().equals(broadcastId))
				getNext = true;
		}

		// No broadcast selected yet but there are broadcasts? Then the previous one was the last one, so take the first one again.
		if (nextBroadcast == null && broadcasts.size() > 0)
		{
//			if(!broadcasts.get(0).getPlaylistId().equals(playlistId))
			//logger.info("Screen with id: " +screenId+ " started playing new playlist with id: "+broadcasts.get(0).getPlaylistId());

			//logger.info("Playlist with id: " +broadcasts.get(0).getPlaylistId()+ " started playing with broadcast id: " +broadcasts.get(0).getBroadcastId()+ " on screen with id: "+screenId);
			nextBroadcast = broadcasts.get(0);
		}

		// If we have a next broadcast, get it's wrapper
		if (nextBroadcast != null)
			wrapper = broadcastDAO.getBroadcastWrapperById(nextBroadcast.getBroadcastWrapperId());

		// If we have found a wrapper, set the playlist id in this wrapper (Transient property. Could/should be made persistent and dealt with by hibernate
		if (wrapper != null){
			wrapper.setPlaylistId(nextBroadcast.getPlaylistId());
			//log in to broadcastplay table in db
			logBroadcast.setBroadcastId(wrapper.getBroadcast().getId());
			logBroadcast.setPlaylistId(nextBroadcast.getPlaylistId());
			logBroadcast.setPublicationId(nextBroadcast.getPublicationId());
			logBroadcast.setScreenId(screen.getId());
			logBroadcast.setPlayTime(new Date());
			//logger.info("logging broadcast id: " +wrapper.getBroadcast().getId()+ " playing on screen with id: "+screenId);
			playlistDAO.logNewBroadcast(logBroadcast);

			wrapper.setPlaylistStartHour(nextBroadcast.getStartHour());
			wrapper.setPlaylistStartMinute(nextBroadcast.getStartMinute());
			wrapper.setPlaylistEndHour(nextBroadcast.getEndHour());
			wrapper.setPlaylistEndMinute(nextBroadcast.getEndMinute());
		}

		//SAAS-1501
		wrapper = setStaticUrlForVideo(wrapper);

		return wrapper;
	}

	public BroadcastWrapper setStaticUrlForVideo(BroadcastWrapper wrapper) {
		//set video link for video
		if(wrapper!= null && wrapper.getBroadcast()!=null && wrapper.getBroadcast().getContentBlocks()!=null) {
			setUrlInBroadcast(wrapper.getBroadcast());
		}


		return wrapper;
	}

	public Broadcast setUrlInBroadcast(Broadcast broadcast) {
		broadcast.getContentBlocks().forEach(content -> {
			if(content instanceof EPublisherVideo && ((EPublisherVideo) content).getSource().equalsIgnoreCase("brightcove") && ((EPublisherVideo) content).getVideoId() !=null) {
				//set static link
				((EPublisherVideo)content).setUrl(BroadcastCacheWrapper.getBrightcoveVideoLink(((EPublisherVideo)content).getVideoId()));
			}
		});

		return broadcast;
	}

	/**
	 * Returns all the active playlists for screengroups for a given
	 * publication.
	 *
	 * @param publicationId
	 * 		to get active playlists and screengroups for the givenPublication id
	 * @return all the active playlists for screengroups for a publication.
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	public List<PlaylistScreengroupAndPublication> getActivePlaylistsAndScreengroupsForPublication(Integer publicationId) throws EpublisherException
	{
		List<PlaylistScreengroupAndPublication> playlistScreengroupAndPublication = playlistDAO.getActivePlaylistsAndScreengroupsForPublication(publicationId);
		return playlistScreengroupAndPublication;
	}

	public BroadcastCacheWrapper getActivePlaylistBroadcastWithAssets(String screenId) throws EpublisherException
	{

		BroadcastCacheWrapper wrapper = playlistDAO.getActivePlaylistBroadcastWithAssets(screenId, getPubIdForActivePlaylist(screenId));
		return wrapper;
	}


	public List<ActivePlaylistWrapper> getActivePlaylistIds(String screenId) throws EpublisherException
	{
		return playlistDAO.getActivePlaylistIds(screenId, getPubIdForActivePlaylist(screenId));
	}

	private Integer getPubIdForActivePlaylist(String screenId) {
		Screen screen = screenService.getScreenByDisplayId(screenId);
		ScreenGroup screengroup = screen.getParentScreenGroup();
		Integer pubId = null;
		ScreenGroup groupPub = screengroup;

		//take publiscation from available screen group
		while(pubId == null) {
			if(groupPub.getPublication()!=null) {
				pubId = groupPub.getPublication().getId();
			}else {
				if( groupPub.getParentScreenGroup()!= null )
					groupPub = groupPub.getParentScreenGroup();
				else
					pubId = 0;
			}
		}

		return pubId;
	}


	public Integer getPublicationByPlaylistId(Integer playlistId) {
		return playlistDAO.getPublicationByPlaylistId(playlistId);
	}

	public List<Playlist> getActivePlaylists(Integer publicationId) {
		return playlistDAO.getActivePlaylistsIdsForPublication(publicationId);
	}

	public List<Playlist> getAncestorPlaylists(Integer publicationId) {
		return playlistDAO.getAncestorPlaylistsIdsForPublication(publicationId);
	}

	public List<Playlist> getAllPlaylistsByParentId(Integer playlistParentId) {
		return playlistDAO.getAllPlaylistsByParentId(playlistParentId);
	}

	public List<PlaylistScreengroupAndPublication> getActivePlaylistsForPublication(Integer publicationId) throws EpublisherException
	{
		//check if publication contains screen group
		List<Integer> screenGroupId = playlistDAO.getScreenGroupByPublicationId(publicationId);
		List<PlaylistScreengroupAndPublication> playlistScreengroupAndPublication = new ArrayList<PlaylistScreengroupAndPublication>();

		//PublicationNarrowcastingNS dtype
		if(screenGroupId.isEmpty()) {
			if(publicationDAO.getPublicationById(publicationId) instanceof PublicationNarrowcastingNS) {
				PublicationNarrowcastingNS pub = (PublicationNarrowcastingNS) publicationDAO.getPublicationById(publicationId);
				for(Playlist playlist:pub.getPlaylists()) {
					if(playlist!=null && playlist.getPublicationDate() == null && !playlist.getState().equals("unpublished")
							&& !playlist.getState().equals("discarded") && playlist.isDeleted() == false){
						PlaylistScreengroupAndPublication obj = new PlaylistScreengroupAndPublication();
						obj.setActive(false);
						obj.setPlaylist(playlist);
						obj.setPublicationId(publicationId);
						obj.setPublicationName(pub.getName());
						obj.setTotalPlaylistDuration(BigInteger.valueOf(calculateDuration(playlist)));
						playlistScreengroupAndPublication.add(obj);
					}

				}
				//PublicationNSTreinen dtype
			}else if (publicationDAO.getPublicationById(publicationId) instanceof PublicationNSTreinen) {
				PublicationNSTreinen pub = (PublicationNSTreinen) publicationDAO.getPublicationById(publicationId);
				for(Playlist playlist:pub.getPlaylists()) {
					if(playlist!=null && playlist.getPublicationDate() == null && !playlist.getState().equals("unpublished")
							&& !playlist.getState().equals("discarded") && playlist.isDeleted() == false){
						PlaylistScreengroupAndPublication obj = new PlaylistScreengroupAndPublication();
						obj.setActive(false);
						obj.setPlaylist(playlist);
						obj.setPublicationId(publicationId);
						obj.setPublicationName(pub.getName());
						obj.setTotalPlaylistDuration(BigInteger.valueOf(calculateDuration(playlist)));
						playlistScreengroupAndPublication.add(obj);
					}

				}
			}
			else if (publicationDAO.getPublicationById(publicationId) instanceof PublicationWayfinder) {
				PublicationWayfinder pub = (PublicationWayfinder) publicationDAO.getPublicationById(publicationId);
				for(Playlist playlist:pub.getPlaylists()) {
					if(playlist!=null && playlist.getPublicationDate() == null && !playlist.getState().equals("unpublished")
							&& !playlist.getState().equals("discarded") && playlist.isDeleted() == false){
						PlaylistScreengroupAndPublication obj = new PlaylistScreengroupAndPublication();
						obj.setActive(false);
						obj.setPlaylist(playlist);
						obj.setPublicationId(publicationId);
						obj.setPublicationName(pub.getName());
						obj.setTotalPlaylistDuration(BigInteger.valueOf(calculateDuration(playlist)));
						playlistScreengroupAndPublication.add(obj);
					}

				}
			}
		}else {
			playlistScreengroupAndPublication = playlistDAO.getActivePlaylistsForPublication(publicationId);

			// exclude deleted broadcasts from the playlist duration
			for (PlaylistScreengroupAndPublication playScreenAndPub : playlistScreengroupAndPublication) {
				Playlist playlist = playScreenAndPub.getPlaylist();
				playScreenAndPub.setTotalPlaylistDuration(BigInteger.valueOf(calculateDuration(playlist)));
			}
		}


		return playlistScreengroupAndPublication;
	}

	private int calculateDuration(Playlist playlist) {
		int duration = 0;
		for (BroadcastWrapper wrapper:playlist.getBroadcastwrappers()) {
			if (!wrapper.isDeleted()) {
				duration += (wrapper.getBroadcast() != null ? wrapper.getBroadcast().getDuration() : 0);
			}
		}
		return duration;
	}

	public String getTemplateForScreen(Map<String, Object> map, HttpServletRequest request) throws IOException, TemplateException {

		String tenant = sessionDao.getTenantIdByURL(request.getServerName());
		String fileName= tenant.toLowerCase();
		String fileNameWithExt=fileName+".ftl";
		Template template = null;
		String webInfPath = context.getRealPath("/WEB-INF");
		String uri =  PropertiesUtil.getProperty("uri.schema")+"://"+ request.getServerName() ;
		try {
			template =getTemplate(fileNameWithExt,uri);
		}catch(FileNotFoundException e) {
			fileName= "narrowcasting";
			fileNameWithExt= fileName+".ftl";
			template =getTemplate(fileNameWithExt,uri);
		}

		Writer fileWriter = new FileWriter(new File(webInfPath+"/views/"+fileNameWithExt));
		try {
			template.process(map, fileWriter);
		} finally {
			fileWriter.close();
		}


		return fileName;
	}

	private Template getTemplate(String name,String uri) throws IOException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setLocalizedLookup(false);
		cfg.setTemplateLoader(new URLTemplateLoader() {

			@Override
			protected URL getURL(String name) {
				try {
					return new URL(uri+"/plugins/narrowcastplayer/assets/"+name);
				} catch (MalformedURLException e) {
					logger.warn("Playlist service getTemplate MalformedURLException", e);
				}
				return null;
			}
		});
		cfg.setDefaultEncoding("UTF-8");
		Template template = cfg.getTemplate(name);
		return template;
	}

	public Playlist discard(Integer playlistId, String email) throws EpublisherException {
		Playlist currentPlaylist = playlistDAO.getPlaylistById(playlistId);
		Playlist newPlaylist = null;
		Playlist ancestorPlaylist = null;

		// check if there are previous published versions of that playlist
		if(currentPlaylist.getAncestorPlaylistId() != null) {
			ancestorPlaylist = playlistDAO.getPlaylistById(currentPlaylist.getAncestorPlaylistId());
			newPlaylist = playlistDAO.save(ancestorPlaylist.copyObject(),email);

			//set discarded playlist as deleted
			currentPlaylist.setDeleted(true);
			currentPlaylist.setLastModifiedBy(email);
			currentPlaylist.setState("discarded");
			//we are no longer sharing the previous playlist
			currentPlaylist.setShared(false);
			playlistDAO.save(currentPlaylist,email);
		} else {
			throw new EpublisherException("Deze afspeellijst is nog niet gepubliceerd.");
		}

		return newPlaylist;
	}

	public Playlist checkIfExists(Integer playlistId, Integer publicationId) throws EpublisherException {
		Integer targetPlaylistId = 0;
		Playlist currentPlaylist = playlistDAO.getPlaylistById(playlistId);
		PublicationNarrowcastingNS targetPublication = (PublicationNarrowcastingNS) publicationDAO.getPublicationById(publicationId);
		Playlist targetPlaylist = null;

		// wrong input values
		if(targetPublication == null || currentPlaylist == null) {
			logger.error("Non existing input values for playlist / publication in checkIfExists function");
			throw new EpublisherException("Could not verify that publication/playlist.");
		}

		if(currentPlaylist.getParentId() != null && currentPlaylist.getParentId() > 0) {
			targetPlaylistId = currentPlaylist.getParentId();
		} else {
			targetPlaylistId = currentPlaylist.getId();
		}

		// look for the playlist in the target publication
		for (Playlist playlist : targetPublication.getPlaylists()) {
			if (Objects.equals(playlist.getParentId(), targetPlaylistId)) {
				targetPlaylist = playlist;
				break;
			}
		}

		return targetPlaylist;
	}

	@Transactional
	public List<Playlist> copyPlaylistToPublications(Integer playlistId, List<Integer> publicationIds, String email) throws EpublisherException {
		Playlist currentPlaylist = playlistDAO.getPlaylistById(playlistId);
		List<Playlist> savedPlaylists = new ArrayList<Playlist>();


		for (Integer pubId : publicationIds) {
			try {
				// publication exists?
				if (publicationDAO.getPublicationById(pubId) != null) {
					Playlist existingPlaylist = checkIfExists(playlistId,pubId);

					// delete already existing playlist with the same parentid
					if (existingPlaylist != null) {
						this.remove(existingPlaylist, email,"false");
					}

					// get clone playlist
					Playlist clonePlaylist = currentPlaylist.copyObject();
					clonePlaylist = clearPlaylistSettings(clonePlaylist,email);

					Playlist targetPlaylist = save(clonePlaylist,pubId, email);
					savedPlaylists.add(targetPlaylist);
				}
			}
			catch (Exception e)
			{
				logger.error("Copy playlist " + currentPlaylist.getName() + " has failed.", e);
				throw new EpublisherException("Copy playlist has failed.", e);
			}
		}

		return savedPlaylists;
	}

	public Playlist sharePlaylist(Integer playlistId, String email) throws EpublisherException {
		Playlist currentPlaylist = playlistDAO.getPlaylistById(playlistId);

		//only share the current playlist, remove shared status from ancestors
		updateShareStatusForAncestors(false, currentPlaylist.getParentId() == null ? currentPlaylist.getId() : currentPlaylist.getParentId(), email);

		// 3 checks to be done here
		currentPlaylist.setShared(true);


		return save(currentPlaylist,currentPlaylist.getPublication().getId(), email);
	}

	public Playlist unsharePlaylist(Integer playlistId, String email) throws EpublisherException {
		Playlist currentPlaylist = playlistDAO.getPlaylistById(playlistId);

		//set all ancestors to NOT shared
		updateShareStatusForAncestors(false, currentPlaylist.getParentId() == null ? currentPlaylist.getId() : currentPlaylist.getParentId(), email);

		currentPlaylist.setShared(false);

		// remove all broadcastwrappers which are ads
		for (BroadcastWrapper bw : currentPlaylist.getBroadcastwrappers()) {
			if(bw.getAdvertisementId() != null && !bw.getAdvertisementId().isEmpty()) {
				currentPlaylist.getBroadcastwrappers().remove(bw);
			}
		}

		return save(currentPlaylist,currentPlaylist.getPublication().getId(), email);
	}

	public void updateShareStatusForAncestors(Boolean shareStatus, Integer playlistId, String email) {
		List<Playlist> ancestorPlaylists = getAllPlaylistsByParentId(playlistId);
		for(Playlist ancestorPlaylist : ancestorPlaylists) {
			ancestorPlaylist.setShared(shareStatus);
			save(ancestorPlaylist, ancestorPlaylist.getPublication().getId(), email);
		}
	}

	public Playlist clearPlaylistSettings(Playlist playlist, String email) throws EpublisherException {
		playlist.setPublication(null);
		playlist.setPublicationDate(null);
		playlist.setLastUpdated(new Date());
		playlist.setLastModifiedBy(email);
		playlist.setLastSubmittedBy(null);
		playlist.setState("initial");

		return playlist;
	}

	public boolean exceedsAllowedDuration(Playlist playlist) {
		PublicationNarrowcastingNS publication = (PublicationNarrowcastingNS) playlist.getPublication();
		int duration = calculateDuration(playlist);
		Integer maxDuration = publication.getMaxPlaylistDuration();
		return (maxDuration != null && duration > maxDuration);
	}

	public List<Playlist> getPlaylistHistory(Integer playlistId,Integer limit) throws EpublisherException
	{
		Playlist p = this.getPlaylistById(playlistId);

		// if it's a child playlist we must get the ancestor which will be shared between all children
		if (p.getParentId() != null) {
			playlistId = p.getParentId();
		}

		return playlistDAO.getPlaylistHistory(playlistId, limit);

	}

	public Long getPlaylistHistoryCount(Integer playlistId) throws EpublisherException
	{
		Playlist p = this.getPlaylistById(playlistId);

		// if it's a child playlist we must get the ancestor which will be shared between all children
		if (p.getParentId() != null) {
			playlistId = p.getParentId();
		}

		return playlistDAO.getPlaylistHistoryCount(playlistId);

	}

//	public void checkMaxBroadcastLimitRule(UserSession user, Playlist currentPlaylist,String type) {
//		Object[] limits = userDAO.getTenantBroadcastsMaxLimits(user.getTenantId());
//		Integer newSlides = 0;
//		Integer dbLimit = 0;
//
//		if (type.equalsIgnoreCase("internal")) {
//			dbLimit = (Integer) limits[0];
//		} else {
//			dbLimit = (Integer) limits[1];
//		}
//
//		for (BroadcastWrapper bw : currentPlaylist.getBroadcastwrappers()) {
//			if(bw.isAdded())
//				newSlides++;
//			else if(bw.isDeleted())
//				newSlides--;
//		}
//
//		if (dbLimit != null && dbLimit != 0) {
//			if((broadcastDAO.countPublishedInternalBroadcasts() + Math.max(0, newSlides)) > dbLimit){
//				throw new EpublisherException("Limiet voor maximale interne uitzendingen bereikt", null);
//			}
//		}
//	}
}


