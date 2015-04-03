package com.somworld.vollywraper.net.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.somworld.vollywraper.common.ContextManager;
import com.somworld.vollywraper.persistene.ModuleConstant;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public class RequestController {

  private static RequestController _instance;

  private RequestQueue mRequestQueue;

  private ImageLoader mImageLoader;

  private final String TAG = "RequsetController";

  private RequestController(){

  }

  public static synchronized RequestController getInstance() {
    if(_instance == null) {
      _instance = new RequestController();
      _instance.init(ContextManager.getInstance().getContext());
    }
    return _instance;
  }

  private  void init(Context context) {
    if(context == null) Log.d(ModuleConstant.LOG.TAG,"Context is not set");
    mRequestQueue = Volley.newRequestQueue(context);
    mImageLoader = new ImageLoader(mRequestQueue,new LruBitmapCache());

  }

  public <T> void addTorequestQueue(Request<T> req,String tag) {
    req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
    mRequestQueue.add(req);
  }

  public <T> void addTorequestQueue(Request<T> req) {
    req.setTag(TAG);
    mRequestQueue.add(req);
  }

  public void cancleAllPendingRequest(Object tag){
    if (mRequestQueue != null) {
      mRequestQueue.cancelAll(tag);
    }
  }

}
