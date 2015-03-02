package pony.log;
/**
 * 日志级别
 * @author WangYong
 *
 * @Date 2015年2月25日
 */
public enum LogLevel {
	TRACE(0),
	DEBUG(1),
	INFO(2),
	WARN(3),
	ERROR(4);
	
	private int value;
	
	private LogLevel(final int _value){
		this.value = _value;
	}

	public final int getValue() {
		return value;
	}
}
