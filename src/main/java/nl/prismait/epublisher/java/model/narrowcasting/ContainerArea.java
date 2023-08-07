// ***************************************************************************
// 
//		Copyright 2013, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL$
//		$Id$
// 
// ***************************************************************************
package nl.prismait.epublisher.java.model.narrowcasting;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import nl.prismait.epublisher.java.model.VersionedBaseEntity;
import nl.prismait.epublisher.java.util.EpublisherConstants;

@Entity
public class ContainerArea extends VersionedBaseEntity {

	private Set<String> allowedContent;
	private int containerPosition;
	//Added this transient property to help front end populate the broadcast editor
	private List<ContentBlock> allowedContentBlock= new ArrayList<>(); 

	@ElementCollection(fetch = FetchType.EAGER)
	@OrderBy
	public Set<String> getAllowedContent() {

		return allowedContent;
	}

	public void setAllowedContent(Set<String> allowedContent) {

		this.allowedContent = allowedContent;
	}

	public int getContainerPosition() {

		return containerPosition;
	}

	public void setContainerPosition(int containerPosition) {

		this.containerPosition = containerPosition;
	}

	@Transient
	public List<ContentBlock> getAllowedContentBlock() {
		if(this.allowedContent!=null)
		{
			this.allowedContentBlock = new ArrayList<>();
			for(String content:getAllowedContent() )
			{

				//TO DO : Add a case for EPublisherWeatherInfo
				switch (content) {

				case EpublisherConstants.IMAGE:
					ContentBlock image = new EPublisherNarrowcastingImage();
					this.allowedContentBlock.add(image);
					break;

				case EpublisherConstants.TEXT:
					ContentBlock text = new EPublisherText();
					this.allowedContentBlock.add(text);
					break;

				case EpublisherConstants.VIDEO:
					ContentBlock video = new EPublisherVideo();
					this.allowedContentBlock.add(video);
					break;

				case EpublisherConstants.CLOCK:
					ContentBlock clock = new EPublisherClock();
					this.allowedContentBlock.add(clock);
					break;

				case EpublisherConstants.DATE:
					ContentBlock date = new EPublisherDate();
					this.allowedContentBlock.add(date);
					break;

				case EpublisherConstants.RSS:
					ContentBlock rss = new EPublisherRss();
					this.allowedContentBlock.add(rss);
					break;

				case EpublisherConstants.TWITTER_FEED:
					ContentBlock twitter = new EPublisherTwitterFeed();
					this.allowedContentBlock.add(twitter);
					break;

				case EpublisherConstants.TITLE:
					ContentBlock titel = new EPublisherTitle();
					this.allowedContentBlock.add(titel);
					break;

				case EpublisherConstants.TELEPHONE_NUMER:
					ContentBlock telephoneNummer = new EPublisherTelephoneNumber();
					this.allowedContentBlock.add(telephoneNummer);
					break;

				case EpublisherConstants.TRAVEL:
					ContentBlock travel = new EPublisherTravelInfo();
					this.allowedContentBlock.add(travel);
					break;

				case EpublisherConstants.WEBSITE:
					ContentBlock website = new EPublisherWebsite();
					this.allowedContentBlock.add(website);
					break;

				case EpublisherConstants.TEXT_AND_IMAGE:
					ContentBlock textAndImage = new EPublisherTextAndImage();
					this.allowedContentBlock.add(textAndImage);
					break;
//					
//				case EpublisherConstants.WAYFINDER:
//					ContentBlock wayfinder = new EPublisherWayfinder();
//					this.allowedContentBlock.add(wayfinder);
//					break;
//					
				default:	
					// non supported content block
				}	
			}
		}
		return allowedContentBlock;
	}

	public void setAllowedContentBlock(List<ContentBlock> allowedContentBlock) {
		this.allowedContentBlock = allowedContentBlock;
	}

	public ContainerArea copyObject() 
	{
		ContainerArea newContainerArea = new ContainerArea();
		
		// Allowed content
		Set<String> newAllowedContent = new HashSet<>();
		
		if (this.getAllowedContent() != null)
		{
			for (String content : this.getAllowedContent()) 
			{
				newAllowedContent.add(content);
			}
			
			newContainerArea.setAllowedContent(newAllowedContent);
		}
		
		// containerPosition
		newContainerArea.setContainerPosition(getContainerPosition());
		
		// Allowed content block
		List<ContentBlock> newAllowedContentBlock = new ArrayList<>(); 
		
		if (this.getAllowedContentBlock() != null)
		{
			for (ContentBlock contentBlock : this.getAllowedContentBlock()) 
			{
				newAllowedContentBlock.add(contentBlock.copyObject());
			}
			
			newContainerArea.setAllowedContentBlock(newAllowedContentBlock);
		}
		
		return newContainerArea;
	}
}
