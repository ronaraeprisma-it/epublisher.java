
package nl.prismait.epublisher.java.model.nstreinen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "CHECKCHECKSUM",
    "HEIGHT",
    "VERSION",
    "CHECKSUM",
    "PATH",
    "WIDTH",
    "NAME",
    "ID"
})

public class File {

    @JsonProperty("CHECKCHECKSUM")
    private Integer checkchecksum;
    @JsonProperty("HEIGHT")
    private Integer height;
    @JsonProperty("VERSION")
    private Integer version;
    @JsonProperty("CHECKSUM")
    private String checksum;
    @JsonProperty("PATH")
    private String path;
    @JsonProperty("WIDTH")
    private Integer width;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("ID")
    private String id;

    @JsonProperty("CHECKCHECKSUM")
    public Integer getCheckchecksum() {
        return checkchecksum;
    }

    @JsonProperty("CHECKCHECKSUM")
    public void setCheckchecksum(Integer checkchecksum) {
        this.checkchecksum = checkchecksum;
    }

    @JsonProperty("HEIGHT")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("HEIGHT")
    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("VERSION")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("VERSION")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("CHECKSUM")
    public String getChecksum() {
        return checksum;
    }

    @JsonProperty("CHECKSUM")
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    @JsonProperty("PATH")
    public String getPath() {
        return path;
    }

    @JsonProperty("PATH")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("WIDTH")
    public Integer getWidth() {
        return width;
    }

    @JsonProperty("WIDTH")
    public void setWidth(Integer width) {
        this.width = width;
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

}
