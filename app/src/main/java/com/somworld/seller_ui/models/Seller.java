package com.somworld.seller_ui.models;

/**
 * Created by somesh.shrivastava on 12/01/15.
 */
public class Seller {

    private int id;

    private String mDisplayId;

    private String mFirstname;

    private String mLastName;

    private String mMiddleName;

    private String mPhoneNumber;

    private Shop mShop;

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayId() {
        return mDisplayId;
    }

    public void setDisplayId(String displayId) {
        this.mDisplayId = displayId;
    }

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String mFirstname) {
        this.mFirstname = mFirstname;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getMiddleName() {
        return mMiddleName;
    }

    public void setMiddleName(String mMiddleName) {
        this.mMiddleName = mMiddleName;
    }

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop mShop) {
        this.mShop = mShop;
    }

    public String getName() {

        return mFirstname + " " + mMiddleName + " " + mLastName;
    }


}
