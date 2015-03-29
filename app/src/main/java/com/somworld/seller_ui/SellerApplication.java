package com.somworld.seller_ui;

import android.app.Application;

import com.somworld.seller.common.ContextManager;
import com.somworld.seller_ui.helpers.Util;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
public class SellerApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
  }

  private void initApplication() {
    ContextManager.getInstance().setContext(Util.getWeakReference(getApplicationContext()));
  }
}
