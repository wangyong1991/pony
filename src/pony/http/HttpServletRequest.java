package pony.http;

import java.util.Enumeration;

public interface HttpServletRequest extends ServletRequest {
	String getContextPath();
	
	Cookie[] getCookies();
	
	long getDateHeader(String name);
	
	String getHeader(String name);
	
	Enumeration<String> getHeaderNames();
	
	Enumeration<String> getHeaders();
	
	int getIntHeader(String name);
	
	String getMethod();
	
	String getPathInfo();
	
	String getPathTranslated();
	
	String getQueryString();
	
	String getRemoteUser();
	
	String getRequestedSessionId();
	
	String getRequestURI();
	
	StringBuffer getRequestURL();
	
	String getServletPath();
	
	HttpSession getSession();
	
	HttpSession getSession(boolean create);
	
	boolean isRequestedSessionIdFromCookie();
	
	boolean isRequestedSessionIdFromUrl();
	
	boolean isRequestedSessionIdFromURL();
	
	boolean isRequestedSessionIdValid();
}
