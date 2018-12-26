package com.example.sabbir.ispbilling;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.sabbir.ispbilling.AddListActivity.Request_code;

public class ShowListActivity extends AppCompatActivity {

    DatabaseReference showDatabase;

    List<Information> sabbirlist;

    ListView listView;
    AdView adView;

    EditText searchEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_list);

        showDatabase = FirebaseDatabase.getInstance().getReference("user");
        listView = findViewById(R.id.list_item_show_data);
        sabbirlist =new ArrayList<>();
        searchEdittext = findViewById(R.id.EdittextSearch);

     //   MobileAds.initialize(this, "ca-app-pub-6449076598303937~2560230025");


     /*   adView = findViewById(R.id.adview);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
*/
        methodListView();

        methodSearch();


    }

    private void methodSearch()
    {
        searchEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                } else {
                    /*
                    * Clear the list when editText is empty
                    * */
                }
            }


        });

    }

    private void setAdapter(final String searchedString)
    {



        showDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                sabbirlist.clear();


/*
                String s =  dataSnapshot.child(user).child(userphone).getValue(String.class);
                textView.setText(s);*/

                for (DataSnapshot sabbirSnapshot : dataSnapshot.getChildren())
                {

                    String id = sabbirSnapshot.child("userid").getValue(String.class);

                    String user_name = sabbirSnapshot.child("userName").getValue(String.class);
                    if(id.toLowerCase().contains(searchedString.toLowerCase()))

                    {

                        Information information = sabbirSnapshot.getValue(Information.class);
                        sabbirlist.add(information);

                    }else if(user_name.toLowerCase().contains(searchedString.toLowerCase()))
                    {
                        Information information = sabbirSnapshot.getValue(Information.class);
                        sabbirlist.add(information);
                    }




                }

                InformationList adapter = new InformationList(ShowListActivity.this,sabbirlist);
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void methodListView()
    {


    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Information information = sabbirlist.get(position);

            //  showUpdatedialog(information,sabbir.getSabbirName());

            showUpdatedialog(information.getUserid(),information.getUserName(),information.getPhone(),information.getAddress(),information.getImageuri());


        }
    });

}

    @Override
    protected void onStart()
    {
        super.onStart();
        methd();

    }

    private void showUpdatedialog(final String userid, final String userName, final String phone, final String address, final String imageuri) {

        final AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);

         LayoutInflater layoutInflater = getLayoutInflater();

        final View dialogview = layoutInflater.inflate(R.layout.update_layout,null);

        dialogbuilder.setView(dialogview);

        final Button buttonupdate = dialogview.findViewById(R.id.update_lsitView_button);
        final Button buttondelete = dialogview.findViewById(R.id.delete_listView_button);

        final AlertDialog alertDialog = dialogbuilder.create();
        alertDialog.show();


        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

                final AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(ShowListActivity.this);

                LayoutInflater layoutInflater = getLayoutInflater();

                final View updateView =layoutInflater.inflate(R.layout.update_dialog_layout,null);

                dialogbuilder.setView(updateView);

              //

                TextView update_id = updateView.findViewById(R.id.id_edittext);
                final EditText update_name = updateView.findViewById(R.id.name_edittext);
                final EditText update_phone = updateView.findViewById(R.id.phone_edittext);
                final EditText update_address = updateView.findViewById(R.id.address_edittext);
                //EditText updatename = findViewById(R.id.ac_edittext);
                //final CheckBox checkBox = updateView.findViewById(R.id.checkbox_edittext);
                Button buttonUpdate = updateView.findViewById(R.id.update_button);
               final ImageView imageView = updateView.findViewById(R.id.imageViewUpdate_part_ShowInformation);

               // Button upload_image = updateView.findViewById(R.id.button_update_showinformation);
               // final RadioGroup


                update_id.setText(userid);
                update_name.setText(userName);
                update_address.setText(address);
                update_phone.setText(phone);
                Glide.with(updateView.getContext()).load(imageuri).into(imageView);






                // checkBox.isChecked();

              final  AlertDialog alertDialog = dialogbuilder.create();

                alertDialog.show();


                buttonUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String username = update_name.getText().toString();
                        String phoneNumber = update_phone.getText().toString();
                        String address =update_address.getText().toString();
                       // String checked = null;

/*
                        if(active.isChecked())
                        {

                            checked = "Active";
                            //Toast.makeText(AddListActivity.this,checked, Toast.LENGTH_SHORT).show();


                        }else
                        {
                            checked ="unActive";
                            //Toast.makeText(AddListActivity.this,checked, Toast.LENGTH_SHORT).show();


                        }*/





                        Information information = new Information( userid ,username ,phoneNumber,address,imageuri);

                        showDatabase.child(userid).setValue(information);

                      //  showDatabase.child("user").setValue()


                        alertDialog.dismiss();
                    }

                });



               /* upload_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent();
                        intent.setType("image*//*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);

                        startActivityForResult(Intent.createChooser(intent,"Select image"),Request_code);


                    }
                });*/



//                update_name.setText(userName);
  //              update_phone.setText(phone);
    //            update_address.setText(address);


                //update(userid,userName,phone,address,active);
            }
        });



        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deletemethod(userid);

                alertDialog.dismiss();
            }
        });


    }





    private void deletemethod(final String id)
    {



        DatabaseReference dtsabbirid = FirebaseDatabase.getInstance().getReference("user").child(id);

        dtsabbirid.removeValue();

        for(int i = 2018; i<= 2030; i++)
        {
            for(int j = 0; j<12 ;j++)
            {

                String m = Month.month[j];

                DatabaseReference payment = FirebaseDatabase.getInstance().getReference("payment").child(String.valueOf(i)).child(m).child(id);
                payment.removeValue();

            }


        }



    }


   /* private void update(final String userid, String userName, String phone, String address, final String active)
    {

        final TextView update_id = findViewById(R.id.id_edittext);
        final EditText update_name = findViewById(R.id.name_edittext);
        final EditText update_phone = findViewById(R.id.phone_edittext);
        final EditText update_address = findViewById(R.id.address_edittext);
        //EditText updatename = findViewById(R.id.ac_edittext);
        //final CheckBox checkBox = findViewById(R.id.checkbox_edittext);
        final Button buttonUpdate = findViewById(R.id.update_button);





        update_id.setText(userid);
        update_name.setText(userName);
        update_phone.setText(phone);
        update_address.setText(address);


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = update_name.getText().toString().trim();
                String phoneNumber = update_name.getText().toString().trim();
                String address = update_name.getText().toString().trim();
               // String name = update_name.getText().toString().trim();
               String checked = null;

                if(!active.isEmpty())
                {

                    checked = "Active";
                  //  Toast.makeText(AddListActivity.this,checked, Toast.LENGTH_SHORT).show();


                }else
                {
                    checked ="unActive";
                   // Toast.makeText(AddListActivity.this,checked, Toast.LENGTH_SHORT).show();


                }
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user");

                Information information = new Information( userid ,username ,phoneNumber,address,checked);

                databaseReference.child(userid).setValue(information);

            }
        });


    }
*/

    private void methd()
    {



        showDatabase.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                sabbirlist.clear();


                for (DataSnapshot sabbirSnapshot : dataSnapshot.getChildren())
                {


                    Information information = sabbirSnapshot.getValue(Information.class);

                    sabbirlist.add(information);


                }




                InformationList adapter = new InformationList(ShowListActivity.this,sabbirlist);
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }





}