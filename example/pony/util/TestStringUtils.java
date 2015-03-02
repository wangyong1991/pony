package pony.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStringUtils {

	@Test
	public void testIsEmpty(){
		String str = null;
		assertTrue(StringUtils.isEmpty(str));
		str = "";
		assertTrue(StringUtils.isEmpty(str));
		str = "   ";
		assertTrue(StringUtils.isEmpty(str));
		str = "\n  ";
		assertTrue(StringUtils.isEmpty(str));
		str = "  \t  ";
		assertTrue(StringUtils.isEmpty(str));
		str = "abs";
		assertFalse(StringUtils.isEmpty(str));
	}
	
	@Test
	public void testIsNumber(){
		String str = "1";
		assertTrue(StringUtils.isNumber(str));
		str = "1   ";
		assertFalse(StringUtils.isNumber(str));
		str = "123";
		assertTrue(StringUtils.isNumber(str));
		str = "1n";
		assertFalse(StringUtils.isNumber(str));
		str = "1\n";
		assertFalse(StringUtils.isNumber(str));
	}
}
