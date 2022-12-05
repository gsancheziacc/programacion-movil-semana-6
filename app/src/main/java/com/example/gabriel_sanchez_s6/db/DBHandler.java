package com.example.gabriel_sanchez_s6.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gabriel_sanchez_s6.Util;

import java.util.ArrayList;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "GSANCHEZ_S6";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Ticket";
    public static final String ID_COL= "id";
    public static final String CAR_NUMBER_COL = "car_number";
    public static final String IN_DATETIME_COL = "in_datetime";
    public static final String PLACE_NUMBER_COL = "place_number";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CAR_NUMBER_COL + " TEXT,"
                + IN_DATETIME_COL + " TEXT,"
                + PLACE_NUMBER_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addTicket(String carNumber, String placeNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAR_NUMBER_COL, carNumber);
        values.put(IN_DATETIME_COL, Util.getDateTime());
        values.put(PLACE_NUMBER_COL, placeNumber);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateTicket(int id, String carNumber, String placeNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAR_NUMBER_COL, carNumber);
        values.put(IN_DATETIME_COL, Util.getDateTime());
        values.put(PLACE_NUMBER_COL, placeNumber);

        db.update(TABLE_NAME, values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteTicket(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public ArrayList<TicketModel> getAllTicket() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<TicketModel> courseModalArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                courseModalArrayList.add(new TicketModel(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return courseModalArrayList;
    }

    public void clearDataBase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
