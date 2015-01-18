package com.somworld.seller_ui.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 17/12/14.
 */
public class Utils {

    public static long ONE_DAY_INTERVAL = 1000 * 60 * 60 * 24;

    public static long FIVE_MINUTE_INTERVAL = 1000 * 60 * 5;

    public static long ONE_HOUR_INTERVAL = 1000 * 60 * 60;

    public static final String validTimeDelimiter = " To ";

    public static final int START_DATE_CONTEXT = 0;

    public static final int END_DATE_CONTEXT = 1;

    public static final int INVALID_ID = -1;

    public static DateFormat getTimeFormat() {

        DateFormat df =  DateFormat.getTimeInstance(DateFormat.SHORT);
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
    public static Date getdefaultTime() {
        Calendar c = Calendar.getInstance();
        c.set(0,Calendar.JANUARY,0,9,0,0);
        return c.getTime();
    }

    public static String validTimeToValidTimeString(Date fromTime, Date toTime) {

        return getTimeFormat().format(fromTime) + validTimeDelimiter +  getTimeFormat().format(toTime);
    }

    public static Map<String,Date> validTimeStringToValidTime(String timeString) throws ParseException {
        String dateString[] = timeString.split(validTimeDelimiter);
        Date fromDate = getTimeFormat().parse(dateString[0]);
        Date toDate = getTimeFormat().parse(dateString[1]);
        Map<String,Date> validDates = new HashMap<String, Date>();
        validDates.put("fromTime",fromDate);
        validDates.put("toTime",toDate);
        return validDates;
    }

}
