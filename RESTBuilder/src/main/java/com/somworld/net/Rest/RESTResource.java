package com.somworld.net.Rest;

/**
 * Created by somesh.shrivastava on 25/03/15.
 */
public interface RESTResource {


  public interface Account {
    String HOME = "/user/";
    String REGISTER = HOME + "register/{role}";
    String LOGIN = HOME + "login/{role}";
    String LOGOUT = HOME + "logout/{role}";
  }

  public interface SELLERS {
    String HOME = "/sellers/";
    String SHOP = HOME + "{sellerId}"+"/shops/";
    String OFFER = SHOP + "{shop_id}"+ "/offers";
    String DASHBOARD = HOME + "{seller_id}" + "/dashboard";
  }

  public interface CUSTOMER {
    String HOME = "/customer/";
    String NEWS_FEED = HOME + "news_feed";
    String OFFER_FEEDBACK = HOME + "{customerId}"+ "/feedback/offers/" +"{offerId}/";
    String SHOP_FEEDBACK = HOME + "{customerId}"+ "/feedback/shop/" +"{shopId}/";
  }

  public interface SHOP {
    String HOME = "/shops/{shopId}";
    String Offer = HOME + "/offer/{offer_id}";
  }

  public interface LOCATION {
    String HOME = "/locations/{locationId}/";
    String NEAR_BY_OFFER = HOME + "near_by_offer/" + "{distance}/?preference={}";
  }

  String placeholderStartDelimiter = "{";
  String placeholderEndDelimiter = "}";


}
