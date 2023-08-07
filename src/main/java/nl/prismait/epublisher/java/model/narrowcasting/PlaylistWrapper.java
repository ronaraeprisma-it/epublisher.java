package nl.prismait.epublisher.java.model.narrowcasting;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import nl.prismait.epublisher.java.model.VersionedBaseEntity;

@Entity
public class PlaylistWrapper extends VersionedBaseEntity {
	private Playlist playlist;
	private int orderOfPlaylist;
	
	@ManyToOne
	@JoinColumn(name="playlistid")
	public Playlist getPlaylist() {
		return playlist;
	}
	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	public int getOrderOfPlaylist() {
		return orderOfPlaylist;
	}
	public void setOrderOfPlaylist(int orderOfPlaylist) {
		this.orderOfPlaylist = orderOfPlaylist;
	}
}
