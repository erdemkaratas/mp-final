package com.example.mp_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mp_final.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SigninActivity extends AppCompatActivity {

    Button btnlogin,btnsign;
    FirebaseAuth auth;
    EditText etname,etsurname,etpassword,etemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        btnlogin = findViewById(R.id.btnLogin);
        etname = findViewById(R.id.etName);
        etsurname=findViewById(R.id.etSurname);
        etpassword=findViewById(R.id.etPass);
        etemail=findViewById(R.id.etEmail);
        btnsign=findViewById(R.id.btnSign);
        auth=FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v){
               Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
               startActivity(intent);
           }
        });
        btnsign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String name=etname.getText().toString();
                String surname=etsurname.getText().toString();
                String email=etemail.getText().toString();
                String password=etpassword.getText().toString();

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(SigninActivity.this,"Kayıt Başarılı",Toast.LENGTH_SHORT).show();
                            FirebaseFirestore database=FirebaseFirestore.getInstance();
                            String uid=task.getResult().getUser().getUid();
                            UserModel user=new UserModel(name,surname,email);
                            database.collection("UserModel").document(uid).set(user).
                                    addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(SigninActivity.this,"Kayıt Başarılı",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                            startActivity(intent);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                            public void onFailure(Exception e) {
                                                Toast.makeText(SigninActivity.this,"Kayıt Başarısız Oldu",Toast.LENGTH_SHORT).show();
                                            }
                                    })  ;


                        }
                    }


                });
            }
        });


    }
}