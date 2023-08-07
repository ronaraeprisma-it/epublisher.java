package nl.prismait.epublisher.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.prismait.epublisher.java.dao.ITenantDAO;
import nl.prismait.epublisher.java.model.dashboard.schema.Tenant;

@Service("tenantService")
public class TenantService implements ITenantService{
	
	@Autowired
	public ITenantDAO tenantDao;

	@Override
	public List<Tenant> getAllTenants() {
		return tenantDao.getAllTenants() ;
	}
	
	@Override
	public List<Tenant> getTotalTenants() {
		return tenantDao.getTotalTenants() ;
	}

	@Override
	public String getTenantImageLocation(String url) {
		return tenantDao.getTenantImageLocation(url) ;
	}

}
