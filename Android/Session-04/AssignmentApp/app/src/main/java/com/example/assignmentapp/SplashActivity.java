package com.example.assignmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread th= new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                    Intent _MenuIntent = new Intent(SplashActivity.this,MenuActivity.class);
                    startActivity(_MenuIntent);
                    finish();

                }
            catch (Exception ex){}
            }
        });
        th.start();
    }
}