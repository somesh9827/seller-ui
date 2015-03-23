package com.somworld.seller_ui.common;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by somesh.shrivastava on 17/03/15.
 */
public class NetworkUtils {

  public static boolean isWiFiOr3GAvailable(Context context) {
    if(context == null) return false;
    ConnectivityManager connectivityManager =
        (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    boolean is3g = false,isWifi = false;
    NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    if(networkInfo != null && networkInfo.isConnectedOrConnecting()) is3g = true;

    //For WiFi Check
    networkInfo =  connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    if(networkInfo != null && networkInfo.isConnectedOrConnecting()) isWifi = true;
    if(!is3g && !isWifi) return false;
    return true;
  }

  public static boolean isGPSAvailable(Context context) {
    if(context != null) {
      final LocationManager
          manager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE );
      return manager.isProviderEnabled(LocationManager.GPS_PROVIDER) ? true : false;
    }
    return false;
  }
}
