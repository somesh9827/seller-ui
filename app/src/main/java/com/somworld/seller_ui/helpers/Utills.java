package com.somworld.seller_ui.helpers;

import android.provider.CalendarContract;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by somesh.shrivastava on 17/12/14.
 */
public class Utills {

    public static long ONE_DAY_INTERVAL = 1000 * 60 * 60 * 24;

    public static final int START_DATE_CONTEXT = 0;
    public static final int END_DATE_CONTEXT = 1;

    public static final int INVALID_ID = -1;

    public static DateFormat getTimeFormat() {

        return DateFormat.getDateTimeInstance();
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

}
