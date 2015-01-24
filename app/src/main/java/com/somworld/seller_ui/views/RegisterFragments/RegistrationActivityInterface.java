package com.somworld.seller_ui.views.RegisterFragments;

import android.os.Bundle;

import com.somworld.seller_ui.models.dtos.RegistrationDTO;

/**
 * Created by somesh.shrivastava on 19/01/15.
 */
public interface RegistrationActivityInterface {

  public void moveToNextPage();

  public void moveToPrevPage();

  public void saveData(Bundle bundle, int fragmentID);

  public RegistrationDTO getFragmentData(int fragmentID);

}
