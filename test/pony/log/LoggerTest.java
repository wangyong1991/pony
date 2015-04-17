package pony.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pony.BaseTest;

public class LoggerTest extends BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	private LogListener logListener ;
	private volatile boolean running = true;
	
	@Test
	public void testError() {
		logListener = LogListener.getInstance();
		Thread thread = new Thread(logListener);
		thread.start();
		
		final Thread client = new Thread(){
			public void run() {
				while(running){
					logger.error("test error!");
				}
			};
		};
		
		client.start();
		try {
			Thread.sleep(2000);
			running = false;
			thread.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
