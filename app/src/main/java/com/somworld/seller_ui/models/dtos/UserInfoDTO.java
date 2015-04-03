package com.somworld.seller_ui.models.dtos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by somesh.shrivastava on 03/04/15.
 */

public class UserInfoDTO  implements RegistrationPageDTO{

  private String mFirstName;
  private String mLastName;



  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(mFirstName);
    parcel.writeString(mLastName);
  }

  public static final Parcelable.Creator<UserInfoDTO>
      creator = new Parcelable.Creator<UserInfoDTO>() {
    @Override
    public UserInfoDTO createFromParcel(Parcel parcel) {
      UserInfoDTO userInfoDTO = new UserInfoDTO();
      userInfoDTO.setFirstName(parcel.readString());
      userInfoDTO.setLastName(parcel.readString());
      return userInfoDTO;
    }

    @Override
    public UserInfoDTO[] newArray(int size) {
      return new UserInfoDTO[size];
    }
  };


  public String getFirstName() {
    return mFirstName;
  }

  public void setFirstName(String firstName) {
    mFirstName = firstName;
  }

  public String getLastName() {
    return mLastName;
  }

  public void setLastName(String lastName) {
    mLastName = lastName;
  }
}
