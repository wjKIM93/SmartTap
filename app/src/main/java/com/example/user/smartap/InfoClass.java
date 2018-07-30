package com.example.user.smartap;

import java.util.Date;

/**
 * Created by user on 2017-10-24.
 */

/*
    데이터베이스에 저장 할 정보
 */
public class InfoClass {
    public int _id;
    public int tab_num;
   // public String day;
   // public long time;
    public String mDate;
    public int type;

    public InfoClass(){}

   /* public InfoClass(int _id, int tab_num, String day, long time, String type){
        this._id = _id;
        this.tab_num = tab_num;
        this.day = day;
        this.time = time;
        this.type = type;
    } */

    public InfoClass(int tab_num, String mDate, int type){
        this.tab_num = tab_num;
        this.mDate = mDate;
        this.type = type;
    }

   public InfoClass(int _id, int tab_num, String mDate, int type){
       this._id = _id;
       this.tab_num = tab_num;
       this.mDate = mDate;
       this.type = type;
   }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getTab_num() {
        return tab_num;
    }

    public void setTab_num(int tab_num) {
        this.tab_num = tab_num;
    }

  /*  public String getDay() {
        return day;
    }

  //    public void setDay(String day) {
        this.day = day;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
*/

    public String getmDate() { return mDate; }

    public void setmDate(String mDate) { this.mDate = mDate; }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
