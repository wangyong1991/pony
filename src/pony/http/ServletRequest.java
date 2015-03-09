package pony.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public interface ServletRequest {

	Object getAttribute(final String name);
	
	Enumeration<String> getAttributeNames();
	
	String getCharacterEncoding();
	
	int getContentLength();
	
	String getContentType();
	
	InputStream getInputStream() throws IOException;
	
	String getLocalAddr();
	
	Locale getLocale();
	
	String getLocalName();
	
	int getLocalPort();
	
	String getParameter(String name);
	
	Map<String,String> getParameterMap();

	Enumeration<String> getParameterNames();
	
	String[] getParameterValues(String name);
	
	String getProtocol();
	
	BufferedReader getReader() throws IOException;
	
	String getRemoteAddr();
	
	String getRemoteHost();
	
	String getServerName();
	
	int getServerPort();
	
	void removeAttribute(String name);
	
	void setAttribute(String name, Object obj);
	
	void setCharacterEncoding(String env) throws UnsupportedEncodingException;

	ServletContext getServletContext();
}
