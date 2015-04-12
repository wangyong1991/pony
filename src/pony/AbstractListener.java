package pony;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractListener<E extends IEvent> implements IListener<E> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractListener.class);
	
	private final EventQueue<E> eventQueue ;
	
	public AbstractListener(final int _capacity){
		eventQueue = new EventQueue<E>(_capacity);
	}
	
	public AbstractListener(){
		eventQueue = new EventQueue<E>();
	}

	@Override
	public void run() {
		try {
			while(true){
				listen();
			}
		} catch (IOException e) {
			logger.error(e.getMessage() , e);
		}
	}

	@Override
	public void onEvent(E _event) {
		eventQueue.push(_event);
	}

}
