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

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import nl.prismait.epublisher.java.model.exception.EpublisherException;
//import nl.prismait.epublisher.java.model.facebook.EditionFacebook;
//import nl.prismait.epublisher.java.model.internet.EditionInternet;
//import nl.prismait.epublisher.java.model.landingpage.EditionLandingPage;
//import nl.prismait.epublisher.java.model.linkedin.EditionLinkedin;
//import nl.prismait.epublisher.java.model.nsintranet.EditionIntranetNS;
//import nl.prismait.epublisher.java.model.nsmagazine.EditionMagazineNS;
//import nl.prismait.epublisher.java.model.nsnewsletter.EditionNewsletterNS;
//import nl.prismait.epublisher.java.model.nsspecialsite.EditionSpecialSiteNS;
//import nl.prismait.epublisher.java.model.pocketrail.EditionPocketRailNS;
//import nl.prismait.epublisher.java.model.portalpage.EditionPortalPage;
//import nl.prismait.epublisher.java.model.texttospeech.EditionTTS;

@JsonTypeInfo(use = Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "dtype")
@JsonSubTypes({
//@Type(value = EditionIntranetNS.class,name ="EditionIntranetNS"),
//@Type(value = EditionMagazineNS.class,name ="EditionMagazineNS"),
//@Type(value = EditionNewsletterNS.class,name ="EditionNewsletterNS"),
//@Type(value = EditionSpecialSiteNS.class,name ="EditionSpecialSiteNS"),
//@Type(value = EditionPocketRailNS.class,name ="EditionPocketRailNS"),
//@Type(value = EditionFacebook.class,name ="EditionFacebook"),
//@Type(value = EditionLinkedin.class,name ="EditionLinkedin"),
//@Type(value = EditionInternet.class,name ="EditionInternet"),
//@Type(value = EditionLandingPage.class,name ="EditionLandingPage"),
//@Type(value = EditionPortalPage.class,name ="EditionPortalPage"),
//@Type(value = EditionTTS.class,name ="EditionTTS")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Entity
public class Edition extends VersionedBaseEntity {

	private String name;
	private String emailSubject;
	private String emailAddress;
	private Publication publication;
	private int editionNumber;
//	private List<ArticleWrapper> articlewrappers;
	private Date publicationDate;
	private Date lastUpdated;
	private Integer TotalNumberOfArticleWrappers;
	private boolean deleted;
	private Date deletedDateTime;
	private String emailDisplayName;
	// table of prologue content properties
	private String prologueText;
	
	
	public String getEmailDisplayName() {
		return emailDisplayName;
	}

	public void setEmailDisplayName(String emailDisplayName) {
		this.emailDisplayName = emailDisplayName;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getDeletedDateTime() {
		return deletedDateTime;
	}

	public void setDeletedDateTime(Date deletedDateTime) {
		this.deletedDateTime = deletedDateTime;
	}
	
	
	public String getPrologueText() {
		return prologueText;
	}

	public void setPrologueText(String prologueText) {
		this.prologueText = prologueText;
	}

	@Transient
	public Integer getTotalNumberOfArticleWrappers() {
		return TotalNumberOfArticleWrappers;
	}

	public void setTotalNumberOfArticleWrappers(Integer totalNumberOfArticleWrappers) {
		TotalNumberOfArticleWrappers = totalNumberOfArticleWrappers;
	}

	@ManyToOne
	public Publication getPublication() {

		return publication;
	}

	public void setPublication(Publication publication) {

		this.publication = publication;
	}

	@Column(name = "name", nullable = false)
	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getEmailSubject() {

		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {

		this.emailSubject = emailSubject;
	}

	public String getEmailAddress() {

		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {

		this.emailAddress = emailAddress;
	}
	
	
//	@OneToMany(cascade=CascadeType.ALL, mappedBy="edition")
//	@OrderBy("orderOfArticle desc")
//	@JsonManagedReference("edition-articlewrapper")
//	public List<ArticleWrapper> getArticlewrappers() {
//
//		return articlewrappers;
//	}
//
//	public void setArticlewrappers(List<ArticleWrapper> articlewrappers) {
//		this.articlewrappers = articlewrappers;
//
//	}

	public Date getPublicationDate() {

		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {

		this.publicationDate = publicationDate;
	}

	public Edition publish(Edition previousEdition) throws EpublisherException {

		Edition editionToReturn;

		publication.getOutputChannel().publish(this, previousEdition);

		// if previously published return the same edition, else return a new one
		if (publicationDate != null) {
			editionToReturn = this;
		} else {
			editionToReturn = publication.retrieveNewEdition(this);
		}

		setPublicationDate(new Date());

		return editionToReturn;
	}
	
//	public void sort() {
//		if (articlewrappers != null) {
//			Collections.sort(articlewrappers);
//		}
//	}

	public void setLastUpdated(Date lastUpdated) {

		this.lastUpdated = lastUpdated;
	}

	public Date getLastUpdated() {

		return lastUpdated;
	}

	public void setEditionNumber(int editionNumber) {

		this.editionNumber = editionNumber;
	}

	public int getEditionNumber() {

		return editionNumber;
	}

//	public ArticleWrapper createNewArticleWrapper() {
//
//		return new ArticleWrapper();
//	}

	public Edition schedulePublish(Edition previousEdition, boolean isSubEdition, boolean isTestAudience) throws EpublisherException {
		Edition editionToReturn = this;

		return editionToReturn;
	}
	
//	public int totalNumberOfTimesViewed() {
//		int numberOfTimesViewed = 0;
//		for (final ArticleWrapper articleWrapper : articlewrappers) {
//			numberOfTimesViewed += articleWrapper.getNumberOfTimesViewed();
//		}
//		return numberOfTimesViewed;
//	}

}