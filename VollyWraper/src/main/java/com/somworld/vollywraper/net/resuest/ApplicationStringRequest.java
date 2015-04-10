package com.somworld.vollywraper.net.resuest;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 10/04/15.
 */
public class ApplicationStringRequest extends StringRequest {

  Map<String,String> mPostParam;
  String mToken = null;

  public ApplicationStringRequest(int method, String url,Map postParam,
                                  Response.Listener<String> listener,
                                  Response.ErrorListener errorListener) {
    super(method, url, listener, errorListener);
    mPostParam = postParam;
  }

  public ApplicationStringRequest(int method, String url,Map postParam,
                                  Response.Listener<String> listener,
                                  Response.ErrorListener errorListener,String token) {
    this(method, url, postParam,listener, errorListener);
    mToken = token;
  }

  @Override
  protected Map<String, String> getParams() throws AuthFailureError {
    if(mPostParam == null)
      return super.getParams();
    else
      return mPostParam;
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
