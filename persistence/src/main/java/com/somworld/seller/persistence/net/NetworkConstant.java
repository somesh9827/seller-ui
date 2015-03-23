package com.somworld.seller.persistence.net;

/**
 * Created by somesh.shrivastava on 24/03/15.
 */
public interface NetworkConstant {

  interface Domain {

    String HOST  = "127.0.0.1";
    String PORT = "80";

  }

  interface USER {
    String HOME = "/user/";
    String REGISTER = HOME + "register/{role}";
    String LOGIN = HOME + "login/{role}";
    String LOGOUT = HOME + "logout/{role}";
  }

  interface SELLERS {
    String HOME = "/sellers/";
    String SHOP = HOME + "{sellerId}"+"/shops/";
    String OFFER = SHOP + "{shop_id}"+ "/offers";
    String DASHBOARD = HOME + "{seller_id}" + "/dashboard";
  }

  interface CUSTOMER {
    String HOME = "/customer/";
    String NEWS_FEED = HOME + "news_feed";
    String OFFER_FEEDBACK = HOME + "{customerId}"+ "/feedback/offers/" +"{offerId}";
    String SHOP_FEEDBACK = HOME + "{customerId}"+ "/feedback/shop/" +"{shopId}";
  }

  interface SHOP {
    String HOME = "/shops/{shopId}";
  }

  interface LOCATION {
    String HOME = "/locations/{locationId}/";
    String NEAR_BY_OFFER = HOME + "near_by_offer/" + "{distance}/?preference={}";
  }






}
