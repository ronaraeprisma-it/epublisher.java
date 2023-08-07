package nl.prismait.epublisher.java.model.narrowcasting;
// ***************************************************************************
// 
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL$
//		$Id$
// 
// ***************************************************************************


import javax.persistence.Entity;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.prismait.epublisher.java.model.Edition;
import nl.prismait.epublisher.java.model.OutputChannel;
import nl.prismait.epublisher.java.model.exception.EpublisherException;

@Entity
public class OutputChannelNSTreinen extends OutputChannel {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	
	@Transient
	public String getDtype()
	{
		return this.getClass().getSimpleName();
	}
	public void publish(Edition edition, Edition previousEdition) throws EpublisherException {
		
		logger.error("NS Treinen publications need not be published");
		
		// Narrowcasting publications are being pulled not pushed.
		// so when publishing a new playlist you do not need to call this method

	}

}
