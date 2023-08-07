package nl.prismait.epublisher.java.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.prismait.epublisher.java.api.exception.ApiException;
import nl.prismait.epublisher.java.model.api.ApiError;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ApiKeyInterceptor extends HandlerInterceptorAdapter
{
    private final static String API_KEY_HEADER_NAME = "ePublisher-API-Key";

    @Value("${api.key}")
    private String apiKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        // If there is no request header with the name of the API key, throw
        // missing api key error
        if (request.getHeader(API_KEY_HEADER_NAME) == null)
        {
            throw new ApiException(ApiError.MISSING_API_KEY);
        }

        // If the value of the api-key request header is not the same as the one
        // defined in this class (injected from .properties file)
        // then throw invalid api key error.
        if (!request.getHeader(API_KEY_HEADER_NAME).equals(apiKey))
        {
            throw new ApiException(ApiError.INVALID_API_KEY);
        }

        // When we reach this point, the api key was found and is valid so
        // return true to allow the request to be executed
        return true;
    }
}

