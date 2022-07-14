package com.example.practiceapp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView _txtName = findViewById(R.id.txtName);
        Intent _intMain =  getIntent();
       String _CourseName = _intMain.getStringExtra("CourseName");
       _txtName.setText(_CourseName);
    }
}