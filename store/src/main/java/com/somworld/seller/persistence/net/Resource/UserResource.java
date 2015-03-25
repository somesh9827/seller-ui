package com.somworld.seller.persistence.net.resource;

import com.somworld.seller.persistence.net.resource.Rest.RESTResource;
import com.somworld.seller.persistence.net.resource.Rest.RestResourceManager;

/**
 * Created by somesh.shrivastava on 26/03/15.
 */
public class UserResource {

  public static RestResourceManager restResourceManager;
  static  {
    restResourceManager = new RestResourceManager();
  }
  public static String getHOME(){
    return restResourceManager.getQualifiedResource(RESTResource.Account.HOME,null,null);
  }

  public static String getResgisterResource(){
    return restResourceManager.getQualifiedResource(RESTResource.Account.REGISTER,null,null);
  }

  public static String getLoginResource(){
    return restResourceManager.getQualifiedResource(RESTResource.Account.LOGIN,null,null);
  }

  public static String getLogoutResource(){
    return restResourceManager.getQualifiedResource(RESTResource.Account.LOGIN,null,null);
  }


}
