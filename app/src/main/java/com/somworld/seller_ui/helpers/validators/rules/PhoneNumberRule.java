package com.somworld.seller_ui.helpers.validators.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by somesh.shrivastava on 21/02/15.
 */
public class PhoneNumberRule implements RULE<String> {

  @Override
  public boolean isValid(String phone) {
    if(null == phone)
      return false;
    String pattern = "^[1-9][0-9]{9}$";
    Pattern expression = Pattern.compile(pattern);
    Matcher phoneMatcher = expression.matcher(phone);
    return phoneMatcher.find();
  }
}
