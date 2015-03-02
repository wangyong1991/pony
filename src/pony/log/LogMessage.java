package pony.log;

import pony.Message;
/**
 * 日志消息
 * @author WangYong
 *
 * Date 2015年2月10日
 */
public class LogMessage extends Message  {
	private LogLevel level;
	private String message;
	private String loggerName;
	private Throwable thrown;
	
	public LogMessage(final String _loggerName, final LogLevel _level, final String _message){
		this.loggerName = _loggerName;
		this.level = _level;
		this.message = _message;
	}
	
	public LogMessage(
			final String _loggerName, 
			final LogLevel _level, 
			final String _message,
			final Throwable _thrown){
		this(_loggerName, _level, _message);
		this.thrown = _thrown;
	}

	public final String getLoggerName() {
		return loggerName;
	}

	public final LogLevel getLevel() {
		return level;
	}

	public final String getMessage() {
		return message;
	}

	public final Throwable getThrown() {
		return thrown;
	}
	
}
