package com.example.federico.mlibrefedericopuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResults {


    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("paging")
    @Expose
    private Paging paging;
    @SerializedName("results")
    @Expose
    private List<Product> products = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public SearchResults() {
    }

    /**
     *
     * @param products
     * @param siteId
     * @param query
     * @param paging
     */
    public SearchResults(String siteId, String query, Paging paging, List<Product> products) {
        super();
        this.siteId = siteId;
        this.query = query;
        this.paging = paging;
        this.products = products;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
