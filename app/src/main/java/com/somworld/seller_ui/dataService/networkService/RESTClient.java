package com.somworld.seller_ui.dataService.networkService;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by somesh.shrivastava on 11/01/15.
 */
public class RESTClient {
    private Context _context;

    private RequestQueue mRequestQueue;

    private ImageLoader mImageLoader;

    public static final String TAG = RESTClient.class.getSimpleName();

    private static RESTClient mInstance;

    public static  RESTClient getInstance(Context context){
        if(mInstance == null)
            synchronized (mInstance) {
                mInstance = new RESTClient(context);
            }
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(_context);
        return mRequestQueue;
    }

    public ImageLoader getImaglaoder() {
        if(mImageLoader ==null)
            mImageLoader = new ImageLoader(this.getRequestQueue(),new LruBitmapCache());
        return mImageLoader;
    }

    public <T> void addTorequestQueue(Request<T> req,String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addTorequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancleAllPendingRequest(Object tag){
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    private  RESTClient(Context context) {
        this._context = context;
    }

}
