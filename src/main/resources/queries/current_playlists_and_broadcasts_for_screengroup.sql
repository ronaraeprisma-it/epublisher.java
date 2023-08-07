SELECT
		pt.id AS screengroupId
		, pt.groupname
		, pt.screengroup_id AS screengroup_parent
		, pt.publication_id
		, pt.name
		, pt.playlistname
		, pt.priority
		, pt.playlistid
		, pt.orderofbroadcast
		, pt.broadcastwrapper_id
		, pt.broadcast_id
		, pnw.orderOfplaylist
		, pt.starthour,pt.startminute,pt.endhour,pt.endminute
FROM
	playlistTime pt left join (select * from publication_playlistwrapper pw inner join playlistwrapper p on pw.playlistwrapper_id = p.id where pw.publication_id =:publicationId ) as pnw on pt.playlistid = pnw.playlistid
WHERE
		pt.broadcastwrapper_id IS NOT NULL
		AND
		pt.broadcast_id IS NOT NULL
		AND
		pt.priority = (
			SELECT
					max(priority)
			FROM
					playlistTime
			)			
		AND pt.broadcast_id  not in (select disabledbroadcast_id from publication_disabledbroadcast where publication_id =:publicationId)
	
		ORDER BY pnw.orderofplaylist asc,
				 pt.orderofbroadcast asc
