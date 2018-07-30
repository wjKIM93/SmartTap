package com.example.user.smartap;

import android.database.Cursor;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<InfoClass> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    DbAdapter db;
    InfoClass info;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        db = new DbAdapter(this);
        db.openDB();
        Cursor cursor = db.getAllData();
        cursor.moveToFirst();
        do {
            InfoClass info = new InfoClass(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            arrayList.add(info);
        } while (cursor.moveToNext());

        adapter = new Adapter_Message(arrayList);
        recyclerView.setAdapter(adapter);
    }

    // 어댑터 클래스
    class Adapter_Message extends RecyclerView.Adapter<Adapter_Message.ViewHolder> {

        ArrayList<InfoClass> arrayList = new ArrayList<>();

        public Adapter_Message(ArrayList<InfoClass> arrayList){
            this.arrayList = arrayList;
        }

        @Override
        public Adapter_Message.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, null);

            return new Adapter_Message.ViewHolder(v);
        }

        @Override
        // 재활용 되는 뷰가 호출하는 메서드, 뷰 홀더 전달, Adapter는 position 데이터 결합
        public void onBindViewHolder(Adapter_Message.ViewHolder holder, int position) {
            info = arrayList.get(position);
            StringBuffer buffer = new StringBuffer();
            buffer.append("탭 번호: " + info.getTab_num() + "번     ");
            if(info.getType() == 1)
                buffer.append("예약상태: ON     ");
            else
                buffer.append("예약상태: OFF    ");
            buffer.append(info.getmDate());

            holder.message.setText(buffer);
        }
        @Override
        // 데이터 갯수 반환
        public int getItemCount() {
            return arrayList.size();
        }

        public void delete(int position){
            info = arrayList.get(position);
            db.openDB();
            db.Delete(info.getTab_num(),info.getmDate());
            db.close();
            arrayList.remove(position);
            notifyItemRemoved(position);
            adapter.notifyDataSetChanged();
        }

        // Adapter에 대한 ViewHolder 구현 및 명시
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView message ;
            Button del;
            CardView cardview;

            public ViewHolder(View itemView) {
                super(itemView);
                cardview = (CardView) itemView.findViewById(R.id.cardview);
                message = (TextView) itemView.findViewById(R.id.message);
                del = (Button) itemView.findViewById(R.id.del);
                del.setOnClickListener(this);
            }

            @Override
            public void onClick(View v){
                delete(getLayoutPosition());
            }
        }
    }
}