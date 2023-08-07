select updateAllSchemaWithGivenQuery('
ALTER TABLE landingpage_form_submission ALTER COLUMN content TYPE varchar(2000);
ALTER TABLE landingpage_form_submission ALTER COLUMN subscriber_email TYPE varchar(255);
ALTER TABLE landingpage_form_submission ALTER COLUMN name TYPE varchar(255);
');
