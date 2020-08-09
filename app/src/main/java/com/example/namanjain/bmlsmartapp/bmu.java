package com.example.namanjain.bmlsmartapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class bmu extends AppCompatActivity {
    private static int SPLASH_TIMEOUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bmu);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intentOne=new Intent(bmu.this,MainActivity.class);
                startActivity(intentOne);
                finish();
            }
        },SPLASH_TIMEOUT);
    }
}
