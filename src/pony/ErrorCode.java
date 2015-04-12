/**
 * 
 */
package pony;

/**
 * 错误码
 * <pre>
 * 该类中定义了在程序中可能出现的错误的码
 * 通过错误码就能准确找到错误的具体信息
 * 如果用户需要自定义错误码，可以继承该类
 * 注意：用户自定义错误码应从 100 开始，1~99为系统错误码
 * </pre>
 * @author &#x738B;&#x52C7;
 * @version 1.0
 * @since 1.0
 */
public class ErrorCode {
	/** 无错误 */
	public static final int NO_ERROR = 0;
	
	// 前100个为系统错误代码
	/** 找不到对应的处理器 */
	public static final int HANDLER_NOT_FOUND = 1;
}
