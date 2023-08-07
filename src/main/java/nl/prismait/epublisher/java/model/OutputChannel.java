// ***************************************************************************
//
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
//
//		$HeadURL$
//		$Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import nl.prismait.epublisher.java.model.exception.EpublisherException;
//import nl.prismait.epublisher.java.model.facebook.OutputChannelFacebook;
//import nl.prismait.epublisher.java.model.internet.OutputChannelInternet;
//import nl.prismait.epublisher.java.model.landingpage.OutputChannelLandingPage;
//import nl.prismait.epublisher.java.model.linkedin.OutputChannelLinkedin;
//import nl.prismait.epublisher.java.model.narrowcasting.OutputChannelNSTreinen;
import nl.prismait.epublisher.java.model.narrowcasting.OutputChannelNarrowcastingNS;
//import nl.prismait.epublisher.java.model.nsintranet.OutputChannelIntranetNS;
//import nl.prismait.epublisher.java.model.nsmagazine.OutputChannelMagazineNS;
//import nl.prismait.epublisher.java.model.nsnewsletter.OutputChannelNewsletterNS;
//import nl.prismait.epublisher.java.model.nsspecialsite.OutputChannelSpecialSiteNS;
//import nl.prismait.epublisher.java.model.pocketrail.OutputChannelPocketRailNS;
//import nl.prismait.epublisher.java.model.portalpage.OutputChannelPortalPage;
//import nl.prismait.epublisher.java.model.texttospeech.OutputChannelTTS;
//import nl.prismait.epublisher.java.model.wayfinder.OutputChannelWayfinder;

/**
 * for Each outputchannel there has to be a record to be made in the database that contains the SimpleClassName of the
 * outputchannel.
 * 
 * @author rolf
 * 
 */

@JsonTypeInfo(use = Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "dtype")
@JsonSubTypes({
@Type(value = OutputChannelNarrowcastingNS.class,name="OutputChannelNarrowcastingNS"),
//@Type(value = OutputChannelIntranetNS.class,name="OutputChannelIntranetNS"),
//@Type(value = OutputChannelMagazineNS.class,name="OutputChannelMagazineNS"),
//@Type(value = OutputChannelNewsletterNS.class,name="OutputChannelNewsletterNS"),
//@Type(value = OutputChannelSpecialSiteNS.class,name="OutputChannelSpecialSiteNS"),
//@Type(value = OutputChannelFacebook.class,name="OutputChannelFacebook"),
//@Type(value = OutputChannelLinkedin.class,name="OutputChannelLinkedin"),
//@Type(value = OutputChannelPocketRailNS.class,name="OutputChannelPocketRailNS"),
//@Type(value = OutputChannelWeb.class,name="OutputChannelWeb"),
//@Type(value = OutputChannelInternet.class,name="OutputChannelInternet"),
//@Type(value = OutputChannelLandingPage.class,name="OutputChannelLandingPage"),
//@Type(value = OutputChannelNSTreinen.class,name="OutputChannelNSTreinen"),
//@Type(value = OutputChannelWayfinder.class,name="OutputChannelWayfinder"),
//@Type(value = OutputChannelPortalPage.class,name="OutputChannelPortalPage"),
//@Type(value = OutputChannelTTS.class,name="OutputChannelTTS")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public abstract  class OutputChannel extends VersionedBaseEntity {

	private String name;

	@Column(nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract  void publish(Edition edition, Edition previousEdition) throws EpublisherException;
	
	public void publish(Edition edition, Edition previousEdition, List<Edition> lastEditionOfAllIntranetPublications) throws EpublisherException
	{
		publish(edition, previousEdition);
	}
	
	
	
	
}
