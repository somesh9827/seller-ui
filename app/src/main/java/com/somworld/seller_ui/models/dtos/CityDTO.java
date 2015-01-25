package com.somworld.seller_ui.models.dtos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by somesh.shrivastava on 24/01/15.
 */
public class CityDTO implements Parcelable {

  public static final Creator<CityDTO> creator = new Creator<CityDTO>() {
    @Override
    public CityDTO createFromParcel(Parcel parcel) {
      CityDTO cityDTO = new CityDTO();
      cityDTO.setCityID(parcel.readInt());
      cityDTO.setCityName(parcel.readString());
      cityDTO.setStateID(parcel.readInt());
      return cityDTO;
    }

    @Override
    public CityDTO[] newArray(int i) {
      return new CityDTO[i];
    }
  };
  private int mCityID;
  private String mCityName;
  private int mStateID;

  public CityDTO() {
  }

  public CityDTO(CityDTO cityDTO) {
    mCityID = cityDTO.mCityID;
    mCityName = cityDTO.mCityName;
    mStateID = cityDTO.mStateID;
  }

  public int getCityID() {
    return mCityID;
  }

  public void setCityID(int mCityID) {
    this.mCityID = mCityID;
  }

  public String getCityName() {
    return mCityName;
  }

  public void setCityName(String mCityName) {
    this.mCityName = mCityName;
  }

  public int getStateID() {
    return mStateID;
  }

  public void setStateID(int mStateID) {
    this.mStateID = mStateID;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(mCityID);
    parcel.writeString(mCityName);
    parcel.writeInt(mStateID);
  }
}
