package com.somworld.seller_ui.dataService.networkService;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by somesh.shrivastava on 28/09/14.
 */
public class LruBitmapCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache{

    public static int getDefaultCacheSize(){
        final int maxMemoery = (int)(Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize = maxMemoery/8;
        return cacheSize;
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    public LruBitmapCache(){
        this(getDefaultCacheSize());
    }
    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }
    public LruBitmapCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url,bitmap);
    }
}
