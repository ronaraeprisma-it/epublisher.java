// ***************************************************************************
//
// Copyright 2014, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.model.monitoring;

public class MonitorResult
{
	private String serviceName;
	private Boolean success = false;
	private String statusMessage;

	public MonitorResult(String serviceName)
	{
		super();
		setServiceName(serviceName);
	}

	public MonitorResult(String serviceName, Boolean success, String statusMessage)
	{
		super();
		setServiceName(serviceName);
		setSuccess(success);
		setStatusMessage(statusMessage);
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public Boolean getSuccess()
	{
		return success;
	}

	public void setSuccess(Boolean success)
	{
		this.success = success;
	}

	public String getStatusMessage()
	{
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage)
	{
		this.statusMessage = statusMessage;
	}

	public String toString()
	{
		return new StringBuilder().append(getServiceName()).append(" ").append(getSuccess()).append(" ").append(getStatusMessage()).toString();
	}
}
