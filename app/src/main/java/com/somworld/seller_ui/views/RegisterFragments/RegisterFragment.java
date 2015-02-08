package com.somworld.seller_ui.views.RegisterFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.ClassUtil;
import com.somworld.seller_ui.models.ParcelableKeys;
import com.somworld.seller_ui.models.dtos.RegistrationDTO;

import java.lang.ref.WeakReference;

/**
 * Created by somesh.shrivastava on 19/01/15.
 */
public abstract class RegisterFragment extends Fragment {

  private String fragmentID;
  protected Button nextButton, backButton, skipButton = null;

  protected final Button getNextButton() {
    return nextButton;
  }

  protected final Button getPrevButton() {
    return backButton;
  }

  protected final Button getSkipButton() {
    return skipButton;
  }

  protected abstract RegistrationDTO getCurrentFragmentData();

  protected abstract String getTitle();

  protected abstract void setCurrentFragmentData(RegistrationDTO bundle);

  private void setFragmentID() {
    fragmentID = ClassUtil.getClassName(new WeakReference<RegisterFragment>(this).get());
  }

  public String getFragmentID() {
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

  }

  @Override
  public void onResume() {
    super.onResume();
    RegistrationDTO
        registrationDTO =
        ((RegistrationActivityInterface) getActivity()).getFragmentData(getFragmentID());
    if (registrationDTO != null) {
      setCurrentFragmentData(registrationDTO);
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
      RegistrationDTO data = getCurrentFragmentData();
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
        default:
          break;
      }
      hideKeyboardIfPresent();
    }

    private void onNextButtonClicked() {
      if (mParent != null) {
        RegistrationActivityInterface registrationActivityInterface =
            ((RegistrationActivityInterface) mParent.getActivity());
        if (registrationActivityInterface != null) {
          RegistrationDTO data = mParent.getCurrentFragmentData();
          Bundle bundle = new Bundle();
          bundle.putParcelable(ParcelableKeys.REGISTRATION_DATA, data);
          registrationActivityInterface.saveData(bundle, mParent.getFragmentID());
          registrationActivityInterface.moveToNextPage();
        }
      }
    }

    private void onPreviousButtonClicked() {
      if (mParent != null) {
        RegistrationActivityInterface registrationActivityInterface =
            ((RegistrationActivityInterface) mParent.getActivity());
        if (registrationActivityInterface != null) {
          RegistrationDTO data = mParent.getCurrentFragmentData();
          Bundle bundle = new Bundle();
          bundle.putParcelable(ParcelableKeys.REGISTRATION_DATA, data);
          registrationActivityInterface.saveData(bundle, mParent.getFragmentID());
          registrationActivityInterface.moveToPrevPage();
        }
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