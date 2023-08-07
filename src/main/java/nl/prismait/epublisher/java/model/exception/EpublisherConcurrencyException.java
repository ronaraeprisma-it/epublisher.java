package nl.prismait.epublisher.java.model.exception;

public class EpublisherConcurrencyException extends EpublisherException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EpublisherConcurrencyException(String message, Exception e) {
		super(message);
		initCause(e);		
	}
	public EpublisherConcurrencyException(Exception e)
	{
		super(e);
	}

	public EpublisherConcurrencyException(String message)
	{
		super(message);
	}




}
