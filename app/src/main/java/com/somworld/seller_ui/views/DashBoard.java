package com.somworld.seller_ui.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.FactoryGirl;
import com.somworld.seller_ui.helpers.OfferHelper;
import com.somworld.seller_ui.helpers.IntentKeys;
import com.somworld.seller_ui.models.OfferItems;
import com.somworld.seller_ui.models.ParcelableKeys;
import com.somworld.seller_ui.views.adapters.DashBoardListAdapter;

import java.util.List;


public class DashBoard extends BaseActivity implements View.OnClickListener {
    private int currentItemPosition = -1;
    private static final int invalidPosition = -1;
    private ListView offerList;
    private List<OfferItems> offers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        offerList = (ListView) findViewById(R.id.dash_board_list);
        offers = FactoryGirl.getOffers(5, 3);
        offerList.setAdapter(new DashBoardListAdapter(this, offers));
        findViewById(R.id.dash_board_add_new_offer).setOnClickListener(this);
        registerForContextMenu(offerList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_dash_board) {
            return false;
        }
        return super.onOptionsItemSelected(item);
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
            currentItemPosition = info.position;
            callUpdateOfferActivity(new OfferItems(offers.get(info.position)));
        }
        else {
            return false;
        }
        return true;
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
        startActivityForResult(intent,IntentKeys.UPDATE_OFFER);
    }

    private void callAddNewOfferActivity() {
        Intent addNewOfferIntent = new Intent(this,CreateOffer.class);
        startActivityForResult(addNewOfferIntent,IntentKeys.ADD_NEW_OFFER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IntentKeys.UPDATE_OFFER :
                if (resultCode == RESULT_OK) {
                    Bundle mBundle = data.getExtras();
                    OfferItems offer = mBundle.getParcelable(ParcelableKeys.OFFER_ITEM);
                    updateListItem(currentItemPosition,offer);
                }
                else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "cancel", Toast.LENGTH_LONG).show();
                }
                break;
            case IntentKeys.ADD_NEW_OFFER:
                if(resultCode == RESULT_OK) {
                    Bundle mBundle = data.getExtras();
                    OfferItems offer =  mBundle.getParcelable(ParcelableKeys.OFFER_ITEM);
                    pushNewListItem(offer);
                }
                else if(resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "cancel", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }


    }

    private void updateListItem(int position, OfferItems newOffer) {
        OfferItems oldOffer = offers.get(position);
        Toast.makeText(this,""+newOffer.getId()+" "+oldOffer.getId(),Toast.LENGTH_LONG).show();
        if(isCurrentItemPositionValid() && newOffer.getId() == oldOffer.getId()) {
            inValidateCurrentItemPosition();
            oldOffer.setActive(newOffer.isActive());
            oldOffer.setStartDate(newOffer.getStartDate());
            oldOffer.setDescription(newOffer.getDescription());
            oldOffer.setEndDate(newOffer.getEndDate());
            oldOffer.setProduct(newOffer.getProduct());
            oldOffer.setDiscount(newOffer.getDiscount());
            ((BaseAdapter) offerList.getAdapter()).notifyDataSetChanged();
        }
    }

    private void pushNewListItem(OfferItems newOffer) {
        OfferItems temp = new OfferItems(newOffer);
        offers.add(0,temp);
        ((BaseAdapter) offerList.getAdapter()).notifyDataSetChanged();
    }

    private void inValidateCurrentItemPosition() {
        currentItemPosition = invalidPosition;
    }

    private boolean isCurrentItemPositionValid() {
        return currentItemPosition != invalidPosition;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dash_board_add_new_offer :
                callAddNewOfferActivity();
                break;
        }
    }
}
