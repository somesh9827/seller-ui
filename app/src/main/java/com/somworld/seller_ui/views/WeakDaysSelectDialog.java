package com.somworld.seller_ui.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.Util;
import com.somworld.seller_ui.models.OnCompleteListener;
import com.somworld.seller_ui.views.adapters.WeekDaysAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 04/04/15.
 */
public class WeakDaysSelectDialog extends Dialog implements View.OnClickListener {
  private Context mContext;
  private OnCompleteListener mOnCompleteListener;
  private ListView multiSelectList;
  public WeakDaysSelectDialog(Context context,OnCompleteListener onCompleteListener) {
    super(context);
    mContext = context;
    mOnCompleteListener = onCompleteListener;
  }

  public WeakDaysSelectDialog(Context context) {
    super(context);
    mContext = context;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dialog_multiselect_list);
    setTitle(mContext.getString(R.string.WeekDaysDialogTitle));
    multiSelectList = (ListView)findViewById(R.id.multi_select_list);
    multiSelectList.setClickable(true);
    WeekDaysAdapter adapter = new WeekDaysAdapter(mContext);
    multiSelectList.setAdapter(adapter);

    Button saveButton,cancelButton;
    saveButton = (Button)findViewById(R.id.save_button);
    cancelButton = (Button)findViewById(R.id.cancel_button);

    saveButton.setOnClickListener(Util.getWeakReference(this));
    cancelButton.setOnClickListener(Util.getWeakReference(this));
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case  R.id.save_button:
        onSaveButtonClicked();
        break;
      case R.id.cancel_button :
        onCancelButtonClicked();
    }


  }

  private void onSaveButtonClicked() {
    Map<String,String> commaSeperetedClosingDays = new HashMap<String, String>();
    View view;
    StringBuilder allCheckedListItem  = new StringBuilder();
    SparseBooleanArray sparseBooleanArray = multiSelectList.getCheckedItemPositions();
    for(int index = 0; index < multiSelectList.getChildCount();index++) {
      if(sparseBooleanArray.get(index) == true) {
        view = multiSelectList.getAdapter().getView(index,null,null);
        if(allCheckedListItem.length() > 0) allCheckedListItem.append(",");
        allCheckedListItem.append(((TextView)view.findViewById(R.id.multi_select_list_text)).getText().toString());
      }
    }
     Toast.makeText(mContext,allCheckedListItem.toString(),Toast.LENGTH_LONG).show();

  }

  private void onCancelButtonClicked() {

  }

}
