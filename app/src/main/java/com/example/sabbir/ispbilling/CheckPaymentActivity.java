package com.example.sabbir.ispbilling;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class CheckPaymentActivity extends AppCompatActivity

{

    Button unpaid_Button, Active_button , Inactive_button,paid_button;

    ListView listView;

    Information information;

    List<Information> sabbirlist;

    ArrayList<PaymentInformtin> sabbir;

    String m,searchedString =null;
    String y;

    int com =0;
Spinner month,year;

    @Override

    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_payment);



        paid_button =findViewById(R.id.Paid_button_a);
        unpaid_Button =findViewById(R.id.Unpaid);
        Active_button =findViewById(R.id.activeButton);
        Inactive_button =findViewById(R.id.inactive);


        month = findViewById(R.id.spinner_month);

        year = findViewById(R.id.spinner_year);

        listView = findViewById(R.id.check_list_view);

        sabbirlist = new ArrayList<>();
        sabbir = new ArrayList<>();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // information = sabbirlist.get(position);

            }
        });



    /*    button_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });
*/

        paid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // searchedString = "all";

              //  month.getSelectedItem();




                if(year.getSelectedItem().equals("Year"))
                {
                    Toast.makeText(getApplicationContext()," Selecte Year ",Toast.LENGTH_SHORT).show();
                }
                else if(month.getSelectedItem().equals("Month"))
                {

                    Toast.makeText(getApplicationContext()," Selecte Month ",Toast.LENGTH_SHORT).show();

                }else {

                    com =1;
                    y = (String) year.getSelectedItem();
                    m = (String) month.getSelectedItem();

                    paid_method(y,m);
                   // com =0;

                }

            }
        });



        unpaid_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com =2;
               // searchedString = "";
                //paid_method(y,m);


                if(year.getSelectedItem().equals("Year"))
                {
                    Toast.makeText(getApplicationContext()," Selecte Year ",Toast.LENGTH_SHORT).show();
                }
                else if(month.getSelectedItem().equals("Month"))
                {

                    Toast.makeText(getApplicationContext()," Selecte Month ",Toast.LENGTH_SHORT).show();

                }else {

                    y = (String) year.getSelectedItem();
                    m = (String) month.getSelectedItem();

                    paid_method(y,m);

                 //   com =0;
                }//

            }
        });



        Active_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // active_method(y,m);
                com = 3;

                if(year.getSelectedItem().equals("Year"))
                {
                    Toast.makeText(getApplicationContext()," Selecte Year ",Toast.LENGTH_SHORT).show();
                }
                else if(month.getSelectedItem().equals("Month"))
                {

                    Toast.makeText(getApplicationContext()," Selecte Month ",Toast.LENGTH_SHORT).show();

                }else {

                    y = (String) year.getSelectedItem();
                    m = (String) month.getSelectedItem();

                    active_method(y,m);
                   // com =0;

                }

            }
        });



        Inactive_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com =4;
                //InActive_method(y,m);
                if(year.getSelectedItem().equals("Year"))
                {
                    Toast.makeText(getApplicationContext()," Selecte Year ",Toast.LENGTH_SHORT).show();
                }
                else if(month.getSelectedItem().equals("Month"))
                {

                    Toast.makeText(getApplicationContext()," Selecte Month ",Toast.LENGTH_SHORT).show();

                }else {

                    y = (String) year.getSelectedItem();
                    m = (String) month.getSelectedItem();

                    active_method(y,m);


                }
            }
        });


    }

   // int com;

/*
    private void InActive_method(String year, String month)

    {



        DatabaseReference sa =   FirebaseDatabase.getInstance().getReference("payment").child(year).child(month);




        sa.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                sabbir.clear();


                for (DataSnapshot sabbirSnapshot : dataSnapshot.getChildren())
                {

                    PaymentInformtin informati = sabbirSnapshot.getValue(PaymentInformtin.class);

                    sabbir.add(informati);

                    //   Toast.makeText(getApplicationContext(),"ert " +k,Toast.LENGTH_SHORT).show();

                    Bill_Payment_information_Adapter adapter = new Bill_Payment_information_Adapter(CheckPaymentActivity.this,sabbir);
                    listView.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
*/


    private void active_method(String year, String month)
    {

        DatabaseReference sab =   FirebaseDatabase.getInstance().getReference("payment").child(year).child(month);

        sab.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                sabbir.clear();

                for (DataSnapshot sabbirSnapshot : dataSnapshot.getChildren())
                {

                    String s= sabbirSnapshot.child("active").getValue().toString();
                  // Toast.makeText(CheckPaymentActivity.this, s ,Toast.LENGTH_SHORT).show();

                   String a = "active";
                   String b = "inactive";

                    if(s.equalsIgnoreCase(a) && com == 3 )
                    {
                      //  Toast.makeText(CheckPaymentActivity.this, s+ " ofen" ,Toast.LENGTH_SHORT).show();

                        PaymentInformtin information = sabbirSnapshot.getValue(PaymentInformtin.class);

                        sabbir.add(information);

                    } else if (s.equalsIgnoreCase(b)&& com ==4 )

                    {
                     //   Toast.makeText(CheckPaymentActivity.this, s+ " ofen" ,Toast.LENGTH_SHORT).show();

                        PaymentInformtin informati = sabbirSnapshot.getValue(PaymentInformtin.class);

                        sabbir.add(informati);
                    }
                    else
                    {
/*
                        Toast.makeText(CheckPaymentActivity.this, s+ " ofen" ,Toast.LENGTH_SHORT).show();

                        PaymentInformtin informati = sabbirSnapshot.getValue(PaymentInformtin.class);

                        sabbir.add(informati);*/
                    }


                    //   Toast.makeText(getApplicationContext(),"ert " +k,Toast.LENGTH_SHORT).show();

                    Bill_Payment_information_Adapter adapter = new Bill_Payment_information_Adapter(CheckPaymentActivity.this,sabbir);

                    listView.setAdapter(adapter);


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void paid_method(String year, String month)

    {



        try {


            DatabaseReference sal = FirebaseDatabase.getInstance().getReference("payment").child(year).child(month);


            sal.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    sabbir.clear();


                    for (DataSnapshot sabbirSnapshot : dataSnapshot.getChildren()) {


                        int s = Integer.parseInt(sabbirSnapshot.child("amount").getValue().toString());
                        //  int i ;
                        // = Integer.parseInt(s);
//                    Toast.makeText(CheckPaymentActivity.this, String.valueOf(s) ,Toast.LENGTH_SHORT).show();

                        if (s > 0 && com == 1) {

                            PaymentInformtin informati = sabbirSnapshot.getValue(PaymentInformtin.class);

                            sabbir.add(informati);
                        } else if (s <= 0 && com == 2)

                        {

                            PaymentInformtin informati = sabbirSnapshot.getValue(PaymentInformtin.class);

                            sabbir.add(informati);
                        }


                        //   Toast.makeText(getApplicationContext(),"ert " +k,Toast.LENGTH_SHORT).show();

                        Bill_Payment_information_Adapter adapter = new Bill_Payment_information_Adapter(CheckPaymentActivity.this, sabbir);

                        listView.setAdapter(adapter);


                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        catch (Exception e)
        {

        }

        }


    private void show()

    {

        try {


            Calendar m = Calendar.getInstance();

            int month = m.get(Calendar.MONTH);

            int year = m.get(Calendar.YEAR);

            // String mc = DateFormat.getDateInstance().format()


            DatabaseReference sabb = FirebaseDatabase.getInstance().getReference("payment").child(String.valueOf(year)).child(Month.month[month]);


            sabb.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    sabbir.clear();


                    for (DataSnapshot sabbirSnapshot : dataSnapshot.getChildren()) {


                        PaymentInformtin informati = sabbirSnapshot.getValue(PaymentInformtin.class);

                        sabbir.add(informati);

                        //   Toast.makeText(getApplicationContext(),"ert " +k,Toast.LENGTH_SHORT).show();

                        Bill_Payment_information_Adapter adapter = new Bill_Payment_information_Adapter(CheckPaymentActivity.this, sabbir);

                        listView.setAdapter(adapter);


                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        catch(Exception c)
        {

        }

    }




/*

    private void diyalogmethod()

    {



        final AlertDialog.Builder dialogbuilder;


        dialogbuilder = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = getLayoutInflater();

        final View dialogview = layoutInflater.inflate(R.layout.activity_bil_informatin_show,null);

        dialogbuilder.setView(dialogview);


        final Spinner year = dialogview.findViewById(R.id.spinner_year);
        final Spinner month = dialogview.findViewById(R.id.spinner_month);

        final Button button = dialogview.findViewById(R.id.save_payment_button);


        final AlertDialog alertDialog = dialogbuilder.create();

        alertDialog.show();

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(year.getSelectedItem().equals("Year"))
        {
            Toast.makeText(getApplicationContext()," Selecte Year ",Toast.LENGTH_SHORT).show();
        }
        else if(month.getSelectedItem().equals("Month"))
        {

            Toast.makeText(getApplicationContext()," Selecte Month ",Toast.LENGTH_SHORT).show();

        }
        else
        {

             y = (String) year.getSelectedItem();
             m = (String) month.getSelectedItem();

            if (com == 2)
            {
                active_method(y,m);

            }else
            {
                datashow(y,m);
            }




           alertDialog.dismiss();
        }

    }
});





    }
*/

    private void datashow(String year, String month)

    {


        DatabaseReference sa =   FirebaseDatabase.getInstance().getReference("payment").child(year).child(month);

        //sa.addChildEventListener()
        sa.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                sabbir.clear();


                for (DataSnapshot sabbirSnapshot : dataSnapshot.getChildren())
                {

                //   String act = sabbirSnapshot.child("active").getValue(String.class);
                   String inact = sabbirSnapshot.child("active").getValue(String.class);
                   String am = sabbirSnapshot.child("amount").getValue(String.class);

                   /* if(act.toLowerCase().contains(searchedString.toLowerCase()) && com == 2)
                    {


                        PaymentInformtin informati = sabbirSnapshot.getValue(PaymentInformtin.class);

                        sabbir.add(informati);


                    }else*/ if(am.toLowerCase().contains(searchedString.toLowerCase())  )
                    {
                        PaymentInformtin informati = sabbirSnapshot.getValue(PaymentInformtin.class);

                        sabbir.add(informati);
                    }
                    else if (inact.toLowerCase().contains(searchedString.toLowerCase()))

                    {
                        PaymentInformtin informati = sabbirSnapshot.getValue(PaymentInformtin.class);

                        sabbir.add(informati);

                    }

                    //   Toast.makeText(getApplicationContext(),"ert " +k,Toast.LENGTH_SHORT).show();

                    Bill_Payment_information_Adapter adapter = new Bill_Payment_information_Adapter(CheckPaymentActivity.this,sabbir);

                    listView.setAdapter(adapter);


                }





            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });



    }


    @Override

    protected void onStart()

    {
        super.onStart();

    //methd();

        show();



    }


    private void methd()

    {



        DatabaseReference dtsabbirid = FirebaseDatabase.getInstance().getReference("user");


        dtsabbirid.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                sabbirlist.clear();


                for (DataSnapshot sabbirSnapshot : dataSnapshot.getChildren())
                {


                    Information information = sabbirSnapshot.getValue(Information.class);

                    sabbirlist.add(information);


                }






                BillidInformation adapter = new BillidInformation(CheckPaymentActivity.this,sabbirlist);
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}