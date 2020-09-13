package com.india.audio_music;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqlitedatabase_use extends SQLiteOpenHelper {
    private static final String DB_NAME="favorite_music";

    public sqlitedatabase_use( Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE PRODUCTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, ID TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "  + "PRODUCTS");
        db.close();
    }
    public boolean insertdata(String title, String key){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("TITLE",title);
        contentValues.put("ID",key);
        long result=db.insert("PRODUCTS",null,contentValues);
        db.close();
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor readdata(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from PRODUCTS",null);
        return cursor;
    }

}