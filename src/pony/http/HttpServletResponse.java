package pony.http;

public interface HttpServletResponse extends ServletResponse {
	public static final int SC_ACCEPTED = 202;
	public static final int SC_BAD_GATEWAY = 502;
	public static final int SC_BAD_REQUEST = 400;
	public static final int SC_CONFLICT = 409;
	public static final int SC_CONTINUE = 100;
	public static final int SC_CREATED = 201;
	public static final int SC_EXPECTATION_FAILED = 417;
	public static final int SC_FORBIDDEN = 403;
	public static final int SC_FOUND = 302;
	public static final int SC_GATEWAY_TIMEOUT = 504;
	public static final int SC_GONE = 410;
	public static final int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
	
	void addCookie(Cookie cookie);
	
	void addDateHeader(String name, long date);
	
	void addHeader(String name, String value);
	
	void addIntHeader(String name, int value);
	
	void containsHeader(String name);
	
	String encodeURL(String url);
	
	void sendError(int sc);
	
	void sendError(int sc, String msg);
	
	void setDateHeader(String name, long date);
	
	void setHeader(String name, String value);
	
	void setIntHeader(String name, int value);
	
	void setStatus(int sc);
	
	void setStatus(int sc, String sm);
}
