package pony.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * TCP服务器<br>
 * 创建
 * @author WangYong
 *
 * @Date 2015年2月11日
 */
public class TcpServer implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(TcpServer.class);
	private ServerSocketChannel serverChannel;
	private final static int port = ServerConfig.getServerPort();
	private final static boolean blocking = ServerConfig.getServerBlocking();
	
	private Selector selector ;
	
	public TcpServer(){
		try {
			serverChannel = ServerSocketChannel.open();
			serverChannel.socket().bind(new InetSocketAddress(port));
			serverChannel.socket().setReuseAddress(true);
			serverChannel.configureBlocking(blocking);
			
			selector = Selector.open();
			
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			logger.error("Create server socket failed!", e);
		}
	}

	@Override
	public void run() {
		try{
			while(true){
				listen();
			}
		}catch(IOException e){
			logger.error("", e);
		}
	}
	
	private void listen() throws IOException {
		selector.select();
		final Set<SelectionKey> selectionKeys = selector.selectedKeys();
		final Iterator<SelectionKey> iterator = selectionKeys.iterator();
		while(iterator.hasNext()){
			final SelectionKey key = iterator.next();
			process(key);
		}
	}

	private void process(SelectionKey _key) throws IOException {
		SocketChannel client = null;
		if(_key.isAcceptable()){
			serverChannel = (ServerSocketChannel) _key.channel();
			client = serverChannel.accept();
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
		} else if(_key.isReadable()){
			client = (SocketChannel) _key.channel();
			
		}
	}
}
