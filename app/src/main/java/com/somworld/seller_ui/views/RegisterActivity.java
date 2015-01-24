package com.somworld.seller_ui.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.views.RegisterFragments.RegisterFragment_1;
import com.somworld.seller_ui.views.adapters.RegisterPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by somesh.shrivastava on 18/01/15.
 */
public class RegisterActivity extends FragmentActivity {

  private RegisterPageAdapter registerPageAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    List<Fragment> fragments = getFragments();
    registerPageAdapter = new RegisterPageAdapter(getSupportFragmentManager(), fragments);
    ViewPager pager = (ViewPager) findViewById(R.id.register_view_pager);
    pager.setAdapter(registerPageAdapter);
  }

  private List<Fragment> getFragments() {
    List<Fragment> fragments = new ArrayList<Fragment>();
    fragments.add(new RegisterFragment_1());
    return fragments;
  }
}
