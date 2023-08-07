package nl.prismait.epublisher.java.model.narrowcasting;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import nl.prismait.epublisher.java.model.PlaylistScreengroupAndPublication;
import nl.prismait.epublisher.java.model.config.PropertiesUtil;
import nl.prismait.epublisher.java.model.exception.EpublisherAmcpInvocationException;
import nl.prismait.epublisher.java.service.AmcpService;

public class BroadcastCacheWrapper {

	private List<Assets> assets = new ArrayList<Assets>();
	private String lastPublishedDate;



	public List<Assets> getAssets() {
		return assets;
	}
	public void setAssets(List<Assets> assets) {
		this.assets = assets;
	}
	public String getLastPublishedDate() {
		return lastPublishedDate;
	}
	public void setLastPublishedDate(String lastPublishedDate) {
		this.lastPublishedDate = lastPublishedDate;
	}

	public static BroadcastCacheWrapper convertObjectsToBroadcastCache(List<Object[]> objects)
	{
		List<Assets> result = new ArrayList<Assets>();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Date lastDate = null;
		String output = "";
		List<Date> playlistsAndBroadcastsPublicationDates = new ArrayList<Date>();
		String base = request!=null?PropertiesUtil.getProperty("uri.schema")+"://"+request.getServerName(): " ";
		BroadcastCacheWrapper cacheWrapper = new BroadcastCacheWrapper();
		if(!objects.isEmpty()) {
			for (Object[] object : objects)
			{
				if(object[0] != null) {
					Assets asset = new Assets();
					asset.setBroadcastId((Integer) object[0]);
					asset.setName((String) object[1]);
					asset.setType((String) object[2]);
					if(asset.getType().equals("EPublisherVideo")) {
						//if video source is brightcove generate the static url
						if(((String) object[9]).equalsIgnoreCase("brightcove") && ((String) object[4]) != null) {
							//get the brightcove details from organisation 
							asset.setLink(getBrightcoveVideoLink((String) object[4]));
						}else {
							asset.setLink((String) object[3]);
						}
						asset.setAssetId((String) object[4]);
					}
					else if(asset.getType().equals("EPublisherNarrowcastingImage")) {
						if((object[5]) != null) {
							asset.setAssetId(((Integer) object[5]).toString());
							asset.setLink( ((String) object[6] !=null &&  !((String) object[6]).contains(base) )?new StringBuffer("https://" + request.getServerName() ).append(request.getContextPath()).append( (String) object[6]).toString():(String) object[6]);
						}
					}
					if((Date)object[8]!=null) {
						asset.setLastChangedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)object[8]));

						if(lastDate == null) {
							lastDate = (Date)object[8];
						}

						playlistsAndBroadcastsPublicationDates.add((Date)object[8]);
					} else {
						asset.setLastChangedDate("");
					}
					if((Date)object[7] != null) {
						playlistsAndBroadcastsPublicationDates.add((Date)object[7]);
					}

					result.add(asset);
				}
			}

			for(Date date : playlistsAndBroadcastsPublicationDates) {
				if(lastDate != null && lastDate.before(date)) {
					lastDate = date;
				}
			}
			output =lastDate!=null? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastDate): null;

			cacheWrapper.setAssets(result);
			cacheWrapper.setLastPublishedDate(output);
		}
		return cacheWrapper;
	}
	public static String getBrightcoveVideoLink(String assetId) {
		AmcpService amcpService = new AmcpService();
		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = sra!=null? sra.getRequest(): null;   
		final String result=(Optional.of(amcpService.get("/brightcoveconnector:token/getStaticToken","",req.getServerName()))).orElseThrow(()-> new EpublisherAmcpInvocationException("Error connecting with brightcove plugin"));
		final  JSONObject json = new JSONObject(result);
		return  MessageFormat.format((String)  PropertiesUtil.getProperty("brightcove.video.link"), json.get("ACCOUNTID"), assetId,json.get("TOKEN"));

	}

}
