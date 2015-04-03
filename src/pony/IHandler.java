package pony;

public interface IHandler {
	void execute(IContext _context, IEvent _message);
	
	void onError(int errorCode);
	
	void onException(Throwable e);
	
	/**
	 * 出错回调
	 * <pre>
	 * 该类主要用于搜集{@link IHandler}处理请求过程中产生的错误信息
	 * </pre>
	 * @author WangYong
	 *
	 * @Date 2015年4月3日
	 */
	public class ErrorCallback {
		private int errorCode ;
		
		public final int getErrorCode() {
			return errorCode;
		}

		public void onError(final int errorCode){
			this.errorCode = errorCode;
		}
	}
}
