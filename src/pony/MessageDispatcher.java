package pony;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import pony.exception.HandlerNotFoundException;

public abstract class MessageDispatcher<M extends IEvent> implements IDispatcher{
	
	private final ConcurrentMap<Class<? extends IEvent>, IHandler> handlerMap;
	
	private final EventHolder<M> messageHolder ;
	
	public MessageDispatcher(final int _capacity){
		super();
		messageHolder = new EventHolder<M>(_capacity);
		handlerMap = new ConcurrentHashMap<Class<? extends IEvent>, IHandler>();
	}
	
	@Override
	public void dispatch(final IEvent _message) {
		final IHandler handler = handlerMap.get(_message.getClass());
		if(null == handler){
			throw new HandlerNotFoundException("No handler be found for " + _message.getClass());
		}
	}

	@Override
	public void register(
			final Class<? extends IEvent> clazz,
			final IHandler _handler) {
		handlerMap.putIfAbsent(clazz, _handler);
	}
}
