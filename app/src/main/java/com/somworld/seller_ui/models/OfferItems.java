package com.somworld.seller_ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.somworld.seller_ui.helpers.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by somesh.shrivastava on 13/12/14.
 */
public class OfferItems implements Parcelable{
    private int id;
    private String product;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date startValidTime;
    private Date endValidTime;
    private String discount;
    private boolean isActive;


    public Date getStartValidTime() {
        return startValidTime;
    }

    public void setStartValidTime(Date startValidTime) {
        this.startValidTime = startValidTime;
    }

    public Date getEndValidTime() {
        return endValidTime;
    }

    public void setEndValidTime(Date endValidTime) {
        this.endValidTime = endValidTime;
    }


    public OfferItems(OfferItems offer) {
        this.setId(offer.id);
        this.setProduct(offer.getProduct());
        this.setDiscount(offer.getDiscount());
        this.setActive(offer.isActive());
        this.setDescription(offer.getDescription());
        this.setStartDate(offer.getStartDate());
        this.setEndDate(offer.getEndDate());
        this.setStartValidTime(offer.getStartValidTime());
        this.setEndValidTime(offer.getEndValidTime());
    }



    public OfferItems() {
        id = Utils.INVALID_ID;
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

    public Date getStartDate() {
        return new Date(startDate.getTime());
    }

    public void setStartDate(Date startDate) {
        this.startDate = new Date(startDate.getTime());
    }

    public void setStartDate(String dateString, DateFormat dateFormat) throws ParseException,NullPointerException{
        if(dateFormat == null) throw new NullPointerException("Date Format should not be null");
        this.startDate = dateFormat.parse(dateString);
    }

    public Date getEndDate() {
        return new Date(endDate.getTime());
    }

    public void setEndDate(Date endDate) {
        this.endDate = new Date(endDate.getTime());
    }

    public void setEndDate(String dateString, DateFormat dateFormat) throws ParseException,NullPointerException{
        if(dateFormat == null) throw new NullPointerException("Date Format should not be null");
        this.endDate = dateFormat.parse(dateString);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(Boolean.toString(isActive));
        parcel.writeLong(endDate.getTime());
        parcel.writeLong(startDate.getTime());
        parcel.writeLong(startValidTime.getTime());
        parcel.writeLong(endValidTime.getTime());
        parcel.writeString(description);
        parcel.writeString(discount);
        parcel.writeString(product);
    }

    public static final Creator<OfferItems> CREATOR = new Creator<OfferItems>() {
        @Override
        public OfferItems createFromParcel(Parcel parcel) {
            OfferItems mOfferItem = new OfferItems();
            mOfferItem.setId(parcel.readInt());
            mOfferItem.setActive(Boolean.parseBoolean(parcel.readString()));
            mOfferItem.setEndDate(new Date(parcel.readLong()));
            mOfferItem.setStartDate(new Date(parcel.readLong()));
            mOfferItem.setStartValidTime(new Date(parcel.readLong()));
            mOfferItem.setEndValidTime(new Date(parcel.readLong()));
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
