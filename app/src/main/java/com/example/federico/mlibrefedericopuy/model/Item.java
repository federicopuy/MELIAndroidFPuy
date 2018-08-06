package com.example.federico.mlibrefedericopuy.model;

import android.location.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


    public class Item {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("site_id")
        @Expose
        private String siteId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("subtitle")
        @Expose
        private Object subtitle;
        @SerializedName("seller_id")
        @Expose
        private Long sellerId;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("official_store_id")
        @Expose
        private Object officialStoreId;
        @SerializedName("price")
        @Expose
        private Double price;
        @SerializedName("base_price")
        @Expose
        private Double basePrice;
        @SerializedName("original_price")
        @Expose
        private Object originalPrice;
        @SerializedName("currency_id")
        @Expose
        private String currencyId;
        @SerializedName("initial_quantity")
        @Expose
        private Long initialQuantity;
        @SerializedName("available_quantity")
        @Expose
        private Long availableQuantity;
        @SerializedName("sold_quantity")
        @Expose
        private Long soldQuantity;
        @SerializedName("sale_terms")
        @Expose
        private List<Object> saleTerms = null;
        @SerializedName("buying_mode")
        @Expose
        private String buyingMode;
        @SerializedName("listing_type_id")
        @Expose
        private String listingTypeId;
        @SerializedName("start_time")
        @Expose
        private String startTime;
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
        @SerializedName("secure_thumbnail")
        @Expose
        private String secureThumbnail;
        @SerializedName("pictures")
        @Expose
        private List<Picture> pictures = null;
        @SerializedName("video_id")
        @Expose
        private Object videoId;
        @SerializedName("descriptions")
        @Expose
        private List<Description> descriptions = null;
        @SerializedName("accepts_mercadopago")
        @Expose
        private Boolean acceptsMercadopago;
        @SerializedName("non_mercado_pago_payment_methods")
        @Expose
        private List<Object> nonMercadoPagoPaymentMethods = null;
        @SerializedName("shipping")
        @Expose
        private Shipping shipping;
        @SerializedName("international_delivery_mode")
        @Expose
        private String internationalDeliveryMode;
        @SerializedName("seller_address")
        @Expose
        private SellerAddress sellerAddress;
        @SerializedName("seller_contact")
        @Expose
        private Object sellerContact;
        @SerializedName("location")
        @Expose
        private Location location;
        @SerializedName("coverage_areas")
        @Expose
        private List<Object> coverageAreas = null;
        @SerializedName("attributes")
        @Expose
        private List<Attribute> attributes = null;
        @SerializedName("warnings")
        @Expose
        private List<Object> warnings = null;
        @SerializedName("listing_source")
        @Expose
        private String listingSource;
        @SerializedName("variations")
        @Expose
        private List<Object> variations = null;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("sub_status")
        @Expose
        private List<Object> subStatus = null;
        @SerializedName("tags")
        @Expose
        private List<String> tags = null;
        @SerializedName("warranty")
        @Expose
        private String warranty;
        @SerializedName("catalog_product_id")
        @Expose
        private Object catalogProductId;
        @SerializedName("domain_id")
        @Expose
        private Object domainId;
        @SerializedName("parent_item_id")
        @Expose
        private Object parentItemId;
        @SerializedName("differential_pricing")
        @Expose
        private Object differentialPricing;
        @SerializedName("deal_ids")
        @Expose
        private List<String> dealIds = null;
        @SerializedName("automatic_relist")
        @Expose
        private Boolean automaticRelist;
        @SerializedName("date_created")
        @Expose
        private String dateCreated;
        @SerializedName("last_updated")
        @Expose
        private String lastUpdated;
        @SerializedName("health")
        @Expose
        private Object health;

        /**
         * No args constructor for use in serialization
         *
         */
        public Item() {
        }

        /**
         *
         * @param location
         * @param descriptions
         * @param sellerAddress
         * @param variations
         * @param startTime
         * @param shipping
         * @param coverageAreas
         * @param domainId
         * @param saleTerms
         * @param condition
         * @param status
         * @param categoryId
         * @param lastUpdated
         * @param sellerContact
         * @param health
         * @param dealIds
         * @param secureThumbnail
         * @param thumbnail
         * @param listingTypeId
         * @param soldQuantity
         * @param price
         * @param nonMercadoPagoPaymentMethods
         * @param permalink
         * @param siteId
         * @param originalPrice
         * @param initialQuantity
         * @param subStatus
         * @param warranty
         * @param internationalDeliveryMode
         * @param acceptsMercadopago
         * @param currencyId
         * @param pictures
         * @param id
         * @param officialStoreId
         * @param title
         * @param videoId
         * @param automaticRelist
         * @param warnings
         * @param differentialPricing
         * @param tags
         * @param catalogProductId
         * @param parentItemId
         * @param stopTime
         * @param buyingMode
         * @param availableQuantity
         * @param sellerId
         * @param listingSource
         * @param subtitle
         * @param dateCreated
         * @param attributes
         * @param basePrice
         */
        public Item(String id, String siteId, String title, Object subtitle, Long sellerId, String categoryId, Object officialStoreId, Double price, Double basePrice, Object originalPrice, String currencyId, Long initialQuantity, Long availableQuantity, Long soldQuantity, List<Object> saleTerms, String buyingMode, String listingTypeId, String startTime, String stopTime, String condition, String permalink, String thumbnail, String secureThumbnail, List<Picture> pictures, Object videoId, List<Description> descriptions, Boolean acceptsMercadopago, List<Object> nonMercadoPagoPaymentMethods, Shipping shipping, String internationalDeliveryMode, SellerAddress sellerAddress, Object sellerContact, Location location,List<Object> coverageAreas, List<Attribute> attributes, List<Object> warnings, String listingSource, List<Object> variations, String status, List<Object> subStatus, List<String> tags, String warranty, Object catalogProductId, Object domainId, Object parentItemId, Object differentialPricing, List<String> dealIds, Boolean automaticRelist, String dateCreated, String lastUpdated, Object health) {
            super();
            this.id = id;
            this.siteId = siteId;
            this.title = title;
            this.subtitle = subtitle;
            this.sellerId = sellerId;
            this.categoryId = categoryId;
            this.officialStoreId = officialStoreId;
            this.price = price;
            this.basePrice = basePrice;
            this.originalPrice = originalPrice;
            this.currencyId = currencyId;
            this.initialQuantity = initialQuantity;
            this.availableQuantity = availableQuantity;
            this.soldQuantity = soldQuantity;
            this.saleTerms = saleTerms;
            this.buyingMode = buyingMode;
            this.listingTypeId = listingTypeId;
            this.startTime = startTime;
            this.stopTime = stopTime;
            this.condition = condition;
            this.permalink = permalink;
            this.thumbnail = thumbnail;
            this.secureThumbnail = secureThumbnail;
            this.pictures = pictures;
            this.videoId = videoId;
            this.descriptions = descriptions;
            this.acceptsMercadopago = acceptsMercadopago;
            this.nonMercadoPagoPaymentMethods = nonMercadoPagoPaymentMethods;
            this.shipping = shipping;
            this.internationalDeliveryMode = internationalDeliveryMode;
            this.sellerAddress = sellerAddress;
            this.sellerContact = sellerContact;
            this.location = location;
            this.coverageAreas = coverageAreas;
            this.attributes = attributes;
            this.warnings = warnings;
            this.listingSource = listingSource;
            this.variations = variations;
            this.status = status;
            this.subStatus = subStatus;
            this.tags = tags;
            this.warranty = warranty;
            this.catalogProductId = catalogProductId;
            this.domainId = domainId;
            this.parentItemId = parentItemId;
            this.differentialPricing = differentialPricing;
            this.dealIds = dealIds;
            this.automaticRelist = automaticRelist;
            this.dateCreated = dateCreated;
            this.lastUpdated = lastUpdated;
            this.health = health;
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

        public Object getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(Object subtitle) {
            this.subtitle = subtitle;
        }

        public Long getSellerId() {
            return sellerId;
        }

        public void setSellerId(Long sellerId) {
            this.sellerId = sellerId;
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

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getBasePrice() {
            return basePrice;
        }

        public void setBasePrice(Double basePrice) {
            this.basePrice = basePrice;
        }

        public Object getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(Object originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(String currencyId) {
            this.currencyId = currencyId;
        }

        public Long getInitialQuantity() {
            return initialQuantity;
        }

        public void setInitialQuantity(Long initialQuantity) {
            this.initialQuantity = initialQuantity;
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

        public List<Object> getSaleTerms() {
            return saleTerms;
        }

        public void setSaleTerms(List<Object> saleTerms) {
            this.saleTerms = saleTerms;
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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
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

        public String getSecureThumbnail() {
            return secureThumbnail;
        }

        public void setSecureThumbnail(String secureThumbnail) {
            this.secureThumbnail = secureThumbnail;
        }

        public List<Picture> getPictures() {
            return pictures;
        }

        public void setPictures(List<Picture> pictures) {
            this.pictures = pictures;
        }

        public Object getVideoId() {
            return videoId;
        }

        public void setVideoId(Object videoId) {
            this.videoId = videoId;
        }

        public List<Description> getDescriptions() {
            return descriptions;
        }

        public void setDescriptions(List<Description> descriptions) {
            this.descriptions = descriptions;
        }

        public Boolean getAcceptsMercadopago() {
            return acceptsMercadopago;
        }

        public void setAcceptsMercadopago(Boolean acceptsMercadopago) {
            this.acceptsMercadopago = acceptsMercadopago;
        }

        public List<Object> getNonMercadoPagoPaymentMethods() {
            return nonMercadoPagoPaymentMethods;
        }

        public void setNonMercadoPagoPaymentMethods(List<Object> nonMercadoPagoPaymentMethods) {
            this.nonMercadoPagoPaymentMethods = nonMercadoPagoPaymentMethods;
        }

        public Shipping getShipping() {
            return shipping;
        }

        public void setShipping(Shipping shipping) {
            this.shipping = shipping;
        }

        public String getInternationalDeliveryMode() {
            return internationalDeliveryMode;
        }

        public void setInternationalDeliveryMode(String internationalDeliveryMode) {
            this.internationalDeliveryMode = internationalDeliveryMode;
        }

        public SellerAddress getSellerAddress() {
            return sellerAddress;
        }

        public void setSellerAddress(SellerAddress sellerAddress) {
            this.sellerAddress = sellerAddress;
        }

        public Object getSellerContact() {
            return sellerContact;
        }

        public void setSellerContact(Object sellerContact) {
            this.sellerContact = sellerContact;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public List<Object> getCoverageAreas() {
            return coverageAreas;
        }

        public void setCoverageAreas(List<Object> coverageAreas) {
            this.coverageAreas = coverageAreas;
        }

        public List<Attribute> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<Attribute> attributes) {
            this.attributes = attributes;
        }

        public List<Object> getWarnings() {
            return warnings;
        }

        public void setWarnings(List<Object> warnings) {
            this.warnings = warnings;
        }

        public String getListingSource() {
            return listingSource;
        }

        public void setListingSource(String listingSource) {
            this.listingSource = listingSource;
        }

        public List<Object> getVariations() {
            return variations;
        }

        public void setVariations(List<Object> variations) {
            this.variations = variations;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Object> getSubStatus() {
            return subStatus;
        }

        public void setSubStatus(List<Object> subStatus) {
            this.subStatus = subStatus;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getWarranty() {
            return warranty;
        }

        public void setWarranty(String warranty) {
            this.warranty = warranty;
        }

        public Object getCatalogProductId() {
            return catalogProductId;
        }

        public void setCatalogProductId(Object catalogProductId) {
            this.catalogProductId = catalogProductId;
        }

        public Object getDomainId() {
            return domainId;
        }

        public void setDomainId(Object domainId) {
            this.domainId = domainId;
        }

        public Object getParentItemId() {
            return parentItemId;
        }

        public void setParentItemId(Object parentItemId) {
            this.parentItemId = parentItemId;
        }

        public Object getDifferentialPricing() {
            return differentialPricing;
        }

        public void setDifferentialPricing(Object differentialPricing) {
            this.differentialPricing = differentialPricing;
        }

        public List<String> getDealIds() {
            return dealIds;
        }

        public void setDealIds(List<String> dealIds) {
            this.dealIds = dealIds;
        }

        public Boolean getAutomaticRelist() {
            return automaticRelist;
        }

        public void setAutomaticRelist(Boolean automaticRelist) {
            this.automaticRelist = automaticRelist;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

        public Object getHealth() {
            return health;
        }

        public void setHealth(Object health) {
            this.health = health;
        }

    }
