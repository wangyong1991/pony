package pony.log;

import java.nio.charset.Charset;

/**
 * 日志布局
 * @author &#x738B;&#x52C7;
 * @version 1.0
 * @since 1.0
 */
public interface ILayout {
	String format(LogEvent _event);
	
	void formatThrowable(Throwable _thrown, StringBuilder _sbuf);
	
	byte[] getHeader();
	
	byte[] getFooter();
	
	Charset getCharset();
	
	String getContentType();
	
	byte[] toByteArray(LogEvent _event);
}
