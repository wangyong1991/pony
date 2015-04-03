package pony.log;

import java.io.IOException;

import pony.EventHolder;
import pony.IEvent;
import pony.IListener;
import pony.annotation.ThreadSafe;
import pony.log.layout.PatternLayout;
/**
 * 日志记录线程
 * @author WangYong
 *
 * @Date 2015年2月10日
 */
@ThreadSafe
public class LogThread implements IListener{
	
	private final static PatternLayout formater = new PatternLayout(LogConfig.getCharset());
	
	private final static LogThread INSTANCE = new LogThread();
	private final EventHolder<LogEvent> eventHolder;
	
	private LogThread(){
		eventHolder = new EventHolder<LogEvent>();
	}
	
	public static LogThread getInstance(){
		return INSTANCE;
	}

	@Override
	public void run() {
		while(true){
			final LogEvent logMessage = eventHolder.pollMessage();
			final String message = formater.toSerializable(logMessage);
			if(LogConfig.isEnableConsole()){
				System.out.println(message);
			}
			if(LogConfig.isEnableFile()){
				
			}
		}
	}

	@Override
	public void listen() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(final IEvent _event) {
		// TODO Auto-generated method stub
		
	}

}
