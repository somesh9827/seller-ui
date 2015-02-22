package com.somworld.seller_ui.common.locationHelper;

/**
 * Created by somesh.shrivastava on 08/02/15.
 */
public class LatLng {
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
    return String.format(LocationUtils.LAT_LNG_FORMAT,mlatitude,mlongitude);
  }
}
