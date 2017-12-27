package com.example.mahmo.befit;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StepCounter_Layout extends AppCompatActivity implements SensorEventListener {
    private TextView tvStepCounter;
    SensorManager sensorManager;
    boolean running = false;

    private DatabaseReference myRefStep;
    FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter__layout);
        tvStepCounter = (TextView) findViewById(R.id.tvStepCounter);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        FirebaseUser firebaseUser = auth.getInstance().getCurrentUser();
        myRefStep = database.getInstance().getReference().child(firebaseUser.getUid()).child("User").child("steps");
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(StepCounter_Layout.this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            myRefStep.setValue(String.valueOf(event.values[0]));
            tvStepCounter.setText(String.valueOf(event.values[0]));

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void reset(View view) {
        tvStepCounter.setText("");
    }
}
