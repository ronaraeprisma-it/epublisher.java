
insert into outputchannel (dtype, id, name, entityversion)
values ('OutputChannelSpecialSiteNS', 5, 'SpecialSiteNS', 1);

ALTER TABLE publication ADD COLUMN targeturl character varying;

-- add new property display date to article
ALTER TABLE article ADD COLUMN displaystartdate timestamp without time zone;
ALTER TABLE article ADD COLUMN displayenddate timestamp without time zone;
  