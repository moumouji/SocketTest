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
			//server.accept()��һ������������ֱ���ͻ��˷���socket�����Ż�ִ��new Thread����
			Socket socket = server.accept();
			new Thread(new socketconn(socket)).start();
		}
	}

}
