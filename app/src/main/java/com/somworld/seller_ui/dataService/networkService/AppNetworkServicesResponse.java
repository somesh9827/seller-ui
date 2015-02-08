package com.somworld.seller_ui.dataService.networkService;

import com.android.volley.VolleyError;
import com.somworld.seller_ui.dataService.networkService.request.RequestType;

/**
 * Created by somesh.shrivastava on 11/01/15.
 */
public interface AppNetworkServicesResponse {

    public void onSuccess(Object o,RequestType type);

    public void onError(VolleyError error);
}
