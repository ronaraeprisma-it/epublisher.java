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
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultMergeEventListener;
import org.hibernate.event.spi.MergeEvent;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

import nl.prismait.epublisher.java.core.persistence.EventProcessor;

public class UpdateListener extends DefaultMergeEventListener implements PostInsertEventListener,
		PostUpdateEventListener {

	private static final long serialVersionUID = 5702330209304143229L;

	// @Autowired
	private List<EventProcessor> processors;

	public void setProcessors(List<EventProcessor> processors) {
		this.processors = processors;
	}

	// @Autowired
	// private EventUtil eventUtil;

	@Override
	public void onMerge(MergeEvent event) throws HibernateException {
		super.onMerge(event);

		for (EventProcessor eventProcessor : processors) {
			eventProcessor.injectNonHibernateManagedProperties(event);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onMerge(MergeEvent event, Map copiedAlready) throws HibernateException {
		super.onMerge(event, copiedAlready);

		for (EventProcessor eventProcessor : processors) {
			eventProcessor.injectNonHibernateManagedProperties(event);
		}
	}

	// @Override
	// public void onSaveOrUpdate(SaveOrUpdateEvent event) {
	// eventUtil.injectNonHibernateManagedProperties(event);
	//
	// super.onSaveOrUpdate(event);
	// }

	@Override
	public void onPostInsert(PostInsertEvent event) {
		for (EventProcessor eventProcessor : processors) {
			eventProcessor.injectNonHibernateManagedProperties(event);
		}
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {

		for (EventProcessor eventProcessor : processors) {
			eventProcessor.injectNonHibernateManagedProperties(event);
		}
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		// TODO Auto-generated method stub
		return false;
	}
}
