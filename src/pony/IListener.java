package pony;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import pony.annotation.ThreadSafe;
/**
 * 监听器
 * <pre>
 * 循环监听客户端触发的事件
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
	 * 事件队列
	 * <pre>
	 * 事件被触发后，先将时间缓存到事件队列中，以防止事件丢失
	 * 事件的存取过程涉及到经典的“生产者-消费者”问题，客户端（包括其他子系统）
	 * 作为生产者将生成的事件pull到消息队列中，监听器作为消费者，将事件从消息
	 * 队列中取出，并进行处理。
	 * 该类需实现为线程安全的。
	 * </pre>
	 * @author &#x738B;&#x52C7;
	 * @version 1.0
	 */
	@ThreadSafe
	public class EventQueue<E extends IEvent> {
		private static final int DEFAULT_CAPACITY = 1 << 8;
		/** 事件队列 */
		private final BlockingQueue<E> eventQueue ; 

		/**
		 * 构造方法
		 * <pre>
		 * 创建事件队列实例，并指定队列的长度。
		 * </pre>
		 * @param _capacity
		 */
		public EventQueue(final int _capacity){
			this.eventQueue = new ArrayBlockingQueue<E>(_capacity);
		}
		
		/**
		 * 默认构造方法
		 * <pre>
		 * 创建事件队列实例，并设置事件队列的长度为{@link DEFAULT_CAPACITY}
		 * </pre>
		 */
		public EventQueue(){
			this.eventQueue = new ArrayBlockingQueue<E>(DEFAULT_CAPACITY);
		}
		
		/**
		 * 将事件放入事件队列中
		 * @param _event
		 */
		public void push(final E _event) {
			this.eventQueue.add(_event);
		}
		
		/**
		 * 取出事件队列中最前面的事件对象
		 * @return
		 */
		public E poll(){
			return this.eventQueue.poll();
		}
		
		/**
		 * 获取事件队列中现有事件数量
		 * @return
		 */
		public int getCount(){
			return this.eventQueue.size();
		}
	}
}
