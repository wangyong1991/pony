package pony;

import java.io.IOException;

public abstract class AbstractListener<E extends IEvent> implements IListener<E> {
	
	protected final EventQueue<E> eventQueue ;
	
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
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onEvent(E _event) {
		try {
			eventQueue.push(_event);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
