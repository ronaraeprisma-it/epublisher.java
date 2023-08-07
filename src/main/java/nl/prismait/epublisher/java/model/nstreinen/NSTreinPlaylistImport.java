
package nl.prismait.epublisher.java.model.nstreinen;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "ROUTENUMBERS",
    "RANGE",
    "PATTERNS",
    "NEXTSTATION",
    "JSONVERSION",
    "VERSION",
    "TOTALDURATION",
    "LONG",
    "PREVIOUSSTATION",
    "LAT",
    "BROADCASTS",
    "PRIORITY",
    "FINALDESTINATION",
    "ID",
    "TRAINSETNUMBERS"
})

public class NSTreinPlaylistImport {

    @JsonProperty("ROUTENUMBERS")
    private String routenumbers;
    @JsonProperty("RANGE")
    private Integer range;
    @JsonProperty("PATTERNS")
    private List<Pattern> patterns = null;
    @JsonProperty("NEXTSTATION")
    private String nextstation;
    @JsonProperty("JSONVERSION")
    private String jsonversion;
    @JsonProperty("VERSION")
    private Integer version;
    @JsonProperty("TOTALDURATION")
    private Integer totalduration;
    @JsonProperty("LONG")
    private Integer _long;
    @JsonProperty("PREVIOUSSTATION")
    private String previousstation;
    @JsonProperty("LAT")
    private Integer lat;
    @JsonProperty("BROADCASTS")
    private List<Broadcast> broadcasts = null;
    @JsonProperty("PRIORITY")
    private Integer priority;
    @JsonProperty("FINALDESTINATION")
    private String finaldestination;
    @JsonProperty("ID")
    private String id;
    @JsonProperty("TRAINSETNUMBERS")
    private String trainsetnumbers;

    @JsonProperty("ROUTENUMBERS")
    public String getRoutenumbers() {
        return routenumbers;
    }

    @JsonProperty("ROUTENUMBERS")
    public void setRoutenumbers(String routenumbers) {
        this.routenumbers = routenumbers;
    }

    @JsonProperty("RANGE")
    public Integer getRange() {
        return range;
    }

    @JsonProperty("RANGE")
    public void setRange(Integer range) {
        this.range = range;
    }

    @JsonProperty("PATTERNS")
    public List<Pattern> getPatterns() {
        return patterns;
    }

    @JsonProperty("PATTERNS")
    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    @JsonProperty("NEXTSTATION")
    public String getNextstation() {
        return nextstation;
    }

    @JsonProperty("NEXTSTATION")
    public void setNextstation(String nextstation) {
        this.nextstation = nextstation;
    }

    @JsonProperty("JSONVERSION")
    public String getJsonversion() {
        return jsonversion;
    }

    @JsonProperty("JSONVERSION")
    public void setJsonversion(String jsonversion) {
        this.jsonversion = jsonversion;
    }

    @JsonProperty("VERSION")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("VERSION")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("TOTALDURATION")
    public Integer getTotalduration() {
        return totalduration;
    }

    @JsonProperty("TOTALDURATION")
    public void setTotalduration(Integer totalduration) {
        this.totalduration = totalduration;
    }

    @JsonProperty("LONG")
    public Integer getLong() {
        return _long;
    }

    @JsonProperty("LONG")
    public void setLong(Integer _long) {
        this._long = _long;
    }

    @JsonProperty("PREVIOUSSTATION")
    public String getPreviousstation() {
        return previousstation;
    }

    @JsonProperty("PREVIOUSSTATION")
    public void setPreviousstation(String previousstation) {
        this.previousstation = previousstation;
    }

    @JsonProperty("LAT")
    public Integer getLat() {
        return lat;
    }

    @JsonProperty("LAT")
    public void setLat(Integer lat) {
        this.lat = lat;
    }

    @JsonProperty("BROADCASTS")
    public List<Broadcast> getBroadcasts() {
        return broadcasts;
    }

    @JsonProperty("BROADCASTS")
    public void setBroadcasts(List<Broadcast> broadcasts) {
        this.broadcasts = broadcasts;
    }

    @JsonProperty("PRIORITY")
    public Integer getPriority() {
        return priority;
    }

    @JsonProperty("PRIORITY")
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JsonProperty("FINALDESTINATION")
    public String getFinaldestination() {
        return finaldestination;
    }

    @JsonProperty("FINALDESTINATION")
    public void setFinaldestination(String finaldestination) {
        this.finaldestination = finaldestination;
    }

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    @JsonProperty("ID")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("TRAINSETNUMBERS")
    public String getTrainsetnumbers() {
        return trainsetnumbers;
    }

    @JsonProperty("TRAINSETNUMBERS")
    public void setTrainsetnumbers(String trainsetnumbers) {
        this.trainsetnumbers = trainsetnumbers;
    }

}
