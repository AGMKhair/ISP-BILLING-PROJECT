/*
package com.example.sabbir.ispbilling.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.sabbir.ispbilling.MainActivity;
import com.example.sabbir.ispbilling.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.example.sabbir.ispbilling.login.loginVariable.m;
import static com.example.sabbir.ispbilling.login.loginVariable.mDatabase;
import static com.example.sabbir.ispbilling.login.loginVariable.n;
import static com.example.sabbir.ispbilling.login.loginVariable.o;

public class LoginSplashScreen extends AppCompatActivity {

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_splash_screen);



        progressBar = findViewById(R.id.progressBar);

        final Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {

               */
/* for(int i = 20 ; i <= 100; i = i+20)
                {
                    try {
                        Thread.sleep(500);
                        progressBar.setProgress(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*//*


               while (m == null)
               {

                   try {

                       Thread.sleep(500);
                       progressBar.setProgress(30);


                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }

                Intent intent = new Intent(LoginSplashScreen.this,MainActivity.class);
                startActivity(intent);

                finish();
            }
        });
        thread.start();

    }

    @Override
    protected void onStart() {
        super.onStart();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                m = dataSnapshot.child("username").getValue(String.class);
                n = dataSnapshot.child("password").getValue(String.class);
                o = dataSnapshot.child("changepass").getValue(String.class);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
*/
