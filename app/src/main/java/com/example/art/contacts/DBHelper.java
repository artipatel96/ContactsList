package com.example.art.contacts;

import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "NUMBER";
    //public static final String COL_4 = "ID2";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,NUMBER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,number);
        //contentValues.put(COL_4,ID);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }

    public String getSize(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(ID) FROM " + TABLE_NAME;
        Cursor x = db.rawQuery(query,null);
        x.moveToFirst();
        return x.getString(0);
    }

//    public Cursor getID(String name){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select ID from "+TABLE_NAME+ "WHERE NAME = name,null);
//        return res;
//    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id, String name,String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,number);
        //contentValues.put(COL_4,id2);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }


    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }


}

