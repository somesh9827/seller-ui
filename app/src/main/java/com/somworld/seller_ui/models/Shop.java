package com.somworld.seller_ui.models;

import java.util.Date;

/**
 * Created by somesh.shrivastava on 12/01/15.
 */

public class Shop {

    private int mId;

    private String mShopName;

    private String mImageUrl = ""; //for future use

    private Address mShopAddress;

    private Location mLocation;

    private Date mOpeningTime;

    private Date mClosingTime;

    private WeekDays[] mClosingDays;

  public WeekDays[] getClosingDays() {
    return mClosingDays;
  }

  public void setClosingDays(WeekDays[] closingDays) {
    if(closingDays == null){
      mClosingDays = null;
      return;
    }
    int i = 0;
    for(WeekDays day : closingDays) {
      mClosingDays[i++] = day;
    }
  }

  public Date getClosingTime() {
    return mClosingTime;
  }

  public void setClosingTime(Date closingTime) {
    mClosingTime = new Date(closingTime.getTime());
  }

  public Date getOpeningTime() {
    return mOpeningTime;
  }

  public void setOpeningTime(Date openingTime) {
    mOpeningTime = new Date(openingTime.getTime());
  }



    public Shop(Shop shop) {
        mId = shop.mId;
        mShopName = shop.mShopName;
        mImageUrl = shop.mImageUrl;
        setShopAddress(shop.getShopAddress());
        setLocation(mLocation);
        setOpeningTime(shop.getOpeningTime());
        setClosingTime(shop.getClosingTime());
        setClosingDays(shop.getClosingDays());

    }

    public Shop() {
        mId = -1;
        mShopName = "";
        mImageUrl = "";
        mShopAddress = new Address();
        mLocation = new Location();
        mClosingDays  = new WeekDays[7];
        mClosingTime = null;
        mOpeningTime = null;
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
