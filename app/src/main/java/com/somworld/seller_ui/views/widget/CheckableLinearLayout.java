package com.somworld.seller_ui.views.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by somesh.shrivastava on 04/04/15.
 */
public class CheckableLinearLayout extends LinearLayout implements Checkable {

  private boolean isChecked;
  private List<Checkable> checkableViews;
  private OnCheckedChangeListener onCheckedChangeListener;

  public CheckableLinearLayout(Context context) {
    super(context);
    init();
  }

  public CheckableLinearLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public CheckableLinearLayout(Context context, AttributeSet attrs,
                               int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void  init() {
    isChecked = false;
    checkableViews = new ArrayList<Checkable>(7);
  }

  public static interface OnCheckedChangeListener {
    public void onCheckedChanged(CheckableLinearLayout layout, boolean isChecked);
  }


  @Override
  public void setChecked(boolean isChecked) {
    this.isChecked = isChecked;
    for (Checkable c : checkableViews) {
      c.setChecked(isChecked);
    }

    if (onCheckedChangeListener != null) {
      onCheckedChangeListener.onCheckedChanged(this, isChecked);
    }
  }

  @Override
  public boolean isChecked() {
    return isChecked;
  }

  @Override
  public void toggle() {
    this.isChecked = !this.isChecked;
    for (Checkable c : checkableViews) {
      c.toggle();
    }
  }

  public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
    this.onCheckedChangeListener = onCheckedChangeListener;
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    final int childCount = this.getChildCount();
    for (int i = 0; i < childCount; ++i) {
      findCheckableChildren(this.getChildAt(i));
    }
  }


  private void findCheckableChildren(View v) {
    if (v instanceof Checkable) {
      this.checkableViews.add((Checkable) v);
    }

    if (v instanceof ViewGroup) {
      final ViewGroup vg = (ViewGroup) v;
      final int childCount = vg.getChildCount();
      for (int i = 0; i < childCount; ++i) {
        findCheckableChildren(vg.getChildAt(i));
      }
    }
  }
}
