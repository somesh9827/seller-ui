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
import com.somworld.seller_ui.helpers.validators.IValidatorListener;
import com.somworld.seller_ui.helpers.validators.RuleValueAdapter;
import com.somworld.seller_ui.helpers.validators.ValidationError;
import com.somworld.seller_ui.helpers.validators.Validator;
import com.somworld.seller_ui.helpers.validators.rules.NotEmpty;
import com.somworld.seller_ui.helpers.validators.rules.RULE;
import com.somworld.seller_ui.models.dtos.RegistrationPageDTO;
import com.somworld.seller_ui.models.dtos.ShopNameDTO;
import com.somworld.seller_ui.models.dtos.UserInfoDTO;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by somesh.shrivastava on 24/01/15.
 */
public class RegisterFragment_2 extends RegisterFragment {

  private EditText ownerFirstName, ownerLastName;

  private static class DataValidator implements IValidatorListener {

    private RegisterFragment_2 mParent;

    public DataValidator(RegisterFragment_2 registerFragment2){
      mParent = registerFragment2;
    }
    @Override
    public void onValidationFail(ValidationError error) {
      if(mParent != null) {
        mParent.showErrorMessage(error);
      }
    }

    @Override
    public void onValidationSuccess(List<RuleValueAdapter> ruleValueAdapters) {
      if(mParent != null) {
        mParent.hideErrorMessage();
      }
    }
  }

  @Override
  protected String getTitle() {
    String title = getActivity().getString(R.string.register_fragment_title);
    return  String.format(title,2,4);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.register_fragment_2, container, false);
    nextButton = (Button) v.findViewById(R.id.next_button);
    backButton = (Button) v.findViewById(R.id.previous_button);
    ownerFirstName = (EditText) v.findViewById(R.id.owner_first_name);
    ownerLastName = (EditText) v.findViewById(R.id.owner_last_name);
    setUp();
    return v;
  }

  @Override
  protected RegistrationPageDTO getCurrentFragmentData() {
    UserInfoDTO userInfoDTO = new UserInfoDTO();
    userInfoDTO.setFirstName(ownerFirstName.getText().toString());
    userInfoDTO.setLastName(ownerLastName.getText().toString());
    return userInfoDTO;
  }

  @Override
  protected void setCurrentFragmentData(RegistrationPageDTO registrationPageDTO) {
    UserInfoDTO userInfoDTO = (UserInfoDTO) registrationPageDTO;
    ownerFirstName.setText(userInfoDTO.getFirstName());
    ownerLastName.setText(userInfoDTO.getLastName());
  }

  @Override
  protected void validateData() {
    String firstName = ((EditText)getActivity().findViewById(R.id.owner_first_name)).getText().toString();
    String ownerLastName = ((EditText)getActivity().findViewById(R.id.owner_last_name)).getText().toString();

    RULE notEmpty;
    notEmpty = new NotEmpty();

    RuleValueAdapter shopRuleValueAdapter,firstNameRuleValueAdapter,lastNameRuleValueAdapter;

    firstNameRuleValueAdapter = new RuleValueAdapter(R.id.owner_first_name,firstName);
    firstNameRuleValueAdapter.addRule(notEmpty,String.format(getString(R.string.not_empty_error),"First Name"));

    lastNameRuleValueAdapter = new RuleValueAdapter(R.id.owner_last_name,ownerLastName);
    lastNameRuleValueAdapter.addRule(notEmpty,String.format(getString(R.string.not_empty_error),"Last Name"));

    List<RuleValueAdapter> ruleValueAdapters = new ArrayList<RuleValueAdapter>();
    ruleValueAdapters.add(firstNameRuleValueAdapter);
    ruleValueAdapters.add(lastNameRuleValueAdapter);

    WeakReference<RegisterFragment_2> registerFragment_2WeakReference = new WeakReference<RegisterFragment_2>(this);

    Validator validator = new Validator(new DataValidator(registerFragment_2WeakReference.get()));

    validator.validate(ruleValueAdapters);
  }

  @Override
  protected boolean validateWhenMoveToPreviousPage() {
    return false;
  }

  @Override
  protected boolean validateWhenMoveToNextPage() {
    return false;
  }

  void showErrorMessage(ValidationError error){
    Vector<Integer> keys = error.getAllKeys();
    Collections.sort(keys);
    int firstKey = keys.get(0);
    String errorMessage = error.getFirstErrorMessage(firstKey);
    TextView errorTextView = (TextView)getActivity().findViewById(R.id.fragment2_error_message);
    errorTextView.setText(errorMessage);
    errorTextView.setVisibility(View.VISIBLE);
    super.onValidationFail(null);
  }

  void hideErrorMessage(){
    TextView errorTextView = (TextView)getActivity().findViewById(R.id.fragment2_error_message);
    errorTextView.setText("");
    errorTextView.setVisibility(View.GONE);
    super.onValidationSuccess(null);
  }


}
