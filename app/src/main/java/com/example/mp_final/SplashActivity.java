package com.example.mp_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    Button splashlogin;
    Button splashsign;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashlogin=findViewById(R.id.splashlogin);
        splashsign=findViewById(R.id.splashsign);

        auth=FirebaseAuth.getInstance();

        splashlogin.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);

        }}
        );

        splashsign.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent);
            }
        });
        if(auth.getCurrentUser() !=null){
            Toast.makeText(getApplicationContext(),"Anasayfaya yönlendiriliyor",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }
}