package com.somworld.seller_ui.views.RegisterFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.ApplicationConstants;
import com.somworld.seller_ui.helpers.validators.IValidatorListener;
import com.somworld.seller_ui.helpers.validators.RuleValueAdapter;
import com.somworld.seller_ui.helpers.validators.ValidationError;
import com.somworld.seller_ui.helpers.validators.Validator;
import com.somworld.seller_ui.helpers.validators.rules.EmailRule;
import com.somworld.seller_ui.helpers.validators.rules.EqualStringRule;
import com.somworld.seller_ui.helpers.validators.rules.MinLengthRule;
import com.somworld.seller_ui.helpers.validators.rules.PhoneNumberRule;
import com.somworld.seller_ui.helpers.validators.rules.RULE;
import com.somworld.seller_ui.models.dtos.MailAndPasswordDTO;
import com.somworld.seller_ui.models.dtos.RegistrationDTO;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

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
    data.setEmail(mEmail.getText().toString());
    data.setContact(mContactNumber.getText().toString());
    data.setPassword(mPassword.getText().toString());
    return data;
  }

  @Override
  protected void setCurrentFragmentData(RegistrationDTO data) {
    MailAndPasswordDTO fragmentData = (MailAndPasswordDTO) data;
    mContactNumber.setText(fragmentData.getContact());
    mEmail.setText(fragmentData.getEmail());

  }

  @Override
  protected void validateData() {

    String email = ((EditText)getActivity().findViewById(R.id.email)).getText().toString();
    String phoneNumber = ((EditText)getActivity().findViewById(R.id.contact_number)).getText().toString();
    String password = ((EditText)getActivity().findViewById(R.id.password)).getText().toString();
    String confirmPassword = ((EditText)getActivity().findViewById(R.id.conform_password)).getText().toString();
    List<RuleValueAdapter> ruleValueAdapters = new ArrayList<RuleValueAdapter>();

    RULE emailRule,contactRule,minLengthRule,confirmPasswordRule;
    RuleValueAdapter emailRuleAdapter,phoneRuleAdapter,passwordRuleAdapter;

    emailRule = new EmailRule();
    contactRule = new PhoneNumberRule();
    minLengthRule= new MinLengthRule(ApplicationConstants.REGISTRATION.minPasswordLength);
    confirmPasswordRule = new EqualStringRule(confirmPassword);

    emailRuleAdapter = new RuleValueAdapter(R.id.email,email);
    emailRuleAdapter.addRule(emailRule,getString(R.string.email_error));

    phoneRuleAdapter = new RuleValueAdapter(R.id.contact_number,phoneNumber);
    phoneRuleAdapter.addRule(contactRule,getString(R.string.phone_error));

    passwordRuleAdapter = new RuleValueAdapter(R.id.password,password);
    passwordRuleAdapter.addRule(minLengthRule,String.format(getString(R.string.min_length_validator), "password",
                                              ApplicationConstants.REGISTRATION.minPasswordLength));
    passwordRuleAdapter.addRule(confirmPasswordRule,getString(R.string.confirm_password_error));
    ruleValueAdapters.add(passwordRuleAdapter);
    ruleValueAdapters.add(phoneRuleAdapter);
    ruleValueAdapters.add(emailRuleAdapter);

    WeakReference<RegisterFragment_1> registerFragment1WeakReference =
          new WeakReference<RegisterFragment_1>(this);
    Validator validator = new Validator(new DataValidator(registerFragment1WeakReference.get()));
    validator.validate(ruleValueAdapters);


  }

  @Override
  protected boolean validateWhenMoveToPreviousPage() {
    return false;
  }

  @Override
  protected boolean validateWhenMoveToNextPage() {
    return true;
  }

  @Override
  public String getFragmentID() {
    return super.getFragmentID();
  }

  void showErrorMessage(ValidationError error){
    Vector<Integer> keys = error.getAllKeys();
    Collections.sort(keys);
    int firstKey = keys.get(0);
    String errorMessage = error.getFirstErrorMessage(firstKey);
    TextView errorTextView = (TextView)getActivity().findViewById(R.id.fragment1_error_message);
    errorTextView.setText(errorMessage);
    errorTextView.setVisibility(View.VISIBLE);
    super.onValidationFail(null);
  }

  void hideErrorMessage(){
    TextView errorTextView = (TextView)getActivity().findViewById(R.id.fragment1_error_message);
    errorTextView.setText("");
    errorTextView.setVisibility(View.GONE);
    super.onValidationSuccess(null);
  }





  private static class DataValidator implements IValidatorListener {
    private RegisterFragment_1 mParent = null;
    DataValidator(RegisterFragment_1 parent){
        mParent = parent;
    }
    @Override
    public void onValidationFail(ValidationError error) {
      if(mParent != null){
        mParent.showErrorMessage(error);
      }
    }

    @Override
    public void onValidationSuccess(List<RuleValueAdapter> ruleValueAdapters) {
      if(mParent != null){
        mParent.hideErrorMessage();
      }
    }
  }

}