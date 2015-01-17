package com.somworld.seller_ui.helpers;

import junit.framework.TestCase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by somesh.shrivastava on 17/01/15.
 */
public class UtilTest extends TestCase {

    public void testGetDateFromTime() {
        Calendar c = Calendar.getInstance();
        c.set(2015,Calendar.JANUARY,17,10,12,13);
        Date d =  c.getTime();
        Date actual = Utils.getDateFromTime(d);
        String expected = "Sat Jan 17 00:12:00 GMT+05:30 2015";
        assertEquals(expected, actual.toString());
    }

}
