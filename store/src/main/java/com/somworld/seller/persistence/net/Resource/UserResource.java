package com.somworld.seller.persistence.net.resource;

import com.somworld.net.Rest.RestResourceManager;
import com.somworld.net.Rest.RESTResource;

/**
 * Created by somesh.shrivastava on 26/03/15.
 */
public class UserResource {


  public static String getHOME(){
    return RestResourceManager.getInstance().getQualifiedResource(RESTResource.Account.HOME,null,null);
  }

  public static String getResgisterResource(){
    return RestResourceManager.getInstance().getQualifiedResource(RESTResource.Account.REGISTER,null,null);
  }

  public static String getLoginResource(){
    return RestResourceManager.getInstance().getQualifiedResource(RESTResource.Account.LOGIN,null,null);
  }

  public static String getLogoutResource(){
    return RestResourceManager.getInstance().getQualifiedResource(RESTResource.Account.LOGIN,null,null);
  }


}
