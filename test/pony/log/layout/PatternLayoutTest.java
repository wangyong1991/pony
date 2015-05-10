package pony.log.layout;

import org.junit.Test;

import pony.log.ILayout;
import pony.log.LogEvent;
import pony.log.LogLevel;

public class PatternLayoutTest {

	@Test
	public void testToByteArray(){
		final ILayout patternLayout = new PatternLayout();
		final LogEvent event = new LogEvent(PatternLayoutTest.class.getName(), LogLevel.DEBUG, "testToByteArray");
		final byte[] output = patternLayout.toByteArray(event);
		System.out.println(new String(output));
	}
	
	@Test
	public void testToByteArrayWithThrown(){
		final ILayout patternLayout = new PatternLayout();
		final LogEvent event = new LogEvent(PatternLayoutTest.class.getName(), LogLevel.DEBUG, "testToByteArray", new NullPointerException());
		final byte[] output = patternLayout.toByteArray(event);
		System.out.println(new String(output));
	}
}
