package com.somworld.seller_ui.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.models.ParcelableKeys;
import com.somworld.seller_ui.models.dtos.RegistrationDTO;
import com.somworld.seller_ui.views.RegisterFragments.RegisterFragment_1;
import com.somworld.seller_ui.views.RegisterFragments.RegisterFragment_2;
import com.somworld.seller_ui.views.RegisterFragments.RegistrationActivityInterface;
import com.somworld.seller_ui.views.adapters.RegisterPageAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterActivity1 extends FragmentActivity implements RegistrationActivityInterface {

  private ViewPager viewPager;
  private RegisterPageAdapter registerPageAdapter;

  @Override
  public void moveToNextPage() {
    viewPager.setCurrentItem(1, true);
  }

  @Override
  public void moveToPrevPage() {
    viewPager.setCurrentItem(-1, true);
  }

  @Override
  public void saveData(Bundle bundle, int fragmentID) {
    RegistrationDataManager dataManager = RegistrationDataManager.getInstance();
    RegistrationDTO data = bundle.getParcelable(ParcelableKeys.REGISTRATION_DATA);
    dataManager.putData(fragmentID, data);
    Toast.makeText(this, Integer.toString(fragmentID), Toast.LENGTH_LONG).show();
  }

  @Override
  public RegistrationDTO getFragmentData(int fragmentID) {
    RegistrationDataManager dataManager = RegistrationDataManager.getInstance();
    RegistrationDTO registrationDTO = dataManager.getData(fragmentID);
    return registrationDTO;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    List<Fragment> fragments = getFragments();
    registerPageAdapter = new RegisterPageAdapter(getSupportFragmentManager(), fragments);
    viewPager = (ViewPager) findViewById(R.id.register_view_pager);
    viewPager.setAdapter(registerPageAdapter);
  }

  private List<Fragment> getFragments() {
    List<Fragment> fragments = new ArrayList<Fragment>();
    fragments.add(new RegisterFragment_1());
    fragments.add(new RegisterFragment_2());
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
    private final ConcurrentHashMap<Integer,RegistrationDTO> registrationDataMap;



    private RegistrationDataManager() {
      registrationDataMap = new ConcurrentHashMap<Integer, RegistrationDTO>();
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


    public void putData(int position, RegistrationDTO registrationData) {
        registrationDataMap.put(position, registrationData);

    }

    public RegistrationDTO getData(int key) {
      if (registrationDataMap.containsKey(key)) {
        return registrationDataMap.get(key);
      }
      return null;
    }

  }
}


