package pony.log;

import pony.MessageHolder;
import pony.annotation.ThreadSafe;
import pony.log.layout.PatternLayout;
/**
 * 日志记录线程
 * @author WangYong
 *
 * Date 2015年2月10日
 */
@ThreadSafe
public class LogThread extends MessageHolder<LogMessage> implements Runnable{
	
	private final static PatternLayout formater = new PatternLayout(LogConfig.getCharset());
	
	private final static LogThread INSTANCE = new LogThread();
	
	private LogThread(){
	}
	
	public static LogThread getInstance(){
		return INSTANCE;
	}

	@Override
	public void run() {
		while(true){
			final LogMessage logMessage = this.pollMessage();
			final String message = formater.toSerializable(logMessage);
			if(LogConfig.isEnableConsole()){
				System.out.println(message);
			}
			if(LogConfig.isEnableFile()){
				
			}
		}
	}

}
