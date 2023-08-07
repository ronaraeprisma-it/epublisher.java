package nl.prismait.epublisher.java.dao;

import java.util.List;
import java.util.Map;

import nl.prismait.epublisher.java.model.dashboard.schema.Tenant;

public interface ITenantDAO{

	List<Tenant> getAllTenants();
	
	Map<String, String> getOrganizationIdFromTenant(String tenantId);
	
	public void addLocalTenantToDashboard(String tenantID, String sessionId);

	String getTenantImageLocation(String tenantUrl);
	
	public Tenant getTenantByTenantId(String tenantId);

	public List<Tenant> getTotalTenants();
}
