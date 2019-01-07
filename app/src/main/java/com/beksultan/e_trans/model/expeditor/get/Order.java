package com.beksultan.e_trans.model.expeditor.get;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order implements Parcelable{

    @SerializedName("id")
    @Expose
    public String id;

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

    @SerializedName("direction")
    public String direction;

    @SerializedName("good_ready")
    public String good_ready;

    @SerializedName("created_date")
    @Expose
    public String created_date;

    @SerializedName("updated_date")
    @Expose
    public String updated_date;

    @SerializedName("finished_date")
    @Expose
    public String finished_date;


    protected Order(Parcel in) {
        id = in.readString();
        status = in.readString();
        full_name = in.readString();
        sender_address = in.readString();
        sender_phone = in.readString();
        seat_count = in.readString();
        company_name = in.readString();
        direction = in.readString();
        good_ready = in.readString();
        created_date = in.readString();
        updated_date = in.readString();
        finished_date = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(status);
        dest.writeString(full_name);
        dest.writeString(sender_address);
        dest.writeString(sender_phone);
        dest.writeString(seat_count);
        dest.writeString(company_name);
        dest.writeString(direction);
        dest.writeString(good_ready);
        dest.writeString(created_date);
        dest.writeString(updated_date);
        dest.writeString(finished_date);
    }
}
