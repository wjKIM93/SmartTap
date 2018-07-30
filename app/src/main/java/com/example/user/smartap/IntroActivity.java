package com.example.user.smartap;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;

public class IntroActivity extends AppCompatActivity {

    public static final String TAG = "_test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        checkPermission();

        Manager.getInstance();

        // 상단 액션 바 감추기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    // 안드로이드 os 6.0(마쉬멜로우) 이상의 운영체제에 대해서 사용자에게 권한요청 처리함.
    private void checkPermission() {

        Log.d(TAG,"checkPermission() ");

        if (Build.VERSION.SDK_INT >= 23) {

            Log.d(TAG,"checkSelfPermission() , 1");
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                Log.d(TAG,"requestPermissions() , 2");
                // 권한이 없을 경우
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, // wifi 기반 위치
                        Manifest.permission.ACCESS_COARSE_LOCATION, // 기지국 기반 위치
                        Manifest.permission.BLUETOOTH,
                        Manifest.permission.BLUETOOTH_ADMIN,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }, 119);


            } else {
                Log.d(TAG,"checkPermission() , 사용자에게 수락받은경우");
                // 사용자에게 수락받은경우
                start_loding();

            }

        } else {
            Log.d(TAG,"checkPermission() , 하위버젼인경우 패스");
            // 마쉬멜로우(6) 보다 낮은 운영체제는 사용자에게 허락받을필요없음.
            start_loding();
        }

    }

    void start_loding() {
        new loding().execute("");
    }

    void startActivity() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d(TAG,"onRequestPermissionsResult() ");

        /*
        // wifi 기반 위치 퍼미션 거부된 경우
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(),grantResults[0] + ": [ACCESS_FINE_LOCATION] 허락을 요구합니다.", Toast.LENGTH_SHORT).show();
            Log.d(Manager.TAG,"onRequestPermissionsResult() , [ACCESS_FINE_LOCATION] : 거부");
            return;
        }
        // 기지국 기반 위치 퍼미션 거부된 경우
        if (grantResults[1] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(),grantResults[1] + ": [ACCESS_COARSE_LOCATION] 허락을 요구합니다.", Toast.LENGTH_SHORT).show();
            Log.d(Manager.TAG,"onRequestPermissionsResult() , [ACCESS_COARSE_LOCATION] : 거부");
            return;
        }
        */

        start_loding();

    }


    class loding extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            startActivity();
        }
    }
}
