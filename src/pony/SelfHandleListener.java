package pony;

public class SelfHandleListener<E extends IEvent> extends AbstractListener<E> implements IHandler {

	@Override
	public void execute(IContext _context, IEvent _message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(int errorCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(Throwable e) {
		// TODO Auto-generated method stub

	}

}
