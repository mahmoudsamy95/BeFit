package com.example.mahmo.befit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RepeatingActivity extends AppCompatActivity {
TextView tvPillname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeating);
        tvPillname=findViewById(R.id.tvPillName);
        Intent i=getIntent();
        String pillname=i.getStringExtra("pill");
        tvPillname.setText(pillname);

    }
}
