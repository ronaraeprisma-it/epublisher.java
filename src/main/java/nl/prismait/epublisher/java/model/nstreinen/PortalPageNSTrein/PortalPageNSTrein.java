package nl.prismait.epublisher.java.model.nstreinen.PortalPageNSTrein;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"TEMPLATEVERSION",
	"LANGUAGE",
	"TEMPLATEVARIABLES",
	"TEMPLATE",
	"ID",
	"MAINPAGE"
})
public class PortalPageNSTrein {

	@JsonProperty("TEMPLATEVERSION")
	private int templateversion;
	
	@JsonProperty("LANGUAGE")
	private String language;
	
	@JsonProperty("TEMPLATEVARIABLES")
	private JSONObject templatevariables;
	
	@JsonProperty("TEMPLATE")
	private String template;
	
	@JsonProperty("ID")
	private String id;
	
	@JsonProperty("MAINPAGE")
	private int mainpage;
	
	public int getTemplateversion() {
		return templateversion;
	}

	public void setTemplateversion(int templateversion) {
		this.templateversion = templateversion;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public JSONObject getTemplatevariables() {
		return templatevariables;
	}

	public void setTemplatevariables(JSONObject templatevariables) {
		this.templatevariables = templatevariables;
	}
	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMainpage() {
		return mainpage;
	}

	public void setMainpage(int mainpage) {
		this.mainpage = mainpage;
	}


}