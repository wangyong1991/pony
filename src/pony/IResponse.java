package pony;

public interface IResponse {
	public void setErrorCode(final int _errorCode);
	
	public int getErrorCode();
	
	public void setMessage(final IMessage _message);
}
