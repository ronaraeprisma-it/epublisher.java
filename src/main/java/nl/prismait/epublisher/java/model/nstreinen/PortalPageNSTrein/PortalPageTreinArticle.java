
package nl.prismait.epublisher.java.model.nstreinen.PortalPageNSTrein;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "SUBTEMPLATE",
    "readMoreText",
    "introduction",
    "SUBTEMPLATEVERSION",
    "VERSION",
    "backgroundImage",
    "headerImage",
    "NAME",
    "ID",
    "TYPE",
    "TITLE",
    "body"
})

public class PortalPageTreinArticle {

    @JsonProperty("SUBTEMPLATE")
    private String subtemplate;
    @JsonProperty("readMoreText")
    private String readMoreText;
    @JsonProperty("introduction")
    private String introduction;
    @JsonProperty("SUBTEMPLATEVERSION")
    private Integer subtemplateversion;
    @JsonProperty("VERSION")
    private Integer version;
    @JsonProperty("backgroundImage")
    private PortalPageArticleImage backgroundImage;
    @JsonProperty("headerImage")
    private PortalPageArticleImage headerImage;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("ID")
    private String id;
    @JsonProperty("TYPE")
    private String type;
    @JsonProperty("TITLE")
    private String title;
    @JsonProperty("body")
    private String body;

    @JsonProperty("SUBTEMPLATE")
    public String getSubtemplate() {
        return subtemplate;
    }

    @JsonProperty("SUBTEMPLATE")
    public void setSubtemplate(String subtemplate) {
        this.subtemplate = subtemplate;
    }

    @JsonProperty("readMoreText")
    public String getReadMoreText() {
        return readMoreText;
    }

    @JsonProperty("readMoreText")
    public void setReadMoreText(String readMoreText) {
        this.readMoreText = readMoreText;
    }

    @JsonProperty("introduction")
    public String getIntroduction() {
        return introduction;
    }

    @JsonProperty("introduction")
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @JsonProperty("SUBTEMPLATEVERSION")
    public Integer getSubtemplateversion() {
        return subtemplateversion;
    }

    @JsonProperty("SUBTEMPLATEVERSION")
    public void setSubtemplateversion(Integer subtemplateversion) {
        this.subtemplateversion = subtemplateversion;
    }

    @JsonProperty("VERSION")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("VERSION")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("backgroundImage")
    public PortalPageArticleImage getBackgroundImage() {
        return backgroundImage;
    }

    @JsonProperty("backgroundImage")
    public void setBackgroundImage(PortalPageArticleImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @JsonProperty("headerImage")
    public PortalPageArticleImage getHeaderImage() {
        return headerImage;
    }

    @JsonProperty("headerImage")
    public void setHeaderImage(PortalPageArticleImage headerImage) {
        this.headerImage = headerImage;
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

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

}
