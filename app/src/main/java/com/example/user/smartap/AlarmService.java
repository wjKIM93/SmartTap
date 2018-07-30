package com.example.user.smartap;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.widget.Toast;

public class AlarmService extends Service {
    public AlarmService() {
    }

    @Override
    public void onCreate() {
        //super.onCreate();
        Thread thr = new Thread(null, mTask, "AlarmService");
        thr.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this,"알람이 울립니다.", Toast.LENGTH_SHORT).show();
        //return START_NOT_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    Runnable mTask = new Runnable(){
        public void run(){
            // 알람 발생 시 마다 실행할 내용
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}