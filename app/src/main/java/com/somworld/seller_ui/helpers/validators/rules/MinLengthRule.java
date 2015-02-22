package com.somworld.seller_ui.helpers.validators.rules;

/**
 * Created by somesh.shrivastava on 21/02/15.
 */
public class MinLengthRule implements RULE<String> {
  private int length = 1;

  public MinLengthRule(int length) {
    this.length = length;
  }

  @Override
  public boolean isValid(String string) {
    if(null != string && string.length() >= length)
      return true;
    return false;
  }
}
