package com.pepg.fordosmas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                " NAME TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

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
