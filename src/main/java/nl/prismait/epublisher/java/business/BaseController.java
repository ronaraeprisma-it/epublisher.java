package nl.prismait.epublisher.java.business;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import nl.prismait.epublisher.java.model.dto.GenericResultWrapper;
import nl.prismait.epublisher.java.model.exception.EPublisherSecurityException;
import nl.prismait.epublisher.java.model.exception.EpublisherConcurrencyException;
import nl.prismait.epublisher.java.model.exception.EpublisherException;
import nl.prismait.epublisher.java.model.exception.EpublisherNotFoundException;
import nl.prismait.epublisher.java.model.exception.EpublisherFileSizeException;

public class BaseController {

	@ExceptionHandler(EpublisherException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<GenericResultWrapper> handleException(Exception ex) 
	{
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<GenericResultWrapper>(
				new GenericResultWrapper(status.value(), status.name(), ex.getMessage()), status);
	}

	@ExceptionHandler(EpublisherConcurrencyException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ResponseEntity<GenericResultWrapper> conflictHandlerException(Exception ex) 
	{
		HttpStatus status = HttpStatus.CONFLICT;;
		return new ResponseEntity<GenericResultWrapper>(
				new GenericResultWrapper(status.value(), status.name(), ex.getMessage()), status);
	}

	@ExceptionHandler(EPublisherSecurityException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ResponseEntity<GenericResultWrapper> securityHandlerException(Exception ex) 
	{
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return new ResponseEntity<GenericResultWrapper>(
				new GenericResultWrapper(status.value(), status.name(), ex.getMessage()), status);
	}

	@ExceptionHandler(EpublisherNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<GenericResultWrapper> notFoundException(Exception ex) 
	{
		HttpStatus status = HttpStatus.NOT_FOUND;
		return new ResponseEntity<GenericResultWrapper>(
				new GenericResultWrapper(status.value(), status.name(), ex.getMessage()), status);
	}
	
	@ExceptionHandler(EpublisherFileSizeException.class)
	@ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
	@ResponseBody
	public ResponseEntity<GenericResultWrapper> payloadTooLargeException(Exception ex) 
	{
		HttpStatus status = HttpStatus.PAYLOAD_TOO_LARGE;
		return new ResponseEntity<GenericResultWrapper>(
				new GenericResultWrapper(status.value(), status.name(), ex.getMessage()), status);
	}

	protected GenericResultWrapper createResult(Object object) 
	{
		GenericResultWrapper result = new GenericResultWrapper(HttpStatus.OK.value(), HttpStatus.OK.name());
		result.setResult(object);
		return result;
	}
	
	
}
