package com.example.federico.mlibrefedericopuy.utils;

import com.example.federico.mlibrefedericopuy.model.Item;
import com.example.federico.mlibrefedericopuy.model.Picture;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String getFormattedPrice(Double price){

        try{
            return "$ " + String.format("%.2f", price);
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static String getCondition(String englishCondition){

        if (englishCondition.equals("new")){
            return "Nuevo";
        } else {
            return "Usado";
        }
    }
    public static String getFormattedRatingAverage(Double ratingAverage){
        return "("+String.valueOf(ratingAverage)+")";
    }

    public static List<String> getPicturesList(Item item){

        ArrayList<String> pictureUrls = new ArrayList<>();

            for (Picture p:item.getPictures()){
                pictureUrls.add(p.getUrl());
            }
            return pictureUrls;
    }
}
