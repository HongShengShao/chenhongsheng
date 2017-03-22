package com.example.asus.bawie1502l20170316;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus on 2017/3/16.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context context) {
        super(context, "bawie", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL("create table yidong(_id integer primary key autoincrement,author varchar(100) not null,title varchar(100) not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
