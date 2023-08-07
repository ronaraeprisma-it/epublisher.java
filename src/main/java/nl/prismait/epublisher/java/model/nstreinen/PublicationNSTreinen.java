package nl.prismait.epublisher.java.model.nstreinen;
// ***************************************************************************
//
// Copyright 2011, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************

import javax.persistence.Entity;
import javax.persistence.Transient;

import nl.prismait.epublisher.java.model.Edition;
import nl.prismait.epublisher.java.model.narrowcasting.OutputChannelNSTreinen;
import nl.prismait.epublisher.java.model.narrowcasting.PublicationNarrowcastingNS;

@Entity
public class PublicationNSTreinen extends PublicationNarrowcastingNS
{

	@Transient
	@Override
	public String getDtype()
	{
		return this.getClass().getSimpleName();
		
	}
	
	@Override
	public Edition retrieveNewEdition()
	{
		// TODO: write new playlist edition code
		/*
		 * EditionMagazineNS newEdition = new EditionMagazineNS();
		 * newEdition.setName(getName() );
		 * newEdition.setPublication(this);
		 * 
		 * newEdition.setArticlewrappers(new ArrayList<ArticleWrapper>());
		 */
		return null;
	}

	@Override
	public Edition retrieveNewEdition(Edition previousEdition)
	{
		// TODO: write new playlist edition code
		/*
		 * EditionMagazineNS newEdition = new EditionMagazineNS();
		 * newEdition.setName(getName() );
		 * newEdition.setPublication(this);
		 * newEdition.setEditionNumber(previousEdition.getEditionNumber() + 1);
		 * 
		 * newEdition.setArticlewrappers(new ArrayList<ArticleWrapper>());
		 */
		return null;
	}

	@Override
	public Class<?> retrieveOutputChannelClass()
	{
		return OutputChannelNSTreinen.class;
	}

}
