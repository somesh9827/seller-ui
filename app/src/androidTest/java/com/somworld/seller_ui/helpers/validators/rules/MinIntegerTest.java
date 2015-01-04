package com.somworld.seller_ui.helpers.validators.rules;

import android.test.InstrumentationTestCase;



/**
 * Created by somesh.shrivastava on 03/01/15.
 */

public class MinIntegerTest extends InstrumentationTestCase {

    MinInteger minValueRule;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        minValueRule = new MinInteger(10);
    }

    public void testIsValidThrowExceptionWhenValueIsNotNumber() throws Exception {
        try {
            minValueRule.isValid(null);
            fail("Missing Exception");
        }catch (IllegalArgumentException e){
            assertEquals("Not a number",e.getMessage());
        }
    }

    public void testIsValidReturnTrueWhenMinIsLessThenValue() {
        assertEquals(true, minValueRule.isValid(11));
    }

    public void testIsValidReturnTrueWhenMinIsEqualToValue() {
        assertEquals(true,minValueRule.isValid(10));
    }

    public void testIsValidReturnFalseWhenMinGreaterThanValue() {
        assertEquals(false,minValueRule.isValid(9));
    }

    public void testIsValidFailWhenMinIsMaximum() throws Exception {
        minValueRule = new MinInteger(Integer.MAX_VALUE);
        assertEquals(false, minValueRule.isValid(-11));
    }

    public void testIsValidPassWhenMinIsMinium() throws Exception {
        minValueRule = new MinInteger(Integer.MIN_VALUE);
        assertEquals(true, minValueRule.isValid(-11));
    }
}
