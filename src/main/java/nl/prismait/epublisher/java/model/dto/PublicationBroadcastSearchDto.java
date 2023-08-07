package nl.prismait.epublisher.java.model.dto;

import java.util.List;

public class PublicationBroadcastSearchDto {
	
	private Integer id;
	private String name;
	private List<PlaylistBroadcastSearchDto> playlists;
	private String dtype;
	private Integer modifiedPlaylists;
	private Integer submittedPlaylists;
	
	
	
	
	public Integer getSubmittedPlaylists() {
		return submittedPlaylists;
	}

	public void setSubmittedPlaylists(Integer submittedPlaylists) {
		this.submittedPlaylists = submittedPlaylists;
	}

	public Integer getModifiedPlaylists() {
		return modifiedPlaylists;
	}

	public void setModifiedPlaylists(Integer modifiedPlaylists) {
		this.modifiedPlaylists = modifiedPlaylists;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<PlaylistBroadcastSearchDto> getPlaylists() {
		return playlists;
	}
	
	public void setPlaylists(List<PlaylistBroadcastSearchDto> playlists) {
		this.playlists = playlists;
	} 
	
}
