package pony.filter;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import pony.IFilter;
/**
 * 数据解密过滤器
 * @author WangYong
 *
 * @Date 2015年2月25日
 */
public class DecryptFilter implements IFilter {
private MessageDigest md5 ;
	
	public DecryptFilter(){
		try {
			md5 =  MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ByteBuffer doFilter(final ByteBuffer input) {
		ByteBuffer output = null;
		byte[] md5Bytes = input.array();
		md5.update(md5Bytes);
		output = ByteBuffer.wrap(md5Bytes);
		return output;
	}

	@Override
	public void destroy() {
		md5 = null;
	}


}
