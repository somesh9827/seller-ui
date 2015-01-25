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

  private EditText mContactNumber;

  private EditText mEmail;

  private EditText mPassword;

  @Override
  protected String getTitle() {
    return  getActivity().getString(R.string.SignUpTitle);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.register_fragment1, container, false);
    nextButton = (Button) v.findViewById(R.id.next_button);
    backButton = (Button) v.findViewById(R.id.previous_button);
    mContactNumber = (EditText) v.findViewById(R.id.contact_number);
    mEmail = (EditText) v.findViewById(R.id.email);
    mPassword = (EditText)v.findViewById(R.id.password);
    setUp();
    return v;
  }



  @Override
  protected RegistrationDTO getCurrentFragmentData() {
    MailAndPasswordDTO data = new MailAndPasswordDTO();
    data.setEmail(mContactNumber.getText().toString());
    data.setContact(mEmail.getText().toString());
    data.setPassword(mPassword.getText().toString());
    return data;
  }

  @Override
  protected void setCurrentFragmentData(RegistrationDTO data) {
    MailAndPasswordDTO fragmentData = (MailAndPasswordDTO) data;
    mContactNumber.setText(fragmentData.getContact());
    mEmail.setText(fragmentData.getEmail());

  }
}
