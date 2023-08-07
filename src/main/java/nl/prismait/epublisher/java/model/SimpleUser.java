// ***************************************************************************
//
// Copyright 2014, Prisma-IT (www.prisma-it.com)
// All rights reserved
//
// $HeadURL$
// $Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.model;

import java.util.ArrayList;
import java.util.List;

public class SimpleUser
{
	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneNumber;
	private String email;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public static List<SimpleUser> convertObjectsToSimpleUsers(List<Object[]> objects)
	{
		List<SimpleUser> result = new ArrayList<SimpleUser>();

		for (Object[] object : objects)
		{
			SimpleUser converted = new SimpleUser();

			converted.setFirstName((String) object[0]);
			converted.setMiddleName((String) object[2]);
			converted.setLastName((String) object[1]);
			converted.setPhoneNumber((String) object[3]);
			converted.setEmail((String) object[4]);

			result.add(converted);
		}

		return result;
	}
}
