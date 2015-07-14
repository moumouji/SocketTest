package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ServerSocket server = new ServerSocket(30000);
		System.out.println("Connecting...");
		while(true){
			//server.accept()是一个阻塞操作，直到客户端发送socket过来才会执行new Thread操作
			Socket socket = server.accept();
			new Thread(new socketconn(socket)).start();
		}
	}

}
