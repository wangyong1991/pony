package pony;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author &#x738B;&#x52C7;
 * @version 1.0
 */
public class EventHolder<E extends IEvent> {
	private static final int DEFAULT_CAPACITY = 1 << 8;
	private final BlockingQueue<E> eventQueue ; 

	public EventHolder(final int _capacity){
		this.eventQueue = new ArrayBlockingQueue<E>(_capacity);
	}
	
	public EventHolder(){
		this.eventQueue = new ArrayBlockingQueue<E>(DEFAULT_CAPACITY);
	}
	
	public void putMessage(final E _message) {
		this.eventQueue.add(_message);
	}
	
	public E pollMessage(){
		return this.eventQueue.poll();
	}
}