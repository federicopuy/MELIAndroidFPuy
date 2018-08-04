package com.example.federico.mlibrefedericopuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("seller")
    @Expose
    private Seller seller;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("currency_id")
    @Expose
    private String currencyId;
    @SerializedName("available_quantity")
    @Expose
    private Long availableQuantity;
    @SerializedName("sold_quantity")
    @Expose
    private Long soldQuantity;
    @SerializedName("buying_mode")
    @Expose
    private String buyingMode;
    @SerializedName("listing_type_id")
    @Expose
    private String listingTypeId;
    @SerializedName("stop_time")
    @Expose
    private String stopTime;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("accepts_mercadopago")
    @Expose
    private Boolean acceptsMercadopago;
    @SerializedName("installments")
    @Expose
    private Installments installments;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("shipping")
    @Expose
    private Shipping shipping;
    @SerializedName("seller_address")
    @Expose
    private SellerAddress sellerAddress;
    @SerializedName("attributes")
    @Expose
    private List<Attribute> attributes = null;
    @SerializedName("original_price")
    @Expose
    private Object originalPrice;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("official_store_id")
    @Expose
    private Object officialStoreId;
    @SerializedName("catalog_product_id")
    @Expose
    private Object catalogProductId;
    @SerializedName("reviews")
    @Expose
    private Reviews reviews;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;

    private List<Picture> pictureList;

    /**
     * No args constructor for use in serialization
     *
     */
    public Product() {
    }

    /**
     *
     * @param tags
     * @param reviews
     * @param catalogProductId
     * @param condition
     * @param currencyId
     * @param categoryId
     * @param seller
     * @param sellerAddress
     * @param installments
     * @param availableQuantity
     * @param buyingMode
     * @param stopTime
     * @param id
     * @param officialStoreId
     * @param shipping
     * @param title
     * @param thumbnail
     * @param price
     * @param soldQuantity
     * @param listingTypeId
     * @param originalPrice
     * @param permalink
     * @param siteId
     * @param address
     * @param attributes
     * @param acceptsMercadopago
     */
    public Product(String id, String siteId, String title, Seller seller, double price, String currencyId, Long availableQuantity, Long soldQuantity, String buyingMode, String listingTypeId, String stopTime, String condition, String permalink, String thumbnail, Boolean acceptsMercadopago, Installments installments, Address address, Shipping shipping, SellerAddress sellerAddress, List<Attribute> attributes, Object originalPrice, String categoryId, Object officialStoreId, Object catalogProductId, Reviews reviews, List<String> tags) {
        super();
        this.id = id;
        this.siteId = siteId;
        this.title = title;
        this.seller = seller;
        this.price = price;
        this.currencyId = currencyId;
        this.availableQuantity = availableQuantity;
        this.soldQuantity = soldQuantity;
        this.buyingMode = buyingMode;
        this.listingTypeId = listingTypeId;
        this.stopTime = stopTime;
        this.condition = condition;
        this.permalink = permalink;
        this.thumbnail = thumbnail;
        this.acceptsMercadopago = acceptsMercadopago;
        this.installments = installments;
        this.address = address;
        this.shipping = shipping;
        this.sellerAddress = sellerAddress;
        this.attributes = attributes;
        this.originalPrice = originalPrice;
        this.categoryId = categoryId;
        this.officialStoreId = officialStoreId;
        this.catalogProductId = catalogProductId;
        this.reviews = reviews;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public Long getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Long availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Long getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Long soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getBuyingMode() {
        return buyingMode;
    }

    public void setBuyingMode(String buyingMode) {
        this.buyingMode = buyingMode;
    }

    public String getListingTypeId() {
        return listingTypeId;
    }

    public void setListingTypeId(String listingTypeId) {
        this.listingTypeId = listingTypeId;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Boolean getAcceptsMercadopago() {
        return acceptsMercadopago;
    }

    public void setAcceptsMercadopago(Boolean acceptsMercadopago) {
        this.acceptsMercadopago = acceptsMercadopago;
    }

    public Installments getInstallments() {
        return installments;
    }

    public void setInstallments(Installments installments) {
        this.installments = installments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public SellerAddress getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(SellerAddress sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Object getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Object originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Object getOfficialStoreId() {
        return officialStoreId;
    }

    public void setOfficialStoreId(Object officialStoreId) {
        this.officialStoreId = officialStoreId;
    }

    public Object getCatalogProductId() {
        return catalogProductId;
    }

    public void setCatalogProductId(Object catalogProductId) {
        this.catalogProductId = catalogProductId;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }
}
