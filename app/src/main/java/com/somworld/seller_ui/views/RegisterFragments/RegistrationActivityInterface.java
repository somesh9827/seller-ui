package com.somworld.seller_ui.views.RegisterFragments;

import android.os.Bundle;

import com.somworld.seller_ui.models.dtos.RegistrationPageDTO;

/**
 * Created by somesh.shrivastava on 19/01/15.
 */
public interface RegistrationActivityInterface {

  public void moveToNextPage();

  public void moveToPrevPage();

  public void saveData(Bundle bundle, String fragmentID);

  public RegistrationPageDTO getFragmentData(String fragmentID);

  public void register();

}
