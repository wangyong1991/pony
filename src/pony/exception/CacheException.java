package pony.exception;

public class CacheException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6227234672259099046L;

	public CacheException(final int _errorCode) {
		super(_errorCode);
	}

	public CacheException(
			final String _message,
			final int _errorCode) {
		super(_message, _errorCode);
	}
}
