package nl.prismait.epublisher.java.model.exception;

public class EpublisherAmcpInvocationException extends EpublisherException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EpublisherAmcpInvocationException(String message, Exception e) {
		super(message);
		initCause(e);		
	}
	public EpublisherAmcpInvocationException(Exception e)
	{
		super(e);
	}

	public EpublisherAmcpInvocationException(String message)
	{
		super(message);
	}




}
