package com.somworld.seller_ui.dataService.networkService;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.somworld.seller_ui.dataService.networkService.request.AppStringRequest;
import com.somworld.seller_ui.dataService.networkService.request.RequestType;

/**
 * Created by somesh.shrivastava on 11/01/15.
 */
public class SellerService implements Response.Listener,Response.ErrorListener {

   private final RequestType mRequestType;


    public SellerService(RequestType type) {
        mRequestType = type;
    }

    public RequestType getRequestType() {
        return mRequestType;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    @Override
    public void onResponse(Object o) {

    }
}
