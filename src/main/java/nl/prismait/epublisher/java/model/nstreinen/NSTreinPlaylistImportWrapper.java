
package nl.prismait.epublisher.java.model.nstreinen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "PUBLISHEDJSON",
    "NAME",
    "PUBLISHDATE",
    "ID"
})

public class NSTreinPlaylistImportWrapper {

    @JsonProperty("PUBLISHEDJSON")
    private String publishedJson;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("PUBLISHDATE")
    private String publishDate;
    @JsonProperty("ID")
    private String id;
    @JsonProperty("DESCRIPTION")
    private String description;
    
    @JsonProperty("PUBLISHEDJSON")
	public String getPublishedJson() {
		return publishedJson;
	}
    
    @JsonProperty("PUBLISHEDJSON")
	public void setPublishedJson(String publishedJson) {
		this.publishedJson = publishedJson;
	}
    
    @JsonProperty("NAME")
	public String getName() {
		return name;
	}
    
    @JsonProperty("NAME")
	public void setName(String name) {
		this.name = name;
	}
    
    @JsonProperty("PUBLISHDATE")
	public String getPublishDate() {
		return publishDate;
	}
	
	@JsonProperty("PUBLISHDATE")
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	@JsonProperty("ID")
	public String getId() {
		return id;
	}
	
	@JsonProperty("ID")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("DESCRIPTION")
	public String getDescription() {
		return description;
	}

	@JsonProperty("DESCRIPTION")
	public void setDescription(String description) {
		this.description = description;
	}
    
	
    
}
