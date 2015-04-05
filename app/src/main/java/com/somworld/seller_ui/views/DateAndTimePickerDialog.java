package com.somworld.seller_ui.views;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.Utils;
import com.somworld.seller_ui.views.callback.IDialogCallback;
import com.somworld.seller_ui.views.callback.OnCompleteListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by somesh.shrivastava on 25/12/14.
 */
public class DateAndTimePickerDialog extends Dialog implements View.OnClickListener {

  private Activity mAct;

  private Button mSave;

  private Button mCancle;

  private DatePicker datePicker;


  private OnCompleteListener mOnCompleteListener;

  public DateAndTimePickerDialog(Activity act, OnCompleteListener onCompleteListener) {
    super(act);
    mAct = act;
    mOnCompleteListener = onCompleteListener;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.dialog_date_and_time_picker);
    datePicker = (DatePicker) findViewById(R.id.date_picker);
    mSave = (Button) findViewById(R.id.date_time_picker_save);
    mCancle = (Button) findViewById(R.id.date_time_picker_cancel);
    mSave.setOnClickListener(this);
    mCancle.setOnClickListener(this);

  }

  public Date getFormatedDate() {
    int day = datePicker.getDayOfMonth();
    int month = datePicker.getMonth();
    int year = datePicker.getYear();
    return Utils.getDateFromYEAR_MONTH_DAY(year, month, day, 0, 0, 0);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {

      case R.id.date_time_picker_save:
        Date date = getFormatedDate();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(IDialogCallback.TAG.DATE, date);
        mOnCompleteListener.complete(OnCompleteListener.SUCCESS, map);
        dismiss();
        break;

      case R.id.date_time_picker_cancel:
        mOnCompleteListener.complete(OnCompleteListener.FAIL, null);
        dismiss();
        break;

      default:
        break;
    }
    dismiss();
  }
}
