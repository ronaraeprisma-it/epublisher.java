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

import java.io.IOException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import nl.prismait.epublisher.java.model.interfaces.Renderer;
import nl.prismait.epublisher.java.model.interfaces.RenderingException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table(name = "Template")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ETemplate extends VersionedBaseEntity implements Comparable<ETemplate> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ETemplate.class);

	// general properties
	private String name;
	private String freeMarkerTemplateToUse;
	private String cssTemplateToUse;

	// header properties
	private EPublisherImage brandImage;
	private EPublisherImage headerImage;
	private String title;
	private boolean includeDateInHeader;
	private boolean includeEditionNumber;
	private boolean includeRecipientName;

	// table of content properties
	private boolean includeTableOfContent;

	// table of prologue content properties
	private boolean includePrologue;
	private String prologueText;

	// article properties
//	private ArticleRepresentation articleRepresentation; // full, readon, readonlink TODO make enumeration
//	private boolean reactionPossible;
//	private String reactionEmailAddress;

	// colophon properties
	private boolean includeColophon;
	private String colophonText;

	// footer properties
	private boolean includeFooter;
	private String footerText;
	private EPublisherImage footerImage;

	//metadata
	private boolean includeBrowserview;
	private boolean includeCancelSubscription;
	private boolean printingAllowed;
	
	// Images in newsletters
	private boolean includeImagesInNewsletter;
	private ImageSize newsletterThumbnailsImageSize;

	private boolean deleted;
	private boolean showSourcesList;
	
	private boolean includeShareButton;
	private Integer campaignId;
	private String utm_content;
	private String pk_keyword;
	
	public boolean isIncludeShareButton() {
		return includeShareButton;
	}

	public void setIncludeShareButton(boolean includeShareButton) {
		this.includeShareButton = includeShareButton;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}

	public String getFreeMarkerTemplateToUse() {

		return freeMarkerTemplateToUse;
	}

	public void setFreeMarkerTemplateToUse(String freeMarkerTemplateToUse) {

		this.freeMarkerTemplateToUse = freeMarkerTemplateToUse;
	}

	public String getCssTemplateToUse() {

		return cssTemplateToUse;
	}

	public void setCssTemplateToUse(String cssTemplateToUse) {

		this.cssTemplateToUse = cssTemplateToUse;
	}

	@OneToOne
	public EPublisherImage getBrandImage() {

		return brandImage;
	}

	public void setBrandImage(EPublisherImage brandImage) {

		this.brandImage = brandImage;
	}

	@OneToOne
	public EPublisherImage getHeaderImage() {

		return headerImage;
	}

	public void setHeaderImage(EPublisherImage headerImage) {

		this.headerImage = headerImage;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public boolean isIncludeDateInHeader() {

		return includeDateInHeader;
	}

	public void setIncludeDateInHeader(boolean includeDateInHeader) {

		this.includeDateInHeader = includeDateInHeader;
	}

	public boolean isIncludeEditionNumber() {

		return includeEditionNumber;
	}

	public void setIncludeEditionNumber(boolean includeEditionNumber) {

		this.includeEditionNumber = includeEditionNumber;
	}

	public boolean isIncludeRecipientName() {

		return includeRecipientName;
	}

	public void setIncludeRecipientName(boolean includeRecipientName) {

		this.includeRecipientName = includeRecipientName;
	}

	public boolean isIncludeTableOfContent() {

		return includeTableOfContent;
	}

	public void setIncludeTableOfContent(boolean includeTableOfContent) {

		this.includeTableOfContent = includeTableOfContent;
	}

	public boolean isIncludePrologue() {

		return includePrologue;
	}

	public void setIncludePrologue(boolean includePrologue) {

		this.includePrologue = includePrologue;
	}

	@Column(columnDefinition = "TEXT")
	public String getPrologueText() {

		return prologueText;
	}

	public void setPrologueText(String prologueText) {

		this.prologueText = prologueText;
	}

//	@Enumerated(EnumType.STRING)
//	public ArticleRepresentation getArticleRepresentation() {
//
//		return articleRepresentation;
//	}
//
//	public void setArticleRepresentation(ArticleRepresentation articleRepresentation) {
//
//		this.articleRepresentation = articleRepresentation;
//	}
//
//	public boolean isReactionPossible() {
//
//		return reactionPossible;
//	}
//
//	public void setReactionPossible(boolean reactionPossible) {
//
//		this.reactionPossible = reactionPossible;
//	}

//	public String getReactionEmailAddress() {
//
//		return reactionEmailAddress;
//	}
//
//	public void setReactionEmailAddress(String reactionEmailAddress) {
//
//		this.reactionEmailAddress = reactionEmailAddress;
//	}

	public boolean isIncludeColophon() {

		return includeColophon;
	}

	public void setIncludeColophon(boolean includeColophon) {

		this.includeColophon = includeColophon;
	}

	@Column(columnDefinition = "TEXT")
	public String getColophonText() {

		return colophonText;
	}

	public void setColophonText(String colophonText) {

		this.colophonText = colophonText;
	}

	public boolean isIncludeFooter() {

		return includeFooter;
	}

	public void setIncludeFooter(boolean includeFooter) {

		this.includeFooter = includeFooter;
	}

	@Column(columnDefinition = "TEXT")
	public String getFooterText() {

		return footerText;
	}

	public void setFooterText(String footerText) {

		this.footerText = footerText;
	}

	@OneToOne
	public EPublisherImage getFooterImage() {

		return footerImage;
	}

	public void setFooterImage(EPublisherImage footerImage) {

		this.footerImage = footerImage;
	}

	public String render(Renderer renderer, Edition edition) throws RenderingException {

		String result = null;
		try {

			result = renderer.render(getFreeMarkerTemplateToUse(), edition, this);
		} catch (IOException e) {
			LOGGER.error("Template could not be rendered file not found", e.getMessage());
			throw new RenderingException(e);
		}
		return result;
	}

	@Override
	public int compareTo(ETemplate o) {

		return (name!=null?name: "").compareToIgnoreCase(o.name!=null?o.name: "" );
	}

	public void setIncludeBrowserview(boolean includeBrowserview) {
		this.includeBrowserview = includeBrowserview;
	}


	public boolean isIncludeBrowserview() {
		return includeBrowserview;
	}

	public void setIncludeCancelSubscription(boolean includeCancelSubscription) {
		this.includeCancelSubscription = includeCancelSubscription;
	}
	public boolean isIncludeCancelSubscription() {
		return includeCancelSubscription;
	}

	public boolean isPrintingAllowed() {
		return printingAllowed;
	}

	public void setPrintingAllowed(boolean printingAllowed){
		this.printingAllowed = printingAllowed;
	}

	public void setIncludeImagesInNewsletter(boolean includeImagesInNewsletter) {
		this.includeImagesInNewsletter = includeImagesInNewsletter;
	}

	public boolean isIncludeImagesInNewsletter() {
		return includeImagesInNewsletter;
	}

	public void setNewsletterThumbnailsImageSize(
			ImageSize newsletterThumbnailsImageSize) {
		this.newsletterThumbnailsImageSize = newsletterThumbnailsImageSize;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	public ImageSize getNewsletterThumbnailsImageSize() {
		return newsletterThumbnailsImageSize;
	}

	public boolean isDeleted()
	{
		return deleted;
	}

	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	public boolean isShowSourcesList() 
	{
		return showSourcesList;
	}

	public void setShowSourcesList(boolean showSourcesList) 
	
	{
		this.showSourcesList = showSourcesList;
	}

	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	public String getUtm_content() {
		return utm_content;
	}

	public void setUtm_content(String utm_content) {
		this.utm_content = utm_content;
	}

	public String getPk_keyword() {
		return pk_keyword;
	}

	public void setPk_keyword(String pk_keyword) {
		this.pk_keyword = pk_keyword;
	}
	
}
