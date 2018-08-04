package com.example.federico.mlibrefedericopuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Installments {


    @SerializedName("quantity")
    @Expose
    private Long quantity;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("currency_id")
    @Expose
    private String currencyId;

    /**
     * No args constructor for use in serialization
     *
     */
    public Installments() {
    }

    /**
     *
     * @param amount
     * @param rate
     * @param currencyId
     * @param quantity
     */
    public Installments(Long quantity, Double amount, Double rate, String currencyId) {
        super();
        this.quantity = quantity;
        this.amount = amount;
        this.rate = rate;
        this.currencyId = currencyId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }
}
