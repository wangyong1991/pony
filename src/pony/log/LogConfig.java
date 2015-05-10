package pony.log;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pony.log.appender.ConsoleAppender;
import pony.log.appender.FileAppender;
import pony.log.appender.RandomAccessFileAppender;
import pony.log.layout.HtmlLayout;
import pony.log.layout.PatternLayout;
import pony.util.Charsets;
import pony.util.StringUtils;

public final class LogConfig {
	private final static Logger logger = LoggerFactory.getLogger(LogConfig.class);
	
	public final static String LOG_LEVEL = "LOG_LEVEL";
	public final static String LOG_FILE_NAME = "LOG_FILE_NAME";
	public final static String LOG_FILE_SIZE = "LOG_FILE_SIZE";
	public final static String LOG_PATTERN = "LOG_PATTERN";
	public final static String LOG_ENABLE_CONSOLE = "LOG_ENABLE_CONSOLE";
	public final static String LOG_ENABLE_FILE = "LOG_ENABLE_FILE";
	public final static String LOG_CHARSET = "LOG_CHARSET";
	
	public final static String LOG_FILE_SUFFIX = ".log";
	public final static String HTML_FILE_SUFFIX = ".html";
	
	public final static String DATE_ROLLING = "DATE";
	public final static String SIZE_ROLLING = "SIZE";
	
	private final static Properties props = new Properties();
	private final static String FILE_PATH = "pony.properties";

	private static final LogLevel DEFAULT_LOG_LEVEL = LogLevel.DEBUG;

	private static final String DEFAULT_LOG_FILE = "LOG";

	private static final int DEFAULT_LOG_FILE_SIZE = 2048;
	
	private static final boolean DEFAULT_LOG_ENABLE_FILE = true;
	
	private static final boolean DEFAULT_LOG_ENABLE_CONSOLE = true;
	
	private static final Charset DEFAULT_CHARSET = Charsets.UTF_8;
	
	private static LogLevel logLevel = DEFAULT_LOG_LEVEL;
	private static String filename = DEFAULT_LOG_FILE;
	private static int fileSize = DEFAULT_LOG_FILE_SIZE;
	private static String pattern = DATE_ROLLING;
	private static boolean enableConsole = DEFAULT_LOG_ENABLE_CONSOLE;
	private static boolean enableFile = DEFAULT_LOG_ENABLE_FILE;
	private static Charset charset = DEFAULT_CHARSET;
	
	private List<IAppender> appenders = new ArrayList<IAppender>();
	
	static{
		try {
			InputStream in = new BufferedInputStream (new FileInputStream(FILE_PATH));
			props.load(in);
			
			// log level
			final String logLevelStr = props.getProperty(LOG_LEVEL);
			if(null != logLevelStr ){
				logLevel = LogLevel.valueOf(logLevelStr);
			}
			
			// log file name
			final String fileName = props.getProperty(LOG_FILE_NAME);
			if(null != fileName ){
				filename = fileName;
			}
			
			// file size
			final String sizeStr = props.getProperty(LOG_FILE_SIZE);
			if(StringUtils.isNotEmpty(sizeStr) && StringUtils.isNumber(sizeStr) ){
				fileSize = Integer.parseInt(sizeStr);
			}
			
			// pattern
			final String logPattern = props.getProperty(LOG_PATTERN);
			if(null != logPattern ){
				pattern = logPattern;
			}
			
			// enable console
			final String enableConsoleStr = props.getProperty(LOG_ENABLE_CONSOLE);
			if(StringUtils.isNotEmpty(enableConsoleStr) && StringUtils.isBoolean(enableConsoleStr) ){
				enableConsole = Boolean.valueOf(enableConsoleStr);
			}
			
			// enable file
			final String enableFileStr = props.getProperty(LOG_ENABLE_FILE);
			if(StringUtils.isNotEmpty(enableFileStr) && StringUtils.isBoolean(enableFileStr) ){
				enableFile = Boolean.valueOf(enableFileStr);
			}
			
			// charset
			final String charsetStr = props.getProperty(LOG_CHARSET);
			if(StringUtils.isNotEmpty(charsetStr) && Charset.isSupported(charsetStr)){
				charset = Charset.forName(charsetStr);
			}
		} catch (IOException e) {
			logger.error("Load properties failed!", e);
		}
	}

	public static final LogLevel getLogLevel() {
		return logLevel;
	}

	public static final String getFilename() {
		return filename;
	}

	public static final int getFileSize() {
		return fileSize;
	}

	public static final String getPattern() {
		return pattern;
	}

	public static final boolean isEnableConsole() {
		return enableConsole;
	}

	public static final boolean isEnableFile() {
		return enableFile;
	}

	public static final Charset getCharset() {
		return charset;
	}
	
	public LogConfig(){
		final ILayout htmlLayout = new HtmlLayout();
		final ILayout patternLayout = new PatternLayout();
		if(enableConsole){
			addAppender(new ConsoleAppender("System.out", patternLayout, System.out, true));
			addAppender(new ConsoleAppender("System.err", patternLayout, System.err, true));
		}else if(enableFile){
//			addAppender(new FileAppender("System.out", patternLayout, System.out, true));
//			addAppender(new RandomAccessFileAppender("System.err", htmlLayout, System.err, true));
		}
	}
	
	private void addAppender(final IAppender _appender){
		this.appenders.add(_appender);
	}

	public final List<IAppender> getAppenders() {
		return this.appenders;
	}
	
	
}
