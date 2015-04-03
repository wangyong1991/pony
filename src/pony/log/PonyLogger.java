package pony.log;


public class PonyLogger {
	
	private String loggerName;

	public PonyLogger(final String _loggerName) {
		this.loggerName = _loggerName;
	}

	public void write(final String _logMsg, final LogLevel _level) {
		final LogEvent logMessage = new LogEvent(loggerName, _level, _logMsg);
		LogThread.getInstance().onEvent(logMessage);
	}
	
	public void write(final String _logMsg, final LogLevel _level, final Throwable _thrown){
		final LogEvent logMessage = new LogEvent(loggerName, _level, _logMsg, _thrown);
		LogThread.getInstance().onEvent(logMessage);
	}

	public String getName(){
		return loggerName;
	}
	
}
