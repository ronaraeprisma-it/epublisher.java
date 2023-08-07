package nl.prismait.epublisher.java.model.exception;

public class EpublisherNotFoundException extends EpublisherException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EpublisherNotFoundException(String message, Exception e) {
		super(message);
		initCause(e);		
	}
	public EpublisherNotFoundException(Exception e)
	{
		super(e);
	}

	public EpublisherNotFoundException(String message)
	{
		super(message);
	}




}
