package com.example.user.smartap;

import android.os.DropBoxManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

//import static com.example.user.smartap.OnOffActivity.recv;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        //선형그래프 생성
        LineChart graph = (LineChart) findViewById(R.id.chart);
        // 그래프에 더미 데이터 입력
        ArrayList<Entry> entries = new ArrayList<>();
        /*entries.add(new Entry(0, 0f));
        entries.add(new Entry(1, 4f));
        entries.add(new Entry(2, 6f));
        entries.add(new Entry(3, 8f));
        entries.add(new Entry(4, 20f));
        entries.add(new Entry(5, 30f));*/
        entries.add(new Entry(0, 0f));

        int cnt=0;
        for(String i: OnOffActivity.recv){
            cnt++;
            entries.add(new Entry(cnt,Float.valueOf(i)));
        }

        //입력된 데이터를 전력소비량으로
        LineDataSet dataSet = new LineDataSet(entries, "전력소비량");
        LineData lineData = new LineData(dataSet);
        //그래프에 데이터설정
        graph.setData(lineData);

        // 상단 액션 바 감추기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

}