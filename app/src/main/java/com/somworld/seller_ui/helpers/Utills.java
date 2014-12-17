package com.somworld.seller_ui.helpers;

import java.text.DateFormat;

/**
 * Created by somesh.shrivastava on 17/12/14.
 */
public class Utills {

    public static long ONE_DAY_INTERVAL = 1000 * 60 * 60 * 24;

    public DateFormat getTimeFormat() {

        return DateFormat.getDateTimeInstance();
    }
}
