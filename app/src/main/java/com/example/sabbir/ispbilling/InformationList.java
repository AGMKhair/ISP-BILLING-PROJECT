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

import java.util.List;




public class InformationList extends ArrayAdapter<Information> {



    private Activity context;
    private List<Information> sabbirList;


    public InformationList(Activity context, List<Information> sabbirList) {
        super(context, R.layout.list_layout, sabbirList);

        this.context = context;
        this.sabbirList = sabbirList;
    }
        


    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        //        return super.getView(position, convertView, parent);

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textVewid = listViewItem.findViewById(R.id.show_user_id);
        TextView textVewname = listViewItem.findViewById(R.id.show_user_name);
        TextView textViewphone = listViewItem.findViewById(R.id.show_user_phone);
        TextView textViewaddress = listViewItem.findViewById(R.id.show_user_address);
        ImageView imageView = listViewItem.findViewById(R.id.imageView_Update_part);

        //   TextView textViewmonth    = listViewItem.findViewById(R.id.show_month_amount);


        Information information = sabbirList.get(position);


        textVewid.setText(information.getUserid());
        textVewname.setText(information.getUserName());
        textViewphone.setText(information.getPhone());
        textViewaddress.setText(information.getAddress());
        //imageView.setImageBitmap();
        Glide.with(context).load(information.getImageuri()).into(imageView);



        //textViewmonth.setText(" user id : "+information.);

        return listViewItem;
    }


}