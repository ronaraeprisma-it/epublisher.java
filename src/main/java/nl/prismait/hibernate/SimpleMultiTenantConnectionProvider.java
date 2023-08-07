package nl.prismait.hibernate;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.mortbay.log.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleMultiTenantConnectionProvider implements  MultiTenantConnectionProvider {

	private DataSource dataSource;

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(final Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(final String tenantIdentifier) throws SQLException {
    	String query ="SET search_path TO " + tenantIdentifier;

        final Connection connection = getAnyConnection();
        	
		try (Statement statement = connection.createStatement()) {
			statement.execute(query);
        } catch (SQLException e) {
            // If an exception occurs while executing the query, close the connection and then rethrow the exception.
            connection.close();
            throw new HibernateException("Could not alter JDBC connection to specified schema, get connection [" + tenantIdentifier + "]",
                                         e);
		}

        return connection;
    }

    public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
    public void releaseConnection(final String tenantIdentifier, final Connection connection) throws SQLException {
        try {
            connection.createStatement().execute("SET search_path TO dummy");
        } catch (SQLException e) {
            // on error, throw an exception to make sure the connection is not returned to the pool.
            // your requirements may differ
            throw new HibernateException(
                    "Could not alter JDBC connection to specified schema release connection [" +
                            tenantIdentifier + "]",
                    e
            );
        } finally {
            connection.close();
        }

    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class aClass) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
	
}
