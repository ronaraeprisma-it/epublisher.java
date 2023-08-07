package nl.prismait.epublisher.java.model.nstreinen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "PATH",
    "FILE",
    "ID"
})
public class VideoFull {
	
    @JsonProperty("PATH")
    private String path;
    
    @JsonProperty("FILE")
    private String file;
    
    @JsonProperty("ID")
    private String id;
    
    @JsonProperty("PATH")
	public String getPath() {
		return path;
	}

    @JsonProperty("PATH")
	public void setPath(String path) {
		this.path = path;
	}

    @JsonProperty("FILE")
	public String getFile() {
		return file;
	}

    @JsonProperty("FILE")
	public void setFile(String file) {
		this.file = file;
	}

    @JsonProperty("ID")
	public String getId() {
		return id;
	}

    @JsonProperty("ID")
	public void setId(String id) {
		this.id = id;
	}

    
}
