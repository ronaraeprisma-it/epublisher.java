package nl.prismait.epublisher.java.service;

import java.util.List;

import nl.prismait.epublisher.java.model.dashboard.schema.Tenant;

public interface ITenantService {
	//this gets the tenants with newsletter outputchannel enabled (not also narrowcasting)
	public List<Tenant> getAllTenants();
	//this get total tenants
	public List<Tenant> getTotalTenants();
	String getTenantImageLocation(String url);

}
