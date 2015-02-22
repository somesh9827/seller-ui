package com.somworld.seller_ui.views.RegisterFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.validators.IValidatorListener;
import com.somworld.seller_ui.helpers.validators.RuleValueAdapter;
import com.somworld.seller_ui.helpers.validators.ValidationError;
import com.somworld.seller_ui.helpers.validators.Validator;
import com.somworld.seller_ui.helpers.validators.rules.NotEmpty;
import com.somworld.seller_ui.helpers.validators.rules.RULE;
import com.somworld.seller_ui.models.dtos.RegistrationDTO;
import com.somworld.seller_ui.models.dtos.ShopNameDTO;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by somesh.shrivastava on 24/01/15.
 */
public class RegisterFragment_2 extends RegisterFragment {

  private EditText shopName, ownerFirstName, ownerLastName;

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
    return  getActivity().getString(R.string.shopInfoTitle);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.register_fragment_2, container, false);
    nextButton = (Button) v.findViewById(R.id.next_button);
    backButton = (Button) v.findViewById(R.id.previous_button);
    shopName = (EditText) v.findViewById(R.id.shop_name);
    ownerFirstName = (EditText) v.findViewById(R.id.owner_first_name);
    ownerLastName = (EditText) v.findViewById(R.id.owner_last_name);
    setUp();
    return v;
  }

  @Override
  protected RegistrationDTO getCurrentFragmentData() {
    ShopNameDTO shopNameDTO = new ShopNameDTO();
    shopNameDTO.setShopName(shopName.getText().toString());
    shopNameDTO.setOwnerFirstName(ownerFirstName.getText().toString());
    shopNameDTO.setOwnerLastName(ownerLastName.getText().toString());
    return shopNameDTO;
  }

  @Override
  protected void setCurrentFragmentData(RegistrationDTO registrationDTO) {
    ShopNameDTO shopNameDTO = (ShopNameDTO)registrationDTO;
    shopName.setText(shopNameDTO.getShopName());
    ownerFirstName.setText(shopNameDTO.getOwnerFirstName());
    ownerLastName.setText(shopNameDTO.getOwnerLastName());
  }

  @Override
  protected void validateData() {
    String shopName = ((EditText)getActivity().findViewById(R.id.shop_name)).getText().toString();
    String firstName = ((EditText)getActivity().findViewById(R.id.owner_first_name)).getText().toString();
    String ownerLastName = ((EditText)getActivity().findViewById(R.id.owner_last_name)).getText().toString();

    RULE notEmpty;
    notEmpty = new NotEmpty();

    RuleValueAdapter shopRuleValueAdapter,firstNameRuleValueAdapter,lastNameRuleValueAdapter;

    shopRuleValueAdapter = new RuleValueAdapter(R.id.shop_name,shopName);
    shopRuleValueAdapter.addRule(notEmpty,String.format(getString(R.string.not_empty_error),"Shop Name"));

    firstNameRuleValueAdapter = new RuleValueAdapter(R.id.owner_first_name,firstName);
    firstNameRuleValueAdapter.addRule(notEmpty,String.format(getString(R.string.not_empty_error),"First Name"));

    lastNameRuleValueAdapter = new RuleValueAdapter(R.id.owner_last_name,ownerLastName);
    lastNameRuleValueAdapter.addRule(notEmpty,String.format(getString(R.string.not_empty_error),"Last Name"));

    List<RuleValueAdapter> ruleValueAdapters = new ArrayList<RuleValueAdapter>();
    ruleValueAdapters.add(shopRuleValueAdapter);
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
    return true;
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
