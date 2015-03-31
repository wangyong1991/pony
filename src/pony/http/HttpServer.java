package pony.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class HttpServer implements Runnable{
	/**
     * Maximum time to wait on Socket.getInputStream().read() (in milliseconds)
     * This is required as the Keep-Alive HTTP connections would otherwise
     * block the socket reading thread forever (or as long the browser is open).
     */
	public static final int SOCKET_READ_TIMEOUT = 5000;
	
	private final String hostname;
	
	private final int port;
	
	private ServerSocket serverSocket;
	
	private Set<Socket> openConnections = new HashSet<Socket>();
	
	/**
     * Constructs an HTTP server on given port.
     */
    public HttpServer(int port) {
        this(null, port);
    }

    /**
     * Constructs an HTTP server on given hostname and port.
     */
    public HttpServer(final String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
    
    
    public void run() {
		try {
			this.serverSocket = new ServerSocket();
			this.serverSocket.bind((hostname != null) ? new InetSocketAddress(hostname, port) : new InetSocketAddress(port));
			
			while(true){
				final Socket socket = serverSocket.accept();
				socket.setSoTimeout(SOCKET_READ_TIMEOUT);
				final InputStream inputStream = socket.getInputStream();
				final OutputStream outputStream = socket.getOutputStream();
				
				final HttpServletRequest req = new HttpServletRequestImpl(socket);
				final HttpServletResponse resp = new HttpServletResponseImpl(socket);
				ServletMapper.execute(req, resp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    /**
     * Registers that a new connection has been set up.
     *
     * @param socket the {@link Socket} for the connection.
     */
    public synchronized void registerConnection(Socket socket) {
        openConnections.add(socket);
    }

    /**
     * Registers that a connection has been closed
     *
     * @param socket
     *            the {@link Socket} for the connection.
     */
    public synchronized void unRegisterConnection(Socket socket) {
        openConnections.remove(socket);
    }

    /**
     * Forcibly closes all connections that are open.
     */
    public synchronized void closeAllConnections() {
        for (Socket socket : openConnections) {
            safeClose(socket);
        }
    }
    

    private static final void safeClose(final ServerSocket serverSocket){
    	if (serverSocket != null) {
            try {
            	serverSocket.close();
            } catch (IOException e) {
            }
        }
    }
    
    private static final void safeClose(Socket closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
