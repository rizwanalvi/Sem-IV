package com.example.practiceapp05;

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
import java.util.List;

public class CustomMovieList extends ArrayAdapter<Movie> {
    private Context aContext;
    private int aResource=0;
    public CustomMovieList(@NonNull Context context, int resource, @NonNull ArrayList<Movie> objects) {
        super(context, resource, objects);
        this.aContext =context;
        this.aResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater _inLayout = LayoutInflater.from(aContext);
        convertView =     _inLayout.inflate(aResource,parent,false);
        TextView _txtMovieName = convertView.findViewById(R.id.txtMovieName);
        ImageView _imgPath = convertView.findViewById(R.id.imgPath);
        _txtMovieName.setText(getItem(position).getMovieName());
        _imgPath.setImageResource(getItem(position).getMoviePoster());

        return convertView;
    }
}
