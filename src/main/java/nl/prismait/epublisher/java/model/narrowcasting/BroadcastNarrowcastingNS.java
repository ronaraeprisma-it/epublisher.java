package nl.prismait.epublisher.java.model.narrowcasting;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class BroadcastNarrowcastingNS extends BroadcastWrapper{
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
		
	}
	
	@Override
	public BroadcastWrapper copyObject()
	{

		BroadcastNarrowcastingNS broadcastWrapperClone = new BroadcastNarrowcastingNS();
		broadcastWrapperClone.setBroadcast(getBroadcast());
		broadcastWrapperClone.setOrderOfBroadcast(getOrderOfBroadcast());
		broadcastWrapperClone.setBroadcastParentId(getBroadcastParentId());
		broadcastWrapperClone.setPlaylistId(getPlaylistId());
		broadcastWrapperClone.setLocation(getLocation());
		broadcastWrapperClone.setMinVideoResolutionHeight(getMinVideoResolutionHeight());
		broadcastWrapperClone.setMinVideoResolutionWidth(getMinVideoResolutionWidth());
		broadcastWrapperClone.setPlaylistEndHour(getPlaylistEndHour());
		broadcastWrapperClone.setPlaylistEndMinute(getPlaylistEndMinute());
		broadcastWrapperClone.setPlaylistStartHour(getPlaylistStartHour());
		broadcastWrapperClone.setPlaylistStartMinute(getPlaylistStartMinute());
		broadcastWrapperClone.setRssUrls(getRssUrls());
		broadcastWrapperClone.setScreenBackgroundImage(getScreenBackgroundImage());
		broadcastWrapperClone.setVideoPermissions(getVideoPermissions());
		broadcastWrapperClone.setActive(isActive());
		
		return broadcastWrapperClone;
	}
}
