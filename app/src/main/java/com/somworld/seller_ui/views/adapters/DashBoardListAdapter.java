package com.somworld.seller_ui.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.somworld.seller_ui.R;
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


    public DashBoardListAdapter(Context _context,List<OfferItems> offers) {

        mContext =  _context;
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
        if(mContext == null) return null;
        if(convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.dash_board_list_item, parent,false);
        }

        TextView tProductName = (TextView)convertView.findViewById(R.id.dash_board_list__item_product_name);
        TextView tProductDescription = (TextView)convertView.findViewById(R.id.dash_board_list__item_description);
        TextView tStartTime  = (TextView)convertView.findViewById(R.id.dash_board_list_item_start_time);
        TextView tEndTime = (TextView)convertView.findViewById(R.id.dash_board_list__item_end_time);

        OfferItems currentoffer = (OfferItems)getItem(i);
        tProductName.setText(currentoffer.getProduct());
        tProductDescription.setText(currentoffer.getDescription());
        DateFormat df = DateFormat.getDateTimeInstance();
        df.setTimeZone(TimeZone.getDefault());
        tStartTime.setText("Offers Start From : "+ df.format(currentoffer.getStartTime()));
        tEndTime.setText("Offers End at : "+ df.format(currentoffer.getEndTime()));

        return convertView;
    }
}
