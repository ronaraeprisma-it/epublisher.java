package nl.prismait.epublisher.java;

// ***************************************************************************
//
// Copyright 2014, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************

/**
 * This Class is solely used to provide the version of the project.
 * Using the templating-maven-plugin in the pom.xml the variables are
 * interpolated and will reflect the version of the build.
 *  
 * During development, it will not be interpolated
 */
public class Version
{
	private static final String VERSION = "${project.version}";

	public static String getVersion()
	{
		return VERSION;
	}
}
