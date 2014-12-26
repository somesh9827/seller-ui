package com.somworld.seller_ui.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.FactoryGirl;
import com.somworld.seller_ui.helpers.OfferHelper;
import com.somworld.seller_ui.models.IntentKeys;
import com.somworld.seller_ui.models.OfferItems;
import com.somworld.seller_ui.models.ParcelableKeys;
import com.somworld.seller_ui.views.adapters.DashBoardListAdapter;

import java.util.List;


public class DashBoard extends Activity {

    private ListView offerList;
    private List<OfferItems> offers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        offerList = (ListView) findViewById(R.id.dash_board_list);
        offers = FactoryGirl.getOffers(5, 3);
        offerList.setAdapter(new DashBoardListAdapter(this, offers));
        registerForContextMenu(offerList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Delete");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Edit");
        boolean isActiveOffer = OfferHelper.isValid(offers.get(((AdapterContextMenuInfo) menuInfo).position));
        if (isActiveOffer)
            menu.add(0, v.getId(), 0, "Disable");
        else
            menu.add(0, v.getId(), 0, "Enable");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle() == "Disable") {
            DisableOffer(offers.get(info.position));
        } else if (item.getTitle() == "Enable") {
            EnableOffer(offers.get(info.position));
        } else if (item.getTitle() == "Edit") {
            callUpdateOfferActivity(new OfferItems(offers.get(info.position)));
        }
        else {
            return false;
        }
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

    private void EnableOffer(OfferItems offerItem) {

        offerItem.setActive(true);
        ((BaseAdapter) offerList.getAdapter()).notifyDataSetChanged();
    }

    private void DisableOffer(OfferItems offerItem) {

        offerItem.setActive(false);
        ((BaseAdapter) offerList.getAdapter()).notifyDataSetChanged();
    }


    private void callUpdateOfferActivity(OfferItems offer) {
        Intent intent = new Intent(this,UpdateOffer.class);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(ParcelableKeys.OFFER_ITEM,offer);
        intent.putExtras(mBundle);
        startActivity(intent);
    }


}
