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
	public void testToSerializable(){
		final ILayout htmlLayout = new HtmlLayout();
		final LogEvent event = new LogEvent(HtmlLayoutTest.class.getName(), LogLevel.DEBUG, "testToSerializable");
		final String output = htmlLayout.toSerializable(event);
		System.out.println(output);
	}
	
	@Test
	public void testToSerializableWithThrown(){
		final ILayout htmlLayout = new HtmlLayout();
		final LogEvent event = new LogEvent(HtmlLayoutTest.class.getName(), LogLevel.DEBUG, "testToSerializable", new NullPointerException());
		final String output = htmlLayout.toSerializable(event);
		System.out.println(new String(htmlLayout.getHeader()));
		System.out.println(output);
		System.out.println(new String(htmlLayout.getFooter()));
	}
}
