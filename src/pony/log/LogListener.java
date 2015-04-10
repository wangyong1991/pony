package pony.log;

import java.io.IOException;

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
public class LogListener implements IListener<LogEvent>{
	
	private final static PatternLayout formater = new PatternLayout(LogConfig.getCharset());
	
	private final static LogListener INSTANCE = new LogListener();
	private final EventHolder<LogEvent> eventHolder;
	
	private LogListener(){
		eventHolder = new EventHolder<LogEvent>();
	}
	
	public static LogListener getInstance(){
		return INSTANCE;
	}

	@Override
	public void run() {
		while(true){
			final LogEvent logMessage = eventHolder.poll();
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
	public void onEvent(final LogEvent _event) {
		// TODO Auto-generated method stub
		
	}

}
