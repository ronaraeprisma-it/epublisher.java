package nl.prismait.epublisher.java.model.narrowcasting;

import javax.persistence.Entity;

import nl.prismait.epublisher.java.model.VersionedBaseEntity;

@Entity
public class ExternalRSSLink extends VersionedBaseEntity{

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
