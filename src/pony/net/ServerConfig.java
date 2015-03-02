package pony.net;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pony.util.Charsets;
import pony.util.StringUtils;

public final class ServerConfig {
	private final static Logger logger = LoggerFactory.getLogger(ServerConfig.class);
	
	public final static String SERVER_NAME = "SERVER_NAME";
	public final static String SERVER_PORT = "SERVER_PORT";
	public final static String SERVER_BLOCKING = "SERVER_BLOCKING";
	public final static String CHARSET = "CHARSET";
	
	private final static Properties props = new Properties();
	private final static String FILE_PATH = "pony.properties";
	
	private final static int DEFAULT_SERVER_PORT = 7777;
	
	private final static boolean DEFAULT_SERVER_BLOCKING = false;

	private static final Charset DEFAULT_CHARSET = Charsets.UTF_8;
	
	static{
		try {
			InputStream in = new BufferedInputStream (new FileInputStream(FILE_PATH));
      
			props.load(in);
		} catch (IOException e) {
			logger.error("Load properties failed!", e);
		}
	}
	
	public static String getServerName(){
		return props.getProperty(SERVER_NAME);
	}
	
	public static int getServerPort(){
		final String portStr = props.getProperty(SERVER_PORT);
		if(StringUtils.isEmpty(portStr) || ! StringUtils.isNumber(portStr) ){
			return DEFAULT_SERVER_PORT;
		}
		return Integer.parseInt(portStr);
	}
	
	public static boolean getServerBlocking(){
		final String str = props.getProperty(SERVER_BLOCKING);
		if(StringUtils.isEmpty(str) || ! StringUtils.isBoolean(str) ){
			return DEFAULT_SERVER_BLOCKING;
		}
		return Boolean.valueOf(str);
	}
	
	public static Charset getCharset(){
		final String str = props.getProperty(CHARSET);
		if(StringUtils.isEmpty(str) || ! Charset.isSupported(str)){
			return DEFAULT_CHARSET;
		}
		return Charset.forName(str);
	}
}
