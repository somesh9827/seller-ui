package com.somworld.seller_ui.helpers;

import com.somworld.seller_ui.models.OfferItems;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by somesh.shrivastava on 17/12/14.
 */
public class OfferHelper {

    public static boolean isValid(OfferItems offer) {

        Date currentDateTime = new Date();
        return (offer.isActive() && offer.getEndTime().getTime() >= currentDateTime.getTime());
    }

    public static String formatDate(Date date,int dateContext,DateFormat format)  {
        StringBuffer dateString = new StringBuffer(Utills.parsedDate(format, date));
        if(dateContext == Utills.START_DATE_CONTEXT) return dateString.insert(0,"").toString();
        if(dateContext == Utills.END_DATE_CONTEXT) return dateString.insert(0,"").toString();
        else return "";
    }
}
