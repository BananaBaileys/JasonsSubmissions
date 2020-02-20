package com.example.jasonyang.tester;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    int counter=0;
    int counterfordisplay;
    TextView tv_steps;

    SensorManager sensorManager;

    boolean running = false;

    TextView newCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState  ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_steps = (TextView) findViewById(R.id.tv_steps);              //display the value of tv_steps
        tv_steps.setText(String.valueOf(0));
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

//            TimerTask repeatedTask = new TimerTask() {
//                public void run() {
//                    counter = Sensor.TYPE_STEP_COUNTER;
//                   // counter=event.value[0];
//
//                }
//            };
//            Timer timer = new Timer("Timer");
//
//            long delay  = 100L;
//            long period = 100L;
//            timer.scheduleAtFixedRate(repeatedTask, delay, period);

    }

    protected void onResume(){
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);

        }else{
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        running = false;
        //if you unregister the hardware will stop detetcting steps
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            tv_steps.setText(String.valueOf(event.values[0]-counter ));
           // tv_steps.setText(String.valueOf(0));
          //  tv_steps.setText(String.valueOf(-counter));


        }
        else {
            event.values[0] = 0;
        }
//        TimerTask repeatedTask = new TimerTask() {
//            public void run() {
//            }
//        };
//        Timer timer = new Timer("Timer");
//
//        long delay  = 100L;
//        long period = 100L;
//        timer.scheduleAtFixedRate(repeatedTask, delay, period);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
