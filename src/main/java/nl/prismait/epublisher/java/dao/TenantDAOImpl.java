package nl.prismait.epublisher.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.prismait.epublisher.java.model.dashboard.schema.Tenant;



@Component("tenantDAO")
public class TenantDAOImpl implements ITenantDAO
{
	
	private static final String EXCEPTION_1 = "Could not shut down embedded database";  
	private static final String EXCEPTION_2 = "Could not close JDBC Connection on shutdown";  
	@Autowired
	private DataSource dataSource;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	@Override
	public List<Tenant> getAllTenants() {

		List<Tenant> tenantList = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		
	    StringBuilder query =new StringBuilder("Select t.tenantid , t.tenanturl from dashboard.um_tenant_organisation t inner join dashboard.um_tenant_outputchannel top on t.id = top.tenantid"); 
      			query.append(" inner join dashboard.outputchannelconfigurator oc on top.outputchannelid = oc.id where oc.name = 'newsletterNS' ");
      			query.append("OR oc.name = 'facebook' OR oc.name = 'landingpage' OR oc.name = 'linkedin' OR oc.name = 'internet'");
	    
		try
		{
			con = dataSource.getConnection();
			rs = con.createStatement().executeQuery(query.toString());
			
    	    while (rs.next()) {
    	    	Tenant tenant = new Tenant();
    	    	tenant.setTenantid(rs.getString(1));
    	    	tenant.setTenantUrl(rs.getString(2));
    	    	tenantList.add(tenant);
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
				
				if (rs != null) {
					rs.close();
				}
			}
			catch (Exception ex) {
				logger.debug(EXCEPTION_2, ex);
			}
		}

		return tenantList;
	}
	
	@Override
	public Map<String, String> getOrganizationIdFromTenant(String tenantId) {

		Map<String, String> tenantMap = new HashMap<>();
	    ResultSet rs = null;
	    StringBuilder query = new StringBuilder("Select organisationid , tenanturl from dashboard.um_tenant_organisation where tenantid = ?");
	    Connection con = null;
	    PreparedStatement statement = null;
	    
	    try
		{
	    		con = dataSource.getConnection();
	    		statement = con.prepareStatement(query.toString());
	    		
				statement.setString(1, tenantId );
				rs = statement.executeQuery();

	    	  while (rs.next()) {
	    		  tenantMap.put("organizationId", rs.getString(1));
	    		  tenantMap.put("tenantUrl", rs.getString(2));
	    	    }  
	    	  
	    }
	    catch (SQLException ex) {
	      logger.info(EXCEPTION_1, ex);
	    }
	    finally {
	        try {
        	    // Close Result sets to avoid memory leak - SonarQube fix
	        	if (con != null) {
					con.close();
				}
	        	
	        	if (statement != null) {
					statement.close();
				}
	        	
	        	if (rs != null) {
					rs.close();
				}	
	        }
	        catch (Exception ex) {
	          logger.debug(EXCEPTION_2, ex);
	        }
	    }
		return tenantMap;
	}
	
	@Override
	public void addLocalTenantToDashboard(String tenantID, String sessionId){
		ResultSet rSet = null;
		PreparedStatement statement = null;
		Connection con = null;
		
		String query1 = "SELECT * FROM dashboard.um_session WHERE sessionid = ?";
		String query2 = "INSERT INTO dashboard.um_session (tenantid, sessionid, useremailaddress) VALUES ( ?, ?, ? )";
		
		try 
		{
			con = dataSource.getConnection();
			statement = con.prepareStatement(query1);
			
			statement.setString(1, sessionId );
			rSet = statement.executeQuery();
			
			if(!rSet.next()) {
				statement = con.prepareStatement(query2);
				statement.setString(1, tenantID);
				statement.setString(2, sessionId );
				statement.setString(3, "hosting@prisma-it.com");
				statement.executeQuery();
			}
			
		} catch (SQLException e) {
			logger.info("Error local dev tenant session: " + e);
		}
		finally {
		        try {
		        	// Close PreparedStatement and ResultSet to avoid memory leaks - SonarQube fix	
		        	if (con != null) {
						con.close();
					}
		        	
		        	if (statement != null) {
						statement.close();
					}
		        	
					if (rSet != null) {
						rSet.close();
					}
		        }
		        catch (Exception ex) {
		          logger.debug(EXCEPTION_2, ex);
		        }
		}

	}

	@Override
	public String getTenantImageLocation(String tenantUrl) {

			String imageLocation = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Connection con = null;
			
			StringBuilder query = new StringBuilder("SELECT imageLocation FROM dashboard.um_tenant_organisation where tenanturl = ? OR tenantname=? OR tenantid = ?");
			
			try
			{
				con = dataSource.getConnection();
				stmt = con.prepareStatement(query.toString());
				stmt.setString(1, tenantUrl);
				stmt.setString(2, tenantUrl);
				stmt.setString(3, tenantUrl);
				rs = stmt.executeQuery();
				while (rs.next()) {
					imageLocation = rs.getString(1);
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

			return imageLocation;
	}
	
	@Override
	public Tenant getTenantByTenantId(String tenantId) {
		Tenant tenant = new Tenant();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt;
		
	    StringBuilder query =new StringBuilder("Select t.tenantid ,t.tenanturl, t.tenantname from dashboard.um_tenant_organisation WHERE tenantid = ?"); 
	    
		try
		{
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, tenantId);
			rs = pstmt.executeQuery();
			
    	    while (rs.next()) {
    	    	tenant.setTenantid(rs.getString(1));
    	    	tenant.setTenantUrl(rs.getString(2));
    	    	tenant.setTenantname(rs.getString(3));
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
				
				if (rs != null) {
					rs.close();
				}
			}
			catch (Exception ex) {
				logger.debug(EXCEPTION_2, ex);
			}
		}

		return tenant;
	}
	
	@Override
	public List<Tenant> getTotalTenants() {

		List<Tenant> tenantList = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		
	    StringBuilder query =new StringBuilder("Select DISTINCT t.tenantid , t.tenanturl from dashboard.um_tenant_organisation t "
	    		+ " inner join dashboard.um_tenant_outputchannel top on t.id = top.tenantid"
	    		+ " inner join dashboard.um_tenant_provider tp on t.id = tp.tenantid"); 
	    
		try
		{
			con = dataSource.getConnection();
			rs = con.createStatement().executeQuery(query.toString());
			
    	    while (rs.next()) {
    	    	Tenant tenant = new Tenant();
    	    	tenant.setTenantid(rs.getString(1));
    	    	tenant.setTenantUrl(rs.getString(2));
    	    	tenantList.add(tenant);
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
				
				if (rs != null) {
					rs.close();
				}
			}
			catch (Exception ex) {
				logger.debug(EXCEPTION_2, ex);
			}
		}

		return tenantList;
	}
}
