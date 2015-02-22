package com.somworld.seller_ui.helpers.validators.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by somesh.shrivastava on 23/02/15.
 */
public class PincodeRule implements RULE<String> {

  @Override
  public boolean isValid(String testString) {
    String pattern = "^[1-9][0-9]{5}$";
    Pattern expression = Pattern.compile(pattern);
    Matcher phoneMatcher = expression.matcher(testString);
    return phoneMatcher.find();

  }
}
