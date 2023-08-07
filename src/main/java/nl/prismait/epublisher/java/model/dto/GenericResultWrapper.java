package nl.prismait.epublisher.java.model.dto;

public class GenericResultWrapper {

	private int statusCode;
	private String statusText;
	private Object result;
	private String errorDetail;
	private String stackTrace;

	public GenericResultWrapper() {

	}

	public GenericResultWrapper(int statusCode, String statusText, Object result, String errorDetail,
			String stackTrace) {
		super();
		this.statusCode = statusCode;
		this.statusText = statusText;
		this.result = result;
		this.errorDetail = errorDetail;
		this.stackTrace = stackTrace;
	}

	public GenericResultWrapper(int statusCode, String statusText, String errorDetail) {
		super();
		this.statusCode = statusCode;
		this.statusText = statusText;
		this.errorDetail = errorDetail;
	}

	public GenericResultWrapper(int statusCode, String statusText, String errorDetail, String stackTrace) {
		super();
		this.statusCode = statusCode;
		this.statusText = statusText;
		this.errorDetail = errorDetail;
		this.stackTrace = stackTrace;
	}

	public GenericResultWrapper(int statusCode, String statusText, Object result) {
		super();
		this.statusCode = statusCode;
		this.statusText = statusText;
		this.result = result;
	}

	public GenericResultWrapper(int statusCode, String statusText) {
		super();
		this.statusCode = statusCode;
		this.statusText = statusText;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
}
