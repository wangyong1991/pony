package pony.filter;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.junit.Test;

import pony.IFilter;
import pony.net.ServerConfig;

public class FilterTest {
	private static String testStr = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaa";

	@Test
	public void testZip() {
		IFilter zipFilter = new ZipFilter();
		byte[] byteArray = testStr.getBytes();
		ByteBuffer buf = ByteBuffer.wrap(byteArray);
		System.out.println("length before zip--> " + buf.limit());
		buf = zipFilter.doFilter(buf);
		System.out.println("length after zip--> " + buf.limit());
	}

	@Test
	public void testUnzip() {
		IFilter zipFilter = new ZipFilter();
		byte[] byteArray = testStr.getBytes();
		ByteBuffer buf = ByteBuffer.wrap(byteArray);
		System.out.println("length before zip--> " + buf.limit());
		buf = zipFilter.doFilter(buf);
		System.out.println("length after zip--> " + buf.limit());
		IFilter unzipFilter = new UnzipFilter();
		buf = unzipFilter.doFilter(buf);
		System.out.println("length after unzip--> " + buf.limit());
	}
	
	@Test
	public void testEncrypt() throws UnsupportedEncodingException{
		IFilter encryptFilter = new EncryptFilter();
		byte[] byteArray = testStr.getBytes(ServerConfig.getCharset());
		ByteBuffer buf = ByteBuffer.wrap(byteArray);
		System.out.println("-------before encrypt------------");
		System.out.println(Arrays.toString(byteArray));
		buf = encryptFilter.doFilter(buf);
		System.out.println("-------after encrypt------------");
		System.out.println(Arrays.toString(buf.array()));
		
		IFilter decryptFilter = new DecryptFilter();
		buf = decryptFilter.doFilter(buf);
		System.out.println("-------after decrypt------------");
		System.out.println(Arrays.toString(buf.array()));
	}
}
