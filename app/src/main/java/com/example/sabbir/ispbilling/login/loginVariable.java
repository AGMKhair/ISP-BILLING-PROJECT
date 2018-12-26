package com.example.sabbir.ispbilling.login;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sabbir on 4/14/2018.
 */

public class loginVariable {

    public static String m=null,n=null,o=null;

    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("login");


}
