package com.somworld.seller_ui.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.dataService.DataServiceErrorResponse;
import com.somworld.seller_ui.dataService.DataServiceSuccessResponse;
import com.somworld.seller_ui.dataService.IDataServiceCallback;
import com.somworld.seller_ui.dataService.SellerDataManager;
import com.somworld.seller_ui.helpers.DtoToModelMapper;
import com.somworld.seller_ui.helpers.Util;
import com.somworld.seller_ui.models.ParcelableKeys;
import com.somworld.seller_ui.models.RegisterModel;
import com.somworld.seller_ui.models.dtos.AddressDTO;
import com.somworld.seller_ui.models.dtos.MailAndPasswordDTO;
import com.somworld.seller_ui.models.dtos.RegistrationDTO;
import com.somworld.seller_ui.models.dtos.RegistrationPageDTO;
import com.somworld.seller_ui.models.dtos.ShopNameDTO;
import com.somworld.seller_ui.models.dtos.UserInfoDTO;
import com.somworld.seller_ui.views.RegisterFragments.RegisterFragment_1;
import com.somworld.seller_ui.views.RegisterFragments.RegisterFragment_2;
import com.somworld.seller_ui.views.RegisterFragments.RegisterFragment_3;
import com.somworld.seller_ui.views.RegisterFragments.RegisterFragment_4;
import com.somworld.seller_ui.views.RegisterFragments.RegistrationActivityInterface;
import com.somworld.seller_ui.views.adapters.RegisterPageAdapter;
import com.somworld.seller_ui.views.common.ApplicationAlertDialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterActivity1 extends FragmentActivity implements RegistrationActivityInterface,
                                                                   IDataServiceCallback,ApplicationAlertDialog.DialogCallbackInterface {

  private ViewPager viewPager;
  private RegisterPageAdapter registerPageAdapter;
  //private  Integer currentFragmentIndex = 0;
  private RegistrationDataManager registrationDataManager;
  private int fragmentsCount = 0;

  @Override
  public void moveToNextPage() {
    synchronized (registrationDataManager.currentFragmentIndex) {
      if(fragmentsCount - 1 <= registrationDataManager.currentFragmentIndex) return;
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
    RegistrationPageDTO data = bundle.getParcelable(ParcelableKeys.REGISTRATION_DATA);
    registrationDataManager.putData(fragmentID, data);
  }

  @Override
  public RegistrationPageDTO getFragmentData(String fragmentID) {
    RegistrationDataManager dataManager = RegistrationDataManager.getInstance();
    RegistrationPageDTO registrationPageDTO = dataManager.getData(fragmentID);
    return registrationPageDTO;
  }

  @Override
  public void register() {
    RegistrationDataManager registrationDataManager = RegistrationDataManager.getInstance();
    ConcurrentHashMap<String,RegistrationPageDTO> registrationData
          = registrationDataManager.getRegistrationData();
    Collection<RegistrationPageDTO> registrationPageDTOCollection =  registrationData.values();
    RegistrationDTO registerDTO = new RegistrationDTO();
    for(RegistrationPageDTO data : registrationPageDTOCollection){
      if(data.getClass().equals(MailAndPasswordDTO.class)){
        Toast.makeText(this, "MailAndPasswordDTO", Toast.LENGTH_LONG).show();
        registerDTO.setLoginDetail((MailAndPasswordDTO)data);
      }
      else if(data.getClass().equals(UserInfoDTO.class)){
        Toast.makeText(this,"UserInfoDTO",Toast.LENGTH_LONG).show();
        registerDTO.setUserInfo((UserInfoDTO) data);
      }
      else if(data.getClass().equals(ShopNameDTO.class)){
        Toast.makeText(this,"ShopNameDTO",Toast.LENGTH_LONG).show();
        registerDTO.setShopDetail((ShopNameDTO) data);
      }
      else if(data.getClass().equals(AddressDTO.class)){
        Toast.makeText(this,"AddressDTO",Toast.LENGTH_LONG).show();
        registerDTO.setShopAddress((AddressDTO) data);
      }
    }

    RegisterModel registerModel = DtoToModelMapper.populateRegisterModel(registerDTO);
    SellerDataManager sellerDataManager = new SellerDataManager(Util.getWeakReference(this));
    sellerDataManager.register(registerModel);
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
    fragments.add(new RegisterFragment_4());
    fragmentsCount = 4;
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

  @Override
  public void onDataServiceSuccess(DataServiceSuccessResponse dataServiceSuccessResponse) {
    Intent intent = new Intent(this,DashBoard.class);
    startActivity(intent);
  }

  @Override
  public void onDataServiceFail(DataServiceErrorResponse dataServiceErrorResponse) {
    Toast.makeText(this,dataServiceErrorResponse.getMessage(),Toast.LENGTH_LONG).show();
  }

  @Override
  public void onBackPressed() {
   // super.onBackPressed();
    ApplicationAlertDialog dialog = new ApplicationAlertDialog();
    dialog.setHeadingText(getString(R.string.default_dialog_alert_heading_text)).setContentText(getString(R.string.registration_leave_confirm_message)).setPositiveButtonText(getString(R.string.yes_button_text)).setNegativeButtonText(getString(R.string.no_button_text));
    dialog.show(getFragmentManager(), "");
  }

  @Override
  public void onPositiveButtonClicked() {
   moveToParentActivity();
  }

  @Override
  public void onNegativeButtonClicked() {

  }

  private void moveToParentActivity(){
    Intent showDashBoard = new Intent(this,LoginOrRegisterSelectionActivity.class);
    startActivity(showDashBoard);
    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    RegistrationDataManager.getInstance().clear();
    finish();
  }

  private static class RegistrationDataManager {

    private static RegistrationDataManager mInstance = new RegistrationDataManager();
    private  Integer currentFragmentIndex = 0;
    private final ConcurrentHashMap<String,RegistrationPageDTO> registrationDataMap;



    private RegistrationDataManager() {
      registrationDataMap = new ConcurrentHashMap<String, RegistrationPageDTO>();
    }

    ConcurrentHashMap<String,RegistrationPageDTO> getRegistrationData(){

      return new ConcurrentHashMap<String, RegistrationPageDTO>(registrationDataMap);
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


    public void putData(String position, RegistrationPageDTO registrationData) {
        registrationDataMap.put(position, registrationData);

    }

    public RegistrationPageDTO getData(String key) {
      if (registrationDataMap.containsKey(key)) {
        return registrationDataMap.get(key);
      }
      return null;
    }

    public void clear(){
      mInstance = null;
      if(registrationDataMap != null)
        registrationDataMap.clear();
    }

  }
}


