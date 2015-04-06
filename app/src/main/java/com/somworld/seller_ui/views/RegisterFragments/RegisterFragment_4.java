package com.somworld.seller_ui.views.RegisterFragments;

import android.content.DialogInterface;
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
import com.somworld.seller_ui.helpers.Util;
import com.somworld.seller_ui.helpers.validators.IValidatorListener;
import com.somworld.seller_ui.helpers.validators.RuleValueAdapter;
import com.somworld.seller_ui.helpers.validators.ValidationError;
import com.somworld.seller_ui.helpers.validators.Validator;
import com.somworld.seller_ui.helpers.validators.rules.NotEmpty;
import com.somworld.seller_ui.helpers.validators.rules.PincodeRule;
import com.somworld.seller_ui.helpers.validators.rules.RULE;
import com.somworld.seller_ui.models.dtos.AddressDTO;
import com.somworld.seller_ui.models.dtos.RegistrationPageDTO;
import com.somworld.seller_ui.views.SingleSelectListDialog;
import com.somworld.seller_ui.views.callback.IDialogCallback;
import com.somworld.seller_ui.views.callback.OnCompleteListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by somesh.shrivastava on 24/01/15.
 */
public class RegisterFragment_4 extends RegisterFragment {

  private EditText addressLine1, addressLine2,area,city,state, pinCode;

  private static class DataValidatorListener implements IValidatorListener {
    private RegisterFragment_4 mParent = null;
    public DataValidatorListener(RegisterFragment_4 registerFragment3) {
      this.mParent = registerFragment3;
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

  private static class LocalOnClickListener implements View.OnClickListener {
    private RegisterFragment_4 mParent;
    LocalOnClickListener(RegisterFragment_4 parent) {
      mParent = parent;
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()) {
        case R.id.city :
          new SingleSelectListDialog(Util.getWeakReference(mParent.getActivity()),"select city",null,new LocalCompleteListener(mParent),
                                     IDialogCallback.TAG.CITY).show();
      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.register_fragment_4, container, false);
    saveButton = (Button) v.findViewById(R.id.save_Button);
    backButton = (Button) v.findViewById(R.id.previous_button);
    addressLine1 = (EditText)v.findViewById(R.id.address_line_1);
    addressLine2 = (EditText)v.findViewById(R.id.address_line_2);
    area = (EditText)v.findViewById(R.id.area);
    city = (EditText)v.findViewById(R.id.city);
    state = (EditText)v.findViewById(R.id.state);
    pinCode = (EditText)v.findViewById(R.id.pincode);
    city.setOnClickListener(new LocalOnClickListener(Util.getWeakReference(this)));
    setUp();
    return v;
  }

  @Override
  protected RegistrationPageDTO getCurrentFragmentData() {
    AddressDTO addressDTO = new AddressDTO();
    addressDTO.setAddressLine1(addressLine1.getText().toString());
    addressDTO.setAddressLine2(addressLine2.getText().toString());
    addressDTO.setArea(area.getText().toString());
    addressDTO.setCity(city.getText().toString());
    addressDTO.setState(state.getText().toString());
    addressDTO.setPincode(pinCode.getText().toString());
    return addressDTO;
  }

  @Override
  protected String getTitle() {
    String title = getActivity().getString(R.string.register_fragment_title);
    return  String.format(title,4,4);
  }

  @Override
  protected void setCurrentFragmentData(RegistrationPageDTO registrationPageDTO) {
    AddressDTO addressDTO = (AddressDTO) registrationPageDTO;
    addressLine1.setText(addressDTO.getAddressLine1());
    addressLine2.setText(addressDTO.getAddressLine2());
    area.setText(addressDTO.getArea());
    city.setText(addressDTO.getCity());
    state.setText(addressDTO.getState());
    pinCode.setText(addressDTO.getPincode());
  }

  @Override
  protected void validateData() {
    String addresLine1 = ((EditText)getActivity().findViewById(R.id.address_line_1)).getText().toString();
    String area = ((EditText)getActivity().findViewById(R.id.area)).getText().toString();
    String city = ((EditText)getActivity().findViewById(R.id.city)).getText().toString();
    String state = ((EditText)getActivity().findViewById(R.id.state)).getText().toString();
    String pinCode = ((EditText)getActivity().findViewById(R.id.pincode)).getText().toString();

    RULE notEmptyRule = new NotEmpty();
    RULE pincodeRule = new PincodeRule();

    RuleValueAdapter addressRuleValueAdapter, areaRuleValueAdapter,cityRuleValueAdapter,stateRuleValueAdapter,pinCodeRuleValueAdapter;

    addressRuleValueAdapter = new RuleValueAdapter(R.id.address_line_1,addresLine1);
    addressRuleValueAdapter.addRule(notEmptyRule,String.format(getString(R.string.not_empty_error),"Address Line1"));

    areaRuleValueAdapter = new RuleValueAdapter(R.id.area,area);
    areaRuleValueAdapter.addRule(notEmptyRule,String.format(getString(R.string.not_empty_error),"Area"));

    cityRuleValueAdapter = new RuleValueAdapter(R.id.city,city);
    cityRuleValueAdapter.addRule(notEmptyRule,String.format(getString(R.string.not_empty_error),"City"));

    stateRuleValueAdapter = new RuleValueAdapter(R.id.state,state);
    stateRuleValueAdapter.addRule(notEmptyRule,String.format(getString(R.string.not_empty_error),"State"));

    pinCodeRuleValueAdapter = new RuleValueAdapter(R.id.pincode,pinCode);

    pinCodeRuleValueAdapter.addRule(notEmptyRule,
                                    String.format(getString(R.string.not_empty_error), "Pin Code"));
    pinCodeRuleValueAdapter.addRule(pincodeRule,getString(R.string.pinCode_error));

    List<RuleValueAdapter> ruleValueAdapters = new ArrayList<RuleValueAdapter>();
    ruleValueAdapters.add(addressRuleValueAdapter);
    ruleValueAdapters.add(areaRuleValueAdapter);
    ruleValueAdapters.add(cityRuleValueAdapter);
    ruleValueAdapters.add(stateRuleValueAdapter);
    ruleValueAdapters.add(pinCodeRuleValueAdapter);

    WeakReference<RegisterFragment_4> weakReference = new WeakReference<RegisterFragment_4>(this);
    Validator validator = new Validator(new DataValidatorListener(weakReference.get()));
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
    TextView errorTextView = (TextView)getActivity().findViewById(R.id.fragment4_error_message);
    errorTextView.setText(errorMessage);
    errorTextView.setVisibility(View.VISIBLE);
    super.onValidationFail(null);
  }

  void hideErrorMessage(){
    TextView errorTextView = (TextView)getActivity().findViewById(R.id.fragment4_error_message);
    errorTextView.setText("");
    errorTextView.setVisibility(View.GONE);
    super.onValidationSuccess(null);
  }

  private void setCity(String cityText) {
    city.setText(cityText);
  }

  private static  class LocalCompleteListener implements OnCompleteListener {
    private RegisterFragment_4 mParent;
    LocalCompleteListener(RegisterFragment_4 fragment) {
      mParent = fragment;
    }
    @Override
    public void complete(int status, Map<String, Object> data) {
      Toast.makeText(mParent.getActivity(),data.toString(),Toast.LENGTH_LONG).show();
      if(status == OnCompleteListener.SUCCESS) {
        if(data != null && data.containsKey(IDialogCallback.TAG.CITY)) {
          mParent.setCity((String)data.get(IDialogCallback.TAG.CITY));
        }
      }
    }
  }

}
