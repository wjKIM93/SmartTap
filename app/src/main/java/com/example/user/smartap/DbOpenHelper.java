package com.example.user.smartap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by user on 2017-10-24.
 */

public class DbOpenHelper extends SQLiteOpenHelper{
    public DbOpenHelper(Context context){
        super(context, DataBases.DB_NAME, null, DataBases.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        try{
            db.execSQL(DataBases.CREATE_TB);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + DataBases.TB_NAME + " ;");
        onCreate(db);
    }
}
