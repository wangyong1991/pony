package pony.log;

import java.io.IOException;
import java.util.List;

import pony.AbstractListener;
import pony.log.layout.PatternLayout;
/**
 * 日志事件监听器
 * <pre>
 * 循环监听日志事件，并记录日志
 * </pre>
 * @author &#x738B;&#x52C7;
 * @version 1.0
 * @since 1.0
 */
public class LogListener extends AbstractListener<LogEvent>{
	
	private final static PatternLayout layout = new PatternLayout(LogConfig.getCharset());
	
	private final static LogListener INSTANCE = new LogListener();
	
	private final LogConfig config = new LogConfig();
	
	private LogListener(){
		super();
	}
	
	public static LogListener getInstance(){
		return INSTANCE;
	}

	@Override
	public void listen() throws IOException, InterruptedException {
		final LogEvent l_logEvent = eventQueue.poll();
		final List<IAppender> l_appenders = config.getAppenders();
		
		for (final IAppender appender : l_appenders) {
			appender.append(l_logEvent);
		}
	}

}
