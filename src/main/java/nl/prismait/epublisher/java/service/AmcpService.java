package nl.prismait.epublisher.java.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import nl.prismait.epublisher.java.model.config.PropertiesUtil;
import nl.prismait.epublisher.java.model.exception.EpublisherAmcpInvocationException;

@Service("amcpService")
public class AmcpService {
	
	private static final String AUTH_HEADER = "X-Forwarded-User";
	private static final String TENANT_HEADER = "X-Forwarded-Tenant";
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	private String internalBaseUrl = PropertiesUtil.getProperty("dashboard.url");
	
	/**
	 * Invoke an AMCP service on behalf of a user through a GET request.
	 * @param requestUri the URI requested by the user
	 * @param onBehalfOf 
	 * 					the user who sends the request
	 * @param tenantUrl 
	 * @throws EpublisherAmcpInvocationException is thrown in case of any exception 
	 * @return  a string containing the response
	 */
	public String get(String requestUri, String onBehalfOf, String tenantUrl) throws EpublisherAmcpInvocationException 
	{
		logger.info("Calling "+ internalBaseUrl + requestUri + " on behalf of " + onBehalfOf);
		try {
			URL obj;
			
			 obj = new URL(internalBaseUrl + requestUri);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			if(!onBehalfOf.isBlank())
				con.addRequestProperty(AUTH_HEADER, onBehalfOf);
			con.addRequestProperty(TENANT_HEADER, tenantUrl);
			int responseCode = con.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK)
			{
				throw new EpublisherAmcpInvocationException("AMCP remote error" + responseCode + " for request" + requestUri);
			}
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			return response.toString();
		}
		catch (Exception e)
		{
			logger.error("AMCP remote error for request " + requestUri, e);
			throw new EpublisherAmcpInvocationException("AMCP generic error for request" + requestUri, e);
		}
	}

	/**
	 * Invoke an AMCP service on behalf of a user through a GET request.
	 * @param sendRequest String	
	 * @param onBehalfOf the user who sends the request
	 * @throws EpublisherAmcpInvocationException  is thrown in case of any exception 
	 */
	public void post(String sendRequest, String onBehalfOf) throws EpublisherAmcpInvocationException 
	{
		try {
			URL obj = new URL(internalBaseUrl + sendRequest);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.addRequestProperty(AUTH_HEADER, onBehalfOf);
			int resultCode = con.getResponseCode();
			if (resultCode != 200)
			{
				throw new EpublisherAmcpInvocationException("AMCP remote error for request" + sendRequest);
			}
		}
		catch (Exception e)
		{
			logger.error("AMCP remote error for request " + sendRequest, e);
			throw new EpublisherAmcpInvocationException("AMCP generic error for request" + sendRequest, e);
		}
	}

}
