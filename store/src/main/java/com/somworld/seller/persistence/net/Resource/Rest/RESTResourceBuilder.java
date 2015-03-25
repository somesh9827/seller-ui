package com.somworld.seller.persistence.net.resource.Rest;

import java.util.Iterator;

/**
 * Created by somesh.shrivastava on 25/03/15.
 */
public class RESTResourceBuilder{


  public static String build(RestResourceConfiguration configuration) {

    StringBuilder resource_path = new StringBuilder("");
    addProtocol(resource_path,configuration);

    resource_path.append(configuration.getDomain() + ":" + configuration.getPort());
    resource_path.append(configuration.getUnqalifiedResourceName());

    addPlaceholder(resource_path,configuration);
    addParam(resource_path,configuration);

    return resource_path.toString();
  }

  private static void addParam(StringBuilder resource_path,RestResourceConfiguration configuration) {
    if(configuration.getResourceParam() != null && !configuration.getResourceParam().isEmpty()) {
      resource_path.append("?");
      for(RESTResourceParam resourceParam : configuration.getResourceParam()){
        resource_path.append(resourceParam.toString());
        resource_path.append(DefaultResourceConfiguration.PARAM_DELIMITER);
      }
      resource_path.deleteCharAt(resource_path.lastIndexOf(DefaultResourceConfiguration.PARAM_DELIMITER));
    }
  }

  private static void addProtocol(StringBuilder resource_path,RestResourceConfiguration configuration) {
    switch(configuration.getProtocol()) {
      case http:
        resource_path.append("http://");break;
      case https:
        resource_path.append("https://");break;
      default:
        resource_path.append("http://");
    }
  }

  private static void addPlaceholder(StringBuilder resource_path,RestResourceConfiguration configuration) {
    boolean hasAnyPlaceholder = resource_path.indexOf(RESTResource.placeholderStartDelimiter) != -1;
    if(configuration.getPlaceholders() != null) {
      Iterator<String> iterator = configuration.getPlaceholders().iterator();
      int endIndex,startIndex;
      while((startIndex = resource_path.indexOf(RESTResource.placeholderStartDelimiter)) != -1 && iterator.hasNext()) {
        endIndex = resource_path.indexOf(RESTResource.placeholderEndDelimiter);
        if(endIndex != -1) {
          resource_path.replace(startIndex,endIndex+1,iterator.next());
        }
        else {
          throw new ResourceBuilderException("Incorrect UnQualified Resource");
        }
      }

      if((startIndex == -1 && iterator.hasNext()) || (startIndex != -1 && !iterator.hasNext()))
        throw new ResourceBuilderException("Wrong Number of placeholders");
    }
    else if(configuration.getPlaceholders() == null && hasAnyPlaceholder) {
      throw new ResourceBuilderException("Wrong Number of placeholders");
    }
  }
}
