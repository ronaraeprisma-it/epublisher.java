// ***************************************************************************
// 
//		Copyright 2013, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL: https://svn.prisma-it.com/epublisher/ePublisherJavaBlazeDSWeb/trunk/src/main/java/nl/prismait/epublisher/java/api/exception/ApiException.java $
//		$Id: ApiException.java 2935 2014-09-29 10:05:59Z aleksandar $
// 
// ***************************************************************************
package nl.prismait.epublisher.java.api.exception;

import nl.prismait.epublisher.java.model.api.ApiError;

public class ApiException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ApiError apiError;

	public void setApiError(ApiError apiError) {
		this.apiError = apiError;
	}

	public ApiError getApiError() {
		return apiError;
	}
	
	public ApiException(ApiError apiError) {
		super(apiError.getErrorMessage());
		setApiError(apiError);
	}
}
