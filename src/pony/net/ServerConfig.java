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
	
	private final static String DEAULT_SERVER_NAME = "Pony";
	
	private final static int DEFAULT_SERVER_PORT = 7777;
	
	private final static boolean DEFAULT_SERVER_BLOCKING = false;

	private static final Charset DEFAULT_CHARSET = Charsets.UTF_8;
	
	
	private static String serverName = DEAULT_SERVER_NAME;
	private static int serverPort = DEFAULT_SERVER_PORT;
	private static boolean blocking = DEFAULT_SERVER_BLOCKING;
	private static Charset charset = DEFAULT_CHARSET;
	
	
	static{
		try {
			InputStream in = new BufferedInputStream (new FileInputStream(FILE_PATH));
			props.load(in);
			// server name
			final String nameStr = props.getProperty(SERVER_NAME);
			if(nameStr != null){
				serverName = nameStr;
			}
			
			// server port
			final String portStr = props.getProperty(SERVER_PORT);
			if(StringUtils.isNotEmpty(portStr) && StringUtils.isNumber(portStr) ){
				serverPort = Integer.parseInt(portStr);
			}
			
			// blocking
			final String blockingStr = props.getProperty(SERVER_BLOCKING);
			if(StringUtils.isNotEmpty(blockingStr) && StringUtils.isBoolean(blockingStr) ){
				blocking =  Boolean.valueOf(blockingStr);
			}
			
			// charset
			final String str = props.getProperty(CHARSET);
			if(StringUtils.isNotEmpty(str) && Charset.isSupported(str)){
				charset = Charset.forName(str);
			}
		} catch (IOException e) {
			logger.error("Load properties failed!", e);
		}
	}
	
	public static final String getServerName() {
		return serverName;
	}

	public static final int getServerPort() {
		return serverPort;
	}

	public static final boolean isBlocking() {
		return blocking;
	}

	public static final Charset getCharset() {
		return charset;
	}

}
