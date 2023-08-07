CREATE SEQUENCE folderid_seq;

-- we need to use a non SELECT to trick JDBC to accept multiple statements
CREATE table tmp (tmp bigint);
INSERT INTO tmp SELECT setval('folderid_seq', max(folderid) +1) FROM EPublisherMedia;
DROP TABLE tmp;

ALTER TABLE EPublisherMedia ALTER COLUMN folderid SET DEFAULT nextval('folderid_seq');

update EPublisherMedia set folderid = -1 where folderid IS NULL;

ALTER TABLE EPublisherMedia ALTER COLUMN folderid SET NOT NULL;

ALTER SEQUENCE folderid_seq OWNED BY EPublisherMedia.folderid;
