// ***************************************************************************
//
// Copyright 2014, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.dao.monitoring;

import nl.prismait.epublisher.java.model.monitoring.MonitorResult;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("databaseMonitor")
public class DatabaseMonitor
{
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public MonitorResult testDatabase()
	{
		MonitorResult result = new MonitorResult("Database");

		try
		{
			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
			SQLQuery query = session.createSQLQuery("SELECT 1;");

			Integer queryResult = (Integer) query.uniqueResult();

			if (queryResult.equals(1))
			{
				result.setSuccess(true);
				result.setStatusMessage("OK");
			}
			else
			{
				result.setSuccess(false);
				result.setStatusMessage("UNKNOWN ERROR");
			}
		}
		catch (Exception e)
		{
			result.setSuccess(false);
			result.setStatusMessage("ERROR: " + e.toString());
		}

		return result;
	}
}
