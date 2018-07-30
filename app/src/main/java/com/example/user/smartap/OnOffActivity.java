package com.example.user.smartap;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class OnOffActivity extends AppCompatActivity {
    // 변수 선언
    public static Switch switch1, switch2, switch3, switch4, switch5;
    Button button;
    boolean is_enable = true;
    public static SharedPreferences pref;
    public static Context mContext;

    public static final String TAG = "TcpClient";
    public static boolean isConnected = false;
    public static ArrayAdapter<String> mConversationArrayAdapter;
    public static ArrayList<String> recv=new ArrayList<>();
    public static ArrayList<String> recv2=new ArrayList<>();

    public static String mServerIP = null;
    public static Socket mSocket = null;
    public static PrintWriter mOut=null;
    public static BufferedReader mIn=null;
    public static Thread mReceiverThread = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_off);

        // 상단 액션 바 감추기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mContext=this;

       /* recv2.add("1");
        recv2.add("2");
        recv2.add("3");*/

        // SharedPreference 객체를 통해 설정값 불러오기
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

        switch1 = (Switch)findViewById(R.id.switch1);
        switch2 = (Switch)findViewById(R.id.switch2);
        switch3 = (Switch)findViewById(R.id.switch3);
        switch4 = (Switch)findViewById(R.id.switch4);
        switch5 = (Switch)findViewById(R.id.switch5);
        button = (Button)findViewById(R.id.button1);

        // 저장된 값 불러오기
        Boolean sw1 = pref.getBoolean("switch1", true);
        Boolean sw2 = pref.getBoolean("switch2", false);
        Boolean sw3 = pref.getBoolean("switch3", false);
        Boolean sw4 = pref.getBoolean("switch4", false);
        Boolean sw5 = pref.getBoolean("switch5", false);

        switch1.setChecked(sw1);
        switch2.setChecked(sw2);
        switch3.setChecked(sw3);
        switch4.setChecked(sw4);
        switch5.setChecked(sw5);

        new Thread(new ConnectThread("172.20.10.2", 8090)).start();
//        String sendMessage="hello esp";
        /*
        if (!isConnected) showErrorDialog("서버로 접속된후 다시 해보세요.");
        else {
            new Thread(new OnOffActivity.SenderThread(sendMessage)).start();
        }
*/
        //setOnCheckedChangeListener -> 스위치 버튼 바뀔 때 동작
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (buttonView.isChecked()){
                    String sendMessage = "1";
                    if (!isConnected)
                        Toast.makeText(getApplicationContext(),"연결이 안된 상태입니다.",Toast.LENGTH_SHORT).show();
                        //showErrorDialog("서버로 접속된후 다시 해보세요.");
                    else {
                        new Thread(new OnOffActivity.SenderThread(sendMessage)).start();
                    }
                }
                else{
                    String sendMessage = "2";
                    if (!isConnected)
                        Toast.makeText(getApplicationContext(),"연결이 안된 상태입니다.",Toast.LENGTH_SHORT).show();
                        //    showErrorDialog("서버로 접속된후 다시 해보세요.");
                    else {
                        new Thread(new OnOffActivity.SenderThread(sendMessage)).start();
                    }
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (buttonView.isChecked()){
                }
                else{
                }
            }
        });

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (buttonView.isChecked()){
                }
                else{
                }
            }
        });

        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (buttonView.isChecked()){
                }
                else{
                }
            }
        });

        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (buttonView.isChecked()){
                    // 전체 전원버튼 on인 경우
                    switch1.setChecked(true);
                    switch2.setChecked(true);
                    switch3.setChecked(true);
                    switch4.setChecked(true);
                }
                else{
                    switch1.setChecked(false);
                    switch2.setChecked(false);
                    switch3.setChecked(false);
                    switch4.setChecked(false);
                }
            }
        });
    }

    // 액티비티 종료시 상태 저장위한 메소드
    public void onStop(){
        super.onStop();
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        // 상태 저장을 위해 Editor 이용
        SharedPreferences.Editor editor = pref.edit();

        switch1 = (Switch)findViewById(R.id.switch1);
        switch2 = (Switch)findViewById(R.id.switch2);
        switch3 = (Switch)findViewById(R.id.switch3);
        switch4 = (Switch)findViewById(R.id.switch4);
        switch5 = (Switch)findViewById(R.id.switch5);

        // 저장할 값 입력
        editor.putBoolean("switch1", switch1.isChecked());
        editor.putBoolean("switch2", switch2.isChecked());
        editor.putBoolean("switch3", switch3.isChecked());
        editor.putBoolean("switch4", switch4.isChecked());
        editor.putBoolean("switch5", switch5.isChecked());

        // 저장
        editor.commit();
    }

    // 스위치 클릭시 아두이노 제어 메소드 넣으면 될듯
    public void onSwitchClicked(View view) {
        switch(view.getId()){
            case R.id.switch1:
                if(switch1.isChecked()) {
                    String sendMessage = "1";
                    if (!isConnected)
                        Toast.makeText(getApplicationContext(),"연결이 안된 상태입니다.",Toast.LENGTH_SHORT).show();
                        //showErrorDialog("서버로 접속된후 다시 해보세요.");
                    else {
                        new Thread(new OnOffActivity.SenderThread(sendMessage)).start();
                    }
                }
                else {
                    String sendMessage = "2";
                    if (!isConnected)
                        Toast.makeText(getApplicationContext(),"연결이 안된 상태입니다.",Toast.LENGTH_SHORT).show();
                        //showErrorDialog("서버로 접속된후 다시 해보세요.");
                    else {
                        new Thread(new OnOffActivity.SenderThread(sendMessage)).start();
                    }
                }
                break;
            case R.id.switch2:
                if(switch2.isChecked()) {
                }
                else {
                }
                break;
            case R.id.switch3:
                if(switch3.isChecked()) {
                }
                else{
                }
                break;
            case R.id.switch4:
                if(switch4.isChecked()) {
                }
                else{
                }
                break;
            case R.id.switch5:
                if(switch5.isChecked()) {
                }
                else{
                }
                break;
        }
    }

    // 버튼 클릭 이벤트
    public void button_click(View view){
        if(is_enable == true) {
            is_enable = false;
            button.setText("Enabled all switch");
        }
        else{
            is_enable = true;
            button.setText("Disabled all switch");
        }
        switch1.setEnabled(is_enable);
        switch2.setEnabled(is_enable);
        switch3.setEnabled(is_enable);
        switch4.setEnabled(is_enable);
        switch5.setEnabled(is_enable);
    }

    public void SwitchThread(String snd){
        String sendMessage=snd;
        new Thread(new OnOffActivity.SenderThread(sendMessage)).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //isConnected = false;
    }

    public class ConnectThread implements Runnable {

        private String serverIP;
        private int serverPort;

        ConnectThread(String ip, int port) {
            serverIP = ip;
            serverPort = port;
            //Toast.makeText(getApplication(), serverIP+","+serverPort, Toast.LENGTH_SHORT).show();
            //mConnectionStatus.setText("connecting to " + serverIP + ".......");
        }

        @Override
        public void run() {
            try {
                mSocket = new Socket(serverIP, serverPort);
                if(mSocket == null)
                    Toast.makeText(getApplication(), "철맨바보", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplication(), "철맨천재", Toast.LENGTH_SHORT).show();
                //ReceiverThread: java.net.SocketTimeoutException: Read timed out 때문에 주석처리
                //mSocket.setSoTimeout(3000);

                mServerIP = mSocket.getRemoteSocketAddress().toString();

            } catch( UnknownHostException e )
            {
                Log.d(TAG,  "ConnectThread: can't find host");
            }
            catch( SocketTimeoutException e )
            {
                Log.d(TAG, "ConnectThread: timeout");
            }
            catch (Exception e) {
                Log.e(TAG, ("ConnectThread:" + e.getMessage()));
            }


            if (mSocket != null) {

                try {

                    mOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream(), "UTF-8")), true);
                    mIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "UTF-8"));
                    isConnected = true;
                } catch (IOException e) {
                    Log.e(TAG, ("ConnectThread:" + e.getMessage()));
                }
            }


            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    if (isConnected) {

                        Log.d(TAG, "connected to " + serverIP);
                        //mConnectionStatus.setText("connected to " + serverIP);

                        mReceiverThread = new Thread(new ReceiverThread());
                        mReceiverThread.start();
                    }else{

                        Log.d(TAG, "failed to connect to server " + serverIP);
                        //mConnectionStatus.setText("failed to connect to server "  + serverIP);
                    }

                }
            });
        }
    }

    public class SenderThread implements Runnable {

        private String msg;

        SenderThread(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {

            mOut.println(this.msg);
            mOut.flush();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "send message: " + msg);
                    //mConversationArrayAdapter.insert("Me - " + msg, 0);
                }
            });
        }
    }

    public class ReceiverThread implements Runnable {

        @Override
        public void run() {

            try {

                while (isConnected) {

                    if ( mIn ==  null ) {

                        Log.d(TAG, "ReceiverThread: mIn is null");
                        break;
                    }

                    final String recvMessage =  mIn.readLine();

                    if (recvMessage != null) {

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                Log.d(TAG, "recv message: "+recvMessage);
                                //mConversationArrayAdapter.insert(mServerIP + " - " + recvMessage, 0);
                                recv.add(recvMessage);
                            }
                        });
                    }
                }

                Log.d(TAG, "ReceiverThread: thread has exited");
                if (mOut != null) {
                    mOut.flush();
                    mOut.close();
                }

                mIn = null;
                mOut = null;

                if (mSocket != null) {
                    try {
                        mSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (IOException e) {

                Log.e(TAG, "ReceiverThread: "+ e);
            }
        }
    }

    /*public void showErrorDialog(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.create().show();
    }*/
}