package com.somworld.seller_ui.views.RegisterFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.models.dtos.MailAndPasswordDTO;
import com.somworld.seller_ui.models.dtos.RegistrationDTO;

/**
 * Created by somesh.shrivastava on 18/01/15.
 */
public class RegisterFragment_1 extends RegisterFragment {

  private Button nextButton;

  private Button previousButton;

  private EditText mContactNumber;

  private EditText mEmail;

  private EditText mPassword;

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.register_fragment1, container, false);
    nextButton = (Button) v.findViewById(R.id.next_button);
    previousButton = (Button) v.findViewById(R.id.previous_button);
    mContactNumber = (EditText) v.findViewById(R.id.contact_number);
    mEmail = (EditText) v.findViewById(R.id.email);
    mPassword = (EditText)v.findViewById(R.id.password);
    setUp();
    return v;
  }

  @Override
  protected Button getNextButton() {
    return nextButton;
  }

  @Override
  protected Button getPrevButton() {
    return previousButton;
  }

  @Override
  protected Button getSkipButton() {
    return null;
  }

  @Override
  protected RegistrationDTO getCurrentFragmentData() {
    MailAndPasswordDTO data = new MailAndPasswordDTO();
    data.setEmail("somesh@gmail.com");
    data.setContact("9008427676");
    data.setPassword("1234");
    return data;
  }

  @Override
  protected void setCurrentFragmentData(RegistrationDTO data) {
    MailAndPasswordDTO fragmentData = (MailAndPasswordDTO) data;
    mContactNumber.setText(fragmentData.getContact());
    mEmail.setText(fragmentData.getEmail());

  }
}
