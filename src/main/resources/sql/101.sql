select updateAllSchemaWithGivenQuery('
UPDATE epublisheruser
	SET showonlyfavoritearticles = false
	WHERE showonlyfavoritearticles IS null; 
;')