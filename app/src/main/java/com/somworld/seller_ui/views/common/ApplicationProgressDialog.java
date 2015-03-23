package com.somworld.seller_ui.views.common;

import android.app.ProgressDialog;
import android.content.Context;

import lombok.Getter;

/**
 * Created by somesh.shrivastava on 18/03/15.
 */
public class ApplicationProgressDialog {
  private ProgressDialog ringProgressDialog;
  private String heading = "",content = "";
  private Context context;
  public ApplicationProgressDialog(Context context) {
    this.context = context;
  }
  public String getHeading() {
    return heading;
  }

  public ApplicationProgressDialog setHeading(String heading) {
    this.heading = heading;
    return this;
  }

  public String getContent() {
    return content;
  }

  public ApplicationProgressDialog setContent(String content) {
    this.content = content;
    return this;
  }

  public void show() {
    if (context != null) {
      if (ringProgressDialog == null) {
        ringProgressDialog = new ProgressDialog(context);
        ringProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        ringProgressDialog.setTitle(heading);
        ringProgressDialog.setMessage(content);
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setCanceledOnTouchOutside(false);
      }
      ringProgressDialog.show();
    }
  }

  public void dismiss() {
    if(ringProgressDialog != null)
      ringProgressDialog.dismiss();
  }

}
