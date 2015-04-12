package pony.log;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pony.BaseTest;

public class LoggerTest extends BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	private LogListener logListener ;
	Thread thread ;
	
	@Test
	public void setUp() {
		logListener = LogListener.getInstance();
		thread = new Thread(logListener);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testError() {
		logger.error("test error");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		thread.stop();
	}
}
