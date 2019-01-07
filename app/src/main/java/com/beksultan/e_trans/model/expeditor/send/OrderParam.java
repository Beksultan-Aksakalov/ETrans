package com.beksultan.e_trans.model.expeditor.send;

public class OrderParam {

    public String sender_city;

    public String receiver_city;

    public String full_name;

    public String sender_address;

    public String sender_phone;

    public String seat_count;

    public String company_name;

    public String receiver_address;

    public String receiver_phone;

    public String receiver_name;

    public String receiver_company;

    public String waybill_number;

    public String cost;

    public String weight;

    public String weight_type;

    public String krizh;

    public String payer;

    public String pay_type;

    public OrderParam(String sender_city,
                      String receiver_city,
                      String full_name,
                      String sender_address,
                      String sender_phone,
                      String seat_count,
                      String company_name,
                      String receiver_address,
                      String receiver_phone,
                      String receiver_name,
                      String receiver_company,
                      String waybill_number,
                      String cost,
                      String weight,
                      String weight_type,
                      String krizh,
                      String payer,
                      String pay_type) {

        this.sender_city = sender_city;
        this.receiver_city = receiver_city;
        this.full_name = full_name;
        this.sender_address = sender_address;
        this.sender_phone = sender_phone;
        this.seat_count = seat_count;
        this.company_name = company_name;
        this.receiver_address = receiver_address;
        this.receiver_phone = receiver_phone;
        this.receiver_name = receiver_name;
        this.receiver_company = receiver_company;
        this.waybill_number = waybill_number;
        this.cost = cost;
        this.weight = weight;
        this.weight_type = weight_type;
        this.krizh = krizh;
        this.payer = payer;
        this.pay_type = pay_type;
    }

    public boolean isValidReceiverFullName() {
        return receiver_name != null && !receiver_name.trim().isEmpty();
    }

    public boolean isValidReceiverCompany() {
        return receiver_company != null && !receiver_company.trim().isEmpty();
    }

    public boolean isValidReceiverCity() {
        return receiver_city != null && !receiver_city.trim().isEmpty();
    }

    public boolean isValidReceiverAddress() {
        return receiver_address != null && !receiver_address.trim().isEmpty();
    }

    public boolean isValidReceiverPhone() {
        return receiver_phone != null && !receiver_phone.trim().isEmpty();
    }

    public boolean isValidSenderFullName() {
        return full_name != null && !full_name.trim().isEmpty();
    }

    public boolean isValidSenderCompany() {
        return company_name != null && !company_name.trim().isEmpty();
    }

    public boolean isValidSenderCity() {
        return sender_city != null && !sender_city.trim().isEmpty();
    }

    public boolean isValidSenderAddress() {
        return sender_address != null && !sender_address.trim().isEmpty();
    }

    public boolean isValidSenderPhone() {
        return sender_phone != null && !sender_phone.trim().isEmpty();
    }

    public boolean isValidSeatCount() {
        return seat_count != null && !seat_count.trim().isEmpty();
    }

    public boolean isValidWaybilNumber() {
        return waybill_number != null && !waybill_number.trim().isEmpty();
    }

    public boolean isValidCost() {
        return cost != null && !cost.trim().isEmpty();
    }

    public boolean isValidWeight() {
        return weight != null && !weight.trim().isEmpty();
    }

    public boolean isValidWeightType() {
        return weight_type != null && !weight_type.trim().isEmpty();
    }

    public boolean isValidKrizh() {
        return krizh != null && !krizh.trim().isEmpty();
    }

    public boolean isValidPayer() {
        return payer != null && !payer.trim().isEmpty();
    }

    public boolean isValidPayType() {
        return pay_type != null && !pay_type.trim().isEmpty();
    }

}
