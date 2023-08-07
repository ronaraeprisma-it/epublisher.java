// ***************************************************************************
//
// Copyright 2014, Paronix
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
// documentation files (the "Software"), to deal in the Software without restriction, including without limitation
// the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
// to permit persons to whom the Software is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all copies or substantial portions
// of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
// THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
// TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
// $HeadURL$
// $Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.dao.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * Sql Utils class provides methods to load SQL files and return them
 * as strings and to replace {0} {1} etc. placeholders in those files
 */
public class SqlUtils
{
	/**
	 * Used to load a SqlFile using a path name of a file on the classpath
	 * 
	 * @param path
	 * 		the path of the sql file 
	 * @return the file content
	 */
	public static String loadSqlFile(final String path)
	{
		// Create input stream of resource
		final InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		return loadSqlFile(stream);
	}

	/**
	 * Used to load a SQL file using an InputStream
	 * 
	 * @param stream
	 * 			the path of the sql file 
	 * @return the file content
	 */
	public static String loadSqlFile(final InputStream stream)
	{
		String replaced = null;

		try
		{
			// load the sql file and remove all single line comments
			String fileContents = IOUtils.toString(stream);
			replaced = fileContents.replaceAll("(?m)^--.*?$[\n\r]{1,}", "");
		}
		catch (final IOException e)
		{
			throw new IllegalStateException(e);
		}
		finally
		{
			IOUtils.closeQuietly(stream);
		}

		return replaced;
	}

	/**
	 * Used to replace {0} placeholders in provided SQL statements
	 * 
	 * @param query
	 * 			the query string
	 * @param replacements
	 * 			the placeholders in the query
	 * @return the query string
	 */
	public static String getQuery(final String query, Object... replacements)
	{
		String result = query;

		// Replace all {0}, {1} etc placeholders with their given replacements
		for (int i = 0; i < replacements.length; i++)
		{
			String replaceWith = (String) replacements[i];
			result = result.replace("{" + i + "}", replaceWith);
		}

		// String result = MessageFormat.format(query, replacements);

		return result;
	}
}
