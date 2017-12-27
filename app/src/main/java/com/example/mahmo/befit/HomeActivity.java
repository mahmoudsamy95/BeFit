package com.example.mahmo.befit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Calendar calendar=Calendar.getInstance();
        Intent intent=new Intent(getApplicationContext(),MyNotificationPublisher.class);
        intent.putExtra("id","3");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),3600*1000*2,pendingIntent);


    }

    public void signOut(View view) {
        auth.getInstance().signOut();

        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(HomeActivity.this, Login.class));
                    finish();
                }
            }
        };
    }

    public void calCalc(View view) {
        startActivity(new Intent(HomeActivity.this,CaloriesCalc_layout.class));
    }

    public void stepCounter(View view) {
        startActivity(new Intent(HomeActivity.this,StepCounter_Layout.class));
    }

    public void pillReminder(View view) {
        startActivity(new Intent(HomeActivity.this,PillReminderActivity.class));
    }

    public void checkup(View view) {
        startActivity(new Intent(HomeActivity.this,CheckUpActivity.class));

    }
}
