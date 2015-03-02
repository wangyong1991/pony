package pony.exception;

public class HandlerNotFoundException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6790039044570167148L;

	public HandlerNotFoundException(final String _message) {
		super(_message);
	}
	
	public HandlerNotFoundException(){
		super();
	}
}
