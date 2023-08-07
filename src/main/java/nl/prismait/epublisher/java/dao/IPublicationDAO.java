package nl.prismait.epublisher.java.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import nl.prismait.epublisher.java.model.ArticleWrapper;
//import nl.prismait.epublisher.java.model.AudienceCDP;
import nl.prismait.epublisher.java.model.Edition;
import nl.prismait.epublisher.java.model.EditionWrapper;
import nl.prismait.epublisher.java.model.OutputChannel;
import nl.prismait.epublisher.java.model.Publication;
import nl.prismait.epublisher.java.model.SchedulePublish;
//import nl.prismait.epublisher.java.model.dto.ArticleArchiveDTO;
import nl.prismait.epublisher.java.model.dto.PublicationSummaryDTO;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.narrowcasting.PlayTime;
import nl.prismait.epublisher.java.model.narrowcasting.Playlist;

public interface IPublicationDAO{

	public Publication save (Publication publication);
	public OutputChannel save (OutputChannel outputChannel);
	public Edition save (Edition edition);
	
	public Edition getCurrentEditionForPublication(Integer publicationId, boolean loadAllArticles);
	public Edition lastPublishedEditionForPublication(Integer publicationId);
	public Edition lastPublishedEditionForPublication(Publication publication);
	public List<String> lastPublishedEditionArticleIdsForPublication(Integer publicationId);
	public List<String> lastPublishedEditionArticleIdsForPublication(Publication publication);

	public void delete(Publication publication) throws EpublisherException;
	public void delete(Edition edition);
	public List<Publication> findAll();
	public Publication getPublicationById(Integer id);
	public void getPublicationByApiId(Integer apiId);
	public Publication getPublicationByName(String publicationName);
	public List<Publication> getPublicationsWithApiId(String apiPublishDate);
	public Edition getEditionById(Integer editionId);
	public Edition getFullEditionById(Integer editionId);
	public Edition getFullEditionById(Integer editionId, Boolean evict);
	public void getAllEditionsForPublication(Integer publicationId);
	public OutputChannel getOutputChannelInstance(Class<?> outputChannelClass);
	public List<Publication> getAllPublicationsOfType(String publicationType);
	public Edition getLastPublishedEditionByPublicationName(String publicationName);
	public List<Edition> getEditionsByYear(Integer publicationId, Integer year);

	public long getTotalNumberOfNewsletterPublications();
	public long getTotalNumberOfNewslettersSent();
	public List<Integer> getallPublicationIds();
	public List<Object[]> getAllArticlesForEditionSummaryId(Integer publicationId);
	public List<PublicationSummaryDTO> findAllSummaries();
	public List<Map<String, String>> getAllPublicationsOfTypeSummaries(String publicationType);
	public Edition getCurrentEditionForPublicationWithPagination(Integer publicationId, Integer startPage, Integer maxResult);
	public void getCurrentScheduledEdition();
	public SchedulePublish saveSchedulePublish(SchedulePublish schedule);
	List<Object[]> getAllArticlesForScheduledEditionSummaryId(Integer publicationId);
	public void getScheduledEditionByEditionId(Integer editionId);
	public List getAllPublicationsForUser(Integer id);
	List<Edition> getFullSubEdition(Edition edition);
	List<Object[]> getAvailableSubEditionForPublication(Integer editionId);
	void deleteSubEditions(List<Edition> editionList) throws EpublisherException;
	Edition getCurrentEditionForPublication(Integer publicationId, Integer subEditionId);
//	Edition getEditionForAudienceList(Set<AudienceCDP> audienceList, Publication publication, Integer editionId);
	Edition getCurrentEditionForPublicationLazy(Integer publicationId, Integer subEditionId);
	Integer getTotalEditionsCountForPublication(Integer publicationId);
	List<Object[]> getAllArticlesForEditionSummaryId(Integer publicationId, Integer startRow, Integer pageSize);
//	List<ArticleArchiveDTO> getArchivedArticleForIntranetOrRailpocket(Integer publicationId, String apiPublishDate, int maxResults,
//			int startRow, String dtype);
	public Integer getLandingPageEditionIdByPublicationId(Integer publicationId);
//	public void updateArticleWrappers(ArticleWrapper wrapper);
	public long getSubmittedPlaylistsById(Integer publicationId);
	public List<Object[]> getOrphanPublication();
	List<Edition> getEditionsByDateRange(String startDate, String endDate, List<Integer> publications, String type)
			throws ParseException;
	Edition getPublishedEditionById(Integer editionId);
	int getOptOutAbonneesAmount(Date startDate, Date endDate, Integer editionId);
//	List<ArticleArchiveDTO> getArticleForRssFeed(Integer publicationId, String apiPublishDate, int maxResults,
//			int startRow);
	public List<EditionWrapper> getEditionsForPublication(Integer publicationId);
	public void getListOfPublications(String type);
	public long getModifiedPlaylistsById(Integer publicationId);
	public List<Playlist> getPublicationHistory(Integer publicationId, Integer limit);
	Long getPublicationHistoryCount(Integer publicationId);
	Edition getCurrentEditionForPublicationPortalPage(Publication publication);
	public Publication getPublicationByNameAndOutputChannel(String publicationName, String outputchannelName);
	public Boolean checkIfNewPortalPageIsValid(String uuid, String language, Boolean checkUuid, Boolean checkLanguage);
	public List<Object[]> getSharedInformation();
	public List<PlayTime> getPlaylistFrequenciesByID(Integer playlistId);
	public List<Object[]> getPlaylistScreensLocationByID(Integer playlistId);
	public List<Playlist> getSharedBookingInformation();
	
}
