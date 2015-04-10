package com.somworld.store.net.resource;


import com.somworld.net.Rest.RESTResourceParam;
import com.somworld.net.Rest.RESTUtil;
import com.somworld.net.Rest.RestResourceManager;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 26/03/15.
 */
public class SellerResource {

  public  String getSeller(){
    return RestResourceManager.getInstance().getQualifiedResource(
        RESTResource.SELLERS.HOME,null,null);
  }
  public static String getSellerShop(String sellerId,Map<String,String> params) {
    List<String> placeholders = new ArrayList<String>();
    placeholders.add(sellerId);
    List<com.somworld.net.Rest.RESTResourceParam> restParams = RESTUtil
        .createRESTResourceParam(params);
    return  RestResourceManager.getInstance().getQualifiedResource(RESTResource.SELLERS.SHOP,
                                                                   restParams, placeholders);
  }

  public static String getSellerShopOffer(String sellerId,String offerId,Map<String,String> params) {
    List<String> placeholders = new ArrayList<String>();
    placeholders.add(sellerId);
    placeholders.add(offerId);
    List<RESTResourceParam> restParams = RESTUtil.createRESTResourceParam(params);
    return RestResourceManager.getInstance().getQualifiedResource(RESTResource.SELLERS.OFFER,
                                                                  restParams, placeholders);
  }

  public static String getSellerDashBoard(String sellerId,Map<String,String> params) {
    List<String> placeholders = new ArrayList<String>();
    placeholders.add(sellerId);
    List<RESTResourceParam> restParams = RESTUtil.createRESTResourceParam(params);
    return RestResourceManager.getInstance().getQualifiedResource(RESTResource.SELLERS.DASHBOARD,
                                                                  restParams, placeholders);
  }

}
