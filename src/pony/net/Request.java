package pony.net;

import pony.IEvent;
import pony.IRequest;

public class Request implements IRequest {
	
	private IEvent message;
	
	public Request(final IEvent _message){
		this.message = _message;
	}

	@Override
	public IEvent getMessage() {
		return this.message;
	}

}
