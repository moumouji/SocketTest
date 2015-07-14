package hp.sockettest;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by hp on 2015/7/13.
 */
public class SocketConnect extends Thread {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private static final String IP ="192.16.137.5";
    private static final int PORT = 30000;
    boolean IsStart = false;
    private String txt;

    public SocketConnect(String txt){
        Log.i("SocketConnect", txt);
        this.txt = txt;
        socketStart();
    }

    public void socketStart(){
       Thread thread = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   IsStart = true;
                   socket = new Socket(IP, PORT);
                   bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                   bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                   Log.i("SocketConnect", "connecting");
                   //输出
                   bufferedWriter.write(txt);
                   bufferedWriter.flush();
                   //一定要shutdownOutput，不然服务器那边readline()会阻塞
                   socket.shutdownOutput();
                   //读入
                   Message msg = new Message();
                   msg.what = MainActivity.UPDATEUI;
                   Bundle bundle = new Bundle();
                   String tmp = bufferedReader.readLine();
                   Log.i("BufferReader", tmp);
                   bundle.putString("TXT", tmp);
                   msg.setData(bundle);
                   MainActivity.handler.sendMessage(msg);

                   bufferedReader.close();
                   bufferedWriter.close();
                   socket.close();

               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       });
        thread.start();
    }
}
