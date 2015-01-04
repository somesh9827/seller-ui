package com.somworld.seller_ui.helpers.validators.rules;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by somesh.shrivastava on 05/01/15.
 */
public class MinDateTest extends TestCase {

    public void testIsValidPassWhenBothDateAreEqual(){
        Calendar c = Calendar.getInstance();
        c.set(2014,Calendar.JANUARY,10);
        Date minDate = c.getTime();
        MinDate minDateRule = new MinDate(minDate);
        assertEquals(true, minDateRule.isValid(minDate));
    }

    public void testIsValidPassWhenMinDateIsLessThenCurrent(){
        Calendar c = Calendar.getInstance();
        c.set(2014,Calendar.JANUARY,10);
        Date minDate = c.getTime();
        MinDate minDateRule = new MinDate(minDate);
        long day_interval = 1000*60*60*24;
        assertEquals(true,minDateRule.isValid(new Date(minDate.getTime()+day_interval)));
    }

    public void testIsValidFailWhenWrongArgumentIsSet(){
        Calendar c = Calendar.getInstance();
        c.set(2014,Calendar.JANUARY,10);
        Date minDate = c.getTime();
        MinDate minDateRule = new MinDate(minDate);
        try {
          minDateRule.isValid(null);
            fail("Should throw Exception");
        }catch(IllegalArgumentException e){
            assertEquals(true,true);
        }
    }

    public void testIsValidFailWhenMinDateIsGreatersThenCurrent(){
        Calendar c = Calendar.getInstance();
        c.set(2014,Calendar.JANUARY,10);
        Date minDate = c.getTime();
        MinDate minDateRule = new MinDate(minDate);
        long day_interval = 1000*60*60*24;
        assertEquals(false,minDateRule.isValid(new Date(minDate.getTime()-day_interval)));
    }


}
