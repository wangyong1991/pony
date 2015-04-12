package pony.exception;
/**
 * Pony中自定义异常的共同父类
 * @author &#x738B;&#x52C7;
 * @version 1.0
 * @since 1.0
 */
public class BaseException extends RuntimeException {
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
	public BaseException(final int _errorCode) {
		super();
		this.errorCode = _errorCode;
	}
	public BaseException(
			final String _message, 
			final Throwable _cause,
			final boolean _enableSuppression, 
			final boolean _writableStackTrace,
			final int _errorCode) {
		super(_message, _cause, _enableSuppression, _writableStackTrace);
		this.errorCode = _errorCode;
	}
	public BaseException(
			final String _message, 
			final Throwable _cause,
			final int _errorCode) {
		super(_message, _cause);
		this.errorCode = _errorCode;
	}
	public BaseException(
			final String _message,
			final int _errorCode) {
		super(_message);
		this.errorCode = _errorCode;
	}
	public BaseException(
			final Throwable _cause,
			final int _errorCode) {
		super(_cause);
		this.errorCode = _errorCode;
	}
}
