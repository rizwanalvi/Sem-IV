package com.example.cropapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
     FirebaseFirestore db;
    StorageReference storageRef;
    FirebaseStorage storage;
    private Uri imgPath;
    private ImageView _img;
    private final int PICK_IMAGE_REQUEST = 22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText _txtUsrName = findViewById(R.id.txtUsrName);
        EditText _txtPassword = findViewById(R.id.txtPassword);
        Button _btnregister = findViewById(R.id.btnregister);
      _img = findViewById(R.id.img);
        _img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _IntentGetimg = new Intent();
                _IntentGetimg.setType("image/*");
                _IntentGetimg.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(_IntentGetimg,0);



            }

        });

        mAuth = FirebaseAuth.getInstance();

         db = FirebaseFirestore.getInstance();
       storageRef = FirebaseStorage.getInstance().getReference();
        //db = FirebaseFirestore.getInstance();
        _btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(_txtUsrName.getText().toString(),_txtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                            final StorageReference riversRef = storageRef.child("mountains.jpg");
                            riversRef.putFile(imgPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String _ph = uri.getPath();
                                            Log.e( "onSuccess: ",_ph );
                                        }
                                    });
                                }
                            });

                                db.collection("Users").document(mAuth.getUid()).set(new User("Ahmed Khan",_txtUsrName.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {


                                @Override
                                public void onSuccess(Void unused) {

                                }
                            });

                        }
                        else{
                            Toast.makeText(MainActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgPath = data.getData();
        Log.e("onActivityResult: ",data.getData().toString() );
        Picasso.get().load(imgPath).into(_img);

    }
}