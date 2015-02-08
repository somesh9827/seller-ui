package com.somworld.seller_ui.models;

/**
 * Created by somesh.shrivastava on 12/01/15.
 */
public class Address {

    private int mId;

    private String mDoorNumber;

    private String mAddress1;

    private String mAddress2;

    private String mArea;

    private String mCity;

    private String mState;

    private String mPincode;

    public int getId() {
        return mId;
    }

    public String getDoorNumber() {
        return mDoorNumber;
    }

    public String getAddress1() {
        return mAddress1;
    }

    public String getAddress2() {
        return mAddress2;
    }

    public String getArea() {
        return mArea;
    }

    public String getmCity() {
        return mCity;
    }

    public String getState() {
        return mState;
    }

    public String getPincode() {
        return mPincode;
    }



    Address(){
        mId = -1;
        mDoorNumber = mAddress1 = mAddress2 = mArea = mCity = mState = mPincode = "";
    }

    Address(int _id,String doorNumber,String address1,String address2,String area,String city, String state,String pincode) {
        mId = _id;
        mDoorNumber = doorNumber;
        mAddress1 = address1;
        mAddress2 = address2;
        mArea = area;
        mCity = city;
        mState = state;
        mPincode = pincode;
    }

    Address(Address oldAddress){
        mId = oldAddress.getId();
        mDoorNumber = oldAddress.getDoorNumber();
        mAddress1 = oldAddress.getAddress1();
        mAddress2 = oldAddress.getAddress2();
        mArea = oldAddress.getArea();
        mCity = oldAddress.getmCity();
        mPincode = oldAddress.getPincode();
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
        if(mPincode != null)
            concatAddress.append(mPincode);

        return concatAddress.toString();
    }
}
