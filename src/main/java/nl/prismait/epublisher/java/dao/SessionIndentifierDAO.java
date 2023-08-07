package nl.prismait.epublisher.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sessionIdentifierDAO")
public class SessionIndentifierDAO {


	@Autowired
	private DataSource dataSource;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String EXCEPTION_1 = "Could not shut down embedded database";  
	private static final String EXCEPTION_2 = "Could not close JDBC Connection on shutdown";  
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getTenantByURL(String tenantUrl) {

		String tenantId = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		try
		{
			con = dataSource.getConnection();
//			stmt = con.prepareStatement("SELECT tenantId FROM dashboard.um_session where sessionid = ?");
			stmt = con.prepareStatement("SELECT tenantId FROM dashboard.um_session where sessionid = ?");
			stmt.setString(1, tenantUrl);
			rs = stmt.executeQuery();
			while (rs.next()) {
				tenantId = rs.getString(1);
			}
		}
		catch (SQLException ex) {
			logger.info(EXCEPTION_1, ex);
		}
		finally {
				try {
					// Close PreparedStatement and ResultSet to avoid memory leaks - SonarQube fix		
					if (con != null) {
						con.close();
					}
					
					if (stmt != null) {
						stmt.close();
					}
					
					if (rs != null) {
						rs.close();
					}
				}
				catch (Exception ex) {
					logger.debug(EXCEPTION_2, ex);
				}
		}

		return tenantId;
	}
	
	public String getTenantIdByURL(String tenantUrl) {
		System.out.println(tenantUrl);
		String tenantId = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		try
		{
			con = dataSource.getConnection();
			stmt = con.prepareStatement("SELECT tenantId FROM dashboard.um_tenant_organisation where tenanturl = ?");
			stmt.setString(1, tenantUrl);
			rs = stmt.executeQuery();
			while (rs.next()) {
				tenantId = rs.getString(1);
			}  
		}
		catch (SQLException ex) {
			logger.info(EXCEPTION_1, ex);
		}
		finally {
			try {
				// Close PreparedStatement and ResultSet to avoid memory leaks - SonarQube fix	
				if (con != null) {
					con.close();
				}
				
				if (stmt != null) {
					stmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			}
			catch (Exception ex) {
				logger.debug(EXCEPTION_2, ex);
			}
		}

		return tenantId;
	}
	
	
	public String getTenantByName(String tenantName) {
		
		String tenantId = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		try
		{
			con = dataSource.getConnection();
			stmt = con.prepareStatement("SELECT tenantId FROM dashboard.um_tenant_organisation where tenantname = ?");
			stmt.setString(1, tenantName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				tenantId = rs.getString(1);
			}  
		}
		catch (SQLException ex) {
			logger.info(EXCEPTION_1, ex);
		}
		finally {
			try {
				// Close PreparedStatement and ResultSet to avoid memory leaks - SonarQube fix
				if (con != null) {
					con.close();
				}
				
				if (stmt != null) {
					stmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			}
			catch (Exception ex) {
				logger.debug(EXCEPTION_2, ex);
			}
		}

		return tenantId;
		
	}
	
	public void saveSessionId(String sessionId, String email, String tenantId) {
		PreparedStatement stmt = null;
		Connection con = null;
		
		try
		{
				con = dataSource.getConnection();
				stmt = con.prepareStatement("INSERT into dashboard.um_session (sessionId,userEmailAddress,tenantId,updateTimestamp) VALUES (?,?,?,?)");
				stmt.setString(1, sessionId);
				stmt.setString(2, email);
				stmt.setString(3, tenantId);
				stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				
				stmt.executeUpdate();
		}
		catch (SQLException ex) {
			logger.info(EXCEPTION_1, ex);
		}
		finally {
			try {
				// Close PreparedStatement and ResultSet to avoid memory leaks - SonarQube fix	
				if (con != null) {
					con.close();
				}
				
				if (stmt != null) {
					stmt.close();
				}
			}
			catch (Exception ex) {
				logger.debug(EXCEPTION_2, ex);
			}
		}
	}
}
