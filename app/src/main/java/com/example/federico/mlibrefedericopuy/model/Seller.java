package com.example.federico.mlibrefedericopuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Seller {


    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("power_seller_status")
    @Expose
    private String powerSellerStatus;
    @SerializedName("car_dealer")
    @Expose
    private Boolean carDealer;
    @SerializedName("real_estate_agency")
    @Expose
    private Boolean realEstateAgency;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Seller() {
    }

    /**
     *
     * @param tags
     * @param powerSellerStatus
     * @param id
     * @param realEstateAgency
     * @param carDealer
     */
    public Seller(Long id, String powerSellerStatus, Boolean carDealer, Boolean realEstateAgency, List<Object> tags) {
        super();
        this.id = id;
        this.powerSellerStatus = powerSellerStatus;
        this.carDealer = carDealer;
        this.realEstateAgency = realEstateAgency;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPowerSellerStatus() {
        return powerSellerStatus;
    }

    public void setPowerSellerStatus(String powerSellerStatus) {
        this.powerSellerStatus = powerSellerStatus;
    }

    public Boolean getCarDealer() {
        return carDealer;
    }

    public void setCarDealer(Boolean carDealer) {
        this.carDealer = carDealer;
    }

    public Boolean getRealEstateAgency() {
        return realEstateAgency;
    }

    public void setRealEstateAgency(Boolean realEstateAgency) {
        this.realEstateAgency = realEstateAgency;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }
}
