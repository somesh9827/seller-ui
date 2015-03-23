package com.somworld.seller_ui.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by somesh.shrivastava on 12/01/15.
 */
@Accessors(prefix = "m")
@Getter
@Setter
public class Address {

    private int mId;

    private String mDoorNumber;

    private String mAddress1;

    private String mAddress2;

    private String mArea;

    private String mCity;

    private String mState;

    private String mPinCode;

    public Address(){
        mId = -1;
        mDoorNumber = mAddress1 = mAddress2 = mArea = mCity = mState = mPinCode = "";
    }

    Address(int _id,String doorNumber,String address1,String address2,String area,String city, String state,String pincode) {
        mId = _id;
        mDoorNumber = doorNumber;
        mAddress1 = address1;
        mAddress2 = address2;
        mArea = area;
        mCity = city;
        mState = state;
        mPinCode = pincode;
    }

    Address(Address oldAddress){
        mId = oldAddress.getId();
        mDoorNumber = oldAddress.getDoorNumber();
        mAddress1 = oldAddress.getAddress1();
        mAddress2 = oldAddress.getAddress2();
        mArea = oldAddress.getArea();
        mCity = oldAddress.getCity();
        mPinCode = oldAddress.getPinCode();
        mState = oldAddress.getState();
    }

    @Override
    public String toString() {
        StringBuilder concatAddress = new StringBuilder();
        String delimiter = ", ";
        if(mDoorNumber != null)
            concatAddress.append(mDoorNumber).append(delimiter);
        if(mAddress1 != null)
            concatAddress.append(mAddress1).append(delimiter);
        if(mAddress2 != null)
            concatAddress.append(mAddress2).append(delimiter);
        if(mArea != null)
            concatAddress.append(mArea).append(delimiter);
        if(mCity != null)
            concatAddress.append(mCity).append(delimiter);
        if(mState != null)
            concatAddress.append(mState).append(delimiter);
        if(mPinCode != null)
            concatAddress.append(mPinCode);

        return concatAddress.toString();
    }
}
