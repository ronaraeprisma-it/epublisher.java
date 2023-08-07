package nl.prismait.epublisher.java.model.exception;

public class EpublisherFileSizeException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EpublisherFileSizeException(String message, Exception e) {
		super(message);
		initCause(e);		
	}

	public EpublisherFileSizeException(Exception e)
	{
		super(e);
	}

	public EpublisherFileSizeException(String message)
	{
		super(message);
	}

}
