package nl.prismait.epublisher.java.model;

import javax.persistence.Entity;

@Entity
public class Label  extends VersionedBaseEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Label copyObject() 
	{
		Label newLabel = new Label();	
		
		newLabel.setName(getName());
		
		return newLabel;
	}
}
