package com.somworld.seller_ui.dataService.networkService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 11/01/15.
 */
public class URLHelper {

    public  static String resolveURL(String URL,Map<String,String> values) {
        if(URL == null) throw new IllegalArgumentException("URL should not be null");

        if(values == null || values.size() == 0) return URL;

        StringBuilder url = new StringBuilder(URL);
        int fromIndex,toIndex;
        for(Map.Entry<String,String> entry : values.entrySet()) {
            fromIndex = url.indexOf(":"+entry.getKey());

            if(fromIndex > 0) {
               toIndex = entry.getKey().length() + fromIndex + 1;
               url = url.replace(fromIndex,toIndex,entry.getValue());
            }
        }
        return url.toString();
    }
}
