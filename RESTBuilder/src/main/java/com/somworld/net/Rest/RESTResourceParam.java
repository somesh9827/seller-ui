package com.somworld.net.Rest;

/**
 * Created by somesh.shrivastava on 25/03/15.
 */
public class RESTResourceParam {

  private String key;
  private String value;

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  public RESTResourceParam(String key,String value) {
    this.key = key;
    this.value = value;
  }
  public RESTResourceParam(RESTResourceParam restResourceParam){
    if(restResourceParam == null) return;
    key = restResourceParam.getKey();
    value = restResourceParam.getValue();
  }

  @Override
  public String toString() {
    return key + "=" + value;
  }

}
