package com.somworld.seller_ui.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.views.adapters.SingleListAdapter;
import com.somworld.seller_ui.views.adapters.WeekDaysAdapter;
import com.somworld.seller_ui.views.callback.OnCompleteListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 05/04/15.
 */
public class SingleSelectListDialog extends Dialog implements AdapterView.OnItemClickListener {

  private ListView singleSelectList;
  private Context mContext;
  private String mTitle;
  private List<String> mValues;
  private OnCompleteListener mListener;
  private String mTag;

  public SingleSelectListDialog(Context context,String title,List<String> values,OnCompleteListener listener,String tag) {
    super(context);
    mTitle = title;
    mValues = values;
    mListener = listener;
    mTag = tag;
  }



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dialog_single_select_list);
    setTitle(mTitle);
    singleSelectList = (ListView) findViewById(R.id.multi_select_list);
    singleSelectList.setClickable(true);
    singleSelectList.setOnItemClickListener(this);
    List<String> cities = new ArrayList();
    cities.add("Bangalore");
    cities.add("Pune");
    cities.add("Mumbei");
    cities.add("Delhi");
    mValues = cities;
    SingleListAdapter adapter = new SingleListAdapter(super.getContext(),cities);
    singleSelectList.setAdapter(adapter);
  }

  @Override
  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    Toast.makeText(getContext(),mValues.get(i),Toast.LENGTH_LONG).show();
    Map<String,Object> data  = new HashMap<>();
    data.put(mTag,mValues.get(i));
    mListener.complete(OnCompleteListener.SUCCESS,data);
    dismiss();
  }
}
