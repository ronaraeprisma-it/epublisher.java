package nl.prismait.epublisher.java.model.exception;

public class EpublisherException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EpublisherException(String message, Exception e) {
		super(message);
		initCause(e);		
	}

	public EpublisherException(Exception e)
	{
		super(e);
	}

	public EpublisherException(String message)
	{
		super(message);
	}



}
