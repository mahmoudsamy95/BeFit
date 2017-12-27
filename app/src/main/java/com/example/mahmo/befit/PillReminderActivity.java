package com.example.mahmo.befit;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PillReminderActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.mahmo.befit.MESSAGE";
public EditText etPillName;
private EditText etPillDate;
private EditText etPillTime;
private TimePicker time;
    private DatabaseReference myRefPill;
    FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_reminder);
        etPillName=findViewById(R.id.etPillName);
        time=findViewById(R.id.timePicker2);
        FirebaseUser firebaseUser = auth.getInstance().getCurrentUser();
        myRefPill = database.getInstance().getReference().child(firebaseUser.getUid()).child("User").child("pills");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setNotification(View view) {
        String pillName=etPillName.getText().toString();
        int hour=time.getHour();
        int min=time.getMinute();
        Calendar calendar=Calendar.getInstance();
        calendar.set(calendar.HOUR_OF_DAY,hour);
        calendar.set(calendar.MINUTE,min);
        Intent intent=new Intent(getApplicationContext(),MyNotificationPublisher.class);
        intent.putExtra("id","1");
        intent.putExtra("pill", pillName);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
//        myRefPill.setValue(pillName);
        Toast.makeText(this, "Pill added!", Toast.LENGTH_SHORT).show();


    }

}
