package com.somworld.seller_ui.views.common;

import com.google.android.gms.location.LocationListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.somworld.seller_ui.R;

import java.lang.ref.WeakReference;


/**
 * Created by somesh.shrivastava on 17/03/15.
 */
public class ApplicationAlertDialog extends DialogFragment {
  private String headingText,contentText,positiveButtonText,negativeButtonText;

  private DialogCallbackInterface callbackInterface;

  private AlertDialog dialog;

  public interface DialogCallbackInterface {
    void onPositiveButtonClicked();
    void onNegativeButtonClicked();
  }


  private static class LocalCallbackListener implements DialogInterface.OnClickListener {

    private ApplicationAlertDialog mParent;

    public LocalCallbackListener(ApplicationAlertDialog parent) {
      mParent = parent;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
      if(mParent != null && mParent.callbackInterface != null) {
        switch (i) {
          case DialogInterface.BUTTON_POSITIVE :
            mParent.callbackInterface.onPositiveButtonClicked();
            break;
          case DialogInterface.BUTTON_NEGATIVE:
            mParent.callbackInterface.onNegativeButtonClicked();
            break;
          default:break;
        }

      }
      mParent.dismiss();
    }
  }

  public String getHeadingText() {
    return headingText;
  }

  public ApplicationAlertDialog setHeadingText(String headingText) {
    this.headingText = headingText;
    return this;
  }

  public String getContentText() {
    return contentText;
  }

  public ApplicationAlertDialog setContentText(String contentText) {
    this.contentText = contentText;
    return this;
  }

  public String getPositiveButtonText() {
    return positiveButtonText;
  }

  public DialogCallbackInterface getCallbackInterface() {
    return callbackInterface;
  }

  public ApplicationAlertDialog setCallbackInterface(DialogCallbackInterface callbackInterface) {
    this.callbackInterface = callbackInterface;
    return this;
  }


  public ApplicationAlertDialog setPositiveButtonText(String positiveButtonText) {
    this.positiveButtonText = positiveButtonText;
    return this;
  }

  public String getNegativeButtonText() {
    return negativeButtonText;
  }

  public ApplicationAlertDialog setNegativeButtonText(String negativeButtonText) {
    this.negativeButtonText = negativeButtonText;
    return this;
  }

  public ApplicationAlertDialog(){
    headingText = "";
    contentText = "";
    positiveButtonText = negativeButtonText = "";
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View dialogView = inflater.inflate(R.layout.dialog_application_alert, null);


    TextView heading = (TextView)dialogView.findViewById(R.id.dialog_heading);
    TextView content = (TextView)dialogView.findViewById(R.id.dialog_content_text);

    if(!headingText.equals(""))
      heading.setText(headingText);
    if(!contentText.equals(""))
      content.setText(contentText);
    String positiveButtonText = this.positiveButtonText.equals("") ? getString(R.string.OKButton) : this.positiveButtonText;
    String negativeButtonText = this.negativeButtonText.equals("") ? getString(R.string.cancel_button_text) : this.negativeButtonText;

    WeakReference<ApplicationAlertDialog> currentWeakReference = new WeakReference<ApplicationAlertDialog>(this);
    LocalCallbackListener localCallbackListener = new LocalCallbackListener(currentWeakReference.get());

    dialog.setView(dialogView).
        setPositiveButton(positiveButtonText,localCallbackListener ).
        setNegativeButton(negativeButtonText,localCallbackListener);

    return dialog.create();
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    try{
      callbackInterface = (DialogCallbackInterface)activity;
    }catch (ClassCastException e) {
      throw new ClassCastException(activity.toString()
                                   + " must implement DialogCallbackInterface");
    }
  }
}
