package com.somworld.seller_ui.models;

/**
 * Created by somesh.shrivastava on 12/01/15.
 */

public class Shop {

    private int mId;

    private String mShopName;

    private String mImageUrl = ""; //for future use

    private Address mShopAddress;

    private Location mLocation;

    public Shop(Shop shop) {
        mId = shop.mId;
        mShopName = shop.mShopName;
        mImageUrl = shop.mImageUrl;
        setShopAddress(shop.getShopAddress());
        setLocation(mLocation);
    }

    public Shop() {
        mId = -1;
        mShopName = "";
        mImageUrl = "";
        mShopAddress = new Address();
        mLocation = new Location();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        this.mShopName = shopName;
    }

    public Address getShopAddress() {
        return mShopAddress;
    }

    public void setShopAddress(Address shopAddress) {

        this.mShopAddress = new Address(shopAddress);
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        this.mLocation = new Location(location);
    }




}
