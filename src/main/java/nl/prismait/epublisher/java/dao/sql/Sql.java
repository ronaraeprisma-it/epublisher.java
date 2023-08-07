package nl.prismait.epublisher.java.dao.sql;

import nl.prismait.epublisher.java.dao.util.SqlUtils;

public class Sql
{
	public static final String ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY;
	public static final String CURRENT_PLAYLISTS_AND_BROADCASTS_FOR_SCREENGROUP;
	public static final String ALL_SCREENGROUPS_TOP_DOWN_QUERY;
	public static final String ALL_ACTIVE_PLAYLISTS_AND_SCREENGROUPS_FOR_PUBLICATION;
	public static final String USERS_WITH_ACCESS_TO_PUBLICATION;
	public static final String CORRECT_PLAYLIST_PRIORITIES_TO_NEW_PRIORITY;
	public static final String GET_DATASET_FOR_SCREEN_TREE;
	public static final String GET_ARTICLES_BY_PUBLICATION;
	public static final String GET_ARTICLES_BY_GROUPS;
	public static final String GET_ALL_SCREENGROUPS;
	public static final String ACTIVE_PLAYLIST_BROADCAST_ASSETS;
	public static final String ALL_ACTIVE_PLAYLISTS_WITH_DURATION_FOR_PUBLICATION;
	public static final String ACTIVE_PLAYLISTS_FOR_PUBLISH_FOR_SCREENGROUP_QUERY;
	public static final String ANCESTOR_PLAYLISTS_FOR_SCREENGROUP_QUERY;
	public static final String GET_ALL_AUDIENCEGROUPS;
	public static final String GET_DATASET_FOR_AUDIENCE_TREE;
	public static final String GET_ARCHIVE_ARTICLES_FOR_INTRANET_OR_RAILPOCKET;
	public static final String GET_OPT_OUT_ABONNEES;
	public static final String GET_ARTICLE_FOR_RSSFEED;
	public static final String GET_ARTICLES_BY_GROUPS_NEW;
	public static final String GET_ARTICLES_BY_PUBLICATION_V4;
	public static final String INTERNAL_ACTIVE_PLAYLISTS_QUERY;
	public static final String EXTERNAL_ACTIVE_PLAYLISTS_QUERY;
	public static final String ACTIVE_PLAYLIST_ID;
	
	static
	{
		// Queries are loaded from the classpath (src/main/resources/queries)
		ACTIVE_PLAYLISTS_FOR_SCREENGROUP_QUERY = SqlUtils.loadSqlFile("queries/active_playlists_for_screengroup_query.sql");
		CURRENT_PLAYLISTS_AND_BROADCASTS_FOR_SCREENGROUP = SqlUtils.loadSqlFile("queries/current_playlists_and_broadcasts_for_screengroup.sql");
		ALL_SCREENGROUPS_TOP_DOWN_QUERY = SqlUtils.loadSqlFile("queries/all_screengroups_top_down_query.sql");
		ALL_ACTIVE_PLAYLISTS_AND_SCREENGROUPS_FOR_PUBLICATION = SqlUtils.loadSqlFile("queries/all_active_playlists_and_screengroups_for_publication.sql");
		USERS_WITH_ACCESS_TO_PUBLICATION = SqlUtils.loadSqlFile("queries/users_with_access_to_publication.sql");
		CORRECT_PLAYLIST_PRIORITIES_TO_NEW_PRIORITY = SqlUtils.loadSqlFile("queries/correct_playlist_priorities_to_new_priority.sql");
		GET_DATASET_FOR_SCREEN_TREE = SqlUtils.loadSqlFile("queries/get_dataset_for_screen_tree.sql");
		GET_ARTICLES_BY_PUBLICATION= SqlUtils.loadSqlFile("queries/get_articles_By_Publication.sql");
		GET_ARTICLES_BY_GROUPS= SqlUtils.loadSqlFile("queries/get_articles_By_Groups.sql");
		GET_ALL_SCREENGROUPS=SqlUtils.loadSqlFile("queries/get_all_screengroups.sql");
		ACTIVE_PLAYLIST_BROADCAST_ASSETS=SqlUtils.loadSqlFile("queries/active_playlist_broadcast_assets.sql");
		ACTIVE_PLAYLISTS_FOR_PUBLISH_FOR_SCREENGROUP_QUERY = SqlUtils.loadSqlFile("queries/active_playlists__for_publish_for_screengroup_query.sql");
		ALL_ACTIVE_PLAYLISTS_WITH_DURATION_FOR_PUBLICATION = SqlUtils.loadSqlFile("queries/all_active_playlists_with_duration_for_publication.sql");	
		ANCESTOR_PLAYLISTS_FOR_SCREENGROUP_QUERY = SqlUtils.loadSqlFile("queries/ancestor_playlists_for_screengroup_query.sql");
		GET_ALL_AUDIENCEGROUPS=SqlUtils.loadSqlFile("queries/get_all_audiencegroups.sql");
		GET_DATASET_FOR_AUDIENCE_TREE=SqlUtils.loadSqlFile("queries/get_dataset_for_audience_tree.sql");
		GET_ARCHIVE_ARTICLES_FOR_INTRANET_OR_RAILPOCKET = SqlUtils.loadSqlFile("queries/get_archive_article_for_intranet.sql");
		GET_OPT_OUT_ABONNEES = SqlUtils.loadSqlFile("/queries/get_opt_out_abonnees.sql");
		GET_ARTICLE_FOR_RSSFEED = SqlUtils.loadSqlFile("queries/get_article_for_rssfeed.sql");
		GET_ARTICLES_BY_GROUPS_NEW= SqlUtils.loadSqlFile("queries/get_articles_By_Groups_V4.sql");
		GET_ARTICLES_BY_PUBLICATION_V4= SqlUtils.loadSqlFile("queries/get_articles_By_Publication_V4.sql");
		INTERNAL_ACTIVE_PLAYLISTS_QUERY = SqlUtils.loadSqlFile("queries/internal_active_playlists_query.sql");
		EXTERNAL_ACTIVE_PLAYLISTS_QUERY = SqlUtils.loadSqlFile("queries/external_active_playlists_query.sql");
		ACTIVE_PLAYLIST_ID = SqlUtils.loadSqlFile("queries/active_playlist_id.sql");
		
	}
}
