package com.somworld.seller_ui.helpers;

import com.somworld.seller_ui.models.OfferItems;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by somesh.shrivastava on 13/12/14.
 */
public class FactoryGirl {

    public static List<OfferItems> getOffers(int numberOfItems) {
        if(numberOfItems <= 0) return null;

        List<OfferItems> offers = new ArrayList<OfferItems>(numberOfItems);
        for( int i = 0; i < numberOfItems; i++ ) {

            OfferItems offer = new OfferItems();
            offer.setActive(true);
            offer.setProduct("Nike Shoes");
            offer.setDescription("20% Discount on All Nike Shoes");
            offer.setDiscount("20%");
            offer.setStartTime(new Date());
            offer.setEndTime(new Date());
            offers.add(offer);
        }

        return offers;
    }
}
