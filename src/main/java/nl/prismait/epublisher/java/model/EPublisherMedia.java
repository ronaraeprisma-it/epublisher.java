// ***************************************************************************
// 
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL$
//		$Id$
// 
// ***************************************************************************
package nl.prismait.epublisher.java.model;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import nl.prismait.epublisher.java.model.config.PropertiesUtil;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="mediatype",
    discriminatorType=DiscriminatorType.STRING
)
@JsonTypeInfo(use = Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "dtype")
@JsonSubTypes({
@Type(value = EPublisherLink.class,name ="EPublisherLink"),
@Type(value = EPublisherFile.class,name ="EPublisherFile"),
@Type(value = EPublisherImage.class,name ="EPublisherImage"),
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class EPublisherMedia extends VersionedBaseEntity implements Comparable<EPublisherMedia> {

	private String name;
	private String fileName;
	//hibernate access by field instead of default getters and setters. this because there is logic in the getter.
	@Access(AccessType.FIELD)
	protected String url;
	
	 
		@GeneratedValue(generator="seq")
		@GenericGenerator(name="seq", strategy = "sequence",
			parameters = {
				@Parameter(name="sequence", value="folderid_seq")
	    	}
		)
	private Long folderid ;
	
	private String uuid = UUID.randomUUID().toString();
	private String username;
	private String version;
	private int sortOrder;
	
	public void setName(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setFileName(String fileName) {

		this.fileName = fileName;
	}

	public String getFileName() {

		return fileName;
	}

	public void setUrl(String url) {
		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = sra!=null? sra.getRequest(): null;   
        String base = req!=null?PropertiesUtil.getProperty("uri.schema")+"://"+req.getServerName()+"/"+PropertiesUtil.getProperty("epublisher.blazeds.contextroot"): " ";

	//	String base = new StringBuffer(PropertiesUtil.getProperty("epublisher.baseurl")).append(PropertiesUtil.getProperty("epublisher.blazeds.contextroot")).toString();
		if (url.contains(base)) {
			url = url.replace(base, "");
		}
		this.url = url;
	}

	@Column(name = "url")
	public String getUrl(){

		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
	        HttpServletRequest req = sra!=null? sra.getRequest(): null;   
		String base = req!=null?PropertiesUtil.getProperty("uri.schema")+"://"+req.getServerName(): " ";

		if(req!=null && !url.contains(base)) {
			url =  new StringBuffer(PropertiesUtil.getProperty("uri.schema")+"://"+req.getServerName()+"/").append(PropertiesUtil.getProperty("epublisher.blazeds.contextroot")).append( url).toString();
		}else {
			//this happens in case of preview 
			if(req!=null && url.contains(base) && !url.contains(PropertiesUtil.getProperty("epublisher.blazeds.contextroot"))) 
				url = url.replace(base,((PropertiesUtil.getProperty("uri.schema")+"://"+req.getServerName()+"/")+(PropertiesUtil.getProperty("epublisher.blazeds.contextroot"))));
		}
		return url;
	
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Column(nullable=true)
	public int getSortOrder() {
		return sortOrder;
	}

	@Override
	public int compareTo(EPublisherMedia o) {
		
		return this.getUrl().compareTo(o.getUrl());
	}




	@Column(name = "folderid", updatable = false, insertable = false)
	public Long getFolderid() {
		return folderid;
	}

	public void setFolderid(Long folderid) {

		if (folderid !=null && folderid == -1){
			folderid = null;
		}
		this.folderid = folderid;
	}

	public String getUuid() 
	{
		return uuid;
	}

	public void setUuid(String uuid) 
	{
		this.uuid = uuid;
	}

	


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVersion() 
	{
		return version;
	}

	public void setVersion(String version) 
	{
		this.version = version;
	}

}
