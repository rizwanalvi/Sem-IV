package com.example.practiceapp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                    try{
                        Thread.sleep(2000);
                        Intent _mainIntent =new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(_mainIntent);
                        finish();
                    }
                    catch (Exception ex){


                    }

            }
        });

        th.start();


    }
}