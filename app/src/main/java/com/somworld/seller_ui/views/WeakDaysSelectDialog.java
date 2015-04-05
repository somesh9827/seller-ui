package com.somworld.seller_ui.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.common.AppConstant;
import com.somworld.seller_ui.helpers.Util;
import com.somworld.seller_ui.views.adapters.WeekDaysAdapter;
import com.somworld.seller_ui.views.callback.IDialogCallback;
import com.somworld.seller_ui.views.callback.OnCompleteListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 04/04/15.
 */
public class WeakDaysSelectDialog extends Dialog implements View.OnClickListener {

  private Context mContext;
  private OnCompleteListener mOnCompleteListener;
  private ListView multiSelectList;
  public final String mTag;

  public WeakDaysSelectDialog(Context context, OnCompleteListener onCompleteListener, String tag) {
    super(context);
    mContext = context;
    mOnCompleteListener = onCompleteListener;
    mTag = tag;
  }

  public WeakDaysSelectDialog(Context context, String tag) {
    super(context);
    mContext = context;
    mTag = tag;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dialog_multiselect_list);
    setTitle(mContext.getString(R.string.WeekDaysDialogTitle));
    multiSelectList = (ListView) findViewById(R.id.multi_select_list);
    multiSelectList.setClickable(true);
    WeekDaysAdapter adapter = new WeekDaysAdapter(mContext);
    multiSelectList.setAdapter(adapter);

    Button saveButton, cancelButton;
    saveButton = (Button) findViewById(R.id.save_button);
    cancelButton = (Button) findViewById(R.id.cancel_button);

    saveButton.setOnClickListener(Util.getWeakReference(this));
    cancelButton.setOnClickListener(Util.getWeakReference(this));
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.save_button:
        onSaveButtonClicked();
        break;
      case R.id.cancel_button:
        onCancelButtonClicked();
    }


  }

  private void onSaveButtonClicked() {
    Map<String, Object> commaSeperetedClosingDays = new HashMap<String, Object>();
    View view;
    StringBuilder allCheckedListItem = new StringBuilder();
    SparseBooleanArray sparseBooleanArray = multiSelectList.getCheckedItemPositions();
    for (int index = 0; index < multiSelectList.getChildCount(); index++) {
      if (sparseBooleanArray.get(index) == true) {
        view = multiSelectList.getAdapter().getView(index, null, null);
        if (allCheckedListItem.length() > 0) {
          allCheckedListItem.append(",");
        }
        allCheckedListItem.append(
            ((TextView) view.findViewById(R.id.multi_select_list_text)).getText().toString());
      }
    }
    Log.d(AppConstant.LOG.TAG, "selected cloasing days" + allCheckedListItem.toString());
    commaSeperetedClosingDays.put(IDialogCallback.TAG.DAYS, allCheckedListItem.toString());
    if (mOnCompleteListener != null) {
      mOnCompleteListener.complete(mOnCompleteListener.SUCCESS, commaSeperetedClosingDays);
    } else {
      Log.d(AppConstant.LOG.TAG, "mOnCompleteListener is null in weakDays Dialog");
    }

    dismiss();
  }

  private void onCancelButtonClicked() {
    if (mOnCompleteListener != null) {
      mOnCompleteListener.complete(mOnCompleteListener.FAIL, null);
    }
    dismiss();
  }

}
