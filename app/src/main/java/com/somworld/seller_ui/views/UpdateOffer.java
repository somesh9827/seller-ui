package com.somworld.seller_ui.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.Utills;
import com.somworld.seller_ui.models.OnCompleteListener;

import java.util.Date;
import java.util.Map;

public class UpdateOffer extends Activity implements View.OnClickListener,OnCompleteListener {
    private static final int startDateContext = 0;
    private static final int endDateContext = 1;
    private static final int noDateContext = -1;
    private int dateContext  = -1 ;

    private EditText offer_start_time;
    private EditText offer_end_time;


    @Override
    public void complete(int status, Map<String, Object> data) {
        if(status == OnCompleteListener.SUCCESS) {
            if(data.containsKey("date"))
            {
                Date date = new Date(((Date)(data.get("date"))).getTime());
                setDateToTextBox(Utills.parsedDate(Utills.getTimeFormat(),date));
            }

        } else {

        }
    }

    private void setDateToTextBox(String dateString) {
        if(dateContext == noDateContext) return;
        else if(dateContext == startDateContext && offer_start_time != null) offer_start_time.setText("Offer Starts at " + dateString);
        else if(dateContext == endDateContext && offer_end_time != null) offer_end_time.setText(dateString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_offer);
        offer_start_time = (EditText)findViewById(R.id.update_offer_Start_time);
        offer_end_time = (EditText)findViewById(R.id.update_offer_End_time);
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
                dateContext = startDateContext;
                break;
            case R.id.update_offer_End_time :
                dateContext = endDateContext;
                break;
            default:
                break;
        }
        DateAndTimePickerDialog d = new DateAndTimePickerDialog(UpdateOffer.this, this);
        d.show();
    }
}
