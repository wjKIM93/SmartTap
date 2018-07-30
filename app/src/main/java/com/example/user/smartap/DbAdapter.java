package com.example.user.smartap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-10-28.
 */

public class DbAdapter {
    public Context mCtx;
    SQLiteDatabase mDB;
    DbOpenHelper mDbOpenHelper;

    public DbAdapter(Context context){
        this.mCtx = context;
        mDbOpenHelper = new DbOpenHelper(mCtx);
    }

    public DbAdapter openDB() throws SQLException{
        try{
            mDB = mDbOpenHelper.getWritableDatabase();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return this;
    }

    public void close(){
        try{
            mDbOpenHelper.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public long Add(int num, String date, int type){
        try{
            ContentValues cv = new ContentValues();
            cv.put(DataBases.TAB_NUM, num);
            cv.put(DataBases.DATE, date);
            cv.put(DataBases.TYPE, type);
            return mDB.insert(DataBases.TB_NAME, DataBases.ROW_ID, cv);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public long Update(int id, int num, String date, int type){
        try{
            ContentValues cv = new ContentValues();
            cv.put(DataBases.TAB_NUM, num);
            cv.put(DataBases.DATE, date);
            cv.put(DataBases.TYPE, type);
            return mDB.update(DataBases.TB_NAME, cv, DataBases.ROW_ID +" =?", new String[]{String.valueOf(id)});
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


    public void Delete(int ttab_num,String tdate){
        try{
            mDB.delete(DataBases.TB_NAME, "num=? and date=?",new String[]{String.valueOf(ttab_num),tdate});
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Cursor getAllData(){
        String[] buffer = {DataBases.TAB_NUM, DataBases.DATE, DataBases.TYPE};
        Cursor cursor = mDB.query(DataBases.TB_NAME, buffer, null, null, null, null, null);
        return cursor;
    }
}
