// ***************************************************************************
//
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
//
//		$HeadURL$
//		$Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.core.persistence;

import org.hibernate.event.spi.AbstractEvent;

public interface EventProcessor {

	void injectNonHibernateManagedProperties(AbstractEvent event);

}
