package pony.net;

import pony.IMessage;
import pony.IResponse;

public class Response implements IResponse {
	
	private int errorCode;
	private IMessage message;
	
	public Response(){
		
	}

	public Response(final int _errorCode){
		this.errorCode = _errorCode;
	}
	
	public Response(final IMessage _message){
		this.message = _message;
	}
	
	public Response(final int _errorCode , final IMessage _message){
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
	public void setMessage(final IMessage _message) {
		this.message = _message;
	}

}
