package pony.filter;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import pony.IFilter;
/**
 * 数据加密过滤器<br>
 * 该过滤器采用MD5加密算法
 * @author WangYong
 *
 * @Date 2015年2月25日
 */
public class EncryptFilter implements IFilter {
	private MessageDigest md5 ;
	
	public EncryptFilter(){
		try {
			md5 =  MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ByteBuffer doFilter(final ByteBuffer input) {
		ByteBuffer output = null;
		byte[] md5Bytes = md5.digest(input.array());
		output = ByteBuffer.wrap(md5Bytes);
		return output;
	}

	@Override
	public void destroy() {
		md5 = null;
	}

}
