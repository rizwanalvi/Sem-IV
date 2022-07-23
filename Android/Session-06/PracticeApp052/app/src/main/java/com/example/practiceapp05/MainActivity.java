package com.example.practiceapp05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView _lstMovie = findViewById(R.id.lstMovie);
        Button _btnAdd = findViewById(R.id.btnAdd);
        EditText _txtMovie = findViewById(R.id.txtMovie);
        ArrayList<Movie> _lsMovie = new ArrayList<>();
        _lsMovie.add(new Movie("Movie 1",R.drawable.ic_launcher_background));
        _lsMovie.add(new Movie("Movie 2",R.drawable.ic_launcher_background));
        _lsMovie.add(new Movie("Movie 3",R.drawable.ic_launcher_background));
        _lsMovie.add(new Movie("Movie 4",R.drawable.ic_launcher_background));

        CustomMovieList _dAdapter = new CustomMovieList(this,R.layout.custom_lisview,_lsMovie);
        _lstMovie.setAdapter(_dAdapter);


        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         //   _ArrayMov.add(_txtMovie.getText().toString());
            }
        });
    }
}