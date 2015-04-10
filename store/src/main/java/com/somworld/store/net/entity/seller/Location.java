package com.somworld.store.net.entity.seller;

import com.somworld.vollywraper.net.entity.IEntity;

/**
 * Created by somesh.shrivastava on 10/04/15.
 */
public class Location implements IEntity {
  private String latitiude;
  private String Longtitude;

  public Location(String latitiude, String longtitude) {
    this.latitiude = latitiude;
    Longtitude = longtitude;
  }

  @Override
  public String toString() {
    return "Location{" +
           "latitiude='" + latitiude + '\'' +
           ", Longtitude='" + Longtitude + '\'' +
           '}';
  }
}
