package com.example.sabbir.ispbilling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainManuActivity extends AppCompatActivity {


    ///CardView;

    ImageView addButton, showButton,CheckButton,DeleteButton,About,Bill;

    CardView add ,show, delete,bill,cheek,about;

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manu);


        addButton = findViewById(R.id.add_image);
        showButton = findViewById(R.id.show_user_image);
        CheckButton = findViewById(R.id.Check_payment_image);
        DeleteButton = findViewById(R.id.delete_user_image);
        Bill = findViewById(R.id.bill_payment_user);
        About = findViewById(R.id.about_image);
        logout = findViewById(R.id.logoutButton);


        add = findViewById(R.id.Add_User);
        show = findViewById(R.id.Show_user);
        delete = findViewById(R.id.Delete_user);
        bill = findViewById(R.id.Bill_Payment);
        cheek = findViewById(R.id.Cheek_payment);
        about= findViewById(R.id.About);

        logoutmethod();

        MainMethod();
        cardview();

    }


    private  void logoutmethod()
    {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(MainManuActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

                /*
                SharedPreferences sharedpreferences = getSharedPreferences
                        ("userlogin", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
//                moveTaskToBack(true);
                MainManuActivity.this.finish();*/


            }
        });

    }

    private void cardview()
    {

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManuActivity.this,AddListActivity.class);
                startActivity(intent);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainManuActivity.this,ShowListActivity.class);
                startActivity(intent);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManuActivity.this,DeleteActivity.class);
                startActivity(intent);
            }
        });

        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManuActivity.this,BilInformatinShowActivity.class);
                startActivity(intent);
            }
        });

        cheek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainManuActivity.this,CheckPaymentActivity.class);
                startActivity(intent);

            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainManuActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });

    }


    protected void MainMethod()

    {


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManuActivity.this,AddListActivity.class);
                startActivity(intent);

                Toast.makeText(MainManuActivity.this, "This is the card view",Toast.LENGTH_SHORT).show();
            }
        });


        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManuActivity.this,ShowListActivity.class);
                startActivity(intent);
            }
        });

        CheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManuActivity.this,CheckPaymentActivity.class);
                startActivity(intent);
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManuActivity.this,DeleteActivity.class);
                startActivity(intent);
            }
        });

        Bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainManuActivity.this,BilInformatinShowActivity.class);
                startActivity(intent);
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManuActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();


    }
}