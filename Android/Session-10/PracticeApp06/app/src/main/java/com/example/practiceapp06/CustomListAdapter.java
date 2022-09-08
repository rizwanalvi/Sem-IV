package com.example.practiceapp06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Course> {
    private Context aContext;
    private Course aCourse;
    private  int aResource;
    public CustomListAdapter(@NonNull Context context, int resource, ArrayList<Course> obj) {
        super(context, resource,obj);
        aContext=context;
        aResource =resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater _inLayout = LayoutInflater.from(aContext);
    convertView = _inLayout.inflate(aResource,parent,false);
        ImageView _img = convertView.findViewById(R.id.img);
        TextView _txtTitle = convertView.findViewById(R.id.txtTitle);
        String _CourseName = getItem(position).get_CourseName();
        int _ImgPath = getItem(position).get_ImgPath();
         _txtTitle.setText(_CourseName);
         _img.setImageResource(_ImgPath);

        return convertView;

    }
}
