package com.somworld.seller.common;

import android.content.Context;

/**
 * Created by somesh.shrivastava on 29/03/15.
 */
public class ContextManager {
  private Context mContext;

  private static ContextManager contextManager;

  public static ContextManager getInstance() {
    if(contextManager == null)
      contextManager = new ContextManager();
    return contextManager;
  }

  public synchronized void setContext(Context context) {
    if(mContext == null)
      mContext = context;
  }

  public Context getContext() {
    return this.mContext;
  }
}
