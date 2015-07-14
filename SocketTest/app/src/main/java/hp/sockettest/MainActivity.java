package hp.sockettest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import android.os.Handler;


public class MainActivity extends Activity {

    public static final int UPDATEUI = 1;
    private static EditText editText;
    private Button bt1;
    private static TextView tv;
    private String tmp;
    private SocketConnect socket;

    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == UPDATEUI){
                String txt = msg.getData().getString("TXT");
                Log.i("scoketTest", txt);
                tv.append("\n"+txt);
                editText.setText("");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edittext);
        tv = (TextView) findViewById(R.id.tv);
        bt1 = (Button) findViewById(R.id.bt1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bt1Colick" , editText.getText().toString());
                socket = new SocketConnect(editText.getText().toString());
            }
        });
    }
}
