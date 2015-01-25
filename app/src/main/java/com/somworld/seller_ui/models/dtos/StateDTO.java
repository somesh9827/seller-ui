package com.somworld.seller_ui.models.dtos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by somesh.shrivastava on 24/01/15.
 */
public class StateDTO implements Parcelable{

  private int mStateID;

  private String mName;

  public int getStateID() {
    return mStateID;
  }

  public void setStateID(int stateID) {
    mStateID = stateID;
  }

  public String getName() {
    return mName;
  }

  public void setName(String mStateName) {
    this.mName = mStateName;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(mStateID);
    parcel.writeString(mName);
  }

  private static Creator<StateDTO> creator = new Creator<StateDTO>() {
    @Override
    public StateDTO createFromParcel(Parcel parcel) {
      StateDTO stateDTO = new StateDTO();
      stateDTO.setStateID(parcel.readInt());
      stateDTO.setName(parcel.readString());
      return stateDTO;
    }

    @Override
    public StateDTO[] newArray(int i) {
      return new StateDTO[0];
    }
  };
}
