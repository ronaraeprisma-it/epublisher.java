// ***************************************************************************
// 
//		Copyright 2013, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL: https://svn.prisma-it.com/epublisher/ePublisherJavaBlazeDSWeb/trunk/src/main/java/nl/prismait/epublisher/java/model/api/ApiError.java $
//		$Id: ApiError.java 2935 2014-09-29 10:05:59Z aleksandar $
// 
// ***************************************************************************
package nl.prismait.epublisher.java.model.api;

import org.springframework.http.HttpStatus;

public enum ApiError {
	
	NOT_IMPLEMENTED(			0, HttpStatus.BAD_REQUEST.value(),			"This request has not been implemented yet"),
	INVALID_API_KEY(			1, HttpStatus.UNAUTHORIZED.value(),			"Invalid API key"),
	MISSING_API_KEY(			2, HttpStatus.UNAUTHORIZED.value(),			"Missing API key"),
	PUBLICATION_NOT_FOUND(		3, HttpStatus.NOT_FOUND.value(),			"No publication available with that ID"),
	PAGE_UNAVAILABLE(			4, HttpStatus.GONE.value(),					"Requested page is unavailable"),
	SERVICE_UNAVAILABLE(		5, HttpStatus.SERVICE_UNAVAILABLE.value(),	"The service is unavailable");
		
	private Integer errorCode;
	private Integer httpStatusCode;
	private String errorMessage;
 
	private ApiError(Integer errorCode, Integer httpStatusCode, String errorMessage) 
	{
		this.errorCode = errorCode;
		this.httpStatusCode = httpStatusCode;
		this.errorMessage = errorMessage;
	}
 
	public Integer getErrorCode() 
	{
		return errorCode;
	}

	public Integer getHttpStatusCode() 
	{
		return httpStatusCode;
	}
	
	public String getErrorMessage() 
	{
		return errorMessage;
	}
}
