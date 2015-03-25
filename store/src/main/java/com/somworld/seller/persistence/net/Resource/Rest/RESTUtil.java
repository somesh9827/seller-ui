package com.somworld.seller.persistence.net.resource.Rest;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 26/03/15.
 */
public class RESTUtil {
  public static List<RESTResourceParam> createRESTResourceParam(Map<String,String> pair) {
    if(pair == null) return null;
    List<RESTResourceParam> params = new ArrayList<RESTResourceParam>();
    RESTResourceParam param = null;
    for (Map.Entry<String,String> entry : pair.entrySet()) {
     param  = new RESTResourceParam(entry.getKey(),entry.getValue());
     params.add(param);
    }
   return params;
  }

}
