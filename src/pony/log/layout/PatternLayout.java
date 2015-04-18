package pony.log.layout;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Date;

import pony.log.LogLevel;
import pony.log.LogEvent;

/**
 * 根据配置生成指定格式的日志信息
 * @author WangYong
 *
 * @Date 2015年2月26日
 */
public class PatternLayout extends AbstractLayout {
	
	public PatternLayout(byte[] header, byte[] footer, Charset charset) {
		super(header, footer, charset);
	}
	
	public PatternLayout(Charset charset) {
		super(charset);
	}

	@Override
	public String toSerializable(final LogEvent _event) {
		final Date date = new Date();
		final String loggerName = _event.getLoggerName();
		final LogLevel level = _event.getLevel();
		final String message = _event.getMessage();
		final Throwable t = _event.getThrown();
		final StringBuilder sb = new StringBuilder();
		sb.append(String.format("%1$tF %1$tT:%1$tL [%2$s] %3$s %4$s\r\n", date, level, loggerName, message));
		if(t != null){
			formatOption(t, sb);
		}
		return sb.toString();
	
	}
	
	private void formatOption(final Throwable throwable, final StringBuilder buffer){
		final StringWriter w = new StringWriter();
		
		throwable.printStackTrace(new PrintWriter(w));
		final int len = buffer.length();
		
		if (len > 0 && !Character.isWhitespace(buffer.charAt(len - 1))) {
            buffer.append(' ');
        }
		
		final StringBuilder sb = new StringBuilder();
		final String[] array = w.toString().split(System.getProperty("line.separator", "\n"));
		final int limit = array.length - 1;
		
		for (int i = 0; i <= limit; ++i) {
            sb.append(array[i]);
            if (i < limit) {
                sb.append(System.getProperty("line.separator", "\n"));
            }
        }
        buffer.append(sb.toString());
        System.out.println(buffer.toString());
	}
}
