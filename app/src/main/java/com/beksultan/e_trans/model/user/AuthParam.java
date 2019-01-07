package com.beksultan.e_trans.model.user;

public class AuthParam {

    public String username;
    public String password;

    public AuthParam(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isValidUsername() { return username != null && !username.trim().isEmpty(); }

    public boolean isValidPassword() {return password != null && !password.trim().isEmpty(); }
}
