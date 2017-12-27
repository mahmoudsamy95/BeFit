package com.example.mahmo.befit;

import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CaloriesCalc_layout extends Activity {
    private ListView lv;
    public TextView tvTotal;
    private TextView tvTxt;
    private DatabaseReference databaseReference;
    Editable value;
    double total;
    int sum;
    int number;
    String day="day"+number;

    private final String[] color_names = {"Meat", "Chicken", "Fish", "Bread", "Rice", "Pasta", "Cake", "Waffle", "Juice"};
    private final Integer[] image_id = {R.drawable.steak, R.drawable.chicken, R.drawable.fish, R.drawable.bread, R.drawable.rice, R.drawable.pasta,
            R.drawable.cake, R.drawable.waffle, R.drawable.juice};
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefCal;
    FirebaseUser firebaseUser;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_calc_layout);
        FirebaseUser firebaseUser = auth.getInstance().getCurrentUser();
        myRefCal = database.getInstance().getReference().child(firebaseUser.getUid()).child("User").child("calories");



        CustomeListAdapter adapter = new CustomeListAdapter(this, image_id, color_names);
        lv = (ListView) findViewById(R.id.LvFood);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvTxt = (TextView) findViewById(R.id.tvTxt);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        showInputDialog(100);
                        break;
                    case 1:
                        showInputDialog(200);
                        break;
                    case 2:
                        showInputDialog(300);
                        break;
                    case 3:
                        showInputDialog(300);
                        break;
                    case 4:
                        showInputDialog(300);
                        break;
                    case 5:
                        showInputDialog(300);
                        break;
                    case 6:
                        showInputDialog(300);
                        break;
                    case 7:
                        showInputDialog(300);
                        break;
                    case 8:
                        showInputDialog(300);
                        break;
                    case 9:
                        showInputDialog(300);
                        break;
                    case 10:
                        showInputDialog(300);
                        break;
                }
                number++;

            }

        });

    }

    /*public void saveCal(int sum){
        myRefCal.child(firebaseUser.getUid()).child("User").child("calories").setValue(sum).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    Toast.makeText(CaloriesCalc_layout.this, "Calories added", Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.e("Error",task.getException().toString());
                    Toast.makeText(CaloriesCalc_layout.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }*/
    public void showInputDialog(final double c) {

        // get prompts.xml view
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("");
        alert.setMessage("Enter the quantity");

// Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                value = input.getText();
                int valueInt = Integer.parseInt(value.toString());
                total = valueInt * c;
                sum += total;
               // tvTotal.setText("" + sum);
                myRefCal.setValue(sum);


            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }

    public void getCal(View view) {
        //startActivity(new Intent(this,AllCaloriesActivity.class));
        myRefCal.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int calories= dataSnapshot.getValue(Integer.class);
                tvTotal.setText(""+calories);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void clear(View view) {
        myRefCal.setValue(0);
        tvTotal.setText(""+0);
    }
}
