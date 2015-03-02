package pony.net;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pony.util.Args;

public class TcpClient {
	private final static Logger logger = LoggerFactory.getLogger(TcpClient.class);
	
	private SocketChannel channel;
	private SelectionKey key;
	private String remoteIp;
	private int remotePort;
	
	private String id;
	private String listenerId;
	private boolean waitingClose = false;
	
	public TcpClient(
			final String _id,
			final String _listenerId,
			final SocketChannel _channel){
		Args.notNull(_id, "_id");
		Args.notNull(_listenerId, "_listenerId");
		Args.notNull(_channel, "SocketChannel");
		
		this.id = _id;
		this.listenerId = _listenerId;
		this.channel = _channel;
	}
}
