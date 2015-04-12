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
 * 
 * <strong>注意：</strong>用户自定义错误码应从 500 开始，1~499为系统错误码
 * </pre>
 * @author &#x738B;&#x52C7;
 * @version 1.0
 * @since 1.0
 */
public class ErrorCode {
	/** 无错误 */
	public static final int NO_ERROR = 0;
	
	// 0~499个为系统错误代码
	// 底层结构相关 （1~49）
	/** 找不到对应的处理器 */
	public static final int HANDLER_NOT_FOUND = 1;
	
	// HTTP相关 （50~99）
	/** 既不是HTTP-request，也不是HTTP-response*/
	public static final int NON_HTTP_REQUEST_OR_RESPONSE = 50;
	
	// Cache相关 （100~149）
	
	// Scheduler相关 （150~199）
	
	// DB相关 （200~249）
	
	// Log相关 （250~299）
	
}
