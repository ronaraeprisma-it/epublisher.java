package nl.prismait.epublisher.java.model.nstreinen;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.Transient;

import nl.prismait.epublisher.java.model.narrowcasting.BroadcastWrapper;
import nl.prismait.epublisher.java.model.narrowcasting.PlayTime;
import nl.prismait.epublisher.java.model.narrowcasting.Playlist;
import nl.prismait.epublisher.java.model.narrowcasting.ScreenGroup;

@Entity
public class PlaylistNSTreinen extends Playlist{
	
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public Playlist copyObject() 
	{
		PlaylistNSTreinen newPlaylist = new PlaylistNSTreinen();
		newPlaylist.setPublication(getPublication());

		newPlaylist.setName(getName());
		newPlaylist.setDescription(getDescription());
		
		newPlaylist.setPriority(getPriority());
		newPlaylist.setDeleted(isDeleted());
		// Since name is dynamic, made a unique property, which has the id of published playlist from which the clone is made.
		newPlaylist.setAncestorPlaylistId(getId());
		newPlaylist.setState("initial");
		//newPlaylist.setDtype(getDtype());
		
		// set parent id for the new playlist
		if (getParentId() != null) {
			newPlaylist.setParentId(getParentId());
		} else {
			newPlaylist.setParentId(getId());
		}
		
		SortedSet<BroadcastWrapper> newWrappers = new TreeSet<>();
		if (getBroadcastwrappers() != null)
		{
			for (BroadcastWrapper wrapper : getBroadcastwrappers()) 
			{
				newWrappers.add(wrapper.copyObject());
			}
			
			newPlaylist.setBroadcastwrappers(newWrappers);
		}
		
		List<PlayTime> newplayFrequency = new ArrayList<>();
		
		for (PlayTime playTime : getPlayFrequency()) 
		{
			newplayFrequency.add(playTime.copyObject());
		}
		
		newPlaylist.setPlayFrequency(newplayFrequency);
		
		if(this.getTrainDetails() != null) {
			newPlaylist.setTrainDetails(this.getTrainDetails().copyObject());
		}
		
		if(this.getLocation() != null) {
			newPlaylist.setLocation(this.getLocation().copyObject());
		}
		
		if(this.getScreengroups() != null) {
			List<ScreenGroup> screengroupsClone = new ArrayList<>();
			
			for (ScreenGroup screengroup : this.getScreengroups()) 
			{
				screengroupsClone.add(screengroup);
			}
			
			newPlaylist.setScreengroups(screengroupsClone);
		}
		
		
		if(getUuid() != null && !getUuid().isEmpty() && getUuid().length() > 0) {
			newPlaylist.setUuid(getUuid());
		}
		
		return newPlaylist;
	}
}
