package com.beksultan.e_trans.model.stockman.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Waybill {

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("sender_city")
    @Expose
    public String sender_city;

    @SerializedName("receiver_city")
    @Expose
    public String receiver_city;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("full_name")
    @Expose
    public String full_name;

    @SerializedName("sender_address")
    @Expose
    public String sender_address;
    @SerializedName("sender_phone")
    @Expose
    public String sender_phone;

    @SerializedName("seat_count")
    @Expose
    public String seat_count;
    @SerializedName("company_name")
    @Expose
    public String company_name;

    @SerializedName("receiver_address")
    @Expose
    public String receiver_address;

    @SerializedName("receiver_phone")
    @Expose
    public String receiver_phone;

    @SerializedName("receiver_name")
    @Expose
    public String receiver_name;

    @SerializedName("receiver_company")
    @Expose
    public String receiver_company;

    @SerializedName("waybill_number")
    @Expose
    public String waybill_number;

    @SerializedName("cost")
    @Expose
    public String cost;

    @SerializedName("weight")
    @Expose
    public String weight;

    @SerializedName("weight_type")
    @Expose
    public String weight_type;

    @SerializedName("krizh")
    @Expose
    public String krizh;

    @SerializedName("created_date")
    @Expose
    public String created_date;

    @SerializedName("updated_date")
    @Expose
    public String updated_date;

    @SerializedName("finished_date")
    @Expose
    public String finished_date;

}
