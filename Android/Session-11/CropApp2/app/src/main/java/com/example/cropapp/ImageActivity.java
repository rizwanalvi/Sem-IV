package com.example.cropapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {
    private Uri imgPath;
    private ImageView _AddImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
         _AddImg = findViewById(R.id.AddImg);
        ImageView _ReadImg = findViewById(R.id.ReadImg);
        Button _btnUpload = findViewById(R.id.btnUpload);
        Button _btnRead = findViewById(R.id.btnRead);
        _AddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _IntentGetimg = new Intent();
                _IntentGetimg.setType("image/*");
                _IntentGetimg.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(_IntentGetimg,0);
            }
        });
        _btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                storageRef.child("apple.png").putFile(imgPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        taskSnapshot.getUploadSessionUri().toString();
                        Log.e("onSuccess: ", taskSnapshot.getUploadSessionUri().toString().split("&")[0]);
                        Log.e("onSuccess: ", taskSnapshot.getUploadSessionUri().toString().split("&")[0].replace("?name=","/"));
                        String _s =taskSnapshot.getUploadSessionUri().toString().split("&")[0].replace("?name=","/");
                        StringBuilder _dBuilder = new StringBuilder(_s);
                        _dBuilder.append("?alt=media&token=43c86a83-e034-4fde-8683-8cfad5c41fd1");
                        Log.e("onSuccess: ", _dBuilder.toString());

                    }
                });
            }
        });
        _btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/cropdb-61937.appspot.com/o/apple.png?alt=media&token=43c86a83-e034-4fde-8683-8cfad5c41fd1").into(_ReadImg);
                Toast.makeText(ImageActivity.this, "ok", Toast.LENGTH_SHORT).show();



            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgPath = data.getData();
        Log.e("onActivityResult: ",data.getData().toString() );
        Picasso.get().load(imgPath).into(_AddImg);

    }
}