
package nl.prismait.epublisher.java.model.nstreinen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "subsection"
})

public class Templatevariables {

    @JsonProperty("subsection")
    private String subsection;

    @JsonProperty("subsection")
    public String getSubsection() {
        return subsection;
    }

    @JsonProperty("subsection")
    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

}
