package com.example.android.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.android.habittracker.data.RunningContract.RunningEntry;
import com.example.android.habittracker.data.RunningDbHelper;

public class MainActivity extends AppCompatActivity {

    private RunningDbHelper mRunningDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fabId);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        mRunningDbHelper = new RunningDbHelper(this);
        displayDb();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDb();
    }

    private Cursor cursorMethod() {
        SQLiteDatabase db = mRunningDbHelper.getReadableDatabase();

        String[] projection = {
                RunningEntry._ID,
                RunningEntry.COLUMN_DATE,
                RunningEntry.COLUMN_ACTIVITY_NAME,
                RunningEntry.COLUMN_TIME
        };

        return db.query(
                RunningEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }

    private void displayDb() {
        Cursor cursor = cursorMethod();

        TextView displayView = (TextView) findViewById(R.id.text_view_records);

        try {
            displayView.setText("The record table contains " + cursor.getCount() + " records.\n\n");
            displayView.append(RunningEntry._ID + " - " +
                    RunningEntry.COLUMN_DATE + " - " +
                    RunningEntry.COLUMN_ACTIVITY_NAME + " - " +
                    RunningEntry.COLUMN_TIME + " - " + "\n");

            int idColumnIndex = cursor.getColumnIndex(RunningEntry._ID);
            int dateColumnIndex = cursor.getColumnIndex(RunningEntry.COLUMN_DATE);
            int activityColumnIndex = cursor.getColumnIndex(RunningEntry.COLUMN_ACTIVITY_NAME);
            int timeColumnIndex = cursor.getColumnIndex(RunningEntry.COLUMN_TIME);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentActivity = cursor.getString(activityColumnIndex);
                double currentTime = cursor.getDouble(timeColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentDate + " - " +
                        currentActivity + " - " +
                        currentTime));
            }

        } finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}