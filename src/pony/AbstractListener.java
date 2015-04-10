package pony;

import java.io.IOException;

public abstract class AbstractListener<E extends IEvent> implements IListener<E> {
	
	private EventHolder<E> eventHolder = new EventHolder<E>();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listen() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(E _event) {
		// TODO Auto-generated method stub
		
	}

}
