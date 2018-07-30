package com.example.user.smartap;

import android.provider.BaseColumns;

/**
 * Created by user on 2017-10-24.
 */

public class DataBases {
        // Columns
        static final String ROW_ID = "id";
        static final String TAB_NUM ="num";
        static final String DATE = "date";
        static final String TYPE = "type";

        //DB PROPERTIES
        static final String DB_NAME = "reserveList";
        static final String TB_NAME = "list";
        static final int DB_VERSION = '1';

        static final String CREATE_TB = "CREATE TABLE "+ TB_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "num INTEGER NOT NULL, date TEXT NOT NULL, type INTEGER NOT NULL);";
}
