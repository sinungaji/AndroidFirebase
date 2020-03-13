package com.example.tugas1;

import androidx.annotation.Nullable;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "tugas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email_user text primary key,nama_user text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean masukkanData(String email, String nama, String pass ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email_user",email);
        contentValues.put("nama_user",nama);
        contentValues.put("password",pass);
        long ins = db.insert("user",null,contentValues);
        if (ins == -1) return false;
        else return true;
    }

    public boolean checkData(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email_user=?",new String[]{email});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    public boolean checkLogin(String email, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email_user=? and password=?",new String[]{email,pass});
        if (cursor.getCount()>0) return true;
        else return false;
    }
}
