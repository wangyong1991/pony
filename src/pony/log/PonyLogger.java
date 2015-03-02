package pony.log;


public class PonyLogger {
	
	private String loggerName;

	public PonyLogger(final String _loggerName) {
		this.loggerName = _loggerName;
	}

	public void write(final String _logMsg, final LogLevel _level) {
		final LogMessage logMessage = new LogMessage(loggerName, _level, _logMsg);
		LogThread.getInstance().putMessage(logMessage);
	}
	
	public void write(final String _logMsg, final LogLevel _level, final Throwable _thrown){
		final LogMessage logMessage = new LogMessage(loggerName, _level, _logMsg, _thrown);
		LogThread.getInstance().putMessage(logMessage);
	}

	public String getName(){
		return loggerName;
	}
	
}
