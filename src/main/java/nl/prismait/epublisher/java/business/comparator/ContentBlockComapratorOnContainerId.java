package nl.prismait.epublisher.java.business.comparator;

import java.util.Comparator;

import nl.prismait.epublisher.java.model.narrowcasting.ContentBlock;

public class ContentBlockComapratorOnContainerId implements Comparator<ContentBlock> 
{
	@Override
	public int compare(ContentBlock arg0, ContentBlock arg1) {
		return Integer.valueOf(arg0.getContainerId()).compareTo(Integer.valueOf(arg1.getContainerId()));
	}

}
