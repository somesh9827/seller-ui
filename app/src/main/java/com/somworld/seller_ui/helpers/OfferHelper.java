package com.somworld.seller_ui.helpers;

import com.somworld.seller_ui.models.OfferItems;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by somesh.shrivastava on 17/12/14.
 */
public class OfferHelper {

    public static boolean isValid(OfferItems offer) {

        Date currentDateTime = Utils.getCurrentDate();
        return (offer.isActive() && offer.getEndDate().getTime() >= currentDateTime.getTime());
    }

    public static String formatDate(Date date,int dateContext,DateFormat format)  {
        StringBuffer dateString = new StringBuffer(Utils.parsedDate(format, date));
        if(dateContext == Utils.START_DATE_CONTEXT) return dateString.insert(0,"").toString();
        if(dateContext == Utils.END_DATE_CONTEXT) return dateString.insert(0,"").toString();
        else return "";
    }
}
