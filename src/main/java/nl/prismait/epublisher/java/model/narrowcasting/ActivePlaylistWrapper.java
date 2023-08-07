package nl.prismait.epublisher.java.model.narrowcasting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ActivePlaylistWrapper {
	private Integer playlistId;
	private String lastPublishedDate;
	
	public Integer getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(Integer playlistId) {
		this.playlistId = playlistId;
	}
	public String getLastPublishedDate() {
		return lastPublishedDate;
	}
	public void setLastPublishedDate(String lastPublishedDate) {
		this.lastPublishedDate = lastPublishedDate;
	}
	
	public static List<ActivePlaylistWrapper> convertObjectsToPlaylistCache(List<Object[]> objects)
	{
		List<ActivePlaylistWrapper> cacheWrapperList = new ArrayList<ActivePlaylistWrapper>();
		if(!objects.isEmpty()) {
			for (Object[] object : objects)
			{
				ActivePlaylistWrapper cacheWrapper = new ActivePlaylistWrapper();
				cacheWrapper.setLastPublishedDate((Date)object[1] != null?new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)object[1]):null);
				cacheWrapper.setPlaylistId((Integer) object[0]);
				cacheWrapperList.add(cacheWrapper);
				
			}
		}
		return cacheWrapperList;
	}
	
	
}
