package com.somworld.seller_ui.views.common;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by somesh.shrivastava on 28/02/15.
 */
public class NoSwipableViewPager extends ViewPager {

  public NoSwipableViewPager(Context context) {
    super(context);
  }

  public NoSwipableViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return false;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return false;
  }
}
