select  updateAllSchemaWithGivenQuery('
UPDATE templatenarrowcasting
	SET previewimage_id=null;

UPDATE templatenarrowcasting
SET previewimage_id = subquery.thumbnail_id
FROM (SELECT thumbnail_id, template_id 
	  	FROM broadcast
			WHERE template_id in (select templates.id from templatenarrowcasting templates)
	  		AND thumbnail_id IS NOT NULL
		) AS subquery
WHERE templatenarrowcasting.id = subquery.template_id 
AND previewimage_id is NULL
AND name Not Like ''%Video%'';
');