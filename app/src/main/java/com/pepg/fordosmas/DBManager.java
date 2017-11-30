package com.pepg.fordosmas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by pengu on 2017-11-02.
 */

public class DBManager extends SQLiteOpenHelper {

    SQLiteDatabase db;
    Cursor cursor;

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE LOGDATA ( " +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAME TEXT," +
                " PRICE INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void insert(String name, int price) {
        db = getWritableDatabase();
        db.execSQL(" INSERT INTO LOGDATA VALUES ( " +
                " null, " +
                "'" + name + "'," +
                price + ");");
        db.close();
    }

    public ArrayList<Item> getValue(){
        ArrayList<Item> list = new ArrayList<>();
        db = getReadableDatabase();
        cursor = db.rawQuery("SELECT NAME, PRICE FROM LOGDATA;", null);
        while(cursor.moveToNext()){
            list.add(new Item(cursor.getString(0), cursor.getInt(1)));
        }
        cursor.close();
        return list;
    }

    public int getSize() {
        db = getReadableDatabase();
        cursor = db.rawQuery("SELECT _id FROM LOGDATA;", null);
        int result = cursor.getCount();
        cursor.close();
        return result;
    }

    public void delete(int id) {
        db = getWritableDatabase();
        db.execSQL("DELETE FROM LOGDATA WHERE _id = '" + id + "';");
        db.close();
    }
}
