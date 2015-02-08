package com.somworld.seller_ui.models;

/**
 * Created by somesh.shrivastava on 24/01/15.
 */
public class City {

  private int mCityID;
  private String mCityName;
  private State mState;

  public int getmCityID() {
    return mCityID;
  }

  public void setmCityID(int mCityID) {
    this.mCityID = mCityID;
  }

  public String getmCityName() {
    return mCityName;
  }

  public void setmCityName(String mCityName) {
    this.mCityName = mCityName;
  }

  public State getmState() {
    return mState;
  }

  public void setmState(State mState) {
    this.mState = mState;
  }
}
