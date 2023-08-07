package nl.prismait.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import nl.prismait.epublisher.java.dao.SessionIndentifierDAO;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import nl.prismait.epublisher.java.dao.ITenantDAO;

@org.springframework.stereotype.Component

public class WebSessionCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {
	private static final Logger LOG = LoggerFactory.getLogger(WebSessionCurrentTenantIdentifierResolver.class);
	private final DataSource dataSource;

	@Autowired
	public WebSessionCurrentTenantIdentifierResolver(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	public HttpServletRequest request;

	@Autowired
	public SessionIndentifierDAO sessionDao;

	@Autowired
	public ITenantDAO tenantDAO;

	private static String SCHEDULED_TASK_TENANTID = "";

	private static final String EXCEPTION_1 = "Could not shut down embedded database";
	private static final String EXCEPTION_2 = "Could not close JDBC Connection on shutdown";

	@Override
	public String resolveCurrentTenantIdentifier() {

		String tenantId = resolveTenantByHttpSession();

		LOG.info(MessageFormat.format("Found TenantId=\"{0}\"", tenantId));

		return tenantId;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}

	/**
	 * Get tenantId in the session attribute KEY_TENANTID_SESSION
	 *
	 * @return TenantId on KEY_TENANTID_SESSION
	 */
	public String resolveTenantByHttpSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		String tenant = null;
		if (attr != null) {

			//for testing
			//tenant = getTenantIdByURL("epub.zr.dev.prisma-it.com");
			var test=attr.getRequest().getServerName();
			tenant = getTenantIdByURL(attr.getRequest().getServerName());

		if(tenant != null) return tenant;
		}

		return "";

	}

//	public String resolveTenantByHttpSession(){
//		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//
//		String tenant = null;
//		final String tenantName = attr!=null?(attr.getRequest()!=null?attr.getRequest().getParameter("company"):""):"";
//
//
//		// If request is coming from ePublisher Mobile App
//		// and is connecting for the first time
//		if (tenantName != null && !tenantName.isEmpty()) {
//			tenant = sessionDao.getTenantByName(tenantName);
//			attr.setAttribute("tenantId", tenant, RequestAttributes.SCOPE_REQUEST);
//		}
//
//
//		// this is applicable only for process that does not involve session -
//		// narrowcasting screens and newsletter images
//		if (tenant != null) {
//			return tenant;
//		} else if (tenant == null && attr != null) {
//			tenant = sessionDao.getTenantIdByURL(attr.getRequest().getServerName());
//			if(tenant != null) return tenant;
//		}
//
//		if (!SCHEDULED_TASK_TENANTID.equals("")) {
//			// otherwise return default tenant
//			LOG.trace("Tenant resolved in session is: " + SCHEDULED_TASK_TENANTID);
//			return SCHEDULED_TASK_TENANTID;
//		}
//		return "";
//	}

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
			LOG.info(EXCEPTION_1, ex);
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
				LOG.debug(EXCEPTION_2, ex);
			}
		}

		return tenantId;
	}

	public static void setScheduledTaskTenantid(String scheduledTaskTenantid) {
		SCHEDULED_TASK_TENANTID = scheduledTaskTenantid;
	}
}