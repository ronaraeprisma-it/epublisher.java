SELECT DISTINCT
	 pt.name
	, pt.publication_id
	, pt.playlistid
	, case  when pt.publication_id= :publicationid then  pt.playlistid else   pt.ancestorplaylistid end 
	, pt.playlistname
	, pt.priority
	, sum(pt.duration) as totalPlaylistDuration
	, pt.settingsdifferentthanpublished
	,  pt.active
	
FROM
	 playlistTime pt left join 
	 	(select * from publication_playlistwrapper pw inner join playlistwrapper p on pw.playlistwrapper_id = p.id where pw.publication_id =:publicationid ) as ppw
	 	on pt.ancestorplaylistid = ppw.playlistid
	 	
where (pt.publication_id !=:publicationid and  pt.ancestorplaylistid is not null) or (pt.publication_id =:publicationid)

	GROUP by 
	 pt.name
	, pt.publication_id
	, pt.playlistid
	, pt.playlistname
	, pt.priority
	, pt.settingsdifferentthanpublished
	,  pt.active
	, pt.ancestorplaylistid
ORDER BY
	pt.priority DESC
	, pt.playlistid  DESC
	