package com.somworld.seller_ui.helpers;

import java.lang.ref.WeakReference;

/**
 * Created by somesh.shrivastava on 29/03/15.
 */
public class Util {
  public static <T> T getWeakReference(T obj){
    WeakReference<T> ref =  new WeakReference<T>(obj);
    return ref.get();
  }
}
