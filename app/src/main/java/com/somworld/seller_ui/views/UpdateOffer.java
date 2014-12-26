package com.somworld.seller_ui.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.OfferHelper;
import com.somworld.seller_ui.helpers.Utills;
import com.somworld.seller_ui.models.OfferItems;
import com.somworld.seller_ui.models.OnCompleteListener;
import com.somworld.seller_ui.models.ParcelableKeys;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class UpdateOffer extends Activity implements View.OnClickListener,OnCompleteListener {
    private int dateContext  = -1 ;

    private EditText product;
    private EditText productDescription;
    private EditText offerStartTime;
    private EditText offerEndTime;
    private Button saveButton;
    private Button cancelButton;
    private OfferItems mOffer;


    @Override
    public void complete(int status, Map<String, Object> data) {
        if(status == OnCompleteListener.SUCCESS) {
            if(data.containsKey("date"))
            {
                Date date = new Date(((Date)(data.get("date"))).getTime());
                setDateToTextBox(date);
            }

        } else {

        }
    }

    private void setDateToTextBox(Date date) {
        if(dateContext == Utills.START_DATE_CONTEXT && offerStartTime != null)
            offerStartTime.setText(OfferHelper.formatDate(date, Utills.START_DATE_CONTEXT, Utills.getTimeFormat()));
        else if(dateContext == Utills.END_DATE_CONTEXT && offerEndTime != null)
            offerEndTime.setText(OfferHelper.formatDate(date, Utills.END_DATE_CONTEXT, Utills.getTimeFormat()));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_offer);
        Intent intent = getIntent();
        Bundle mBundle = intent.getExtras();
        mOffer = mBundle.getParcelable(ParcelableKeys.OFFER_ITEM);
        offerStartTime = (EditText)findViewById(R.id.update_offer_Start_time);
        offerEndTime = (EditText)findViewById(R.id.update_offer_End_time);
        product = (EditText)findViewById(R.id.update_offer_product);
        productDescription = (EditText)findViewById(R.id.update_offer_description);
        saveButton = (Button)findViewById(R.id.update_offer_save_button);
        cancelButton = (Button)findViewById(R.id.update_offer_cancel_button);

        product.setText(mOffer.getProduct());
        productDescription.setText(mOffer.getDescription());
        offerStartTime.setText(OfferHelper.formatDate(mOffer.getStartTime(), Utills.START_DATE_CONTEXT, Utills.getTimeFormat()));
        offerEndTime.setText(OfferHelper.formatDate(mOffer.getEndTime(), Utills.END_DATE_CONTEXT, Utills.getTimeFormat()));

        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        offerStartTime.setOnClickListener(this);
        offerEndTime.setOnClickListener(this);
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
            case R.id.update_offer_Start_time :
                dateContext = Utills.START_DATE_CONTEXT;
                new DateAndTimePickerDialog(UpdateOffer.this, this).show();
                break;
            case R.id.update_offer_End_time :
                dateContext = Utills.END_DATE_CONTEXT;
                new DateAndTimePickerDialog(UpdateOffer.this, this).show();
                break;
            case R.id.update_offer_save_button :
                saveOffer();
            case R.id.update_offer_cancel_button :
                cancelUpdate();
            default:
                break;
        }

    }

    private void saveOffer() {
        try {
            mOffer.setProduct(product.getText().toString());
            mOffer.setDescription(productDescription.getText().toString());
            mOffer.setStartTime(Utills.getTimeFormat().parse(offerStartTime.getText().toString()));
            mOffer.setEndTime(Utills.getTimeFormat().parse(offerEndTime.getText().toString()));

            Bundle mBundle = new Bundle();
            mBundle.putParcelable(ParcelableKeys.OFFER_ITEM,mOffer);
            Intent showDashBoardIntent = new Intent();
            showDashBoardIntent.putExtras(mBundle);

            setResult(RESULT_OK,showDashBoardIntent);
            finish();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void cancelUpdate() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
