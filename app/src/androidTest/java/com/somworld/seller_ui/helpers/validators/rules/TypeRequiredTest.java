package com.somworld.seller_ui.helpers.validators.rules;

import junit.framework.TestCase;

/**
 * Created by somesh.shrivastava on 22/02/15.
 */
public class TypeRequiredTest extends TestCase {

  public void testNumberShouldPass() {
    String number = "1098765";
    TypeRequiredRule numberRule = new TypeRequiredRule(TypeRequiredRule.TYPE.NUMBER);
    assertEquals(true,numberRule.isValid(number));
  }

  public void testNumberShouldFail() {
    String number = "1098765a";
    TypeRequiredRule numberRule = new TypeRequiredRule(TypeRequiredRule.TYPE.NUMBER);
    assertEquals(false,numberRule.isValid(number));
  }

  public void testLowerCaseShouldPass() {
    String number = "abhf";
    TypeRequiredRule numberRule = new TypeRequiredRule(TypeRequiredRule.TYPE.SMALL_CASE);
    assertEquals(true,numberRule.isValid(number));
  }

  public void testLowerCaseShouldFail() {
    String number = "asdL";
    TypeRequiredRule numberRule = new TypeRequiredRule(TypeRequiredRule.TYPE.SMALL_CASE);
    assertEquals(false,numberRule.isValid(number));
  }

  public void testUpperCaseShouldPass() {
    String number = "ASDF";
    TypeRequiredRule numberRule = new TypeRequiredRule(TypeRequiredRule.TYPE.UPPER_CASE);
    assertEquals(true,numberRule.isValid(number));
  }

  public void testUpperCaseShouldFail() {
    String number = "ASJNa";
    TypeRequiredRule numberRule = new TypeRequiredRule(TypeRequiredRule.TYPE.UPPER_CASE);
    assertEquals(false,numberRule.isValid(number));
  }


}
