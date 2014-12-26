package com.somworld.seller_ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by somesh.shrivastava on 13/12/14.
 */
public class OfferItems implements Parcelable{

    private String product;
    private String description;
    private Date startTime;
    private Date endTime;
    private String discount;
    private boolean isActive;

    public OfferItems(OfferItems offer) {
        this.setProduct(offer.getProduct());
        this.setDiscount(offer.getDiscount());
        this.setActive(offer.isActive());
        this.setDescription(offer.getDescription());
        this.setStartTime(offer.getStartTime());
        this.setEndTime(offer.getEndTime());
    }



    public OfferItems() {
    }

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
        this.endTime = new Date(endTime.getTime());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Boolean.toString(isActive));
        parcel.writeLong(endTime.getTime());
        parcel.writeLong(startTime.getTime());
        parcel.writeString(description);
        parcel.writeString(discount);
        parcel.writeString(product);
    }

    public static final Creator<OfferItems> CREATOR = new Creator<OfferItems>() {
        @Override
        public OfferItems createFromParcel(Parcel parcel) {
            OfferItems mOfferItem = new OfferItems();
            mOfferItem.setActive(Boolean.parseBoolean(parcel.readString()));
            mOfferItem.setEndTime(new Date(parcel.readLong()));
            mOfferItem.setStartTime(new Date(parcel.readLong()));
            mOfferItem.setDescription(parcel.readString());
            mOfferItem.setDiscount(parcel.readString());
            mOfferItem.setProduct(parcel.readString());
            return mOfferItem;
        }

        @Override
        public OfferItems[] newArray(int size) {
            return new OfferItems[size];
        }
    };
}
