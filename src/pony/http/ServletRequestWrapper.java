package pony.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public class ServletRequestWrapper implements ServletRequest {
	private ServletRequest request;

    /**
     * Creates a ServletRequest adaptor wrapping the given request object. 
     * @throws java.lang.IllegalArgumentException if the request is null
     */
    public ServletRequestWrapper(ServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");   
        }
        this.request = request;
    }


    /**
     * Return the wrapped request object.
     */
    public ServletRequest getRequest() {
        return this.request;
    }


    /**
     * Sets the request object being wrapped. 
     * @throws java.lang.IllegalArgumentException if the request is null.
     */
    public void setRequest(ServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        this.request = request;
    }


    /**
     * The default behavior of this method is to call getAttribute(String name)
     * on the wrapped request object.
     */
    public Object getAttribute(String name) {
        return this.request.getAttribute(name);
    }


    /**
     * The default behavior of this method is to return getAttributeNames()
     * on the wrapped request object.
     */
    public Enumeration<String> getAttributeNames() {
        return this.request.getAttributeNames();
    }    


    /**
     * The default behavior of this method is to return getCharacterEncoding()
     * on the wrapped request object.
     */
    public String getCharacterEncoding() {
        return this.request.getCharacterEncoding();
    }


    /**
     * The default behavior of this method is to set the character encoding
     * on the wrapped request object.
     */
    public void setCharacterEncoding(String enc)
            throws UnsupportedEncodingException {
        this.request.setCharacterEncoding(enc);
    }


    /**
     * The default behavior of this method is to return getContentLength()
     * on the wrapped request object.
     */
    public int getContentLength() {
        return this.request.getContentLength();
    }


    /**
     * The default behavior of this method is to return getContentType()
     * on the wrapped request object.
     */
    public String getContentType() {
        return this.request.getContentType();
    }


    /**
     * The default behavior of this method is to return getInputStream()
     * on the wrapped request object.
     */
    public InputStream getInputStream() throws IOException {
        return this.request.getInputStream();
    }


    /**
     * The default behavior of this method is to return
     * getParameter(String name) on the wrapped request object.
     */
    public String getParameter(String name) {
        return this.request.getParameter(name);
    }


    /**
     * The default behavior of this method is to return getParameterMap()
     * on the wrapped request object.
     */
    public Map<String, String> getParameterMap() {
        return this.request.getParameterMap();
    }


    /**
     * The default behavior of this method is to return getParameterNames()
     * on the wrapped request object.
     */
    public Enumeration<String> getParameterNames() {
        return this.request.getParameterNames();
    }


    /**
     * The default behavior of this method is to return
     * getParameterValues(String name) on the wrapped request object.
     */
    public String[] getParameterValues(String name) {
        return this.request.getParameterValues(name);
    }


    /**
     * The default behavior of this method is to return getProtocol()
     * on the wrapped request object.
     */
    public String getProtocol() {
        return this.request.getProtocol();
    }

    /**
     * The default behavior of this method is to return getServerName()
     * on the wrapped request object.
     */
    public String getServerName() {
        return this.request.getServerName();
    }


    /**
     * The default behavior of this method is to return getServerPort()
     * on the wrapped request object.
     */
    public int getServerPort() {
        return this.request.getServerPort();
    }

    /**
     * The default behavior of this method is to return getRemoteAddr()
     * on the wrapped request object.
     */
    public String getRemoteAddr() {
        return this.request.getRemoteAddr();
    }


    /**
     * The default behavior of this method is to return getRemoteHost()
     * on the wrapped request object.
     */
    public String getRemoteHost() {
        return this.request.getRemoteHost();
    }


    /**
     * The default behavior of this method is to return
     * setAttribute(String name, Object o) on the wrapped request object.
     */
    public void setAttribute(String name, Object o) {
        this.request.setAttribute(name, o);
    }


    /**
     * The default behavior of this method is to call
     * removeAttribute(String name) on the wrapped request object.
     */
    public void removeAttribute(String name) {
        this.request.removeAttribute(name);
    }


    /**
     * The default behavior of this method is to return getLocale()
     * on the wrapped request object.
     */
    public Locale getLocale() {
        return this.request.getLocale();
    }

    /**
     * The default behavior of this method is to return
     * getLocalName() on the wrapped request object.
     *
     * @since Servlet 2.4
     */
    public String getLocalName(){
        return this.request.getLocalName();
    }


    /**
     * The default behavior of this method is to return
     * getLocalAddr() on the wrapped request object.
     *
     * @since Servlet 2.4
     */       
    public String getLocalAddr(){
        return this.request.getLocalAddr();
    }


    /**
     * The default behavior of this method is to return
     * getLocalPort() on the wrapped request object.
     *
     * @since Servlet 2.4
     */
    public int getLocalPort(){
        return this.request.getLocalPort();
    }


    /**
     * Gets the servlet context to which the wrapped servlet request was last
     * dispatched.
     *
     * @return the servlet context to which the wrapped servlet request was
     * last dispatched
     *
     * @since Servlet 3.0
     */
    public ServletContext getServletContext() {
        return request.getServletContext();
    }
 
    /**
     * Checks (recursively) if this ServletRequestWrapper wraps the given
     * {@link ServletRequest} instance.
     *
     * @param wrapped the ServletRequest instance to search for
     *
     * @return true if this ServletRequestWrapper wraps the
     * given ServletRequest instance, false otherwise
     *
     * @since Servlet 3.0
     */
    public boolean isWrapperFor(ServletRequest wrapped) {
        if (request == wrapped) {
            return true;
        } else if (request instanceof ServletRequestWrapper) {
            return ((ServletRequestWrapper) request).isWrapperFor(wrapped);
        } else {
            return false;
        }
    }


    /**
     * Checks (recursively) if this ServletRequestWrapper wraps a
     * {@link ServletRequest} of the given class type.
     *
     * @param wrappedType the ServletRequest class type to
     * search for
     *
     * @return true if this ServletRequestWrapper wraps a
     * ServletRequest of the given class type, false otherwise
     *
     * @throws IllegalArgumentException if the given class does not
     * implement {@link ServletRequest}
     *
     * @since Servlet 3.0
     */
    public boolean isWrapperFor(Class wrappedType) {
        if (!ServletRequest.class.isAssignableFrom(wrappedType)) {
            throw new IllegalArgumentException("Given class " +
                wrappedType.getName() + " not a subinterface of " +
                ServletRequest.class.getName());
        }
        if (wrappedType.isAssignableFrom(request.getClass())) {
            return true;
        } else if (request instanceof ServletRequestWrapper) {
            return ((ServletRequestWrapper) request).isWrapperFor(wrappedType);
        } else {
            return false;
        }
    }

    /**
     * The default behavior of this method is to return getReader()
     * on the wrapped request object.
     */
    public BufferedReader getReader() throws IOException {
        return this.request.getReader();
    }

}
