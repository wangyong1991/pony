package pony.log;

/**
 * 日志系统
 * <pre>
 * 日志分级、记录
 * </pre>
 * @author &#x738B;&#x52C7;
 * @version 1.0
 */
public class PonyLogger {
	
	private String loggerName;

	public PonyLogger(final String _loggerName) {
		this.loggerName = _loggerName;
	}

	public void write(final String _logMsg, final LogLevel _level) {
		final LogEvent l_logEvent = new LogEvent(loggerName, _level, _logMsg);
		LogListener.getInstance().onEvent(l_logEvent);
	}
	
	public void write(final String _logMsg, final LogLevel _level, final Throwable _thrown){
		final LogEvent l_logEvent = new LogEvent(loggerName, _level, _logMsg, _thrown);
		LogListener.getInstance().onEvent(l_logEvent);
	}

	public String getName(){
		return loggerName;
	}
	
}
