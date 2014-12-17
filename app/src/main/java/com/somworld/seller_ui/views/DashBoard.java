package com.somworld.seller_ui.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.FactoryGirl;
import com.somworld.seller_ui.models.OfferItems;
import com.somworld.seller_ui.views.adapters.DashBoardListAdapter;

import java.util.List;


public class DashBoard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ListView offerList = (ListView)findViewById(R.id.dash_board_list);
        List<OfferItems> offers = FactoryGirl.getOffers(5,3);
        offerList.setAdapter(new DashBoardListAdapter(this,offers));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
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
}
