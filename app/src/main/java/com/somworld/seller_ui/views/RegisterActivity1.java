package com.somworld.seller_ui.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.models.ParcelableKeys;
import com.somworld.seller_ui.models.dtos.RegistrationDTO;
import com.somworld.seller_ui.views.RegisterFragments.RegisterFragment_1;
import com.somworld.seller_ui.views.RegisterFragments.RegisterFragment_2;
import com.somworld.seller_ui.views.RegisterFragments.RegisterFragment_3;
import com.somworld.seller_ui.views.RegisterFragments.RegistrationActivityInterface;
import com.somworld.seller_ui.views.adapters.RegisterPageAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterActivity1 extends FragmentActivity implements RegistrationActivityInterface {

  private ViewPager viewPager;
  private RegisterPageAdapter registerPageAdapter;
  //private  Integer currentFragmentIndex = 0;
  private RegistrationDataManager registrationDataManager;

  @Override
  public void moveToNextPage() {
    synchronized (registrationDataManager.currentFragmentIndex) {
      if(viewPager.getChildCount() - 1 <= registrationDataManager.currentFragmentIndex) return;
      registrationDataManager.setCurrentFragmentIndex(registrationDataManager.currentFragmentIndex+1);
      viewPager.setCurrentItem(registrationDataManager.currentFragmentIndex , true);
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    viewPager.setCurrentItem(registrationDataManager.currentFragmentIndex,false);

  }

  @Override
  public void moveToPrevPage() {
    synchronized (registrationDataManager.currentFragmentIndex) {
      if(registrationDataManager.currentFragmentIndex <= 0) return;
      registrationDataManager.setCurrentFragmentIndex(registrationDataManager.currentFragmentIndex-1);

      viewPager.setCurrentItem(registrationDataManager.currentFragmentIndex, true);
    }
  }

  @Override
  public void saveData(Bundle bundle, String fragmentID) {
    RegistrationDTO data = bundle.getParcelable(ParcelableKeys.REGISTRATION_DATA);
    registrationDataManager.putData(fragmentID, data);
  }

  @Override
  public RegistrationDTO getFragmentData(String fragmentID) {
    RegistrationDataManager dataManager = RegistrationDataManager.getInstance();
    RegistrationDTO registrationDTO = dataManager.getData(fragmentID);
    return registrationDTO;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    List<Fragment> fragments = getFragments();
    registrationDataManager = RegistrationDataManager.getInstance();
    registerPageAdapter = new RegisterPageAdapter(getSupportFragmentManager(), fragments);
    viewPager = (ViewPager) findViewById(R.id.register_view_pager);
    viewPager.setAdapter(registerPageAdapter);
  }

  private List<Fragment> getFragments() {
    List<Fragment> fragments = new ArrayList<Fragment>();
    fragments.add(new RegisterFragment_1());
    fragments.add(new RegisterFragment_2());
    fragments.add(new RegisterFragment_3());

    return fragments;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.register_activity1, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private static class RegistrationDataManager {

    private static RegistrationDataManager mInstance = new RegistrationDataManager();
    private  Integer currentFragmentIndex = 0;
    private final ConcurrentHashMap<String,RegistrationDTO> registrationDataMap;



    private RegistrationDataManager() {
      registrationDataMap = new ConcurrentHashMap<String, RegistrationDTO>();
    }

    public void setCurrentFragmentIndex(int index) {
      currentFragmentIndex = index;
    }

    public static RegistrationDataManager getInstance() {
      if (mInstance == null) {
        synchronized (RegistrationDataManager.class) {
          if (mInstance == null) {
            mInstance = new RegistrationDataManager();
          }
        }
      }
      return mInstance;
    }


    public void putData(String position, RegistrationDTO registrationData) {
        registrationDataMap.put(position, registrationData);

    }

    public RegistrationDTO getData(String key) {
      if (registrationDataMap.containsKey(key)) {
        return registrationDataMap.get(key);
      }
      return null;
    }

  }
}


