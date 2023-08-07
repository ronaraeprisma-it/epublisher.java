CREATE SCHEMA IF NOT EXISTS Ziekenhuis_Martini  AUTHORIZATION epublisher;




-----------------------------------------------------------------------------------------------------------------------------------------------


CREATE SEQUENCE Ziekenhuis_Martini .epublisher_sequence INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1  NO CYCLE;

CREATE SEQUENCE Ziekenhuis_Martini .folderid_seq INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1  NO CYCLE;

CREATE TABLE Ziekenhuis_Martini .article (id INTEGER NOT NULL, articleid CHARACTER VARYING(255), availableoutsidepublication BOOLEAN NOT NULL, category CHARACTER VARYING(255), content TEXT, doctoken TEXT, documentsource CHARACTER VARYING(255), lastchangeddate TIMESTAMP(6) WITHOUT TIME ZONE, lastpublicationdate TIMESTAMP(6) WITHOUT TIME ZONE, numberoftimesviewed INTEGER NOT NULL, prologue TEXT, sourcedate TIMESTAMP(6) WITHOUT TIME ZONE, sourceurl CHARACTER VARYING(255), title CHARACTER VARYING(255), type CHARACTER VARYING(255), updateallowed BOOLEAN NOT NULL, version INTEGER NOT NULL, lasteditedby_id INTEGER, publication_id INTEGER, relevantarticles_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, displaystartdate TIMESTAMP(6) WITHOUT TIME ZONE, displayenddate TIMESTAMP(6) WITHOUT TIME ZONE, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .article_extensions (article_id INTEGER NOT NULL, articleextensions_id INTEGER NOT NULL, PRIMARY KEY (article_id, articleextensions_id), UNIQUE (articleextensions_id));

CREATE TABLE Ziekenhuis_Martini .article_tags (article_id INTEGER NOT NULL, tags CHARACTER VARYING(255));

CREATE TABLE Ziekenhuis_Martini .articleextension (dtype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, enddate DATE, header CHARACTER VARYING(255), startdate DATE, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .articleextension_functiongroup (articleextension_id INTEGER NOT NULL, functiongroups_id INTEGER NOT NULL, index INTEGER NOT NULL, CONSTRAINT pk_artext_funct PRIMARY KEY (articleextension_id, functiongroups_id));

CREATE TABLE Ziekenhuis_Martini .articlewrapper (dtype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, numberoftimesviewed INTEGER NOT NULL, orderofarticle INTEGER, categorya BOOLEAN, categoryb BOOLEAN, categoryc BOOLEAN, highlighted BOOLEAN, onhomepage BOOLEAN, rank INTEGER, article_id INTEGER, category_id INTEGER, thumbnailimage_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, alreadypublished BOOLEAN DEFAULT false, edition_id INTEGER, urgent BOOLEAN DEFAULT false NOT NULL, PRIMARY KEY (id), CONSTRAINT constraint_unique_articlewrapper_edition_id_article_id UNIQUE (edition_id, article_id));

CREATE TABLE Ziekenhuis_Martini .broadcast (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, description CHARACTER VARYING(255), duration INTEGER NOT NULL, name CHARACTER VARYING(255), template_id INTEGER, datecreated TIMESTAMP(6) WITHOUT TIME ZONE, displaystartdate TIMESTAMP(6) WITHOUT TIME ZONE, displayenddate TIMESTAMP(6) WITHOUT TIME ZONE, createdby INTEGER, CONSTRAINT pk_broadcast PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .broadcast_contentblock (broadcast_id INTEGER NOT NULL, contentblocks_id INTEGER NOT NULL, CONSTRAINT pk_broadcast_contentblock PRIMARY KEY (broadcast_id, contentblocks_id));

CREATE TABLE Ziekenhuis_Martini .broadcastplay (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, screenid INTEGER NOT NULL, publicationid INTEGER NOT NULL, playlistid INTEGER NOT NULL, broadcastid INTEGER NOT NULL, playtime TIMESTAMP(6) WITHOUT TIME ZONE, CONSTRAINT pk_broadcastplay PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .broadcastwrapper (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, alreadypublished BOOLEAN NOT NULL, orderofbroadcast INTEGER, broadcast_id INTEGER, CONSTRAINT pk_broadcastwrapper PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .categories (etemplate_id INTEGER NOT NULL, categories CHARACTER VARYING(255));

CREATE TABLE Ziekenhuis_Martini .category (id INTEGER NOT NULL, name CHARACTER VARYING(255), entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .containerarea (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, containerposition INTEGER NOT NULL, CONSTRAINT pk_containerarea PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .containerarea_allowedcontent (containerarea_id INTEGER NOT NULL, allowedcontent CHARACTER VARYING(255));

CREATE TABLE Ziekenhuis_Martini .contentblock (dtype CHARACTER VARYING(31) NOT NULL, image_id INTEGER, id INTEGER NOT NULL, entityversion INTEGER NOT NULL, containerid INTEGER NOT NULL, timezone CHARACTER VARYING(255), name CHARACTER VARYING(255), embedcode TEXT, brightcoveid CHARACTER VARYING(255), url CHARACTER VARYING(255), content TEXT, enabled BOOLEAN NOT NULL, title CHARACTER VARYING(30), CONSTRAINT pk_contentblock PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .edition (dtype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, editionnumber INTEGER NOT NULL, lastupdated TIMESTAMP(6) WITHOUT TIME ZONE, name CHARACTER VARYING(255) NOT NULL, publicationdate TIMESTAMP(6) WITHOUT TIME ZONE, totalnumberofbounces INTEGER, totalnumberofsentmails INTEGER, emailremark CHARACTER VARYING(255), publication_id INTEGER, subscriber_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, emailaddress CHARACTER VARYING(255), emailsubject CHARACTER VARYING(255), prologuetext TEXT, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .edition_category (edition_id INTEGER NOT NULL, categories_id INTEGER NOT NULL, index_col INTEGER NOT NULL, PRIMARY KEY (edition_id, index_col), UNIQUE (categories_id));

CREATE TABLE Ziekenhuis_Martini .edition_subscriptiongroup (edition_id INTEGER NOT NULL, subscriptiongroups_id INTEGER NOT NULL, PRIMARY KEY (edition_id, subscriptiongroups_id));

CREATE TABLE Ziekenhuis_Martini .epublishermedia (mediatype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, name CHARACTER VARYING(255), url CHARACTER VARYING(255), filename CHARACTER VARYING(255), filesizeinbytes BIGINT, liferayfileentryid BIGINT, alttext CHARACTER VARYING(255), caption CHARACTER VARYING(255), height INTEGER, width INTEGER, thumbnails_id INTEGER, images_id INTEGER, attachedurls_id INTEGER, attacheddocuments_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, articlewrapper_id INTEGER, originalimage_id INTEGER, sortorder INTEGER DEFAULT '-1'::integer NOT NULL, generated BOOLEAN, parentimageid INTEGER, username CHARACTER VARYING(255), uuid CHARACTER VARYING(255), folderid BIGINT DEFAULT nextval('folderid_seq'::regclass) NOT NULL, version CHARACTER VARYING(75), PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .epublisheruser (id INTEGER NOT NULL, articlepreviewmethod CHARACTER VARYING(255), email CHARACTER VARYING(255), employeenumber CHARACTER VARYING(255), firstname CHARACTER VARYING(255), lastname CHARACTER VARYING(255), middlename CHARACTER VARYING(255), password CHARACTER VARYING(255), phonenumber CHARACTER VARYING(255), preferedscreen CHARACTER VARYING(255), entityversion INTEGER DEFAULT 1 NOT NULL, passwordnonce CHARACTER VARYING(255), PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .epublisheruser_allowedbrightcovefolders (user_id INTEGER NOT NULL, allowedbrightcovefolders CHARACTER VARYING(255));

CREATE TABLE Ziekenhuis_Martini .epublisheruser_profile (epublisheruser_id INTEGER NOT NULL, profiles_id INTEGER NOT NULL, PRIMARY KEY (epublisheruser_id, profiles_id), UNIQUE (profiles_id));

CREATE TABLE Ziekenhuis_Martini .epublisheruser_publication (epublisheruser_id INTEGER NOT NULL, availablepublications_id INTEGER NOT NULL, PRIMARY KEY (epublisheruser_id, availablepublications_id));

CREATE TABLE Ziekenhuis_Martini .epublisheruser_templatenarrowcasting (epublisheruser_id INTEGER NOT NULL, availabletemplates_id INTEGER NOT NULL, PRIMARY KEY (epublisheruser_id, availabletemplates_id));

CREATE TABLE Ziekenhuis_Martini .functiongroup (id INTEGER NOT NULL, abbreviation CHARACTER VARYING(255), name CHARACTER VARYING(255), entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .imagesize (id INTEGER NOT NULL, height INTEGER NOT NULL, name CHARACTER VARYING(255), width INTEGER NOT NULL, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .ipv4restrictor (id INTEGER NOT NULL, ip1 BIGINT NOT NULL, ip2 BIGINT, CONSTRAINT pk_ipv4restrictor PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .outputchannel (dtype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, name CHARACTER VARYING(255) NOT NULL, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .playlist (id INTEGER NOT NULL, publication_id INTEGER, entityversion INTEGER NOT NULL, deleted BOOLEAN NOT NULL, description CHARACTER VARYING(255), lastupdated TIMESTAMP(6) WITHOUT TIME ZONE, name CHARACTER VARYING(255) NOT NULL, priority INTEGER NOT NULL, publicationdate TIMESTAMP(6) WITHOUT TIME ZONE, settingsdifferentthanpublished BOOLEAN NOT NULL, ancestorplaylistid INTEGER, CONSTRAINT pk_playlist PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .playlist_broadcastwrapper (playlist_id INTEGER NOT NULL, broadcastwrappers_id INTEGER NOT NULL);

CREATE TABLE Ziekenhuis_Martini .playtime (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, startdate TIMESTAMP(6) WITHOUT TIME ZONE, enddate TIMESTAMP(6) WITHOUT TIME ZONE, starthour INTEGER, startminute INTEGER, endhour INTEGER, endminute INTEGER, playlist_id INTEGER, CONSTRAINT pk_playtime PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .playtime_days (playtime_id INTEGER NOT NULL, days INTEGER);

CREATE TABLE Ziekenhuis_Martini .profile (id INTEGER NOT NULL, name CHARACTER VARYING(255), profileactive BOOLEAN NOT NULL, includelatesteditionfrompublication_id INTEGER, searchobject_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .profilesources (searchobject_id INTEGER NOT NULL, sources CHARACTER VARYING(255));

CREATE TABLE Ziekenhuis_Martini .publication (dtype CHARACTER VARYING(31) NOT NULL, id INTEGER NOT NULL, name CHARACTER VARYING(255) NOT NULL, numberoffeaturedarticles INTEGER, outputchannel_id INTEGER, template_id INTEGER, entityversion INTEGER DEFAULT 1 NOT NULL, deleted BOOLEAN NOT NULL, deleteddatetime TIMESTAMP(6) WITHOUT TIME ZONE, maxplaylistpriority INTEGER, targeturl CHARACTER VARYING, urgentarticlesenabled BOOLEAN, emailaddress CHARACTER VARYING(255), emailsubject CHARACTER VARYING(255), sharestrategy CHARACTER VARYING(25) DEFAULT 'SHARESTRATEGYPRIVATE'::character varying, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .publication_group (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, apiid INTEGER, name CHARACTER VARYING(255) NOT NULL, type CHARACTER VARYING(255), PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .publication_group_epublishermedia (publication_group_id INTEGER NOT NULL, images_id INTEGER NOT NULL, PRIMARY KEY (publication_group_id, images_id), UNIQUE (images_id));

CREATE TABLE Ziekenhuis_Martini .publication_group_publication (publication_group_id INTEGER NOT NULL, publications_id INTEGER NOT NULL, PRIMARY KEY (publication_group_id, publications_id));

CREATE TABLE Ziekenhuis_Martini .publication_imagesize (publication_id INTEGER NOT NULL, allowedthumbnailsizes_id INTEGER NOT NULL, thumbsize_index_col INTEGER NOT NULL, CONSTRAINT pk_publication_imagesize PRIMARY KEY (publication_id, thumbsize_index_col));

CREATE TABLE Ziekenhuis_Martini .publication_subscriptiongroup (publication_id INTEGER NOT NULL, availablesubscriptiongroups_id INTEGER NOT NULL, CONSTRAINT p_asg_pid_asgid_pkey PRIMARY KEY (publication_id, availablesubscriptiongroups_id));

CREATE TABLE Ziekenhuis_Martini .resourceinfo (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, mimetype CHARACTER VARYING(255), name CHARACTER VARYING(255), reference CHARACTER VARYING(255), resources_id INTEGER, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .rssfeed (id INTEGER NOT NULL, description CHARACTER VARYING(2000) NOT NULL, feedname CHARACTER VARYING(50) NOT NULL, maxentries INTEGER NOT NULL, title CHARACTER VARYING(100) NOT NULL, ttl INTEGER NOT NULL, publication_id INTEGER NOT NULL, CONSTRAINT pk_rssfeed PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .rssfeed_ipv4restrictor (rssfeed_id INTEGER NOT NULL, allowedips_id INTEGER NOT NULL);

CREATE TABLE Ziekenhuis_Martini .schemaversiontable (schemaversioncolumn INTEGER NOT NULL);

CREATE TABLE Ziekenhuis_Martini .screen (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, description CHARACTER VARYING(255), displayid CHARACTER VARYING(255), name CHARACTER VARYING(255), resolutionheight INTEGER NOT NULL, resolutionwidth INTEGER NOT NULL, touchenabled BOOLEAN, screengroup_id INTEGER, datetimelastrequest TIMESTAMP(6) WITHOUT TIME ZONE, minvideoresolutionwidth INTEGER, minvideoresolutionheight INTEGER, displayname CHARACTER VARYING(255), locationcode CHARACTER VARYING(255), location CHARACTER VARYING(255), backgroundimage_id INTEGER, CONSTRAINT pk_screen PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .screengroup (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, description CHARACTER VARYING(255), name CHARACTER VARYING(255), screengroup_id INTEGER, publication_id INTEGER, backgroundimage_id INTEGER, CONSTRAINT pk_screengroup PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .searchobject (id INTEGER NOT NULL, fromdate TIMESTAMP(6) WITHOUT TIME ZONE, numberofdaysfrompresent INTEGER NOT NULL, searchstring CHARACTER VARYING(255), todate TIMESTAMP(6) WITHOUT TIME ZONE, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .subscriber (id INTEGER NOT NULL, emailaddress CHARACTER VARYING(255) NOT NULL, firstname CHARACTER VARYING(255), lastname CHARACTER VARYING(255), middlename CHARACTER VARYING(255), entityversion INTEGER DEFAULT 1 NOT NULL, dtype CHARACTER VARYING(31) DEFAULT 'Subscriber'::character varying NOT NULL, origin CHARACTER VARYING(255) DEFAULT 'INTERNAL'::character varying NOT NULL, externidentifier CHARACTER VARYING(255), PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .subscriber_subscriptiongroupfilter (subscriber_id INTEGER NOT NULL, subscribersfilters_id INTEGER NOT NULL, PRIMARY KEY (subscriber_id, subscribersfilters_id));

CREATE TABLE Ziekenhuis_Martini .subscriptiongroup (id INTEGER NOT NULL, name CHARACTER VARYING(255), testgroup BOOLEAN NOT NULL, entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .subscriptiongroup_subscriber (subscriptiongroup_id INTEGER NOT NULL, subscribers_id INTEGER NOT NULL, subscriberstate CHARACTER VARYING(255) DEFAULT 'ACTIVE'::character varying NOT NULL, PRIMARY KEY (subscriptiongroup_id, subscribers_id), UNIQUE (subscribers_id));

CREATE TABLE Ziekenhuis_Martini .subscriptiongroup_subscriptiongroupfilter (subscriptiongroup_id INTEGER NOT NULL, subscriptiongroupfilter_id INTEGER NOT NULL, PRIMARY KEY (subscriptiongroup_id, subscriptiongroupfilter_id));

CREATE TABLE Ziekenhuis_Martini .subscriptiongroupfilter (id INTEGER NOT NULL, filtercode CHARACTER VARYING(255) NOT NULL, filtertype CHARACTER VARYING(255) NOT NULL, filtervalue CHARACTER VARYING(255), entityversion INTEGER DEFAULT 1 NOT NULL, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .template (id INTEGER NOT NULL, articlerepresentation CHARACTER VARYING(255), colophontext TEXT, csstemplatetouse CHARACTER VARYING(255), footertext TEXT, freemarkertemplatetouse CHARACTER VARYING(255), includecolophon BOOLEAN NOT NULL, includedateinheader BOOLEAN NOT NULL, includeeditionnumber BOOLEAN NOT NULL, includefooter BOOLEAN NOT NULL, includeprologue BOOLEAN NOT NULL, includerecipientname BOOLEAN NOT NULL, includetableofcontent BOOLEAN NOT NULL, name CHARACTER VARYING(255), prologuetext TEXT, reactionemailaddress CHARACTER VARYING(255), reactionpossible BOOLEAN NOT NULL, title CHARACTER VARYING(255), brandimage_id INTEGER, footerimage_id INTEGER, headerimage_id INTEGER, includecancelsubscription BOOLEAN NOT NULL, includebrowserview BOOLEAN NOT NULL, printingallowed BOOLEAN DEFAULT false NOT NULL, entityversion INTEGER DEFAULT 1 NOT NULL, includeimagesinnewsletter BOOLEAN NOT NULL, newsletterthumbnailsimagesize_id INTEGER, deleted BOOLEAN DEFAULT false NOT NULL, showsourceslist BOOLEAN DEFAULT true NOT NULL, PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .template_category (template_id INTEGER NOT NULL, categories_id INTEGER NOT NULL, index_col INTEGER NOT NULL, CONSTRAINT pk_template_category PRIMARY KEY (index_col, template_id));

CREATE TABLE Ziekenhuis_Martini .templatenarrowcasting (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, htmltemplate CHARACTER VARYING(255), name CHARACTER VARYING(255), previewimage_id INTEGER, CONSTRAINT pk_templatenarrowcasting PRIMARY KEY (id));

CREATE TABLE Ziekenhuis_Martini .templatenarrowcasting_containerarea (templatenarrowcasting_id INTEGER NOT NULL, containerareas_id INTEGER NOT NULL, CONSTRAINT pk_templatenarrowcasting_containerarea PRIMARY KEY (containerareas_id, templatenarrowcasting_id));
ALTER TABLE Ziekenhuis_Martini .article ADD CONSTRAINT a_a_raid_fkey FOREIGN KEY ("relevantarticles_id") REFERENCES Ziekenhuis_Martini .article ("id");
ALTER TABLE Ziekenhuis_Martini .article ADD CONSTRAINT a_epu_lebid_fkey FOREIGN KEY ("lasteditedby_id") REFERENCES Ziekenhuis_Martini .epublisheruser ("id");
ALTER TABLE Ziekenhuis_Martini .article ADD CONSTRAINT a_p_pid_fkey FOREIGN KEY ("publication_id") REFERENCES Ziekenhuis_Martini .publication ("id");
ALTER TABLE Ziekenhuis_Martini .article_extensions ADD CONSTRAINT fk32bef73d424b17e6 FOREIGN KEY ("articleextensions_id") REFERENCES Ziekenhuis_Martini .articleextension ("id");
ALTER TABLE Ziekenhuis_Martini .article_extensions ADD CONSTRAINT fk32bef73de0162f4d FOREIGN KEY ("article_id") REFERENCES Ziekenhuis_Martini .article ("id");
ALTER TABLE Ziekenhuis_Martini .article_tags ADD CONSTRAINT a_t_a_aid_fkey FOREIGN KEY ("article_id") REFERENCES Ziekenhuis_Martini .article ("id");
ALTER TABLE Ziekenhuis_Martini .articleextension_functiongroup ADD CONSTRAINT ae_fg_ae_aeid_fkey FOREIGN KEY ("articleextension_id") REFERENCES Ziekenhuis_Martini .articleextension ("id");
ALTER TABLE Ziekenhuis_Martini .articleextension_functiongroup ADD CONSTRAINT ae_fg_fg_fgid_fkey FOREIGN KEY ("functiongroups_id") REFERENCES Ziekenhuis_Martini .functiongroup ("id");
ALTER TABLE Ziekenhuis_Martini .articlewrapper ADD CONSTRAINT aw_a_aid_fkey FOREIGN KEY ("article_id") REFERENCES Ziekenhuis_Martini .article ("id");
ALTER TABLE Ziekenhuis_Martini .articlewrapper ADD CONSTRAINT aw_c_cid_fkey FOREIGN KEY ("category_id") REFERENCES Ziekenhuis_Martini .category ("id");
ALTER TABLE Ziekenhuis_Martini .articlewrapper ADD CONSTRAINT aw_e_eid_fkey FOREIGN KEY ("edition_id") REFERENCES Ziekenhuis_Martini .edition ("id");
ALTER TABLE Ziekenhuis_Martini .articlewrapper ADD CONSTRAINT aw_epm_tiid_fkey FOREIGN KEY ("thumbnailimage_id") REFERENCES Ziekenhuis_Martini .epublishermedia ("id");
ALTER TABLE Ziekenhuis_Martini .broadcast ADD CONSTRAINT fk16f408a18ff9251b FOREIGN KEY ("template_id") REFERENCES Ziekenhuis_Martini .templatenarrowcasting ("id");
ALTER TABLE Ziekenhuis_Martini .broadcast_contentblock ADD CONSTRAINT fk8cb806925b69c222 FOREIGN KEY ("contentblocks_id") REFERENCES Ziekenhuis_Martini .contentblock ("id");
ALTER TABLE Ziekenhuis_Martini .broadcast_contentblock ADD CONSTRAINT fk8cb80692e908ae87 FOREIGN KEY ("broadcast_id") REFERENCES Ziekenhuis_Martini .broadcast ("id");
ALTER TABLE Ziekenhuis_Martini .broadcastwrapper ADD CONSTRAINT fkee24cb2e908ae87 FOREIGN KEY ("broadcast_id") REFERENCES Ziekenhuis_Martini .broadcast ("id");
ALTER TABLE Ziekenhuis_Martini .containerarea_allowedcontent ADD CONSTRAINT fkd53058270777507 FOREIGN KEY ("containerarea_id") REFERENCES Ziekenhuis_Martini .containerarea ("id");
ALTER TABLE Ziekenhuis_Martini .contentblock ADD CONSTRAINT fk39c3d7b4fd8e9956 FOREIGN KEY ("image_id") REFERENCES Ziekenhuis_Martini .epublishermedia ("id");
ALTER TABLE Ziekenhuis_Martini .edition ADD CONSTRAINT e_p_pid_fkey FOREIGN KEY ("publication_id") REFERENCES Ziekenhuis_Martini .publication ("id");
ALTER TABLE Ziekenhuis_Martini .edition ADD CONSTRAINT e_s_sid_fkey FOREIGN KEY ("subscriber_id") REFERENCES Ziekenhuis_Martini .subscriber ("id");
ALTER TABLE Ziekenhuis_Martini .edition_category ADD CONSTRAINT e_c_c_cid_fkey FOREIGN KEY ("categories_id") REFERENCES Ziekenhuis_Martini .category ("id");
ALTER TABLE Ziekenhuis_Martini .edition_category ADD CONSTRAINT fk5834369f7492966e FOREIGN KEY ("edition_id") REFERENCES Ziekenhuis_Martini .edition ("id");
ALTER TABLE Ziekenhuis_Martini .edition_subscriptiongroup ADD CONSTRAINT e_sg_e_eid_fkey FOREIGN KEY ("edition_id") REFERENCES Ziekenhuis_Martini .edition ("id");
ALTER TABLE Ziekenhuis_Martini .edition_subscriptiongroup ADD CONSTRAINT e_sg_sg_sgid_fkey FOREIGN KEY ("subscriptiongroups_id") REFERENCES Ziekenhuis_Martini .subscriptiongroup ("id");
ALTER TABLE Ziekenhuis_Martini .epublishermedia ADD CONSTRAINT epm_a_adid_fkey FOREIGN KEY ("attacheddocuments_id") REFERENCES Ziekenhuis_Martini .article ("id");
ALTER TABLE Ziekenhuis_Martini .epublishermedia ADD CONSTRAINT epm_a_auid_fkey FOREIGN KEY ("attachedurls_id") REFERENCES Ziekenhuis_Martini .article ("id");
ALTER TABLE Ziekenhuis_Martini .epublishermedia ADD CONSTRAINT epm_a_iid FOREIGN KEY ("images_id") REFERENCES Ziekenhuis_Martini .article ("id");
ALTER TABLE Ziekenhuis_Martini .epublishermedia ADD CONSTRAINT epm_a_tid FOREIGN KEY ("thumbnails_id") REFERENCES Ziekenhuis_Martini .article ("id");
ALTER TABLE Ziekenhuis_Martini .epublishermedia ADD CONSTRAINT t_aw_awid FOREIGN KEY ("articlewrapper_id") REFERENCES Ziekenhuis_Martini .articlewrapper ("id");
ALTER TABLE Ziekenhuis_Martini .epublishermedia ADD CONSTRAINT t_epm_oiid FOREIGN KEY ("originalimage_id") REFERENCES Ziekenhuis_Martini .epublishermedia ("id");
ALTER TABLE Ziekenhuis_Martini .epublisheruser_allowedbrightcovefolders ADD CONSTRAINT fk_user FOREIGN KEY ("user_id") REFERENCES Ziekenhuis_Martini .epublisheruser ("id");
ALTER TABLE Ziekenhuis_Martini .epublisheruser_profile ADD CONSTRAINT epu_profile_epu_epuid_fkey FOREIGN KEY ("epublisheruser_id") REFERENCES Ziekenhuis_Martini .epublisheruser ("id");
ALTER TABLE Ziekenhuis_Martini .epublisheruser_profile ADD CONSTRAINT epu_profile_profile_pid_fkey FOREIGN KEY ("profiles_id") REFERENCES Ziekenhuis_Martini .profile ("id");
ALTER TABLE Ziekenhuis_Martini .epublisheruser_publication ADD CONSTRAINT epu_pub_epu_epuid_fkey FOREIGN KEY ("epublisheruser_id") REFERENCES Ziekenhuis_Martini .epublisheruser ("id");
ALTER TABLE Ziekenhuis_Martini .epublisheruser_publication ADD CONSTRAINT epu_pub_p_apubid_fkey FOREIGN KEY ("availablepublications_id") REFERENCES Ziekenhuis_Martini .publication ("id");
ALTER TABLE Ziekenhuis_Martini .epublisheruser_templatenarrowcasting ADD CONSTRAINT epu_tn_epu_epuid_fkey FOREIGN KEY ("epublisheruser_id") REFERENCES Ziekenhuis_Martini .epublisheruser ("id");
ALTER TABLE Ziekenhuis_Martini .epublisheruser_templatenarrowcasting ADD CONSTRAINT epu_tn_tn_atnid_fkey FOREIGN KEY ("availabletemplates_id") REFERENCES Ziekenhuis_Martini .templatenarrowcasting ("id");
ALTER TABLE Ziekenhuis_Martini .playlist ADD CONSTRAINT fk73e0e5f285f7ba0d FOREIGN KEY ("publication_id") REFERENCES Ziekenhuis_Martini .publication ("id");
ALTER TABLE Ziekenhuis_Martini .playlist_broadcastwrapper ADD CONSTRAINT fk17392b5f9c54866d FOREIGN KEY ("playlist_id") REFERENCES Ziekenhuis_Martini .playlist ("id");
ALTER TABLE Ziekenhuis_Martini .playlist_broadcastwrapper ADD CONSTRAINT fk17392b5fc45239e FOREIGN KEY ("broadcastwrappers_id") REFERENCES Ziekenhuis_Martini .broadcastwrapper ("id");
ALTER TABLE Ziekenhuis_Martini .playtime_days ADD CONSTRAINT fk22dc2d954320182d FOREIGN KEY ("playtime_id") REFERENCES Ziekenhuis_Martini .playtime ("id");
ALTER TABLE Ziekenhuis_Martini .profile ADD CONSTRAINT fk50c72189693a9307 FOREIGN KEY ("searchobject_id") REFERENCES Ziekenhuis_Martini .searchobject ("id");
ALTER TABLE Ziekenhuis_Martini .profile ADD CONSTRAINT fk50c72189b4089f26 FOREIGN KEY ("includelatesteditionfrompublication_id") REFERENCES Ziekenhuis_Martini .publication ("id");
ALTER TABLE Ziekenhuis_Martini .profilesources ADD CONSTRAINT fkf2d891ef693a9307 FOREIGN KEY ("searchobject_id") REFERENCES Ziekenhuis_Martini .searchobject ("id");
ALTER TABLE Ziekenhuis_Martini .publication ADD CONSTRAINT fkbfbba22c4e45a56d FOREIGN KEY ("outputchannel_id") REFERENCES Ziekenhuis_Martini .outputchannel ("id");
ALTER TABLE Ziekenhuis_Martini .publication ADD CONSTRAINT fkbfbba22cc702f792 FOREIGN KEY ("template_id") REFERENCES Ziekenhuis_Martini .template ("id");
ALTER TABLE Ziekenhuis_Martini .publication_group_epublishermedia ADD CONSTRAINT fkf32b535af027fb4e FOREIGN KEY ("publication_group_id") REFERENCES Ziekenhuis_Martini .publication_group ("id");
ALTER TABLE Ziekenhuis_Martini .publication_group_publication ADD CONSTRAINT fk900b0919b1958e92 FOREIGN KEY ("publications_id") REFERENCES Ziekenhuis_Martini .publication ("id");
ALTER TABLE Ziekenhuis_Martini .publication_group_publication ADD CONSTRAINT fk900b0919f027fb4e FOREIGN KEY ("publication_group_id") REFERENCES Ziekenhuis_Martini .publication_group ("id");
ALTER TABLE Ziekenhuis_Martini .publication_imagesize ADD CONSTRAINT p_i_is_atsid_fkeyp FOREIGN KEY ("allowedthumbnailsizes_id") REFERENCES Ziekenhuis_Martini .imagesize ("id");
ALTER TABLE Ziekenhuis_Martini .publication_imagesize ADD CONSTRAINT p_i_p_pid_fkey FOREIGN KEY ("publication_id") REFERENCES Ziekenhuis_Martini .publication ("id");
ALTER TABLE Ziekenhuis_Martini .publication_subscriptiongroup ADD CONSTRAINT p_asg_asgid FOREIGN KEY ("availablesubscriptiongroups_id") REFERENCES Ziekenhuis_Martini .subscriptiongroup ("id");
ALTER TABLE Ziekenhuis_Martini .publication_subscriptiongroup ADD CONSTRAINT p_asg_pid FOREIGN KEY ("publication_id") REFERENCES Ziekenhuis_Martini .publication ("id");
ALTER TABLE Ziekenhuis_Martini .resourceinfo ADD CONSTRAINT fkf2dd39fcbf65fbbe FOREIGN KEY ("resources_id") REFERENCES Ziekenhuis_Martini .article ("id");
ALTER TABLE Ziekenhuis_Martini .rssfeed ADD CONSTRAINT t_rss_p_id FOREIGN KEY ("publication_id") REFERENCES Ziekenhuis_Martini .publication ("id");
ALTER TABLE Ziekenhuis_Martini .rssfeed_ipv4restrictor ADD CONSTRAINT t_rss_ir_id FOREIGN KEY ("allowedips_id") REFERENCES Ziekenhuis_Martini .ipv4restrictor ("id");
ALTER TABLE Ziekenhuis_Martini .rssfeed_ipv4restrictor ADD CONSTRAINT t_rss_rss_id FOREIGN KEY ("rssfeed_id") REFERENCES Ziekenhuis_Martini .rssfeed ("id");
ALTER TABLE Ziekenhuis_Martini .screen ADD CONSTRAINT fk_screen_screengroup FOREIGN KEY ("screengroup_id") REFERENCES Ziekenhuis_Martini .screengroup ("id");
ALTER TABLE Ziekenhuis_Martini .screen ADD CONSTRAINT fk_image_id FOREIGN KEY ("backgroundimage_id") REFERENCES Ziekenhuis_Martini .epublishermedia ("id");
ALTER TABLE Ziekenhuis_Martini .screengroup ADD CONSTRAINT fk_screengroup_publication FOREIGN KEY ("publication_id") REFERENCES Ziekenhuis_Martini .publication ("id");
ALTER TABLE Ziekenhuis_Martini .screengroup ADD CONSTRAINT fk_screengroup_screengroup FOREIGN KEY ("screengroup_id") REFERENCES Ziekenhuis_Martini .screengroup ("id");
ALTER TABLE Ziekenhuis_Martini .screengroup ADD CONSTRAINT fk_image_id FOREIGN KEY ("backgroundimage_id") REFERENCES Ziekenhuis_Martini .epublishermedia ("id");
ALTER TABLE Ziekenhuis_Martini .subscriber_subscriptiongroupfilter ADD CONSTRAINT fk27166aa3533d2c7b FOREIGN KEY ("subscribersfilters_id") REFERENCES Ziekenhuis_Martini .subscriptiongroupfilter ("id");
ALTER TABLE Ziekenhuis_Martini .subscriber_subscriptiongroupfilter ADD CONSTRAINT fk27166aa36683e2ec FOREIGN KEY ("subscriber_id") REFERENCES Ziekenhuis_Martini .subscriber ("id");
ALTER TABLE Ziekenhuis_Martini .subscriptiongroup_subscriber ADD CONSTRAINT sg_s_s_sid_fkey FOREIGN KEY ("subscribers_id") REFERENCES Ziekenhuis_Martini .subscriber ("id");
ALTER TABLE Ziekenhuis_Martini .subscriptiongroup_subscriber ADD CONSTRAINT sg_s_sg_sgid_fkey FOREIGN KEY ("subscriptiongroup_id") REFERENCES Ziekenhuis_Martini .subscriptiongroup ("id");
ALTER TABLE Ziekenhuis_Martini .subscriptiongroup_subscriptiongroupfilter ADD CONSTRAINT sg_s_sg_sgid_fkey FOREIGN KEY ("subscriptiongroup_id") REFERENCES Ziekenhuis_Martini .subscriptiongroup ("id");
ALTER TABLE Ziekenhuis_Martini .subscriptiongroup_subscriptiongroupfilter ADD CONSTRAINT sg_sf_s_sid_fkey FOREIGN KEY ("subscriptiongroupfilter_id") REFERENCES Ziekenhuis_Martini .subscriptiongroupfilter ("id");
ALTER TABLE Ziekenhuis_Martini .template ADD CONSTRAINT t_epm_biid FOREIGN KEY ("brandimage_id") REFERENCES Ziekenhuis_Martini .epublishermedia ("id");
ALTER TABLE Ziekenhuis_Martini .template ADD CONSTRAINT t_epm_fiid FOREIGN KEY ("footerimage_id") REFERENCES Ziekenhuis_Martini .epublishermedia ("id");
ALTER TABLE Ziekenhuis_Martini .template ADD CONSTRAINT t_epm_hiid_fkey FOREIGN KEY ("headerimage_id") REFERENCES Ziekenhuis_Martini .epublishermedia ("id");
ALTER TABLE Ziekenhuis_Martini .template ADD CONSTRAINT t_is_nltisid FOREIGN KEY ("newsletterthumbnailsimagesize_id") REFERENCES Ziekenhuis_Martini .imagesize ("id");
ALTER TABLE Ziekenhuis_Martini .template_category ADD CONSTRAINT t_c_c_cid_fkey FOREIGN KEY ("categories_id") REFERENCES Ziekenhuis_Martini .category ("id");
ALTER TABLE Ziekenhuis_Martini .template_category ADD CONSTRAINT t_c_t_tid_fkey FOREIGN KEY ("template_id") REFERENCES Ziekenhuis_Martini .template ("id");
ALTER TABLE Ziekenhuis_Martini .templatenarrowcasting ADD CONSTRAINT fkcb7d950ee67b351e FOREIGN KEY ("previewimage_id") REFERENCES Ziekenhuis_Martini .epublishermedia ("id");
ALTER TABLE Ziekenhuis_Martini .templatenarrowcasting_containerarea ADD CONSTRAINT fk81b60f1d107ca090 FOREIGN KEY ("containerareas_id") REFERENCES Ziekenhuis_Martini .containerarea ("id");
ALTER TABLE Ziekenhuis_Martini .templatenarrowcasting_containerarea ADD CONSTRAINT fk81b60f1d44123bc7 FOREIGN KEY ("templatenarrowcasting_id") REFERENCES Ziekenhuis_Martini .templatenarrowcasting ("id");
CREATE TABLE Ziekenhuis_Martini .externalRSSLink (id INTEGER NOT NULL,entityversion INTEGER NOT NULL, url CHARACTER VARYING(255));
ALTER TABLE Ziekenhuis_Martini .externalRSSLink
  ADD CONSTRAINT pk_externalLink
  PRIMARY KEY (id);

CREATE TABLE Ziekenhuis_Martini .screen_externalrsslink (screen_id INTEGER NOT NULL, externalRSSLink_id INTEGER NOT NULL, PRIMARY KEY (screen_id, externalRSSLink_id), UNIQUE (screen_id,externalRSSLink_id));

ALTER TABLE Ziekenhuis_Martini .screen_externalrsslink
  ADD CONSTRAINT fk_screen_externalrsslink_screenId
  FOREIGN KEY (screen_id)
  REFERENCES Ziekenhuis_Martini .screen (id);

ALTER TABLE Ziekenhuis_Martini .screen_externalrsslink
  ADD CONSTRAINT fk_screen_externalrsslink__id
  FOREIGN KEY (externalRSSLink_id)
  REFERENCES Ziekenhuis_Martini .externalRSSLink (id);
  
CREATE TABLE Ziekenhuis_Martini .Permission(id INTEGER NOT NULL, entityversion INTEGER NOT NULL,name CHARACTER VARYING(255));

ALTER TABLE Ziekenhuis_Martini .Permission  ADD CONSTRAINT pk_Permission  PRIMARY KEY (id);

CREATE TABLE Ziekenhuis_Martini .Roles(id INTEGER NOT NULL, entityversion INTEGER NOT NULL,name CHARACTER VARYING(255));
ALTER TABLE Ziekenhuis_Martini .Roles  ADD CONSTRAINT pk_Roles  PRIMARY KEY (id);

CREATE TABLE Ziekenhuis_Martini .epublisheruser_roles(epublisheruser_id INTEGER NOT NULL, roles_id INTEGER NOT NULL, 
PRIMARY KEY (epublisheruser_id, roles_id ), UNIQUE (epublisheruser_id ,roles_id ));
ALTER TABLE Ziekenhuis_Martini .epublisheruser_roles ADD CONSTRAINT fk_epublisheruser_roles_epublisheruserid  FOREIGN KEY (epublisheruser_id)  REFERENCES  Ziekenhuis_Martini .epublisheruser(id);
ALTER TABLE Ziekenhuis_Martini .epublisheruser_roles  ADD CONSTRAINT fk_epublisheruser_roles_id  FOREIGN KEY (roles_id)  REFERENCES      Ziekenhuis_Martini .roles(id);

CREATE TABLE Ziekenhuis_Martini .roles_permission(roles_id  INTEGER NOT NULL, permission_id INTEGER NOT NULL,read boolean , write boolean ,
PRIMARY KEY (roles_id , permission_id ), UNIQUE (roles_id  , permission_id ));
ALTER TABLE Ziekenhuis_Martini .roles_permission  ADD CONSTRAINT fk_roles_permission_rolesid  FOREIGN KEY (roles_id)  REFERENCES  Ziekenhuis_Martini .roles(id);
ALTER TABLE Ziekenhuis_Martini .roles_permission  ADD CONSTRAINT fk_roles_permission_id  FOREIGN KEY (permission_id)  REFERENCES  Ziekenhuis_Martini .permission(id);

ALTER TABLE Ziekenhuis_Martini .screen ADD COLUMN iframeUrl VARCHAR;

CREATE TABLE Ziekenhuis_Martini .publication_disabledbroadcastwrapper (publication_id INTEGER NOT NULL, disabledbroadcastwrapper_id INTEGER NOT NULL, 
PRIMARY KEY (publication_id, disabledbroadcastwrapper_id), UNIQUE (publication_id,disabledbroadcastwrapper_id));


ALTER TABLE Ziekenhuis_Martini .publication_disabledbroadcastwrapper
  ADD CONSTRAINT fk_publication_disabledbroadcastwrapper_publicationId
  FOREIGN KEY (publication_id)
  REFERENCES Ziekenhuis_Martini .publication (id);

ALTER TABLE Ziekenhuis_Martini .publication_disabledbroadcastwrapper
  ADD CONSTRAINT fk_publication_disabledbroadcastwrapper_Id
  FOREIGN KEY (disabledbroadcastwrapper_id)
  REFERENCES Ziekenhuis_Martini .broadcastwrapper (id);
  
ALTER TABLE Ziekenhuis_Martini .contentblock RENAME COLUMN brightcoveid TO videoId;
ALTER TABLE Ziekenhuis_Martini .contentblock ADD COLUMN duration INTEGER;
ALTER TABLE Ziekenhuis_Martini .contentblock ADD COLUMN source VARCHAR;

CREATE TABLE Ziekenhuis_Martini .PlaylistWrapper (id INTEGER NOT NULL, entityversion INTEGER NOT NULL, playlistid INTEGER NOT NULL, orderOfPlaylist INTEGER NOT NULL);

ALTER TABLE Ziekenhuis_Martini .PlaylistWrapper
  ADD CONSTRAINT pk_PlaylistWrapper
  PRIMARY KEY (id);

ALTER TABLE Ziekenhuis_Martini .PlaylistWrapper
  ADD CONSTRAINT fk_PlaylistWrapper_playlistId
  FOREIGN KEY (playlistid)
  REFERENCES Ziekenhuis_Martini .playlist (id);

CREATE TABLE Ziekenhuis_Martini .publication_PlaylistWrapper (publication_id INTEGER NOT NULL, playlistwrapper_id INTEGER NOT NULL,
 UNIQUE (publication_id,playlistwrapper_id));


ALTER TABLE Ziekenhuis_Martini .publication_PlaylistWrapper
  ADD CONSTRAINT fk_publication_PlaylistWrapper_publicationId
  FOREIGN KEY (publication_id)
  REFERENCES Ziekenhuis_Martini .publication (id);

ALTER TABLE Ziekenhuis_Martini .publication_PlaylistWrapper
  ADD CONSTRAINT fk_publication_PlaylistWrapper_Id
  FOREIGN KEY (playlistwrapper_id)
  REFERENCES Ziekenhuis_Martini .PlaylistWrapper (id);
  
Drop TABLE Ziekenhuis_Martini .publication_disabledbroadcastwrapper ;

CREATE TABLE Ziekenhuis_Martini .publication_disabledbroadcast (publication_id INTEGER NOT NULL, disabledbroadcast_id INTEGER NOT NULL, 
PRIMARY KEY (publication_id, disabledbroadcast_id), UNIQUE (publication_id,disabledbroadcast_id));


ALTER TABLE Ziekenhuis_Martini .publication_disabledbroadcast
  ADD CONSTRAINT fk_publication_disabledbroadcast_publicationId
  FOREIGN KEY (publication_id)
  REFERENCES Ziekenhuis_Martini .publication (id);

ALTER TABLE Ziekenhuis_Martini .publication_disabledbroadcast
  ADD CONSTRAINT fk_publication_disabledbroadcast_Id
  FOREIGN KEY (disabledbroadcast_id)
  REFERENCES Ziekenhuis_Martini .broadcast (id);
  
ALTER TABLE Ziekenhuis_Martini .contentblock alter column url TYPE TEXT; 

ALTER TABLE Ziekenhuis_Martini.article ADD createdByName CHARACTER VARYING(255);
ALTER TABLE Ziekenhuis_Martini.templateNarrowcasting ADD imageAspectRatio CHARACTER VARYING(255);


GRANT ALL ON TABLE Ziekenhuis_Martini .roles_permission TO epublisher;
GRANT SELECT, REFERENCES ON TABLE Ziekenhuis_Martini .roles_permission TO dashboard;
GRANT USAGE ON SCHEMA Ziekenhuis_Martini  TO GROUP dashboard;


GRANT ALL ON ALL TABLES IN SCHEMA Ziekenhuis_Martini  TO GROUP epublisher; 


INSERT INTO  Ziekenhuis_Martini .outputchannel (dtype, id, name, entityversion) VALUES ('OutputChannelNewsletterNS'	,2,	'newsletterNS',	1);
INSERT INTO  Ziekenhuis_Martini .outputchannel (dtype, id, name, entityversion) VALUES ('OutputChannelIntranetNS'	,1,	'intranetNS',	1);
INSERT INTO  Ziekenhuis_Martini .outputchannel (dtype, id, name, entityversion) VALUES ('OutputChannelPocketRailNS'	,3,	'railpocketNS',	1);


ALTER TABLE Ziekenhuis_Martini.templateNarrowcasting ADD templateimageWidth INTEGER; 
ALTER TABLE Ziekenhuis_Martini.templateNarrowcasting ADD templateimageheight INTEGER; 


 

GRANT  ALL ON ALL SEQUENCES IN SCHEMA Ziekenhuis_Martini  TO  GROUP epublisher;
GRANT ALL ON TABLE  Ziekenhuis_Martini .Permission TO epublisher;
GRANT SELECT, REFERENCES ON TABLE  Ziekenhuis_Martini .Permission TO dashboard;
GRANT ALL ON TABLE Ziekenhuis_Martini .Roles TO epublisher;
GRANT SELECT, REFERENCES ON TABLE Ziekenhuis_Martini .Roles TO dashboard;
GRANT ALL ON TABLE Ziekenhuis_Martini .epublisheruser_roles TO epublisher;
GRANT SELECT, REFERENCES ON TABLE Ziekenhuis_Martini .epublisheruser_roles TO dashboard;
grant select, references on Ziekenhuis_Martini .epublisheruser to dashboard;
grant select, references on Ziekenhuis_Martini .articlewrapper to dashboard;
grant select, references on Ziekenhuis_Martini .article to dashboard;
grant select, references on Ziekenhuis_Martini .resourceinfo to dashboard;
grant select, references on Ziekenhuis_Martini .category to dashboard;
grant select, references on Ziekenhuis_Martini .edition to dashboard;
grant select, references on Ziekenhuis_Martini .publication to dashboard;
grant select, references on Ziekenhuis_Martini .template to dashboard;
grant select, references on Ziekenhuis_Martini .epublishermedia to dashboard;
grant select, references on Ziekenhuis_Martini .edition_subscriptiongroup to dashboard;
grant select, references on Ziekenhuis_Martini .subscriptiongroup to dashboard;
grant select, references on Ziekenhuis_Martini .subscriptiongroup_subscriber to dashboard;
grant select, references,update on Ziekenhuis_Martini .epublisheruser to dashboard;
grant select, references on Ziekenhuis_Martini .epublisheruser_publication to dashboard;
grant select, references on Ziekenhuis_Martini .subscriber to dashboard;

SELECT setval('Ziekenhuis_Martini .epublisher_sequence', 100);



-----------------------------------------------------------------------DATA ------------------------------------------------------------

INSERT INTO Ziekenhuis_Martini .outputchannel (dtype, id, name, entityversion) VALUES ('OutputChannelNarrowcastingNS'	,4,	'narrowcastingNS',	1);

INSERT
INTO
    Ziekenhuis_Martini .epublisheruser
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
        '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',
        null,
        'publish',
                0,
        'c34eba71-57d3-4af5-8b68-0d449d34ea9a'
    );


INSERT INTO Ziekenhuis_Martini .roles (id, entityversion, name) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 0, 'ROLE_ADMIN');
INSERT INTO Ziekenhuis_Martini .roles (id, entityversion, name) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 0, 'ROLE_USER');

INSERT INTO Ziekenhuis_Martini .epublisheruser_roles (epublisheruser_id, roles_id) VALUES ((select max(id) from Ziekenhuis_Martini .epublisheruser where email ='hosting@prisma-it.com'), (SELECT max(id) FROM Ziekenhuis_Martini .Roles where name= 'ROLE_ADMIN'));
INSERT INTO Ziekenhuis_Martini .epublisheruser_roles (epublisheruser_id, roles_id) VALUES ((select max(id) from Ziekenhuis_Martini .epublisheruser where email ='hosting@prisma-it.com'), (SELECT max(id) FROM Ziekenhuis_Martini .Roles where name= 'ROLE_USER'));


INSERT INTO Ziekenhuis_Martini .permission (id, entityversion, name) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 0, 'Wijzig Uitzendingen');
INSERT INTO Ziekenhuis_Martini .permission (id, entityversion, name) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 0, 'Verwijder Uitzendingen');
INSERT INTO Ziekenhuis_Martini .permission (id, entityversion, name) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 0, 'Wijzig Playlist');
INSERT INTO Ziekenhuis_Martini .permission (id, entityversion, name) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 0, 'Verwijder Playlist');
INSERT INTO Ziekenhuis_Martini .permission (id, entityversion, name) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 0, 'Publiceer Playlist');



--------------------------------------TEMPLATE --------------------------------------------
--full screen video
--(portrait) title, tekst, image
--(landscape) title, tekst, image




-----------------------video-fullscreen-----------------

INSERT INTO Ziekenhuis_Martini .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Ziekenhuis_Martini .epublisher_sequence'),	1,	'zm-video-fullscreen',	'Video-Fullscreen'	,null);

INSERT INTO Ziekenhuis_Martini .containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 1, 1);
INSERT INTO Ziekenhuis_Martini .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Martini .containerarea),'video');
INSERT INTO Ziekenhuis_Martini .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Martini .templatenarrowcasting),	(Select max(id) from Ziekenhuis_Martini .containerarea));



------------------------Landscape slide---------------------------

INSERT INTO Ziekenhuis_Martini .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, imageaspectratio) VALUES ( nextval('Ziekenhuis_Martini .epublisher_sequence'),	1,	'zm-landscape-slide',	'Landscape slide'	,null , '4/1' );


INSERT INTO Ziekenhuis_Martini .containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 1, 1);
INSERT INTO Ziekenhuis_Martini .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Martini .containerarea),'tekst');
INSERT INTO Ziekenhuis_Martini .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Martini .templatenarrowcasting),	(Select max(id) from Ziekenhuis_Martini .containerarea));


INSERT INTO Ziekenhuis_Martini .containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 1, 2);
INSERT INTO Ziekenhuis_Martini .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Martini .containerarea),'afbeelding');
INSERT INTO Ziekenhuis_Martini .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Martini .templatenarrowcasting),	(Select max(id) from Ziekenhuis_Martini .containerarea));

------------------------Portrait slide ---------------------------

INSERT INTO Ziekenhuis_Martini .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Ziekenhuis_Martini .epublisher_sequence'),	1,	'zm-portrait-slide',	'Portrait slide'	,null);


INSERT INTO Ziekenhuis_Martini .containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 1, 1);
INSERT INTO Ziekenhuis_Martini .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Martini .containerarea),'tekst');
INSERT INTO Ziekenhuis_Martini .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Martini .templatenarrowcasting),	(Select max(id) from Ziekenhuis_Martini .containerarea));


INSERT INTO Ziekenhuis_Martini .containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Martini .epublisher_sequence'), 1, 2);
INSERT INTO Ziekenhuis_Martini .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Martini .containerarea),'afbeelding');
INSERT INTO Ziekenhuis_Martini .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Martini .templatenarrowcasting),	(Select max(id) from Ziekenhuis_Martini .containerarea));





