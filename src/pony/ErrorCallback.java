package pony;


public final class ErrorCallback {
	private int errorCode ;
	
	public final int getErrorCode() {
		return errorCode;
	}

	public void onError(final int _errorCode){
		this.errorCode = _errorCode;
	}
}
