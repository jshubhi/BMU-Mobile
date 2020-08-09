package com.example.namanjain.bmlsmartapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Shuttle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Shuttle");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
    }
}
