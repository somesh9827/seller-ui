package com.somworld.seller.persistence.net.resource;

import com.somworld.seller.persistence.net.resource.Rest.RESTResource;
import com.somworld.seller.persistence.net.resource.Rest.RESTResourceParam;
import com.somworld.seller.persistence.net.resource.Rest.RESTUtil;
import com.somworld.seller.persistence.net.resource.Rest.RestResourceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 26/03/15.
 */
public class CustomerResource {
  private static RestResourceManager restResourceManager;

  static{
    restResourceManager = new RestResourceManager();
  }

  public static String getCustomer(){
    return restResourceManager.getQualifiedResource(RESTResource.CUSTOMER.HOME,null,null);
  }

  public static String getCustomeFeedbackOfShop(String customerId,String shopId,Map<String,String> params) {
    List<RESTResourceParam> restResourceParams = RESTUtil.createRESTResourceParam(params);
    List<String> placeholder = new ArrayList<String>();
    placeholder.add(customerId);
    placeholder.add(shopId);
    return restResourceManager.getQualifiedResource(RESTResource.CUSTOMER.SHOP_FEEDBACK,null,null);
  }

  public static String getCustomeFeedbackOfOffer(String customerId,String offerId,Map<String,String> params) {
    List<RESTResourceParam> restResourceParams = RESTUtil.createRESTResourceParam(params);
    List<String> placeholder = new ArrayList<String>();
    placeholder.add(customerId);
    placeholder.add(offerId);
    return restResourceManager.getQualifiedResource(RESTResource.CUSTOMER.OFFER_FEEDBACK,null,null);
  }

  public static String getCustomeNearByOffer(Map<String,String> params) {
    List<RESTResourceParam> restResourceParams = RESTUtil.createRESTResourceParam(params);
    return restResourceManager.getQualifiedResource(RESTResource.CUSTOMER.NEWS_FEED,null,null);
  }


}
