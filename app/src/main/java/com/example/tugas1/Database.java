package com.example.tugas1;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "tugas1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase tugasDatabase) {
        tugasDatabase.execSQL("Create table user(email_user text primary key,name_user text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase tugasDatabase, int oldVersion, int newVersion) {
        tugasDatabase.execSQL("drop table if exists user");
    }

    public boolean masukkanData(String email, String name, String password ){
        SQLiteDatabase tugasDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email_user",email);
        contentValues.put("name_user",name);
        contentValues.put("password",password);
        long ins = tugasDatabase.insert("user",null,contentValues);
        if (ins == -1) return false;
        else return true;
    }

    public boolean checkData(String email){
        SQLiteDatabase tugasDatabase = this.getReadableDatabase();
        Cursor cursor = tugasDatabase.rawQuery("Select * from user where email_user=?",new String[]{email});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    public boolean checkLogin(String email, String password){
        SQLiteDatabase tugasDatabase = this.getReadableDatabase();
        Cursor cursor = tugasDatabase.rawQuery("Select * from user where email_user=? and password=?",new String[]{email,password});
        if (cursor.getCount()>0) return true;
        else return false;
    }
}
