package nl.prismait.epublisher.java.model.dto;

public class PlaylistBroadcastSearchDto {
	
	private Integer id;
	private String name;
	private boolean isAlreadyLinked;
	private String state;
	private boolean isChildBroadcast;
	
	
	
	
	public boolean isChildBroadcast() {
		return isChildBroadcast;
	}
	public void setChildBroadcast(boolean isChildBroadcast) {
		this.isChildBroadcast = isChildBroadcast;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
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
	
	public boolean isAlreadyLinked() {
		return isAlreadyLinked;
	}
	
	public void setAlreadyLinked(boolean isAlreadyLinked) {
		this.isAlreadyLinked = isAlreadyLinked;
	}

}
