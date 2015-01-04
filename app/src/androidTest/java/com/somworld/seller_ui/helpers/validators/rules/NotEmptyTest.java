package com.somworld.seller_ui.helpers.validators.rules;


import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Created by somesh.shrivastava on 03/01/15.
 */
public class NotEmptyTest extends TestCase {

    public void testIsValidReturnTrue() throws Exception{
        NotEmpty rule = new NotEmpty();
        String s = "Test";
        assertEquals(true,rule.isValid(s));
    }

    public void testIsValidReturnFalseWhenStringIsNull() {
        NotEmpty rule = new NotEmpty();
        String s = null;
        assertEquals(false,rule.isValid(s));
    }

    public void testIsValidReturnFalseWhenStringIsEmpty() {
        NotEmpty rule = new NotEmpty();
        String s = "";
        assertEquals(false,rule.isValid(s));
    }

    public void testIsValidWithNonStringParameter(){
        NotEmpty rule = new NotEmpty();
        int i = 10;
        assertEquals(true,rule.isValid(i));
    }
}
