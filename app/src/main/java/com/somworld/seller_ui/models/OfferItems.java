package com.somworld.seller_ui.models;

import java.util.Date;

/**
 * Created by somesh.shrivastava on 13/12/14.
 */
public class OfferItems {

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return new Date(startTime.getTime());
    }

    public void setStartTime(Date startTime) {
        this.startTime = new Date(startTime.getTime());
    }

    public Date getEndTime() {
        return new Date(endTime.getTime());
    }

    public void setEndTime(Date endTime) {
        this.endTime = new Date(endTime.getTime());;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public OfferItems(OfferItems offer) {
        this.setProduct(offer.getProduct());
        this.setDiscount(offer.getDiscount());
        this.setActive(offer.isActive());
        this.setDescription(offer.getDescription());
        this.setStartTime(offer.getStartTime());
        this.setEndTime(offer.getEndTime());
    }

    public OfferItems() {}

    private String product;


    private String description;


    private Date startTime;


    private Date endTime;


    private String discount;


    private boolean isActive;

}
