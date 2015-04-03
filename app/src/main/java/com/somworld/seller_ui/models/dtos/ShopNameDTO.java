package com.somworld.seller_ui.models.dtos;

import android.os.Parcel;

/**
 * Created by somesh.shrivastava on 24/01/15.
 */
public class ShopNameDTO implements RegistrationPageDTO {

    private String shopName;

    private String mShopOpeningTime;
    private String mShopClosingTime;
    private String mShopClosingDays;

  public String getShopOpeningTime() {
    return mShopOpeningTime;
  }

  public void setShopOpeningTime(String shopOpeningTime) {
    mShopOpeningTime = shopOpeningTime;
  }

  public String getShopClosingTime() {
    return mShopClosingTime;
  }

  public void setShopClosingTime(String mShopClosingTime) {
    mShopClosingTime = mShopClosingTime;
  }

  public String getShopClosingDays() {
    return mShopClosingDays;
  }

  public void setShopClosingDays(String shopClosingDays) {
    mShopClosingDays = shopClosingDays;
  }

  public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(shopName);
        parcel.writeString(mShopOpeningTime);
        parcel.writeString(mShopClosingTime);
        parcel.writeString(mShopClosingDays);
    }

    public static final Creator<ShopNameDTO> creator = new Creator<ShopNameDTO>() {
        @Override
        public ShopNameDTO createFromParcel(Parcel parcel) {
            ShopNameDTO shopNameDTO = new ShopNameDTO();
            shopNameDTO.setShopName(parcel.readString());
            shopNameDTO.setShopOpeningTime(parcel.readString());
            shopNameDTO.setShopClosingTime(parcel.readString());
            shopNameDTO.setShopClosingDays(parcel.readString());
            return shopNameDTO;
        }

        @Override
        public ShopNameDTO[] newArray(int size) {
            return new ShopNameDTO[size];
        }
    };
}
