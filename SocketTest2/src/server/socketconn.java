package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.channels.ShutdownChannelGroupException;

public class socketconn implements Runnable{

	Socket socket = null;
	
	public socketconn(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String line;
        String txt = "";
        try {
        	System.out.println("Receiving...");
        	
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //¶Á
            line = bufferedReader.readLine();
            while (line  != null) {
            	txt += line;
                System.out.println("Received txt : " + line);
                line = bufferedReader.readLine();
            }
            socket.shutdownInput();
            
			bufferedWriter.write(txt);
            bufferedWriter.flush();
            socket.shutdownOutput();
            
            bufferedWriter.close();
            bufferedReader.close();
            socket.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
