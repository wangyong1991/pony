package pony.log;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pony.log.LogLevel;
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
	
	public final static String DATE_ROLLING = "DATE";
	public final static String SIZE_ROLLING = "SIZE";
	
	private final static Properties props = new Properties();
	private final static String FILE_PATH = "pony.properties";

	private static final LogLevel DEFAULT_LOG_LEVEL = LogLevel.DEBUG;

	private static final String DEFAULT_LOG_FILE = "log";

	private static final int DEFAULT_LOG_FILE_SIZE = 2048;
	
	private static final boolean DEFAULT_LOG_ENABLE_FILE = true;
	
	private static final boolean DEFAULT_LOG_ENABLE_CONSOLE = true;
	
	private static final Charset DEFAULT_CHARSET = Charsets.UTF_8;
	
	static{
		try {
			InputStream in = new BufferedInputStream (new FileInputStream(FILE_PATH));
      
			props.load(in);
		} catch (IOException e) {
			logger.error("Load properties failed!", e);
		}
	}
	
	public static LogLevel getLogLevel(){
		final String logLevel = props.getProperty(LOG_LEVEL);
		if(null == logLevel ){
			return DEFAULT_LOG_LEVEL;
		}
		return LogLevel.valueOf(logLevel);
	}
	
	public static String getLogFileName(){
		final String fileName = props.getProperty(LOG_FILE_NAME);
		if(null == fileName ){
			return DEFAULT_LOG_FILE;
		}
		return fileName;
	}
	
	public static int getFileSize(){
		final String sizeStr = props.getProperty(LOG_FILE_SIZE);
		if(StringUtils.isEmpty(sizeStr) || ! StringUtils.isNumber(sizeStr) ){
			return DEFAULT_LOG_FILE_SIZE;
		}
		return Integer.parseInt(sizeStr);
	}
	
	public static String getPattern(){
		final String logPattern = props.getProperty(LOG_PATTERN);
		if(null == logPattern ){
			return DATE_ROLLING;
		}
		return logPattern;
	}
	
	public static boolean isEnableConsole(){
		final String str = props.getProperty(LOG_ENABLE_CONSOLE);
		if(StringUtils.isEmpty(str) || ! StringUtils.isBoolean(str) ){
			return DEFAULT_LOG_ENABLE_CONSOLE;
		}
		return Boolean.valueOf(str);
	}
	
	public static boolean isEnableFile(){
		final String str = props.getProperty(LOG_ENABLE_FILE);
		if(StringUtils.isEmpty(str) || ! StringUtils.isBoolean(str) ){
			return DEFAULT_LOG_ENABLE_FILE;
		}
		return Boolean.valueOf(str);
	}
	
	public static Charset getCharset(){
		final String str = props.getProperty(LOG_CHARSET);
		if(StringUtils.isEmpty(str) || ! Charset.isSupported(str)){
			return DEFAULT_CHARSET;
		}
		return Charset.forName(str);
	}
}
