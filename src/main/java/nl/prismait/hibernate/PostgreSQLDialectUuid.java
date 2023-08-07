// ***************************************************************************
// 
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL$
//		$Id$
// 
// ***************************************************************************
package nl.prismait.hibernate;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL82Dialect;

public class PostgreSQLDialectUuid extends PostgreSQL82Dialect {
	
	public PostgreSQLDialectUuid() {
		super();
		registerColumnType(Types.OTHER, "uuid");
	}

}
