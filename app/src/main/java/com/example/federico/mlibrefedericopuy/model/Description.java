package com.example.federico.mlibrefedericopuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Description {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("plain_text")
    @Expose
    private String plainText;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;

    /**
     * No args constructor for use in serialization
     *
     */
    public Description() {
    }

    /**
     *
     * @param text
     * @param dateCreated
     * @param lastUpdated
     * @param plainText
     */
    public Description(String text, String plainText, String lastUpdated, String dateCreated) {
        super();
        this.text = text;
        this.plainText = plainText;
        this.lastUpdated = lastUpdated;
        this.dateCreated = dateCreated;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
