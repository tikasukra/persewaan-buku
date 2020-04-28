package com.learn.app.persewaanbuku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "persewaan-buku.db";
    public static final String TABLE_NAME = "tbl_buku";
    public static final String COL_1 = "esbn";
    public static final String COL_2 = "judul_buku";
    public static final String COL_3 = "pengarang";
    public static final String COL_4 = "penerbit";
    public static final String COL_5 = "kategori";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (esbn TEXT PRIMARY KEY,judul_buku TEXT,pengarang TEXT, penerbit TEXT,kategori TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String esbn,String judul_buku,String pengarang,String penerbit,String kategori) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,esbn);
        contentValues.put(COL_2,judul_buku);
        contentValues.put(COL_3,pengarang);
        contentValues.put(COL_4,penerbit);
        contentValues.put(COL_5,kategori);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String esbn,String judul_buku,String pengarang,String penerbit,String kategori) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,esbn);
        contentValues.put(COL_2,judul_buku);
        contentValues.put(COL_3,pengarang);
        contentValues.put(COL_4,penerbit);
        contentValues.put(COL_5,kategori);
        int result = db.update(TABLE_NAME, contentValues, "esbn = ?",new String[] { esbn });
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean deleteData(String esbn) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME,"esbn = ?" ,new String[] { esbn });
        if(result == 0)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


}
