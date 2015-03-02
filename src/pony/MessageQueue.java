package pony;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import pony.annotation.ThreadSafe;
/**
 * 消息队列<br>
 * <p>
 * 该组件作为消息存储的容器，主要用以防止消息过多时可能造成的消息丢失
 * 该组件为线程安全的
 * </p>
 * @author WangYong
 *
 * Date 2015年2月11日
 */
@ThreadSafe
public final class MessageQueue <E extends IMessage> {
	protected final Lock lock = new ReentrantLock();
	protected List<E> waitingMessages = new LinkedList<E>();
	
	public void offerMessage(final E _message){
		if(null == _message)
			throw new NullPointerException("Message should not be null");
		this.lock.lock();
		this.waitingMessages.add(_message);
		this.lock.unlock();
	}
	
	public E pollMessage(){
		try{
			this.lock.lock();
			if(waitingMessages.isEmpty()){
				return null;
			}
			final E message = this.waitingMessages.get(0);
			return message;
		}finally{
			this.lock.unlock();
		}
	}
	
	public int getWaitingMessageCount() {
		try {
			this.lock.lock();
			return this.waitingMessages.size();
		} finally {
			this.lock.unlock();
		}
	}
}
