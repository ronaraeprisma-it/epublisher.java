// ***************************************************************************
//
// Copyright 2013, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.model.narrowcasting;


public class ScreenFilter
{
	public ScreenFilter()
	{
		super();
	}

	public ScreenFilter(String screenGroupKeyword, String screenKeyword)
	{
		super();
		theScreenGroupKeyword = screenGroupKeyword;
		theScreenKeyword = screenKeyword;
	}

	private String theScreenGroupKeyword = null;
	private String theScreenKeyword = null;

	public String getScreenGroupKeyword()
	{
		return theScreenGroupKeyword;
	}

	public void setScreenGroupKeyword(String screenGroupKeyword)
	{
		theScreenGroupKeyword = screenGroupKeyword;
	}

	public String getScreenKeyword()
	{
		return theScreenKeyword;
	}

	public void setScreenKeyword(String screenKeyword)
	{
		theScreenKeyword = screenKeyword;
	}
}
