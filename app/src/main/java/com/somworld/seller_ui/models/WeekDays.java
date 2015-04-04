package com.somworld.seller_ui.models;

/**
 * Created by somesh.shrivastava on 03/04/15.
 */
public enum WeekDays {

  SUNDAY("Sunday"),MONDAY("Monday"),TUESDAY("Tuesday"),WEDNESDAY("Wednesday"),THURSDAY("Thursday"),FRIDAY("Friday"), SATURDAY("Saturday");

  private String mDay;

  private WeekDays(String day) {
    mDay = day;
  }


  @Override
  public String toString() {
    return mDay;
  }
}
