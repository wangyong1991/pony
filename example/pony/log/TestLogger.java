package pony.log;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {
	private static final Logger logger = LoggerFactory.getLogger("TestLogger");

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testError(){
		logger.error("test error");
	}

}
