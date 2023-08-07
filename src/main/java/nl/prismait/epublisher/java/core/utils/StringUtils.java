package nl.prismait.epublisher.java.core.utils;


//
//		Copyright 2012, Prisma-IT (www.prisma-it.com)
//		All rights reserved
//
//		$HeadURL$
//		$Id$
//
// ***************************************************************************
public class StringUtils {

	public static String cleanNonAsciiChars(String rawString)
	{
		String title = rawString;
		title = title.replaceAll("[^a-zA-Z0-9]", "");
		return title;
	}

	public static String stripHtml(String input)
	{
		return org.apache.commons.lang3.StringUtils.isEmpty(input) ? "" : input.replaceAll("\\<[^>]*>", "");
	}
}
