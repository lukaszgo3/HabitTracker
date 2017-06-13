package com.example.android.habittracker.data;

import android.provider.BaseColumns;

public class RunningContract {

    public static final class RunningEntry implements BaseColumns {
        public final static String TABLE_NAME = "records";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DATE = "date";
        public final static String COLUMN_ACTIVITY_NAME = "activity";
        public final static String COLUMN_TIME = "time";
    }
}