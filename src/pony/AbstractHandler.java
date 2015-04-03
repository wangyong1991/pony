package pony;

public abstract class AbstractHandler implements IHandler {
	private ErrorCallback errorCallback = new ErrorCallback();

	@Override
	public abstract void execute(IContext _context, IEvent _message);

	@Override
	public void onError(int errorCode) {
		errorCallback.onError(errorCode);
	}

	@Override
	public void onException(Throwable e) {
		// TODO Auto-generated method stub

	}

}
