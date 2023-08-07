select updatespecificschema('world_trade_center',
'
UPDATE broadcastwrapper
SET dtype = ''BroadcastWayfinder''
WHERE broadcast_id IN (
SELECT b.id
	FROM broadcast as b
	INNER JOIN broadcastwrapper as bw 
	ON b.id = bw.broadcast_id
	where b.wayfinder is true
);


UPDATE playlist
SET dtype=''PlaylistWayfinder''
WHERE ID IN (
	SELECT playlist_id
		FROM playlist_broadcastwrapper as plbw
		JOIN broadcastwrapper as bw
			ON plbw.broadcastwrappers_id = bw.id
				WHERE bw.broadcast_id IN (
					SELECT b.id
						FROM broadcast as b
						INNER JOIN broadcastwrapper as bw 
						ON b.id = bw.broadcast_id
						WHERE b.wayfinder is true
					)
);

UPDATE publication
	SET dtype = ''PublicationWayfinder'',
		outputchannel_id = 8
	WHERE id in (
		SELECT publication_id
			FROM playlist as pl
			WHERE pl.dtype = ''PlaylistWayfinder''
);

DELETE FROM group_publication
	WHERE publicationid IN (
			SELECT id
			FROM publication as p
			WHERE p.dtype = ''PublicationWayfinder''
	);
'
);
