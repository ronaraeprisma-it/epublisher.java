
package nl.prismait.epublisher.java.model.nstreinen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "imageFull"
})

public class Assetreferences {

    @JsonProperty("imageFull")
    private ImageFull imageFull;
    
    @JsonProperty("videoFull")
    private VideoFull videoFull;

    @JsonProperty("imageFull")
    public ImageFull getImageFull() {
        return imageFull;
    }

    @JsonProperty("imageFull")
    public void setImageFull(ImageFull imageFull) {
        this.imageFull = imageFull;
    }

    @JsonProperty("videoFull")
	public VideoFull getVideoFull() {
		return videoFull;
	}

    @JsonProperty("videoFull")
	public void setVideoFull(VideoFull videoFull) {
		this.videoFull = videoFull;
	}
    
    

}
