package com.somworld.seller_ui.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by somesh.shrivastava on 18/01/15.
 */
public class RegisterPageAdapter extends FragmentPagerAdapter {

  private List<Fragment> registerFragmnets;

  public RegisterPageAdapter(FragmentManager fragment, List<Fragment> fragments) {
    super(fragment);
    this.registerFragmnets = fragments;
  }


  @Override
  public android.support.v4.app.Fragment getItem(int position) {
    return registerFragmnets.get(position);
  }

  @Override
  public int getCount() {
    return registerFragmnets.size();
  }
}
