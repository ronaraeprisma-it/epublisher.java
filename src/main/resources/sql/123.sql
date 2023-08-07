CREATE SCHEMA IF NOT EXISTS Zaan_Apotheek  AUTHORIZATION epublisher;




-----------------------------------------------------------------------------------------------------------------------------------------------


CREATE SEQUENCE Zaan_Apotheek .epublisher_sequence INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1  NO CYCLE;

CREATE SEQUENCE Zaan_Apotheek .folderid_seq INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1  NO CYCLE;

CREATE TABLE Zaan_Apotheek .article (id INTEGER NOT NULL, articleid CHARACTER VARYING(255), availableoutsidepublication BOOLEAN NOT NULL, category CHARACTER VARYING(255), content TEXT, doctoken TEXT, documentsource CHARACTER VARYING(255), lastchangeddate TIMESTAMP(6) WITHOUT TIME ZONE, lastpublicationdate TIMESTAMP(6) WITHOUT TIME ZONE, numberoftimesviewed INTEGER NOT NULL, prologue TEXT, sourcedate TIMESTAMP(6) WITHOUT TIME ZONE, sourceurl CHARACTER VARYING(255), title CHARACTER VARYING(255), type CHARACTER VARYING(255), updateallowed BOOLEAN NOT NULL, version INTEGER NOT NULL, lasteditedby_id INTEGER, publication_id INTEGER, relevantarticles_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, displaystartdate TIMESTAMP(6) WITHOUT TIME ZONE, displayenddate TIMESTAMP(6) WITHOUT TIME ZONE, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .article_extensions (article_id INTEGER NOT NULL, articleextensions_id INTEGER NOT NULL, PRIMARY KEY (article_id, articleextensions_id), UNIQUE (articleextensions_id));

CREATE TABLE Zaan_Apotheek .article_tags (article_id INTEGER NOT NULL, tags CHARACTER VARYING(255));

CREATE TABLE Zaan_Apotheek .articleextension (dtype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, enddate DATE, header CHARACTER VARYING(255), startdate DATE, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .articleextension_functiongroup (articleextension_id INTEGER NOT NULL, functiongroups_id INTEGER NOT NULL, index INTEGER NOT NULL, CONSTRAINT pk_artext_funct PRIMARY KEY (articleextension_id, functiongroups_id));

CREATE TABLE Zaan_Apotheek .articlewrapper (dtype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, numberoftimesviewed INTEGER NOT NULL, orderofarticle INTEGER, categorya BOOLEAN, categoryb BOOLEAN, categoryc BOOLEAN, highlighted BOOLEAN, onhomepage BOOLEAN, rank INTEGER, article_id INTEGER, category_id INTEGER, thumbnailimage_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, alreadypublished BOOLEAN DEFAULT false, edition_id INTEGER, urgent BOOLEAN DEFAULT false NOT NULL, PRIMARY KEY (id), CONSTRAINT constraint_unique_articlewrapper_edition_id_article_id UNIQUE (edition_id, article_id));

CREATE TABLE Zaan_Apotheek .broadcast (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, description CHARACTER VARYING(255), duration INTEGER NOT NULL, name CHARACTER VARYING(255), template_id INTEGER, datecreated TIMESTAMP(6) WITHOUT TIME ZONE, displaystartdate TIMESTAMP(6) WITHOUT TIME ZONE, displayenddate TIMESTAMP(6) WITHOUT TIME ZONE, createdby INTEGER, CONSTRAINT pk_broadcast PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .broadcast_contentblock (broadcast_id INTEGER NOT NULL, contentblocks_id INTEGER NOT NULL, CONSTRAINT pk_broadcast_contentblock PRIMARY KEY (broadcast_id, contentblocks_id));

CREATE TABLE Zaan_Apotheek .broadcastplay (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, screenid INTEGER NOT NULL, publicationid INTEGER NOT NULL, playlistid INTEGER NOT NULL, broadcastid INTEGER NOT NULL, playtime TIMESTAMP(6) WITHOUT TIME ZONE, CONSTRAINT pk_broadcastplay PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .broadcastwrapper (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, alreadypublished BOOLEAN NOT NULL, orderofbroadcast INTEGER, broadcast_id INTEGER, CONSTRAINT pk_broadcastwrapper PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .categories (etemplate_id INTEGER NOT NULL, categories CHARACTER VARYING(255));

CREATE TABLE Zaan_Apotheek .category (id INTEGER NOT NULL, name CHARACTER VARYING(255), entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .containerarea (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, containerposition INTEGER NOT NULL, CONSTRAINT pk_containerarea PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .containerarea_allowedcontent (containerarea_id INTEGER NOT NULL, allowedcontent CHARACTER VARYING(255));

CREATE TABLE Zaan_Apotheek .contentblock (dtype CHARACTER VARYING(31) NOT NULL, image_id INTEGER, id INTEGER NOT NULL, entityversion INTEGER NOT NULL, containerid INTEGER NOT NULL, timezone CHARACTER VARYING(255), name CHARACTER VARYING(255), embedcode TEXT, brightcoveid CHARACTER VARYING(255), url CHARACTER VARYING(255), content TEXT, enabled BOOLEAN NOT NULL, title CHARACTER VARYING(30), CONSTRAINT pk_contentblock PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .edition (dtype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, editionnumber INTEGER NOT NULL, lastupdated TIMESTAMP(6) WITHOUT TIME ZONE, name CHARACTER VARYING(255) NOT NULL, publicationdate TIMESTAMP(6) WITHOUT TIME ZONE, totalnumberofbounces INTEGER, totalnumberofsentmails INTEGER, emailremark CHARACTER VARYING(255), publication_id INTEGER, subscriber_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, emailaddress CHARACTER VARYING(255), emailsubject CHARACTER VARYING(255), prologuetext TEXT, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .edition_category (edition_id INTEGER NOT NULL, categories_id INTEGER NOT NULL, index_col INTEGER NOT NULL, PRIMARY KEY (edition_id, index_col), UNIQUE (categories_id));

CREATE TABLE Zaan_Apotheek .edition_subscriptiongroup (edition_id INTEGER NOT NULL, subscriptiongroups_id INTEGER NOT NULL, PRIMARY KEY (edition_id, subscriptiongroups_id));

CREATE TABLE Zaan_Apotheek .epublishermedia (mediatype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, name CHARACTER VARYING(255), url CHARACTER VARYING(255), filename CHARACTER VARYING(255), filesizeinbytes BIGINT, liferayfileentryid BIGINT, alttext CHARACTER VARYING(255), caption CHARACTER VARYING(255), height INTEGER, width INTEGER, thumbnails_id INTEGER, images_id INTEGER, attachedurls_id INTEGER, attacheddocuments_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, articlewrapper_id INTEGER, originalimage_id INTEGER, sortorder INTEGER DEFAULT '-1'::integer NOT NULL, generated BOOLEAN, parentimageid INTEGER, username CHARACTER VARYING(255), uuid CHARACTER VARYING(255), folderid BIGINT DEFAULT nextval('folderid_seq'::regclass) NOT NULL, version CHARACTER VARYING(75), PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .epublisheruser (id INTEGER NOT NULL, articlepreviewmethod CHARACTER VARYING(255), email CHARACTER VARYING(255), employeenumber CHARACTER VARYING(255), firstname CHARACTER VARYING(255), lastname CHARACTER VARYING(255), middlename CHARACTER VARYING(255), password CHARACTER VARYING(255), phonenumber CHARACTER VARYING(255), preferedscreen CHARACTER VARYING(255), entityversion INTEGER DEFAULT 1 NOT NULL, passwordnonce CHARACTER VARYING(255), PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .epublisheruser_allowedbrightcovefolders (user_id INTEGER NOT NULL, allowedbrightcovefolders CHARACTER VARYING(255));

CREATE TABLE Zaan_Apotheek .epublisheruser_profile (epublisheruser_id INTEGER NOT NULL, profiles_id INTEGER NOT NULL, PRIMARY KEY (epublisheruser_id, profiles_id), UNIQUE (profiles_id));

CREATE TABLE Zaan_Apotheek .epublisheruser_publication (epublisheruser_id INTEGER NOT NULL, availablepublications_id INTEGER NOT NULL, PRIMARY KEY (epublisheruser_id, availablepublications_id));

CREATE TABLE Zaan_Apotheek .epublisheruser_templatenarrowcasting (epublisheruser_id INTEGER NOT NULL, availabletemplates_id INTEGER NOT NULL, PRIMARY KEY (epublisheruser_id, availabletemplates_id));

CREATE TABLE Zaan_Apotheek .functiongroup (id INTEGER NOT NULL, abbreviation CHARACTER VARYING(255), name CHARACTER VARYING(255), entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .imagesize (id INTEGER NOT NULL, height INTEGER NOT NULL, name CHARACTER VARYING(255), width INTEGER NOT NULL, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .ipv4restrictor (id INTEGER NOT NULL, ip1 BIGINT NOT NULL, ip2 BIGINT, CONSTRAINT pk_ipv4restrictor PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .outputchannel (dtype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, name CHARACTER VARYING(255) NOT NULL, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .playlist (id INTEGER NOT NULL, publication_id INTEGER, entityversion INTEGER NOT NULL, deleted BOOLEAN NOT NULL, description CHARACTER VARYING(255), lastupdated TIMESTAMP(6) WITHOUT TIME ZONE, name CHARACTER VARYING(255) NOT NULL, priority INTEGER NOT NULL, publicationdate TIMESTAMP(6) WITHOUT TIME ZONE, settingsdifferentthanpublished BOOLEAN NOT NULL, ancestorplaylistid INTEGER, CONSTRAINT pk_playlist PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .playlist_broadcastwrapper (playlist_id INTEGER NOT NULL, broadcastwrappers_id INTEGER NOT NULL);

CREATE TABLE Zaan_Apotheek .playtime (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, startdate TIMESTAMP(6) WITHOUT TIME ZONE, enddate TIMESTAMP(6) WITHOUT TIME ZONE, starthour INTEGER, startminute INTEGER, endhour INTEGER, endminute INTEGER, playlist_id INTEGER, CONSTRAINT pk_playtime PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .playtime_days (playtime_id INTEGER NOT NULL, days INTEGER);

CREATE TABLE Zaan_Apotheek .profile (id INTEGER NOT NULL, name CHARACTER VARYING(255), profileactive BOOLEAN NOT NULL, includelatesteditionfrompublication_id INTEGER, searchobject_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .profilesources (searchobject_id INTEGER NOT NULL, sources CHARACTER VARYING(255));

CREATE TABLE Zaan_Apotheek .publication (dtype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, name CHARACTER VARYING(255) NOT NULL, numberoffeaturedarticles INTEGER, outputchannel_id INTEGER, template_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, deleted BOOLEAN NOT NULL, deleteddatetime TIMESTAMP(6) WITHOUT TIME ZONE, maxplaylistpriority INTEGER, targeturl CHARACTER VARYING, urgentarticlesenabled BOOLEAN, emailaddress CHARACTER VARYING(255), emailsubject CHARACTER VARYING(255), sharestrategy CHARACTER VARYING(25) DEFAULT 'SHARESTRATEGYPRIVATE'::character varying, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .publication_group (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, apiid INTEGER, name CHARACTER VARYING(255) NOT NULL, type CHARACTER VARYING(255), PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .publication_group_epublishermedia (publication_group_id INTEGER NOT NULL, images_id INTEGER NOT NULL, PRIMARY KEY (publication_group_id, images_id), UNIQUE (images_id));

CREATE TABLE Zaan_Apotheek .publication_group_publication (publication_group_id INTEGER NOT NULL, publications_id INTEGER NOT NULL, PRIMARY KEY (publication_group_id, publications_id));

CREATE TABLE Zaan_Apotheek .publication_imagesize (publication_id INTEGER NOT NULL, allowedthumbnailsizes_id INTEGER NOT NULL, thumbsize_index_col INTEGER NOT NULL, CONSTRAINT pk_publication_imagesize PRIMARY KEY (publication_id, thumbsize_index_col));

CREATE TABLE Zaan_Apotheek .publication_subscriptiongroup (publication_id INTEGER NOT NULL, availablesubscriptiongroups_id INTEGER NOT NULL, CONSTRAINT p_asg_pid_asgid_pkey PRIMARY KEY (publication_id, availablesubscriptiongroups_id));

CREATE TABLE Zaan_Apotheek .resourceinfo (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, mimetype CHARACTER VARYING(255), name CHARACTER VARYING(255), reference CHARACTER VARYING(255), resources_id INTEGER, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .rssfeed (id INTEGER NOT NULL, description CHARACTER VARYING(2000) NOT NULL, feedname CHARACTER VARYING(50) NOT NULL, maxentries INTEGER NOT NULL, title CHARACTER VARYING(100) NOT NULL, ttl INTEGER NOT NULL, publication_id INTEGER NOT NULL, CONSTRAINT pk_rssfeed PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .rssfeed_ipv4restrictor (rssfeed_id INTEGER NOT NULL, allowedips_id INTEGER NOT NULL);

CREATE TABLE Zaan_Apotheek .schemaversiontable (schemaversioncolumn INTEGER NOT NULL);

CREATE TABLE Zaan_Apotheek .screen (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, description CHARACTER VARYING(255), displayid CHARACTER VARYING(255), name CHARACTER VARYING(255), resolutionheight INTEGER NOT NULL, resolutionwidth INTEGER NOT NULL, touchenabled BOOLEAN, screengroup_id INTEGER, datetimelastrequest TIMESTAMP(6) WITHOUT TIME ZONE, minvideoresolutionwidth INTEGER, minvideoresolutionheight INTEGER, displayname CHARACTER VARYING(255), locationcode CHARACTER VARYING(255), location CHARACTER VARYING(255), backgroundimage_id INTEGER, CONSTRAINT pk_screen PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .screengroup (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, description CHARACTER VARYING(255), name CHARACTER VARYING(255), screengroup_id INTEGER, publication_id INTEGER, backgroundimage_id INTEGER, CONSTRAINT pk_screengroup PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .searchobject (id INTEGER NOT NULL, fromdate TIMESTAMP(6) WITHOUT TIME ZONE, numberofdaysfrompresent INTEGER NOT NULL, searchstring CHARACTER VARYING(255), todate TIMESTAMP(6) WITHOUT TIME ZONE, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .subscriber (id INTEGER NOT NULL, emailaddress CHARACTER VARYING(255) NOT NULL, firstname CHARACTER VARYING(255), lastname CHARACTER VARYING(255), middlename CHARACTER VARYING(255), entityversion INTEGER DEFAULT 1 NOT NULL, dtype CHARACTER VARYING(31) DEFAULT 'Subscriber'::character varying NOT NULL, origin CHARACTER VARYING(255) DEFAULT 'INTERNAL'::character varying NOT NULL, externidentifier CHARACTER VARYING(255), PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .subscriber_subscriptiongroupfilter (subscriber_id INTEGER NOT NULL, subscribersfilters_id INTEGER NOT NULL, PRIMARY KEY (subscriber_id, subscribersfilters_id));

CREATE TABLE Zaan_Apotheek .subscriptiongroup (id INTEGER NOT NULL, name CHARACTER VARYING(255), testgroup BOOLEAN NOT NULL, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .subscriptiongroup_subscriber (subscriptiongroup_id INTEGER NOT NULL, subscribers_id INTEGER NOT NULL, subscriberstate CHARACTER VARYING(255) DEFAULT 'ACTIVE'::character varying NOT NULL, PRIMARY KEY (subscriptiongroup_id, subscribers_id), UNIQUE (subscribers_id));

CREATE TABLE Zaan_Apotheek .subscriptiongroup_subscriptiongroupfilter (subscriptiongroup_id INTEGER NOT NULL, subscriptiongroupfilter_id INTEGER NOT NULL, PRIMARY KEY (subscriptiongroup_id, subscriptiongroupfilter_id));

CREATE TABLE Zaan_Apotheek .subscriptiongroupfilter (id INTEGER NOT NULL, filtercode CHARACTER VARYING(255) NOT NULL, filtertype CHARACTER VARYING(255) NOT NULL, filtervalue CHARACTER VARYING(255), entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .template (id INTEGER NOT NULL, articlerepresentation CHARACTER VARYING(255), colophontext TEXT, csstemplatetouse CHARACTER VARYING(255), footertext TEXT, freemarkertemplatetouse CHARACTER VARYING(255), includecolophon BOOLEAN NOT NULL, includedateinheader BOOLEAN NOT NULL, includeeditionnumber BOOLEAN NOT NULL, includefooter BOOLEAN NOT NULL, includeprologue BOOLEAN NOT NULL, includerecipientname BOOLEAN NOT NULL, includetableofcontent BOOLEAN NOT NULL, name CHARACTER VARYING(255), prologuetext TEXT, reactionemailaddress CHARACTER VARYING(255), reactionpossible BOOLEAN NOT NULL, title CHARACTER VARYING(255), brandimage_id INTEGER, footerimage_id INTEGER, headerimage_id INTEGER, includecancelsubscription BOOLEAN NOT NULL, includebrowserview BOOLEAN NOT NULL, printingallowed BOOLEAN DEFAULT false NOT NULL, entityversion INTEGER DEFAULT 1 NOT NULL, includeimagesinnewsletter BOOLEAN NOT NULL, newsletterthumbnailsimagesize_id INTEGER, deleted BOOLEAN DEFAULT false NOT NULL, showsourceslist BOOLEAN DEFAULT true NOT NULL, PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .template_category (template_id INTEGER NOT NULL, categories_id INTEGER NOT NULL, index_col INTEGER NOT NULL, CONSTRAINT pk_template_category PRIMARY KEY (index_col, template_id));

CREATE TABLE Zaan_Apotheek .templatenarrowcasting (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, htmltemplate CHARACTER VARYING(255), name CHARACTER VARYING(255), previewimage_id INTEGER, CONSTRAINT pk_templatenarrowcasting PRIMARY KEY (id));

CREATE TABLE Zaan_Apotheek .templatenarrowcasting_containerarea (templatenarrowcasting_id INTEGER NOT NULL, containerareas_id INTEGER NOT NULL, CONSTRAINT pk_templatenarrowcasting_containerarea PRIMARY KEY (containerareas_id, templatenarrowcasting_id));

CREATE TABLE Zaan_Apotheek .epublisheruser_favoritearticles (article_id INT NOT NULL, epublisheruser_id INT NOT NULL, CONSTRAINT favarticle_articleid FOREIGN KEY ("article_id") REFERENCES Zaan_Apotheek .article ("id"), CONSTRAINT favarticle_epubuserid FOREIGN KEY ("epublisheruser_id") REFERENCES Zaan_Apotheek .epublisheruser ("id") );

ALTER TABLE Zaan_Apotheek .article ADD CONSTRAINT a_a_raid_fkey FOREIGN KEY ("relevantarticles_id") REFERENCES Zaan_Apotheek .article ("id");
ALTER TABLE Zaan_Apotheek .article ADD CONSTRAINT a_epu_lebid_fkey FOREIGN KEY ("lasteditedby_id") REFERENCES Zaan_Apotheek .epublisheruser ("id");
ALTER TABLE Zaan_Apotheek .article ADD CONSTRAINT a_p_pid_fkey FOREIGN KEY ("publication_id") REFERENCES Zaan_Apotheek .publication ("id");
ALTER TABLE Zaan_Apotheek .article_extensions ADD CONSTRAINT fk32bef73d424b17e6 FOREIGN KEY ("articleextensions_id") REFERENCES Zaan_Apotheek .articleextension ("id");
ALTER TABLE Zaan_Apotheek .article_extensions ADD CONSTRAINT fk32bef73de0162f4d FOREIGN KEY ("article_id") REFERENCES Zaan_Apotheek .article ("id");
ALTER TABLE Zaan_Apotheek .article_tags ADD CONSTRAINT a_t_a_aid_fkey FOREIGN KEY ("article_id") REFERENCES Zaan_Apotheek .article ("id");
ALTER TABLE Zaan_Apotheek .articleextension_functiongroup ADD CONSTRAINT ae_fg_ae_aeid_fkey FOREIGN KEY ("articleextension_id") REFERENCES Zaan_Apotheek .articleextension ("id");
ALTER TABLE Zaan_Apotheek .articleextension_functiongroup ADD CONSTRAINT ae_fg_fg_fgid_fkey FOREIGN KEY ("functiongroups_id") REFERENCES Zaan_Apotheek .functiongroup ("id");
ALTER TABLE Zaan_Apotheek .articlewrapper ADD CONSTRAINT aw_a_aid_fkey FOREIGN KEY ("article_id") REFERENCES Zaan_Apotheek .article ("id");
ALTER TABLE Zaan_Apotheek .articlewrapper ADD CONSTRAINT aw_c_cid_fkey FOREIGN KEY ("category_id") REFERENCES Zaan_Apotheek .category ("id");
ALTER TABLE Zaan_Apotheek .articlewrapper ADD CONSTRAINT aw_e_eid_fkey FOREIGN KEY ("edition_id") REFERENCES Zaan_Apotheek .edition ("id");
ALTER TABLE Zaan_Apotheek .articlewrapper ADD CONSTRAINT aw_epm_tiid_fkey FOREIGN KEY ("thumbnailimage_id") REFERENCES Zaan_Apotheek .epublishermedia ("id");
ALTER TABLE Zaan_Apotheek .broadcast ADD CONSTRAINT fk16f408a18ff9251b FOREIGN KEY ("template_id") REFERENCES Zaan_Apotheek .templatenarrowcasting ("id");
ALTER TABLE Zaan_Apotheek .broadcast_contentblock ADD CONSTRAINT fk8cb806925b69c222 FOREIGN KEY ("contentblocks_id") REFERENCES Zaan_Apotheek .contentblock ("id");
ALTER TABLE Zaan_Apotheek .broadcast_contentblock ADD CONSTRAINT fk8cb80692e908ae87 FOREIGN KEY ("broadcast_id") REFERENCES Zaan_Apotheek .broadcast ("id");
ALTER TABLE Zaan_Apotheek .broadcastwrapper ADD CONSTRAINT fkee24cb2e908ae87 FOREIGN KEY ("broadcast_id") REFERENCES Zaan_Apotheek .broadcast ("id");
ALTER TABLE Zaan_Apotheek .containerarea_allowedcontent ADD CONSTRAINT fkd53058270777507 FOREIGN KEY ("containerarea_id") REFERENCES Zaan_Apotheek .containerarea ("id");
ALTER TABLE Zaan_Apotheek .contentblock ADD CONSTRAINT fk39c3d7b4fd8e9956 FOREIGN KEY ("image_id") REFERENCES Zaan_Apotheek .epublishermedia ("id");
ALTER TABLE Zaan_Apotheek .edition ADD CONSTRAINT e_p_pid_fkey FOREIGN KEY ("publication_id") REFERENCES Zaan_Apotheek .publication ("id");
ALTER TABLE Zaan_Apotheek .edition ADD CONSTRAINT e_s_sid_fkey FOREIGN KEY ("subscriber_id") REFERENCES Zaan_Apotheek .subscriber ("id");
ALTER TABLE Zaan_Apotheek .edition_category ADD CONSTRAINT e_c_c_cid_fkey FOREIGN KEY ("categories_id") REFERENCES Zaan_Apotheek .category ("id");
ALTER TABLE Zaan_Apotheek .edition_category ADD CONSTRAINT fk5834369f7492966e FOREIGN KEY ("edition_id") REFERENCES Zaan_Apotheek .edition ("id");
ALTER TABLE Zaan_Apotheek .edition_subscriptiongroup ADD CONSTRAINT e_sg_e_eid_fkey FOREIGN KEY ("edition_id") REFERENCES Zaan_Apotheek .edition ("id");
ALTER TABLE Zaan_Apotheek .edition_subscriptiongroup ADD CONSTRAINT e_sg_sg_sgid_fkey FOREIGN KEY ("subscriptiongroups_id") REFERENCES Zaan_Apotheek .subscriptiongroup ("id");
ALTER TABLE Zaan_Apotheek .epublishermedia ADD CONSTRAINT epm_a_adid_fkey FOREIGN KEY ("attacheddocuments_id") REFERENCES Zaan_Apotheek .article ("id");
ALTER TABLE Zaan_Apotheek .epublishermedia ADD CONSTRAINT epm_a_auid_fkey FOREIGN KEY ("attachedurls_id") REFERENCES Zaan_Apotheek .article ("id");
ALTER TABLE Zaan_Apotheek .epublishermedia ADD CONSTRAINT epm_a_iid FOREIGN KEY ("images_id") REFERENCES Zaan_Apotheek .article ("id");
ALTER TABLE Zaan_Apotheek .epublishermedia ADD CONSTRAINT epm_a_tid FOREIGN KEY ("thumbnails_id") REFERENCES Zaan_Apotheek .article ("id");
ALTER TABLE Zaan_Apotheek .epublishermedia ADD CONSTRAINT t_aw_awid FOREIGN KEY ("articlewrapper_id") REFERENCES Zaan_Apotheek .articlewrapper ("id");
ALTER TABLE Zaan_Apotheek .epublishermedia ADD CONSTRAINT t_epm_oiid FOREIGN KEY ("originalimage_id") REFERENCES Zaan_Apotheek .epublishermedia ("id");
ALTER TABLE Zaan_Apotheek .epublisheruser_allowedbrightcovefolders ADD CONSTRAINT fk_user FOREIGN KEY ("user_id") REFERENCES Zaan_Apotheek .epublisheruser ("id");
ALTER TABLE Zaan_Apotheek .epublisheruser_profile ADD CONSTRAINT epu_profile_epu_epuid_fkey FOREIGN KEY ("epublisheruser_id") REFERENCES Zaan_Apotheek .epublisheruser ("id");
ALTER TABLE Zaan_Apotheek .epublisheruser_profile ADD CONSTRAINT epu_profile_profile_pid_fkey FOREIGN KEY ("profiles_id") REFERENCES Zaan_Apotheek .profile ("id");
ALTER TABLE Zaan_Apotheek .epublisheruser_publication ADD CONSTRAINT epu_pub_epu_epuid_fkey FOREIGN KEY ("epublisheruser_id") REFERENCES Zaan_Apotheek .epublisheruser ("id");
ALTER TABLE Zaan_Apotheek .epublisheruser_publication ADD CONSTRAINT epu_pub_p_apubid_fkey FOREIGN KEY ("availablepublications_id") REFERENCES Zaan_Apotheek .publication ("id");
ALTER TABLE Zaan_Apotheek .epublisheruser_templatenarrowcasting ADD CONSTRAINT epu_tn_epu_epuid_fkey FOREIGN KEY ("epublisheruser_id") REFERENCES Zaan_Apotheek .epublisheruser ("id");
ALTER TABLE Zaan_Apotheek .epublisheruser_templatenarrowcasting ADD CONSTRAINT epu_tn_tn_atnid_fkey FOREIGN KEY ("availabletemplates_id") REFERENCES Zaan_Apotheek .templatenarrowcasting ("id");
ALTER TABLE Zaan_Apotheek .playlist ADD CONSTRAINT fk73e0e5f285f7ba0d FOREIGN KEY ("publication_id") REFERENCES Zaan_Apotheek .publication ("id");
ALTER TABLE Zaan_Apotheek .playlist_broadcastwrapper ADD CONSTRAINT fk17392b5f9c54866d FOREIGN KEY ("playlist_id") REFERENCES Zaan_Apotheek .playlist ("id");
ALTER TABLE Zaan_Apotheek .playlist_broadcastwrapper ADD CONSTRAINT fk17392b5fc45239e FOREIGN KEY ("broadcastwrappers_id") REFERENCES Zaan_Apotheek .broadcastwrapper ("id");
ALTER TABLE Zaan_Apotheek .playtime_days ADD CONSTRAINT fk22dc2d954320182d FOREIGN KEY ("playtime_id") REFERENCES Zaan_Apotheek .playtime ("id");
ALTER TABLE Zaan_Apotheek .profile ADD CONSTRAINT fk50c72189693a9307 FOREIGN KEY ("searchobject_id") REFERENCES Zaan_Apotheek .searchobject ("id");
ALTER TABLE Zaan_Apotheek .profile ADD CONSTRAINT fk50c72189b4089f26 FOREIGN KEY ("includelatesteditionfrompublication_id") REFERENCES Zaan_Apotheek .publication ("id");
ALTER TABLE Zaan_Apotheek .profilesources ADD CONSTRAINT fkf2d891ef693a9307 FOREIGN KEY ("searchobject_id") REFERENCES Zaan_Apotheek .searchobject ("id");
ALTER TABLE Zaan_Apotheek .publication ADD CONSTRAINT fkbfbba22c4e45a56d FOREIGN KEY ("outputchannel_id") REFERENCES Zaan_Apotheek .outputchannel ("id");
ALTER TABLE Zaan_Apotheek .publication ADD CONSTRAINT fkbfbba22cc702f792 FOREIGN KEY ("template_id") REFERENCES Zaan_Apotheek .template ("id");
ALTER TABLE Zaan_Apotheek .publication_group_epublishermedia ADD CONSTRAINT fkf32b535af027fb4e FOREIGN KEY ("publication_group_id") REFERENCES Zaan_Apotheek .publication_group ("id");
ALTER TABLE Zaan_Apotheek .publication_group_publication ADD CONSTRAINT fk900b0919b1958e92 FOREIGN KEY ("publications_id") REFERENCES Zaan_Apotheek .publication ("id");
ALTER TABLE Zaan_Apotheek .publication_group_publication ADD CONSTRAINT fk900b0919f027fb4e FOREIGN KEY ("publication_group_id") REFERENCES Zaan_Apotheek .publication_group ("id");
ALTER TABLE Zaan_Apotheek .publication_imagesize ADD CONSTRAINT p_i_is_atsid_fkeyp FOREIGN KEY ("allowedthumbnailsizes_id") REFERENCES Zaan_Apotheek .imagesize ("id");
ALTER TABLE Zaan_Apotheek .publication_imagesize ADD CONSTRAINT p_i_p_pid_fkey FOREIGN KEY ("publication_id") REFERENCES Zaan_Apotheek .publication ("id");
ALTER TABLE Zaan_Apotheek .publication_subscriptiongroup ADD CONSTRAINT p_asg_asgid FOREIGN KEY ("availablesubscriptiongroups_id") REFERENCES Zaan_Apotheek .subscriptiongroup ("id");
ALTER TABLE Zaan_Apotheek .publication_subscriptiongroup ADD CONSTRAINT p_asg_pid FOREIGN KEY ("publication_id") REFERENCES Zaan_Apotheek .publication ("id");
ALTER TABLE Zaan_Apotheek .resourceinfo ADD CONSTRAINT fkf2dd39fcbf65fbbe FOREIGN KEY ("resources_id") REFERENCES Zaan_Apotheek .article ("id");
ALTER TABLE Zaan_Apotheek .rssfeed ADD CONSTRAINT t_rss_p_id FOREIGN KEY ("publication_id") REFERENCES Zaan_Apotheek .publication ("id");
ALTER TABLE Zaan_Apotheek .rssfeed_ipv4restrictor ADD CONSTRAINT t_rss_ir_id FOREIGN KEY ("allowedips_id") REFERENCES Zaan_Apotheek .ipv4restrictor ("id");
ALTER TABLE Zaan_Apotheek .rssfeed_ipv4restrictor ADD CONSTRAINT t_rss_rss_id FOREIGN KEY ("rssfeed_id") REFERENCES Zaan_Apotheek .rssfeed ("id");
ALTER TABLE Zaan_Apotheek .screen ADD CONSTRAINT fk_screen_screengroup FOREIGN KEY ("screengroup_id") REFERENCES Zaan_Apotheek .screengroup ("id");
ALTER TABLE Zaan_Apotheek .screen ADD CONSTRAINT fk_image_id FOREIGN KEY ("backgroundimage_id") REFERENCES Zaan_Apotheek .epublishermedia ("id");
ALTER TABLE Zaan_Apotheek .screengroup ADD CONSTRAINT fk_screengroup_publication FOREIGN KEY ("publication_id") REFERENCES Zaan_Apotheek .publication ("id");
ALTER TABLE Zaan_Apotheek .screengroup ADD CONSTRAINT fk_screengroup_screengroup FOREIGN KEY ("screengroup_id") REFERENCES Zaan_Apotheek .screengroup ("id");
ALTER TABLE Zaan_Apotheek .screengroup ADD CONSTRAINT fk_image_id FOREIGN KEY ("backgroundimage_id") REFERENCES Zaan_Apotheek .epublishermedia ("id");
ALTER TABLE Zaan_Apotheek .subscriber_subscriptiongroupfilter ADD CONSTRAINT fk27166aa3533d2c7b FOREIGN KEY ("subscribersfilters_id") REFERENCES Zaan_Apotheek .subscriptiongroupfilter ("id");
ALTER TABLE Zaan_Apotheek .subscriber_subscriptiongroupfilter ADD CONSTRAINT fk27166aa36683e2ec FOREIGN KEY ("subscriber_id") REFERENCES Zaan_Apotheek .subscriber ("id");
ALTER TABLE Zaan_Apotheek .subscriptiongroup_subscriber ADD CONSTRAINT sg_s_s_sid_fkey FOREIGN KEY ("subscribers_id") REFERENCES Zaan_Apotheek .subscriber ("id");
ALTER TABLE Zaan_Apotheek .subscriptiongroup_subscriber ADD CONSTRAINT sg_s_sg_sgid_fkey FOREIGN KEY ("subscriptiongroup_id") REFERENCES Zaan_Apotheek .subscriptiongroup ("id");
ALTER TABLE Zaan_Apotheek .subscriptiongroup_subscriptiongroupfilter ADD CONSTRAINT sg_s_sg_sgid_fkey FOREIGN KEY ("subscriptiongroup_id") REFERENCES Zaan_Apotheek .subscriptiongroup ("id");
ALTER TABLE Zaan_Apotheek .subscriptiongroup_subscriptiongroupfilter ADD CONSTRAINT sg_sf_s_sid_fkey FOREIGN KEY ("subscriptiongroupfilter_id") REFERENCES Zaan_Apotheek .subscriptiongroupfilter ("id");
ALTER TABLE Zaan_Apotheek .template ADD CONSTRAINT t_epm_biid FOREIGN KEY ("brandimage_id") REFERENCES Zaan_Apotheek .epublishermedia ("id");
ALTER TABLE Zaan_Apotheek .template ADD CONSTRAINT t_epm_fiid FOREIGN KEY ("footerimage_id") REFERENCES Zaan_Apotheek .epublishermedia ("id");
ALTER TABLE Zaan_Apotheek .template ADD CONSTRAINT t_epm_hiid_fkey FOREIGN KEY ("headerimage_id") REFERENCES Zaan_Apotheek .epublishermedia ("id");
ALTER TABLE Zaan_Apotheek .template ADD CONSTRAINT t_is_nltisid FOREIGN KEY ("newsletterthumbnailsimagesize_id") REFERENCES Zaan_Apotheek .imagesize ("id");
ALTER TABLE Zaan_Apotheek .template_category ADD CONSTRAINT t_c_c_cid_fkey FOREIGN KEY ("categories_id") REFERENCES Zaan_Apotheek .category ("id");
ALTER TABLE Zaan_Apotheek .template_category ADD CONSTRAINT t_c_t_tid_fkey FOREIGN KEY ("template_id") REFERENCES Zaan_Apotheek .template ("id");
ALTER TABLE Zaan_Apotheek .templatenarrowcasting ADD CONSTRAINT fkcb7d950ee67b351e FOREIGN KEY ("previewimage_id") REFERENCES Zaan_Apotheek .epublishermedia ("id");
ALTER TABLE Zaan_Apotheek .templatenarrowcasting_containerarea ADD CONSTRAINT fk81b60f1d107ca090 FOREIGN KEY ("containerareas_id") REFERENCES Zaan_Apotheek .containerarea ("id");
ALTER TABLE Zaan_Apotheek .templatenarrowcasting_containerarea ADD CONSTRAINT fk81b60f1d44123bc7 FOREIGN KEY ("templatenarrowcasting_id") REFERENCES Zaan_Apotheek .templatenarrowcasting ("id");
CREATE TABLE Zaan_Apotheek .externalRSSLink (id INTEGER NOT NULL,entityversion INTEGER NOT NULL, url CHARACTER VARYING(255));
ALTER TABLE Zaan_Apotheek .externalRSSLink
  ADD CONSTRAINT pk_externalLink
  PRIMARY KEY (id);
  
ALTER TABLE Zaan_Apotheek .epublisheruser ADD COLUMN showonlyfavoritearticles BOOLEAN DEFAULT false;  

CREATE TABLE Zaan_Apotheek .screen_externalrsslink (screen_id INTEGER NOT NULL, externalRSSLink_id INTEGER NOT NULL, PRIMARY KEY (screen_id, externalRSSLink_id), UNIQUE (screen_id,externalRSSLink_id));

ALTER TABLE Zaan_Apotheek .screen_externalrsslink
  ADD CONSTRAINT fk_screen_externalrsslink_screenId
  FOREIGN KEY (screen_id)
  REFERENCES Zaan_Apotheek .screen (id);

ALTER TABLE Zaan_Apotheek .screen_externalrsslink
  ADD CONSTRAINT fk_screen_externalrsslink__id
  FOREIGN KEY (externalRSSLink_id)
  REFERENCES Zaan_Apotheek .externalRSSLink (id);
  
CREATE TABLE Zaan_Apotheek .Permission(id INTEGER NOT NULL, entityversion INTEGER NOT NULL,name CHARACTER VARYING(255));

ALTER TABLE Zaan_Apotheek .Permission  ADD CONSTRAINT pk_Permission  PRIMARY KEY (id);
ALTER TABLE Zaan_Apotheek .permission ADD COLUMN nameid VARCHAR;

CREATE TABLE Zaan_Apotheek .Roles(id INTEGER NOT NULL, entityversion INTEGER NOT NULL,name CHARACTER VARYING(255));
ALTER TABLE Zaan_Apotheek .Roles  ADD CONSTRAINT pk_Roles  PRIMARY KEY (id);

CREATE TABLE Zaan_Apotheek .epublisheruser_roles(epublisheruser_id INTEGER NOT NULL, roles_id INTEGER NOT NULL, 
PRIMARY KEY (epublisheruser_id, roles_id ), UNIQUE (epublisheruser_id ,roles_id ));
ALTER TABLE Zaan_Apotheek .epublisheruser_roles ADD CONSTRAINT fk_epublisheruser_roles_epublisheruserid  FOREIGN KEY (epublisheruser_id)  REFERENCES  Zaan_Apotheek .epublisheruser(id);
ALTER TABLE Zaan_Apotheek .epublisheruser_roles  ADD CONSTRAINT fk_epublisheruser_roles_id  FOREIGN KEY (roles_id)  REFERENCES      Zaan_Apotheek .roles(id);

CREATE TABLE Zaan_Apotheek .roles_permission(roles_id  INTEGER NOT NULL, permission_id INTEGER NOT NULL,read boolean , write boolean ,
PRIMARY KEY (roles_id , permission_id ), UNIQUE (roles_id  , permission_id ));
ALTER TABLE Zaan_Apotheek .roles_permission  ADD CONSTRAINT fk_roles_permission_rolesid  FOREIGN KEY (roles_id)  REFERENCES  Zaan_Apotheek .roles(id);
ALTER TABLE Zaan_Apotheek .roles_permission  ADD CONSTRAINT fk_roles_permission_id  FOREIGN KEY (permission_id)  REFERENCES  Zaan_Apotheek .permission(id);

CREATE TABLE Zaan_Apotheek .roles_author(roles_id  INTEGER NOT NULL, author_id INTEGER NOT NULL,read boolean ,
PRIMARY KEY (roles_id , author_id ), UNIQUE (roles_id  , author_id ));
ALTER TABLE Zaan_Apotheek .roles_author  ADD CONSTRAINT fk_roles_author_rolesid  FOREIGN KEY (roles_id)  REFERENCES  Zaan_Apotheek .roles(id);
ALTER TABLE Zaan_Apotheek .roles_author  ADD CONSTRAINT fk_roles_author_id  FOREIGN KEY (author_id)  REFERENCES  Zaan_Apotheek .epublisheruser(id);


ALTER TABLE Zaan_Apotheek .screen ADD COLUMN iframeUrl VARCHAR;

CREATE TABLE Zaan_Apotheek .publication_disabledbroadcastwrapper (publication_id INTEGER NOT NULL, disabledbroadcastwrapper_id INTEGER NOT NULL, 
PRIMARY KEY (publication_id, disabledbroadcastwrapper_id), UNIQUE (publication_id,disabledbroadcastwrapper_id));


ALTER TABLE Zaan_Apotheek .publication_disabledbroadcastwrapper
  ADD CONSTRAINT fk_publication_disabledbroadcastwrapper_publicationId
  FOREIGN KEY (publication_id)
  REFERENCES Zaan_Apotheek .publication (id);

ALTER TABLE Zaan_Apotheek .publication_disabledbroadcastwrapper
  ADD CONSTRAINT fk_publication_disabledbroadcastwrapper_Id
  FOREIGN KEY (disabledbroadcastwrapper_id)
  REFERENCES Zaan_Apotheek .broadcastwrapper (id);
  
ALTER TABLE Zaan_Apotheek .contentblock RENAME COLUMN brightcoveid TO videoId;
ALTER TABLE Zaan_Apotheek .contentblock ADD COLUMN duration INTEGER;
ALTER TABLE Zaan_Apotheek .contentblock ADD COLUMN source VARCHAR;

CREATE TABLE Zaan_Apotheek .PlaylistWrapper (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, playlistid INTEGER NOT NULL, orderOfPlaylist INTEGER NOT NULL);

ALTER TABLE Zaan_Apotheek .PlaylistWrapper
  ADD CONSTRAINT pk_PlaylistWrapper
  PRIMARY KEY (id);

ALTER TABLE Zaan_Apotheek .PlaylistWrapper
  ADD CONSTRAINT fk_PlaylistWrapper_playlistId
  FOREIGN KEY (playlistid)
  REFERENCES Zaan_Apotheek .playlist (id);

CREATE TABLE Zaan_Apotheek .publication_PlaylistWrapper (publication_id INTEGER NOT NULL, playlistwrapper_id INTEGER NOT NULL,
 UNIQUE (publication_id,playlistwrapper_id));


ALTER TABLE Zaan_Apotheek .publication_PlaylistWrapper
  ADD CONSTRAINT fk_publication_PlaylistWrapper_publicationId
  FOREIGN KEY (publication_id)
  REFERENCES Zaan_Apotheek .publication (id);

ALTER TABLE Zaan_Apotheek .publication_PlaylistWrapper
  ADD CONSTRAINT fk_publication_PlaylistWrapper_Id
  FOREIGN KEY (playlistwrapper_id)
  REFERENCES Zaan_Apotheek .PlaylistWrapper (id);
  
Drop TABLE Zaan_Apotheek .publication_disabledbroadcastwrapper ;

CREATE TABLE Zaan_Apotheek .publication_disabledbroadcast (publication_id INTEGER NOT NULL, disabledbroadcast_id INTEGER NOT NULL, 
PRIMARY KEY (publication_id, disabledbroadcast_id), UNIQUE (publication_id,disabledbroadcast_id));


ALTER TABLE Zaan_Apotheek .publication_disabledbroadcast
  ADD CONSTRAINT fk_publication_disabledbroadcast_publicationId
  FOREIGN KEY (publication_id)
  REFERENCES Zaan_Apotheek .publication (id);

ALTER TABLE Zaan_Apotheek .publication_disabledbroadcast
  ADD CONSTRAINT fk_publication_disabledbroadcast_Id
  FOREIGN KEY (disabledbroadcast_id)
  REFERENCES Zaan_Apotheek .broadcast (id);
  
ALTER TABLE Zaan_Apotheek .contentblock alter column url TYPE TEXT; 

ALTER TABLE Zaan_Apotheek.article ADD createdByName CHARACTER VARYING(255);
ALTER TABLE Zaan_Apotheek.templateNarrowcasting ADD imageAspectRatio CHARACTER VARYING(255);


GRANT ALL ON TABLE Zaan_Apotheek .roles_permission TO epublisher;
GRANT SELECT, REFERENCES ON TABLE Zaan_Apotheek .roles_permission TO dashboard;
GRANT USAGE ON SCHEMA Zaan_Apotheek  TO GROUP dashboard;

GRANT ALL ON TABLE Zaan_Apotheek .roles_author TO epublisher;
GRANT SELECT, REFERENCES ON TABLE Zaan_Apotheek .roles_author TO dashboard;

GRANT ALL ON ALL TABLES IN SCHEMA Zaan_Apotheek  TO GROUP epublisher; 

INSERT INTO  Zaan_Apotheek .outputchannel (dtype, id, name, entityversion) VALUES ('OutputChannelNewsletterNS'	,2,	'newsletterNS',	1);
INSERT INTO  Zaan_Apotheek .outputchannel (dtype, id, name, entityversion) VALUES ('OutputChannelIntranetNS'	,1,	'intranetNS',	1);
INSERT INTO  Zaan_Apotheek .outputchannel (dtype, id, name, entityversion) VALUES ('OutputChannelPocketRailNS'	,3,	'railpocketNS',	1);

ALTER TABLE Zaan_Apotheek.templateNarrowcasting ADD templateimageWidth INTEGER; 
ALTER TABLE Zaan_Apotheek.templateNarrowcasting ADD templateimageheight INTEGER; 

CREATE TABLE Zaan_Apotheek.articletype(id INTEGER NOT NULL, name character varying(255), entityversion integer NOT NULL DEFAULT 1,
PRIMARY KEY (id), UNIQUE (id));

CREATE TABLE Zaan_Apotheek.article_articletype(article_id  INTEGER NOT NULL, articletype_id INTEGER NOT NULL,
PRIMARY KEY (article_id , articletype_id ), UNIQUE (article_id  , articletype_id ));

CREATE TABLE Zaan_Apotheek.epublisheruser_articletype(epublisheruser_id  INTEGER NOT NULL, availablearticletypes_id INTEGER NOT NULL,
PRIMARY KEY (epublisheruser_id , availablearticletypes_id ), UNIQUE (epublisheruser_id  , availablearticletypes_id ));

CREATE TABLE Zaan_Apotheek.schedulePublish (id INTEGER NOT NULL, entityversion INTEGER NOT NULL,edition_id INTEGER NOT NULL, 
scheduledDate  timestamp without time zone, scheduledhour INTEGER, scheduledminute INTEGER ,status varchar(255), cancelled boolean ,createdBy  varchar(255), updatedtimestamp  varchar(255));
CREATE TABLE Zaan_Apotheek.profileauthors (searchobject_id INT NOT NULL , epublisheruser_id INT NOT NULL);

-----------------------------------------------------------------------Add missing tables and columns to Zaan_Apotheek client-----------------------------------------------------------------------
 
ALTER TABLE Zaan_Apotheek.profileauthors ADD CONSTRAINT profileauthors_searchobjectid FOREIGN KEY ("searchobject_id") REFERENCES Zaan_Apotheek.searchobject ("id");
ALTER TABLE Zaan_Apotheek.profileauthors ADD CONSTRAINT profileauthors_epubuserid FOREIGN KEY ("epublisheruser_id") REFERENCES Zaan_Apotheek.epublisheruser ("id"); 
  
ALTER TABLE Zaan_Apotheek.article ADD COLUMN createdby INTEGER;
ALTER TABLE Zaan_Apotheek.article DROP COLUMN createdbyname;
ALTER TABLE Zaan_Apotheek.edition ADD column deleted boolean DEFAULT False ,ADD column  deletedDateTime  timestamp without time zone;

ALTER TABLE Zaan_Apotheek.articletype ADD COLUMN deleted boolean NOT NULL DEFAULT false;

ALTER TABLE Zaan_Apotheek.article DROP COLUMN type;

ALTER TABLE Zaan_Apotheek.screen ADD offlineEnabled BOOLEAN;
ALTER TABLE Zaan_Apotheek.broadcast ADD lastChangedDate  timestamp without time zone;

GRANT ALL ON TABLE Zaan_Apotheek.articletype TO epublisher;
GRANT SELECT, REFERENCES ON TABLE Zaan_Apotheek.articletype TO dashboard;

ALTER TABLE Zaan_Apotheek.article_articletype  ADD CONSTRAINT fk_article_articletype_articleid  FOREIGN KEY (article_id)  REFERENCES  Zaan_Apotheek.article(id);
ALTER TABLE Zaan_Apotheek.article_articletype  ADD CONSTRAINT fk_article_articletype_id  FOREIGN KEY (articletype_id)  REFERENCES  Zaan_Apotheek.articletype(id);

GRANT ALL ON TABLE Zaan_Apotheek.article_articletype TO epublisher;
GRANT SELECT, REFERENCES ON TABLE Zaan_Apotheek.article_articletype TO dashboard;


ALTER TABLE Zaan_Apotheek.epublisheruser_articletype  ADD CONSTRAINT fk_epublisheruser_articletype_epublisheruserid  FOREIGN KEY (epublisheruser_id)  REFERENCES  Zaan_Apotheek.epublisheruser(id);
ALTER TABLE Zaan_Apotheek.epublisheruser_articletype  ADD CONSTRAINT fk_epublisheruser_articletype_id  FOREIGN KEY (availablearticletypes_id)  REFERENCES  Zaan_Apotheek.articletype(id);

ALTER TABLE Zaan_Apotheek.schedulePublish ADD CONSTRAINT pk_schedulePublish PRIMARY KEY (id);
ALTER TABLE Zaan_Apotheek.schedulePublish ADD CONSTRAINT fk_schedulePublish FOREIGN KEY (edition_id) REFERENCES Zaan_Apotheek.edition (id); 

GRANT ALL ON TABLE Zaan_Apotheek.epublisheruser_articletype TO epublisher;
GRANT SELECT, REFERENCES ON TABLE Zaan_Apotheek.epublisheruser_articletype TO dashboard;

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

GRANT  ALL ON ALL SEQUENCES IN SCHEMA Zaan_Apotheek  TO  GROUP epublisher;
GRANT ALL ON TABLE  Zaan_Apotheek .Permission TO epublisher;
GRANT SELECT, REFERENCES ON TABLE  Zaan_Apotheek .Permission TO dashboard;
GRANT ALL ON TABLE Zaan_Apotheek .Roles TO epublisher;
GRANT SELECT, REFERENCES ON TABLE Zaan_Apotheek .Roles TO dashboard;
GRANT ALL ON TABLE Zaan_Apotheek .epublisheruser_roles TO epublisher;
GRANT SELECT, REFERENCES ON TABLE Zaan_Apotheek .epublisheruser_roles TO dashboard;
grant select, references on Zaan_Apotheek .epublisheruser to dashboard;
grant select, references on Zaan_Apotheek .articlewrapper to dashboard;
grant select, references on Zaan_Apotheek .article to dashboard;
grant select, references on Zaan_Apotheek .resourceinfo to dashboard;
grant select, references on Zaan_Apotheek .category to dashboard;
grant select, references on Zaan_Apotheek .edition to dashboard;
grant select, references on Zaan_Apotheek .publication to dashboard;
grant select, references on Zaan_Apotheek .template to dashboard;
grant select, references on Zaan_Apotheek .epublishermedia to dashboard;
grant select, references on Zaan_Apotheek .edition_subscriptiongroup to dashboard;
grant select, references on Zaan_Apotheek .subscriptiongroup to dashboard;
grant select, references on Zaan_Apotheek .subscriptiongroup_subscriber to dashboard;
grant select, references,update on Zaan_Apotheek .epublisheruser to dashboard;
grant select, references on Zaan_Apotheek .epublisheruser_publication to dashboard;
grant select, references on Zaan_Apotheek .subscriber to dashboard;
grant select, references on Zaan_Apotheek. publication_subscriptiongroup to dashboard;


ALTER table Zaan_Apotheek. ePublishermedia ADD COLUMN uploaded boolean default FALSE;
ALTER TABLE Zaan_Apotheek. templatenarrowcasting ADD COLUMN defaultimage_id INTEGER;
ALTER TABLE Zaan_Apotheek. templatenarrowcasting ADD CONSTRAINT fkcb7d950ee67b987e FOREIGN KEY ("defaultimage_id") REFERENCES epublishermedia ("id");

SELECT setval('Zaan_Apotheek .epublisher_sequence', 100);

ALTER TABLE Zaan_Apotheek. epublisheruser_articletype
ADD COLUMN read boolean NOT NULL DEFAULT false,
ADD COLUMN write boolean NOT NULL DEFAULT false;

ALTER TABLE Zaan_Apotheek. epublisheruser_articletype
RENAME COLUMN availablearticletypes_id TO articletype_id;

ALTER TABLE Zaan_Apotheek. epublishermedia 
RENAME COLUMN uploaded TO mobileupload;
-----------------------------------------------------------------------DATA ------------------------------------------------------------

INSERT INTO Zaan_Apotheek .outputchannel (dtype, id, name, entityversion) VALUES ('OutputChannelNarrowcastingNS'	,4,	'narrowcastingNS',	1);
INSERT INTO  Zaan_Apotheek .outputchannel (dtype, id, name, entityversion) VALUES ('OutputChannelFacebook'	,5,	'facebook',	1);

INSERT
INTO
    Zaan_Apotheek .epublisheruser
    (
        id,
        articlepreviewmethod,
        email,
        employeenumber,
        firstname,
        lastname,
        middlename,
        password,
        phonenumber,
        preferedscreen,
        entityversion,
        passwordnonce
    )
    VALUES
    (
        5,
        'click',
        'hosting@prisma-it.com',
        null,
        'Prisma',
        'Support',
        '',
        'd90fd5eb440c3c4c80ee0242f17994067a8f815b',
        null,
        'publish',
                0,
        'c34eba71-57d3-4af5-8b68-0d449d34ea9a'
    );


INSERT INTO Zaan_Apotheek .roles (id, entityversion, name) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 0, 'ROLE_ADMIN');
INSERT INTO Zaan_Apotheek .roles (id, entityversion, name) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 0, 'ROLE_USER');

INSERT INTO Zaan_Apotheek .epublisheruser_roles (epublisheruser_id, roles_id) VALUES ((select max(id) from Zaan_Apotheek .epublisheruser where email ='hosting@prisma-it.com'), (SELECT max(id) FROM Zaan_Apotheek .Roles where name= 'ROLE_ADMIN'));
INSERT INTO Zaan_Apotheek .epublisheruser_roles (epublisheruser_id, roles_id) VALUES ((select max(id) from Zaan_Apotheek .epublisheruser where email ='hosting@prisma-it.com'), (SELECT max(id) FROM Zaan_Apotheek .Roles where name= 'ROLE_USER'));


INSERT INTO Zaan_Apotheek .permission (id, entityversion, name) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 0, 'Wijzig Uitzendingen');
INSERT INTO Zaan_Apotheek .permission (id, entityversion, name) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 0, 'Verwijder Uitzendingen');
INSERT INTO Zaan_Apotheek .permission (id, entityversion, name) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 0, 'Wijzig Playlist');
INSERT INTO Zaan_Apotheek .permission (id, entityversion, name) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 0, 'Verwijder Playlist');
INSERT INTO Zaan_Apotheek .permission (id, entityversion, name) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 0, 'Publiceer Playlist');
INSERT INTO Zaan_Apotheek .permission (id, entityversion, name, nameid) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 0, 'Alle Auteurs', 'all-authors');


--------------------------------------TEMPLATE --------------------------------------------
--full screen website
--full screen image
--full screen video
--portrait slide

------------------------ Portrait slide---------------------------

INSERT INTO Zaan_Apotheek .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('Zaan_Apotheek .epublisher_sequence'),	1,	'zaan-portrait-slide',	'Portrait slide'	,null, null, '16:27');


INSERT INTO Zaan_Apotheek .containerarea (id, entityversion, containerposition) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 1, 1);
INSERT INTO Zaan_Apotheek .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zaan_Apotheek .containerarea),'tekst');
INSERT INTO Zaan_Apotheek .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Zaan_Apotheek .templatenarrowcasting),	(Select max(id) from Zaan_Apotheek .containerarea));


INSERT INTO Zaan_Apotheek .containerarea (id, entityversion, containerposition) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 1, 2);
INSERT INTO Zaan_Apotheek .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zaan_Apotheek .containerarea),'afbeelding');
INSERT INTO Zaan_Apotheek .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Zaan_Apotheek .templatenarrowcasting),	(Select max(id) from Zaan_Apotheek .containerarea));


------------------------ Fullscreen Website Template ---------------------------
INSERT INTO Zaan_Apotheek.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('Zaan_Apotheek.epublisher_sequence'),	1,	'zaan-website-fullscreen',	'Website Fullscreen'	,null, null);

INSERT INTO Zaan_Apotheek.containerarea (id, entityversion, containerposition) VALUES (nextval('Zaan_Apotheek.epublisher_sequence'), 1, 1);
INSERT INTO Zaan_Apotheek.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zaan_Apotheek.containerarea),'website');
INSERT INTO Zaan_Apotheek.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Zaan_Apotheek.templatenarrowcasting),	(Select max(id) from Zaan_Apotheek.containerarea));


------------------------ Fullscreen Image Template ---------------------------
INSERT INTO Zaan_Apotheek.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('Zaan_Apotheek.epublisher_sequence'),	1,	'zaan-fullscreen-Image',	'Full Screen Image'	,null, null,'16:9');


INSERT INTO Zaan_Apotheek.containerarea (id, entityversion, containerposition) VALUES (nextval('Zaan_Apotheek.epublisher_sequence'), 1, 1);
INSERT INTO Zaan_Apotheek.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zaan_Apotheek.containerarea),'afbeelding');
INSERT INTO Zaan_Apotheek.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Zaan_Apotheek.templatenarrowcasting),	(Select max(id) from Zaan_Apotheek.containerarea));


----------------------- Video-fullscreen-----------------
INSERT INTO Zaan_Apotheek .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('Zaan_Apotheek .epublisher_sequence'),	1,	'zaan-video-fullscreen',	'Video-Fullscreen'	,null ,null);

INSERT INTO Zaan_Apotheek .containerarea (id, entityversion, containerposition) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 1, 1);
INSERT INTO Zaan_Apotheek .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zaan_Apotheek .containerarea),'video');
INSERT INTO Zaan_Apotheek .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Zaan_Apotheek .templatenarrowcasting),	(Select max(id) from Zaan_Apotheek .containerarea));
