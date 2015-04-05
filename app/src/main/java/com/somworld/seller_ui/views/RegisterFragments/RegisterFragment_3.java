package com.somworld.seller_ui.views.RegisterFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.common.AppConstant;
import com.somworld.seller_ui.helpers.Util;
import com.somworld.seller_ui.helpers.Utils;
import com.somworld.seller_ui.helpers.validators.IValidatorListener;
import com.somworld.seller_ui.helpers.validators.RuleValueAdapter;
import com.somworld.seller_ui.helpers.validators.ValidationError;
import com.somworld.seller_ui.helpers.validators.Validator;
import com.somworld.seller_ui.helpers.validators.rules.NotEmpty;
import com.somworld.seller_ui.helpers.validators.rules.RULE;
import com.somworld.seller_ui.models.dtos.RegistrationPageDTO;
import com.somworld.seller_ui.models.dtos.ShopNameDTO;
import com.somworld.seller_ui.views.TimePickerDialog;
import com.somworld.seller_ui.views.WeakDaysSelectDialog;
import com.somworld.seller_ui.views.callback.IDialogCallback;
import com.somworld.seller_ui.views.callback.OnCompleteListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by somesh.shrivastava on 03/04/15.
 */
public class RegisterFragment_3 extends RegisterFragment {

  private EditText shopName, shopTiming, closingDays;

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
    shopTiming = (EditText)v.findViewById(R.id.shop_timing);
    closingDays = (EditText)v.findViewById(R.id.shop_closing_day);
    nextButton = (Button)v.findViewById(R.id.next_button);
    backButton = (Button)v.findViewById(R.id.previous_button);
    LocalClickListener localClickListener = new LocalClickListener(Util.getWeakReference(this));
    closingDays.setOnClickListener(localClickListener);
    shopTiming.setOnClickListener(localClickListener);
    setUp();
    return v;
  }

  @Override
  protected RegistrationPageDTO getCurrentFragmentData() {
    ShopNameDTO shopNameDTO = new ShopNameDTO();
    shopNameDTO.setShopName(shopName.getText().toString());
    shopNameDTO.setShopOpeningTime(shopTiming.getText().toString());
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
    shopTiming.setText(shopNameDTO.getShopOpeningTime());
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

  void setShopTiming(String timing) {
    shopTiming.setText(timing);
  }



  @Override
  protected void validateData() {
    String shopName = ((EditText)getActivity().findViewById(R.id.shop_name)).getText().toString();
    String shopTiming = ((EditText)getActivity().findViewById(R.id.shop_timing)).getText().toString();

    RULE notEmpty;
    notEmpty = new NotEmpty();

    RuleValueAdapter shopNameRuleValueAdapter,shopTimingRuleValueAdapter;

    shopNameRuleValueAdapter = new RuleValueAdapter(R.id.shop_name,shopName);
    shopNameRuleValueAdapter.addRule(notEmpty,String.format(getString(R.string.not_empty_error),"Shop Name"));

    shopTimingRuleValueAdapter = new RuleValueAdapter(R.id.shop_timing,shopTiming);
    shopTimingRuleValueAdapter.addRule(notEmpty,String.format(getString(R.string.not_empty_error),"Shop Timing"));

    List<RuleValueAdapter> ruleValueAdapters = new ArrayList<RuleValueAdapter>();
    ruleValueAdapters.add(shopNameRuleValueAdapter);
    ruleValueAdapters.add(shopTimingRuleValueAdapter);

    Validator validator = new Validator(new DataValidator(Util.getWeakReference(this)));

    validator.validate(ruleValueAdapters);
  }



  void setClosingDays(String commaSaperatedDays) {
    closingDays.setText(commaSaperatedDays);
  }

  @Override
  protected boolean validateWhenMoveToPreviousPage() {
    return false;
  }

  @Override
  protected boolean validateWhenMoveToNextPage() {
    return true;
  }


  private static class LocalClickListener implements View.OnClickListener {
    private RegisterFragment_3 mParent;
    LocalClickListener(RegisterFragment_3 parent) {
      mParent = parent;
    }

    @Override
    public void onClick(View view) {
      if (mParent != null && mParent.getActivity() != null) {
        LocalOnCompleteListener completeListener = new LocalOnCompleteListener(mParent);
        switch (view.getId()) {
          case R.id.shop_closing_day:
            new WeakDaysSelectDialog(Util.getWeakReference(mParent.getActivity()),completeListener,null).show();
            break;
          case R.id.shop_timing:
            new TimePickerDialog(mParent.getActivity(),completeListener,mParent.getString(R.string.shop_opening_time),mParent.getString(R.string.shop_closing_time)).show();
            break;
          default:break;
        }
      }
      else {
        Log.d(AppConstant.LOG.TAG,"fregmant_3/onClick/mParent is null");
      }
    }
  }


  private static class LocalOnCompleteListener implements OnCompleteListener {
    private RegisterFragment_3 mParent;

    LocalOnCompleteListener(RegisterFragment_3 parent) {
      mParent = parent;
    }

    @Override
    public void complete(int status, Map<String, Object> data) {
      if(mParent == null) Log.d(AppConstant.LOG.TAG, "Fragment_3 is null when returning from dialog");
      if(status == OnCompleteListener.FAIL) {
        Log.d(AppConstant.LOG.TAG, "Dialog cancel button clicked");
        return;
      }
      if(status == OnCompleteListener.SUCCESS) {
        if(data == null) Log.d(AppConstant.LOG.TAG, "No data from dialog");
        else if(data.containsKey(IDialogCallback.TAG.DAYS)) {
          mParent.setClosingDays((String)data.get(IDialogCallback.TAG.DAYS));
        }
        else if(data.containsKey(IDialogCallback.TAG.FROM_TIME) && data.containsKey(IDialogCallback.TAG.TO_TIME)) {
          setShopTiming(data);
        }
      }
    }

    private void setShopTiming(Map<String, Object> data) {
      Date fromTime = new Date(((Date) (data.get(IDialogCallback.TAG.FROM_TIME))).getTime());
      Date toTime = new Date(((Date) (data.get(IDialogCallback.TAG.TO_TIME))).getTime());
      String shopTiming = Utils.validTimeToValidTimeString(fromTime, toTime);
      mParent.setShopTiming(shopTiming);
    }
  }

}
