package com.somworld.seller_ui.models;

/**
 * Created by somesh.shrivastava on 12/01/15.
 */
public class Location {

    private String mLatitude;

    private String mLongtitude;

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getLongtitude() {
        return mLongtitude;
    }

    public void setLongtitude(String longtitude) {
        mLongtitude = longtitude;
    }

    @Override
    public String toString() {
        return
                "Latitude='" + mLatitude + '\'' +
                ", Longtitude='" + mLongtitude + '\'';
    }

    public Location(Location location) {
        mLatitude = location.getLatitude();
        mLongtitude = location.getLongtitude();
    }

    public Location() {
        mLatitude = null;
        mLongtitude = null;
    }
}
