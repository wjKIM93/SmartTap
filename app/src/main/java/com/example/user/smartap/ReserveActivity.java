package com.example.user.smartap;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.IDNA;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ReserveActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;  // 멀티탭 버튼 1 ~ 4 번
    Button dBtn1, dBtn2, dBtn3, dBtn4, dBtn5, dBtn6, dBtn7; // 요일에 해당하는 버튼
    Button resBtn; // 예약 버튼
    TimePicker mTime;   //TimePicker
    int nHourDay,nMinute;   //Hour & Minute to Reserve
    int count=0;        //variable to multiple alarm.
    Switch resSwtich; // ON / OFF 설정
    int yoil=0; // 요일
    int current_Btn=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        // 상단 액션 바 감추기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        resSwtich = (Switch)findViewById(R.id.switch20);

        resSwtich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (buttonView.isChecked()){
                    resSwtich.setChecked(true);
                }
                else{
                    resSwtich.setChecked(false);
                }
            }
        });
    }

    public void resSwitchClicked(View view){
        resSwtich = (Switch)findViewById(R.id.switch20);
        if(resSwtich.isChecked()){
        }
        else{
        }
    }
    public void btnClicked(View view){
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn4 = (Button)findViewById(R.id.button4);

        if(view.getId() == R.id.button1){
            if(btn1.isSelected()){
                btn1.setSelected(false);
                current_Btn = -1;
            }
            else {
                btn1.setSelected(true);
                btn2.setSelected(false);
                btn3.setSelected(false);
                btn4.setSelected(false);
                current_Btn = 1;
            }
        }
        else if(view.getId() == R.id.button2){
            if(btn2.isSelected()){
                btn2.setSelected(false);
                current_Btn = -1;
            }
            else {
                btn1.setSelected(false);
                btn2.setSelected(true);
                btn3.setSelected(false);
                btn4.setSelected(false);
                current_Btn = 2;
            }
        }
        else if(view.getId() == R.id.button3){
            if(btn3.isSelected()){
                btn3.setSelected(false);
                current_Btn = -1;
            }
            else {
                btn1.setSelected(false);
                btn2.setSelected(false);
                btn3.setSelected(true);
                btn4.setSelected(false);
                current_Btn = 3;
            }
        }
        else{
            if(btn4.isSelected()){
                btn4.setSelected(false);
                current_Btn = -1;
            }
            else {
                btn1.setSelected(false);
                btn2.setSelected(false);
                btn3.setSelected(false);
                btn4.setSelected(true);
                current_Btn = 4;
            }
        }

    }

    public void dayBtnClicked(View view){
        dBtn1 = (Button)findViewById(R.id.dButton1);
        dBtn2 = (Button)findViewById(R.id.dButton2);
        dBtn3 = (Button)findViewById(R.id.dButton3);
        dBtn4 = (Button)findViewById(R.id.dButton4);
        dBtn5 = (Button)findViewById(R.id.dButton5);
        dBtn6 = (Button)findViewById(R.id.dButton6);
        dBtn7 = (Button)findViewById(R.id.dButton7);


        if(view.getId() == R.id.dButton1){
            if(dBtn1.isSelected()){
                dBtn1.setSelected(false);
                yoil=0;
            }
            else {
                dBtn1.setSelected(true);
                dBtn2.setSelected(false);
                dBtn3.setSelected(false);
                dBtn4.setSelected(false);
                dBtn5.setSelected(false);
                dBtn6.setSelected(false);
                dBtn7.setSelected(false);
                yoil=2;
            }
        }
        else if(view.getId() == R.id.dButton2){
            if(dBtn2.isSelected()){
                dBtn2.setSelected(false);
                yoil=0;
            }
            else {
                dBtn1.setSelected(false);
                dBtn2.setSelected(true);
                dBtn3.setSelected(false);
                dBtn4.setSelected(false);
                dBtn5.setSelected(false);
                dBtn6.setSelected(false);
                dBtn7.setSelected(false);
                yoil=3;
            }
        }
        else if(view.getId() == R.id.dButton3){
            if(dBtn3.isSelected()){
                dBtn3.setSelected(false);
                yoil=0;
            }
            else {
                dBtn1.setSelected(false);
                dBtn2.setSelected(false);
                dBtn3.setSelected(true);
                dBtn4.setSelected(false);
                dBtn5.setSelected(false);
                dBtn6.setSelected(false);
                dBtn7.setSelected(false);
                yoil=4;
            }
        }
        else if(view.getId() == R.id.dButton4){
            if(dBtn4.isSelected()){
                dBtn4.setSelected(false);
                yoil=0;
            }
            else {
                dBtn1.setSelected(false);
                dBtn2.setSelected(false);
                dBtn3.setSelected(false);
                dBtn4.setSelected(true);
                dBtn5.setSelected(false);
                dBtn6.setSelected(false);
                dBtn7.setSelected(false);
                yoil=5;
            }
        }
        else if(view.getId() == R.id.dButton5){
            if(dBtn5.isSelected()){
                dBtn5.setSelected(false);
                yoil=0;
            }
            else {
                dBtn1.setSelected(false);
                dBtn2.setSelected(false);
                dBtn3.setSelected(false);
                dBtn4.setSelected(false);
                dBtn5.setSelected(true);
                dBtn6.setSelected(false);
                dBtn7.setSelected(false);
                yoil=6;
            }
        }
        else if(view.getId() == R.id.dButton6){
            if(dBtn6.isSelected()){
                dBtn6.setSelected(false);
                yoil=0;
            }
            else {
                dBtn1.setSelected(false);
                dBtn2.setSelected(false);
                dBtn3.setSelected(false);
                dBtn4.setSelected(false);
                dBtn5.setSelected(false);
                dBtn6.setSelected(true);
                dBtn7.setSelected(false);
                yoil=7;
            }
        }
        else{
            if(dBtn7.isSelected()){
                dBtn7.setSelected(false);
                yoil=0;
            }
            else {
                dBtn1.setSelected(false);
                dBtn2.setSelected(false);
                dBtn3.setSelected(false);
                dBtn4.setSelected(false);
                dBtn5.setSelected(false);
                dBtn6.setSelected(false);
                dBtn7.setSelected(true);
                yoil=1;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void resBtnClicked(View view){
        resBtn = (Button)findViewById(R.id.resBtn);
        Calendar mCalendar= Calendar.getInstance(); //현재 날짜 정보
        mTime=(TimePicker)findViewById(R.id.timePicker1); // 예약 날짜 정보
        nHourDay=mTime.getCurrentHour(); //예약 시간
        nMinute=mTime.getCurrentMinute(); //예약 분
        Toast.makeText(getApplicationContext(),"알람 예약", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(ReserveActivity.this,AlarmService.class);
        //startService(intent);
        //int CurrentDay=mCalendar.DAY_OF_WEEK;
        int nowyoil = mCalendar.get(Calendar.DAY_OF_WEEK);//현재요일 1~7
        int year = mCalendar.get(Calendar.YEAR);
        int mon =mCalendar.get(Calendar.MONTH);//현재 월
        int day = mCalendar.get(Calendar.DAY_OF_MONTH); //현재 일
        int nowhour=mCalendar.get(Calendar.HOUR_OF_DAY); //현재 시간
        int nowminute=mCalendar.get(Calendar.MINUTE); //현재 분

        //번호 선택 안한 경우
        if ( current_Btn == -1 ) {
            Toast.makeText(getBaseContext(), "번호가 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 날짜 선택 안한 경우
        else if(yoil==0){
            Toast.makeText(getBaseContext(), "요일이 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            count++;
            if (nowyoil == yoil) //같은 요일인 경우
            {
                if (nHourDay > nowhour) // 예약 시간 > 현재 시간
                {
                    mCalendar.set(year,mon,day,nHourDay,nMinute,0);


                } else if (nHourDay == nowhour) // 예약 시간 = 현재 시간
                {
                    if (nMinute > nowminute) // 예약 분> 현재 분
                    {
                        mCalendar.set(year,mon,day,nHourDay,nMinute,0);

                    } else {
                        mCalendar.set(year,mon,day+7,nHourDay,nMinute,0);

                    }

                } else { // 예약시간 < 현재시간
                    mCalendar.set(year,mon,day+7,nHourDay,nMinute,0);

                }
            } else {
                final int co = 7;
                int cout = 0;
                cout = (co - nowyoil + yoil) % 7;
                mCalendar.set(year,mon,day+cout,nHourDay,nMinute,0);

            }
            yoil=0;
            year=mCalendar.get(mCalendar.YEAR);
            mon=mCalendar.get(mCalendar.MONTH)+1    ;
            day=mCalendar.get(mCalendar.DATE);
            nowhour=mCalendar.get(mCalendar.HOUR_OF_DAY);
            nowminute=mCalendar.get(mCalendar.MINUTE);
            Toast.makeText(getApplicationContext(), "알람 예약", Toast.LENGTH_SHORT).show();
            //알람매니저를 이용한 알람등록
            Intent mAlarmIntent = new Intent("com.example.user.smartap.ALARM_START");
            PendingIntent mPendingIntent = PendingIntent.getBroadcast(this, count, mAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            mAlarmManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), mPendingIntent);

            DbAdapter db = new DbAdapter(this);
            db.openDB();

            String date = year+"년"+mon+"월"+day+"일 "+nHourDay+"시"+nMinute+"분";
            int type = 0;
            if(resSwtich.isChecked())
                type = 1 ;
            else
                type = 0;

            long result = db.Add(current_Btn, date, type);
            db.close();
        }

        //mCalendar.set(Calendar.HOUR_OF_DAY,nHourDay);
        //mCalendar.set(Calendar.MINUTE,nMinute);
        //mCalendar.set(Calendar.SECOND,0);
    }

}