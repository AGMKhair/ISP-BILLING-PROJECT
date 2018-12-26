/*
package com.example.sabbir.ispbilling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;

public class BillPaymentActivity extends AppCompatActivity {

    ImageButton user_Payment_using_id, user_payment_using_month;

    CardView billpayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment);


        user_Payment_using_id = findViewById(R.id.bill_user);

      //  user_payment_using_month = findViewById(R.id.bill_month);


        billpayment = findViewById(R.id.cadviewPaymentuser);

       // cadviewPaymentuser


        billpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BillPaymentActivity.this,BilInformatinShowActivity.class);

                startActivity(intent);

            }
        });

        user_Payment_using_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(BillPaymentActivity.this,BilInformatinShowActivity.class);

                startActivity(intent);

            }
        });




    }
}
*/
