package com.beksultan.e_trans.model.expeditor.put;

public class StatusParam {

    public String status;

    public StatusParam(String status) {
        this.status = status;
    }

    public boolean isValidAddress() { return status != null && !status.trim().isEmpty(); }
}
