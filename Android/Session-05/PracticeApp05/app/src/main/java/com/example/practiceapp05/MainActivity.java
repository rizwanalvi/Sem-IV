package com.example.practiceapp05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button _btnShowButton= findViewById(R.id.btnShow);
        _btnShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                CustomToast("Write Message Here!");
            }
        });

    }
    private void CustomToast(String message){

        LayoutInflater _imInflate = getLayoutInflater();
        View _NewToast =   _imInflate.inflate(R.layout.custom_toast,null);
        TextView _textView = _NewToast.findViewById(R.id.txtToast);
        _textView.setText(message);
         Toast _Toast = new Toast(getApplicationContext());
        _Toast.setView(_NewToast);
        _Toast.setGravity(Gravity.CENTER,0,0);
        _Toast.setDuration(Toast.LENGTH_LONG);
        _Toast.show();

    }
}