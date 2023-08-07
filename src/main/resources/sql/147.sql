select  updateAllSchemaWithGivenQuery('
ALTER table templatenarrowcasting
	ADD COLUMN regeneratethumbnail boolean DEFAULT false;
');