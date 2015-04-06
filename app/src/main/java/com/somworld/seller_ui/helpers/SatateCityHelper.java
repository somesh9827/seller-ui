package com.somworld.seller_ui.helpers;

import android.content.Context;
import android.util.Log;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.common.AppConstant;

import java.util.List;

/**
 * Created by somesh.shrivastava on 06/04/15.
 */
public class SatateCityHelper {
  public static String[] getAllStates(Context context,String country) {
    if(context == null) {
      Log.d(AppConstant.LOG.TAG,"Context is Null in getAllStates()");
      return new String[1];
    }
    String states[] = context.getResources().getStringArray(R.array.state_list);
    return states;
  }

  public static String[] getAllCityByState(Context context,String state) {
    if(context == null) {
      Log.d(AppConstant.LOG.TAG,"Context is Null in getAllStates()");
      return new String[1];
    }
    if(state == null) {
      Log.d(AppConstant.LOG.TAG,"state is Null in getAllStates()");
      return new String[1];
    }
    String name = formateStringValueToName(state);
    int arrayId = context.getResources().getIdentifier(name,"array",context.getPackageName());
    return context.getResources().getStringArray(arrayId);

  }

  private static String formateStringValueToName(String value){
    String formatedString =  value.replaceAll(" +"," ");
    formatedString = formatedString.replaceAll(" ","_");
    return formatedString.toLowerCase();
  }
}
