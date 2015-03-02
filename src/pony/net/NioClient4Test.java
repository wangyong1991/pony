package pony.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
/**
 * 该类用于模拟TCP客户端向服务器发送请求
 * @author WangYong
 *
 * @Date 2015年2月25日
 */
public class NioClient4Test implements Runnable {
	private SocketChannel channel;
	private final int MAX_LENGTH = 1024;
	private ByteBuffer readBuf = ByteBuffer.allocate(MAX_LENGTH);
	private ByteBuffer writeBuf = ByteBuffer.allocate(MAX_LENGTH);
	private static int port = 7777;
	private InetSocketAddress address ;
	
	public NioClient4Test() throws IOException{
		address = new InetSocketAddress("localhost", port);
		channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(address);
	}

	@Override
	public void run() {
		while(true){
			
		}
	}
	
	
}
