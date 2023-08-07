// ***************************************************************************
//
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
//
//		$HeadURL: https://svn.prisma-it.com/ns/ePublisherJava/trunk/src/main/java/nl/prismait/epublisher/java/model/User.java $
//		$Id: User.java 18 2011-03-16 14:17:46Z ron $
//
// ***************************************************************************
package nl.prismait.epublisher.java.web.content.print;

import nl.prismait.epublisher.java.model.*;
import nl.prismait.epublisher.java.model.interfaces.Renderer;
import nl.prismait.epublisher.java.model.interfaces.RenderingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.IOException;

public class PrintTemplate extends ETemplate  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrintTemplate.class);

	private ETemplate parent;

	public PrintTemplate(ETemplate parent){
		this.parent = parent;
	}

//	@Override
//	public ArticleRepresentation getArticleRepresentation(){
//		return ArticleRepresentation.FULL;
//	}
	public void setName(String name) {

		parent.setName(name);
	}

	public String getName() {

		return parent.getName();
	}

	public String getFreeMarkerTemplateToUse() {

		return parent.getFreeMarkerTemplateToUse();
	}

	public void setFreeMarkerTemplateToUse(String freeMarkerTemplateToUse) {

		parent.setFreeMarkerTemplateToUse(freeMarkerTemplateToUse);
	}

	public String getCssTemplateToUse() {

		return parent.getCssTemplateToUse();
	}

	public void setCssTemplateToUse(String cssTemplateToUse) {

		parent.setCssTemplateToUse(cssTemplateToUse);
	}

	@OneToOne
	public EPublisherImage getBrandImage() {

		return parent.getBrandImage();
	}

	public void setBrandImage(EPublisherImage brandImage) {

		parent.setBrandImage(brandImage);
	}

	@OneToOne
	public EPublisherImage getHeaderImage() {

		return parent.getHeaderImage();
	}

	public void setHeaderImage(EPublisherImage headerImage) {

		parent.setHeaderImage( headerImage);
	}

	public String getTitle() {

		return parent.getTitle();
	}

	public void setTitle(String title) {

		parent.setTitle( title);
	}

	public boolean isIncludeDateInHeader() {

		return parent.isIncludeDateInHeader();
	}

	public void setIncludeDateInHeader(boolean includeDateInHeader) {

		parent.setIncludeDateInHeader( includeDateInHeader);
	}

	public boolean isIncludeEditionNumber() {

		return parent.isIncludeEditionNumber();
	}

	public void setIncludeEditionNumber(boolean includeEditionNumber) {

		parent.setIncludeEditionNumber( includeEditionNumber);
	}

	public boolean isIncludeRecipientName() {

		return parent.isIncludeRecipientName();
	}

	public void setIncludeRecipientName(boolean includeRecipientName) {

		parent.setIncludeRecipientName(includeRecipientName);
	}

	public boolean isIncludeTableOfContent() {

		return parent.isIncludeTableOfContent();
	}

	public void setIncludeTableOfContent(boolean includeTableOfContent) {

		parent.setIncludeTableOfContent(includeTableOfContent);
	}

	public boolean isIncludePrologue() {

		return parent.isIncludePrologue();
	}

	public void setIncludePrologue(boolean includePrologue) {

		parent.setIncludePrologue(includePrologue);
	}

	@Column(columnDefinition = "TEXT")
	public String getPrologueText() {

		return parent.getPrologueText();
	}

	public void setPrologueText(String prologueText) {

		parent.setPrologueText(prologueText);
	}


//	public void setArticleRepresentation(ArticleRepresentation articleRepresentation) {
//
//		parent.setArticleRepresentation(articleRepresentation);
//	}

//	public boolean isReactionPossible() {
//
//		return parent.isReactionPossible();
//	}

//	public void setReactionPossible(boolean reactionPossible) {
//
//		parent.setReactionPossible(reactionPossible);
//	}

//	public String getReactionEmailAddress() {
//
//		return parent.getReactionEmailAddress();
//	}

//	public void setReactionEmailAddress(String reactionEmailAddress) {
//
//		parent.setReactionEmailAddress(reactionEmailAddress);
//	}

	public boolean isIncludeColophon() {

		return parent.isIncludeColophon();
	}

	public void setIncludeColophon(boolean includeColophon) {

		parent.setIncludeColophon(includeColophon);
	}

	public String getColophonText() {

		return parent.getColophonText();
	}

	public void setColophonText(String colophonText) {

		parent.setColophonText(colophonText);
	}

	public boolean isIncludeFooter() {

		return parent.isIncludeFooter();
	}

	public void setIncludeFooter(boolean includeFooter) {

		parent.setIncludeFooter(includeFooter);
	}

	public String getFooterText() {

		return parent.getFooterText();
	}

	public void setFooterText(String footerText) {

		parent.setFooterText(footerText);
	}

	public EPublisherImage getFooterImage() {

		return parent.getFooterImage();
	}

	public void setFooterImage(EPublisherImage footerImage) {

		parent.setFooterImage(footerImage);
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

		return parent.compareTo(o);
	}

	public void setIncludeBrowserview(boolean includeBrowserview) {
		parent.setIncludeBrowserview(includeBrowserview);
	}


	public boolean isIncludeBrowserview() {
		return parent.isIncludeBrowserview();
	}

	public void setIncludeCancelSubscription(boolean includeCancelSubscription) {
		parent.setIncludeCancelSubscription(includeCancelSubscription);
	}
	public boolean isIncludeCancelSubscription() {
		return parent.isIncludeCancelSubscription();
	}

	public boolean isPrintingAllowed() {
		return parent.isPrintingAllowed();
	}

	public void setPrintingAllowed(boolean printingAllowed){
		parent.setPrintingAllowed(printingAllowed);
	}
}
