package pony;

import pony.net.TcpServer;

public class Main {

	public static void main(String[] args) {
		TcpServer server = new TcpServer();
		Thread thread = new Thread(server);
		thread.start();
	}

}
