package pony;

public abstract class MessageHolder<E extends IMessage> {
	private MessageQueue<E> messageQueue ; 

	protected MessageHolder(){
		this.messageQueue = new MessageQueue<E>();
	}
	
	public void putMessage(final E _message) {
		this.messageQueue.offerMessage(_message);
	}
	
	protected E pollMessage(){
		return this.messageQueue.pollMessage();
	}
}
