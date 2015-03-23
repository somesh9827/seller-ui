package com.somworld.seller_ui.models.dtos;

import android.os.Parcel;

/**
 * Created by somesh.shrivastava on 24/01/15.
 */
public class ShopNameDTO implements RegistrationPageDTO {

    private String shopName;
    private String ownerFirstName;
    private String ownerLastName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(shopName);
        parcel.writeString(ownerFirstName);
        parcel.writeString(ownerLastName);
    }

    public static final Creator<ShopNameDTO> creator = new Creator<ShopNameDTO>() {
        @Override
        public ShopNameDTO createFromParcel(Parcel parcel) {
            ShopNameDTO shopNameDTO = new ShopNameDTO();
            shopNameDTO.setShopName(parcel.readString());
            shopNameDTO.setOwnerFirstName(parcel.readString());
            shopNameDTO.setOwnerLastName(parcel.readString());
            return shopNameDTO;
        }

        @Override
        public ShopNameDTO[] newArray(int size) {
            return new ShopNameDTO[size];
        }
    };
}
