package pony.net;

import pony.IEvent;
import pony.IResponse;

public class Response implements IResponse {
	
	private int errorCode;
	private IEvent message;
	
	public Response(){
		
	}

	public Response(final int _errorCode){
		this.errorCode = _errorCode;
	}
	
	public Response(final IEvent _message){
		this.message = _message;
	}
	
	public Response(final int _errorCode , final IEvent _message){
		this.errorCode = _errorCode;
		this.message = _message;
	}
	
	@Override
	public void setErrorCode(final int _errorCode) {
		this.errorCode = _errorCode;
	}

	@Override
	public int getErrorCode() {
		return this.errorCode;
	}

	@Override
	public void setMessage(final IEvent _message) {
		this.message = _message;
	}

}
