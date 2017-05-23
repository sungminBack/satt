package com.satt.mjbus.Timetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;


/**
 * Created by hn012 on 2017-05-22.
 */

public class TimeTableActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);

        setContentView(com.satt.mjbus.R.layout.timetable_layout);

        Intent intent2 = new Intent(this.getIntent());

    }
}
