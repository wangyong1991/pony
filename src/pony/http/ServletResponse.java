package pony.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

public interface ServletResponse {
	void flushBuffer() throws IOException;
	
	int getBufferSize();
	
	String getCharacterEncoding();
	
	String getContentType();
	
	Locale getLocale();
	
	OutputStream getOutputStream() throws IOException;
	
	PrintWriter getWriter() throws IOException;
	
	boolean isCommitted();
	
	void reset();
	
	void resetBuffer();
	
	void setBufferSize(int size);
	
	void setCharacterEncoding(String charset);
	
	void setContentLength(int len);
	
	void setContentType(String type);
	
	void setLocale(Locale loc);
}
