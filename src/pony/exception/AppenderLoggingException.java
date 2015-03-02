package pony.exception;

public class AppenderLoggingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppenderLoggingException() {
		super();
	}

	public AppenderLoggingException(String message, Throwable cause) {
		super(message, cause);
	}

}
