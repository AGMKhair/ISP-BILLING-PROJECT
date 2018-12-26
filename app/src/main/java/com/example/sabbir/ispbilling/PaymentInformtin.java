
package com.example.sabbir.ispbilling;

/**
 * Created by sabbir on 3/23/2018.
 */

public class PaymentInformtin{

    String customer_id;
    String year;
    String month;
    String active;
    String amount;
    String bandwidth;
    String payment_type;

    public PaymentInformtin() {
    }
/*    public PaymentInformtin(String customer_id, String year, String month, String active, String amount, String bandwidth) {
        this.customer_id = customer_id;
        this.year = year;
        this.month = month;
        this.active = active;
        this.amount = amount;
        this.bandwidth = bandwidth;
    }*/

    public PaymentInformtin(String customer_id, String year, String month, String active, String amount, String bandwidth, String payment_type) {
        this.customer_id = customer_id;
        this.year = year;
        this.month = month;
        this.active = active;
        this.amount = amount;
        this.bandwidth = bandwidth;
        this.payment_type = payment_type;
    }






    public String getCustomer_id() {
        return customer_id;
    }

    public String getActive() {
        return active;
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public String getAmount() {
        return amount;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

  /*  public PaymentInformtin(String customer_id, String year, String month, String amount, String payment_type) {
        this.customer_id = customer_id;
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.payment_type = payment_type;
    }*/
}
