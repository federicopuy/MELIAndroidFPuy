package com.example.federico.mlibrefedericopuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reviews {


    @SerializedName("rating_average")
    @Expose
    private Double ratingAverage;
    @SerializedName("total")
    @Expose
    private Long total;

    /**
     * No args constructor for use in serialization
     *
     */
    public Reviews() {
    }

    /**
     *
     * @param total
     * @param ratingAverage
     */
    public Reviews(Double ratingAverage, Long total) {
        super();
        this.ratingAverage = ratingAverage;
        this.total = total;
    }

    public Double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(Double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
