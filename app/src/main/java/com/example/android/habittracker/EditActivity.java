package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.habittracker.data.RunningContract.RunningEntry;
import com.example.android.habittracker.data.RunningDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditActivity extends AppCompatActivity {

    private TextView mDate;
    private EditText mActivity;
    private EditText mTime;
    private String cDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mDate = (TextView) findViewById(R.id.editDateId);
        mActivity = (EditText) findViewById(R.id.editActivityId);
        mTime = (EditText) findViewById(R.id.editTimeId);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        cDate = simpleDateFormat.format(calendar.getTime());
        Log.d("Edit", cDate);
        mDate.setText(cDate);

    }

    private void insertRecord() {

        String activityString = mActivity.getText().toString().trim();
        String timeString = mTime.getText().toString().trim();

        double time = 0;
        if (!"".equals(timeString)) {
            time = Double.parseDouble(timeString);
        }

        RunningDbHelper mRunningDbHelper = new RunningDbHelper(this);
        SQLiteDatabase sqlDb = mRunningDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(RunningEntry.COLUMN_DATE, cDate);
        contentValues.put(RunningEntry.COLUMN_ACTIVITY_NAME, activityString);
        contentValues.put(RunningEntry.COLUMN_TIME, time);

        long rowId = sqlDb.insert(RunningEntry.TABLE_NAME, null, contentValues);

        if (rowId == -1) {
            Toast.makeText(this, "Error with saving record", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Record saved with row id: " + rowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.saveId:
                insertRecord();
                finish();
                return true;

            case android.R.id.home:

                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}