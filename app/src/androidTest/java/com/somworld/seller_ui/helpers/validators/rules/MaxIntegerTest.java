package com.somworld.seller_ui.helpers.validators.rules;

import android.test.InstrumentationTestCase;

import junit.framework.TestCase;

/**
 * Created by somesh.shrivastava on 03/01/15.
 */
public class MaxIntegerTest extends TestCase {
    MaxInteger maxIntegeRule;
    public void setUp() throws Exception {
        maxIntegeRule = new MaxInteger(10);
    }

    public void testIsValidThrowExceptionWhenValueIsNotNumber() throws Exception {
        try {
            maxIntegeRule.isValid(null);
            fail("Missing Exception");
        }catch (IllegalArgumentException e){
            assertEquals("Not a number",e.getMessage());
        }
    }

    public void testIsValidPassWhenValueIsLessThanMax() throws Exception {
        assertEquals(true, maxIntegeRule.isValid(9));
    }

    public void testIsValidPassWhenValueEqualToMax() throws Exception {
        assertEquals(true, maxIntegeRule.isValid(10));
    }

    public void testIsValidFailWhenValueIsGreaterMax() throws Exception {
        assertEquals(false, maxIntegeRule.isValid(11));
    }

    public void testIsValidPassWhenMaxIsINF() throws Exception {
        maxIntegeRule = new MaxInteger(Integer.MAX_VALUE);
        assertEquals(true, maxIntegeRule.isValid(11));
    }

    public void testIsValidFailWhenMaxIsINF() throws Exception {
        maxIntegeRule = new MaxInteger(Integer.MIN_VALUE);
        assertEquals(false, maxIntegeRule.isValid(11));

    }

}
