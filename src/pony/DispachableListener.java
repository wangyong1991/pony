package pony;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import pony.exception.HandlerNotFoundException;
/**
 * 自带事件分派功能的监听器
 * <pre>
 * 
 * </pre>
 * @author &#x738B;&#x52C7;
 * @version 1.0
 * @since 1.0
 */
public class DispachableListener<E extends IEvent> extends AbstractListener<E> implements IDispatcher {

	private final ConcurrentMap<Class<? extends IEvent>, Class<? extends IHandler>> handlerMap;
	
	public DispachableListener(final int _capacity){
		super(_capacity);
		handlerMap = new ConcurrentHashMap<Class<? extends IEvent>, Class<? extends IHandler>>();
	}
	
	public DispachableListener(){
		super();
		handlerMap = new ConcurrentHashMap<Class<? extends IEvent>, Class<? extends IHandler>>();
	}
	
	@Override
	public void dispatch(final IEvent _message) {
		final Class<? extends IHandler> clazz = handlerMap.get(_message.getClass());
		if(null == clazz){
			throw new HandlerNotFoundException("No handler been found for " + _message.getClass(), ErrorCode.HANDLER_NOT_FOUND);
		}
	}

	@Override
	public void register(
			final Class<? extends IEvent> _eventClass,
			final Class<? extends IHandler> _handlerClass) {
		handlerMap.putIfAbsent(_eventClass, _handlerClass);
	}

	@Override
	public void listen() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
