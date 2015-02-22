package com.somworld.seller_ui.helpers.validators.rules;

/**
 * Created by somesh.shrivastava on 21/02/15.
 */
public class EqualStringRule implements RULE<String> {
  private String currentString;

  public EqualStringRule(String currentString) {
    this.currentString = currentString;
  }

  @Override
  public boolean isValid(String testString) {
    return currentString.equals(testString);
  }
}
