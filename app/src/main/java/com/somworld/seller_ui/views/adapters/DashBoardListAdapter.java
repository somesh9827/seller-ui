package com.somworld.seller_ui.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.OfferHelper;
import com.somworld.seller_ui.helpers.Utils;
import com.somworld.seller_ui.models.OfferItems;

import java.text.DateFormat;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by somesh.shrivastava on 13/12/14.
 */
public class DashBoardListAdapter extends BaseAdapter {

  private List<OfferItems> mOffers = Collections.emptyList();


  private Context mContext;


  public DashBoardListAdapter(Context _context, List<OfferItems> offers) {

    mContext = _context;
    mOffers = offers;
  }


  @Override
  public int getCount() {
    return mOffers.size();
  }

  @Override
  public Object getItem(int i) {
    return mOffers.get(i);
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int i, View convertView, ViewGroup parent) {
    if (mContext == null) {
      return null;
    }
    if (convertView == null) {

      convertView =
          LayoutInflater.from(mContext).inflate(R.layout.dash_board_list_item, parent, false);
    }

    TextView
        tProductName =
        (TextView) convertView.findViewById(R.id.dash_board_list__item_product_name);
    TextView
        tProductDescription =
        (TextView) convertView.findViewById(R.id.dash_board_list__item_description);
    TextView tStartTime = (TextView) convertView.findViewById(R.id.dash_board_list_item_start_time);
    TextView tEndTime = (TextView) convertView.findViewById(R.id.dash_board_list__item_end_time);
    TextView
        tOfferValidity =
        (TextView) convertView.findViewById(R.id.dash_board_list__item_offer_validity_time);
    ImageView isActive = (ImageView) convertView.findViewById(R.id.dash_board_list_item_is_active);

    OfferItems currentoffer = (OfferItems) getItem(i);

    tProductName.setText(currentoffer.getProduct());
    tProductDescription.setText(currentoffer.getDescription());
    DateFormat df = DateFormat.getDateInstance();
    df.setTimeZone(TimeZone.getDefault());
    tStartTime.setText("Starts : " + df.format(currentoffer.getStartDate()));
    tEndTime.setText("Ends : " + df.format(currentoffer.getEndDate()));
    tOfferValidity.setText("Validity : " + Utils
        .validTimeToValidTimeString(currentoffer.getStartValidTime(),
                                    currentoffer.getEndValidTime()));
    if (OfferHelper.isValid(currentoffer)) {

      isActive.setImageResource(R.drawable.offer_active);
    } else {

      isActive.setImageResource(R.drawable.offer_deactive);
    }
    return convertView;
  }
}
