package com.example.sabbir.ispbilling;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

/**
 * Created by sabbir on 3/23/2018.
 */

public class BillidInformation extends ArrayAdapter<Information> {



    private Activity context;
    private List<Information> sabbirList;


    public BillidInformation(Activity context, List<Information> sabbirList) {
        super(context, R.layout.bill_id_layout, sabbirList);

        this.context = context;
        this.sabbirList = sabbirList;
    }



    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.bill_id_layout, null, true);

        TextView textVewid = listViewItem.findViewById(R.id.bill_id_show);
        TextView textVewname = listViewItem.findViewById(R.id.bill_name_show);
        ImageView imageView = listViewItem.findViewById(R.id.bill_image_show);

       //  TextView textViewphone = listViewItem.findViewById(R.id);
       /* TextView textViewaddress = listViewItem.findViewById(R.id.show_user_address);
        TextView textViewactive = listViewItem.findViewById(R.id.show_user_active);
        // */ // TextView textViewmonth = listViewItem.findViewById(R.id.show_month_amount);

/*


        Calendar m = Calendar.getInstance();

        int month = m.get(Calendar.MONTH);

        int year = m.get(Calendar.YEAR);

        // String mc = DateFormat.getDateInstance().format()




*/

        Information information = sabbirList.get(position);


       /* DatabaseReference sabb = FirebaseDatabase.getInstance().getReference("payment").child(String.valueOf(year)).child(Month.month[month]).child(information.getUserid());

        sabbir(sabb);
*/
        //information.getUserid();
        textVewid.setText(information.getUserid());
        textVewname.setText(information.getUserName());
     //   textViewphone.setText(active);
        Glide.with(context).load(information.getImageuri()).into(imageView);
        /* textVewname.setText(information.getUserName());
        textViewphone.setText(information.getPhone());
        textViewaddress.setText(information.getAddress());
        textViewactive.setText(information.getActive());

*/


        //textViewmonth.setText(" user id : "+information.);

        return listViewItem;

    }

    public String active = null;

/*

    private void sabbir(DatabaseReference data)
    {
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                active = dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
*/

}
