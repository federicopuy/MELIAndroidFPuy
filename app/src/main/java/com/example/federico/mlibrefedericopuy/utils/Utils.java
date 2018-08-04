package com.example.federico.mlibrefedericopuy.utils;

public class Utils {

    public static String getFormattedPrice(Double price){

        try{
            return "$ " + String.format("%.2f", price);
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
