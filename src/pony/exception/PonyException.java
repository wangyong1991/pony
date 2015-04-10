package pony.exception;

public class PonyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5642245495645361600L;
	private int errorCode;
	public final int getErrorCode() {
		return this.errorCode;
	}
	public final void setErrorCode(int _errorCode) {
		this.errorCode = _errorCode;
	}
	public PonyException(final int _errorCode) {
		super();
		this.errorCode = _errorCode;
	}
	public PonyException(
			final String _message, 
			final Throwable _cause,
			final boolean _enableSuppression, 
			final boolean _writableStackTrace,
			final int _errorCode) {
		super(_message, _cause, _enableSuppression, _writableStackTrace);
		this.errorCode = _errorCode;
	}
	public PonyException(
			final String _message, 
			final Throwable _cause,
			final int _errorCode) {
		super(_message, _cause);
		this.errorCode = _errorCode;
	}
	public PonyException(
			final String _message,
			final int _errorCode) {
		super(_message);
		this.errorCode = _errorCode;
	}
	public PonyException(
			final Throwable _cause,
			final int _errorCode) {
		super(_cause);
		this.errorCode = _errorCode;
	}
}
