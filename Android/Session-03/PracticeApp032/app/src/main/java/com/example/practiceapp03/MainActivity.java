package com.example.practiceapp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView _lstCourse = findViewById(R.id.lstCourse);
        _lstCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             //   Toast.makeText(MainActivity.this, String.valueOf(_lstCourse.getItemAtPosition(i)) , Toast.LENGTH_SHORT).show();
                Intent _IntDetal = new Intent(MainActivity.this,DetailActivity.class);
                _IntDetal.putExtra("CourseName",String.valueOf(_lstCourse.getItemAtPosition(i)));
                    startActivity(_IntDetal);
            }
        });
    }
}