package pony.net;

import pony.IMessage;
import pony.IRequest;

public class Request implements IRequest {
	
	private IMessage message;
	
	public Request(final IMessage _message){
		this.message = _message;
	}

	@Override
	public IMessage getMessage() {
		return this.message;
	}

}
