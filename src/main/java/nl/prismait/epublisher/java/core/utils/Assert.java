package nl.prismait.epublisher.java.core.utils;

public class Assert {

	public static <T> T verifyType(Class<T> type, Object instance) throws AssertionException {
		if (!(instance.getClass().isAssignableFrom(type))) {
			throw new AssertionException("object given is not of expected type");
		}
		return type.cast(instance);
	}

}
