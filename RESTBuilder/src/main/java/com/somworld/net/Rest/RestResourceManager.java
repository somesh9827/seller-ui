package com.somworld.net.Rest;

import java.util.List;

/**
 * Created by somesh.shrivastava on 25/03/15.
 */

public class RestResourceManager {

  private RestResourceConfiguration restResourceConfiguration;

  private static RestResourceManager instance;

  static {
    instance = new RestResourceManager();
  }

  private RestResourceManager() {
  }

  public static RestResourceManager getInstance() {
    return instance;
  }

  public String getQualifiedResource(String unqualifiedResource,List<RESTResourceParam> resourceParams,List<String> placeholders) {
    restResourceConfiguration = new RestResourceConfiguration(unqualifiedResource);
    restResourceConfiguration.setDomain(DefaultResourceConfiguration.DOMAIN).setPort(DefaultResourceConfiguration.PORT).setProtocol(DefaultResourceConfiguration.PROTOCOL);
    restResourceConfiguration.setPlaceholders(placeholders).setResourceParam(resourceParams);
    RESTResourceBuilder restResourceBuilder = new RESTResourceBuilder();
    return restResourceBuilder.build(restResourceConfiguration);
  }

}
