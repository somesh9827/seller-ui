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
import com.somworld.seller_ui.models.dtos.RegistrationPageDTO;
import com.somworld.seller_ui.models.dtos.ShopNameDTO;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by somesh.shrivastava on 03/04/15.
 */
public class RegisterFragment_3 extends RegisterFragment {

  private EditText shopName,openingTime,closingTime,closingDays;

  private static class DataValidator implements IValidatorListener {

    private RegisterFragment_3 mParent;

    public DataValidator(RegisterFragment_3 registerFragment_3){
      mParent = registerFragment_3;
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
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.register_fragment_3,container,false);
    shopName = (EditText)v.findViewById(R.id.shop_name);
    openingTime= (EditText)v.findViewById(R.id.shop_opening_time);
    closingTime = (EditText)v.findViewById(R.id.shop_closing_time);
    closingDays = (EditText)v.findViewById(R.id.shop_closing_day);
    nextButton = (Button)v.findViewById(R.id.next_button);
    backButton = (Button)v.findViewById(R.id.previous_button);
    setUp();
    return v;
  }

  @Override
  protected RegistrationPageDTO getCurrentFragmentData() {
    ShopNameDTO shopNameDTO = new ShopNameDTO();
    shopNameDTO.setShopName(shopName.getText().toString());
    shopNameDTO.setShopOpeningTime(openingTime.getText().toString());
    shopNameDTO.setShopClosingTime(closingTime.getText().toString());
    shopNameDTO.setShopClosingDays(closingDays.getText().toString());
    return shopNameDTO;
  }

  @Override
  protected String getTitle() {
    return getActivity().getString(R.string.shopInfoTitle);
  }

  @Override
  protected void setCurrentFragmentData(RegistrationPageDTO bundle) {
    ShopNameDTO shopNameDTO = (ShopNameDTO)bundle;
    shopName.setText(shopNameDTO.getShopName());
    openingTime.setText(shopNameDTO.getShopOpeningTime());
    closingTime.setText(shopNameDTO.getShopClosingTime());
    closingDays.setText(shopNameDTO.getShopClosingDays());
  }


  void showErrorMessage(ValidationError error) {
    Vector<Integer> keys = error.getAllKeys();
    Collections.sort(keys);
    int firstKey = keys.get(0);
    String errorMessage = error.getFirstErrorMessage(firstKey);
    TextView errorTextView = (TextView)getActivity().findViewById(R.id.fragment3_error_message);
    errorTextView.setText(errorMessage);
    errorTextView.setVisibility(View.VISIBLE);
    super.onValidationFail(null);
  }

  void hideErrorMessage() {
    TextView errorTextView = (TextView)getActivity().findViewById(R.id.fragment3_error_message);
    errorTextView.setText("");
    errorTextView.setVisibility(View.GONE);
    super.onValidationSuccess(null);
  }

  @Override
  protected boolean validateWhenMoveToPreviousPage() {
    return false;
  }

  @Override
  protected boolean validateWhenMoveToNextPage() {
    return false;
  }

}
