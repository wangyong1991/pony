package pony;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import pony.exception.HandlerNotFoundException;
import pony.net.ListenerMessage;

public abstract class MessageDispatcher extends MessageHolder<ListenerMessage> implements IDispatcher{
	
	private ConcurrentMap<Class<? extends IMessage>, IHandler> handlerMap;
	
	public MessageDispatcher(){
		super();
		handlerMap = new ConcurrentHashMap<Class<? extends IMessage>, IHandler>();
	}
	
	@Override
	public void dispatch(final IMessage _message) {
		final IHandler handler = handlerMap.get(_message.getClass());
		if(null == handler){
			throw new HandlerNotFoundException("No handler be found for " + _message.getClass());
		}
	}

	@Override
	public void registerHandler(
			final Class<? extends IMessage> clazz,
			final IHandler _handler) {
		handlerMap.putIfAbsent(clazz, _handler);
	}
}
