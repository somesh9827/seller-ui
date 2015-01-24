package com.somworld.seller_ui.views;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TimePicker;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.helpers.Utils;
import com.somworld.seller_ui.models.OnCompleteListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by somesh.shrivastava on 18/01/15.
 */
public class TimePickerDialog extends Dialog implements View.OnClickListener {

  private Activity mAct;

  private OnCompleteListener mOnCompleteListener;

  private TimePicker fromTimePicker;

  private TimePicker toTimePicker;

  public TimePickerDialog(Activity _context, OnCompleteListener onCompleteListener) {
    super(_context);
    mAct = _context;
    mOnCompleteListener = onCompleteListener;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.dialog_time_picker);
    findViewById(R.id.time_picker_save).setOnClickListener(this);
    findViewById(R.id.time_picker_cancel).setOnClickListener(this);
    fromTimePicker = (TimePicker) findViewById(R.id.from_time_picker);
    toTimePicker = (TimePicker) findViewById(R.id.to_time_picker);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.time_picker_save:
        saveTime();
        break;
      case R.id.time_picker_cancel:
        closeDialog();
        break;
      default:
        break;
    }
    dismiss();
  }

  private void saveTime() {
    Date fromTime = getFormatedTime(fromTimePicker);
    Date toTime = getFormatedTime(toTimePicker);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("fromTime", fromTime);
    map.put("toTime", toTime);
    mOnCompleteListener.complete(OnCompleteListener.SUCCESS, map);
    dismiss();
  }

  private void closeDialog() {
    mOnCompleteListener.complete(OnCompleteListener.FAIL, null);
    dismiss();
  }

  public Date getFormatedTime(TimePicker timePicker) {
    int hour = timePicker.getCurrentHour();
    int min = timePicker.getCurrentMinute();
    return Utils.getDateFromYEAR_MONTH_DAY(0, 0, 0, hour, min, 0);
  }
}
