package pony.log.layout;

import static org.junit.Assert.fail;

import org.junit.Test;

import pony.log.ILayout;
import pony.log.LogEvent;
import pony.log.LogLevel;

public class HtmlLayoutTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testToByteArray(){
		final ILayout htmlLayout = new HtmlLayout();
		final LogEvent event = new LogEvent(HtmlLayoutTest.class.getName(), LogLevel.DEBUG, "testToByteArray");
		final byte[] output = htmlLayout.toByteArray(event);
		System.out.println(new String(output));
	}
	
	@Test
	public void testToByteArrayWithThrown(){
		final ILayout htmlLayout = new HtmlLayout();
		final LogEvent event = new LogEvent(HtmlLayoutTest.class.getName(), LogLevel.DEBUG, "testToByteArray", new NullPointerException());
		final byte[] output = htmlLayout.toByteArray(event);
		System.out.println(new String(htmlLayout.getHeader()));
		System.out.println(new String(output));
		System.out.println(new String(htmlLayout.getFooter()));
	}
}
