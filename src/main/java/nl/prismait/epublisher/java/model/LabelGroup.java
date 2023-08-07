package nl.prismait.epublisher.java.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class LabelGroup   extends VersionedBaseEntity {

	private String name;
	private Set<Label> labels = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.REFRESH, CascadeType.MERGE})
	public Set<Label> getLabels() {
		return labels;
	}
	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}
	public synchronized void initialize()
	{
		// initialize only if undefined
		if (this.labels == null)
			this.labels = new HashSet<Label>();
	}

	
	
}
