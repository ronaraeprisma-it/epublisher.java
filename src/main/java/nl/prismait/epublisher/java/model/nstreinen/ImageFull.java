
package nl.prismait.epublisher.java.model.nstreinen;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "VERSION",
    "PATH",
    "FILES",
    "NAME",
    "ID",
    "TYPE",
    "TITLE"
})

public class ImageFull {

    @JsonProperty("VERSION")
    private Integer version;
    @JsonProperty("PATH")
    private String path;
    @JsonProperty("FILES")
    private List<File> files = null;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("ID")
    private String id;
    @JsonProperty("TYPE")
    private String type;
    @JsonProperty("TITLE")
    private String title;

    @JsonProperty("VERSION")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("VERSION")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("PATH")
    public String getPath() {
        return path;
    }

    @JsonProperty("PATH")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("FILES")
    public List<File> getFiles() {
        return files;
    }

    @JsonProperty("FILES")
    public void setFiles(List<File> files) {
        this.files = files;
    }

    @JsonProperty("NAME")
    public String getName() {
        return name;
    }

    @JsonProperty("NAME")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    @JsonProperty("ID")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("TYPE")
    public String getType() {
        return type;
    }

    @JsonProperty("TYPE")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("TITLE")
    public String getTitle() {
        return title;
    }

    @JsonProperty("TITLE")
    public void setTitle(String title) {
        this.title = title;
    }

}
