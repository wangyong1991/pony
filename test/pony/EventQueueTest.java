package pony;

import org.junit.Test;

import pony.IListener.EventQueue;
import pony.log.LogEvent;
import pony.log.LogLevel;

public class EventQueueTest {
	private volatile boolean running = true;

	@Test
	public void testPushAndPoll(){
		EventQueue<LogEvent> eventQueue = new EventQueue<LogEvent>();
		final Thread listener = new Thread(){
			public void run() {
				while(running){
					final LogEvent event = new LogEvent("pony", LogLevel.DEBUG, "test logger!");
					try {
						eventQueue.push(event);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		};
		
		final Thread client = new Thread(){
			public void run() {
				while(running){
					LogEvent event;
					try {
						event = eventQueue.poll();
						System.out.println(event.getMessage());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}
			};
		};
		
		listener.start();
		client.run();
		try {
			Thread.sleep(1000);
			running = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
