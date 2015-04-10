package com.somworld.vollywraper.net;

import com.somworld.vollywraper.net.common.RequestController;
import com.somworld.vollywraper.net.response.StringNetworkResponseListenr;
import com.somworld.vollywraper.net.resuest.ApplicationStringRequest;
import com.somworld.vollywraper.persistene.ICallback;
import com.android.volley.Request.Method;

import java.util.Map;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public  class NetworkDataManagerImpl implements INetworkDataManager {
  private final ICallback mCallback;

  public NetworkDataManagerImpl(ICallback callback) {
    mCallback = callback;
  }

  @Override
  public ICallback getCallBack() {
    return mCallback;
  }

  protected void get(String URL,Class jsonMapperClass){
    apply(Method.GET,URL,jsonMapperClass,null);
  }

  protected void post(int method,String URL,Class jsonMapperClass,Map<String,String> payload){
    apply(Method.POST,URL,jsonMapperClass,payload);
  }

  protected void put(int method,String URL,Class jsonMapperClass,Map<String,String> payload){
    apply(Method.PUT,URL,jsonMapperClass,payload);
  }

  protected void delete(int method,String URL,Class jsonMapperClass,Map<String,String> payload){
    apply(Method.DELETE,URL,jsonMapperClass,payload);
  }

  private void apply(int method,String URL,Class jsonMapperClass,Map<String,String> payload){
    StringNetworkResponseListenr
        listener = new StringNetworkResponseListenr(getCallBack(),jsonMapperClass);
    ApplicationStringRequest request = new ApplicationStringRequest(
        method,URL,payload,listener,listener);
    RequestController.getInstance().addTorequestQueue(request);
  }

}
