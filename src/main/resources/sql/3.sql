CREATE TABLE contentblock
(
  dtype varchar(31) NOT NULL,
  image_id integer,
  id integer NOT NULL,
  entityversion integer NOT NULL,
  containerid integer NOT NULL,
  timezone varchar(255),
  name varchar(255),
  embedcode text,
  brightcoveid varchar(255),
  url varchar(255),
  content text
);

ALTER TABLE contentblock
  ADD CONSTRAINT pk_contentblock
  PRIMARY KEY (id);

ALTER TABLE contentblock
  ADD CONSTRAINT fk39c3d7b4fd8e9956
  FOREIGN KEY (image_id)
  REFERENCES epublishermedia (id);

CREATE TABLE containerarea
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  containerposition integer NOT NULL
);

ALTER TABLE containerarea
  ADD CONSTRAINT pk_containerarea
  PRIMARY KEY (id);

CREATE TABLE containerarea_allowedcontent
(
  containerarea_id integer NOT NULL,
  allowedcontent varchar(255)
);

ALTER TABLE containerarea_allowedcontent
  ADD CONSTRAINT fkd53058270777507
  FOREIGN KEY (containerarea_id)
  REFERENCES containerarea (id);

CREATE TABLE templatenarrowcasting
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  htmltemplate varchar(255),
  name varchar(255),
  previewimage_id integer
);

ALTER TABLE templatenarrowcasting
  ADD CONSTRAINT pk_templatenarrowcasting
  PRIMARY KEY (id);

ALTER TABLE templatenarrowcasting
  ADD CONSTRAINT fkcb7d950ee67b351e
  FOREIGN KEY (previewimage_id)
  REFERENCES epublishermedia (id);
  
CREATE TABLE templatenarrowcasting_containerarea
(
  templatenarrowcasting_id integer NOT NULL,
  containerareas_id integer NOT NULL
);

ALTER TABLE templatenarrowcasting_containerarea
  ADD CONSTRAINT pk_templatenarrowcasting_containerarea
  PRIMARY KEY (containerareas_id, templatenarrowcasting_id);

ALTER TABLE templatenarrowcasting_containerarea
  ADD CONSTRAINT fk81b60f1d44123bc7
  FOREIGN KEY (templatenarrowcasting_id)
  REFERENCES templatenarrowcasting (id);
ALTER TABLE templatenarrowcasting_containerarea
  ADD CONSTRAINT fk81b60f1d107ca090
  FOREIGN KEY (containerareas_id)
  REFERENCES containerarea (id);
CREATE UNIQUE INDEX templatenarrowcasting_containerarea_containerareas_id_key ON templatenarrowcasting_containerarea (containerareas_id);

CREATE TABLE broadcast
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  description varchar(255),
  duration integer NOT NULL,
  name varchar(255),
  template_id integer,
  datecreated timestamp without time zone
);

ALTER TABLE broadcast
  ADD CONSTRAINT pk_broadcast
  PRIMARY KEY (id);

ALTER TABLE broadcast
  ADD CONSTRAINT fk16f408a18ff9251b
  FOREIGN KEY (template_id)
  REFERENCES templatenarrowcasting (id);
CREATE TABLE broadcast_contentblock
(
  broadcast_id integer NOT NULL,
  contentblocks_id integer NOT NULL
);

ALTER TABLE broadcast_contentblock
  ADD CONSTRAINT pk_broadcast_contentblock
  PRIMARY KEY (broadcast_id, contentblocks_id);

ALTER TABLE broadcast_contentblock
  ADD CONSTRAINT fk8cb80692e908ae87
  FOREIGN KEY (broadcast_id)
  REFERENCES broadcast (id);
ALTER TABLE broadcast_contentblock
  ADD CONSTRAINT fk8cb806925b69c222
  FOREIGN KEY (contentblocks_id)
  REFERENCES contentblock (id);
CREATE UNIQUE INDEX broadcast_contentblock_contentblocks_id_key ON broadcast_contentblock (contentblocks_id);

