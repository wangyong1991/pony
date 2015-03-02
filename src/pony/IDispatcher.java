package pony;

public interface IDispatcher {
	
	public void registerHandler(final Class<? extends IMessage> clazz, final IHandler _handler);
	
	public void dispatch(final IMessage _message);
}
