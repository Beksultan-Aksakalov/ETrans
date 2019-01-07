package com.beksultan.e_trans.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("key")
    @Expose
    public String token;

    public String getAuthToken(){
        return "Token " + token;
    }
}
