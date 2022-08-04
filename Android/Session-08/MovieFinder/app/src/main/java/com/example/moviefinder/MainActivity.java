package com.example.moviefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView _txtTitle,_txtCat =null;
    ImageView _imgView =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button _btnGet = findViewById(R.id.btnGet);
        EditText _txtMovieName = findViewById(R.id.txtMovieName);
        _txtTitle = findViewById(R.id.txtTitle);
        _imgView = findViewById(R.id.imgView);
        _txtCat = findViewById(R.id.txtCat);
        _btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonBackgroundTask _Jt = new JsonBackgroundTask();
                _Jt.execute(_txtMovieName.getText().toString());

            }
        });
    }
    public  class JsonBackgroundTask extends AsyncTask<String,String,String>{
        HttpURLConnection _conn =null;
        String _line =null;
        ProgressDialog _pd=null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            _pd = new ProgressDialog(MainActivity.this);
            _pd.setIndeterminate(true);
            _pd.show();
        }
        @Override
        protected String doInBackground(String... strings) {
          try{
              URL _url = new URL("https://www.omdbapi.com/?t="+strings[0]+"&apikey=6ff571af");
             _conn = (HttpURLConnection) _url.openConnection();
             _conn.connect();
              InputStream _stream = _conn.getInputStream();
              InputStreamReader _sReader = new InputStreamReader(_stream);
              BufferedReader _dReader = new BufferedReader(_sReader);
               _line = _dReader.readLine();          }
          catch (Exception ex){              Log.e( "doInBackground: ",ex.getMessage().toString() );  }
            return _line;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject _jobj = new JSONObject(s);
                _txtTitle.setText(_jobj.getString("Title"));
                _txtCat.setText(_jobj.getString("Genre"));

                Picasso.get().load(_jobj.getString("Poster")).into(_imgView);

            }
            catch (Exception ex){


            }


            _pd.dismiss();
        }
    }
}