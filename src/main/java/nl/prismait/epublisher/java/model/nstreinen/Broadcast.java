
package nl.prismait.epublisher.java.model.nstreinen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "ARTICLEREFERENCES",
    "TEMPLATEVERSION",
    "DURATION",
    "TEMPLATEVARIABLES",
    "ASSETREFERENCES",
    "TEMPLATE",
    "NAME",
    "ID",
    "TYPE"
})

public class Broadcast {

    @JsonProperty("ARTICLEREFERENCES")
    private Articlereferences articlereferences;
    @JsonProperty("TEMPLATEVERSION")
    private Integer templateversion;
    @JsonProperty("DURATION")
    private Integer duration;
    @JsonProperty("TEMPLATEVARIABLES")
    private Templatevariables templatevariables;
    @JsonProperty("ASSETREFERENCES")
    private Assetreferences assetreferences;
    @JsonProperty("TEMPLATE")
    private String template;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("ID")
    private String id;
    @JsonProperty("TYPE")
    private String type;

    @JsonProperty("ARTICLEREFERENCES")
    public Articlereferences getArticlereferences() {
        return articlereferences;
    }

    @JsonProperty("ARTICLEREFERENCES")
    public void setArticlereferences(Articlereferences articlereferences) {
        this.articlereferences = articlereferences;
    }

    @JsonProperty("TEMPLATEVERSION")
    public Integer getTemplateversion() {
        return templateversion;
    }

    @JsonProperty("TEMPLATEVERSION")
    public void setTemplateversion(Integer templateversion) {
        this.templateversion = templateversion;
    }

    @JsonProperty("DURATION")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("DURATION")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("TEMPLATEVARIABLES")
    public Templatevariables getTemplatevariables() {
        return templatevariables;
    }

    @JsonProperty("TEMPLATEVARIABLES")
    public void setTemplatevariables(Templatevariables templatevariables) {
        this.templatevariables = templatevariables;
    }

    @JsonProperty("ASSETREFERENCES")
    public Assetreferences getAssetreferences() {
        return assetreferences;
    }

    @JsonProperty("ASSETREFERENCES")
    public void setAssetreferences(Assetreferences assetreferences) {
        this.assetreferences = assetreferences;
    }

    @JsonProperty("TEMPLATE")
    public String getTemplate() {
        return template;
    }

    @JsonProperty("TEMPLATE")
    public void setTemplate(String template) {
        this.template = template;
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

}
