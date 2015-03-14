package com.somworld.seller_ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.somworld.seller_ui.common.locationHelper.LocationUtils;

/**
 * Created by somesh.shrivastava on 12/01/15.
 */
public class Location implements Parcelable {

  public static final String PRECISION_FORMAT = "%.8f";

  public static final String LOCATION_FORMAT = "Latitude='" + PRECISION_FORMAT + '\'' +
                                               ", Longtitude='"+ PRECISION_FORMAT + '\'';
  private Double mlatitude;
  private Double mlongitude;

  public Double getLatitude() {
    return mlatitude;
  }

  public void setLatitude(Double latitude) {
    this.mlatitude = latitude;
  }

  public Double getLongitude() {
    return mlongitude;
  }

  public void setLongitude(Double longitude) {
    this.mlongitude = longitude;
  }

  @Override
  public String toString() {
    return String.format(LOCATION_FORMAT,mlatitude,mlongitude);
  }

   public Location(Location location) {
     if (location == null){
       mlatitude = null;
       mlongitude = null;
     }

     else {
       mlatitude = location.getLatitude();
       mlongitude = location.getLongitude();
     }
   }

    public Location() {
      mlatitude = null;
      mlongitude = null;
    }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(String.format(PRECISION_FORMAT,mlatitude));
    parcel.writeString(String.format(PRECISION_FORMAT,mlongitude));
  }

  public static Creator<Location> creator =   new Creator<Location>() {
    @Override
    public Location createFromParcel(Parcel parcel) {
      Location location = new Location();
      location.setLatitude(Double.parseDouble(parcel.readString()));
      location.setLongitude(Double.parseDouble(parcel.readString()));
      return location;
    }

    @Override
    public Location[] newArray(int i) {
      return new Location[0];
    }
  };
}
