package com.somworld.seller_ui.models.dtos;

import android.os.Parcel;

import com.somworld.seller_ui.helpers.ApplicationConstants;
import com.somworld.seller_ui.helpers.Utils;

/**
 * Created by somesh.shrivastava on 25/01/15.
 */
public class AddressDTO implements RegistrationDTO {

  String mAddressLine1, mAddressLine2, mArea, mCity, mState, mPincode;

  public AddressDTO() {
    mAddressLine1 = mAddressLine2 = mArea = mCity = mState = mPincode = ApplicationConstants.EMPTY_STRING;
  }

  public String getAddressLine1() {
    return mAddressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.mAddressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return mAddressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.mAddressLine2 = addressLine2;
  }

  public String getArea() {
    return mArea;
  }

  public void setArea(String area) {
    this.mArea = area;
  }

  public String getCity() {
    return mCity;
  }

  public void setCity(String city) {
    this.mCity = city;
  }

  public String getState() {
    return mState;
  }

  public void setState(String state) {
    this.mState = state;
  }

  public String getPincode() {
    return mPincode;
  }

  public void setPincode(String pincode) {
    this.mPincode = pincode;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(mAddressLine1);
    parcel.writeString(mAddressLine2);
    parcel.writeString(mCity);
    parcel.writeString(mArea);
    parcel.writeString(mState);
    parcel.writeString(mPincode);
  }

  public static Creator<AddressDTO> creator = new Creator<AddressDTO>() {
    @Override
    public AddressDTO createFromParcel(Parcel parcel) {
      AddressDTO addressDTO = new AddressDTO();
      addressDTO.setAddressLine1(parcel.readString());
      addressDTO.setAddressLine2(parcel.readString());
      addressDTO.setArea(parcel.readString());
      addressDTO.setCity(parcel.readString());
      addressDTO.setState(parcel.readString());
      addressDTO.setPincode(parcel.readString());
      return addressDTO;
    }

    @Override
    public AddressDTO[] newArray(int i) {
      return new AddressDTO[i];
    }
  };
}
