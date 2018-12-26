package com.example.sabbir.ispbilling;

/**
 * Created by sabbir on 3/22/2018.
 */

public class Information{

    String userid;
    String userName;
    String phone;
    String address;
    String imageuri;

    public Information() {

    }


    public Information(String userid, String userName, String phone, String address, String imageuri) {
        this.userid = userid;
        this.userName = userName;
        this.phone = phone;
        this.address = address;
        this.imageuri = imageuri;
    }

    public String getUserid() {
        return userid;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getImageuri() {
        return imageuri;
    }
}
