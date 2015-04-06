package com.somworld.seller_ui.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.somworld.seller_ui.R;

import java.util.List;

/**
 * Created by somesh.shrivastava on 05/04/15.
 */
public class SingleListAdapter extends BaseAdapter {
  String[] mValues;
  private LayoutInflater inflater;

  public SingleListAdapter(Context context,String[] values) {
    if(context == null || values == null) throw new IllegalArgumentException("Context or values can't be null");
    mValues = values;
    inflater = LayoutInflater.from(context);
  }
  @Override
  public int getCount() {
    return mValues.length;
  }

  @Override
  public Object getItem(int i) {
    return mValues[i];
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup viewGroup) {
    if(convertView == null)
      convertView = inflater.inflate(R.layout.dialog_single_select_row,viewGroup,false);
    TextView textView = (TextView)convertView.findViewById(R.id.single_select_list_text);
    textView.setText(mValues[position].toString());
    return convertView;
  }
}
