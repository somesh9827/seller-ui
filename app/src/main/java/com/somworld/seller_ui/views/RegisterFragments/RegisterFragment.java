package com.somworld.seller_ui.views.RegisterFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.ClassUtil;
import com.somworld.seller_ui.models.ParcelableKeys;
import com.somworld.seller_ui.models.dtos.RegistrationPageDTO;

import java.lang.ref.WeakReference;

/**
 * Created by somesh.shrivastava on 19/01/15.
 */
public abstract class RegisterFragment extends Fragment {

  private String fragmentID;
  protected Button nextButton, backButton, skipButton = null;
  protected static Button saveButton = null;

  protected final Button getNextButton() {
    return nextButton;
  }

  protected final Button getPrevButton() {
    return backButton;
  }

  protected final Button getSkipButton() {
    return skipButton;
  }

  protected static final Button getSaveButton(){
    return saveButton;
  }

  protected abstract RegistrationPageDTO getCurrentFragmentData();

  protected abstract String getTitle();

  protected abstract void setCurrentFragmentData(RegistrationPageDTO bundle);

  private enum  MOVE_TO_PAGE {
    NEXT,PREVIOUS,SAVE
  };

  private MOVE_TO_PAGE page = MOVE_TO_PAGE.NEXT;

  private void setFragmentID() {
    fragmentID = ClassUtil.getClassName(new WeakReference<RegisterFragment>(this).get());
  }

  protected  void validateData(){
    onValidationSuccess(null);
  }

  protected boolean validateWhenMoveToPreviousPage(){
    return false;
  }

  protected boolean validateWhenMoveToNextPage(){
    return false;
  }

  protected final void onValidationSuccess(Object data) {
    if(page == MOVE_TO_PAGE.NEXT) moveToNextPage();
    else if(page == MOVE_TO_PAGE.PREVIOUS)
      moveToPreviousPage();
    else if(page == MOVE_TO_PAGE.SAVE)
         saveAndRegister();
  }

  protected final void onValidationFail(Object error){
    //Toast.makeText(this.getActivity(),"Fail",Toast.LENGTH_LONG).show();

  }

  private void moveToNextPage() {
    RegistrationActivityInterface registrationActivityInterface =
        ((RegistrationActivityInterface) getActivity());
    if (registrationActivityInterface != null) {
      RegistrationPageDTO registrationData = getCurrentFragmentData();
      Bundle bundle = new Bundle();
      bundle.putParcelable(ParcelableKeys.REGISTRATION_DATA, registrationData);
      registrationActivityInterface.saveData(bundle, getFragmentID());
      registrationActivityInterface.moveToNextPage();
    }
  }

  private void moveToPreviousPage() {
    RegistrationActivityInterface registrationActivityInterface =
        ((RegistrationActivityInterface)getActivity());
    if (registrationActivityInterface != null) {
      RegistrationPageDTO data = getCurrentFragmentData();
      Bundle bundle = new Bundle();
      bundle.putParcelable(ParcelableKeys.REGISTRATION_DATA, data);
      registrationActivityInterface.saveData(bundle,getFragmentID());
      registrationActivityInterface.moveToPrevPage();
    }
  }


  public void saveAndRegister(){
   Toast.makeText(getActivity(),"saveAndRegister called",Toast.LENGTH_LONG).show();
    RegistrationActivityInterface registrationActivityInterface =
        ((RegistrationActivityInterface)getActivity());
    if (registrationActivityInterface != null) {
      RegistrationPageDTO registrationData = getCurrentFragmentData();
      Bundle bundle = new Bundle();
      bundle.putParcelable(ParcelableKeys.REGISTRATION_DATA, registrationData);
      registrationActivityInterface.saveData(bundle, getFragmentID());
      registrationActivityInterface.register();
    }

  }


  public  String getFragmentID() {
    if(fragmentID == null || fragmentID.equals(""))
      fragmentID =  ClassUtil.getClassName(new WeakReference<RegisterFragment>(this).get());
    return fragmentID;
  }

  public void setUp() {
    setFragmentID();
    NavigationButtonClickListener
        navigationButtonClickListener =
        new NavigationButtonClickListener(this);

    Button nextButton = getNextButton();
    Button prevButton = getPrevButton();
    Button skipButton = getSkipButton();

    if (nextButton != null) {
      nextButton.setOnClickListener(navigationButtonClickListener);
    }

    if (prevButton != null) {
      prevButton.setOnClickListener(navigationButtonClickListener);
    }

    if (skipButton != null) {
      skipButton.setOnClickListener(navigationButtonClickListener);
    }

    if(saveButton != null) {
      saveButton.setOnClickListener(navigationButtonClickListener);
    }

  }

  @Override
  public void onResume() {
    super.onResume();
    RegistrationPageDTO
        registrationPageDTO =
        ((RegistrationActivityInterface) getActivity()).getFragmentData(getFragmentID());
    if (registrationPageDTO != null) {
      setCurrentFragmentData(registrationPageDTO);
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    saveData();
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      getActivity().setTitle(getTitle());
    }
  }

  private void saveData() {
    RegistrationActivityInterface registrationActivityInterface =
        ((RegistrationActivityInterface) getActivity());
    if (registrationActivityInterface != null) {
      RegistrationPageDTO data = getCurrentFragmentData();
      Bundle bundle = new Bundle();
      bundle.putParcelable(ParcelableKeys.REGISTRATION_DATA, data);
      registrationActivityInterface.saveData(bundle, getFragmentID());
    }
  }

  private static class NavigationButtonClickListener implements View.OnClickListener {

    private RegisterFragment mParent;

    public NavigationButtonClickListener(RegisterFragment parent) {
      mParent = parent;
    }

    @Override
    public void onClick(View view) {
      if (mParent == null) {
        Log.d("Registration Fragment/onClick", "Current Fragment is null");
        return;
      }
      if (!(mParent.getActivity() instanceof RegistrationActivityInterface)) {
        Log.d("Registration Fragment/onClick",
              "Activity is not RegistrationActivityInterface is Null");
        return;
      }
      switch (view.getId()) {
        case R.id.next_button:
          onNextButtonClicked();
          break;
        case R.id.previous_button:
          onPreviousButtonClicked();
          break;
        case R.id.skipButton:
          onSkipButtonClicked();
          break;
        case R.id.save_Button:
          onSaveButtonClicked();
        default:
          break;
      }
      hideKeyboardIfPresent();
    }

    private void onNextButtonClicked() {
      if (mParent != null) {
        mParent.page = MOVE_TO_PAGE.NEXT;
        if(mParent.validateWhenMoveToNextPage())
          mParent.validateData();
        else
          mParent.moveToNextPage();
      }
    }

    private void onPreviousButtonClicked() {
      if (mParent != null) {
        mParent.page = MOVE_TO_PAGE.PREVIOUS;
        if(mParent.validateWhenMoveToPreviousPage())
          mParent.validateData();
        else
          mParent.moveToPreviousPage();
      }


    }

    private void onSkipButtonClicked() {
      if (mParent != null) {
        RegistrationActivityInterface registrationActivityInterface =
            ((RegistrationActivityInterface) mParent.getActivity());
        if (registrationActivityInterface != null) {
          registrationActivityInterface.moveToNextPage();
        }
      }
    }

    private void onSaveButtonClicked(){
      if (mParent != null) {
        mParent.page = MOVE_TO_PAGE.SAVE;
        if(mParent.validateWhenMoveToNextPage())
          mParent.validateData();
        else
          mParent.saveAndRegister();
      }
    }

    private void hideKeyboardIfPresent() {
      if(mParent == null || mParent.getActivity() == null) return;
      InputMethodManager
          inputMethodManager =
          (InputMethodManager) mParent.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(
          (null == mParent.getActivity().getCurrentFocus()) ? null : mParent.getActivity()
              .getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
  }

}
