// ***************************************************************************
//
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
//
//		$HeadURL$
//		$Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.dao.persitenceutil;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.spi.LoadEvent;

import nl.prismait.epublisher.java.core.persistence.EventProcessor;

public class LoadListener extends DefaultLoadEventListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 6300867310472055606L;

	// @Autowired
	private List<EventProcessor> processors;

	public void setProcessors(List<EventProcessor> processors) {
		this.processors = processors;
	}

	// @Autowired
	// EventUtil eventUtil;

	@Override
	public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
		super.onLoad(event, loadType);
		for (EventProcessor eventProcessor : processors) {
			eventProcessor.injectNonHibernateManagedProperties(event);
		}
	}
}
