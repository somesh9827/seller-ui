package com.somworld.seller_ui.helpers.validators.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by somesh.shrivastava on 22/02/15.
 */
public class TypeRequiredRule implements RULE<String> {
  private TYPE type;

  public TypeRequiredRule(TYPE type) {
    this.type = type;
  }

  @Override
  public boolean isValid(String testString) {
    if(type == null || testString == null) return false;
    String pattern = type.getPattern();
    Pattern expression = Pattern.compile(pattern);
    Matcher phoneMatcher = expression.matcher(testString);
    return phoneMatcher.find();
  }

  public static enum TYPE {
    NUMBER("^[0-9]+$"),
    ALPHABET("^[a-zA-Z]+$"),
    SMALL_CASE("^[a-z]+$"),
    UPPER_CASE("^[A-Z]+$");

    private String pattern;

    TYPE(String pattern) {
      this.pattern = pattern;
    }

    public String getPattern() {return pattern;}
  };
}
