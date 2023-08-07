package nl.prismait.epublisher.java.service;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//import nl.prismait.epublisher.java.business.ArticleController;
import nl.prismait.epublisher.java.dao.IBroadcastDAO;
import nl.prismait.epublisher.java.model.EPublisherFile;
import nl.prismait.epublisher.java.model.EPublisherImage;
import nl.prismait.epublisher.java.model.Location;
import nl.prismait.epublisher.java.model.Route;
//import nl.prismait.epublisher.java.model.User;
//import nl.prismait.epublisher.java.model.dashboard.schema.UserSession;
import nl.prismait.epublisher.java.model.dto.BroadcastSearchResult;
import nl.prismait.epublisher.java.model.dto.PlaylistBroadcastSearchDto;
import nl.prismait.epublisher.java.model.dto.PublicationBroadcastSearchDto;
import nl.prismait.epublisher.java.model.exception.EpublisherConcurrencyException;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.Broadcast;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastFilter;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastSearch;
import nl.prismait.epublisher.java.model.narrowcasting.BroadcastWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.ContentBlock;
import nl.prismait.epublisher.java.model.narrowcasting.EPublisherNarrowcastingImage;
import nl.prismait.epublisher.java.model.narrowcasting.EPublisherPowerpoint;
import nl.prismait.epublisher.java.model.narrowcasting.EPublisherText;
import nl.prismait.epublisher.java.model.narrowcasting.EPublisherTextAndImage;
import nl.prismait.epublisher.java.model.narrowcasting.EPublisherVideo;
import nl.prismait.epublisher.java.model.narrowcasting.Playlist;
import nl.prismait.epublisher.java.model.narrowcasting.PublicationNarrowcastingNS;
import nl.prismait.epublisher.java.model.narrowcasting.TemplateNarrowcasting;
import nl.prismait.epublisher.java.util.EpublisherConstants;
import nl.prismait.epublisher.java.util.Sanitize;
@Service
public class BroadCastService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
//	private final static Logger LOG = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	private IBroadcastDAO broadcastDAO;
	
	@Autowired
	private TemplateNarrowcastingService templateService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private PlaylistService playlistService;
	
//	@Autowired
//	private UserService userService;
	
	/**
	 * Gets all available templates.
	 * 
	 * @return all narrowcasting templates.
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	public List<TemplateNarrowcasting> getAllTemplates() throws EpublisherException 
	{
		List<TemplateNarrowcasting> allTemplates = broadcastDAO.getAllTemplates();
		return allTemplates;
	}

	/**
	 * Gets all available routes.
	 * 
	 * @return all routes
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
//	@Transactional
//	public List<Route> getAllRoutes(User user) throws EpublisherException 
//	{
//		Set<Location> allowedLocations = user.getAllowedLocations();
//		List<Route> allRoutes = broadcastDAO.getAllRoutes(allowedLocations);
//		return allRoutes;
//	}
	
	/**
	 * Saves the function group
	 * 
	 * @param route
	 *            the functionGroup to save.
	 * @return the saved FunctionGroup
	 * @throws EpublisherException
	 *             if any exception occurs
	 */
	@Transactional
	public Route saveRoute(Route route) throws EpublisherException 
	{

		Route savedRoute;
		
		try {
			savedRoute = broadcastDAO.saveRoute(route);
		} catch (DataIntegrityViolationException e) {
			if (e.getCause() instanceof ConstraintViolationException)
				throw new EpublisherException("Route met deze naam bestaat al.", e);
			else
				throw new EpublisherException("Error saving Route", e);
		} catch (HibernateOptimisticLockingFailureException e) {
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException(
					"Een fout is opgetreden. \nEen andere gebruiker heeft deze route tussentijds aangepast.\nVervers het route scherm en probeer het opnieuw.", e);
		}

		return savedRoute;
	}
	
	public Broadcast findBroadcastById(Integer id) 
	{
		Broadcast broadcast = broadcastDAO.getBroadcastById(id);
		return broadcast;
	}
	
	public Route findRouteById(Integer id) 
	{
		Route route = broadcastDAO.getRouteById(id);
		return route;
	}
	
	/**
	 * Get broadcasts paginated
	 * 
	 * @param filter an instance of {@link BroadcastFilter}
	 * @see BroadcastFilter
	 * @return the list of broadcasts including the total number of broadcasts
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	public BroadcastSearch searchBroadcasts(BroadcastFilter filter) throws EpublisherException
	{
		BroadcastSearch searchResult = broadcastDAO.searchBroadcasts(filter);
		return searchResult;
	}
	

	private static PublicationBroadcastSearchDto getObjectReference(List<PublicationBroadcastSearchDto> SummaryList, PublicationBroadcastSearchDto publicationSummaryObj) {
	      
        for (PublicationBroadcastSearchDto summaryObj : SummaryList)
        {
            if ((publicationSummaryObj.getId()).equals(summaryObj.getId()))
                return summaryObj;
        }
		return null;
   
}
	/**
	 * Saves a Broadcast
	 * 
	 * @param broadcast
	 * 			 the broadcast object to save.
	 * @see Broadcast
	 * @return the saved broadcast
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	public Broadcast save(Broadcast broadcast) throws EpublisherException 
	{
		Broadcast savedBroadcast = null;

		// set regenerate template thumbnail flag here
		if (broadcast.getTemplate() != null && broadcast.getTemplate().getRegenerateThumbnail()) {
			templateService.save(broadcast.getTemplate());
		}

		// *** ckeditor sanitize ***
		for(ContentBlock cb : broadcast.getContentBlocks()) {
			if(cb instanceof EPublisherTextAndImage) {
				if (((EPublisherTextAndImage) cb).getContent() != null && !((EPublisherTextAndImage) cb).getContent().isEmpty()) {
					((EPublisherTextAndImage) cb).setContent(Sanitize.sanitizeString(((EPublisherTextAndImage) cb).getContent()));
				}
			}else if (cb instanceof EPublisherText){
				if (((EPublisherText) cb).getContent() != null && !((EPublisherText) cb).getContent().isEmpty()) {
					((EPublisherText) cb).setContent(Sanitize.sanitizeString(((EPublisherText) cb).getContent()));
				}
			}
		}
		// *** ckeditor sanitize ***
				
		try {
			savedBroadcast = broadcastDAO.save(broadcast);
		} catch (HibernateOptimisticLockingFailureException e) {
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException(
					"Er is een fout opgetreden.\nEen andere gebruiker heeft deze uitzending tussentijds aangepast.\nVervers het scherm en probeer het opnieuw.",
					e);
		} finally {
		}
		

		return savedBroadcast;
	}

	/**
	 * Removes a Broadcast
	 * 
	 * @param broadcast the broadcast object to delete.
	 * @see Broadcast 
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	public void delete(Broadcast broadcast) throws EpublisherException 
	{
		try {
			// get broadcast children clones
			List<Integer> childrenBroadcasts = broadcastDAO.getBroadcastChildrenIds(broadcast.getId());
			List<BroadcastWrapper> broadcastwrappers = broadcastDAO.getBroadcastWrapperList(broadcast.getId());
			
			//do not chenag ethe order
			if(broadcastwrappers.size()>0) {
				broadcastDAO.deleteBroadcastWrapper(broadcastwrappers, broadcast.getId());
			}
			// delete clones also if parent article is deletedS
			if (childrenBroadcasts.size() > 0) {
				for(Integer cloneBroadcastId : childrenBroadcasts) { 
					Broadcast broadcastClone = broadcastDAO.getBroadcastById(cloneBroadcastId);
					// we only update and not delete broadcast SAAS-1620
					broadcastClone.setDeleted(true);
					broadcastDAO.save(broadcastClone);
					
				}
			}
			
		
			broadcast.setDeleted(true);
			broadcastDAO.save(broadcast);
		} catch (HibernateOptimisticLockingFailureException e) {
			logger.warn("Error deleting object", e);
			throw new EpublisherConcurrencyException(
					"Er is een fout opgetreden.\nEen andere gebruiker heeft deze uitzending tussentijds aangepast.\nVervers het scherm en probeer het opnieuw.",
					e);
		} catch (DataIntegrityViolationException ex) {
			logger.warn("DataIntegrityViolationException when deleting broadcast: " + broadcast.getName(), ex);
			//in case of DataIntegrityViolationException we go to a special method to delete broadcasts
			//that have a wrong parent ID set
			//NOTE Method commented since we are not gonna delete the broadcast and there will be no depency error- SAAS-1620
			//broadcastDAO.deleteBrokenBroadcasts(broadcast);
		}finally {
		}
	}
	


	/**
	 * Removes a Broadcast
	 * 
	 * @param route object to delete.
	 * @see Broadcast 
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	public void deleteRoute(Route route) throws EpublisherException 
	{
		try {
			broadcastDAO.deleteRoutesOnLocations(route);
			broadcastDAO.deleteRoutesOnContentblocks(route);
			broadcastDAO.deleteRoute(route);
		} catch (HibernateOptimisticLockingFailureException e) {
			logger.warn("Error deleting object", e);
			throw new EpublisherConcurrencyException(
					"Er is een fout opgetreden.\nEen andere gebruiker heeft deze route tussentijds aangepast.\nVervers het scherm en probeer het opnieuw.",
					e);
		} finally {
		}
	}
	
	/**
	 * Saves a BroadcastWrapper
	 * 
	 * @param broadcastWrapper
	 *            the broadcastwrapper object to save.
	 * @return the saved broadcastwrapper
	 * @throws EpublisherException
	 *             if the user is not authorized for this method.
	 */
	@Transactional
	public BroadcastWrapper save(BroadcastWrapper broadcastWrapper) throws EpublisherException 
	{
		BroadcastWrapper savedBroadcastWrapper = null;

		try {
			savedBroadcastWrapper = broadcastDAO.save(broadcastWrapper);
		} catch (HibernateOptimisticLockingFailureException e) {
			logger.warn("Error saving object", e);
			throw new EpublisherConcurrencyException(
					"Er is een fout opgetreden.\nEen andere gebruiker heeft deze uitzending tussentijds aangepast.\nVervers het scherm en probeer het opnieuw.",
					e);
		} finally {
		}
		return savedBroadcastWrapper;

	}

	public BroadcastSearchResult searchBroadcastsSummaries(BroadcastFilter filter, String emailAddress) { //,User user) {
		//All the available broadcast ids for the given limit
		//Refer jira ticket number NS_EPB-786
		List<Object[]> searchResultList = broadcastDAO.searchBroadcastsSummaries(filter); //,user);
		
		//for PublicationWayfinder we only show wayfinder publications
		//for PublicationNarrowcastingNS and PublicationNSTreinen we show both
		List<String> publicationTypes = new ArrayList<>();
		List<Object[]> activeBroadcastForPlaylist = new ArrayList<>();
		List<Object[]> availablePublicationWithPlaylist = new ArrayList<>();
		
		if(filter != null && filter.isWayfinder()) {
			publicationTypes.add("PublicationWayfinder");
		}else {
			publicationTypes.add("PublicationNarrowcastingNS");
			publicationTypes.add("PublicationNSTreinen");
		}
		
		//All available publications and their playlist associated with the user
		if(filter.isExternalPlaylist())
			availablePublicationWithPlaylist = broadcastDAO.getAvailablePublicationWithPlaylist(filter,emailAddress, publicationTypes, true);
		else
			availablePublicationWithPlaylist = broadcastDAO.getAvailablePublicationWithPlaylist(filter,emailAddress, publicationTypes, false);
		//total number of broadcast available irrespective of the limit 
		int count = broadcastDAO.getBroadcastCount(filter).intValue();
		List<BroadcastSearch> broadcastSerachList=  new ArrayList<>();
		
		BroadcastSearchResult broadcastSearchResult = new BroadcastSearchResult();
		broadcastSearchResult.setMaxResults(searchResultList.size());
		broadcastSearchResult.setTotalNumberOfResults(count);
		broadcastSearchResult.setStartrowSearch(filter.getStartPage());
		
		broadcastSearchResult.setMenuList(getMenu(availablePublicationWithPlaylist));
		for(Object[] searchResult: searchResultList)
		{
			int childCount = 0;
			Map<Integer,String> identityMap=  new HashMap();
			List<PublicationBroadcastSearchDto> publicationList = new ArrayList<>();
			BroadcastSearch searchObject = new BroadcastSearch();
			searchObject.setId((Integer)searchResult[0]);
			searchObject.setName((String)searchResult[1]);
			searchObject.setActive((boolean)searchResult[4]);
			searchObject.setTemplate((String)searchResult[2]);
			searchObject.setCreatedDate((Date)searchResult[5]);
			searchObject.setApp((String)searchResult[3]);
			searchObject.setWayfinder((boolean)searchResult[6]);
			searchObject.setThumbnail((String)searchResult[7]);
			
			//list of playlist that already linked to the given broadcast id 
			if(filter.isExternalPlaylist())
				activeBroadcastForPlaylist = broadcastDAO.getActiveBroadcastForPlaylist(searchObject.getId(),emailAddress, publicationTypes, true);
			else
				activeBroadcastForPlaylist = broadcastDAO.getActiveBroadcastForPlaylist(searchObject.getId(),emailAddress, publicationTypes, false);
			
			searchObject.setPublications(getPublicationByBroadcastId(activeBroadcastForPlaylist, availablePublicationWithPlaylist,publicationList,identityMap));
			
			//count the children broadcast 
			for(PublicationBroadcastSearchDto obj :searchObject.getPublications()) {
			
				for(PlaylistBroadcastSearchDto playlist: obj.getPlaylists()) {
					if(playlist.isChildBroadcast()) {
						childCount= childCount+1;
					}
				}
			}
			
			searchObject.setNumberOfActiveChildBroadcast(childCount);
			broadcastSerachList.add(searchObject);
			
		}
		broadcastSearchResult.setBroadcastSearchObject(broadcastSerachList);
		return broadcastSearchResult;
	
	}
	
	private  List<PublicationBroadcastSearchDto> getMenu(List<Object[]> availablePublicationWithPlaylist)
	{
		List<PublicationBroadcastSearchDto> publicationList = new ArrayList<>();
		Map<Integer,String> menu = new HashMap<>();
		for(Object[] publicationObject : availablePublicationWithPlaylist)
		{
			PublicationBroadcastSearchDto publicationBroadcastDto = new PublicationBroadcastSearchDto();
			PlaylistBroadcastSearchDto playlistBroadcastDto = new PlaylistBroadcastSearchDto();
			
			if(menu.containsKey((Integer)publicationObject[0]))
			{
				publicationBroadcastDto.setId((Integer)publicationObject[0]);
				PublicationBroadcastSearchDto publicationBroadcastDtoRef = getObjectReference(publicationList, publicationBroadcastDto) ;
				List<PlaylistBroadcastSearchDto> playlistList = publicationBroadcastDtoRef.getPlaylists();
				playlistBroadcastDto.setName((String)publicationObject[3]);
				playlistBroadcastDto.setId((Integer)publicationObject[2]);
				playlistList.add(playlistBroadcastDto);
			}
			else
			{
				publicationBroadcastDto.setId((Integer)publicationObject[0]);
				publicationBroadcastDto.setName((String)publicationObject[1]);
				menu.put((Integer)publicationObject[0], (String)publicationObject[1]);
				
				List<PlaylistBroadcastSearchDto> playlistList = new ArrayList<>();
				playlistBroadcastDto.setId((Integer)publicationObject[2]);
				playlistBroadcastDto.setName((String)publicationObject[3]);
				
				playlistList.add(playlistBroadcastDto);
				publicationBroadcastDto.setPlaylists(playlistList);
				publicationList.add(publicationBroadcastDto);
			}
			
		}
		return publicationList;
	}

	private  List<PublicationBroadcastSearchDto>  getPublicationByBroadcastId(List<Object[]> activeBroadcastForPlaylist,List<Object[]> availablePublicationWithPlaylist,
			List<PublicationBroadcastSearchDto> publicationList,Map<Integer,String> identityMap)
	{
		for(Object[] publicationObject : availablePublicationWithPlaylist)
		{
			Integer modifiedPlaylists = 0;
			Integer submittedPlaylists = 0;
			PublicationBroadcastSearchDto publicationBroadcastDto = new PublicationBroadcastSearchDto();
			PlaylistBroadcastSearchDto playlistBroadcastDto = new PlaylistBroadcastSearchDto();
			publicationBroadcastDto.setId((Integer)publicationObject[0]);
			publicationBroadcastDto.setName((String)publicationObject[1]);
			publicationBroadcastDto.setDtype((String)publicationObject[4]);
			
			if(identityMap.containsKey((Integer)publicationObject[0]))
			{
				// get the object reference to add playlist if the publications are same 
				PublicationBroadcastSearchDto publicationBroadcastDtoRef = getObjectReference(publicationList, publicationBroadcastDto) ;
				List<PlaylistBroadcastSearchDto> playlistList = publicationBroadcastDtoRef.getPlaylists();
				getActivePlaylist(activeBroadcastForPlaylist, publicationObject, publicationBroadcastDto, playlistBroadcastDto, playlistList);
				
				// set number of modified and submitted playlists on the publication level
				for (PlaylistBroadcastSearchDto playlist : publicationBroadcastDtoRef.getPlaylists()) {
					if (playlist.getState() != null && ( playlist.getState().equals("modified") || playlist.getState().equals("initial")) ){
						modifiedPlaylists++;
					} else if(playlist.getState() != null && playlist.getState().equals("submitted")) {
						submittedPlaylists++;
					}
				}
				
				publicationBroadcastDtoRef.setModifiedPlaylists(modifiedPlaylists);
				publicationBroadcastDtoRef.setSubmittedPlaylists(submittedPlaylists);
			}else
			{
				List<PlaylistBroadcastSearchDto> playlistList = new ArrayList<>();
				identityMap.put((Integer)publicationObject[0], (String)publicationObject[1]);
				publicationBroadcastDto.setPlaylists(getActivePlaylist(activeBroadcastForPlaylist, publicationObject, publicationBroadcastDto, playlistBroadcastDto, playlistList));
				
				publicationList.add(publicationBroadcastDto);
			}
			
			
		}
		return publicationList;
	}
	
	private List<PlaylistBroadcastSearchDto> getActivePlaylist(List<Object[]> activeBroadcastForPlaylist,Object[] publicationObject,PublicationBroadcastSearchDto publicationBroadcastDto,
			PlaylistBroadcastSearchDto playlistBroadcastDto,List<PlaylistBroadcastSearchDto> playlistList)
	{
		//iterate through all the playlist to find the  playlist already linked to the broadcast 
		for(Object[] playlistObject : activeBroadcastForPlaylist)
		{
			if(publicationBroadcastDto.getId().equals(playlistObject[0]) && publicationObject[2].equals(playlistObject[2]))
			{
				playlistBroadcastDto.setId((Integer)playlistObject[2]);
				playlistBroadcastDto.setName((String)playlistObject[3]);
				playlistBroadcastDto.setState((String)playlistObject[6]);
				playlistBroadcastDto.setAlreadyLinked(true);
				if(playlistObject[5] !=null) {
					playlistBroadcastDto.setChildBroadcast(true);
				}
				playlistList.add(playlistBroadcastDto);
			}
		}
		if(playlistList.isEmpty() || !playlistList.contains(playlistBroadcastDto))
		{
			playlistBroadcastDto.setId((Integer)publicationObject[2]);
			playlistBroadcastDto.setName((String)publicationObject[3]);
			playlistBroadcastDto.setState((String)publicationObject[5]);
			playlistBroadcastDto.setAlreadyLinked(false);
			playlistList.add(playlistBroadcastDto);
		}
		
		return playlistList;
	}

	/**
	 * @param powerpointFile 
	 * @param emailAddress 
	 * @param broadcast 
	 * @throws EpublisherException 
	 * @throws IOException 
	 */
	@Transactional
	public void processPowerpoint(EPublisherFile powerpointFile, String emailAddress,
			Broadcast broadcast, Integer playlistId, String tenantId, HttpServletRequest request)  throws EpublisherException, IOException
	{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		Broadcast newBroadcast;
		Integer templateId = 0;
		List<TemplateNarrowcasting> allTemplates = broadcastDAO.getAllTemplates();
		TemplateNarrowcasting newTemplate = new TemplateNarrowcasting();
		Integer containerId = 0;
		String title = "";
		EPublisherImage savedFile;
		String powerpointUrl = powerpointFile.getUrl().replace("http:", "https:");
		String powerpointName = powerpointFile.getName();
		String powerpointFileName = powerpointFile.getFileName();
		String broadcastPublicationMethod = null;
		String finalPowerpointUrl = powerpointUrl;
		String serverName = request.getServerName();
		
//		LOG.info("Powerpoint pre url: " + powerpointFile.getUrl());
		attr.getRequest().getSession().setAttribute("tenantName", tenantId);
		
		// if url does not include protocol, we add it manually
		if (!powerpointUrl.contains("https")) {
			String scheme = "https";
			StringBuilder finalUrl = new StringBuilder();
			 
			finalUrl.append(scheme).append("://").append(serverName).append(powerpointUrl);
			finalPowerpointUrl = finalUrl.toString();
		}
		
		for(TemplateNarrowcasting template:allTemplates) {
			if (template.getHtmlTemplate().contains("fullscreen-Image")) {
				templateId = template.getId();
				newTemplate = findTemplateById(templateId);
			}
		}
		
		// if fullscreen template exists
		if (templateId > 0) {
			
//			LOG.info("Powerpoint conversion started!");
			
			XMLSlideShow ppt = null;
			
			try {	  
				// tests
				//FileInputStream is = new FileInputStream("C:\\Users\\d.lopes\\nstest\\voorbeeldpp met video.pptx");
				//InputStream is = new URL("https://www-acc1.ns-epublisher.com/BlazeDS/documents/10157/b39de66f-6737-4644-aca7-c6f678c0dc42").openStream();
				
//				LOG.info("Powerpoint url: " + finalPowerpointUrl);
				InputStream is = new URL(finalPowerpointUrl).openStream();
				
				//HSLFSlideShow ppt = new HSLFSlideShow(is);
				ppt = new XMLSlideShow(is);
				is.close();
				
				Dimension pgsize = ppt.getPageSize();
				int idx = 1;
				
				for (XSLFSlide slide : ppt.getSlides()) {
					BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
					Graphics2D graphics = img.createGraphics();
					
					//improve slides quality
					graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			        graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			        graphics.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 150);
			         
					// clear the drawing area
					graphics.setPaint(Color.white);
					graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
					
					// render
					slide.draw(graphics);
					
					// create & upload epublisher image
					EPublisherImage imageToReturn = new EPublisherImage();
					
					// convert BufferedImage to byte[]
			        byte[] bytes = toByteArray(img, "png");
			        
					try {
						BufferedImage image = img;
						imageToReturn.setName((powerpointName == null || powerpointName.isEmpty()) ? powerpointFileName+idx : powerpointName+idx);
						imageToReturn.setCaption("");
						imageToReturn.setFileSizeInbytes(bytes.length);
						imageToReturn.setFileName(powerpointName+idx+".png");
						imageToReturn.setUsername(emailAddress);
						imageToReturn.setHeight(image.getHeight());
						imageToReturn.setWidth(image.getWidth());
						imageToReturn.setUrl(EpublisherConstants.URL_ROOT+imageToReturn.getUuid());
						imageToReturn.setVersion("1.0");
						savedFile = fileService.saveUploadedImage(imageToReturn,bytes,serverName);
						imageToReturn.setFolderid(savedFile.getFolderid());
						
					}catch (HibernateOptimisticLockingFailureException e)
					{
						logger.warn("Error saving object", e);
						throw new EpublisherConcurrencyException("Een fout is opgetreden. \nEen andere gebruiker heeft dit bestand tussentijds aangepast.\n Sluit het artikel, heropen het en probeer het opnieuw.", e);
					}
					
					newBroadcast = broadcast.copyObject();
					newBroadcast.setTemplate(newTemplate);
					newBroadcast.setName(newBroadcast.getName() + " " + idx + " (Powerpoint)");
					// sets date so that powerpoints appear in chronological order
					newBroadcast.setDateCreated(new java.util.Date());
					
					for (Iterator<ContentBlock> iterator = newBroadcast.getContentBlocks().iterator(); iterator.hasNext();) {
						ContentBlock content =  iterator.next();
						
						if (content.getClass().toString().contains("Powerpoint")) {
							containerId = content.getContainerId();
							title = content.getTitle();
							
							EPublisherNarrowcastingImage newContent = new EPublisherNarrowcastingImage();
							newContent.setContainerId(containerId);
							newContent.setEnabled(true);
							newContent.setTitle(title + " " + idx + " (Powerpoint)");
							newContent.setImage(savedFile);
							
							newBroadcast.getContentBlocks().remove(content);
							newBroadcast.getContentBlocks().add(newContent);
							
							break;
						}
					}
					
					
					// save fullscreen broadcast
					Broadcast savedBroadcast = save(newBroadcast);
					
					// add broadcast to playlist if broadcast was created from within the playlist
					if(playlistId != null && !playlistId.equals("undefined") && !playlistId.equals(0)) {
						//get user default broadcast publication method
//						if(broadcastPublicationMethod == null || broadcastPublicationMethod.equals("undefined")) {
//							broadcastPublicationMethod = userService.loadBroadcastPublicationMethod(tenantId);
//						}
						
						// add new broadcast to matching playlist

						playlistService.addBroadcastToPlaylist(playlistId, savedBroadcast.getId(), broadcastPublicationMethod,emailAddress,null,false,false);
					}
					
					
					idx++;
				}
			} catch (IOException e) {
	//			LOG.warn("Powerpoint conversion exception!", e);
			}
			finally {
				// Close XMLSlideShow to avoid memory leaks - SonarQube fix
				ppt.close();
			}
			
//			LOG.info("Powerpoint conversion ended!");
		
		} else {
	//		LOG.info("Fullscreen template not found!");
		}
		
	}
	
	
	/**
	 * @param id
	 * @return template
	 */
	public TemplateNarrowcasting findTemplateById(Integer id) 
	{
		TemplateNarrowcasting template = broadcastDAO.getTemplateById(id);
		return template;
	}
	
	/**
	 * @param bi
	 * @param format
	 * @return bytes
	 * @throws IOException
	 */
	// convert BufferedImage to byte[]
    public static byte[] toByteArray(BufferedImage bi, String format)
        throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }
    
    /**
   	 * Clones a Broadcast that was edited
   	 * 
   	 * @param broadcast
   	 * 			 the broadcast object to save.
   	 * @see Broadcast
   	 * @return the saved broadcast
   	 * @throws EpublisherException
   	 *             if the user is not authorized for this method.
   	 */
   	@Transactional
   	public Broadcast cloneEditedBroadcast(Broadcast oldBroadcast, Integer playlistId, Integer broadcastWrapperId,
   			String email) throws EpublisherException 
   	{
   		Broadcast newBroadcast = null;
   		Playlist playlist = null;
   		BroadcastWrapper oldBroadcastWrapper = broadcastDAO.getBroadcastWrapperById(broadcastWrapperId);
   		BroadcastWrapper newBroadcastWrapper = null;
   		playlist = playlistService.getPlaylistById(playlistId);
   		PublicationNarrowcastingNS publication = (PublicationNarrowcastingNS) playlist.getPublication();
   		
   		// edit requires cloning to avoid losing changes (OBIS)
   		newBroadcast = oldBroadcast.copyObject();
		this.save(newBroadcast);
		
		// copy broadcast wrapper
		newBroadcastWrapper = oldBroadcastWrapper.copyObject();
		newBroadcastWrapper.setBroadcast(newBroadcast);
		//set state isMofified in broadcastwrapper
		newBroadcastWrapper.setModified(true);
		newBroadcastWrapper.setAdded(false);
		newBroadcastWrapper.setDeleted(false);
		
		// remove old broadcastwrapper
		playlist.getBroadcastwrappers().remove(oldBroadcastWrapper);
		
		// save the changed playlist (To make this work in hibernate)
		playlistService.save(playlist,null,email);
				
		// add new broadcast
		playlist.getBroadcastwrappers().add(newBroadcastWrapper);
		
		// check broadcastwrapper validity against publication settings
		if (publication.getMaxPlaylistDuration() != null) {
			if (playlistService.exceedsAllowedDuration(playlist)) {
				for (BroadcastWrapper bw : playlist.getBroadcastwrappers()) {
					bw.setActive(false);
				}
			} else {
				// does not exceed, check all broadcasts in playlist on validity
				for (BroadcastWrapper bw : playlist.getBroadcastwrappers()) {
					bw.setActive(checkBroadcastValidityAgainstPublicationSettings(newBroadcast, publication));
				}
			}
		} else {
			newBroadcastWrapper.setActive(checkBroadcastValidityAgainstPublicationSettings(newBroadcast, publication));
		}
		
		// save the changed playlist
		playlistService.save(playlist,null,email);
					
		return newBroadcast;
   	}
   	
   	private boolean checkValidityVideoBlocksAgainstPublicationSettings(Broadcast broadcast, PublicationNarrowcastingNS publication) {
		boolean valid = true;
   		// check if video requirements match focusing on the negative outcome
		if (broadcast.getContentBlocks() != null && !broadcast.getContentBlocks().isEmpty()){
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
   	 * Clones a Broadcast that was edited
   	 * 
   	 * @param broadcast
   	 * 			 the broadcast object to save.
   	 * @see Broadcast
   	 * @return the saved broadcast
   	 * @throws EpublisherException
   	 *             if the user is not authorized for this method.
   	 */
   	@Transactional
   	public Broadcast processBroadcast(String editClone, Integer broadcastWrapperId,Broadcast broadcast, Integer playlistId, 
  //			UserSession sessionUser,
   			HttpServletRequest request) throws EpublisherException, IOException
   	{
   		Broadcast savedBroadcast = null;
		EPublisherFile powerpointFile = null;
		BroadcastWrapper broadcastWrapper = null;
		
		// edit clone type
		if (editClone.equals("true") && broadcastWrapperId != null && playlistId != null && broadcastWrapperId != -1) {
		//	savedBroadcast = this.cloneEditedBroadcast(broadcast,playlistId,broadcastWrapperId,sessionUser.getUserEmailaddress());
		}
		
		// standard type
		else {
			for(ContentBlock content : broadcast.getContentBlocks())
			{
				if (content.getClass().getName().contains("EPublisherPowerpoint")) {
					EPublisherPowerpoint powerpointContent = (EPublisherPowerpoint) content;
					powerpointFile = powerpointContent.getPowerpoint();				
					break;
				}
			}
			
			// powerpoint
//			if (powerpointFile != null) {
//				this.processPowerpoint(powerpointFile, sessionUser.getUserEmailaddress(), broadcast, playlistId, sessionUser.getTenantId(),request);
//				
//			//others
//			} else { // others
//				savedBroadcast=this.save(broadcast);
//			}
		}
				
		return savedBroadcast;
   	}

	public List<BroadcastSearch> getChildBroadcast(Integer broadcastId, String userEmailaddress) {
		
		
		List<Object[]> activeBroadcastForPlaylist = broadcastDAO.getActiveChildBroadcastForPlaylist(broadcastId,userEmailaddress,Arrays.asList( "PublicationNarrowcastingNS","PublicationNSTreinen"));
		List<BroadcastSearch> searchObjectList = new ArrayList();
		
		activeBroadcastForPlaylist.forEach(searchResult->{
			PublicationBroadcastSearchDto publicationBroadcastDto = new PublicationBroadcastSearchDto();
			PlaylistBroadcastSearchDto playlistBroadcastDto = new PlaylistBroadcastSearchDto();
			BroadcastSearch searchObject = new BroadcastSearch();
			searchObject.setId((Integer)searchResult[5]);
			searchObject.setName((String)searchResult[7]);
			searchObject.setThumbnail((String)searchResult[8]); 
			playlistBroadcastDto.setId((Integer)searchResult[2]);
			playlistBroadcastDto.setName((String)searchResult[3]);
			publicationBroadcastDto.setId((Integer)searchResult[0]);
			publicationBroadcastDto.setName((String)searchResult[1]);
			publicationBroadcastDto.setPlaylists(Arrays.asList(playlistBroadcastDto));
			searchObject.setPublications(Arrays.asList(publicationBroadcastDto));
			searchObjectList.add(searchObject);
		});

		return searchObjectList;
	}

	
	public List<BroadcastSearch> getSearchSummaryForSingleBroadcast(Integer broadcastId, String userEmailaddress,BroadcastFilter filter) {
			
			//get details for braoadcast
			String thumbnailUrl = broadcastDAO.getBroadcastThumbnail(broadcastId);
			List<Object[]> activeBroadcastForPlaylist = broadcastDAO.getActiveBroadcastForPlaylist(broadcastId,userEmailaddress,Arrays.asList( "PublicationNarrowcastingNS","PublicationNSTreinen"),false);
			List<Object[]> availablePublicationWithPlaylist = broadcastDAO.getAvailablePublicationWithPlaylist(filter,userEmailaddress, Arrays.asList( "PublicationNarrowcastingNS","PublicationNSTreinen"),false);
			return getSearchObject(activeBroadcastForPlaylist,broadcastId,availablePublicationWithPlaylist,thumbnailUrl);
		}

	private List<BroadcastSearch> getSearchObject(List<Object[]> activeBroadcastForPlaylist, Integer broadcastId, List<Object[]> availablePublicationWithPlaylist, String thumbnailUrl) {
		List<BroadcastSearch> searchObjectList = new ArrayList();
		Map<Integer,String> identityMap=  new HashMap();
		int childCount = 0;
		List<PublicationBroadcastSearchDto> publicationList = new ArrayList<>();
		BroadcastSearch searchObject = new BroadcastSearch();
		searchObject.setId(broadcastId);
		searchObject.setThumbnail(thumbnailUrl);
		searchObject.setPublications(getPublicationByBroadcastId(activeBroadcastForPlaylist, availablePublicationWithPlaylist,publicationList,identityMap));
		//count the children broadcast 
		for(PublicationBroadcastSearchDto obj :searchObject.getPublications()) {
		
			for(PlaylistBroadcastSearchDto playlist: obj.getPlaylists()) {
				if(playlist.isChildBroadcast()) {
					childCount= childCount+1;
				}
			}
		}
		
		searchObject.setNumberOfActiveChildBroadcast(childCount);
		searchObjectList.add(searchObject);
		return searchObjectList;
	}
	
	public Integer countPublishedInternalBroadcasts() throws EpublisherException
	{
		return broadcastDAO.countPublishedInternalBroadcasts();
	}
	
	public Integer countPublishedExternalBroadcasts() throws EpublisherException
	{
		return broadcastDAO.countPublishedExternalBroadcasts();
	}
}