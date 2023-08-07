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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

//import nl.prismait.epublisher.java.model.facebook.PublicationFacebook;
//import nl.prismait.epublisher.java.model.internet.PublicationInternet;
//import nl.prismait.epublisher.java.model.landingpage.PublicationLandingPage;
//import nl.prismait.epublisher.java.model.linkedin.PublicationLinkedin;
import nl.prismait.epublisher.java.model.narrowcasting.Geolocation;
import nl.prismait.epublisher.java.model.narrowcasting.PublicationNarrowcastingNS;
//import nl.prismait.epublisher.java.model.nsintranet.PublicationIntranetNS;
//import nl.prismait.epublisher.java.model.nsmagazine.PublicationMagazineNS;
//import nl.prismait.epublisher.java.model.nsnewsletter.PublicationNewsletterNS;
//import nl.prismait.epublisher.java.model.nsspecialsite.PublicationSpecialSiteNS;
//import nl.prismait.epublisher.java.model.nstreinen.PublicationNSTreinen;
//import nl.prismait.epublisher.java.model.pocketrail.PublicationPocketRailNS;
//import nl.prismait.epublisher.java.model.portalpage.PublicationPortalPage;
//import nl.prismait.epublisher.java.model.texttospeech.PublicationTTS;
//import nl.prismait.epublisher.java.model.wayfinder.PublicationWayfinder;

@JsonTypeInfo(use = Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "dtype")
@JsonSubTypes({
//@Type(value = PublicationNewsletterNS.class,name ="PublicationNewsletterNS"),
@Type(value = PublicationNarrowcastingNS.class,name ="PublicationNarrowcastingNS"),
//@Type(value = PublicationIntranetNS.class,name ="PublicationIntranetNS"),
//@Type(value = PublicationMagazineNS.class,name ="PublicationMagazineNS"),
//@Type(value = PublicationSpecialSiteNS.class,name ="PublicationSpecialSiteNS"),
//@Type(value = PublicationFacebook.class,name ="PublicationFacebook"),
//@Type(value = PublicationLinkedin.class,name ="PublicationLinkedin"),
//@Type(value = PublicationPocketRailNS.class,name ="PublicationPocketRailNS"),
//@Type(value = PublicationInternet.class,name ="PublicationInternet"),
//@Type(value = PublicationLandingPage.class, name = "PublicationLandingPage"),
//@Type(value = PublicationNSTreinen.class, name = "PublicationNSTreinen"),
//@Type(value = PublicationWayfinder.class, name = "PublicationWayfinder"),
//@Type(value = PublicationLandingPage.class,name ="PublicationLandingPage"),
//@Type(value = PublicationPortalPage.class,name ="PublicationPortalPage"),
//@Type(value = PublicationTTS.class,name ="PublicationTTS")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
//@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true, selectBeforeUpdate = true)
@Table(name="publication")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public  class Publication extends VersionedBaseEntity implements Comparable<Publication> {

	private OutputChannel outputChannel;
	private String name;
	private String emailAddress;
	private String emailSubject;
	private ETemplate template;
	private List<ImageSize> allowedThumbnailSizes;
	private boolean deleted;
	private Date deletedDateTime;
	private String username;
	private String password;
	private String interneturl;
	private String sharepointLocation;
	private String appLocation;
	
	private Integer videoWidth;
	private Integer videoHeight;
	private Integer videoMinRate;
	private Integer videoMaxRate;
	
	@Transient
	private Integer connectedScreens;
	@Transient
	private long sizeInBytes;
	private Geolocation geolocation;
	private String language;
	private String uuid;
	
	
	

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="geolocation_id", referencedColumnName="id")
	public Geolocation getGeolocation()
	{
		return geolocation;
	}
	
	public void setGeolocation(Geolocation geolocation) 
	{
		this.geolocation = geolocation;
	}
	
	public Integer getVideoWidth() {
		return videoWidth;
	}

	public void setVideoWidth(Integer videoWidth) {
		this.videoWidth = videoWidth;
	}

	public Integer getVideoHeight() {
		return videoHeight;
	}

	public void setVideoHeight(Integer videoHeight) {
		this.videoHeight = videoHeight;
	}

	public Integer getVideoMinRate() {
		return videoMinRate;
	}

	public void setVideoMinRate(Integer videoMinRate) {
		this.videoMinRate = videoMinRate;
	}

	public Integer getVideoMaxRate() {
		return videoMaxRate;
	}

	public void setVideoMaxRate(Integer videoMaxRate) {
		this.videoMaxRate = videoMaxRate;
	}

	@Transient
	public long getSizeInBytes() {
		return sizeInBytes;
	}

	public void setSizeInBytes(long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

	@Transient
	public Integer getConnectedScreens() {
		return connectedScreens;
	}

	public void setConnectedScreens(Integer connectedScreens) {
		this.connectedScreens = connectedScreens;
	}

	public String getSharepointLocation() {
		return sharepointLocation;
	}

	public void setSharepointLocation(String sharepointLocation) {
		this.sharepointLocation = sharepointLocation;
	}

	public String getAppLocation() {
		return appLocation;
	}

	public void setAppLocation(String appLocation) {
		this.appLocation = appLocation;
	}
	
	private List<PublicationGroup> publicationGroups = new ArrayList<PublicationGroup>();
	private String shareStrategy;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    public OutputChannel getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(OutputChannel outputChannel) {
		this.outputChannel = outputChannel;
	}

	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShareStrategy() {
		return shareStrategy;
	}

	public void setShareStrategy(String shareStrategy) {
		this.shareStrategy = shareStrategy;
	}

	public Edition retrieveNewEdition() {

		Edition newEdition = new Edition();
		newEdition.setName("new Edition " + name );
		newEdition.setPublication(this);

		return newEdition;
	}

	public Edition retrieveNewEdition(Edition previousEdition) {

		Edition newEdition = new Edition();
		newEdition.setName("new Edition " + name );
		newEdition.setPublication(this);
		newEdition.setEditionNumber(previousEdition.getEditionNumber() + 1);
		
//		newEdition.setArticlewrappers(new ArrayList<ArticleWrapper>());
//		if (previousEdition.getArticlewrappers() != null) {
//			for (ArticleWrapper wrapper : previousEdition.getArticlewrappers()){
//				newEdition.getArticlewrappers().add(wrapper.copyObject());
//			}				
//		}
		
		return newEdition;
	}

	@Override
	public int compareTo(Publication o) {
		
		if (o == null || o.getName() == null) {
			return -1;
		}
		else {
			return this.name.compareToIgnoreCase(o.getName());
		}
	}

	public Class<?> retrieveOutputChannelClass() {

		return OutputChannel.class;
	}

	public void setTemplate(ETemplate template) {
	
		this.template = template;
	}

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	public ETemplate getTemplate() {
	
		return template;
	}

	public void setAllowedThumbnailSizes(List<ImageSize> allowedThumbnailSizes) {
		this.allowedThumbnailSizes = allowedThumbnailSizes;
	}

	@ManyToMany(cascade={ CascadeType.PERSIST }, fetch=FetchType.EAGER)
	@OrderColumn(name="THUMBSIZE_INDEX_COL")
	@JoinTable(
            name = "publication_imagesize",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "allowedthumbnailsizes_id")
    )
	public List<ImageSize> getAllowedThumbnailSizes() {
		return allowedThumbnailSizes;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeletedDateTime(Date deletedDateTime)
	{
		this.deletedDateTime = deletedDateTime;
	}

	public Date getDeletedDateTime()
	{
		return deletedDateTime;
	}

	@ManyToMany(mappedBy = "publications")
	@JsonIgnore
	@JsonManagedReference
	public List<PublicationGroup> getPublicationGroups()
	{
		return publicationGroups;
	}

	public void setPublicationGroups(List<PublicationGroup> publicationGroups)
	{
		this.publicationGroups = publicationGroups;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInterneturl() {
		return interneturl;
	}

	public void setInterneturl(String interneturl) {
		this.interneturl = interneturl;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
