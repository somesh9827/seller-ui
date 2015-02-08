package com.somworld.seller_ui.dataService.networkService;

/**
 * Created by somesh.shrivastava on 11/01/15.
 */
public class API_SERVICE {

    public static final String GET_SELLER_INFO_API;
    public static final String UPDATE_SELLER_INFO_API;
    public static final String GET_OFFERS_API;
    public static final String GET_OFFER_API;
    public static final String CREATE_OFFER_API;
    public static final String UPDATE_OFFER_API;
    public static final String DELETE_OFFER_API;

    static {
         GET_SELLER_INFO_API = "sellers/get/";
         UPDATE_SELLER_INFO_API = "sellers/update_seller_id";
         GET_OFFERS_API = "offers//get/";
         GET_OFFER_API = "offers/:offer_id";
         CREATE_OFFER_API = "offers/create_offer";
         UPDATE_OFFER_API = "offers/update_offer/:offer_id";
         DELETE_OFFER_API = "offers/delete_offer/";

    }
}
