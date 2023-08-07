select  updateAllSchemaWithGivenQuery('CREATE TABLE schedulePublish (id INTEGER NOT NULL, entityversion INTEGER NOT NULL,edition_id INTEGER NOT NULL, scheduledDate  timestamp without time zone, scheduledhour INTEGER, scheduledminute INTEGER ,status varchar(255), cancelled boolean ,createdBy  varchar(255), updatedtimestamp  varchar(255));

ALTER TABLE schedulePublish
  ADD CONSTRAINT pk_schedulePublish
  PRIMARY KEY (id);

ALTER TABLE schedulePublish
  ADD CONSTRAINT fk_schedulePublish
  FOREIGN KEY (edition_id)
  REFERENCES edition (id); ')