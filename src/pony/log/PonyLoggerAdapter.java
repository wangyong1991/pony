package pony.log;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import pony.net.ServerConfig;

public class PonyLoggerAdapter implements Logger {

	private PonyLogger logger ;
	
	public PonyLoggerAdapter(final PonyLogger _logger){
		this.logger = _logger;
	}
	
	@Override
	public void debug(final String _logMsg) {
		if (isDebugEnabled()) {
			logger.write(_logMsg, LogLevel.DEBUG);
		}
	}

	@Override
	public void debug(final String format, final Object arg1) {
		if(isDebugEnabled()){
			FormattingTuple ft = MessageFormatter.format(format, arg1);
			logger.write(ft.getMessage(), LogLevel.DEBUG);
		}
	}

	@Override
	public void debug(final String format, final Object... arg1) {
		if(isDebugEnabled()){
			FormattingTuple ft = MessageFormatter.arrayFormat(format, arg1);
			logger.write(ft.getMessage(), LogLevel.DEBUG);
		}
	}

	@Override
	public void debug(final String _logMsg, final Throwable _thrown) {
		if(isDebugEnabled()){
			logger.write(_logMsg, LogLevel.DEBUG, _thrown);
		}
	}

	@Override
	public void debug(Marker arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		if(isDebugEnabled()){
			FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
			logger.write(ft.getMessage(), LogLevel.DEBUG);
		}
	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debug(Marker arg0, String arg1, Object... arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debug(Marker arg0, String arg1, Throwable arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2, Object arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(String _logMsg) {
		if (isErrorEnabled()) {
			logger.write(_logMsg, LogLevel.ERROR);
		}
	}

	@Override
	public void error(String format, Object arg1) {
		if(isErrorEnabled()){
			FormattingTuple ft = MessageFormatter.format(format, arg1);
			logger.write(ft.getMessage(), LogLevel.ERROR);
		}
	}

	@Override
	public void error(String format, Object... argsArray) {
		if(isErrorEnabled()){
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argsArray);
			logger.write(ft.getMessage(), LogLevel.ERROR);
		}
	}

	@Override
	public void error(String _logMsg, Throwable _thrown) {
		if(isErrorEnabled()){
			logger.write(_logMsg, LogLevel.ERROR, _thrown);
		}
	}

	@Override
	public void error(Marker arg0, String arg1) {
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		if(isErrorEnabled()){
			FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
			logger.write(ft.getMessage(), LogLevel.ERROR);
		}
	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(Marker arg0, String arg1, Object... arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(Marker arg0, String arg1, Throwable arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2, Object arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return logger.getName();
	}

	@Override
	public void info(String _logMsg) {
		if (isInfoEnabled()) {
			logger.write(_logMsg, LogLevel.INFO);
		}
	}

	@Override
	public void info(String format, Object arg1) {
		if(isInfoEnabled()){
			FormattingTuple ft = MessageFormatter.format(format, arg1);
			logger.write(ft.getMessage(), LogLevel.INFO);
		}
	}

	@Override
	public void info(String format, Object... argsArray) {
		if(isInfoEnabled()){
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argsArray);
			logger.write(ft.getMessage(), LogLevel.INFO);
		}
	}

	@Override
	public void info(String _logMsg, Throwable _thrown) {
		if(isInfoEnabled()){
			logger.write(_logMsg, LogLevel.INFO, _thrown);
		}
	}

	@Override
	public void info(Marker arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		if(isInfoEnabled()){
			FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
			logger.write(ft.getMessage(), LogLevel.INFO);
		}
	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(Marker arg0, String arg1, Object... arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(Marker arg0, String arg1, Throwable arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2, Object arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDebugEnabled() {
		if (LogConfig.getLogLevel().getValue() <= LogLevel.DEBUG.getValue()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isDebugEnabled(Marker arg0) {
		return false;
	}

	@Override
	public boolean isErrorEnabled() {
		if (LogConfig.getLogLevel().getValue() <= LogLevel.ERROR.getValue()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isErrorEnabled(Marker arg0) {
		return false;
	}

	@Override
	public boolean isInfoEnabled() {
		if (LogConfig.getLogLevel().getValue() <= LogLevel.INFO.getValue()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isInfoEnabled(Marker arg0) {
		return false;
	}

	@Override
	public boolean isTraceEnabled() {
		if (LogConfig.getLogLevel().getValue() <= LogLevel.TRACE.getValue()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isTraceEnabled(Marker arg0) {
		return false;
	}

	@Override
	public boolean isWarnEnabled() {
		if (LogConfig.getLogLevel().getValue() <= LogLevel.WARN.getValue()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isWarnEnabled(Marker arg0) {
		return false;
	}

	@Override
	public void trace(String _logMsg) {
		if (isTraceEnabled()) {
			logger.write(_logMsg, LogLevel.TRACE);
		}
	}

	@Override
	public void trace(String format, Object arg1) {
		if(isTraceEnabled()){
			FormattingTuple ft = MessageFormatter.format(format, arg1);
			logger.write(ft.getMessage(), LogLevel.TRACE);
		}
	}

	@Override
	public void trace(String format, Object... argArray) {
		if(isTraceEnabled()){
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
			logger.write(ft.getMessage(), LogLevel.TRACE);
		}
	}

	@Override
	public void trace(String _logMsg, Throwable _thrown) {
		if(isTraceEnabled()){
			logger.write(_logMsg, LogLevel.DEBUG, _thrown);
		}
	}

	@Override
	public void trace(Marker arg0, String arg1) {

	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		if(isTraceEnabled()){
			FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
			logger.write(ft.getMessage(), LogLevel.TRACE);
		}
	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2) {

	}

	@Override
	public void trace(Marker arg0, String arg1, Object... arg2) {

	}

	@Override
	public void trace(Marker arg0, String arg1, Throwable arg2) {

	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2, Object arg3) {

	}

	@Override
	public void warn(String _logMsg) {
		if (isWarnEnabled()) {
			logger.write(_logMsg, LogLevel.WARN);
		}
	}

	@Override
	public void warn(String format, Object arg1) {
		if(isWarnEnabled()){
			FormattingTuple ft = MessageFormatter.format(format, arg1);
			logger.write(ft.getMessage(), LogLevel.WARN);
		}
	}

	@Override
	public void warn(String format, Object... argArray) {
		if(isWarnEnabled()){
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
			logger.write(ft.getMessage(), LogLevel.WARN);
		}
	}

	@Override
	public void warn(String _logMsg, Throwable _thrown) {
		if(isWarnEnabled()){
			logger.write(_logMsg, LogLevel.DEBUG, _thrown);
		}
	}

	@Override
	public void warn(Marker arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		if(isWarnEnabled()){
			FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
			logger.write(ft.getMessage(), LogLevel.WARN);
		}
	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker arg0, String arg1, Object... arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker arg0, String arg1, Throwable arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2, Object arg3) {
		// TODO Auto-generated method stub
		
	}
}