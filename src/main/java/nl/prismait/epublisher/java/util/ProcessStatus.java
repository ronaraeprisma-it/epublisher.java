package nl.prismait.epublisher.java.util;

public enum ProcessStatus {

	READY("ready"),
	PROCESSING("processing"),
	FAILED("failed"),
	COMPLETED("completed");


	private String value;
	private ProcessStatus(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
