package com.somworld.seller_ui.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.OfferHelper;
import com.somworld.seller_ui.helpers.Utils;
import com.somworld.seller_ui.models.OfferItems;
import com.somworld.seller_ui.models.OnCompleteListener;
import com.somworld.seller_ui.models.ParcelableKeys;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

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

    private static class CreateOfferOnClickListener implements View.OnClickListener, OnCompleteListener {
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
            try {
                OfferItems offer = new OfferItems();
                String description = ((EditText) mParent.findViewById(R.id.create_offer_description)).getText().toString();
                String productName = ((EditText) mParent.findViewById(R.id.create_offer_product)).getText().toString();
                String startTimeString = ((EditText) mParent.findViewById(R.id.create_offer_Start_time)).getText().toString();
                String endTimeString = ((EditText) mParent.findViewById(R.id.create_offer_End_time)).getText().toString();

                description = description.equals(mParent.getString(R.string.product_description)) ? "" : description;
                productName = productName.equals(mParent.getString(R.string.product_name)) ? "" : productName;

                Date startTime = Utils.getTimeFormat().parse(startTimeString);
                Date endTime = Utils.getTimeFormat().parse(endTimeString);
                offer.setActive(true);
                offer.setDiscount("");
                offer.setDescription(description);
                offer.setProduct(productName);
                offer.setStartTime(startTime);
                offer.setEndTime(endTime);
                offer.setActive(OfferHelper.isValid(offer));

                Bundle mBundle = new Bundle();
                mBundle.putParcelable(ParcelableKeys.OFFER_ITEM,offer);
                Intent showDashBoardIntent = new Intent();
                showDashBoardIntent.putExtras(mBundle);

                mParent.setResult(RESULT_OK, showDashBoardIntent);
                mParent.finish();

            }catch (ParseException ex) {}

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
    }
}
