package com.somworld.seller_ui.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.somworld.seller_ui.models.OnCompleteListener;
import com.somworld.seller_ui.models.ParcelableKeys;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CreateOffer extends Activity {
    int dateContext  = -1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offer);
        WeakReference<CreateOffer> createOfferWeakReference = new WeakReference<CreateOffer>(this);
        findViewById(R.id.create_offer_save_button).setOnClickListener(new CreateOfferOnClickListener(createOfferWeakReference.get()));
        findViewById(R.id.create_offer_cancel_button).setOnClickListener(new CreateOfferOnClickListener(createOfferWeakReference.get()));
        findViewById(R.id.create_offer_Start_time).setOnClickListener(new CreateOfferOnClickListener(createOfferWeakReference.get()));
        findViewById(R.id.create_offer_End_time).setOnClickListener(new CreateOfferOnClickListener(createOfferWeakReference.get()));

    }

    private void setDateToTextBox(Date date) {
       EditText offerStartTime = (EditText)findViewById(R.id.create_offer_Start_time);
       EditText offerEndTime = (EditText)findViewById(R.id.create_offer_End_time);
        if(dateContext == Utils.START_DATE_CONTEXT && offerStartTime != null)
            offerStartTime.setText(OfferHelper.formatDate(date, Utils.START_DATE_CONTEXT, Utils.getTimeFormat()));
        else if(dateContext == Utils.END_DATE_CONTEXT && offerEndTime != null)
            offerEndTime.setText(OfferHelper.formatDate(date, Utils.END_DATE_CONTEXT, Utils.getTimeFormat()));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_offer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private static class CreateOfferOnClickListener implements View.OnClickListener, OnCompleteListener, IValidatorListener {
        CreateOffer mParent;
        CreateOfferOnClickListener (CreateOffer parent) {
            mParent = parent;
        }



        @Override
        public void onClick(View view) {
            if(mParent == null) return;
            switch (view.getId()) {
                case R.id.create_offer_save_button :
                    createNewOffer();
                    break;
                case R.id.create_offer_cancel_button :
                    cancel();
                    break;
                case R.id.create_offer_Start_time :
                    mParent.dateContext = Utils.START_DATE_CONTEXT;
                    new DateAndTimePickerDialog(mParent,this).show();
                    break;
                case R.id.create_offer_End_time :
                    mParent.dateContext = Utils.END_DATE_CONTEXT;
                    new DateAndTimePickerDialog(mParent,this).show();
                    break;
                default:break;
            }
        }

        private void createNewOffer() {
            if(mParent == null) return;
           //try {
               /* OfferItems offer = new OfferItems();
                String description = ((EditText) mParent.findViewById(R.id.create_offer_description)).getText().toString();
                String productName = ((EditText) mParent.findViewById(R.id.create_offer_product)).getText().toString();
                String startTimeString = ((EditText) mParent.findViewById(R.id.create_offer_Start_time)).getText().toString();
                String endTimeString = ((EditText) mParent.findViewById(R.id.create_offer_End_time)).getText().toString();

                description = description.equals(mParent.getString(R.string.product_description)) ? "" : description;
                productName = productName.equals(mParent.getString(R.string.product_name)) ? "" : productName;
*/
                validate();



           // }catch (ParseException ex) {}

        }

        private void validate() {
                if(mParent == null) return;

            try {
                String description = ((EditText) mParent.findViewById(R.id.create_offer_description)).getText().toString();
                String productName = ((EditText) mParent.findViewById(R.id.create_offer_product)).getText().toString();
                String startTimeString = ((EditText) mParent.findViewById(R.id.create_offer_Start_time)).getText().toString();
                String endTimeString = ((EditText) mParent.findViewById(R.id.create_offer_End_time)).getText().toString();

                Date startTime = Utils.getTimeFormat().parse(startTimeString);
                Date endTime = Utils.getTimeFormat().parse(endTimeString);


                description = description.equals(mParent.getString(R.string.product_description)) ? "" : description;
                productName = productName.equals(mParent.getString(R.string.product_name)) ? "" : productName;

                List<RuleValueAdapter> ruleValueAdapters = new ArrayList<RuleValueAdapter>();
                RULE NotEmptyRule = new NotEmpty();
                RuleValueAdapter<String> ruleValueAdapter1 = new RuleValueAdapter<String>(R.id.create_offer_description, description);
                ruleValueAdapter1.addRule(NotEmptyRule, String.format(mParent.getString(R.string.not_empty),"Product Description"));
                ruleValueAdapters.add(ruleValueAdapter1);

                RULE NotEmptyRule2 = new NotEmpty();
                RuleValueAdapter<String> ruleValueAdapter2 = new RuleValueAdapter<String>(R.id.create_offer_product, productName);
                ruleValueAdapter2.addRule(NotEmptyRule2, String.format(mParent.getString(R.string.not_empty),"Product Name"));
                ruleValueAdapters.add(ruleValueAdapter2);

                RULE NotEmptyRule3 = new NotEmpty();
                RULE minDateRule = new MinDate(startTime);
                RuleValueAdapter<Date> ruleValueAdapter3 = new RuleValueAdapter<Date>(R.id.create_offer_End_time, endTime);
                ruleValueAdapter3.addRule(NotEmptyRule3, String.format(mParent.getString(R.string.not_empty),"End Time"));
                ruleValueAdapter3.addRule(minDateRule,String.format(mParent.getString(R.string.max_error),"End Time","Start Time"));
                ruleValueAdapters.add(ruleValueAdapter3);

                RULE NotEmptyRule4 = new NotEmpty();
                RULE minDateRule1 = new MinDate(Utils.getMinAllowedStartTime(new Date()));
                RuleValueAdapter<Date> ruleValueAdapter4 = new RuleValueAdapter<Date>(R.id.create_offer_Start_time, startTime);
                ruleValueAdapter4.addRule(NotEmptyRule4, String.format(mParent.getString(R.string.not_empty),"Start Time"));
                ruleValueAdapter4.addRule(minDateRule1,String.format(mParent.getString(R.string.max_error),"Start Time","Current Time"));
                ruleValueAdapters.add(ruleValueAdapter4);

                Validator validator = new Validator(this);

                validator.validate(ruleValueAdapters);
        }
            catch (ParseException e){
                Toast.makeText(mParent,"In Correct Data",Toast.LENGTH_LONG).show();
            }

        }

        private void cancel() {
            if(mParent == null) return;
            mParent.finish();
        }

        @Override
        public void complete(int status, Map<String, Object> data) {
            if(mParent == null) return;
            if(status == OnCompleteListener.SUCCESS) {
                if(data.containsKey("date"))
                {
                    Date date = new Date(((Date)(data.get("date"))).getTime());
                    mParent.setDateToTextBox(date);
                }

            } else {

            }
        }

        @Override
        public void onValidationFail(ValidationError error) {
            Vector<Integer> keys = error.getAllKeys();
            int firstKey = keys.get(0);
            String errorMessage = error.getConcatinatedErrorMessage(firstKey);
            Toast.makeText(mParent,errorMessage,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onValidationSuccess(List<RuleValueAdapter> fields) {
            OfferItems offer = new OfferItems();
            for (RuleValueAdapter field: fields) {
                switch (field.getId()) {
                    case R.id.create_offer_description:
                        offer.setDescription(field.getValue().toString());
                        break;
                    case R.id.create_offer_product:
                        offer.setProduct(field.getValue().toString());
                        break;
                    case R.id.create_offer_Start_time:
                        offer.setStartTime((Date)field.getValue());
                        break;
                    case R.id.create_offer_End_time:
                        offer.setEndTime((Date)field.getValue());
                        break;
                    default:
                        break;
                }

            }
                offer.setActive(true);
                offer.setDiscount("");
                offer.setActive(OfferHelper.isValid(offer));

                Bundle mBundle = new Bundle();
                mBundle.putParcelable(ParcelableKeys.OFFER_ITEM, offer);
                Intent showDashBoardIntent = new Intent();
                showDashBoardIntent.putExtras(mBundle);

                mParent.setResult(RESULT_OK, showDashBoardIntent);
                mParent.finish();



        }
    }
}

