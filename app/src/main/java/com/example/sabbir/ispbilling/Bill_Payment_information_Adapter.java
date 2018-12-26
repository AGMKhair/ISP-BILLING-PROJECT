package com.example.sabbir.ispbilling;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sabbir on 4/2/2018.
 */

public class Bill_Payment_information_Adapter extends ArrayAdapter<PaymentInformtin>
{




    private Activity context;
    private List<PaymentInformtin> sabbirList;


    public Bill_Payment_information_Adapter(Activity context, List<PaymentInformtin> sabbirList) {
        super(context, R.layout.show_payment_layout, sabbirList);

        this.context = context;
        this.sabbirList = sabbirList;
    }



    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.show_payment_layout, null, true);


        TextView id = listViewItem.findViewById(R.id.textView);
        TextView year_month = listViewItem.findViewById(R.id.textView1);
        TextView amount = listViewItem.findViewById(R.id.textView2);
        TextView bandwith = listViewItem.findViewById(R.id.bandwth);
        TextView active = listViewItem.findViewById(R.id.activeuser);
        TextView caseh = listViewItem.findViewById(R.id.textView3);

        //TextView textViewactive = listViewItem.findViewById(R.id.show_user_active);
        // */ // TextView textViewmonth = listViewItem.findViewById(R.id.show_month_amount);


        PaymentInformtin information = sabbirList.get(position);


        id.setText(information.getCustomer_id());
        amount.setText("Amount : "+information.getAmount());
        caseh.setText(information.getPayment_type());
        bandwith.setText("Bandwidth : "+ information.getBandwidth());
        year_month.setText(information.getMonth() + " - "+information.getYear());

        active.setText(information.getActive());
  //      textViewactive.setText(information.getActive());




        //textViewmonth.setText(" user id : "+information.);

        return listViewItem;

    }



}
