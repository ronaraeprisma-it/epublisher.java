package nl.prismait.hibernate;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LocalSessionFactoryBean extends org.springframework.orm.hibernate5.LocalSessionFactoryBean {
	private final Logger logger = LoggerFactory.getLogger(getClass());
    private Map<String, DataSource> dataSourceMap;
    private static final String EXCEPTION_1 = "Could not shut down embedded database";  
	private static final String EXCEPTION_2 = "Could not close JDBC Connection on shutdown"; 
	
    @Override
    public void afterPropertiesSet() throws IOException {
        super.afterPropertiesSet();
        Connection con = null;
        
        for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
        	// Try-with-resources to avoid memory leak - Sonarqube fix
            try {
            	con = entry.getValue().getConnection();
                SchemaExport export = new SchemaExport();
                export.setOutputFile(entry.getKey() + "-schema.sql");
                export.setDelimiter(";");

                //this must be changed in order to support create, drop, validate etc
                //export.execute(true, true, false, true);

            } catch (SQLException ex) {
            	logger.info(EXCEPTION_1, ex);
            }
            finally {
				try {
					// Close PreparedStatement and ResultSet to avoid memory leaks - SonarQube fix	
					if (con != null) {
						con.close();
					}
				}
				catch (Exception ex) {
					logger.debug(EXCEPTION_2, ex);
				}
				
			}
        }
    }

    public void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }
}