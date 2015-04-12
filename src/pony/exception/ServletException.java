package pony.exception;

public class ServletException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7151379566314715096L;
	
	public ServletException(final int _errorCode) {
		super(_errorCode);
	}

	public ServletException(
			final String _message,
			final int _errorCode) {
		super(_message, _errorCode);
	}
}