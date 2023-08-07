// ***************************************************************************
// 
//		Copyright 2013, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL$
//		$Id$
// 
// ***************************************************************************
package nl.prismait.epublisher.java.jdbc;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class DataSourceInitializer
{
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private DataSource dataSource;
	
    public void setDataSource(DataSource dataSource) 
    {
    	this.dataSource = dataSource;
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
	
	protected String databaseName;
	protected String versionTableName;
	protected String versionColumnName;
	protected String sqlFilesLocationPattern;

	/**
	 * @return the databaseName
	 */
	public String getDatabaseName()
	{
		return databaseName;
	}

	/**
	 * @param databaseName the databaseName to set
	 */
	public void setDatabaseName(String databaseName)
	{
		this.databaseName = databaseName;
	}

	/**
	 * @return the versionTableName
	 */
	public String getVersionTableName()
	{
		return versionTableName;
	}

	/**
	 * @param versionTableName the versionTableName to set
	 */
	public void setVersionTableName(String versionTableName)
	{
		this.versionTableName = versionTableName;
	}

	/**
	 * @return the versionColumnName
	 */
	public String getVersionColumnName()
	{
		return versionColumnName;
	}

	/**
	 * @param versionColumnName the versionColumnName to set
	 */
	public void setVersionColumnName(String versionColumnName)
	{
		this.versionColumnName = versionColumnName;
	}

	/**
	 * @return the sqlFilesLocationPattern
	 */
	public String getSqlFilesLocationPattern()
	{
		return sqlFilesLocationPattern;
	}

	/**
	 * @param sqlFilesLocationPattern the sqlFilesLocationPattern to set
	 */
	public void setSqlFilesLocationPattern(String sqlFilesLocationPattern)
	{
		this.sqlFilesLocationPattern = sqlFilesLocationPattern;
	}

	public void init() throws SQLException
	{
		logger.info("starting datasource initialization");

		// get current schemaversion
		Integer schemaversion = getSchemaVersion();

		logger.info("current datasource schema version is " + schemaversion.toString());

		// Get and sort required sql files (using files defined in sqlFilesLocationPattern of bean configuration)
		List<Resource> sqlResources = sqlResources();
		Collections.sort(sqlResources, new NumberedResourceComparator());
		
		// Loop through sql files
		for(Resource sqlResource: sqlResources)
		{
			// Get version number from sql file
			Integer sqlVersion = Integer.valueOf(sqlResource.getFilename().replaceAll("(\\d*).*", "$1"));

			// If version number is higher than current schemaversion, execute file
			if(sqlVersion > schemaversion)
			{
				// Create populator, add script and execute it.
				final ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
				rdp.setSqlScriptEncoding("UTF-8");
				rdp.addScript(sqlResource);
				rdp.populate(dataSource.getConnection());
				
				// Update schemaversion to the current file
				logger.info("updating datasource schema to version " + sqlVersion);
				final String sql = "UPDATE " + getVersionTableName() + " SET " + getVersionColumnName() + " = " + sqlVersion + ";";
				jdbcTemplate.execute(sql);
				
				// Set schemaversion to current file for possible next iteration
				schemaversion = sqlVersion;
			}
			// if the current schemaversion is the same or higher than the current file, skip it
			else
			{
				logger.info("skipping update of version " + sqlVersion + " as it is not newer than the current schema version " + schemaversion);
			}
		}
		
		logger.info("datasource initialization complete");
	}

	private Integer getSchemaVersion()
	{
		logger.info("checking if schemaversion table exists for database '" + getDatabaseName() + "' and table name '" + getVersionTableName() + "'");
		String createQuery ="CREATE TABLE :tableName (:columnName integer NOT NULL)";
		String insertQuery ="INSERT INTO :tableName (  :columnName ) VALUES (0)";
		String query2 ="SELECT "+getVersionColumnName()  +"  FROM " + getVersionTableName() ;

		String query = "" +
				"SELECT count(*) " +
				"FROM information_schema.TABLES " +
				"WHERE TABLE_NAME = :tableName";
		
		HashMap values = new HashMap();
		values.put("tableName", getVersionTableName());
		values.put("columnName", getVersionColumnName());
		
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(values);
		Integer versionTableExists = namedParameterJdbcTemplate.queryForObject(query,namedParameters, Integer.class);
		
		// If the table does not exists yet, create it.
		if(versionTableExists == 0)
		{
			logger.info("Creating schemaversion table as it does not exist yet.");
			
			// Create schemaversion table
			namedParameterJdbcTemplate.execute(createQuery, values, null);
			
			// Initialize version to 0
			namedParameterJdbcTemplate.update(insertQuery, values);

			logger.info("Created '" + getVersionTableName() + "' table and initialized version to 0");
		}
		
		logger.info("schemaversion table exists.");

		logger.info("getting schemaversion.");

		// Get and return current schemaversion
		return jdbcTemplate.queryForObject(query2, Integer.class);
	}

	private List<Resource> sqlResources()
	{
	    try
	    {
	        PathMatchingResourcePatternResolver r = new PathMatchingResourcePatternResolver();
	        return asList(r.getResources(getSqlFilesLocationPattern()));
	    }
	    catch (IOException x)
	    {
	    	throw new RuntimeException(x);
	    }
	}

	private class NumberedResourceComparator implements Comparator<Resource>
	{
	    public int compare(Resource a, Resource b)
	    {
	    	Integer i1 =Integer.valueOf(a.getFilename().replaceAll("(\\d*).*", "$1"));
	    	Integer i2 =Integer.valueOf(b.getFilename().replaceAll("(\\d*).*", "$1"));
	    	return i1.compareTo(i2);
	    }
	}
}