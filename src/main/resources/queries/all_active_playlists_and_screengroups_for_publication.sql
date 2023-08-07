SELECT DISTINCT
	pt.groupname
	, pt.name
	, pt.publication_id
	, pt.playlistid
	, pt.playlistname
	, pt.priority
	, ppw.orderOfPlaylist
	
FROM
	 playlistTime pt left join 
	 	(select * from publication_playlistwrapper pw inner join playlistwrapper p on pw.playlistwrapper_id = p.id where pw.publication_id =:publicationid ) as ppw
	 	on pt.playlistid = ppw.playlistid
ORDER BY
	pt.priority DESC
	,ppw.orderOfplaylist
	, pt.groupname
	, pt.playlistname
