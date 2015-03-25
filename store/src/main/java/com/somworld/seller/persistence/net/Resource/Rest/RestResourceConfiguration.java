package com.somworld.seller.persistence.net.resource.Rest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by somesh.shrivastava on 25/03/15.
 */
public class RestResourceConfiguration {

  private String mDomain;
  private Protocol protocol;
  private int mPort;
  private String mUnQualifiedResourceName;
  private List<String> mPlaceholders;
  private List<RESTResourceParam> mResourceParam;

  public RestResourceConfiguration(String domain,int port,String unQualifiedResourceName,List<String>placeholders,List<RESTResourceParam> resourceParam) {
    mDomain = domain;
    mPort = port;
    mUnQualifiedResourceName = unQualifiedResourceName;
    mPlaceholders = new ArrayList<String>(placeholders);
    mResourceParam = new ArrayList<RESTResourceParam>(resourceParam);
  }

  public RestResourceConfiguration(String unQualifiedResourceName){
    mDomain = DefaultResourceConfiguration.DOMAIN;
    mPort = DefaultResourceConfiguration.PORT;
    protocol = DefaultResourceConfiguration.PROTOCOL;
    mPlaceholders = null;
    mResourceParam = null;
    mUnQualifiedResourceName = unQualifiedResourceName;
  }

  public RestResourceConfiguration setResourceParam(List<RESTResourceParam> resourceParams){
    if(resourceParams == null) return this;
    mResourceParam = new ArrayList<RESTResourceParam>();
    for(RESTResourceParam resourceParam : resourceParams) {
      mResourceParam.add(new RESTResourceParam(resourceParam));
    }
    return this;
  }

  public RestResourceConfiguration setPlaceholders(List<String> placeholders) {
    if(placeholders == null) return this;
    mResourceParam = new ArrayList<RESTResourceParam>();
    mPlaceholders = new ArrayList<String>(placeholders);
    return this;
  }

  public RestResourceConfiguration setProtocol(Protocol protocol){
    this.protocol = protocol;
    return this;
  }

  public RestResourceConfiguration setDomain(String domain) {
    if(domain == null) return this;
    mDomain = domain;
    return this;
  }

  public RestResourceConfiguration setPort(int port) {
    if(port <= 0) return this;
    mPort = port;
    return this;
  }

  public String getDomain(){
    return mDomain;
  }

  public int getPort() {
    return mPort;
  }

  public String getUnqalifiedResourceName() {
    return mUnQualifiedResourceName;
  }

  public List<String> getPlaceholders(){
    return mPlaceholders;
  }

  public List<RESTResourceParam> getResourceParam(){
    return mResourceParam;
  }

  public Protocol getProtocol() {
    return protocol;
  }



}
