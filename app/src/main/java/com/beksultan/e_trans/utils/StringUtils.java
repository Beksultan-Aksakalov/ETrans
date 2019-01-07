package com.beksultan.e_trans.utils;

public class StringUtils {

    public static boolean isOk(String text){
        return text != null && !text.trim().isEmpty();
    }

    public static double getDouble(String text){
        try{
            return isOk(text) ? Double.parseDouble(text) : 0;
        }
        catch (Exception e){
            return  0;
        }
    }
}
