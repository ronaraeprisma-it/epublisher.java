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

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class EPublisherVideo extends ContentBlock {
	
	private String name;
	private String videoId;
	private String url;
	private Integer duration;
	private String source;
	private Long fileSizeInBytes;
	private Integer videoHeight;
	private Integer videoWidth;
	private Integer videoMinrate;
	private Integer videoMaxrate;
	
	
	
	
	
	
	public Integer getVideoHeight() {
		return videoHeight;
	}

	public void setVideoHeight(Integer videoHeight) {
		this.videoHeight = videoHeight;
	}

	public Integer getVideoWidth() {
		return videoWidth;
	}

	public void setVideoWidth(Integer videoWidth) {
		this.videoWidth = videoWidth;
	}

	public Integer getVideoMinrate() {
		return videoMinrate;
	}

	public void setVideoMinrate(Integer videoMinrate) {
		this.videoMinrate = videoMinrate;
	}

	public Integer getVideoMaxrate() {
		return videoMaxrate;
	}

	public void setVideoMaxrate(Integer videoMaxrate) {
		this.videoMaxrate = videoMaxrate;
	}

	public Long getFileSizeInBytes() {
		return fileSizeInBytes;
	}

	public void setFileSizeInBytes(Long fileSizeInBytes) {
		this.fileSizeInBytes = fileSizeInBytes;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
	

	
	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public EPublisherVideo copyObject() 
	{
		EPublisherVideo newEPublisherVideo = new EPublisherVideo();
		
		newEPublisherVideo.setVideoId(getVideoId());
		newEPublisherVideo.setContainerId(getContainerId());
		newEPublisherVideo.setEnabled(isEnabled());
		newEPublisherVideo.setName(getName());
		newEPublisherVideo.setTitle(getTitle());
		newEPublisherVideo.setUrl(getUrl());
		newEPublisherVideo.setDuration(getDuration());
		newEPublisherVideo.setSource(getSource());
		newEPublisherVideo.setVideoHeight(getVideoHeight());
		newEPublisherVideo.setVideoWidth(getVideoWidth());
		newEPublisherVideo.setVideoMinrate(getVideoMinrate());
		newEPublisherVideo.setVideoMaxrate(getVideoMaxrate());;
		if(getFileuuid() != null && !getFileuuid().isEmpty() && getFileuuid().length() > 0) {
			newEPublisherVideo.setFileuuid(getFileuuid());
		}
		
		return newEPublisherVideo;
	}
}
