package com.somworld.seller_ui.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.OfferHelper;
import com.somworld.seller_ui.helpers.Utils;
import com.somworld.seller_ui.helpers.validators.IValidatorListener;
import com.somworld.seller_ui.helpers.validators.RuleValueAdapter;
import com.somworld.seller_ui.helpers.validators.ValidationError;
import com.somworld.seller_ui.helpers.validators.Validator;
import com.somworld.seller_ui.helpers.validators.rules.MinDate;
import com.somworld.seller_ui.helpers.validators.rules.NotEmpty;
import com.somworld.seller_ui.helpers.validators.rules.RULE;
import com.somworld.seller_ui.models.OfferItems;
import com.somworld.seller_ui.views.callback.IDialogCallback;
import com.somworld.seller_ui.views.callback.OnCompleteListener;
import com.somworld.seller_ui.models.ParcelableKeys;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class UpdateOffer extends Activity implements View.OnClickListener, OnCompleteListener {

  private int dateContext = -1;

  private EditText product;
  private EditText productDescription;
  private EditText offerStartDate;
  private EditText offerEndDate;
  private EditText validOfferTime;
  private Button saveButton;
  private Button cancelButton;
  private OfferItems mOffer;


  @Override
  public void complete(int status, Map<String, Object> data) {
    if (status == OnCompleteListener.SUCCESS) {
      if (data.containsKey("date")) {
        Date date = new Date(((Date) (data.get("date"))).getTime());
        setDateToTextBox(date);
      } else if (data.containsKey("fromTime") && data.containsKey("toTime")) {

      }

    } else {

    }
  }

  private void setDateToTextBox(Date date) {
    if (dateContext == Utils.START_DATE_CONTEXT && offerStartDate != null) {
      offerStartDate
          .setText(OfferHelper.formatDate(date, Utils.START_DATE_CONTEXT, Utils.getDateFormat()));
    } else if (dateContext == Utils.END_DATE_CONTEXT && offerEndDate != null) {
      offerEndDate
          .setText(OfferHelper.formatDate(date, Utils.END_DATE_CONTEXT, Utils.getDateFormat()));
    }

  }

  private void setTimeToTextBox(Date fromTime, Date toTime) {
    String ValidOfferTimeString = Utils.validTimeToValidTimeString(fromTime, toTime);
    EditText validOfferTimeText = (EditText) findViewById(R.id.update_offer_valid_time);
    validOfferTimeText.setText(ValidOfferTimeString);

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_update_offer);
    Intent intent = getIntent();
    Bundle mBundle = intent.getExtras();
    mOffer = mBundle.getParcelable(ParcelableKeys.OFFER_ITEM);
    offerStartDate = (EditText) findViewById(R.id.update_offer_start_date);
    offerEndDate = (EditText) findViewById(R.id.update_offer_end_date);
    product = (EditText) findViewById(R.id.update_offer_product);
    productDescription = (EditText) findViewById(R.id.update_offer_description);
    saveButton = (Button) findViewById(R.id.update_offer_save_button);
    cancelButton = (Button) findViewById(R.id.update_offer_cancel_button);
    validOfferTime = (EditText) findViewById(R.id.update_offer_valid_time);

    product.setText(mOffer.getProduct());
    productDescription.setText(mOffer.getDescription());
    offerStartDate.setText(OfferHelper.formatDate(mOffer.getStartDate(), Utils.START_DATE_CONTEXT,
                                                  Utils.getDateFormat()));
    offerEndDate.setText(
        OfferHelper.formatDate(mOffer.getEndDate(), Utils.END_DATE_CONTEXT, Utils.getDateFormat()));
    validOfferTime.setText(
        Utils.validTimeToValidTimeString(mOffer.getStartValidTime(), mOffer.getEndValidTime()));
    WeakReference<UpdateOffer> updateOfferWeakReference = new WeakReference<UpdateOffer>(this);
    UpdateOfferOnClickListener
        updateOfferOnClickListener =
        new UpdateOfferOnClickListener(updateOfferWeakReference.get());

    saveButton.setOnClickListener(updateOfferOnClickListener);
    cancelButton.setOnClickListener(updateOfferOnClickListener);
    offerStartDate.setOnClickListener(updateOfferOnClickListener);
    offerEndDate.setOnClickListener(updateOfferOnClickListener);
    validOfferTime.setOnClickListener(updateOfferOnClickListener);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.update_offer, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }


  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.update_offer_start_date:
        dateContext = Utils.START_DATE_CONTEXT;
        new DateAndTimePickerDialog(UpdateOffer.this, this).show();
        break;
      case R.id.update_offer_end_date:
        dateContext = Utils.END_DATE_CONTEXT;
        new DateAndTimePickerDialog(UpdateOffer.this, this).show();
        break;
      case R.id.update_offer_save_button:
        saveOffer();
        break;
      case R.id.update_offer_cancel_button:
        cancelUpdate();
        break;
      default:
        break;
    }

  }

  private void setErrorMessage(String errorMessage) {
    TextView errorTextView = (TextView) findViewById(R.id.update_offer_error_message);
    errorTextView.setText(errorMessage);
    errorTextView.setVisibility(View.VISIBLE);
  }

  private void resetErrorMessage() {
    TextView errorTextView = (TextView) findViewById(R.id.update_offer_error_message);
    errorTextView.setText("");
    errorTextView.setVisibility(View.GONE);
  }

  private void saveOffer() {
    try {
      mOffer.setProduct(product.getText().toString());
      mOffer.setDescription(productDescription.getText().toString());
      //mOffer.setStartDate(Utils.getTimeFormat().parse(offerStartDate.getText().toString()));
      mOffer.setStartDate(offerStartDate.getText().toString(), Utils.getDateFormat());
      //mOffer.setEndDate(Utils.getTimeFormat().parse(offerEndDate.getText().toString()));
      mOffer.setEndDate(offerEndDate.getText().toString(), Utils.getDateFormat());
      Bundle mBundle = new Bundle();
      mBundle.putParcelable(ParcelableKeys.OFFER_ITEM, mOffer);
      Intent showDashBoardIntent = new Intent();
      showDashBoardIntent.putExtras(mBundle);

      setResult(RESULT_OK, showDashBoardIntent);
      finish();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }


  private void cancelUpdate() {
    setResult(RESULT_CANCELED);
    finish();
  }

  private static class UpdateOfferOnClickListener
      implements View.OnClickListener, OnCompleteListener, IValidatorListener {

    private UpdateOffer mParent;

    UpdateOfferOnClickListener(UpdateOffer offerActivity) {
      mParent = offerActivity;
    }


    @Override
    public void onClick(View view) {
      if (mParent == null) {
        return;
      }
      switch (view.getId()) {
        case R.id.update_offer_start_date:
          mParent.dateContext = Utils.START_DATE_CONTEXT;
          new DateAndTimePickerDialog(mParent, this).show();
          break;
        case R.id.update_offer_end_date:
          mParent.dateContext = Utils.END_DATE_CONTEXT;
          new DateAndTimePickerDialog(mParent, this).show();
          break;
        case R.id.update_offer_valid_time:
          new TimePickerDialog(mParent, this,mParent.getString(R.string.offer_valid_from),mParent.getString(R.string.offer_valid_till)).show();
          break;
        case R.id.update_offer_save_button:
          validateOffer();
          break;
        case R.id.update_offer_cancel_button:
          mParent.cancelUpdate();
          break;
        default:
          break;
      }
    }


    private void validateOffer() {
      if (mParent == null) {
        return;
      }

      try {
        String
            description =
            ((EditText) mParent.findViewById(R.id.update_offer_description)).getText().toString();
        String
            productName =
            ((EditText) mParent.findViewById(R.id.update_offer_product)).getText().toString();
        String
            startTimeString =
            ((EditText) mParent.findViewById(R.id.update_offer_start_date)).getText().toString();
        String
            endTimeString =
            ((EditText) mParent.findViewById(R.id.update_offer_end_date)).getText().toString();

        Date startTime = Utils.getDateFormat().parse(startTimeString);
        Date endTime = Utils.getDateFormat().parse(endTimeString);

        description =
            description.equals(mParent.getString(R.string.product_description)) ? "" : description;
        productName =
            productName.equals(mParent.getString(R.string.product_name)) ? "" : productName;

        List<RuleValueAdapter> ruleValueAdapters = new ArrayList<RuleValueAdapter>();

        RULE NotEmptyRule2 = new NotEmpty();
        RuleValueAdapter<String>
            ruleValueAdapter2 =
            new RuleValueAdapter<String>(R.id.update_offer_product, productName);
        ruleValueAdapter2.addRule(NotEmptyRule2, String
            .format(mParent.getString(R.string.not_empty_error), "Product Name"));
        ruleValueAdapters.add(ruleValueAdapter2);

        RULE NotEmptyRule = new NotEmpty();
        RuleValueAdapter<String>
            ruleValueAdapter1 =
            new RuleValueAdapter<String>(R.id.update_offer_description, description);
        ruleValueAdapter1.addRule(NotEmptyRule, String
            .format(mParent.getString(R.string.not_empty_error), "Product Description"));
        ruleValueAdapters.add(ruleValueAdapter1);

        RULE NotEmptyRule3 = new NotEmpty();
        RULE minDateRule = new MinDate(startTime);
        RuleValueAdapter<Date>
            ruleValueAdapter3 =
            new RuleValueAdapter<Date>(R.id.update_offer_end_date, endTime);
        ruleValueAdapter3.addRule(NotEmptyRule3,
                                  String.format(mParent.getString(R.string.not_empty_error), "End Date"));
        ruleValueAdapter3.addRule(minDateRule, String
            .format(mParent.getString(R.string.min_error), "Start Date", "End Date"));
        ruleValueAdapters.add(ruleValueAdapter3);

        RULE NotEmptyRule4 = new NotEmpty();
        RULE minDateRule1 = new MinDate(Utils.getMinAllowedStartTime(Utils.getCurrentDate()));
        RuleValueAdapter<Date>
            ruleValueAdapter4 =
            new RuleValueAdapter<Date>(R.id.update_offer_start_date, startTime);
        ruleValueAdapter4.addRule(NotEmptyRule4, String
            .format(mParent.getString(R.string.not_empty_error), "Start Date"));
        ruleValueAdapter4.addRule(minDateRule1, String
            .format(mParent.getString(R.string.min_error), "Start Date", "Current Date"));
        ruleValueAdapters.add(ruleValueAdapter4);

        Validator validator = new Validator(this);

        validator.validate(ruleValueAdapters);
      } catch (ParseException e) {
        Toast.makeText(mParent, "In Correct Data", Toast.LENGTH_LONG).show();
      }

    }


    @Override
    public void complete(int status, Map<String, Object> data) {
      if (mParent == null) {
        return;
      }
      if (status == OnCompleteListener.SUCCESS) {
        if (data.containsKey(IDialogCallback.TAG.DATE)) {
          Date date = new Date(((Date) (data.get(IDialogCallback.TAG.DATE))).getTime());
          mParent.setDateToTextBox(date);
        } else if (data.containsKey(IDialogCallback.TAG.FROM_TIME) && data.containsKey(IDialogCallback.TAG.TO_TIME)) {
          Date fromTime = new Date(((Date) (data.get(IDialogCallback.TAG.FROM_TIME))).getTime());
          Date toTime = new Date(((Date) (data.get(IDialogCallback.TAG.TO_TIME))).getTime());
          mParent.setTimeToTextBox(fromTime, toTime);
        }

      } else {

      }
    }

    @Override
    public void onValidationFail(ValidationError error) {
      Vector<Integer> keys = error.getAllKeys();
      int firstKey = keys.get(0);
      String errorMessage = error.getFirstErrorMessage(firstKey);
      //Toast.makeText(mParent,errorMessage,Toast.LENGTH_LONG).show();
      mParent.setErrorMessage(errorMessage);
    }

    @Override
    public void onValidationSuccess(List<RuleValueAdapter> ruleValueAdapters) {
      OfferItems offer = new OfferItems();
      for (RuleValueAdapter field : ruleValueAdapters) {
        switch (field.getId()) {
          case R.id.update_offer_description:
            offer.setDescription(field.getValue().toString());
            break;
          case R.id.update_offer_product:
            offer.setProduct(field.getValue().toString());
            break;
          case R.id.update_offer_start_date:
            offer.setStartDate((Date) field.getValue());
            break;
          case R.id.update_offer_end_date:
            offer.setEndDate((Date) field.getValue());
            break;
          default:
            break;
        }

      }
      offer.setId(mParent.mOffer.getId());
      offer.setActive(true);
      offer.setDiscount("");
      offer.setActive(OfferHelper.isValid(offer));

      String
          validTimeString =
          ((EditText) mParent.findViewById(R.id.update_offer_valid_time)).getText().toString();
      try {
        Map<String, Date> validTime = Utils.validTimeStringToValidTime(validTimeString);
        offer.setStartValidTime(validTime.get("fromTime"));
        offer.setEndValidTime(validTime.get("toTime"));
      } catch (ParseException e) {
        e.printStackTrace();
      }

      Bundle mBundle = new Bundle();

      mBundle.putParcelable(ParcelableKeys.OFFER_ITEM, offer);
      Intent showDashBoardIntent = new Intent();
      showDashBoardIntent.putExtras(mBundle);

      mParent.resetErrorMessage();
      mParent.setResult(RESULT_OK, showDashBoardIntent);
      mParent.resetErrorMessage();
      mParent.finish();
    }
  }


}
