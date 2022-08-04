package com.example.moviefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {
     ArrayList<String> _movList = null;
    ArrayAdapter _dAdapter =null;
    ListView _lstMovie =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        _lstMovie = findViewById(R.id.lstMovie);
        _movList = new ArrayList<>();
         _dAdapter = new ArrayAdapter(MovieListActivity.this,R.layout.support_simple_spinner_dropdown_item,_movList);
        SearchView _serchView = findViewById(R.id.serchView);
        _serchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new JsonReader().execute(query.toString());
                // Toast.makeText(MovieListActivity.this, query.toString(), Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    class JsonReader extends AsyncTask<String, String, String> {
        HttpURLConnection _conn = null;
        String _line = null;
        ProgressDialog _pd = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e( "onPreExecute: ","Start" );
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL _url = new URL("https://www.omdbapi.com/?s=" + strings[0] + "&apikey=6ff571af");
                _conn = (HttpURLConnection) _url.openConnection();
                _conn.connect();
                InputStream _stream = _conn.getInputStream();
                InputStreamReader _sReader = new InputStreamReader(_stream);
                BufferedReader _dReader = new BufferedReader(_sReader);
                _line = _dReader.readLine();
                Log.e("doInBackground: ", _line);
            } catch (Exception ex) {
                Log.e("doInBackground: ", ex.getMessage().toString());
            }

            return _line;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try{
                JSONObject _jb = new JSONObject(s);
               JSONArray _jArray = _jb.getJSONArray("Search");
               _movList.clear();
               for (int i=0; i<_jArray.length();i++){
                   JSONObject _jbinner = _jArray.getJSONObject(i);
                   _movList.add(_jbinner.getString("Title"));
                   Log.e( "onPostExecute: ",_jbinner.getString("Title"));
               }
               _lstMovie.setAdapter(_dAdapter);
            }
            catch (Exception ex){


            }

        }
    }
}


