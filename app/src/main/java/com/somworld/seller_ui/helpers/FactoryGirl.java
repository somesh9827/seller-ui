package com.somworld.seller_ui.helpers;

import com.somworld.seller_ui.models.OfferItems;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by somesh.shrivastava on 13/12/14.
 */
public class FactoryGirl {

    public static List<OfferItems> getOffers(int activeOfferCount, int inActiveOfferCount) {
        if(activeOfferCount <= 0) return null;

        List<OfferItems> offers = new ArrayList<OfferItems>(activeOfferCount);
        for( int i = 0; i < activeOfferCount; i++ ) {

            OfferItems offer = new OfferItems();
            offer.setId(i);
            offer.setActive(true);
            offer.setProduct("Nike Shoes");
            offer.setDescription("20% Discount on All Nike Shoes");
            offer.setDiscount("20%");
            offer.setStartDate(Utils.getCurrentDate());
            Date endDate = Utils.getCurrentDate();
            endDate.setTime(endDate.getTime() + Utils.ONE_DAY_INTERVAL);
            offer.setEndDate(endDate);
            offers.add(offer);
        }

        for( int i = 0; i < inActiveOfferCount; i++ ) {

            OfferItems offer = new OfferItems();
            offer.setId(i);
            offer.setActive(false);
            offer.setProduct("Nike Shoes");
            offer.setDescription("20% Discount on All Nike Shoes");
            offer.setDiscount("20%");
            offer.setStartDate(Utils.getCurrentDate());
            offer.setEndDate(Utils.getCurrentDate());
            offers.add(offer);
        }

        return offers;
    }
}
