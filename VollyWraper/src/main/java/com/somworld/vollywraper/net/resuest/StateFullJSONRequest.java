package com.somworld.vollywraper.net.resuest;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 24/03/15.
 */
public class StateFullJSONRequest extends JsonObjectRequest {
  String mToken;

  public StateFullJSONRequest(int method, String url, JSONObject jsonRequest,
                              Response.Listener<JSONObject> listener,
                              Response.ErrorListener errorListener, String token) {
    super(method, url, jsonRequest, listener, errorListener);
    mToken = token;
  }

  public StateFullJSONRequest(String url, JSONObject jsonRequest,
                              Response.Listener<JSONObject> listener,
                              Response.ErrorListener errorListener, String token) {
    super(url, jsonRequest, listener, errorListener);
    mToken = token;
  }

  @Override
  public Map<String, String> getHeaders() throws AuthFailureError {
    Map<String, String> headers = super.getHeaders();
    if (headers == null
        || headers.equals(Collections.emptyMap())) {
      headers = new HashMap<String, String>();
    }
    if(mToken != null && !mToken.equals(""))
      headers.put("token", mToken);
    return headers;
  }
}
