package com.somworld.seller_ui.models;

/**
 * Created by somesh.shrivastava on 03/04/15.
 */
public enum WeekDays {

  SUNDAY("sunday"),MONDAY("monday"),TUESDAY("tuesday"),WEDNESDAY("wednesday"),THURSDAY("thursday"),FRIDAY("friday"), SATURDAY("saturday");

  private String mDay;

  private WeekDays(String day) {
    mDay = day;
  }


  @Override
  public String toString() {
    return mDay;
  }
}
