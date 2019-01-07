package com.beksultan.e_trans.model.expeditor.get;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderCount implements Parcelable{

    @SerializedName("count")
    @Expose
    public int count;

    @SerializedName("next")
    @Expose
    public String next;

    @SerializedName("previous")
    @Expose
    public String previous;

    @SerializedName("results")
    @Expose
    public ArrayList<Order> orderList;

    protected OrderCount(Parcel in) {
        count = in.readInt();
        next = in.readString();
        previous = in.readString();
    }

    public static final Creator<OrderCount> CREATOR = new Creator<OrderCount>() {
        @Override
        public OrderCount createFromParcel(Parcel in) {
            return new OrderCount(in);
        }

        @Override
        public OrderCount[] newArray(int size) {
            return new OrderCount[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeString(next);
        dest.writeString(previous);
    }
}
