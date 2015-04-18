package pony.log;

import pony.AbstractEvent;
/**
 * 日志事件
 * <pre>
 * 日志事件包含的主要信息包括日志级别、日志信息
 * 日志类、异常堆栈等。通过日志事件对象能快速追述到
 * 日志触发的原因及相关信息，并迅速找到解决方案
 * 该类只定义了相关字段的getter方法而未定义其setter方法，
 * 目的就是防止在日志事件触发后修改其信息，因而造成信息的不一致
 * </pre>
 * @author &#x738B;&#x52C7;
 * @version 1.0
 * @since 1.0
 */
public class LogEvent extends AbstractEvent  {
	private LogLevel level;
	private String message;
	private String loggerName;
	private Throwable thrown;
	
	public LogEvent(
			final String _loggerName, 
			final LogLevel _level, 
			final String _message){
		this.loggerName = _loggerName;
		this.level = _level;
		this.message = _message;
	}
	
	public LogEvent(
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
