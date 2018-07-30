package com.example.user.smartap;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import static com.example.user.smartap.OnOffActivity.switch1;

public class MainActivity extends AppCompatActivity {

    Intent i;
    SpeechRecognizer mRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 상단 액션 바 감추기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // 메인 액티비티의 4개 버튼 객체 생성
        Button powerBtn = (Button) findViewById(R.id.powerBtn);
        Button reserveBtn = (Button) findViewById(R.id.reserveBtn);
        Button checkBtn = (Button) findViewById(R.id.checkBtn);
        Button listBtn = (Button) findViewById(R.id.listBtn);
        Button micBtn = (Button) findViewById(R.id.micBtn);

        Log.d(this.getClass().getName(),"INTENT START");
        i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);                 //intent 생성.
        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());   //package 호출.
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");                   //음성인식 언어 설정.
        Log.d(this.getClass().getName(),"INTENT END");

        Log.d(this.getClass().getName(),"SpeechRecognizer START");
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);            //음성인식 객체 생성.
        mRecognizer.setRecognitionListener(listener);                          //음성인식 리스너 등록.
        //mRecognizer.startListening(i);                                          //리스너 실행.

        // ON/OFF 버튼 클릭시 해당 액티비티로 이동
        powerBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), OnOffActivity.class);
                        startActivity(intent);
                    }
                }
        );
        // 스마트 예약 버튼 클릭시 해당 액티비티로 이동
        reserveBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ReserveActivity.class);
                        startActivity(intent);
                    }
                }
        );
        // 전력사용량 확인 버튼 클릭시 해당 액티비티로 이동
        checkBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), CheckActivity.class);
                        startActivity(intent);
                    }
                }
        );
        // 예약목록 버튼 클릭시 해당 액티비티로 이동
        listBtn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                        startActivity(intent);
                    }
                }
        );
        // 음성인식 버튼 클릭시 리스너 실행
        micBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        mRecognizer.startListening(i);                                    //리스너 실행.
                    }
                }
        );
    }

    private RecognitionListener listener = new RecognitionListener() {
        @Override   //음성인식 준비가 되었을 때.
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(getApplicationContext(), "음성 입력", Toast.LENGTH_LONG).show();
        }

        @Override   //입력이 시작될 때.
        public void onBeginningOfSpeech() {

        }

        @Override   //입력 소리 변경시.
        public void onRmsChanged(float rmsdB) {

        }

        @Override   //더 많은 소리를 받을 때.
        public void onBufferReceived(byte[] buffer) {

        }

        @Override   //입력이 끝났을 때.
        public void onEndOfSpeech() {

        }

        @Override   //에러 발생시.
        public void onError(int error) {

        }

        @Override   //음성인식 결과.
        public void onResults(Bundle results) {
            Log.d(this.getClass().getName()," onResults START");
            String key = "";                                               //키값 초기화.
            key = SpeechRecognizer.RESULTS_RECOGNITION;                 //키값 받아오기.
            ArrayList<String> mResult = results.getStringArrayList(key);   //음성인식 결과는 ArrayList형태로 받아옴.
            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);
            //tv.setText("음성인식내용: "+rs[0]);                          //화면에 출력.
            Toast.makeText(getApplicationContext(),rs[0], Toast.LENGTH_SHORT).show();   //화면에 출력.

            SharedPreferences.Editor edit=OnOffActivity.pref.edit();
            switch(rs[0]){
                case "켜줘":
                case "켜 줘":
                case "온":
                case "켜":
                    if(((OnOffActivity)OnOffActivity.mContext).switch1.isChecked())
                    {
                        Toast.makeText(getApplicationContext(),"이미 On 상태입니다.",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else
                    {
                        //((OnOffActivity)OnOffActivity.mContext).switch1.setChecked(true);
                        ((OnOffActivity)OnOffActivity.mContext).SwitchThread("1");
                        edit.putBoolean("switch1",true);
                        //((OnOffActivity)OnOffActivity.mContext).onSwitchClicked(switch1);
                        break;
                    }
                case "꺼줘":
                case "꺼 줘":
                case "오프":
                case "꺼":
                    if(((OnOffActivity)OnOffActivity.mContext).switch1.isChecked())
                    {
                        //((OnOffActivity)OnOffActivity.mContext).switch1.setChecked(false);
                        ((OnOffActivity)OnOffActivity.mContext).SwitchThread("2");
                        edit.putBoolean("switch1",false);
                        //((OnOffActivity)OnOffActivity.mContext).onSwitchClicked(switch1);
                        break;
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"이미 Off 상태입니다.",Toast.LENGTH_SHORT).show();
                        break;
                    }

                default:
                    Toast.makeText(getApplicationContext(),"다시 말씀해주시길 바랍니다.",Toast.LENGTH_SHORT).show();
                    break;
            }
            edit.commit();
            //mRecognizer.startListening(i);
            Log.d(this.getClass().getName()," onResults END");
        }

        @Override   //인식결과의 일부가 유효할 때.
        public void onPartialResults(Bundle partialResults) {

        }

        @Override   //미래에 이벤트를 추가하기 위해 미리 예약되어진 함수.
        public void onEvent(int eventType, Bundle params) {

        }
    }; // 음성인식 클릭시 실행
}
