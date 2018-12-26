package com.example.sabbir.ispbilling;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    ListView listView;

    List<Information> sabbirlist;

    DatabaseReference dtsabbirid;

    Spinner year, month;

    DatabaseReference payment_database,Backup_payment;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        dtsabbirid = FirebaseDatabase.getInstance().getReference("user");

        listView = findViewById(R.id.Delete_listView);

        sabbirlist = new ArrayList<>();


        year = findViewById(R.id.spinner_year_update);
        month = findViewById(R.id.spinner_month_update);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // alartDialogMethod(information);


                if( year.getSelectedItem().equals("Year"))
                {
                    Toast.makeText(getApplicationContext()," Select Year ",Toast.LENGTH_SHORT).show();

                }


                else if (month.getSelectedItem().equals("Month") )

                {
                    Toast.makeText(getApplicationContext()," Select Month ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final Information information = sabbirlist.get(position);


                    final AlertDialog.Builder dialogbuilder ;


                    dialogbuilder = new AlertDialog.Builder(DeleteActivity.this);

                    LayoutInflater layoutInflater = getLayoutInflater();

                    final View dialogview = layoutInflater.inflate(R.layout.activity_update,null);

                    dialogbuilder.setView(dialogview);

                    final Spinner active = dialogview.findViewById(R.id.Spinner_Update_dialog);
                    final Button button = dialogview.findViewById(R.id.Button_update_dialog);
                    final EditText bandwidth = dialogview.findViewById(R.id.edittext_dialog);

                    final AlertDialog alertDialog = dialogbuilder.create();

                    alertDialog.show();

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            String yearstring = year.getSelectedItem().toString();

                            String monthstring = month.getSelectedItem().toString();

                            String bandwth = bandwidth.getText().toString();

                            if(bandwth.isEmpty())
                            {
                                bandwth = "0" ;
                            }

                            PaymentInformtin paymentInformtin = new PaymentInformtin(information.getUserid(),yearstring,monthstring,active.getSelectedItem().toString(), "0", bandwth , "null" );

                            payment_database = FirebaseDatabase.getInstance().getReference("payment");
                            payment_database.child(yearstring).child(monthstring).child(information.getUserid()).setValue(paymentInformtin);


                            Backup_payment = FirebaseDatabase.getInstance().getReference("backup_payment");
                            Backup_payment.child(yearstring).child(monthstring).child(information.getUserid()).setValue(paymentInformtin);
                            alertDialog.dismiss();


                        }
                    });

                }







                //deletmonth.removeValue();

            }
        });

    }



    private void dialogmethod(Information information)
    {

    }




    private void alartDialogMethod(final Information information)
    {


        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to delete it ");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        DatabaseReference dtsabbirid = FirebaseDatabase.getInstance().getReference("user").child(information.getUserid());
                        //  DatabaseReference deletmonth = FirebaseDatabase.getInstance().getReference("payment").child(information.getUserid());


                        for(int i = 2018; i<= 2030; i++)
                        {
                            for(int j = 0; j<12 ;j++)
                            {

                                String m = Month.month[j];

                                DatabaseReference payment = FirebaseDatabase.getInstance().getReference("payment").child(String.valueOf(i)).child(m).child(information.getUserid());
                                payment.removeValue();

                            }


                        }

                        dtsabbirid.removeValue();

                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    @Override
    protected void onStart()
    {
        super.onStart();

        methd();

    }

    private void methd()
    {



        dtsabbirid.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                sabbirlist.clear();


/*
                String s =  dataSnapshot.child(user).child(userphone).getValue(String.class);
                textView.setText(s);*/

                for (DataSnapshot sabbirSnapshot : dataSnapshot.getChildren())
                {


                    Information information = sabbirSnapshot.getValue(Information.class);

                    sabbirlist.add(information);


                }






                BillidInformation adapter = new BillidInformation(DeleteActivity.this,sabbirlist);
//                findViewById(R.id.bill_active_show).isEnabled();
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
