package com.example.cropapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class DumActivity extends AppCompatActivity {
    private Uri imgPath;
    ImageView _imgAdd,_imgShow;
    private FirebaseAuth mAuth;
    EditText _txtUserName,_txtPassword,_txtFullName,_txtBatchId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dum);
         _imgAdd = findViewById(R.id.imgAdd);
        _imgShow = findViewById(R.id.imgShow);
        Button _btnRead = findViewById(R.id.btnRead);
        _txtUserName = findViewById(R.id.txtUserName);
        _txtPassword = findViewById(R.id.txtPassword);
        _txtFullName = findViewById(R.id.txtFullName);
        _txtBatchId = findViewById(R.id.txtBatchId);
         mAuth = FirebaseAuth.getInstance();

        _btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/cropdb-61937.appspot.com/o/mango.png?alt=media&token=b96ed550-678b-4d13-b2fc-2bab8ebfe363").into(_imgShow);


            }
        });
        Button _btnUpload = findViewById(R.id.btnUpload);
        _btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _userName = _txtUserName.getText().toString();
                String _userPass = _txtPassword.getText().toString();
                String _FullName = _txtFullName.getText().toString();
                String _BatchId = _txtBatchId.getText().toString();

                mAuth.createUserWithEmailAndPassword(_userName,_userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                        storageReference.child(mAuth.getUid().toString()+".png").putFile(imgPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                String[] _imgUrl = taskSnapshot.getUploadSessionUri().toString().split("&");
                                Log.e( "onSuccess: ", taskSnapshot.getUploadSessionUri().toString());
                                Log.e( "onSuccess: ",_imgUrl[0].replace("?name=","/") );
                                StringBuilder _dBuilder = new StringBuilder(_imgUrl[0].replace("?name=","/"));
                                _dBuilder.append("?alt=media&token=b96ed550-678b-4d13-b2fc-2bab8ebfe363");
//
                                Log.e( "onSuccess: ",_dBuilder.toString() );
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("javaTut").document(mAuth.getUid().toString()).
                                        set(new UserInfo(_userName,_FullName,_BatchId,_dBuilder.toString())).
                                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });




                            }
                        });






                        Snackbar.make(view,"Account has been Created",Snackbar.LENGTH_LONG).show();

                    }
                    }
                });








            }
        });
        _imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _IntentImg = new Intent();
                _IntentImg.setType("image/*");
                _IntentImg.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(_IntentImg,0);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgPath = data.getData();
        Picasso.get().load(imgPath).into(_imgAdd);
        Log.e( "onActivityResult: ",imgPath.toString() );
    }
}