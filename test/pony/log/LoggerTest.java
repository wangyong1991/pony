package pony.log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pony.BaseTest;

public class LoggerTest extends BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	private LogListener logListener ;
	private volatile boolean running = true;
	
	@Before
	public void setUp(){
		logListener = LogListener.getInstance();
		Thread thread = new Thread(logListener);
		thread.start();
	}
	
	@Test
	public void testError() {
		logger.error("test error!");
	}
	
	@After
	public void finalize() throws Throwable {
		try {
			Thread.sleep(1000);
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};
	
	
	@Test
	public void testOutputException(){
		logger.debug("test exception", new NullPointerException());
	}
}
