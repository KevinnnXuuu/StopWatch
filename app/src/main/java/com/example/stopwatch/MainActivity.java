package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private Button buttonStartStop;
    private Button buttonReset;
    private Chronometer chronometerTime;
    public static final String TAG = MainActivity.class.getSimpleName();

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
    public void setLitseners(){
        buttonStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometerTime.start();
                buttonStartStop.setText("STOP");
                buttonStartStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        chronometerTime.stop();
                        buttonStartStop.setText("START");
                    }
                });
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

