package pony.exception;

public class HandlerNotFoundException extends BaseException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6790039044570167148L;

	public HandlerNotFoundException(final String _message, final int _errorCode) {
		super(_message, _errorCode);
	}
	
	public HandlerNotFoundException(final int _errorCode){
		super(_errorCode);
	}
}
