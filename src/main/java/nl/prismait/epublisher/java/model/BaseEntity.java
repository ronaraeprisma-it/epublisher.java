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

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;

@MappedSuperclass
public class BaseEntity {

	private Integer id;
	
	@Id
	@DocumentId
	@GeneratedValue(generator="hibseq")
	@GenericGenerator(name="hibseq", strategy = "seqhilo",
		parameters = {
			@Parameter(name="max_lo", value = "5"),
			@Parameter(name="sequence", value="epublisher_sequence")
    	}
	)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		if (id !=null && id == -1){
			id = null;
		}
		this.id = id;
	}

	
}
