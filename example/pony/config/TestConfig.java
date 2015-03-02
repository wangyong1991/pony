package pony.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pony.log.LogConfig;
import pony.log.LogLevel;
import pony.net.ServerConfig;

public class TestConfig {

	@Test
	public void testGets(){
		assertEquals("pony", ServerConfig.getServerName());
		assertEquals(7777, ServerConfig.getServerPort());
		assertEquals(LogLevel.DEBUG, LogConfig.getLogLevel());
		assertEquals("LOG.log", LogConfig.getLogFileName());
		assertEquals(2048, LogConfig.getFileSize());
		assertEquals("DATE", LogConfig.getPattern());
		assertEquals(true, LogConfig.isEnableConsole());
		assertEquals(true, LogConfig.isEnableFile());
	}
}
