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
public class ShopResource {

  public static String getShop(String shopId) {
    List<String> placeholder = new ArrayList<String>();
    placeholder.add(shopId);
    return RestResourceManager.getInstance().getQualifiedResource(RESTResource.SHOP.HOME, null,
                                                                  placeholder);
  }

  public static String getShopOffers(String shopId,Map<String,String> params) {
    List<RESTResourceParam> restResourceParams = RESTUtil.createRESTResourceParam(params);
    List<String> placeholder = new ArrayList<String>();
    placeholder.add(shopId);
    return RestResourceManager.getInstance().getQualifiedResource(RESTResource.SHOP.Offer,
                                                                  restResourceParams, placeholder);
  }

}
