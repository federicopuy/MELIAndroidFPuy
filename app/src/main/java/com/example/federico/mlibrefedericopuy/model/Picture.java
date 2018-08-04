package com.example.federico.mlibrefedericopuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Picture {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("secure_url")
    @Expose
    private String secureUrl;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("max_size")
    @Expose
    private String maxSize;
    @SerializedName("quality")
    @Expose
    private String quality;

    /**
     * No args constructor for use in serialization
     *
     */
    public Picture() {
    }

    /**
     *
     * @param id
     * @param quality
     * @param secureUrl
     * @param maxSize
     * @param url
     * @param size
     */
    public Picture(String id, String url, String secureUrl, String size, String maxSize, String quality) {
        super();
        this.id = id;
        this.url = url;
        this.secureUrl = secureUrl;
        this.size = size;
        this.maxSize = maxSize;
        this.quality = quality;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecureUrl() {
        return secureUrl;
    }

    public void setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

}
