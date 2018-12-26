package com.example.sabbir.ispbilling;

import android.app.AlertDialog;
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
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class BilInformatinShowActivity extends AppCompatActivity {


    PaymentInformtin info;
    ListView list;
    ArrayList<PaymentInformtin> sabbirlist;
    List<Information> sabbir;

    Button Button_Search ;

    Spinner spinner_year ,spinner_month;


    DatabaseReference payment_database,Backup_payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_layout);


        list = findViewById(R.id.payment_listView);

        sabbirlist = new ArrayList<>();
        sabbir = new ArrayList<>();

        Button_Search = findViewById(R.id.button_payment_search);

        spinner_year = findViewById(R.id.spinner_payment_year);
        spinner_month = findViewById(R.id.spinner_payment_month);

        Button_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if(spinner_month.getSelectedItem().equals("Year"))

                if (spinner_year.getSelectedItem().equals("Year")) {
                    Toast.makeText(getApplicationContext(), " Selecte Year ", Toast.LENGTH_SHORT).show();
                } else if (spinner_month.getSelectedItem().equals("Month")) {

                    Toast.makeText(getApplicationContext(), " Selecte Month ", Toast.LENGTH_SHORT).show();

                } else {


                    active_method(spinner_year.getSelectedItem().toString(), spinner_month.getSelectedItem().toString());


                }
            }
        });



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                info = sabbirlist.get(position);
             showdialog(info);

           //     test();

            }
        });


/*DatabaseReference dtsabbirid = FirebaseDatabase.getInstance().getReference("user").child(information.getUserid());

                dtsabbirid.removeValue();*/


    }


    private void active_method( final String year, final String month)

    {

        //System.out.println();


        DatabaseReference sab = FirebaseDatabase.getInstance().getReference("payment").child(year).child(month);

       // Toast.makeText(BilInformatinShowActivity.this, " first " ,Toast.LENGTH_SHORT).show();

        sab.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

      //          Toast.makeText(BilInformatinShowActivity.this, " secand " + dataSnapshot ,Toast.LENGTH_SHORT).show();

                sabbirlist.clear();


                for (DataSnapshot sabbirSnapshot : dataSnapshot.getChildren())
                {
    //                System.out.println(" success "+ sabbirSnapshot);

                    String s = sabbirSnapshot.child("active").getValue().toString();

//                     Toast.makeText(BilInformatinShowActivity.this, s ,Toast.LENGTH_SHORT).show();

                    String a = "active";
                    String b = "inactive";

                    if(s.equalsIgnoreCase(a))
                    {
  //                      Toast.makeText(BilInformatinShowActivity.this," if " +s ,Toast.LENGTH_SHORT).show();


                        PaymentInformtin information = sabbirSnapshot.getValue(PaymentInformtin.class);

                        sabbirlist.add(information);

                    }


                    Bill_Payment_information_Adapter adapter = new Bill_Payment_information_Adapter(BilInformatinShowActivity.this,sabbirlist);

                    list.setAdapter(adapter);

                }


                    //   Toast.makeText(getApplicationContext(),"ert " +k,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }






    @Override
    protected void onStart() {
        super.onStart();
     //   methd();


        Calendar m = Calendar.getInstance();

        int month = m.get(Calendar.MONTH);
        int year = m.get(Calendar.YEAR);

        String s = Month.month[month];
        active_method(String.valueOf(year), s);

    }


    public void test()

    {

         AlertDialog.Builder dialogbuild;

        dialogbuild = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = getLayoutInflater();

         View dialogview = layoutInflater.inflate(R.layout.activity_paymnt_shw,null);

        dialogbuild.setView(dialogview);

     /*   final Spinner month = dialogview.findViewById(R.id.spinner_month);
        final Spinner paid = dialogview.findViewById(R.id.spinner_paid);
        final Spinner year = dialogview.findViewById(R.id.spinner_year);

        final Button save_payment = dialogview.findViewById(R.id.save_payment_button);

*/

         AlertDialog alertDialog = dialogbuild.create();

        alertDialog.show();

    }



    private void showdialog(final PaymentInformtin info)

    {

        final AlertDialog.Builder dialogbuilder;


        dialogbuilder = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = getLayoutInflater();

        final View dialogview = layoutInflater.inflate(R.layout.activity_paymnt_shw,null);

        dialogbuilder.setView(dialogview);

        final Spinner month = dialogview.findViewById(R.id.payment_month);
        //final Spinner active = dialogview.findViewById(R.id.payment_active);
        final Spinner year = dialogview.findViewById(R.id.payment_year);
        final Spinner type = dialogview.findViewById(R.id.payment_type);

        final EditText amount =  dialogview.findViewById(R.id.amount);
        //final EditText bandwidth =  dialogview.findViewById(R.id.bandwidth);

        final Button save_payment = dialogview.findViewById(R.id.payment_button);

        /*amount.setText("0.00");
        amount.setText("0.00");*/
        final AlertDialog alertDialog = dialogbuilder.create();

        alertDialog.show();

        save_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if( year.getSelectedItem().equals("Year"))
                {
                    Toast.makeText(getApplicationContext()," Select Year ",Toast.LENGTH_SHORT).show();

                }

                else if (month.getSelectedItem().equals("Month") )
                    {
                        Toast.makeText(getApplicationContext()," Select Month ",Toast.LENGTH_SHORT).show();
                    }

                  else if( amount.getText().toString().isEmpty() )
                   {
                       Toast.makeText(getApplicationContext()," Enter The Amount ",Toast.LENGTH_SHORT).show();

                   }
                    else if(type.getSelectedItem().equals("Payment Type") )
                   {

                       Toast.makeText(getApplicationContext()," Select Payment Type ",Toast.LENGTH_SHORT).show();

                   }
                   else
                    {

                        String yearstring = (String) year.getSelectedItem();
                        String monthstring = (String) month.getSelectedItem();
                       // String activestring = (String) active.getSelectedItem();
                        String typestring = (String) type.getSelectedItem();

                        String amountString = amount.getText().toString();
                       // String bandwidthString = bandwidth.getText().toString();

                        PaymentInformtin paymentInformtin = new PaymentInformtin(info.getCustomer_id(),yearstring,monthstring,info.getActive(),amountString,info.bandwidth, typestring);


                        payment_database = FirebaseDatabase.getInstance().getReference("payment");
                        payment_database.child(yearstring).child(monthstring).child(info.getCustomer_id()).setValue(paymentInformtin);


                        Backup_payment = FirebaseDatabase.getInstance().getReference("backup_payment");
                        Backup_payment.child(yearstring).child(monthstring).child(info.getCustomer_id()).setValue(paymentInformtin);

                        alertDialog.dismiss();

                    }

            }
        });



    }


    private void methd()

    {

        DatabaseReference dtsabbirid = FirebaseDatabase.getInstance().getReference("user");


        dtsabbirid.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                sabbir.clear();


/*
                String s =  dataSnapshot.child(user).child(userphone).getValue(String.class);
                textView.setText(s);*/

                for (DataSnapshot sabbirSnapshot : dataSnapshot.getChildren())
                {


                    Information information = sabbirSnapshot.getValue(Information.class);

                    sabbir.add(information);


                }






                BillidInformation adapter = new BillidInformation(BilInformatinShowActivity.this,sabbir);
//                findViewById(R.id.bill_active_show).isEnabled();
                list.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
