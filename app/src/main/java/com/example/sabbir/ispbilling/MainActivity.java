package com.example.sabbir.ispbilling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.sabbir.ispbilling.login.loginVariable.m;
import static com.example.sabbir.ispbilling.login.loginVariable.mDatabase;
import static com.example.sabbir.ispbilling.login.loginVariable.n;
import static com.example.sabbir.ispbilling.login.loginVariable.o;

public class MainActivity extends AppCompatActivity {


    SharedPreferences login;
    EditText username, password;
    Button button;
    TextView textView ;
    private FirebaseAuth mAuth=null;
    private  FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // FirebaseAuth.getInstance().signOut();
        mAuth = FirebaseAuth.getInstance();


        button = findViewById(R.id.loginbutton);
        username = findViewById(R.id.EditText_Login_User);
        password = findViewById(R.id.EditText_Login_Password);


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null)
                {

                    Intent intent = new Intent(MainActivity.this, MainManuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sinInMethod();
            /*    login = getSharedPreferences("user", Context.MODE_PRIVATE);

               //  String s = login.getString("loginValue","");

               // if( login.getInt("loginValue",0) == 1)

                try
                {


                    if(login.contains("login"))
                    {



                        Intent intent = new Intent(MainActivity.this,MainManuActivity.class);
                        startActivity(intent);


                    }
                    else
                    {

                        if(username.getText().toString().equals(m) && password.getText().toString().equals(n))
                        {editor.putString("login", "sabbir");
                            editor.commit();



                            SharedPreferences.Editor editor = login.edit();


                            Intent intent = new Intent(MainActivity.this,MainManuActivity.class);
                            startActivity(intent);

                        }else
                        {
                            Toast.makeText(MainActivity.this,"Wrong Password or User Id ", Toast.LENGTH_SHORT).show();
                        }
                        username.setText("");
                        username.setHint("User Name");
                        password.setText("");
                        password.setHint("Password");
                    }






                }catch (Exception t)
                {

                }
*/
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(authStateListener);

       /* mDatabase.addValueEventListener(new ValueEventListener() {
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

        //Toast.makeText(this,m + " " + n+" " + o,Toast.LENGTH_SHORT).show();
*/

    }



    private void sinInMethod()
    {

        String user = username.getText().toString();
        String pass = password.getText().toString();




        if (user.isEmpty() || pass.isEmpty())
        {
            Toast.makeText(MainActivity.this,"Filup the box",Toast.LENGTH_SHORT).show();
        }
        else
        {

            mAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {


                       // Toast.makeText(MainActivity.this," tr User Name Password ",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, MainManuActivity.class);
                        startActivity(intent);
                        //finish();
                        //Toast.makeText(MainActivity.this," trps User Name Password ",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this," InCurrect User Name Password ",Toast.LENGTH_SHORT).show();
                        password.setText("");
                    }


                }
            });

        }

    }



}
