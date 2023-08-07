package nl.prismait.epublisher.java.model.exception;

public class EPublisherSecurityException extends EpublisherException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EPublisherSecurityException(String message, Exception e) {
		super(message);
		initCause(e);		
	}
	public EPublisherSecurityException(Exception e)
	{
		super(e);
	}

	public EPublisherSecurityException(String message)
	{
		super(message);
	}

}
