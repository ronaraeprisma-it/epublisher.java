package nl.prismait.epublisher.java.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import nl.prismait.epublisher.java.model.OutputChannel;

public class PublicationSummaryDTO
{
	private Integer id;
	private String name;
	private OutputChannel channel;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 @JsonProperty("outputChannel")
	public OutputChannel getOutputChannel() {
		return channel;
	}
	public void setOutputChannel(OutputChannel outputChannel) {
		this.channel = outputChannel;
	}

}
