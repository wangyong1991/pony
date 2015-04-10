package pony;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * 监听器
 * <pre>
 * 循环监听目标对象的变化
 * </pre>
 * @author &#x738B;&#x52C7;
 * @version 1.0
 */
public interface IListener<E extends IEvent> extends Runnable {
	/**
	 * 监听
	 * <pre>
	 * 定义监听的具体内容和具体方式
	 * </pre>
	 * @throws IOException
	 */
	void listen()  throws IOException;
	
	void onEvent(E _event);
	
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
		
		public void push(final E _message) {
			this.eventQueue.add(_message);
		}
		
		public E poll(){
			return this.eventQueue.poll();
		}
	}
}
