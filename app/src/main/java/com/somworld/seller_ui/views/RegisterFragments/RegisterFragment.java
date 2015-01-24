package com.somworld.seller_ui.views.RegisterFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.models.ParcelableKeys;
import com.somworld.seller_ui.models.dtos.RegistrationDTO;

/**
 * Created by somesh.shrivastava on 19/01/15.
 */
public abstract class RegisterFragment extends Fragment {

  private int fragmentID;

  protected abstract Button getNextButton();

  protected abstract Button getPrevButton();

  protected abstract Button getSkipButton();

  protected abstract RegistrationDTO getCurrentFragmentData();

  protected abstract void setCurrentFragmentData(RegistrationDTO bundle);

  private void setFragmentID() {
    fragmentID = FragmentIDMAnager.getNextID();
  }

  public int getFragmentID(){
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
    RegistrationDTO registrationDTO = ((RegistrationActivityInterface) getActivity()).getFragmentData(getFragmentID());
    if (registrationDTO != null) {
        setCurrentFragmentData(registrationDTO);

    }

  }

  @Override
  public void onPause() {
    super.onPause();
    saveData();
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
    }

    private void onNextButtonClicked() {
      if (mParent != null) {
        RegistrationActivityInterface registrationActivityInterface =
            ((RegistrationActivityInterface) mParent.getActivity());
        if (registrationActivityInterface != null) {
          RegistrationDTO data = mParent.getCurrentFragmentData();
          Bundle bundle = new Bundle();
          bundle.putParcelable(ParcelableKeys.REGISTRATION_DATA, data);
          registrationActivityInterface.saveData(bundle,mParent.getFragmentID() );
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
          registrationActivityInterface.saveData(bundle,mParent.getFragmentID() );
          registrationActivityInterface.moveToPrevPage();
        }
      }

    }

    private void onSkipButtonClicked() {
      if (mParent != null) {
        RegistrationActivityInterface registrationActivityInterface =
            ((RegistrationActivityInterface) mParent.getActivity());
        if (registrationActivityInterface != null) {
          RegistrationDTO data = mParent.getCurrentFragmentData();
          registrationActivityInterface.moveToNextPage();
        }
      }
    }

  }

}
final class FragmentIDMAnager{

  private static int currentID = 0x0;

  final static synchronized int getNextID() {
    return currentID++;
  }
}