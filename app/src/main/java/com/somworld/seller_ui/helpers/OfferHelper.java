package com.somworld.seller_ui.helpers;

import com.somworld.seller_ui.models.OfferItems;
import java.util.Date;

/**
 * Created by somesh.shrivastava on 17/12/14.
 */
public class OfferHelper {

    public static boolean isValid(OfferItems offer) {

        Date currentDateTime = new Date();
        return (offer.isActive() && offer.getEndTime().getTime() >= currentDateTime.getTime());
    }
}
