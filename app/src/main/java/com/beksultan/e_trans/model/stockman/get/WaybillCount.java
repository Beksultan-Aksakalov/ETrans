package com.beksultan.e_trans.model.stockman.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class WaybillCount {

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
    public ArrayList<Waybill> waybillList;

}
