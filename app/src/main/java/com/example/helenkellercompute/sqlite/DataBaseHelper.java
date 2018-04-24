package com.example.helenkellercompute.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Helen keller compute on 2017/10/6.
 */

public class DataBaseHelper extends SQLiteOpenHelper {//继承这个类 SQLiteOpenHelper
    private static final int VERSION = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.print("create table USER");
        db.execSQL("create table user1(age int,name varchar(20))");
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public  DataBaseHelper(Context context, String name){
        this(context,name,null,VERSION);
    }
    public  DataBaseHelper(Context context, String name,int version ){
        this(context,name,null,version);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        System.out.print("onUpgrade");
    }
}
