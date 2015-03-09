package pony.http;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

public interface ServletResponse {
	void flushBuffer();
	
	int getBufferSize();
	
	String getCharacterEncoding();
	
	String getContentType();
	
	Locale getLocale();
	
	OutputStream getOutputStream();
	
	PrintWriter getWriter();
	
	boolean isCommitted();
	
	void reset();
	
	void resetBuffer();
	
	void setBufferSize(int size);
	
	void setCharacterEncoding(String charset);
	
	void setContentLength(int len);
	
	void setContentType(String type);
	
	void setLocale(Locale loc);
}
