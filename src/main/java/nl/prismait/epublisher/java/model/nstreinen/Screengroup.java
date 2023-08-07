
package nl.prismait.epublisher.java.model.nstreinen;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "FILTERSTRING",
    "ID",
    "TRAINTYPE"
})

public class Screengroup {

    @JsonProperty("FILTERSTRING")
    private String filterstring;
    @JsonProperty("ID")
    private String id;
    @JsonProperty("TRAINTYPE")
    private String traintype;

    @JsonProperty("FILTERSTRING")
    public String getFilterstring() {
        return filterstring;
    }

    @JsonProperty("FILTERSTRING")
    public void setFilterstring(String filterstring) {
        this.filterstring = filterstring;
    }

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    @JsonProperty("ID")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("TRAINTYPE")
    public String getTraintype() {
        return traintype;
    }

    @JsonProperty("TRAINTYPE")
    public void setTraintype(String traintype) {
        this.traintype = traintype;
    }

}
