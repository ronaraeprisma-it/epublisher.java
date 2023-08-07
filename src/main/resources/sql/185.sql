select updateAllSchemaWithGivenQuery('
ALTER TABLE articleextension ADD COLUMN starthour integer;
ALTER TABLE articleextension ADD COLUMN startminute integer;
ALTER TABLE articleextension ADD COLUMN endhour integer DEFAULT 23;
ALTER TABLE articleextension ADD COLUMN endminute integer DEFAULT 59;

');