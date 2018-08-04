package com.example.federico.mlibrefedericopuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Shipping {


    @SerializedName("free_shipping")
    @Expose
    private Boolean freeShipping;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("logistic_type")
    @Expose
    private String logisticType;

    /**
     * No args constructor for use in serialization
     *
     */
    public Shipping() {
    }

    /**
     *
     * @param tags
     * @param freeShipping
     * @param logisticType
     * @param mode
     */
    public Shipping(Boolean freeShipping, String mode, List<Object> tags, String logisticType) {
        super();
        this.freeShipping = freeShipping;
        this.mode = mode;
        this.tags = tags;
        this.logisticType = logisticType;
    }

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public String getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(String logisticType) {
        this.logisticType = logisticType;
    }
}
