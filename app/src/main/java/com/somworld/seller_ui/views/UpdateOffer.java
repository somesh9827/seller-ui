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
import com.somworld.seller_ui.helpers.Utills;
import com.somworld.seller_ui.models.OfferItems;
import com.somworld.seller_ui.models.OnCompleteListener;
import com.somworld.seller_ui.models.ParcelableKeys;

import java.util.Date;
import java.util.Map;

public class UpdateOffer extends Activity implements View.OnClickListener,OnCompleteListener {
    private static final int startDateContext = 0;
    private static final int endDateContext = 1;
    private static final int noDateContext = -1;
    private int dateContext  = -1 ;

    private EditText product;
    private EditText product_description;
    private EditText offer_start_time;
    private EditText offer_end_time;


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
        if(dateContext == Utills.START_DATE_CONTEXT && offer_start_time != null)
            offer_start_time.setText(OfferHelper.formatDate(date, Utills.START_DATE_CONTEXT, Utills.getTimeFormat()));
        else if(dateContext == Utills.END_DATE_CONTEXT && offer_end_time != null)
            offer_end_time.setText(OfferHelper.formatDate(date, Utills.END_DATE_CONTEXT, Utills.getTimeFormat()));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_offer);
        Intent intent = getIntent();
        Bundle mBundle = intent.getExtras();
        OfferItems offer = mBundle.getParcelable(ParcelableKeys.OFFER_ITEM);

        offer_start_time = (EditText)findViewById(R.id.update_offer_Start_time);
        offer_end_time = (EditText)findViewById(R.id.update_offer_End_time);
        product = (EditText)findViewById(R.id.update_offer_product);
        product_description = (EditText)findViewById(R.id.update_offer_description);

        product.setText(offer.getProduct());
        product_description.setText(offer.getDescription());
        offer_start_time.setText(OfferHelper.formatDate(offer.getStartTime(), Utills.START_DATE_CONTEXT, Utills.getTimeFormat()));
        offer_end_time.setText(OfferHelper.formatDate(offer.getStartTime(), Utills.END_DATE_CONTEXT, Utills.getTimeFormat()));

        offer_start_time.setOnClickListener(this);
        offer_end_time.setOnClickListener(this);
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

    public void openStartTimeDialog() {
       // new DateAndTimePickerDialog(this).show();
        Toast.makeText(this,"open dialog",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_offer_Start_time :
                dateContext = Utills.START_DATE_CONTEXT;
                break;
            case R.id.update_offer_End_time :
                dateContext = Utills.END_DATE_CONTEXT;
                break;
            default:
                break;
        }
        DateAndTimePickerDialog d = new DateAndTimePickerDialog(UpdateOffer.this, this);
        d.show();
    }
}
