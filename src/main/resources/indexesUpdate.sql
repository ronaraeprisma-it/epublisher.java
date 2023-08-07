-- indexes improeving performance

DO $$
BEGIN
IF NOT EXISTS (
    SELECT 1
    FROM   pg_class c
    JOIN   pg_namespace n ON n.oid = c.relnamespace
    WHERE  c.relname = 'playtime_days_playtime_id_idx'
    AND    n.nspname = 'public' -- 'public' by default
    ) THEN
	CREATE INDEX PlayTime_days_PlayTime_id_idx ON PlayTime_days (PlayTime_id);
	ANALYZE PlayTime_days;
END IF;

IF NOT EXISTS (
    SELECT 1
    FROM   pg_class c
    JOIN   pg_namespace n ON n.oid = c.relnamespace
    WHERE  c.relname = 'playlist_broadcastwrapper_playlist_id_idx'
    AND    n.nspname = 'public' -- 'public' by default
    ) THEN
	CREATE INDEX Playlist_BroadcastWrapper_Playlist_id_idx ON Playlist_BroadcastWrapper (Playlist_id);
	ANALYZE Playlist_BroadcastWrapper;

END IF;

IF NOT EXISTS (
    SELECT 1
    FROM   pg_class c
    JOIN   pg_namespace n ON n.oid = c.relnamespace
    WHERE  c.relname = 'playtime_playlist_id_idx'
    AND    n.nspname = 'public' -- 'public' by default
    ) THEN
	CREATE INDEX PlayTime_Playlist_id_idx ON PlayTime (playlist_id);
	
END IF;


IF NOT EXISTS (
    SELECT 1
    FROM   pg_class c
    JOIN   pg_namespace n ON n.oid = c.relnamespace
    WHERE  c.relname = 'article_numberoftimesviewed_idx' -- check only first
    AND    n.nspname = 'public' -- 'public' by default
    ) THEN
	CREATE INDEX Article_numberOfTimesViewed_idx ON Article (numberOfTimesViewed);
	CREATE INDEX Playlist_Article_articleid_idx ON Article (articleid);
	
END IF;

IF NOT EXISTS (
    SELECT 1
    FROM   pg_class c
    JOIN   pg_namespace n ON n.oid = c.relnamespace
    WHERE  c.relname = 'epublishermedia_thumbnails_id_idx'
    AND    n.nspname = 'public' -- 'public' by default
    ) THEN
	CREATE INDEX EPublisherMedia_thumbnails_id_idx ON EPublisherMedia (thumbnails_id);
	CREATE INDEX EPublisherMedia_images_id_idx ON EPublisherMedia (images_id);
	CREATE INDEX EPublisherMedia_articlewrapper_id_idx ON EPublisherMedia (articlewrapper_id);
	
END IF;

END$$;