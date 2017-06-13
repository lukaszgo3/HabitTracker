package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.RunningContract.RunningEntry;

public class RunningDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "runningrecords.db";
    private static final int DATABASE_VERSION = 1;

    public RunningDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_RECORDS_TABLE = "CREATE TABLE " + RunningEntry.TABLE_NAME + " ("
                + RunningEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RunningEntry.COLUMN_DATE + " DATETIME NOT NULL DEFAULT CURRENT_DATE, "
                + RunningEntry.COLUMN_ACTIVITY_NAME + " TEXT, "
                + RunningEntry.COLUMN_TIME + " DOUBLE NOT NULL DEFAULT 0);";
        db.execSQL(SQL_CREATE_RECORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}