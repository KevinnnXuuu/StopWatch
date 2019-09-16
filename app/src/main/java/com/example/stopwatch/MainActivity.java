package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ToggleButton buttonStartStop;
    private Button buttonReset;
    private Chronometer chronometerTime;
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_CHRONOMETER_BASE = "chronometer base";
    public static final String KEY_CHRONOMETER_STOPTIME = "chronometer stop time";
    public static final String KEY_CHRONOMETER_STARTTIME = "chronometer start time";
    public static final String KEY_CHRONOMETER_STATE = "chronometer state";
    public boolean state;
    // Look up the log class for Android.
    // list all the levels of logging and when they're used
    // v
    // d
    // i
    // w
    // e
    // a
    // wtf

    // Launched app --> onCreate, onStart, onResume
    // rotate --> onPause, onStop, onDestroy, onCreate, onStart, onResume
    // hit the square button --> onPause, onStop
    // click back on the app from the square button --> onStart, onResume
    // hit the circle button --> onPause, onStop
    // relaunch the app from the phone navigation --> onStart, onResume
    // hit the back button --> onPause, onStop, onDestroy

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        wireWidgets();
        setLitseners();

        // if the savedInstanceState isn't null
            // pull out the value of the base that we saved from the Bundle
            // set the chronometer's base to that value
            // start the chronometer
        if (savedInstanceState != null) {
            long baseBundle = savedInstanceState.getLong(KEY_CHRONOMETER_BASE);
            chronometerTime.setBase(baseBundle);
            long stopTime = savedInstanceState.getLong(KEY_CHRONOMETER_STOPTIME);
            boolean state = savedInstanceState.getBoolean(KEY_CHRONOMETER_STATE);
            if (state) {
                chronometerTime.stop();
            }
            if (!state) {
                chronometerTime.stop();
                chronometerTime.setBase(chronometerTime.getBase() + SystemClock.elapsedRealtime() - stopTime);
            }

        }



        // next functionally would be to also store in the bundle
        // whether it was running or stopped to decide if you should
        // start it or not in onCreate
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
    // activity is now running

    // another activity is covering part od this activity
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    // this activity is completely hidden
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_CHRONOMETER_BASE, chronometerTime.getBase());
        outState.putLong(KEY_CHRONOMETER_STOPTIME, stopTime);
        outState.putLong(KEY_CHRONOMETER_STARTTIME, startTime);
        outState.putBoolean(KEY_CHRONOMETER_STATE, state);
    }

    // when activity is finished
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public void wireWidgets() {
        buttonStartStop = findViewById(R.id.button__main_start_stop);
        buttonReset = findViewById(R.id.button_main_reset);
        chronometerTime = findViewById(R.id.chronometer__main_time);
    }

    long startTime = SystemClock.elapsedRealtime();
    long stopTime = SystemClock.elapsedRealtime();
    public void setLitseners(){
        buttonStartStop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (buttonStartStop.getText().toString().equals(getString(R.string.main_stop))) {
                    chronometerTime.stop();
                    state = false;
                    stopTime = SystemClock.elapsedRealtime();
                    buttonStartStop.setText(getString(R.string.main_start));
                }
                else {
                    chronometerTime.setBase(chronometerTime.getBase() + SystemClock.elapsedRealtime() - stopTime);
                    chronometerTime.start();
                    state = true;
                    buttonStartStop.setText(getString(R.string.main_stop));
                }
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonStartStop.toggle();
                chronometerTime.stop();
                chronometerTime.setBase(SystemClock.elapsedRealtime());
                startTime = SystemClock.elapsedRealtime();
                buttonStartStop.setText(getString(R.string.main_start));
            }
        });
    }
}