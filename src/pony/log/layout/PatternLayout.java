package pony.log.layout;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Date;

import pony.log.LogEvent;
import pony.log.LogLevel;

public class PatternLayout extends AbstractLayout {
	
	public static final PatternLayout DEFAULT_PATTERN_LAYOUT = new PatternLayout();

	public PatternLayout() {
		super();
	}

	public PatternLayout(byte[] _header, byte[] _footer, Charset _charset) {
		super(_header, _footer, _charset);
	}

	public PatternLayout(Charset _charset) {
		super(_charset);
	}

	@Override
	public String format(final LogEvent _event) {
		final Date date = new Date();
		final String loggerName = _event.getLoggerName();
		final LogLevel level = _event.getLevel();
		final String message = _event.getMessage();
		final Throwable t = _event.getThrown();
		final StringBuilder sb = new StringBuilder();
		sb.append(String.format("%1$tF %1$tT:%1$tL [%2$s] %3$s %4$s\r\n", date, level, loggerName, message));
		if(t != null){
			formatThrowable(t, sb);
		}
		return sb.toString();
	}

	@Override
	public void formatThrowable(final Throwable _thrown, final StringBuilder _sbuf) {
		final StringWriter w = new StringWriter();
				
		_thrown.printStackTrace(new PrintWriter(w));
		final int len = _sbuf.length();
		
		if (len > 0 && !Character.isWhitespace(_sbuf.charAt(len - 1))) {
            _sbuf.append(' ');
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
        _sbuf.append(sb.toString());
	}

}
