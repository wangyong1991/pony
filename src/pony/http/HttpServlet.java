package pony.http;

public abstract class HttpServlet {
	public abstract void init();
	public void service(ServletRequest request, ServletResponse response){
		
	}
	public abstract void doGet(ServletRequest request, ServletResponse response);
	public abstract void doPost(ServletRequest request, ServletResponse response);
	public abstract void doPut(ServletRequest request, ServletResponse response);
	public abstract void doDelete(ServletRequest request, ServletResponse response);
	public abstract void doHead(ServletRequest request, ServletResponse response);
	public abstract void doOptions(ServletRequest request, ServletResponse response);
	public abstract void doTrace(ServletRequest request, ServletResponse response);
	
	public void destroy(){
		
	}
}
