package com.example.federico.mlibrefedericopuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paging {


    @SerializedName("total")
    @Expose
    private Long total;
    @SerializedName("offset")
    @Expose
    private Long offset;
    @SerializedName("limit")
    @Expose
    private Long limit;
    @SerializedName("primary_results")
    @Expose
    private Long primaryResults;

    /**
     * No args constructor for use in serialization
     *
     */
    public Paging() {
    }

    /**
     *
     * @param limit
     * @param total
     * @param offset
     * @param primaryResults
     */
    public Paging(Long total, Long offset, Long limit, Long primaryResults) {
        super();
        this.total = total;
        this.offset = offset;
        this.limit = limit;
        this.primaryResults = primaryResults;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getPrimaryResults() {
        return primaryResults;
    }

    public void setPrimaryResults(Long primaryResults) {
        this.primaryResults = primaryResults;
    }
}
