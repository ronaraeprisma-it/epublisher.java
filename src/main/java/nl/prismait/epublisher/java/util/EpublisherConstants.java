package nl.prismait.epublisher.java.util;

import nl.prismait.epublisher.java.model.config.PropertiesUtil;

public class EpublisherConstants
{
	
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_CHIEFEDITOR = "ROLE_CHIEFEDITOR";
	public static final String ROLE_EDITOR = "ROLE_EDITOR";
	public static final String NULL = null;
	public static final String IS_NULL = " IS NULL ";
	//Used for file handling
	public static final String URL_ROOT = "/documents/10157/";
	public static final String URL_ROOT_POWERPOINT = "/documents/10157/ppt/";
	public static final int IMAGE_MAX_X_SIZE = 1024;
	public static final int IMAGE_MAX_Y_SIZE = 768;
	public static final String PATH =PropertiesUtil.getProperty("epublisher.filelocationbase");
	//Used for Allowed contents in container area
	public static final String IMAGE = "afbeelding";
	public static final String TEXT = "tekst";
	public static final String VIDEO = "video";
	public static final String CLOCK = "klok";
	public static final String DATE = "datum";
	public static final String RSS = "RSS";
	public static final String TWITTER_FEED = "twitter feed";
	public static final String TITLE = "titel";
	public static final String TELEPHONE_NUMER = "telefoonnummer";
	public static final String TRAVEL = "travel";
	public static final String WEBSITE = "website";
	public static final String TEXT_AND_IMAGE = "tekst+afb.";
	public static final String WAYFINDER = "wayfinder";
	
	//Dashboard Dimensions
	
	public static final String SHAREPOINT_USERS_DIMENSION = "ga:users";
	public static final String SHAREPOINT_USERS_TREND = "ga:users,ga:newUsers";
	public static final String YOY_TABLE = "ga:users,ga:bounceRate,ga:avgTimeOnPage";
	public static final String SHAREPOINT_NEW_USERS_DIMENSION = "ga:newUsers";
	public static final String BOUNCE_RATE_DIMENSION = "ga:bounceRate";
	public static final String AVG_TIME_TREND = "ga:avgTimeOnPage";
	public static final String AVG_SCREEN_TREND = "ga:avgScreenviewDuration";
	public static final String AVG_EXIT_TREND = "ga:exitRate";
	public static final String AVG_EXIT_TREND_APP = "ga:exitRate";
	public static final String SESSIONS = "ga:sessions";
	public static final String AVG_TIME_ON_PAGE = "ga:avgTimeOnPage";
	public static final String USAGE_TABLE = "ga:users,ga:newUsers,ga:exitRate,ga:avgTimeOnPage";
	public static final String USAGE_DATA_APP ="ga:users,ga:newUsers,ga:exitRate,ga:avgScreenviewDuration" ;
	
	public static final Integer SIX_MONTHS = 180;
	public static final Integer TWO_MONTHS = 60;
	public static final Integer YEAR = 365;


	//Dashboard Dimensions

	public static final String DATE_METRIC = "ga:date";
	public static final String YEAR_MONTH = "ga:year,ga:month";
	public static final String MONTH = "ga:month";

	public static final String[] COLUMN_HEADER_USAGE ={"Site","Gebruikers","YoY Groei%","Nieuwe Gebruikers","Bounce%","YoY Bounce%","Avg.Tijd Op Pagina ","YoY Avg.Tijd Op Pagina%"};
	public static final String SHAREPOINT_TREND = "ga:bounceRate,ga:avgTimeOnPage";

	//Top article from google analitics

	public static final String INSITE_METRIC = "ga:uniquePageviews,ga:avgTimeOnPage";
	public static final String INSITE_DIMENSION = "ga:pagePath";
	public static final String INSITE_FILTER = "ga:pagePath=~/actueel";
	public static final String INSITE_SORT = "-ga:uniquePageviews";
	public static final String[] COLUMN_HEADER_INSITE ={"Page Path","Page Views","Avg.Tijd Op Pagina In Min "};
	
	public static final String SOCIAL_BEZOEKERS = "ga:users";
	public static final String SOCIAL_FILTER = "ga:source==whatsapp,ga:source==facebook,ga:source==linkedin,ga:source==email,ga:source==twitter,ga:medium==newsletter;ga:campaign==ea-2020-2021";
	
	public static final String SOCIAL_SOLLICITANTEN = "ga:productCheckouts";


	//Brightcove
	public static final String BRIGHTCOVE_PLAYERS = PropertiesUtil.getProperty("brightcove.players");
	public static final String BRIGHTCOVE_NEWSLETTER_PLAYER = PropertiesUtil.getProperty("brightcove.newsletter.player");
	public static final String BRIGHTCOVE_INSITE_APP_PLAYER = PropertiesUtil.getProperty("bright.insite.app.player");
	public static final String BRIGHTCOVE_SITE_QUERY_URL = PropertiesUtil.getProperty("brightcove.site.query.url");

	//Types / Dashboards
	public static final String OVERVIEW_DASHBOARD = "Overview";
	public static final String NEWSLETTER_DASHBOARD = "EditionNewsletterNS";
	public static final String INSITE_APP_DASHBOARD = "EditionIntranetNS";


	//Piwik
	public static final String PIWIK_TOKEN_URL =PropertiesUtil.getProperty("piwik.token.url");
	public static final String PIWIK_SITE_QUERY_URL = PropertiesUtil.getProperty("piwik.site.query.url");
	public static final String PIWIK_WEBSITE_ID =PropertiesUtil.getProperty("piwik.website.id");
	public static final String SOCIAL_NEWSLETTER =PropertiesUtil.getProperty("social.newsletter.name");
	public static final String PIWIK_CLIENT_ID = PropertiesUtil.getProperty("piwik.client.id");
	public static final String PIWIK_CLIENT_SECRET =PropertiesUtil.getProperty("piwik.client.secret");
	public static final String INSITE_TOP_BLOGS_NAME =PropertiesUtil.getProperty("insite.blogs.name");
	public static final String APP_TOP_BLOGS_NAME =PropertiesUtil.getProperty("app.blogs.name");
	public static final String INSITE_CARROUSEL_NAME =PropertiesUtil.getProperty("insite.carrousel.name");
	public static final String NEWSLETTER_KPI_CLICK_THROUGH_RATE = "newsletter_kpi_click_through_rate";
	public static final String NEWSLETTER_KPI_OPEN_RATE = "newsletter_kpi_open_rate";

	public static final String TO_DATE = "to_date";
	public static final String TO_START_OF_MONTH = "to_start_of_month";
	public static final String TO_START_OF_WEEK = "to_start_of_week";
	public static final String TO_START_OF_QUATER = "to_start_of_quarter";

	public static final String PIWIK_EDITION_FILTER_1 = "{\r\n\t\t\t\t\t\t\"column_id\": \"event_custom_dimension_2\",\r\n\t\t\t\t\t\t\"condition\": {\r\n\t\t\t\t\t\t\t\"operator\": \"eq\",\r\n\t\t\t\t\t\t\t\"value\": \"";
	public static final String PIWIK_EDITION_FILTER_2 = "\"}\r\n}";
	
	public static final String PIWIK_PUBLICATION_FILTER_1 = "{\r\n\"transformation_id\": \"to_path\",\r\n\"column_id\": \"event_url\",\r\n\"condition\": {\r\n\"operator\": \"starts_with\",\r\n\"value\": \"";
	public static final String PIWIK_PUBLICATION_FILTER_2 = "\"}\r\n}";

	//http methods
	public static final String GET = "GET";
	public static final String POST = "POST";
	
	
	//Wordpress
	public static final String WORDPRESS_IFRAME_CONTAINER_START = "<iframe style='width: 100%; height: 336px; position: relative; top: 0px; bottom: 0px; right: 0px; left: 0px;' scrolling='no' frameborder='0'  src= 'https://player.vimeo.com/video/";
	public static final String WORDPRESS_IFRAME_CONTAINER_END = "?controls=1&loop=1'></iframe>";
	public static final String WORDPRESS_MEDIA_UPLOAD_URL = "https://dev-posttest.viewonit.nl/wp-json/wp/v2/media/";
	
	//Password encrypt
	public static final String WORDPRESS_ENCRYPTION_KEY = "!A%C*F-JaNdRgUkX"; // 128 bit key
	public static final String PUBLIC_FORM_SUBMISSION = "o9szYIOq1rRMiouNhNvaq96lqUvCekxR";

	//Tenant ids for narrowcasting files
	public static final String[] TENANT_IDS = {"World_Trade_Center", "Wilhelmina_Ziekenhuis","ProRail","Ziekenhuis_tjongerschans",
      "Zorggroep_almere", "olvg_amsterdam", "prisma_mcp", "Ziekenhuis_Amstelland", "Den_Haag", "Viecuri", "NS", "woord_en_daad", "spaarne_gasthuis","maatwerk","paradigma","storeplay","zorgcentrale_noord", "Haaglanden_Medisch_Centrum"};

	
	//Landing Page Form submissions Subscriber fields
	public static final String SUBSCRIBER = "subscriber";
	public static final String SUBMISSION_FIELDS = "submissionFields";
	public static final String SUBSCRIPTION_GROUP_ID = "sgid";
	public static final String[] SUBSCRIBER_SPECIFIC_FIELDS = { "firstName", "middleName", "lastName", "emailAddress" };
	
	//Railpocket ROB API
	public static final String ROB_API_URL = PropertiesUtil.getProperty("rob.api.url");
	public static final String ROB_API_CLIENT_ID = PropertiesUtil.getProperty("rob.client.id");
	public static final String ROB_API_CLIENT_SECRET = PropertiesUtil.getProperty("rob.client.secret");
	public static final String ROB_API_SUBSCRIPTION_KEY = PropertiesUtil.getProperty("rob.subscription.key");
	public static final String ROB_TEMPLATE_NAME = PropertiesUtil.getProperty("rob.template.name");
	
	public static final String[] ROB_FUNCTION_CODES = {"Hc", "Hcm", "Mcn", "Plp", "Smw", "Mcnm", "Cw", "VenS"};
	public static final String[] ROB_ORGANIZATIONAL_UNITS = {"Mcni", "Mcnt", "Mcnb", "Hcb", "Hci", "Hct"};
	
	//RSS Feeds (main start screen)
	public static final String RSS_FEED_PRISMA_MCP = PropertiesUtil.getProperty("rss.feed.url");
	public static final String RSS_FEED_NS = PropertiesUtil.getProperty("rss.feed.ns");
	
	//Advertisement API
	public static final String ADVERTISEMENT_API_URL = PropertiesUtil.getProperty("advertisement.api.url");
	public static final String ADVERTISEMENT_API_STATUS = PropertiesUtil.getProperty("advertisement.api.status");
	
	 private EpublisherConstants()
	 {
		 //this prevents even the native class from 
		 //calling this ctor as well :
		 throw new AssertionError();
	 }
	
	//Brightcove
	public static final String BRIGHTCOVE_TOKEN_REQUEST_URL = PropertiesUtil.getProperty("brightcove.token.url");
	public static final String BRIGHTCOVE_CREATE_VIDEO_URL = PropertiesUtil.getProperty("brightcove.create.video.url");
	public static final String BRIGHTCOVE_UPLOAD_VIDEO_URL = PropertiesUtil.getProperty("brightcove.upload.video.url");
	public static final String BRIGHTCOVE_MOVE_VIDEO_TO_FOLDER_URL = PropertiesUtil.getProperty("brightcove.move.toFolder.url");
    public static final String BRIGHTCOVE_INGEST_URL = PropertiesUtil.getProperty("brightcove.ingest.url");
	public static final String BRIGHTCOVE_CLIENT_ID = PropertiesUtil.getProperty("brightcove.client.id");
	public static final String BRIGHTCOVE_CLIENT_SECRET = PropertiesUtil.getProperty("brightcove.client.secret");
	public static final String BRIGHTCOVE_ACCOUNTID = PropertiesUtil.getProperty("accountId");
	
	//NS Trein Microservice
	public static final String NS_TREIN_CHANNEL_BASE_URL = PropertiesUtil.getProperty("ns.trein.channel.base.url");
	public static final String NS_TREIN_CHANNEL_PUBLISH_PLAYLIST = "/publishTreinPlaylist";
	public static final String NS_TREIN_CHANNEL_UNPUBLISH_PLAYLIST = "/unpublishTreinPlaylist";
	public static final String NS_TREIN_CHANNEL_DELETE_PLAYLIST = "/deleteTreinPlaylist";
	public static final String NS_TREIN_CHANNEL_PUBLISH_PORTAL_PAGE = "/publishPortalPage";
	public static final String NS_TREIN_CHANNEL_PUBLISH_TTS_ARTICLE = "/publishTTSArticle";

}
