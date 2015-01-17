package com.somworld.seller_ui.helpers;

import android.provider.CalendarContract;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by somesh.shrivastava on 17/12/14.
 */
public class Utils {

    public static long ONE_DAY_INTERVAL = 1000 * 60 * 60 * 24;

    public static long FIVE_MINUTE_INTERVAL = 1000 * 60 * 5;

    public static final int START_DATE_CONTEXT = 0;
    public static final int END_DATE_CONTEXT = 1;

    public static final int INVALID_ID = -1;

    public static DateFormat getTimeFormat() {

        DateFormat df =  DateFormat.getTimeInstance();
        return df;
    }

    public static DateFormat getDateTimeFormat() {
        DateFormat df =  DateFormat.getDateTimeInstance();
        return df;
    }

    public static DateFormat getDateFormat() {
        DateFormat df =  DateFormat.getDateInstance();
        return df;
    }

    public static Date getDateFromYEAR_MONTH_DAY(int year, int month, int day, int hours, int mins, int secs) {
        Calendar c = Calendar.getInstance();
        c.set(year,month,day,hours,mins,secs);
        return c.getTime();
    }

    public static String parsedDate(DateFormat format,Date date) {
        if(format != null && date != null)
            return format.format(date);
        else
            return "";

    }

    public static Date getMinAllowedStartTime(final Date currentTime) {
        if(currentTime == null) throw new IllegalArgumentException("Argument should not be null");
        return new Date( currentTime.getTime() -  FIVE_MINUTE_INTERVAL);
    }

    public static Date getDateFromTime(Date date) {
        if(null == date) throw new IllegalArgumentException("Argument should not be null");
        DateFormat dateFormat = getDateFormat();
        try {
             return dateFormat.parse(dateFormat.format(date));
        } catch (ParseException e) {
            //e.printStackTrace(
        }
        return date;
    }

    public static Date getCurrentDate() {
        return getDateFromTime(new Date());
    }

}
