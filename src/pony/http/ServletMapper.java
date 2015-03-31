package pony.http;

import java.util.HashMap;
import java.util.Map;

public class ServletMapper {
	private static Map<String, Class<? extends HttpServlet>> servletMap ;
	
	public static void init(){
		servletMap = new HashMap<String, Class<? extends HttpServlet>>();
	}
	
	public static void registerServlet(final String uri, final Class<? extends HttpServlet> servlet){
		servletMap.put(uri, servlet);
	}
	
	public static void execute(final HttpServletRequest req, final HttpServletResponse resp){
		try {
			final HttpServlet servlet = getServlet(req.getRequestURI());
			servlet.service(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static HttpServlet getServlet(final String uri) throws Exception{
		if(servletMap.containsKey(uri)){
			final Class<? extends HttpServlet> clazz = servletMap.get(uri);
			return clazz.newInstance();
		}
		return null;
	}
}
