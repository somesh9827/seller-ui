package com.somworld.seller.persistence.net.resource;



import com.somworld.net.Rest.RESTResource;
import com.somworld.net.Rest.RESTResourceParam;
import com.somworld.net.Rest.RESTUtil;
import com.somworld.net.Rest.RestResourceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 26/03/15.
 */
public class CustomerResource {

  public static String getCustomer(){
    return RestResourceManager.getInstance().getQualifiedResource(RESTResource.CUSTOMER.HOME,null,null);
  }

  public static String getCustomeFeedbackOfShop(String customerId,String shopId,Map<String,String> params) {
    List<RESTResourceParam> restResourceParams = RESTUtil.createRESTResourceParam(params);
    List<String> placeholder = new ArrayList<String>();
    placeholder.add(customerId);
    placeholder.add(shopId);
    return RestResourceManager.getInstance().getQualifiedResource(
        RESTResource.CUSTOMER.SHOP_FEEDBACK, null, null);
  }

  public static String getCustomeFeedbackOfOffer(String customerId,String offerId,Map<String,String> params) {
    List<RESTResourceParam> restResourceParams = RESTUtil.createRESTResourceParam(params);
    List<String> placeholder = new ArrayList<String>();
    placeholder.add(customerId);
    placeholder.add(offerId);
    return RestResourceManager.getInstance().getQualifiedResource(
        RESTResource.CUSTOMER.OFFER_FEEDBACK, null, null);
  }

  public static String getCustomeNearByOffer(Map<String,String> params) {
    List<RESTResourceParam> restResourceParams = RESTUtil.createRESTResourceParam(params);
    return RestResourceManager.getInstance().getQualifiedResource(RESTResource.CUSTOMER.NEWS_FEED,
                                                                  null, null);
  }


}
