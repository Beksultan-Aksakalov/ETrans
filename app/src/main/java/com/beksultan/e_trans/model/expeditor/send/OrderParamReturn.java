package com.beksultan.e_trans.model.expeditor.send;

public class OrderParamReturn {

    public String full_name;

    public String sender_address;

    public String sender_city;

    public String sender_phone;

    public String seat_count;

    public String company_name;

    public String receiver_address;

    public String receiver_city;

    public String receiver_phone;

    public String weight;

    public String weight_type;

    public String cost;

    public String waybill_number;

    public String status;

    public String expeditor;

    public String krizh;

    public OrderParamReturn(String full_name, String sender_address, String sender_city, String sender_phone, String seat_count, String company_name, String receiver_address, String receiver_city, String receiver_phone, String weight, String weight_type, String cost, String waybill_number, String status, String expeditor, String krizh) {
        this.full_name = full_name;
        this.sender_address = sender_address;
        this.sender_city = sender_city;
        this.sender_phone = sender_phone;
        this.seat_count = seat_count;
        this.company_name = company_name;
        this.receiver_address = receiver_address;
        this.receiver_city = receiver_city;
        this.receiver_phone = receiver_phone;
        this.weight = weight;
        this.weight_type = weight_type;
        this.cost = cost;
        this.waybill_number = waybill_number;
        this.status = status;
        this.expeditor = expeditor;
        this.krizh = krizh;
    }
}
