
package nl.prismait.epublisher.java.model.nstreinen;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "STARTTIME",
    "WEDNESDAY",
    "MONDAY",
    "ENDDATE",
    "INTERVAL",
    "STARTDATE",
    "THURSDAY",
    "PHASE",
    "SCREENGROUP",
    "ENDTIME",
    "SUNDAY",
    "PHASEREPEAT",
    "FRIDAY",
    "SATURDAY",
    "THUESDAY"
})

public class Pattern {

    @JsonProperty("STARTTIME")
    private String starttime;
    @JsonProperty("WEDNESDAY")
    private Integer wednesday;
    @JsonProperty("MONDAY")
    private Integer monday;
    @JsonProperty("ENDDATE")
    private String enddate;
    @JsonProperty("INTERVAL")
    private Integer interval;
    @JsonProperty("STARTDATE")
    private String startdate;
    @JsonProperty("THURSDAY")
    private Integer thursday;
    @JsonProperty("PHASE")
    private Integer phase;
    @JsonProperty("SCREENGROUP")
    private List<Screengroup> screengroup = null;
    @JsonProperty("ENDTIME")
    private String endtime;
    @JsonProperty("SUNDAY")
    private Integer sunday;
    @JsonProperty("PHASEREPEAT")
    private Integer phaserepeat;
    @JsonProperty("FRIDAY")
    private Integer friday;
    @JsonProperty("SATURDAY")
    private Integer saturday;
    @JsonProperty("THUESDAY")
    private Integer thuesday;

    @JsonProperty("STARTTIME")
    public String getStarttime() {
        return starttime;
    }

    @JsonProperty("STARTTIME")
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    @JsonProperty("WEDNESDAY")
    public Integer getWednesday() {
        return wednesday;
    }

    @JsonProperty("WEDNESDAY")
    public void setWednesday(Integer wednesday) {
        this.wednesday = wednesday;
    }

    @JsonProperty("MONDAY")
    public Integer getMonday() {
        return monday;
    }

    @JsonProperty("MONDAY")
    public void setMonday(Integer monday) {
        this.monday = monday;
    }

    @JsonProperty("ENDDATE")
    public String getEnddate() {
        return enddate;
    }

    @JsonProperty("ENDDATE")
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    @JsonProperty("INTERVAL")
    public Integer getInterval() {
        return interval;
    }

    @JsonProperty("INTERVAL")
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    @JsonProperty("STARTDATE")
    public String getStartdate() {
        return startdate;
    }

    @JsonProperty("STARTDATE")
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    @JsonProperty("THURSDAY")
    public Integer getThursday() {
        return thursday;
    }

    @JsonProperty("THURSDAY")
    public void setThursday(Integer thursday) {
        this.thursday = thursday;
    }

    @JsonProperty("PHASE")
    public Integer getPhase() {
        return phase;
    }

    @JsonProperty("PHASE")
    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    @JsonProperty("SCREENGROUP")
    public List<Screengroup> getScreengroup() {
        return screengroup;
    }

    @JsonProperty("SCREENGROUP")
    public void setScreengroup(List<Screengroup> screengroup) {
        this.screengroup = screengroup;
    }

    @JsonProperty("ENDTIME")
    public String getEndtime() {
        return endtime;
    }

    @JsonProperty("ENDTIME")
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    @JsonProperty("SUNDAY")
    public Integer getSunday() {
        return sunday;
    }

    @JsonProperty("SUNDAY")
    public void setSunday(Integer sunday) {
        this.sunday = sunday;
    }

    @JsonProperty("PHASEREPEAT")
    public Integer getPhaserepeat() {
        return phaserepeat;
    }

    @JsonProperty("PHASEREPEAT")
    public void setPhaserepeat(Integer phaserepeat) {
        this.phaserepeat = phaserepeat;
    }

    @JsonProperty("FRIDAY")
    public Integer getFriday() {
        return friday;
    }

    @JsonProperty("FRIDAY")
    public void setFriday(Integer friday) {
        this.friday = friday;
    }

    @JsonProperty("SATURDAY")
    public Integer getSaturday() {
        return saturday;
    }

    @JsonProperty("SATURDAY")
    public void setSaturday(Integer saturday) {
        this.saturday = saturday;
    }

    @JsonProperty("THUESDAY")
    public Integer getThuesday() {
        return thuesday;
    }

    @JsonProperty("THUESDAY")
    public void setThuesday(Integer thuesday) {
        this.thuesday = thuesday;
    }

}
