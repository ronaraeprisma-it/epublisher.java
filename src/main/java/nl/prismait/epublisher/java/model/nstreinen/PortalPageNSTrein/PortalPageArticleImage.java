
package nl.prismait.epublisher.java.model.nstreinen.PortalPageNSTrein;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import nl.prismait.epublisher.java.model.nstreinen.File;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "PATH",
    "FILES"
})

public class PortalPageArticleImage {

    @JsonProperty("PATH")
    private String path;
    @JsonProperty("FILES")
    private List<File> files = null;

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

}
