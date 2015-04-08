package pony;

import java.io.IOException;
/**
 * 监听器
 * <pre>
 * 循环监听目标对象的变化
 * </pre>
 * @author &#x738B;&#x52C7;
 * @version 1.0
 */
public interface IListener<E extends IEvent> extends Runnable {
	/**
	 * 监听
	 * <pre>
	 * 定义监听的具体内容和具体方式
	 * </pre>
	 * @throws IOException
	 */
	void listen()  throws IOException;
	
	void onEvent(E _event);
}
