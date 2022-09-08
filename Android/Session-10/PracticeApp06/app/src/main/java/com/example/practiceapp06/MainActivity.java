package com.example.practiceapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView _lstData = findViewById(R.id.lstData);
        ArrayList<Course> _c = new ArrayList<Course>();
        _c.add(new Course("Maths",R.drawable.ic_launcher_background));
        _c.add(new Course("English",R.drawable.ic_launcher_background));
        _c.add(new Course("Urdu",R.drawable.ic_launcher_background));
        _c.add(new Course("Islamiat",R.drawable.ic_launcher_background));
       // CustomListAdapter<Course> _cp = new CustomListAdapter<Course>();
        CustomListAdapter _cp = new CustomListAdapter(this,R.layout.custom_list,_c);
        _lstData.setAdapter(_cp);
        ArrayList<String> _lstName = new ArrayList<>();
        _lstName.add("English");
        _lstName.add("Urdu");
        _lstName.add("Maths");
        _lstName.add("Islamiat");
        _lstName.add("Geography");
        ArrayAdapter<String> _dAdapter =  new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,_lstName);
       // _lstData.setAdapter(_dAdapter);
    }
}