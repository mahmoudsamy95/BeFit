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
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.TimePicker;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.Calendar;

public class CheckUpActivity extends AppCompatActivity {
    private DatePicker dpDate;
    private EditText etDocName;
    private DatabaseReference myRefPill;
    FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_up);
        FirebaseUser firebaseUser = auth.getInstance().getCurrentUser();
        dpDate=findViewById(R.id.datePicker2);
        etDocName=findViewById(R.id.etDocName);
        myRefPill = database.getInstance().getReference().child(firebaseUser.getUid()).child("User").child("checkups");
    }


    public void set(View view) {
        int dateDay = dpDate.getDayOfMonth();
        int dateMonth=dpDate.getMonth()-1;
        int dateyear=dpDate.getYear();
        String checkup=etDocName.getText().toString();
        Calendar calendar=Calendar.getInstance();
        calendar.set(dateyear,dateMonth,dateDay,9,0);
        Intent intent=new Intent(getApplicationContext(),MyNotificationPublisher.class);
        intent.putExtra("id","2");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        myRefPill.setValue(checkup);
        Toast.makeText(this, "Checkup added!", Toast.LENGTH_SHORT).show();

    }
}
