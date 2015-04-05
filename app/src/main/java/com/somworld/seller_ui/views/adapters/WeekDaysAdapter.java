package com.somworld.seller_ui.views.adapters;

import com.google.dexmaker.dx.rop.cst.CstArray;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.models.WeekDays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by somesh.shrivastava on 04/04/15.
 */
public class WeekDaysAdapter extends BaseAdapter {
  private LayoutInflater inflater;
  WeekDays [] days;
  public WeekDaysAdapter(Context context) {
    days = WeekDays.values();
    inflater = LayoutInflater.from(context);
  }

  @Override
  public int getCount() {
    return days.length;
  }

  @Override
  public Object getItem(int i) {
    return days[i];
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView == null)
      convertView = inflater.inflate(R.layout.dialog_multiselect_list_row,parent,false);
    TextView textView = (TextView)convertView.findViewById(R.id.multi_select_list_text);
    textView.setText(days[position].toString());
    return convertView;
  }

}
