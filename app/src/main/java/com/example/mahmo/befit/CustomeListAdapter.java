package com.example.mahmo.befit;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class CustomeListAdapter extends ArrayAdapter {
    String[] food_names;
    Integer[] image_id;
    Context context;


    public CustomeListAdapter(Activity context, Integer[] image_id, String[] text) {
        super(context, R.layout.my_list, text);
        this.food_names = text;
        this.image_id = image_id;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.my_list, null,
                true);
        TextView textView = (TextView) single_row.findViewById(R.id.textView);
        ImageView imageView = (ImageView) single_row.findViewById(R.id.imageView);
        textView.setText(food_names[position]);
        imageView.setImageResource(image_id[position]);
        return single_row;
    }
}