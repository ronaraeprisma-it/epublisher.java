package nl.prismait.epublisher.java.model.dashboard.schema;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dashboard.um_tenant_organisation")
public class Tenant {

	private String organisationId;
	private String tenantname;
	private String tenantid;
	@Id
	private String id;
	private String tenantUrl;
	private String imageLocation;
	private String broadcastPublicationMethod;
	private String articlePublicationMethod;
	private String videoSource;
	
	
	
	
	
	public String getBroadcastPublicationMethod() {
		return broadcastPublicationMethod;
	}
	public void setBroadcastPublicationMethod(String broadcastPublicationMethod) {
		this.broadcastPublicationMethod = broadcastPublicationMethod;
	}
	public String getArticlePublicationMethod() {
		return articlePublicationMethod;
	}
	public void setArticlePublicationMethod(String articlePublicationMethod) {
		this.articlePublicationMethod = articlePublicationMethod;
	}
	public String getVideoSource() {
		return videoSource;
	}
	public void setVideoSource(String videoSource) {
		this.videoSource = videoSource;
	}
	public String getImageLocation() {
		return imageLocation;
	}
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	public String getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}
	public String getTenantname() {
		return tenantname;
	}
	public void setTenantname(String tenantname) {
		this.tenantname = tenantname;
	}
	public String getTenantid() {
		return tenantid;
	}
	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenantUrl() {
		return tenantUrl;
	}
	public void setTenantUrl(String tenantUrl) {
		this.tenantUrl = tenantUrl;
	}
	

	
}
