package com.somworld.seller_ui.dataService.networkService.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.somworld.seller_ui.dataService.networkService.CookieManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 11/01/15.
 */
public class AppStringRequest extends StringRequest {
    private final Map<String,String> mParams;

    public AppStringRequest(int method, String url, Map<String, String> params,Response.Listener<String> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);

        mParams = params;
    }

    @Override
    protected Map<String, String> getParams() {
        return mParams;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        CookieManager.checkSessionCookie(response.headers);
        return super.parseNetworkResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        if (headers == null
                || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }
        CookieManager.addSessionCookie(headers);

        return headers;
    }

}
