package nl.prismait.epublisher.java.business;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.prismait.epublisher.java.model.dto.GenericResultWrapper;
import nl.prismait.epublisher.java.model.exception.EpublisherException;

@Controller
@RequestMapping("editor/util")
public class EPublisherUtilController {

	
	/**
	 * Returns the exception code message 
	 * /BlazeDS/editor/util/echo/{code} GET
	 * 
	 * @param code
	 * 			error code from front end
	 * @return GenericResultWrapper with status and error message
	 * @throws EpublisherException if any unhandled exceptions occur
	 */
	@RequestMapping(method = RequestMethod.GET, value = "echo/{code}")
	@ResponseBody
	public ResponseEntity<GenericResultWrapper> echo(@PathVariable Integer code) throws EpublisherException
	{
		HttpStatus status = null;
		String message = "This is response for code : "+ code;
		switch(code){
		case 409: 
				status = HttpStatus.CONFLICT;
				break;
		case 200:
				status = HttpStatus.OK;
				break;
		case 400:
				status = HttpStatus.BAD_REQUEST;
				break;
		case 401:
				status = HttpStatus.UNAUTHORIZED;
				break;
		case 500:
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
		default:
				status = HttpStatus.NOT_FOUND;
				message = "Requested Page/code not found";
				break;
				
		}
		return new ResponseEntity<GenericResultWrapper>(
				new GenericResultWrapper(status == null ?null:status.value(), status == null ?null:status.name(),message), status);
	}
}
