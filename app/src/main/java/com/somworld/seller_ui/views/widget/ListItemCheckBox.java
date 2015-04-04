package com.somworld.seller_ui.views.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.CheckBox;

/**
 * Created by somesh.shrivastava on 04/04/15.
 */
public class ListItemCheckBox extends CheckBox {

  public ListItemCheckBox(Context context) {
    super(context);
  }

  public ListItemCheckBox(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ListItemCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public boolean onKeyPreIme(int keyCode, KeyEvent event) {
    return false;
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    return false;
  }

  @Override
  public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
    return false;
  }

  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    return false;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    return false;
  }

  @Override
  public boolean onTrackballEvent(MotionEvent event) {
    return false;
  }
}
