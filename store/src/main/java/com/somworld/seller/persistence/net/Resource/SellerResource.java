package com.somworld.seller.persistence.net.resource;

import com.somworld.seller.persistence.net.resource.Rest.RESTPlaceholder;
import com.somworld.seller.persistence.net.resource.Rest.RESTResource;
import com.somworld.seller.persistence.net.resource.Rest.RESTResourceParam;
import com.somworld.seller.persistence.net.resource.Rest.RESTUtil;
import com.somworld.seller.persistence.net.resource.Rest.RestResourceManager;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 26/03/15.
 */
public class SellerResource {
  public static RestResourceManager restResourceManager;

  static {
    restResourceManager = new RestResourceManager();
  }

  public static String getSeller(){
    return restResourceManager.getQualifiedResource(RESTResource.SELLERS.HOME,null,null);
  }

  public static String getSellerShop(String sellerId,Map<String,String> params) {
    List<String> placeholders = new ArrayList<String>();
    placeholders.add(sellerId);
    List<RESTResourceParam> restParams = RESTUtil.createRESTResourceParam(params);
    return restResourceManager.getQualifiedResource(RESTResource.SELLERS.SHOP,restParams,placeholders);
  }

  public static String getSellerShopOffer(String sellerId,String offerId,Map<String,String> params) {
    List<String> placeholders = new ArrayList<String>();
    placeholders.add(sellerId);
    placeholders.add(offerId);
    List<RESTResourceParam> restParams = RESTUtil.createRESTResourceParam(params);
    return restResourceManager.getQualifiedResource(RESTResource.SELLERS.OFFER,restParams,placeholders);
  }

  public static String getSellerDashBoard(String sellerId,Map<String,String> params) {
    List<String> placeholders = new ArrayList<String>();
    placeholders.add(sellerId);
    List<RESTResourceParam> restParams = RESTUtil.createRESTResourceParam(params);
    return restResourceManager.getQualifiedResource(RESTResource.SELLERS.DASHBOARD,restParams,placeholders);
  }

}
