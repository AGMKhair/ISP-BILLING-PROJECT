package com.example.sabbir.ispbilling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowUpdateLayoutActivity extends AppCompatActivity {

    TextView textView_id;
    EditText editText_name,editText_phone,editText_address;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_update_layout);


        Intent intent = getIntent();

        //final String id = intent.getStringExtra("id");
      /*  String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String address = intent.getStringExtra("address");
        String active = intent.getStringExtra("active");*/

/*

        textView_id = findViewById(R.id.show_id_edittext);
        textView_id.setText(id);

        editText_name = findViewById(R.id.show_name_edittext);
        editText_name.setText(name);

        editText_phone = findViewById(R.id.show_phone_edittext);
        editText_phone.setText(phone);



        editText_address = findViewById(R.id.show_address_edittext);
        editText_address.setText(address);


        checkBox = findViewById(R.id.show_checkbox_edittext);
        if(active.equals("Active"))
        {
            checkBox.setText("Active");
            checkBox.isEnabled();
        }


        Button buttonUpdate = findViewById(R.id.update_button);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(id);
            }
        });

*/


    }

    private void update(String id) {

        String username = editText_name.getText().toString().trim();
        String phoneNumber = editText_phone.getText().toString().trim();
        String address = editText_address.getText().toString().trim();
        // String name = update_name.getText().toString().trim();
        String checked = null;


        if(checkBox.isChecked())
        {

            checked = "Active";
            //  Toast.makeText(AddListActivity.this,checked, Toast.LENGTH_SHORT).show();


        }else
        {
            checked ="unActive";
            // Toast.makeText(AddListActivity.this,checked, Toast.LENGTH_SHORT).show();


        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user");

        Information information = new Information( id ,username ,phoneNumber,address,checked);

        databaseReference.child(id).setValue(information);

        finish();

    }
}
